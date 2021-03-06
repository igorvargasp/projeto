package br.com.ufsm.projeto.compasso.produto.controller;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.projeto.compasso.produto.controller.form.ProdutoForm;
import br.com.ufsm.projeto.compasso.produto.model.Produto;
import br.com.ufsm.projeto.compasso.produto.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoService service;
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Produto>> listarDisponiveis (@RequestParam(required = false) String nome, Pageable paginacao) {
		try {
			List<Produto> produtos = (nome != null && !nome.isEmpty()) ? 
					service.findByName(nome) : service.findAll();
			produtos = produtos.stream().filter(p -> p.getDisponivel()).collect(Collectors.toList());
			Page<Produto> disponiveis = new PageImpl<Produto>(produtos, paginacao, produtos.size());
			return ResponseEntity.ok(disponiveis);
		} catch (PropertyReferenceException e) {
			LOGGER.info("Campo de ordenacao indevido " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public List<Produto> listar (String nome) {
		List<Produto> produtos = (nome != null && !nome.isEmpty()) ? 
				service.findByName(nome) : service.findAll();
		return produtos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> detalhe (@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (Exception e) {
			LOGGER.info("Erro ao pesquisar produto " + e);
		}
		LOGGER.info("Produto não encontrado");
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Produto> cadastrarProduto(@Validated  @RequestBody ProdutoForm form) throws IOException{
		try {
			Produto produto = new Produto(form.getNome(), form.getPreco(), form.getQuantidade(), form.getDisponivel());
			produto = service.save(produto);
			LOGGER.info("Produto cadastrado " + produto.getId());
			return ResponseEntity.ok(produto);
		} catch (Exception e) {
			LOGGER.info("Erro no cadastro de produto " + e);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<Produto> remover(@PathVariable Long id) {	
		try {
			Boolean deleted = service.deleteById(id);
			if (deleted) {
				LOGGER.info("Produto Removido " + id);
				return ResponseEntity.ok().build();
			}
			LOGGER.info("Não foi possível remover o produto");
		} catch (Exception e) {
			LOGGER.info("Erro na remoção do produto " + e);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<Produto> update (@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
		try {
			Produto produto = service.atualizar(id, form);
			if (produto != null) {
				LOGGER.info("Produto atualizado " + id);
				return ResponseEntity.ok(produto);
			}
			LOGGER.info("Produto não encontrado");
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			LOGGER.info("Erro na atualizacao de produto " + e);
		}
		return ResponseEntity.notFound().build();
	}
}

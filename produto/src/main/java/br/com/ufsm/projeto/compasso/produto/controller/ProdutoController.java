package br.com.ufsm.projeto.compasso.produto.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.projeto.compasso.produto.model.Produto;
import br.com.ufsm.projeto.compasso.produto.repository.*;




@RestController
public class ProdutoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);
	
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	
	@GetMapping(value = "/produto")
	public List<Produto> listar(){
		List<Produto> produto = produtoRepository.findAll();
		
		return produto;
	}
	
	@PostMapping(value = "/produto")
	public ResponseEntity<Produto> cadastrarProduto(@Validated  @RequestBody Produto produto) throws IOException{
		
		try {
					
			LOGGER.info("Produto cadastrado "+produto.getId());
			return ResponseEntity.ok(produtoRepository.save(produto));	
		} catch (Exception e) {
			LOGGER.info("Erro no cadastro de produto "+e);
		}
		
		return  ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/produto/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
			
		try {
			Optional<Produto> optional = produtoRepository.findById(id);
			if(optional.isPresent()) {
				produtoRepository.deleteById(id);
				LOGGER.info("Produto Atualizado "+id);
				return ResponseEntity.ok().build();
			}	
		} catch (Exception e) {
			LOGGER.info("Erro na remoção do produto "+e);
		}
		

		return ResponseEntity.notFound().build();
	}
	
	
	@Transactional
	@PutMapping(value = "/produto/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable("id")Long id, @RequestBody Produto produto){
			
			try {
				
				Optional<Produto> optional = produtoRepository.findById(id);
				if(optional.isPresent()) {
					Produto produtoAtualizado = new Produto();
					produtoAtualizado.atualizar(produto.getId(), produtoRepository);
					LOGGER.info("Produto Atualizado "+produto.getId());
					return ResponseEntity.ok(produtoAtualizado);
				}
			} catch (Exception e) {
				LOGGER.info("Erro na atualização do Produto "+e);
			}
			
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/produto/disponivel/{id}")
	public ResponseEntity<?> produtoDisponivel(@PathVariable("id") Long id){
			
		try {
			Optional<Produto> optional = produtoRepository.findById(id);
			if(optional.isPresent()) {
				Produto produto = produtoRepository.getOne(id);
				LOGGER.info("Procura Produto disponivel "+ produto.getId());
				if(produto.getDisponivel() == true) {
					Map<Double, Boolean> novo = new HashMap<Double, Boolean>();			
					novo.put(produto.getPreco(), produto.getDisponivel());
					LOGGER.info("Produto disponivel "+ id);
				return ResponseEntity.status(HttpStatus.OK).body(novo);
				}
			}
					
		} catch (Exception e) {
			LOGGER.info("Erro na disponibilidade do produto "+e);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Indisponivel");
	}
	
	
	
}

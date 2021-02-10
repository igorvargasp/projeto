package br.com.ufsm.projeto.compasso.produto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ufsm.projeto.compasso.produto.controller.form.ProdutoForm;
import br.com.ufsm.projeto.compasso.produto.model.Produto;
import br.com.ufsm.projeto.compasso.produto.repository.ProdutoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service 
public class ProdutoService {
	private final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
	private ProdutoRepository prodRepository;

	public ProdutoService(ProdutoRepository prodRepository) { 
		this.prodRepository = prodRepository;
	}

	public Page<Produto> findAll(Pageable paginacao) {
		return prodRepository.findAll(paginacao);
	}
	
	public List<Produto> findAll() {
		return prodRepository.findAll();
	}
	
	public List<Produto> findByName(String nome) {
		return prodRepository.findByNome(nome);
	}
	
	public Page<Produto> findByName(String nome, Pageable paginacao) {
		List<Produto> produto = prodRepository.findByNome(nome);
        return new PageImpl<Produto>(produto, paginacao, produto.size());
	}
	
	public Produto findById(Long id) {
		Optional<Produto> produto = prodRepository.findById(id);
		if (produto.isPresent()) {
			LOGGER.info("Produto encontrado " + produto.get().getId());
			return produto.get();
		}
		LOGGER.info("Produto não encontrado");
		return null;
	}

	public Boolean deleteById (Long id) {
		Produto produto = findById(id);
		if (produto != null) {
			prodRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Produto save(Produto usuario) {
		if (usuario == null) { 
			LOGGER.error("Usuario é NULL!!!");
			return null;
		}
		return prodRepository.save(usuario);
	}
	
	public Produto atualizar(Long id, ProdutoForm form) {
		Produto produto = findById(id);
		produto.setNome(form.getNome());
		produto.setPreco(form.getPreco());
		produto.setDisponivel(form.getDisponivel());
		produto.setQuantidade(form.getQuantidade());
		return produto;
	}
}
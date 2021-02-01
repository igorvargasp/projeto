package br.com.ufsm.projeto.compasso.produto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.ufsm.projeto.compasso.produto.repository.*;

@Entity
@Table(name = "produto")
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "nome")
	private String nome;
	
	
	@Column(name = "preco")
	private Double preco;
	
	
	@Column(name = "disponivel")
	private Boolean disponivel;
	
	
	public Produto() {
		
	}
	
	public Produto(String nome, Double preco, Boolean disponivel, Long id) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.disponivel = disponivel;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.getOne(id);
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		produto.setDisponivel(this.disponivel);		
		return produto;
	}
	
	
	
	
}

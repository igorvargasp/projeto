package br.com.ufsm.projeto.compasso.apiPedido.model;



public class Produto {

	
	private Long id;
	
	private String nome;
	
	private Double preco;
	
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
	
	
	
}

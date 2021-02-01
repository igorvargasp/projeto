package br.com.ufsm.projeto.compasso.apiPedido.model;


public class Usuario {

	
	private long id;
	
	private String name;
	
	private String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}

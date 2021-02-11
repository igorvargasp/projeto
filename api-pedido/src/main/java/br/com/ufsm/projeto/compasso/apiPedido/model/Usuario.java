package br.com.ufsm.projeto.compasso.apiPedido.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Usuario {

	private long id;

	private String nome;

	private String senha;

	public Usuario(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Usuario() {

	}

}

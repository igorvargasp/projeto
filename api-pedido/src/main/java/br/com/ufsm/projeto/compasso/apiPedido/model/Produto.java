package br.com.ufsm.projeto.compasso.apiPedido.model;

import lombok.Data;
import lombok.Getter;

import lombok.Setter;

@Data
@Getter
@Setter
public class Produto {

	private Long id;

	private String nome;

	private Double preco;

	private Boolean disponivel;

	public Produto(String nome, Double preco, Boolean disponivel, Long id) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.disponivel = disponivel;
		this.id = id;
	}

	public Produto() {

	}

}

package br.com.ufsm.projeto.compasso.produto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
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
	
	public Produto(String nome, Double preco, Boolean disponivel, Long id) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.disponivel = disponivel;
		this.id = id;
	}
}

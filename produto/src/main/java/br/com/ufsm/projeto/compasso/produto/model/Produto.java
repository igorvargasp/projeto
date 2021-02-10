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
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "disponivel")
	private Boolean disponivel;
	
	public Produto(String nome, Double preco, Integer quantidade, Boolean disponivel) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.disponivel = disponivel;
	}
	
	public void removeQuantidade(int quantidade) throws Exception {
		if (quantidade <= this.quantidade)
			this.quantidade -= quantidade;
		else
			throw new Exception("Quantidade do produto é insuficiente");
	}
}

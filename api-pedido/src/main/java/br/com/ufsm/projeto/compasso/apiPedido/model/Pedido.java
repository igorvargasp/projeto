package br.com.ufsm.projeto.compasso.apiPedido.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Data
@Getter
@Setter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;

	@Column(name = "idUsuario")
	private Long idUsuario;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "idProduto")
	private Long idProduto;
	@Column(name = "produto")
	private String produto;
	@Column(name = "preco")
	private Double preco;
	@Column(name = "finalizado")
	private boolean finalizado;

	public Pedido(Long id, Integer quantidade) {
		super();
		this.id = id;
		this.quantidade = quantidade;
	}

	public Pedido() {

	}
}

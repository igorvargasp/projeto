package br.com.ufsm.projeto.compasso.produto.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	
	@NotNull
	private Double preco;
	
	@NotNull
	private Boolean disponivel;
}
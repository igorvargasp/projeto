package br.com.ufsm.projeto.compasso.produto.config.validation;

import lombok.Getter;

@Getter
public class ErrorForm {

	private String campo;
	private String erro;
	
	public ErrorForm(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}

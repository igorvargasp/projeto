package br.com.ufsm.projeto.compasso.usuario.config.Validation;

import lombok.Data;

@Data
public class ErrorForm {
	
	private String campo;
	private String erro;
	
	public ErrorForm(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}

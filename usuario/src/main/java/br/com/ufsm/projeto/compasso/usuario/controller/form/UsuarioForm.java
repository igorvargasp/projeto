package br.com.ufsm.projeto.compasso.usuario.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ufsm.projeto.compasso.usuario.model.Usuario;
import br.com.ufsm.projeto.compasso.usuario.service.UsuarioService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 8)
	private String senha;
}
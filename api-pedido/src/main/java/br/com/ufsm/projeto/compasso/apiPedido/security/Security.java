package br.com.ufsm.projeto.compasso.apiPedido.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import br.com.ufsm.projeto.compasso.apiPedido.client.UsuarioClient;
import br.com.ufsm.projeto.compasso.apiPedido.model.Usuario;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioClient client;
	



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .anyRequest().authenticated()
		 .and().httpBasic().and().csrf().disable();
		       	
		}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		List<Usuario> user = client.buscaUsuario();
		for (Usuario usuario : user) {
			auth.inMemoryAuthentication().withUser(usuario.getName()).password("{noop}"+usuario.getSenha()).roles("USER");	
		}
		
	}
	
	 
	    

}

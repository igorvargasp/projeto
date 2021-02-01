package br.com.ufsm.projeto.compasso.apiPedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.ufsm.projeto.compasso.apiPedido.model.Usuario;

@FeignClient(name = "usuario", url = "http://localhost:8080/usuario" )
public interface UsuarioClient {

	@RequestMapping(method = RequestMethod.GET, value ="", produces = "application/json;charset=UTF-8")
	public List<Usuario> buscaUsuario();

	
	
	
}

package br.com.ufsm.projeto.compasso.usuario.controller;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraintvalidation.SupportedValidationTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.projeto.compasso.usuario.model.Usuario;
import br.com.ufsm.projeto.compasso.usuario.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	
	@GetMapping(value= "/usuario")
	public List<Usuario> listar(){
		List<Usuario> usuario = usuarioRepository.findAll();
		return usuario;
	}
	
	@PostMapping(value = "/usuario")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		try {
			LOGGER.info("Usuario cadastro "+ usuario.getName());
			return ResponseEntity.ok(usuarioRepository.save(usuario));
						
		} catch (Exception e) {
			LOGGER.info("Erro no cadastro "+e);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/usuario/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
			
			Optional<Usuario> optional = usuarioRepository.findById(id);
			if(optional.isPresent()) {
				usuarioRepository.deleteById(id);
				LOGGER.info("Usuario Deletado "+ id);
				return ResponseEntity.ok().build();
			}
		} catch (Exception e) {
			LOGGER.info("Erro na exclusao de usuario "+e);
		}
		

		return ResponseEntity.notFound().build();
	}
	

	@Transactional
	@PutMapping( value = "/usuario/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id){
			
		try {
			Optional<Usuario> optional = usuarioRepository.findById(id);
			if(optional.isPresent()) {
				Usuario usuario = new Usuario();
				usuario.atualizar(id, usuarioRepository);
				LOGGER.info("Usuario atualizado "+ id);
				return ResponseEntity.ok(usuario);
			}
			
			
			} catch (Exception e) {
				LOGGER.info("Erro na atualizacao de usuario "+ e);
			}
		
		
		return ResponseEntity.notFound().build();
	}
}

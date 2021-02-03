package br.com.ufsm.projeto.compasso.usuario.controller;


import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.projeto.compasso.usuario.controller.form.UsuarioForm;
import br.com.ufsm.projeto.compasso.usuario.model.Usuario;
import br.com.ufsm.projeto.compasso.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioService service;
	
	
	@GetMapping
	public List<Usuario> listar (String nome) {
		List<Usuario> usuarios = (nome != null && !nome.isEmpty()) ? 
				service.findByName(nome) : service.findAll();
		return usuarios;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> detalhe (@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (Exception e) {
			LOGGER.info("Erro ao pesquisar usuário " + e);
		}
		LOGGER.info("Usuário não encontrado");
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioForm form){
		try {
			Usuario usuario = new Usuario((long)0, form.getNome(), form.getSenha());
			usuario = service.save(usuario);
			LOGGER.info("Usuario cadastro " + usuario.getId());
			return ResponseEntity.ok(usuario);			
		} catch (Exception e) {
			LOGGER.info("Erro no cadastro " + e);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {	
		try {
			Boolean deleted = service.deleteById(id);
			if (deleted) {
				LOGGER.info("Usuário Removido " + id);
				return ResponseEntity.ok().build();
			}
			LOGGER.info("Não foi possível remover o usuário");
		} catch (Exception e) {
			LOGGER.info("Erro na remoção de usuário " + e);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<Usuario> update (@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
		try {
			Usuario usuario = service.findById(id);
			if (usuario != null) {
				usuario = service.atualizar(id, form);
				if (usuario != null) {
					LOGGER.info("Usuario atualizado "+ id);
					return ResponseEntity.ok(usuario);
				}
				LOGGER.info("Usuário não encontrado");
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			LOGGER.info("Erro na atualizacao de usuario " + e);
		}
		return ResponseEntity.notFound().build();
	}
}

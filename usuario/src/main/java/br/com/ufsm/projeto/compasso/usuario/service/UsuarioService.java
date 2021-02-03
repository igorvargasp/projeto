package br.com.ufsm.projeto.compasso.usuario.service;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ufsm.projeto.compasso.usuario.controller.form.UsuarioForm;
import br.com.ufsm.projeto.compasso.usuario.model.Usuario;
import br.com.ufsm.projeto.compasso.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service 
public class UsuarioService {
	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) { 
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> findByName(String nome) {
		return usuarioRepository.findByNome(nome);
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			LOGGER.info("Produto encontrado " + usuario.get().getId());
			return usuario.get();
		}
		LOGGER.info("Produto não encontrado");
		return null;
	}

	public Boolean deleteById (Long id) {
		Usuario usuario = findById(id);
		if (usuario != null) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Usuario save(Usuario usuario) {
		if (usuario == null) { 
			LOGGER.error("Usuario é NULL!!!");
			return null;
		}
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizar (Long id, UsuarioForm form) {
		Usuario usuario = findById(id);
		usuario.setNome(form.getNome());
		usuario.setSenha(form.getSenha());
		return usuario;
	}
}
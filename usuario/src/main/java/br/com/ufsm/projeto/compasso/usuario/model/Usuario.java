package br.com.ufsm.projeto.compasso.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.ufsm.projeto.compasso.usuario.repository.UsuarioRepository;
import lombok.Builder;
import lombok.Data;





@Entity
@Table(name = "usuario", schema = "usuario")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "senha")
	private String senha;
	
	
	public Usuario() {
		
	}
	
	public Usuario(long id, String name, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.senha = senha;
	}
	
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setName(this.name);
		usuario.setSenha(this.senha);
		
		return usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

	
}

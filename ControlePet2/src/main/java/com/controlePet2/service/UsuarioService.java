package com.controlePet2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controlePet2.entities.Usuario;
import com.controlePet2.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario>getAllUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario alteraUsuario(Long id, Usuario usuario) {
		Optional<Usuario>existeUsuario = usuarioRepository.findById(id);
		if(existeUsuario.isPresent()) {
			usuario.setId(id);
			return usuarioRepository.save(usuario);
		}return null;
	}
	
	public boolean deleteUsuario(Long id) {
		Optional<Usuario>existeUsuario = usuarioRepository.findById(id);
		if(existeUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}return false;
	}
}

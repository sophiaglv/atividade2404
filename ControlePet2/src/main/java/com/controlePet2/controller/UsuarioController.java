package com.controlePet2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlePet2.entities.Usuario;
import com.controlePet2.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario>getUsuarioById(@PathVariable Long id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Usuario>>getAllUsuario(){
		List<Usuario>usuarios = usuarioService.getAllUsuario();
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping("/")
	public ResponseEntity<Usuario>saveUsuario(@RequestBody @Valid Usuario usuario){
		Usuario saveUsuario = usuarioService.saveUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario>alteraUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario alteraUsuario = usuarioService.alteraUsuario(id, usuario);
		if(alteraUsuario != null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteUsuario(@PathVariable Long id){
		boolean delete = usuarioService.deleteUsuario(id);
		if(delete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}

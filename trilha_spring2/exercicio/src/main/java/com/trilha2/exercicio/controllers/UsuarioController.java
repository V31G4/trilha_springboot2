package com.trilha2.exercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trilha2.exercicio.usuario.Usuario;
import com.trilha2.exercicio.usuario.UsuarioRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Recebido JSON: " + usuario);
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("/{id}")
	public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
			usuario.setNome(usuarioAtualizado.getNome());
			usuario.setEmail(usuarioAtualizado.getEmail());
			return usuarioRepository.save(usuario);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}






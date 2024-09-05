package com.example.finans.service;

import com.example.finans.domain.Usuario;
import com.example.finans.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario salvar(Usuario usuario) {
		return repository.salvar(usuario);
	}
	
}

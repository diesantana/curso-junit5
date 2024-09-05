package com.example.finans.service;

import com.example.finans.domain.Usuario;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario salvar(Usuario usuario) {
		// verifica já existe um user com o mesmo email
		repository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado!", user.getEmail()));
		});
		return repository.salvar(usuario);
	}
	
}

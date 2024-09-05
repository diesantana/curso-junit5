package com.example.finans.service.repositories;

import java.util.Optional;

import com.example.finans.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUserByEmail(String email);
}

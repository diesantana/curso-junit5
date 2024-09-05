package com.example.finans.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.finans.domain.Usuario;
import com.example.finans.service.repositories.UsuarioRepository;

public class UsuarioMemoryRepository implements UsuarioRepository {
	
	private List<Usuario> users = new ArrayList<Usuario>();
	private Long currentId;
	

	public UsuarioMemoryRepository() {
		this.currentId = 0L;
		salvar(new Usuario(null, "User #1", "user1@email.com", "123456"));
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		Usuario newUser = new Usuario(currentId, usuario.getNome(), usuario.getEmail(), usuario.getSenha());
		users.add(newUser);
		return newUser;
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	private Long nextId() {
		return ++currentId; 
	}

}

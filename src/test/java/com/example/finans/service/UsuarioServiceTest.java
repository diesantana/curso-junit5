package com.example.finans.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.finans.domain.Usuario;
import com.example.finans.domain.builders.UsuarioBuilderByMaster;
import com.example.finans.infra.UsuarioMemoryRepository;
import com.example.finans.service.repositories.UsuarioRepository;

public class UsuarioServiceTest {
	
	UsuarioRepository usuarioRepository;
	UsuarioService service;
	
	@BeforeEach
	void beforeEach() {
		usuarioRepository = mock(UsuarioMemoryRepository.class); // Mock do repository
		service = new UsuarioService(usuarioRepository); // Instancia o SUT com o Mock do repository
	}

	
	@Test
	void deveRetornarEmptyQuandoUsuarioNaoExiste() {
		when(usuarioRepository.getUserByEmail("user@email.com")).thenReturn(Optional.empty()); // configura o mock
		
		Optional<Usuario> usuario = service.getUserByEmail("user@email.com"); // faz a ação
		assertTrue(usuario.isEmpty()); // faz a assertiva
	}
	
	@Test
	void deveRetornarUmUsuarioPorEmail() {
		when(usuarioRepository.getUserByEmail("bob@email.com"))
			.thenReturn(Optional.of(UsuarioBuilderByMaster.umUsuario().comEmail("bob@email.com").agora())); // configura o Mock
		
		Optional<Usuario> user = service.getUserByEmail("bob@email.com"); 
		assertTrue(user.isPresent());
	}
}

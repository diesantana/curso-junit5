package com.example.finans.service;

import static com.example.finans.domain.builders.UsuarioBuilderByMaster.umUsuario;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.finans.domain.Usuario;
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
		// Given
		when(usuarioRepository.getUserByEmail("user@email.com"))
			.thenReturn(Optional.empty()); // configura o mock
		// When
		Optional<Usuario> usuario = service.getUserByEmail("user@email.com"); // faz a ação
		// Then
		assertTrue(usuario.isEmpty()); // faz a assertiva
	}

	@Test
	void deveRetornarUmUsuarioPorEmail() {
		// Given
		Optional<Usuario> userToReturn = Optional.of(umUsuario().comEmail("bob@email.com").agora());
		when(usuarioRepository.getUserByEmail("bob@email.com"))
			.thenReturn(userToReturn);
		// When
		Optional<Usuario> user = service.getUserByEmail("bob@email.com"); 
		// Then
		assertTrue(user.isPresent());
		verify(usuarioRepository, times(1)).getUserByEmail("bob@email.com");
		verify(usuarioRepository, never()).getUserByEmail("outro@email.com");
	}
	
	@Test
	void deveSalvarUsuarioComSucesso() {
		// Given - Arrange
		Usuario userToSave = umUsuario().comId(null).agora(); // User a ser salvo
		when(usuarioRepository.getUserByEmail(userToSave.getEmail()))
			.thenReturn(Optional.empty());  // Configura o comportamento do Mock
		when(usuarioRepository.salvar(userToSave)).thenReturn(umUsuario().agora()); 
		// When - Act
		Usuario savedUser = service.salvar(userToSave);
		// Then - Assert
		assertNotNull(savedUser.getId());
		verify(usuarioRepository).getUserByEmail(userToSave.getEmail());
	}
	
	
}

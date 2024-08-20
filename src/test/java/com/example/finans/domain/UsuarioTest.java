package com.example.finans.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.finans.domain.exception.ValidationException;

class UsuarioTest {

	@Test
	@DisplayName("Deve criar um usuário válido")
	void deveCriarUmUsuarioValido() {
		Usuario user = new Usuario(1L, "Bob", "bob@email.com", "1234");
		assertAll("Usuário valido", 
				() -> assertEquals(1L, user.getId()),
				() -> assertEquals("Bob", user.getNome()),
				() -> assertEquals("bob@email.com", user.getEmail()),
				() -> assertEquals("1234", user.getSenha())
		);
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário sem nome")
	void deveRejeitarUmUsuarioSemNome() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
			new Usuario(1L, null, "bob@email.com", "1234"));
		assertEquals("Nome é obrigatório", ex.getMessage());
	}
	


}

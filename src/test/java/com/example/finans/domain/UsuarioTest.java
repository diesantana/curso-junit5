package com.example.finans.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}

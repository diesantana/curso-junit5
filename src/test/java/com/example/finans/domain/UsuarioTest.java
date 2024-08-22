package com.example.finans.domain;

//import static com.example.finans.domain.builders.UsuarioBuilder.umUsuario;
import static com.example.finans.domain.builders.UsuarioBuilderByMaster.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.finans.domain.exception.ValidationException;

class UsuarioTest {

	@Test
	@DisplayName("Deve criar um usuário válido")
	void deveCriarUmUsuarioValido() {
		//Usuario user = new Usuario(1L, "Bob", "bob@email.com", "1234");
		//Usuario user = umUsuario().agora();
		Usuario user = umUsuario().agora();
		
		assertAll("Usuário valido", 
				() -> assertEquals(1L, user.getId()),
				() -> assertEquals("Bob", user.getNome()),
				() -> assertEquals("bob@email.com", user.getEmail()),
				() -> assertEquals("1234", user.getSenha())
		);
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com nome nulo")
	void deveRejeitarUmUsuarioComNomeNulo() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
			umUsuario().comNome(null).agora());
		assertEquals("Nome é obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com nome vazio")
	void deveRejeitarUmUsuarioComNomeVazio() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
			umUsuario().comNome("  ").agora());
		assertEquals("Nome é obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com email nulo")
	void deveRejeitarUmUsuarioComEmailNulo() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
			umUsuario().comEmail(null).agora());
		assertEquals("Email é obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com email vazio")
	void deveRejeitarUmUsuarioComEmailVazio() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
			umUsuario().comEmail("  ").agora());
		assertEquals("Email é obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com senha null")
	void deveRejeitarUmUsuarioComSenhaNull() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
		umUsuario().comSenha(null).agora());
		assertEquals("Senha é obrigatória", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve lançar uma exceção: usuário com senha vazia")
	void deveRejeitarUmUsuarioComSenhaVazia() {
		ValidationException ex = assertThrows(ValidationException.class, () -> 
		umUsuario().comSenha("  ").agora());
		assertEquals("Senha é obrigatória", ex.getMessage());
	}
	


}

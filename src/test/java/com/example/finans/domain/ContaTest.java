package com.example.finans.domain;

import static com.example.finans.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.finans.domain.builders.ContaBuilder;

public class ContaTest {

	@Test
	@DisplayName("Deve criar uma conta válida")
	void deveCriarUmaContaValida() {
		Conta conta = ContaBuilder.umConta().agora();
		
		assertAll("Conta Válida",
			() -> assertEquals(1L, conta.getId()),
			() -> assertEquals("Conta Válida", conta.getNome()),
			() -> assertEquals(umUsuario().agora(), conta.getUsuario())
		);
	}
}

package com.example.finans.domain;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static com.example.finans.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.finans.domain.exception.ValidationException;

@Tag("domain")
@Tag("account")
public class ContaTest {
	@Test
	@DisplayName("Deve criar uma conta válida")
	void deveCriarUmaContaValida() {
		Conta conta = umConta().agora();
		
		assertAll("Conta Válida",
			() -> assertEquals(1L, conta.getId()),
			() -> assertEquals("Conta Válida", conta.getNome()),
			() -> assertEquals(umUsuario().agora(), conta.getUsuario())
		);
	}
	
	@ParameterizedTest(name = "[{index}] - {3}")
	@MethodSource("dadosInvalidos")
	@DisplayName("Deve rejeitar uma conta inválida")
	void deveRejeitarUmaContaInvalida(Long id, String nome, Usuario usuario, String mensagem) {
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			Conta conta = umConta().comId(id).comNome(nome).comUsuario(usuario).agora();
		});
		assertEquals(mensagem, exception.getMessage());
	}
	
	private static Stream<Arguments> dadosInvalidos() {
		return Stream.of(
				Arguments.of(1L, null, umUsuario().agora(), "Nome é obrigatório"),
				Arguments.of(1L, "  ", umUsuario().agora(), "Nome é obrigatório"),
				Arguments.of(1L, "Conta Válida", null, "Usuário é obrigatório")
		);
	}
}

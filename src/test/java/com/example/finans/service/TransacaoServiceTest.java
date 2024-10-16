package com.example.finans.service;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static com.example.finans.domain.builders.TransacaoBuilder.umaTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finans.domain.Conta;
import com.example.finans.domain.Transacao;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.TransacaoDao;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

	@InjectMocks 
	@Spy
	private TransacaoService service;
	@Mock
	private TransacaoDao dao;
	//@Mock private ClockService clockService;
	
	@BeforeEach
	void beaforeEach() {
		//when(clockService.getCurrentTime()).thenReturn(LocalDateTime.of(2024, 10, 15, 7, 30, 30));
		//when(service.getTime()).thenReturn(LocalDateTime.of(2024, 10, 15, 7, 30, 30));
		doReturn(LocalDateTime.of(2024, 10, 15, 7, 30, 30)).when(service).getTime();
	}

	@Test
	void deveSalvarTransacaoValida() {
		
		// Given - Arrange
		Transacao transacaoToSave = umaTransacao().comId(null).agora();
		when(dao.salvar(transacaoToSave)).thenReturn(umaTransacao().agora());
		// When - Act
		Transacao transacaoPersistida = service.salvar(transacaoToSave);
		// Then - Assert
		assertNotNull(transacaoPersistida.getId());
		assertAll("Transacao", 
				() -> assertEquals("Transação válida", transacaoPersistida.getDescricao()),
				() -> assertAll("Conta", 
						() -> assertEquals("Conta Válida", transacaoPersistida.getConta().getNome()),
						() -> assertAll("Usuario", 
								() -> assertEquals("Bob", transacaoPersistida.getConta().getUsuario().getNome())
						)
				)
		);
	}
	
	@ParameterizedTest(name = "{5}")
	@MethodSource()
	@DisplayName("Método salvar dele lançar uma ValidationException")
	void salvarDeveLancarExcecaoQuandoDadosForemInvalidos(String descricao, 
			Double valor, Conta conta, LocalDate data, Boolean status, String expectedMsg) {
		// Arrange - Given
		Transacao transacao = umaTransacao().comDescricao(descricao)
				.comValor(valor).comConta(conta).comData(data).comStatus(status).agora();
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			service.salvar(transacao);
		});
		assertEquals(expectedMsg, exception.getMessage());
	}
	
	static Stream<Arguments> salvarDeveLancarExcecaoQuandoDadosForemInvalidos() {
		return Stream.of(
			Arguments.of(null, 100.0, umConta().agora(), LocalDate.now(), true, "Descrição inexistente"),
			Arguments.of("Uma Descrição", null, umConta().agora(), LocalDate.now(), true, "Valor inexistente"),
			Arguments.of("Uma Descrição", 100.0, null, LocalDate.now(), true, "Conta inexistente"),
			Arguments.of("Uma Descrição", 100.0, umConta().agora(), null, true, "Data inexistente")
		);
	}
	
}

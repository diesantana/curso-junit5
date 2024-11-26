package com.example.finans.service;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static com.example.finans.domain.builders.TransacaoBuilder.umaTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.finans.domain.Conta;
import com.example.finans.domain.Transacao;
import com.example.finans.domain.builders.TransacaoBuilder;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.TransacaoDao;

@Tag("service")
@Tag("transaction")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransacaoServiceTest {

	@InjectMocks 
	@Spy
	private TransacaoService service;
	@Mock
	private TransacaoDao dao;
	//@Mock private ClockService clockService;
	
	@Captor
	private ArgumentCaptor<Transacao> transacaoCaptor;
	
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
	
	@Test
	void salvarDeveLancarExcecaoQuandoHoraInvalida() {
		doReturn(LocalDateTime.of(2024, 10, 15, 17, 30, 30)).when(service).getTime();
		//Mockito.reset(service);
		Transacao transacao = umaTransacao().agora();
		
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			service.salvar(transacao);
		});
		
		assertEquals("Tente novamente amanhã", exception.getMessage());;
	}
	
	@Test
	void salvarDeveSalvarTrasacaoComStatusFalse() {
		Transacao transacao = umaTransacao().comStatus(null).agora();
		// capturando o arg passado para o dao.salvar()
		service.salvar(transacao);
		verify(dao).salvar(transacaoCaptor.capture());
		Transacao transacaoPersistida = transacaoCaptor.getValue();
		assertFalse(transacaoPersistida.getStatus());
	}
	
}

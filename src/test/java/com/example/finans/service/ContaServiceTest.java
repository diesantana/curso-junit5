package com.example.finans.service;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finans.domain.Conta;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.external.ContaEvent;
import com.example.finans.service.external.ContaEvent.EventType;
import com.example.finans.service.repositories.ContaRepository;

@Tag("service")
@Tag("account")
@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	@Mock
	private ContaRepository contaRepository; 
	@Mock
	private ContaEvent event; 
	@InjectMocks
	private ContaService contaService;
	@Captor
	private ArgumentCaptor<Conta> contaCaptor;
	
	@Test
	void deveSalvarContaComSucesso() throws Exception {
		// Given - Arrange
		Conta contaToSave = umConta().comId(null).agora();
		when(contaRepository.getContaByName(contaToSave.getNome()))
			.thenReturn(Optional.empty());
		when(contaRepository.salvar(contaToSave))
			.thenReturn(umConta().agora());
		doNothing().when(event).dispatch(umConta().agora(), EventType.CREATED);
		
		// When - Action
		Conta savedConta = contaService.salvar(contaToSave);
		
		// Then - Assertion 
		assertNotNull(savedConta.getId());
	}
	
	@Test
	void deveRejeitarContaExistente() {
		// Given - Arrange
		Conta contaToSave = umConta().comId(null).agora();
		when(contaRepository.getContaByName(contaToSave.getNome()))
			.thenReturn(Optional.of(umConta().agora()));
		
		// When (Act) && Then (Assert)
		assertThrows(ValidationException.class, () -> {
			contaService.salvar(contaToSave);
		});
		verify(contaRepository, never()).salvar(contaToSave);
	}
	
	@Test
	void deveLancarExcecaoQuandoOcorrerErroNoContaEvent() throws Exception {

		// Given - Arrange
		Conta contaToSave = umConta().comId(null).agora();
		Conta contaSalva = umConta().agora();
		
		when(contaRepository.getContaByName(contaToSave.getNome()))
			.thenReturn(Optional.empty());
		when(contaRepository.salvar(contaToSave)).thenReturn(contaSalva);
		
		Conta contaPersistida = contaRepository.salvar(contaToSave);
		Mockito.doThrow(new Exception("uma mensagem"))
			.when(event).dispatch(contaPersistida, EventType.CREATED);
		
		// When (Act) && Then (Assert)
		Exception exception = assertThrows(Exception.class, ()-> {
			contaService.salvar(contaToSave);
		});
		
		assertEquals("Falha na criação da conta, tente novamente", exception.getMessage());
		verify(contaRepository).deletar(contaPersistida);
	}

	@Test
	void deveRejeitarContaExistente2() {
		// Given - Arrange
		Conta contaToSave = umConta().comId(null).agora();
		when(contaRepository.getContaByName(Mockito.anyString()))
			.thenReturn(Optional.of(umConta().agora()));
		
		// When (Act) && Then (Assert)
		assertThrows(ValidationException.class, () -> {
			contaService.salvar(contaToSave);
		});
		verify(contaRepository, never()).salvar(contaToSave);
	}
	
	@Test
	void deveSalvarContaComCaptor() {
		// Given - Arrange
		Conta contaToSalve = umConta().comId(null).agora();
		when(contaRepository.getContaByName(contaToSalve.getNome()))
			.thenReturn(Optional.empty());
		when(contaRepository.salvar(contaToSalve)).thenReturn(umConta().agora());
		
		// When - Act 
		Conta contaPersistida = contaService.salvar(contaToSalve);
		verify(contaRepository).salvar(contaCaptor.capture()); // verifica se o salver do repository foi chamado e captura o seu argumento
		
		// Then - Assert
		Conta contaCapturada = contaCaptor.getValue();
		assertNotNull(contaPersistida.getId());
		assertEquals(contaToSalve.getId(), contaCapturada.getId());
		assertEquals(contaToSalve.getNome(), contaCapturada.getNome());
		
	}
}
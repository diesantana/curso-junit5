package com.example.finans.service;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finans.domain.Conta;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.events.ContaEvent;
import com.example.finans.service.events.ContaEvent.EventType;
import com.example.finans.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	@Mock
	private ContaRepository contaRepository; 
	@Mock
	private ContaEvent event; 
	@InjectMocks
	private ContaService contaService;
	
	@Test
	void deveSalvarContaComSucesso() {
		// Given - Arrange
		Conta contaToSalve = umConta().comId(null).agora();
		when(contaRepository.getContaByName(contaToSalve.getNome()))
			.thenReturn(Optional.empty());
		when(contaRepository.salvar(contaToSalve))
			.thenReturn(umConta().agora());
		doNothing().when(event).dispatch(umConta().agora(), EventType.CREATED);
		
		// When - Action
		Conta savedConta = contaService.salvar(contaToSalve);
		
		// Then - Assertion 
		assertNotNull(savedConta.getId());
	}
	
	@Test
	void deveRejeitarContaExistente() {
		// Given - Arrange
		Conta contaToSalve = umConta().comId(null).agora();
		when(contaRepository.getContaByName(contaToSalve.getNome()))
			.thenReturn(Optional.of(umConta().agora()));
		
		// When (Act) && Then (Assert)
		assertThrows(ValidationException.class, () -> {
			contaService.salvar(contaToSalve);
		});
		verify(contaRepository, never()).salvar(contaToSalve);
	}
}

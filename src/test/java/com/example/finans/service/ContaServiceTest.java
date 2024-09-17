package com.example.finans.service;

import static com.example.finans.domain.builders.ContaBuilder.umConta;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finans.domain.Conta;
import com.example.finans.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	@Mock
	private ContaRepository contaRepository; 
	@InjectMocks
	private ContaService contaService;
	
	@Test
	void deveSalvarContaComSucesso() {
		// Given - Arrange
		Conta contaToSalve = umConta().comId(null).agora();
		Mockito.when(contaRepository.salvar(contaToSalve))
			.thenReturn(umConta().agora());
		
		// When - Action
		Conta savedConta = contaService.salvar(contaToSalve);
		
		// Then - Assertion 
		assertNotNull(savedConta.getId());
	}
}

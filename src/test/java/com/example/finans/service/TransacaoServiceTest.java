package com.example.finans.service;

import static com.example.finans.domain.builders.TransacaoBuilder.umaTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.finans.domain.Transacao;
import com.example.finans.service.repositories.TransacaoDao;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService service;
	@Mock
	private TransacaoDao dao;
	
	@Test
	void deveSalvarTransacaoValida() {
		// Given - Arrange
		Transacao transacaoToSave = umaTransacao().comId(null).agora();
		Mockito.when(dao.salvar(transacaoToSave)).thenReturn(umaTransacao().agora());
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
}

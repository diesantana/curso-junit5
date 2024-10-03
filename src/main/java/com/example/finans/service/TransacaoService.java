package com.example.finans.service;

import java.time.LocalDateTime;

import com.example.finans.domain.Transacao;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.TransacaoDao;

public class TransacaoService {

	private TransacaoDao dao;

	public TransacaoService(TransacaoDao dao) {
		this.dao = dao;
	}
	
	public Transacao salvar(Transacao transacao) {
		if (LocalDateTime.now().getHour() > 16) 
				throw new RuntimeException("Tente novamente amanhã");
		
		if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
		if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
		if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
		if(transacao.getData() == null) throw new ValidationException("Data inexistente");
		if(transacao.getStatus() == null) transacao.setStatus(false);
		
		return dao.salvar(transacao);
	}
}

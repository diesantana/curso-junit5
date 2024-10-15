package com.example.finans.service;

import java.time.LocalDateTime;

import com.example.finans.domain.Transacao;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.external.ClockService;
import com.example.finans.service.repositories.TransacaoDao;

public class TransacaoService {

	private TransacaoDao dao;
	//private ClockService clockService;	
	
	public TransacaoService(TransacaoDao dao, ClockService clockService) {
		this.dao = dao;
		//this.clockService = clockService;
	}


	public Transacao salvar(Transacao transacao) {
		if (getTime().getHour() > 16) 
			throw new RuntimeException("Tente novamente amanhã");
		
		if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
		if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
		if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
		if(transacao.getData() == null) throw new ValidationException("Data inexistente");
		if(transacao.getStatus() == null) transacao.setStatus(false);
		
		return dao.salvar(transacao);
	}
	
	protected LocalDateTime getTime() {
		return LocalDateTime.now();
	}
	
}

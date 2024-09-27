package com.example.finans.service;

import com.example.finans.domain.Transacao;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.TransacaoDao;

public class TransacaoService {

	private TransacaoDao dao;

	public TransacaoService(TransacaoDao dao) {
		this.dao = dao;
	}
	
	public Transacao salvar(Transacao transacao) {
		if(transacao.getDescricao() == null) throw new ValidationException("Transação insexistente");
		if(transacao.getValor() == null) throw new ValidationException("Valor insexistente");
		if(transacao.getConta() == null) throw new ValidationException("Conta insexistente");
		if(transacao.getData() == null) throw new ValidationException("Data insexistente");
		if(transacao.getStatus() == null) transacao.setStatus(false);
		
		return dao.salvar(transacao);
	}
}

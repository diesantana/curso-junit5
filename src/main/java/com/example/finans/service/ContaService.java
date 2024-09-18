package com.example.finans.service;

import com.example.finans.domain.Conta;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.events.ContaEvent;
import com.example.finans.service.events.ContaEvent.EventType;
import com.example.finans.service.repositories.ContaRepository;

public class ContaService {
	
	private ContaRepository repository;
	private ContaEvent contaEvent;
	
	public ContaService(ContaRepository repository, ContaEvent contaEvent) {
		this.repository = repository;
		this.contaEvent = contaEvent;
	}

	public Conta salvar(Conta contaToSalve) {
		repository.getContaByName(contaToSalve.getNome()).ifPresent(conta -> {
			throw new ValidationException("A conta já existe!");
		});
		Conta contaPersistida = repository.salvar(contaToSalve);
		contaEvent.dispatch(contaPersistida, EventType.CREATED);
		return contaPersistida;
	}
}

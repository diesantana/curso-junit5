package com.example.finans.service;

import com.example.finans.domain.Conta;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.repositories.ContaRepository;

public class ContaService {
	
	private ContaRepository repository;
	
	public ContaService(ContaRepository repository) {
		this.repository = repository;
	}

	public Conta salvar(Conta contaToSalve) {
		repository.getContaByName(contaToSalve.getNome()).ifPresent(conta -> {
			throw new ValidationException("A conta já existe!");
		});
		
		return repository.salvar(contaToSalve);
	}
}

package com.example.finans.service;

import com.example.finans.domain.Conta;
import com.example.finans.service.repositories.ContaRepository;

public class ContaService {
	
	private ContaRepository repository;
	
	public ContaService(ContaRepository repository) {
		this.repository = repository;
	}

	public Conta salvar(Conta contaToSalve) {
		return repository.salvar(contaToSalve);
	}
}

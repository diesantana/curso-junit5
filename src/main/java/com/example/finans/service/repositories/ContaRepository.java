package com.example.finans.service.repositories;

import java.util.Optional;

import com.example.finans.domain.Conta;

public interface ContaRepository {
	Conta salvar(Conta conta);
	Optional<Conta> getContaByName(String nome);
	Void deletar(Conta conta);
}
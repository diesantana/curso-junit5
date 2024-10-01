package com.example.finans.domain.builders;

import static com.example.finans.domain.builders.ContaBuilder.umConta;

import java.time.LocalDate;

import com.example.finans.domain.Conta;
import com.example.finans.domain.Transacao;


public class TransacaoBuilder {
	private Transacao elemento;
	private TransacaoBuilder(){}

	public static TransacaoBuilder umaTransacao() {
		TransacaoBuilder builder = new TransacaoBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	public static void inicializarDadosPadroes(TransacaoBuilder builder) {
		builder.elemento = new Transacao();
		Transacao elemento = builder.elemento;

		
		elemento.setId(1L);
		elemento.setDescricao("Transação válida");
		elemento.setValor(50.0);
		elemento.setConta(umConta().agora());
		elemento.setData(LocalDate.now());
		elemento.setStatus(false);
	}

	public TransacaoBuilder comId(Long param) {
		elemento.setId(param);
		return this;
	}

	public TransacaoBuilder comDescricao(String param) {
		elemento.setDescricao(param);
		return this;
	}

	public TransacaoBuilder comValor(Double param) {
		elemento.setValor(param);
		return this;
	}

	public TransacaoBuilder comConta(Conta param) {
		elemento.setConta(param);
		return this;
	}

	public TransacaoBuilder comData(LocalDate param) {
		elemento.setData(param);
		return this;
	}

	public TransacaoBuilder comStatus(Boolean param) {
		elemento.setStatus(param);
		return this;
	}

	public Transacao agora() {
		return elemento;
	}
}

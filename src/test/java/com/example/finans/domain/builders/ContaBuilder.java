package com.example.finans.domain.builders;

import static com.example.finans.domain.builders.UsuarioBuilder.umUsuario;

import com.example.finans.domain.Conta;
import com.example.finans.domain.Usuario;

public class ContaBuilder {
	private Long id;
	private String nome;
	private Usuario usuario;

	private ContaBuilder(){}

	public static ContaBuilder umConta() {
		ContaBuilder builder = new ContaBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	private static void inicializarDadosPadroes(ContaBuilder builder) {
		builder.id = 1L;
		builder.nome = "Conta Válida";
		builder.usuario = umUsuario().agora();
	}

	public ContaBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public ContaBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ContaBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public Conta agora() {
		return new Conta(id, nome, usuario);
	}
}


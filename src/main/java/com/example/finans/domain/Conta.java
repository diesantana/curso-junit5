package com.example.finans.domain;

import java.util.Objects;

public class Conta {
	private Long id;
	private String nome;
	private Usuario usuario;
	
	public Conta(Long id, String nome, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(usuario, other.usuario);
	}
}

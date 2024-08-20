package com.example.finans.domain.builders;

import com.example.finans.domain.Usuario;

public class UsuarioBuilder {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		inicializarDadosPadrao(builder);
		return builder;
	}

	private static void inicializarDadosPadrao(UsuarioBuilder builder) {
		builder.id = 1L;
		builder.nome = "Bob";
		builder.email = "bob@email.com";
		builder.senha = "1234";
	}
	
	public UsuarioBuilder comId(Long newId) {
		id = newId;
		return this;
	}
	
	public UsuarioBuilder comNome(String newNome) {
		nome = newNome;
		return this;
	}
	
	public UsuarioBuilder comEmail(String newEmail) {
		email = newEmail;
		return this;
	}
	
	public UsuarioBuilder comSenha(String newSenha) {
		senha = newSenha;
		return this;
	}
	
	public Usuario agora() {
		return new Usuario(id, nome, email, senha);
	}
}

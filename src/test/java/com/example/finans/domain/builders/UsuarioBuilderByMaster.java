package com.example.finans.domain.builders;
/**
 * Classe builder gerada com o BuilderMaster
 */
import com.example.finans.domain.Usuario;

public class UsuarioBuilderByMaster {
	private Long id;
	private String nome;
	private String email;
	private String senha;

	private UsuarioBuilderByMaster(){}

	public static UsuarioBuilderByMaster umUsuario() {
		UsuarioBuilderByMaster builder = new UsuarioBuilderByMaster();
		inicializarDadosPadroes(builder);
		return builder;
	}

	private static void inicializarDadosPadroes(UsuarioBuilderByMaster builder) {
		builder.id = 1L;
		builder.nome = "Bob";
		builder.email = "bob@email.com";
		builder.senha = "1234";
	}

	public UsuarioBuilderByMaster comId(Long id) {
		this.id = id;
		return this;
	}

	public UsuarioBuilderByMaster comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public UsuarioBuilderByMaster comEmail(String email) {
		this.email = email;
		return this;
	}

	public UsuarioBuilderByMaster comSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public Usuario agora() {
		return new Usuario(id, nome, email, senha);
	}
}


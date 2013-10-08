package br.ufpb.aps.jogo.entidade;

import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Usuario {

	private String nome;
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null) {
			throw new ExcecaoJogoTabuleiro("Não é permitido jogador sem nome!");
		}
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha == null) {
			throw new ExcecaoJogoTabuleiro("Não é permitido jogador sem senha!");
		}
		this.senha = senha;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jogador))
			return false;
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}

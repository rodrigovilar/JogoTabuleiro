package br.ufpb.aps.jogo.entidade;

import java.io.Serializable;

import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Jogador implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				nome;
	private String				senha;
	private int					score;

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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		if (score < 0) {
			throw new ExcecaoJogoTabuleiro("Valor irregular no score!");
		}
		this.score = score;
	}
	// TODO excluir pois deveria estar no GerenteJogador
	public void aumentarScore() {
		score += 3;
	}
	// TODO excluir pois deveria estar no GerenteJogador
	public void diminuirScore() {
		score -= 1;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jogador))
			return false;
		Jogador other = (Jogador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}

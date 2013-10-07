package br.ufpb.aps.jogo.entidade;

import java.io.Serializable;

public class Personagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Posicao posicao;
	private String nome;

	public Personagem() {

		this.posicao = new Posicao();
		this.posicao.setPosicaoX(0);
		this.posicao.setPosicaoY(0);

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosicaoX() {

		return this.posicao.getPosicaoX();

	}

	public void setPosicaoX(int coordenada_X) {

		this.posicao.setPosicaoX(getPosicaoX() + coordenada_X);

	}

	public int getPosicaoY() {

		return this.posicao.getPosicaoY();

	}

	public void setPosicaoY(int coordenada_y) {

		this.posicao.setPosicaoY(getPosicaoY() + coordenada_y);

	}

}
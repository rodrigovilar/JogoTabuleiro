package br.ufpb.aps.jogo.entidade;

import java.io.Serializable;

public class Posicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int coordenada_x;
	private int coordenada_y;

	public Posicao() {

		this(0, 0);

	}

	public Posicao(int coordenada_x, int coordenada_y) {

		this.coordenada_x = coordenada_x;
		this.coordenada_y = coordenada_y;

	}

	public int getPosicaoX() {

		return this.coordenada_x;

	}

	public int getPosicaoY() {

		return this.coordenada_y;
	}

	public void setPosicaoX(int coordenada_x) {

		this.coordenada_x = coordenada_x;

	}

	public void setPosicaoY(int coordenada_y) {

		this.coordenada_y = coordenada_y;
	}

}

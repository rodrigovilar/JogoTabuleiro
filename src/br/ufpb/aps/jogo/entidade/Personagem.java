package br.ufpb.aps.jogo.entidade;

public class Personagem {

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

	public void setAvancarPosicaoX(int coordenada_X) {

		this.posicao.setPosicaoX(getPosicaoX() + coordenada_X);

	}

	public int getPosicaoY() {

		return this.posicao.getPosicaoY();

	}

	public void setAvancarPosicaoY(int coordenada_y) {

		this.posicao.setPosicaoY(getPosicaoY() + coordenada_y);

	}

}
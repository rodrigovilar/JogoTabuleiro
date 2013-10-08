package br.ufpb.aps.jogo.excecoes;

@SuppressWarnings("serial")
public class ExcecaoJogoTabuleiro extends RuntimeException {
	public ExcecaoJogoTabuleiro(String msg) {
		super(msg);
	}
}

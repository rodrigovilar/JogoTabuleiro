package ufpb.br.projeto;

import java.util.List;

/**
 * Essa classe agregarar inderetamente todas as outras entidades
 * 
 * @author jonathas Firmo
 * 
 */
public class Jogo {

	private String tabuleiro[] = new String[] { null, null, null, null};
	private int posicaoPersonagem;
	private int valorDado;
	private boolean resultado;
	private int score;
	private boolean iniciouJogo = false;
	private boolean definirPersonagemX;

	public boolean acabou() {
		if (getPosicaoPersonagem() == 3) {
			return true;
		}

		return false;
	}

	public boolean isEscolhaPersonagemX() {
		return definirPersonagemX;
	}

	public void setEscolhaPersonagemX(boolean b) {
		if (iniciouJogo) {
			throw new ExcecaoJogoTabuleiro("O jogo já foi iniciado!");
		}

		this.definirPersonagemX = b;
	}

	public int lancarDado() {

		if(acabou()){
			throw new ExcecaoJogoTabuleiro("O jogo já foi acabado!");
		}
		
		if (!definirPersonagemX) {
			throw new ExcecaoJogoTabuleiro("Personagem não foi definido!");
		}

		iniciouJogo = true;
		return valorDado = 1;

	}

	public boolean desafio(String questao, String alternativas[],
			String gabarito, String resposta) {

		if (resposta.equals(gabarito)) {
			resultado = true;
			posicaoPersonagem += valorDado;
			score += 1;

		} else {
			resultado = false;
		}
		return resultado;
	}

	public boolean isRespostaPersonagemX() {

		return resultado;
	}

	public int getScore() {
		return score;
	}

	public int getPosicaoPersonagem() {
		return posicaoPersonagem;
	}

	public void moverPersonagemX(int posicao) {
		String escolha = (definirPersonagemX) ? "X" : "Y";

		if (posicao < 0 || posicao >= tabuleiro.length) {
			throw new ExcecaoJogoTabuleiro("Posicao irregular!");
		}
		this.tabuleiro[posicao] = escolha;

	}

}
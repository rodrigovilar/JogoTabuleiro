package ufpb.br.aps.jogo;

import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

//import ufpb.br.aps.jogo.excecoes.ExcecaoNomeJogador;

/**
 * Essa classe sera a fachada do jogo.
 * 
 * @authors Jonathas Firmo, Adriano Patricio and Lucas Cruz
 */
public class Jogo {

	private String tabuleiro[] = new String[] { null, null, null, null };
	private int posicaoPersonagem; 
	private Personagem personagem = new Personagem();
	private Dado dado = new Dado();
	private Questao questao = new Questao();
	private boolean resultado;
	private int score;
	private boolean iniciouJogo = false;
	private boolean definirPersonagemX;
	private boolean contemSurpresa = false;
	private boolean responder = false;
	private String respostaPersonagem = "";
	private String gabarito = "";

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
			throw new ExcecaoJogoTabuleiro("O jogo ja foi iniciado!");
		}

		this.definirPersonagemX = b;
	}

	public int lancarDado() {

		if (acabou()) {
			throw new ExcecaoJogoTabuleiro("O jogo ja foi acabado!");
		}

		if (!definirPersonagemX) {
			throw new ExcecaoJogoTabuleiro(" O Personagem nao foi definido!");
		}

		iniciouJogo = true;
		return dado.lancarDado();

	}
	public void questao(String pergunta, String alternativas[],
			String respostaCorreta) {
		if (dado.getValorDoDado() == 0) {
			throw new ExcecaoJogoTabuleiro(
					"Questao nao pode ser exibida antes de lancar o dado!");
		}
		this.responder = true;
		questao.setGabarito(respostaCorreta);
	}

	public String getRespostaPersonagemX() {
		return respostaPersonagem;
	}

	public void setRespostaPersonagemX(String alternativa) {

		if (!respostaValida(alternativa)) {
			throw new ExcecaoJogoTabuleiro("Resposta invalida!");
		}
		if (!podeResponder()) {
			throw new ExcecaoJogoTabuleiro(
					"Nao pode responder antes da pergunta ser exibida!");
		}
		if (alternativa.equals(questao.getGabarito())) {
			resultado = true;
			posicaoPersonagem += dado.lancarDado();

		} else {
			resultado = false;
		}

		adicionarPontuacao(resultado);

		this.respostaPersonagem = alternativa;
	}

	private void adicionarPontuacao(boolean resultado) {
		if (resultado == true)
			score += 3;
		if (score != 0 && resultado == false) {
			score -= 1;
		}
		if (score == 0 && resultado == false)
			return;
	}

	public boolean isResultadoQuestao() {
		return resultado;
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

	public boolean surpresa(int valorSurpresa) {
		boolean saida = false;

		if (valorSurpresa > 0) {
			saida = true;
		}
		contemSurpresa = true;
		return saida;
	}

	public boolean isSurpresa() {
		return contemSurpresa;
	}

	public void setSurpresa(boolean contem) {
		this.contemSurpresa = contem;
		// Exemplo que adiciona mais 1 na posicao
		if (this.contemSurpresa) {
			this.posicaoPersonagem++;
		}
	}

	public boolean respostaValida(String alternativa) {
		boolean result = false;
		if ((alternativa.equals("a") || alternativa.equals("b"))
				|| alternativa.equals("c")) {
			result = true;
		}
		return result;
	}

	public boolean podeResponder() {
		return responder;
	}

	public void setNomeJogador(String s) {
		if (s.length() > 2) {
			getPersonagem().setNome(s);
		}
	}

	public String getNomeJogador() {
		return getPersonagem().getNome();
	}

	public boolean encerrarAntesDoTempo() {
		return true;
	}

	public int getValorDoDado() {
		return dado.getValorDoDado();
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public int getTamanhoNome() {
		return getNomeJogador().length();

	}
}

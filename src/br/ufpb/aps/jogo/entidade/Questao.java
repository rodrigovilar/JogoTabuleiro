package br.ufpb.aps.jogo.entidade;

import java.io.Serializable;

import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Questao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pergunta;
	private String[] alternativas;
	private String gabarito;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		if (pergunta == null) {
			throw new ExcecaoJogoTabuleiro("Não é permitido pergunta nula!");
		}
		this.pergunta = pergunta;
	}

	public String[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String[] alternativas) {

		for (String aux : alternativas) {

			if (aux == null) {
				throw new ExcecaoJogoTabuleiro(
						"Não é permitido inserir alternativa nula!");
			}
		}
		this.alternativas = alternativas;
	}

	public String getGabarito() {

		return this.gabarito;
	}

	public void setGabarito(String resposta) {
		if (resposta == null) {
			throw new ExcecaoJogoTabuleiro(
					"Não é permitido gabarito nulo!");
		}
		this.gabarito = resposta;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Questao))
			return false;
		Questao other = (Questao) obj;
		if (pergunta == null) {
			if (other.pergunta != null)
				return false;
		} else if (!alternativas.equals(other.alternativas)) {
			return false;
		}

		else if (!gabarito.equals(other.gabarito))
			return false;
		return true;
	}

}

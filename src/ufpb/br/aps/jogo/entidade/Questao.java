package ufpb.br.aps.jogo.entidade;

import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Questao {

	private String pergunta;
	private String[] alternativas;
	private String gabarito;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		if(pergunta == null){
			throw new ExcecaoJogoTabuleiro("Não é permitido pergunta nula!");
		}
		this.pergunta = pergunta;
	}

	public String[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String[] alternativas) {
		this.alternativas = alternativas;
	}

	public String getGabarito() {

		return this.gabarito;
	}

	public void setGabarito(String resposta) {
		if(resposta == null){
			throw new ExcecaoJogoTabuleiro("Não é permitido gabarito sem resposta!");
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

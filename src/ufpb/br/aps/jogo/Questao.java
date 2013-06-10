package ufpb.br.aps.jogo;

import java.util.LinkedList;
import java.util.List;

public class Questao {
	private String questao;
	private String[] alternativa;
	private String gabarito;
	private List<Questao> listaDeQuestoes = new LinkedList<Questao>();


	public String getGabarito() {

		return this.gabarito;
	}

	public void setGabarito(String resposta) {
		this.gabarito = resposta;
	}

}

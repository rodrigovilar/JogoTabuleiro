package ufpb.br.aps.jogo.entidade;

import java.util.LinkedList;
import java.util.List;

import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Questionario {
	private List<Questao> listaDeQuestoes = new LinkedList<Questao>();

	public void criarQuestao(Questao questao) {
		this.listaDeQuestoes.add(questao);

	}

	public List<Questao> listarQuestoes() {
		return listaDeQuestoes;
	}

	public int sizeQuestionario() {
		return listaDeQuestoes.size();
	}

	public boolean equalsQuestao(String pergunta) {
		boolean saida = false;
		for (Questao q : listaDeQuestoes) {
			if (q.getPergunta().equals(pergunta)) {
				saida = true;
			} else {
				saida = false;
				throw new ExcecaoJogoTabuleiro("Já existe essa questao!");
			}

		}
		return saida;

	}
}

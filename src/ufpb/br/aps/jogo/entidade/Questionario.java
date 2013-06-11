package ufpb.br.aps.jogo.entidade;

import java.util.LinkedList;
import java.util.List;

import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Questionario {
	private List<Questao> listaDeQuestoes = new LinkedList<Questao>();

	public void cadastrarQuestao(Questao questao) {
		equalsQuestao(questao.getPergunta());
		this.listaDeQuestoes.add(questao);

	}

	public List<Questao> listarQuestoes() {
		return listaDeQuestoes;
	}

	public int sizeQuestionario() {
		return listaDeQuestoes.size();
	}

	public void equalsQuestao(String pergunta) {

		for (Questao q : listaDeQuestoes) {
			if (q.getPergunta().equals(pergunta)) {

				throw new ExcecaoJogoTabuleiro("Já existe essa questao!");
			}
		}

	}
}

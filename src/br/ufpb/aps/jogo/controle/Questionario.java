package br.ufpb.aps.jogo.controle;

import java.util.LinkedList;
import java.util.List;

import ufpb.br.aps.jogo.entidade.Questao;
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

	public void removerQuestao(Questao questao) {
		obterQuestao(questao.getPergunta());
		this.listarQuestoes().remove(questao);

	}

	public Questao obterQuestao(String pergunta) {

		for (Questao questao : listaDeQuestoes) {
			if (questao.getPergunta().equals(pergunta)) {
				return questao;

			}
		}
		throw new ExcecaoJogoTabuleiro("Questao inexistente!");

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
	
	public Questao criarQuestao() {
		return new Questao();
	}
}

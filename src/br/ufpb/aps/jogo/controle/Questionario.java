package br.ufpb.aps.jogo.controle;

import java.util.LinkedList;
import java.util.List;

import br.ufpb.aps.jogo.entidade.Questao;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Questionario {
	private Questao			questao			= new Questao();
	private List<Questao>	listaDeQuestoes	= new LinkedList<Questao>();

	public void cadastrarQuestao(Questao questao) {
		equalsQuestao(questao.getPergunta());
		this.listaDeQuestoes.add(questao);
	}
	public List<Questao> mostrarQuestoes() {
		return listaDeQuestoes;
	}
	public void removerQuestao(Questao questao) {
		obterQuestao(questao.getPergunta());
		this.mostrarQuestoes().remove(questao);
	}
	public Questao obterQuestao(String pergunta) {
		for (Questao questao : listaDeQuestoes) {
			if (questao.getPergunta().equals(pergunta)) {
				return questao;
			}
		}
		throw new ExcecaoJogoTabuleiro("Questao inexistente!");
	}
	// falta corrigir esse metodo
	public Questao alterarQuestao(Questao questaoNova) {
		for (Questao questaoAntiga : listaDeQuestoes) {
			if (questaoAntiga.getPergunta().equals(questaoNova.getPergunta())) {
				removerQuestao(questaoAntiga);
				cadastrarQuestao(questaoNova);
				return questaoNova;
			}
		}
		throw new ExcecaoJogoTabuleiro("Questao inexistente!");
	}
	public void criarQuestao(Questao questao) {
		this.questao = questao;
	}
	public Questao getQuestao() {
		return questao;
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
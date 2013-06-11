package ufpb.br.aps.jogo.entidade;

import java.util.LinkedList;
import java.util.List;



public class Questionario {
	private List<Questao> listaDeQuestoes = new LinkedList<Questao>();

	public void criarQuestao(Questao questao) {
		this.listaDeQuestoes.add(questao);

	}
	
	public List<Questao> listarQuestoes(){
		return listaDeQuestoes;
	}
	
	public int sizeQuestionario(){
		return listaDeQuestoes.size();
	}
	
	
	
	
}
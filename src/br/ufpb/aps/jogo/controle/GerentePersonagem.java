package br.ufpb.aps.jogo.controle;

import ufpb.br.aps.jogo.entidade.Personagem;

public class GerentePersonagem { 
	
	private Personagem personagem;
	
	public void criarPersonagem(Personagem personagem){
		this.personagem = personagem;
	}

	public Personagem getPersonagens() {
		return personagem;
	}
	
	public String obterNome(){
		return getPersonagens().getNome();
	}
	
	public void alterarNome(String nome){
		getPersonagens().setNome(nome);
	}
}

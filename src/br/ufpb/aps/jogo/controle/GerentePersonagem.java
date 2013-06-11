package br.ufpb.aps.jogo.controle;

import java.util.ArrayList;
import java.util.List;

import ufpb.br.aps.jogo.entidade.Personagem;

public class GerentePersonagem {

	private List<Personagem> personagens = new ArrayList<Personagem>();
	
	public void addPersonagem(Personagem personagem){
		personagens.add(personagem);
	}
}

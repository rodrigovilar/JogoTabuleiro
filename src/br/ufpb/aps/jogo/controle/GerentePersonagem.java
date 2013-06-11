package br.ufpb.aps.jogo.controle;

/*import java.util.ArrayList;
import java.util.List;*/

import ufpb.br.aps.jogo.entidade.Personagem;

public class GerentePersonagem {

	//private List<Personagem> personagens = new ArrayList<Personagem>();
	
	//Acho que não talvez precise dessa lista basta a 
	//cada vez que for iniciar um novo jogo com um Personagem diferente dar um new() 
	
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

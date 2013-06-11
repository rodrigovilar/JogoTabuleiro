package ufpb.br.aps.jogo.entidade;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
	
	private List<Personagem> listaPersonagens = new ArrayList<Personagem>();
	// Esta lista não devia pertencer a classe personagem e sim ao gerente de personagem

	private String nome;

	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public void criarPersonagem(Personagem p) {
		this.listaPersonagens.add(p);

	}
	
	public List<Personagem> listarPersonagens(){
		return listaPersonagens;
	}
	
	public int sizeListaPersonagem(){
		return this.listaPersonagens.size();
	}


	public void setResultado(String n){
		this.nome=n;
	}

/*	public int getResultado() {
		int i,res = -1;
		for (i = 0;i<listaPersonagens.size();i++)
		    if (listaPersonagens.get(i).getNome()==this.nome)  
		         res =  listaPersonagens.get(i).getResultado();
		return res;
	}*/
	
}
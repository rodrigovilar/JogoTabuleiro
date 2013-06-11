package ufpb.br.aps.jogo.entidade;

import java.util.LinkedList;
import java.util.List;

public class Personagem {
	
	private List<Personagem> listaPersonagens = new LinkedList<Personagem>();

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
	
//	public void setResultado(String n){
//		this.nome=n;
//	}
//
//	public int getResultado() {
//		int i;
//		for (i =0;i<listaPersonagens.size();i++)
//		    if (listaPersonagens.get(i).getNome()==this.nome)  
//		         return listaPersonagens.get(i).getResultado();
//		return listaPersonagens.get(i).getResultado();
//	}
	
}
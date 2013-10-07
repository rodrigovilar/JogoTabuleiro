package br.ufpb.aps.jogo.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.aps.jogo.entidade.Personagem;

public class GerentePersonagem {

	private List<Personagem> listaPersonagens = new ArrayList<Personagem>();

	private Personagem personagem = new Personagem();

	public void adicionarPersonagem(Personagem personagem) {
		listaPersonagens.add(personagem);
	}

	public List<Personagem> listarPersonagens() {
		return listaPersonagens;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public String obterNome() {
		return getPersonagem().getNome();
	}

	public void alterarNome(String nome) {
		if (nome.length() > 2) {
			getPersonagem().setNome(nome);
		}

	}
	public int getTamanhoNome() {
		return obterNome().length();

	}
}

package br.ufpb.aps.jogo.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.aps.jogo.entidade.Jogador;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;
import br.ufpb.aps.jogo.persistencia.Serializador;

public class GerenteJogador {
	private List<Jogador>	jogadores	= new ArrayList<Jogador>();
	private Jogador			jogador		= new Jogador();

	public void cadastrarJogador(Jogador jogador) {
		equalsJogador(jogador.getSenha());
		jogadores.add(jogador);
	}
	public boolean efetuarLogin(Jogador jogado){
		return obterJogador(jogador.getSenha());
	}
	public List<Jogador> listarJogadores() {
		return jogadores;
	}
	public void removerJogador(Jogador jogador) {
		obterJogador(jogador.getSenha());
		jogadores.remove(jogador);
	}
	public boolean obterJogador(String senha) {
		for (Jogador j : jogadores) {
			if (j.getSenha().equals(senha)) {
				return true;
			}
		}
		throw new ExcecaoJogoTabuleiro("Jogador inexistente!");
	}
	public void criarJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void equalsJogador(String senha) {
		for (Jogador j : jogadores) {
			if (j.getSenha().equals(senha)) {
				throw new ExcecaoJogoTabuleiro("Já existe esse jogador!");
			}
		}
	}
}

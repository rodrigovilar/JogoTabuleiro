package br.ufpb.aps.jogo.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.aps.jogo.entidade.Administrador;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class GerenteAdministrador {

	private List<Administrador> administradores = new ArrayList<Administrador>();
	private Administrador administrador = new Administrador();

	public void cadastrarJogador(Administrador administrador) {
		equalsAdministrador(administrador.getSenha());
		administradores.add(administrador);
	}

	public boolean efetuarLogin(Administrador administrador) {
		return obterAdministrador(administrador.getSenha());
	}

	public void removerJogador(Administrador administrador) {
		obterAdministrador(administrador.getSenha());
		administradores.remove(administrador);
	}

	public boolean obterAdministrador(String senha) {
		for (Administrador admin : administradores) {
			if (admin.getSenha().equals(senha)) {
				return true;
			}
		}
		throw new ExcecaoJogoTabuleiro("Administrador inexistente!");
	}

	public void criarAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void equalsAdministrador(String senha) {
		for (Administrador admin : administradores) {
			if (admin.getSenha().equals(senha)) {
				throw new ExcecaoJogoTabuleiro("Já existe esse administrador!");
			}
		}
	}
}

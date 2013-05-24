package ufpb.br.projeto;

/**
 * Essa classe agregarar inderetamente todas as outras entidades
 * 
 * @author jonathas Firmo
 * 
 */
public class Jogo {

	private boolean definirPersonagemX;

	public boolean acabou() {
		return false;
	}

	public boolean isPersonagemX() {
		return definirPersonagemX;
	}

	public void setPersonagemX(boolean b) {
		this.definirPersonagemX = b;
	}

	public void lancarDado() {

	}

	public int getDado() {
		return 1;
	}

}
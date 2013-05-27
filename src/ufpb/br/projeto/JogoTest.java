package ufpb.br.projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoTest {

	private Jogo jogo;
	
	//Teste

	@Before
	public void criarJogo() {
		jogo = new Jogo();
	}

	@Test
	public void iniciarJogo() {
		assertFalse("O jogo iniciou acabado", jogo.acabou());
	}

	@Test
	public void definirPersonagemX() {
		jogo.setPersonagemX(true);
		assertTrue("Esperava que o primeiro personagem fosse X",
				jogo.isPersonagemX());

	}

	@Test
	public void definirPersonagemXDeNovo() {
		jogo.setPersonagemX(true);
		jogo.setPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				jogo.isPersonagemX());
	}

	@Test
	public void definirPersonagemXAposInicio() {
		// TODO
		// Nao da para fazer esse teste, pois nao sabemos como ele inicializa.
	}

	@Test
	public void jogarDado() {
		jogo.setPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7",
				jogo.lancarDado() > 0 && jogo.lancarDado() < 7);

	}
	

}

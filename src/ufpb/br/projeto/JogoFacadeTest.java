package ufpb.br.projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoFacadeTest {

	private Jogo jogo;

	@Before
	public void criarJogo() {
		jogo = new Jogo();
	}

	// teste 1
	@Test
	public void iniciarJogo() {
		assertFalse("O jogo iniciou acabado", jogo.acabou());
	}

	// teste 2
	@Test
	public void definirPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue("Esperava que o primeiro personagem fosse X",
				jogo.isEscolhaPersonagemX());

	}

	// teste 3
	@Test
	public void definirPersonagemXDeNovo() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				jogo.isEscolhaPersonagemX());
	}

	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.setEscolhaPersonagemX(false);

	}

	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		jogo.lancarDado();
	}

	// teste 6
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void moverPersonagemPosicaoIrregular() {
		jogo.setEscolhaPersonagemX(true);
		jogo.moverPersonagemX(-1);
	}

}

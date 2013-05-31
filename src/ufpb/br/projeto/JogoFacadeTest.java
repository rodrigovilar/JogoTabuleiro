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

	// teste 7
	@Test
	public void scoreZero() {
		// Testa se o score inicializou zerado
		jogo.setEscolhaPersonagemX(true);
		assertEquals(jogo.getScore(), 0);
	}

	// teste 8
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue(jogo.getPosicaoPersonagem() == 0);
	}

	// teste 9
	@Test
	public void jogarDado() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", jogo.lancarDado() > 0
				&& jogo.lancarDado() < 7);

	}

	// teste 10
	@Test
	public void verificarRespostaPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.desafio("questao", new String[] { "a", "b", "c" }, "a", "a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				jogo.isRespostaPersonagemX());

	}
	

	// teste 11
	@Test
	public void verificarPosicaoAposAcerto() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.desafio("questao", new String[] { "a", "b", "c" }, "a", "a");
		jogo.isRespostaPersonagemX();
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				jogo.getPosicaoPersonagem() == 1);

	}
	

}

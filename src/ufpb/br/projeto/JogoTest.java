package ufpb.br.projeto;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JogoTest {

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
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				jogo.isResultadoQuestao());

	}

	// teste 11
	@Test
	public void verificarPosicaoAposAcerto() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		assertTrue("Espera-se que o personagem esteja na posicao 1",
				jogo.getPosicaoPersonagem() == 1);
	}

	// teste 12
	@Test
	public void verificarPosicaoAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("b");

		assertTrue("Espera-se que o personagem esteja na posicao 0",
				jogo.getPosicaoPersonagem() == 0);
	}

	// teste 13
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void verificarValorIrregularScore() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setScore(-1);
	}

	// teste 14
	@Test
	public void verificarScoreAposAcerto() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		assertEquals(3, jogo.getScore());
	}

	// teste 15
	@Test
	public void verificarScoreAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("b");

		assertEquals(0, jogo.getScore());
	}

	// teste 16
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("b");
		jogo.isResultadoQuestao();
		assertEquals(2, jogo.getScore());
	}

	// teste 17
	@Test
	public void jogoGanho() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				jogo.acabou());
	}

	// teste 18
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");

		jogo.lancarDado();
	}

	// teste 19
	@Test
	public void verificarCasaSurpresa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa",
				jogo.isSurpresa());
	}

	// teste 20
	@Test
	public void surpresaBoa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa boa",
				(jogo.surpresa(2)));
	}

	// teste 21
	@Test
	public void surpresaRuim() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertFalse("Espera-se que casa contenha uma surpresa ruim",
				(jogo.surpresa(0)));
	}

	// teste 22
	@Test
	public void verificaPosicaoAposSupresa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que a posicao seja 2",
				jogo.getPosicaoPersonagem() == 2);
	}

	//teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado(){
		jogo.setEscolhaPersonagemX(true);
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
	}
	
	// teste 24
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest(){
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.setRespostaPersonagemX("a");
	}

	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherQuestaoInexistente() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("d");
		assertFalse("Espera-se que a resposta não seja valida",
				jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	//teste 26
	@Test
	public void respostaValidaTest(){
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		assertEquals(true,jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	//teste 27
	public void respostaInvalidaTest(){
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("d");
		assertEquals(false,jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	//teste 28
	@Test
	public void verificaRespostaDoPersonagemX(){
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		assertEquals("a",jogo.getRespostaPersonagemX());
	}

	//teste 29
	@Test
	public void testValorDadoAntesDeComeçarJogo(){
		jogo.setEscolhaPersonagemX(true);
		assertTrue(jogo.getValorDado()==0);
	}

	// teste 30
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"a");
		jogo.setRespostaPersonagemX("a");
		
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" },"b");
		jogo.setRespostaPersonagemX("a");

		assertTrue("Espera-se que o personagem esteja na posicao 1",
				jogo.getPosicaoPersonagem() == 1);
	}
}
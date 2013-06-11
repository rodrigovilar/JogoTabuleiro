package ufpb.br.aps.jogo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ufpb.br.aps.jogo.entidade.Questao;
import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;
import ufpb.br.aps.jogo.fachada.Jogo;

//import ufpb.br.aps.jogo.excecoes.ExcecaoNomeJogador;

public class JogoTest {

	private Jogo fachada;

	@Before
	public void criarJogo() {
		fachada = new Jogo();
	}

	// teste 1
	@Test
	public void iniciarJogo() {
		assertFalse("O jogo iniciou acabado", fachada.acabou());
	}

	// teste 2
	@Test
	public void definirPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue("Esperava que o primeiro personagem fosse X",
				fachada.isEscolhaPersonagemX());
	}

	// teste 3
	@Test
	public void definirPersonagemXDeNovo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				fachada.isEscolhaPersonagemX());
	}

	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.setEscolhaPersonagemX(false);
	}

	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		fachada.lancarDado();
	}

	// teste 6
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void moverPersonagemPosicaoIrregular() {
		fachada.setEscolhaPersonagemX(true);
		fachada.moverPersonagemX(-1);
	}

	// teste 7
	@Test
	public void scoreZero() {
		fachada.setEscolhaPersonagemX(true);
		assertEquals(fachada.getScore(), 0);
	}

	// teste 8
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue(fachada.getPosicaoPersonagem() == 0);
	}

	// teste 9
	@Test
	public void jogarDado() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", fachada.lancarDado() > 0
				&& fachada.lancarDado() < 7);
	}

	// teste 10
	@Test
	public void verificarRespostaPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				fachada.isResultadoQuestao());
	}

	// teste 11
	@Test
	public void verificarPosicaoAposAcerto() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 12
	@Test
	public void verificarPosicaoAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagem() == 0);
	}

	// teste 13
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void verificarValorIrregularScore() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setScore(-1);
	}

	// teste 14
	@Test
	public void verificarScoreAposAcerto() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertEquals(3, fachada.getScore());
	}

	// teste 15
	@Test
	public void verificarScoreAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("b");
		assertEquals(0, fachada.getScore());
	}

	// teste 16
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("b");
		fachada.isResultadoQuestao();
		assertEquals(2, fachada.getScore());
	}

	// teste 17
	@Test
	public void jogoGanho() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}

	// teste 18
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
	}

	// teste 19
	@Test
	public void verificarCasaSurpresa() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa",
				fachada.isSurpresa());
	}

	// teste 20
	@Test
	public void surpresaBoa() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa boa",
				(fachada.getPosicaoPersonagem() == 2));
	}

	// teste 21
	@Test
	public void surpresaRuim() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.setSurpresa(true);
		assertFalse("Espera-se que casa contenha uma surpresa ruim",
				(fachada.surpresa(0)));
	}

	// teste 22
	@Test
	public void verificaPosicaoAposSupresa() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.setSurpresa(true);
		assertTrue("Espera-se que a posicao seja 2",
				fachada.getPosicaoPersonagem() == 2);
	}

	// teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		fachada.setEscolhaPersonagemX(true);
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
	}

	// teste 24
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.setRespostaPersonagemX("a");
	}

	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherQuestaoInexistente() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("d");
		assertFalse("Espera-se que a resposta não seja valida",
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 26
	@Test
	public void respostaValidaTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertEquals(true,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 27
	public void respostaInvalidaTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("d");
		assertEquals(false,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 28
	@Test
	public void verificaRespostaDoPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		assertEquals("a", fachada.getRespostaPersonagemX());
	}

	// teste 29
	@Test
	public void testValorDadoAntesDeComeçarJogo() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue(fachada.getValorDoDado() == 0);
	}

	// teste 30
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.lancarDado();
		fachada.questao("questao", new String[] { "a", "b", "c" }, "b");
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 31
	@Test
	public void verificarScoreSalvo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "b");
		fachada.setRespostaPersonagemX("b");
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "c");
		fachada.setRespostaPersonagemX("c");
		assertTrue("Espera-se que o score seja 9", fachada.getScore() == 9);
	}

	// teste 32
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Lucas");
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		// assertEquals("Espera o valor 3", 3, jogo.verificaPontuacao("Lucas"));
	}

	// teste 33
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				fachada.encerrarAntesDoTempo() == true);

	}

	// teste 34
	@Test
	public void verificarScoreDePartidaCancelada() {
		fachada.setEscolhaPersonagemX(true);
		fachada.lancarDado();
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		fachada.setRespostaPersonagemX("a");
		fachada.questao("pergunta", new String[] { "a", "b", "c" }, "b");
		fachada.setRespostaPersonagemX("b");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 6", fachada.getScore() == 6);
	}

	// teste 35
	@Test
	public void testNomeJogador() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				fachada.getNomeJogador() == "Joao");
	}

	// teste 36
	@Test
	public void testTamanhoNomeAceitavel() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Luc");
		fachada.getTamanhoNome();
		assertTrue("Espera-se que o personagem seja Luc",
				fachada.getNomeJogador() == "Luc");
		assertEquals(3, fachada.getTamanhoNome());
	}
	
	// teste 37
	@Test
	public void cadastrarQuestao() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		assertEquals(1, questoesSalvas.size());

		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
	}
	
	// teste 38
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarQuestaoNovamente() {
		Questao questao = criarQuestao();
		fachada.cadastrarQuestao(questao);
		fachada.cadastrarQuestao(questao);
	}

	// teste 39
	@Test
	public void removerQuestao() {
		Questao questao = criarQuestao();

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		fachada.removerQuestao(questao);
		assertTrue(questoesSalvas.size() == 0);
	}

	// teste 40
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoDeNovo() {
		Questao questao = criarQuestao();

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		fachada.removerQuestao(questao);
		fachada.removerQuestao(questao);
	}

	// teste 41
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoInexistente() {
		Questao questao = criarQuestao();
		fachada.removerQuestao(questao);
	}
	
	// teste 42
	@Test
	public void alterarQuestao() {
		Questao questao = criarQuestao();

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		fachada.alterarQuestao(questao);
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
	}

	// teste 43
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirPerguntaNula() {
		Questao questao = new Questao();
		questao.setPergunta(null);

	}
	
	//teste 44
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirGabaritoSemResposta(){
		Questao questao = new Questao();
		questao.setGabarito(null);
	}
	
	//test 45
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirAlternativaNula(){
		String alternativas[] = new String[]{null,"",""};
		Questao questao = new Questao();
		questao.setAlternativas(alternativas);
	}
	
	// metodo auxiliar de teste
	public Questao criarQuestao(){
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");
		return questao;
	}

}
package br.ufpb.aps.jogo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.jogo.entidade.Questao;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;
import br.ufpb.aps.jogo.fachada.Jogo;

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
		escolhaDoPersonagemX();
		fachada.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				fachada.isEscolhaPersonagemX());
	}

	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		escolhaDoPersonagemX();
		fachada.jogarDado();
		fachada.setEscolhaPersonagemX(false);
	}

	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		lancarDado();
	}

	// teste 6
	@Test
	public void scoreZero() {
		escolhaDoPersonagemX();
		assertEquals(fachada.verificarScore(), 0);
	}

	// teste 7
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		escolhaDoPersonagemX();

		assertEquals(0, fachada.getPosicaoPersonagem());
	}

	// teste 8
	@Test
	public void jogarDado() {
		escolhaDoPersonagemX();
		assertTrue("espera-se um numero > 0 e < 7", lancarDado() > 0
				&& lancarDado() < 7);
	}

	// teste 9
	@Test
	public void verificarRespostaPersonagemX() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		assertTrue("Espera-se que o personagem responda correto o desafio",
				fachada.isResultadoQuestao());
	}

	// teste 10
	@Test
	public void verificarPosicaoAposAcerto() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 11
	@Test
	public void verificarPosicaoAposErro() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagem() == 0);
	}

	// teste 12
	@Test
	public void verificarScoreAposAcerto() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		assertEquals(3, fachada.verificarScore());
	}

	// teste 13
	@Test
	public void verificarScoreAposErro() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		fachada.setRespostaPersonagemX("b");
		assertEquals(0, fachada.verificarScore());
	}

	// teste 14
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");

		assertEquals(2, fachada.verificarScore());
	}

	// teste 15 
	@Test
	public void vencedorPersonagemX() {
		escolhaDoPersonagemX();

		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();
		
		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		fachada.setRespostaPersonagemY("b");
		
		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		escolhaDaResposta();
		
		lancarDado();
		Questao questao4 = questao();
		criarQuestao(questao4);
		fachada.setRespostaPersonagemY("b");

		lancarDado();
		Questao questao5 = questao();
		criarQuestao(questao5);
		escolhaDaResposta();
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}
	// teste 16
	@Test
	public void vencedorPersonagemY() {
		escolhaDoPersonagemX();

		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		fachada.setRespostaPersonagemX("b");
		
		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		fachada.setRespostaPersonagemY("a");
		
		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		fachada.setRespostaPersonagemX("b");
		
		lancarDado();
		Questao questao4 = questao();
		criarQuestao(questao4);
		fachada.setRespostaPersonagemY("a");

		lancarDado();
		Questao questao5 = questao();
		criarQuestao(questao5);
		fachada.setRespostaPersonagemX("b");
		
		lancarDado();
		Questao questao6 = questao();
		criarQuestao(questao6);
		fachada.setRespostaPersonagemY("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}
	
	

	// teste 17
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		escolhaDaResposta();

		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		escolhaDaResposta();
		lancarDado();
	}

	// teste 18 
	@Test
	public void verificarCasaSurpresa() {

		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		fachada.casaSurpresa();
		assertTrue("Espera-se que casa contenha surpresa", fachada.isSurpresa());
	}

	// teste 19 
	@Test
	public void verificaPosicaoAposSupresaBoa() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		fachada.surpresaBoa();

		assertTrue("Espera-se que a posicao seja 2",
				fachada.getPosicaoPersonagem() == 2);
	}

	// teste 21
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		escolhaDoPersonagemX();
		Questao questao = questao();
		criarQuestao(questao);
	}

	// teste 22
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		escolhaDoPersonagemX();
		lancarDado();
		escolhaDaResposta();
	}

	// teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherAlternativaInexistente() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		fachada.setRespostaPersonagemX("d");
	}

	// teste 24
	@Test
	public void respostaValidaTest() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		assertEquals(true,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void respostaInvalidaTest() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		fachada.setRespostaPersonagemX("d");
		assertEquals(false,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 26
	@Test
	public void verificaRespostaDoPersonagemX() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		assertEquals("a", fachada.getRespostaPersonagemX());
	}

	// teste 27
	@Test
	public void testValorDadoAntesDeComeçarJogo() {
		escolhaDoPersonagemX();
		assertTrue(fachada.getValorDoDado() == 0);
	}

	// teste 28
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();
		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 29
	@Test
	public void verificarScoreSalvo() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();
		Questao questao2 = questao();
		criarQuestao(questao2);
		escolhaDaResposta();
		Questao questao3 = questao();
		criarQuestao(questao3);
		escolhaDaResposta();
		assertTrue("Espera-se que o score seja 9",
				fachada.verificarScore() == 9);
	}

	// teste 30
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		escolhaDoPersonagemX();
		fachada.setNomeJogador("Lucas");
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		fachada.encerrarAntesDoTempo();
	}

	// teste 31
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		escolhaDaResposta();
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				fachada.encerrarAntesDoTempo() == true);
	}

	// teste 32
	@Test
	public void verificarScoreDePartidaCancelada() {
		escolhaDoPersonagemX();
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		escolhaDaResposta();
		Questao questao2 = questao();
		criarQuestao(questao2);
		escolhaDaResposta();
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 6",
				fachada.verificarScore() == 6);
	}

	// teste 33
	@Test
	public void testNomeJogador() {
		escolhaDoPersonagemX();
		fachada.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				fachada.getNomeJogador() == "Joao");
	}

	// teste 34
	@Test
	public void testTamanhoNomeAceitavel() {
		escolhaDoPersonagemX();
		fachada.setNomeJogador("Luc");
		fachada.getTamanhoNome();
		assertTrue("Espera-se que o personagem seja Luc",
				fachada.getNomeJogador() == "Luc");
		assertEquals(3, fachada.getTamanhoNome());
	}

	// teste 35
	@Test
	public void cadastrarQuestao() {
		Questao questao = questao();

		cadastrarQuestao(questao);

		List<Questao> questoesSalvas = listarQuestoes();
		assertEquals(1, questoesSalvas.size());

		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
	}

	// teste 36
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarQuestaoNovamente() {
		Questao questao = questao();
		cadastrarQuestao(questao);
		cadastrarQuestao(questao);
	}

	// teste 37
	@Test
	public void removerQuestao() {
		Questao questao = questao();

		cadastrarQuestao(questao);

		List<Questao> questoesSalvas = listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		removerQuestao(questao);
		assertTrue(questoesSalvas.size() == 0);
	}

	// teste 38
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoDeNovo() {
		Questao questao = questao();

		cadastrarQuestao(questao);

		List<Questao> questoesSalvas = listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		removerQuestao(questao);
		removerQuestao(questao);
	}

	// teste 39
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoInexistente() {
		Questao questao = questao();
		removerQuestao(questao);
	}

	// teste 40 falta corrigir esse metodo
	@Test
	public void alterarQuestao() {
		Questao questao = questao();

		cadastrarQuestao(questao);

		List<Questao> questoesSalvas = listarQuestoes();
		fachada.alterarQuestao(questao);
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
	}

	// teste 41
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirPerguntaNula() {
		Questao questao = new Questao();
		questao.setPergunta(null);
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");
	}

	// teste 42
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirGabaritoSemResposta() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito(null);
	}

	// test 43
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirAlternativaNula() {
		String alternativas[] = new String[] { null, "", "" };
		Questao questao = new Questao();
		questao.setAlternativas(alternativas);
	}

	// Metodo auxiliar de teste
	public Questao questao() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");
		return questao;
	}

	// Metodo auxiliar de teste
	public void cadastrarQuestao(Questao questao) {
		fachada.cadastrarQuestao(questao);
	}

	public List<Questao> listarQuestoes() {
		return fachada.listarQuestoes();
	}

	// Metodo auxiliar de teste
	public void removerQuestao(Questao questao) {
		fachada.removerQuestao(questao);
	}

	public void criarQuestao(Questao questao) {
		fachada.criarQuestao(questao);
	}

	// Metodo auxiliar de teste
	public void escolhaDoPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
	}

	// Metodo auxiliar de teste
	public void escolhaDaResposta() {
		fachada.setRespostaPersonagemX("a");
	}

	// Metodo auxiliar de teste
	public int lancarDado() {
		return fachada.jogarDado();
	}

}
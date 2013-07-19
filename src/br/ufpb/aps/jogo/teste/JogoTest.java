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
		escolhaDoPersonagemX(true);
		escolhaDoPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				fachada.isEscolhaPersonagemX());
	}
	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		escolhaDoPersonagemX(true);
		lancarDado();
		escolhaDoPersonagemX(false);
	}
	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		lancarDado();
	}
	// teste 6
	@Test
	public void scoreZero() {
		escolhaDoPersonagemX(true);
		assertEquals(verificarScore(), 0);
	}
	// teste 7
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		escolhaDoPersonagemX(true);
		assertEquals(0, fachada.getPosicaoPersonagem());
	}
	// teste 8
	@Test
	public void jogarDado() {
		escolhaDoPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", lancarDado() > 0
				&& lancarDado() < 7);
	}
	// teste 9
	@Test
	public void verificarRespostaPersonagemX() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				fachada.isResultadoQuestao());
	}
	// teste 10
	@Test
	public void verificarPosicaoAposAcerto() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}
	// teste 11
	@Test
	public void verificarPosicaoAposErro() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagem() == 0);
	}
	// teste 12
	@Test
	public void verificarScoreAposAcerto() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		assertEquals(3,verificarScore());
	}
	// teste 13
	@Test
	public void verificarScoreAposErro() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("b");
		assertEquals(0,verificarScore());
	}
	// teste 14
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_X("b");

		assertEquals(2,verificarScore());
	}
	// teste 15
	@Test
	public void vencedorPersonagemX() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_Y("b");

		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		respostaDoPersonagem_X("a");

		lancarDado();
		Questao questao4 = questao();
		criarQuestao(questao4);
		respostaDoPersonagem_Y("b");

		lancarDado();
		Questao questao5 = questao();
		criarQuestao(questao5);
		respostaDoPersonagem_X("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}
	// teste 16
	@Test
	public void vencedorPersonagemY() {
		escolhaDoPersonagemX(true);

		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("b");

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_Y("a");

		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		respostaDoPersonagem_X("b");

		lancarDado();
		Questao questao4 = questao();
		criarQuestao(questao4);
		respostaDoPersonagem_Y("a");

		lancarDado();
		Questao questao5 = questao();
		criarQuestao(questao5);
		respostaDoPersonagem_X("b");

		lancarDado();
		Questao questao6 = questao();
		criarQuestao(questao6);
		respostaDoPersonagem_Y("a");
		assertTrue("Espera-se que o personagem Y tenha ganhado o jogo",
				fachada.acabou());
	}
	// teste 17
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");

		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_X("a");

		lancarDado();
		Questao questao3 = questao();
		criarQuestao(questao3);
		respostaDoPersonagem_X("a");
		lancarDado();
	}
	// teste 18
	@Test
	public void verificarCasaSurpresa() {

		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		fachada.casaSurpresa();
		assertTrue("Espera-se que casa contenha surpresa", fachada.isSurpresa());
	}
	// teste 19
	@Test
	public void verificaPosicaoAposSupresaBoa() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		fachada.surpresaBoa();

		assertTrue("Espera-se que a posicao seja 2",
				fachada.getPosicaoPersonagem() == 2);
	}
	// teste 20
	@Test
	public void verificaPosicaoAposSupresaRuim() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		fachada.surpresaRuim();
		assertTrue("Espera-se que a posicao seja 0",
				fachada.getPosicaoPersonagem() == 0);
	}
	// teste 21
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		escolhaDoPersonagemX(true);
		Questao questao = questao();
		criarQuestao(questao);
	}
	// teste 22
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		escolhaDoPersonagemX(true);
		lancarDado();
		respostaDoPersonagem_X("a");
	}
	// teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherAlternativaInexistente() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("d");
	}
	// teste 24
	@Test
	public void respostaValidaTest() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		assertEquals(true,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}
	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void respostaInvalidaTest() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("d");
		assertEquals(false,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}
	// teste 26
	@Test
	public void verificaRespostaDoPersonagemX() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		assertEquals("a", fachada.getRespostaPersonagemX());
	}
	// teste 27
	@Test
	public void testValorDadoAntesDeComeçarJogo() {
		escolhaDoPersonagemX(true);
		assertTrue(fachada.getValorDoDado() == 0);
	}
	// teste 28
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");
		lancarDado();
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_X("b");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}
	// teste 29
	@Test
	public void verificarScoreSalvo() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_X("a");
		Questao questao3 = questao();
		criarQuestao(questao3);
		respostaDoPersonagem_X("a");
		assertTrue("Espera-se que o score seja 9",
				verificarScore() == 9);
	}
	// teste 30
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		escolhaDoPersonagemX(true);
		fachada.setNomeJogador("Lucas");
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		fachada.encerrarAntesDoTempo();
	}
	// teste 31
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao = questao();
		criarQuestao(questao);
		respostaDoPersonagem_X("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				fachada.encerrarAntesDoTempo() == true);
	}
	// teste 32
	@Test
	public void verificarScoreDePartidaCancelada() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");
		Questao questao2 = questao();
		criarQuestao(questao2);
		respostaDoPersonagem_X("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 6",
				verificarScore() == 6);
	}
	// teste 33
	@Test
	public void testNomeJogador() {
		escolhaDoPersonagemX(true);
		fachada.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				fachada.getNomeJogador() == "Joao");
	}
	// teste 34
	@Test
	public void testTamanhoNomeAceitavel() {
		escolhaDoPersonagemX(true);
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
	// teste 43
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirAlternativaNula() {
		String alternativas[] = new String[] { null, "", "" };
		Questao questao = new Questao();
		questao.setAlternativas(alternativas);
	}
	// teste 44
	@Test
	public void verificarPersonagemXNoTabuleiro() {
		escolhaDoPersonagemX(true);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");
		assertTrue(fachada.verificarPersonagemNoTabuleiro());
	}
	// test 45
	@Test
	public void verificarPersonagemYNoTabuleiro() {
		fachada.setEscolhaPersonagemX(false);
		lancarDado();
		Questao questao1 = questao();
		criarQuestao(questao1);
		respostaDoPersonagem_X("a");
		assertFalse(fachada.verificarPersonagemNoTabuleiro());
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
	public void escolhaDoPersonagemX(boolean escolha) {
		fachada.setEscolhaPersonagemX(escolha);
	}
	// Metodo auxiliar de teste
	public void respostaDoPersonagem_X(String alternativa) {
		fachada.setRespostaPersonagemX(alternativa);
	}
	public void respostaDoPersonagem_Y(String alternativa) {
		fachada.setRespostaPersonagemX(alternativa);
	}
	public int verificarScore(){
		return fachada.verificarScore();
	}
	// Metodo auxiliar de teste
	public int lancarDado() {
		return fachada.jogarDado();
	}

}
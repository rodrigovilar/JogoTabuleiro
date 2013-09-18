package br.ufpb.aps.jogo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.jogo.entidade.Dado;
import br.ufpb.aps.jogo.entidade.Jogador;
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
		fachada.setEscolhaPersonagemX(true);;
		fachada.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				fachada.isEscolhaPersonagemX());
	}

	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		fachada.setEscolhaPersonagemX(false);
	}

	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		fachada.jogarDado();
	}

	// teste 6
	@Test
	public void scoreZero() {
		fachada.setEscolhaPersonagemX(true);
		assertEquals(fachada.verificarScore(), 0);
	}

	// teste 7
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		fachada.setEscolhaPersonagemX(true);

		assertEquals(0, fachada.getPosicaoPersonagem());
	}

	// teste 8
	@Test
	public void jogarDado() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", fachada.jogarDado() > 0
				&& fachada.jogarDado() < 7);
	}

	// teste 9
	@Test
	public void verificarRespostaPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				fachada.isResultadoQuestao());
	}

	// teste 10
	@Test
	public void verificarPosicaoAposAcerto() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");

		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 11
	@Test
	public void verificarPosicaoAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagem() == 0);
	}

	// teste 12
	@Test
	public void verificarScoreAposAcerto() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		assertEquals(3, fachada.verificarScore());
	}

	// teste 13
	@Test
	public void verificarScoreAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("b");
		assertEquals(0, fachada.verificarScore());
	}

	// teste 14
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");

		assertEquals(2, fachada.verificarScore());
	}

	// teste 15
	@Test
	public void vencedorPersonagemX() {
		fachada.setEscolhaPersonagemX(true);

		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemY("a");

		fachada.jogarDado();
		Questao questao3 = questao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao4 = questao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemY("b");

		fachada.jogarDado();
		Questao questao5 = questao();
		fachada.criarQuestao(questao5);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao6 = questao();
		fachada.criarQuestao(questao6);
		fachada.setRespostaPersonagemY("b");

		fachada.jogarDado();
		Questao questao7 = questao();
		fachada.criarQuestao(questao7);
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}

	// teste 16
	@Test
	public void vencedorPersonagemY() {
		fachada.setEscolhaPersonagemX(true);

		fachada.jogarDado();
		Questao questao1 = questao();

		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("b");

		fachada.jogarDado();
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemY("a");

		fachada.jogarDado();
		Questao questao3 = questao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("b");

		fachada.jogarDado();
		Questao questao4 = questao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemY("a");

		fachada.jogarDado();
		Questao questao5 = questao();
		fachada.criarQuestao(questao5);
		fachada.setRespostaPersonagemX("b");

		fachada.jogarDado();
		Questao questao6 = questao();
		fachada.criarQuestao(questao6);
		fachada.setRespostaPersonagemY("a");

		fachada.jogarDado();
		Questao questao7 = questao();
		fachada.criarQuestao(questao7);
		fachada.setRespostaPersonagemX("b");

		fachada.jogarDado();
		Questao questao8 = questao();
		fachada.criarQuestao(questao8);
		fachada.setRespostaPersonagemY("a");

		assertFalse("Espera-se que o personagem Y tenha ganhado o jogo",
				fachada.acabou());
	}

	// teste 17
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao3 = questao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");
		fachada.jogarDado();

		fachada.jogarDado();
		Questao questao4 = questao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemX("a");
		fachada.jogarDado();
	}

	// teste 18 novo
	@Test
	public void verificarCasaSurpresa() {

		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.casaSurpresa();
		assertTrue("Espera-se que casa contenha surpresa", fachada.isSurpresa());
	}

	// teste 19 modificado
	@Test
	public void verificaPosicaoAposSupresaBoa() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.surpresaBoa();

		assertTrue("Espera-se que a posicao seja 2",
				fachada.getPosicaoPersonagem() == 2);
	}

	// teste 20 novo teste feito
	@Test
	public void verificaPosicaoAposSupresaRuim() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.surpresaRuim();
		assertTrue("Espera-se que a posicao seja 0",
				fachada.getPosicaoPersonagem() == 0);
	}

	// teste 21
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		fachada.setEscolhaPersonagemX(true);
		Questao questao = questao();
		fachada.criarQuestao(questao);
	}

	// teste 22
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		fachada.setRespostaPersonagemX("a");
	}

	// teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherAlternativaInexistente() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("d");
	}

	// teste 24
	@Test
	public void respostaValidaTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		assertEquals(true,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void respostaInvalidaTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("d");
		assertEquals(false,
				fachada.respostaValida(fachada.getRespostaPersonagemX()));
	}

	// teste 26
	@Test
	public void verificaRespostaDoPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		assertEquals("a", fachada.getRespostaPersonagemX());
	}

	// teste 27
	@Test
	public void testValorDadoAntesDeComeçarJogo() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue(fachada.getValorDoDado() == 0);
	}

	// teste 28 quando os valores do dado tiver aleatorio vai dar erro
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {

		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");

		fachada.jogarDado();
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagem() == 1);
	}

	// teste 29
	@Test
	public void verificarScoreSalvo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");

		Questao questao2 = questao();
		fachada.criarQuestao(questao2);;
		fachada.setRespostaPersonagemX("a");

		Questao questao3 = questao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o score seja 9", fachada.verificarScore() == 9);
	}

	// teste 30 falta corrigir,estar sem o assert
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Lucas");
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
	}

	// teste 31
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao = questao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				fachada.encerrarAntesDoTempo() == true);
	}

	// teste 32
	@Test
	public void verificarScoreDePartidaCancelada() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");
		Questao questao2 = questao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 6", fachada.verificarScore() == 6);
	}

	// teste 33
	@Test
	public void testNomeJogador() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				fachada.getNomeJogador() == "Joao");
	}

	// teste 34
	@Test
	public void testTamanhoNomeAceitavel() {
		fachada.setEscolhaPersonagemX(true);
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
		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		assertEquals(1, questoesSalvas.size());

		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
	}

	// teste 36
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarQuestaoNovamente() {
		Questao questao = questao();
		fachada.cadastrarQuestao(questao);
		fachada.cadastrarQuestao(questao);
	}

	// teste 37
	@Test
	public void removerQuestao() {
		Questao questao = questao();

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		fachada.removerQuestao(questao);
		assertTrue(questoesSalvas.size() == 0);
	}

	// teste 38
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoDeNovo() {
		Questao questao = questao();
		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		fachada.removerQuestao(questao);
		fachada.removerQuestao(questao);
	}

	// teste 39
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoInexistente() {
		Questao questao = questao();
		fachada.removerQuestao(questao);
	}

	// teste 40 falta corrigir esse metodo
	@Test
	public void alterarQuestao() {
		Questao questao = questao();

		fachada.cadastrarQuestao(questao);

		List<Questao> questoesSalvas = fachada.listarQuestoes();
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

	// teste 44 novo
	@Test
	public void verificarPersonagemXNoTabuleiro() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");
		assertTrue(fachada.personagemNoTabuleiro());
	}

	// teste 45 novo
	// @Test falta corrigir esse metodo
	public void verificarPersonagemYNoTabuleiro() {
		fachada.setEscolhaPersonagemX(false);
		fachada.jogarDado();
		Questao questao1 = questao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemX("a");
		assertFalse(fachada.personagemNoTabuleiro());
	}

	// teste 46 novo
	@Test
	public void cadastrarJogador() {
		Jogador jogador = new Jogador();
		jogador.setNome("jogador_1");
		jogador.setSenha("1a2b");
		fachada.cadastrarJogador(jogador);

		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		assertEquals("Espera-se que o tamanho da lista de jogadores seja 1", 1,
				jogadoresSalvos.size());

		Jogador jogadorSalvo = jogadoresSalvos.get(0);

		assertEquals(
				"Espera-se que o jogador salvo seja igual ao jogador cadastrado",
				jogador, jogadorSalvo);
	}

	// teste 47 novo
	@Test
	public void removerJogador() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha("senha");
		fachada.cadastrarJogador(jogador);

		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		Jogador jogadorSalvo = jogadoresSalvos.get(0);
		assertEquals(jogador, jogadorSalvo);
		fachada.removerJogador(jogador);
		assertTrue(jogadoresSalvos.size() == 0);
	}

	// teste 48 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorComMesmaSenha() {
		Jogador jogador1 = new Jogador();
		jogador1.setSenha("1a");
		fachada.cadastrarJogador(jogador1);

		Jogador jogador2 = new Jogador();
		jogador2.setSenha("1a");
		fachada.cadastrarJogador(jogador2);
	}

	// teste 49 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerJogadorNovamente() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha("senha");
		fachada.cadastrarJogador(jogador);

		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		Jogador jogadorSalvo = jogadoresSalvos.get(0);
		assertEquals(jogador, jogadorSalvo);
		fachada.removerJogador(jogador);
		fachada.removerJogador(jogador);
	}

	// teste 50 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerJogadorInexistentee() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha("senha");
		fachada.removerJogador(jogador);
	}

	// teste 51 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorSemNome() {
		Jogador jogador = new Jogador();
		jogador.setNome(null);
		jogador.setSenha("senha");
		fachada.cadastrarJogador(jogador);
	}

	// teste 52 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorSemSenha() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha(null);
		fachada.cadastrarJogador(jogador);
	}

	// Metodo auxiliar de teste
	public Questao questao() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");
		return questao;
		
	}

}
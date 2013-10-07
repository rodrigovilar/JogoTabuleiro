package br.ufpb.aps.jogo.controle;

import br.ufpb.aps.jogo.entidade.Dado;
import br.ufpb.aps.jogo.entidade.Questao;
import br.ufpb.aps.jogo.entidade.Surpresa;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Tabuleiro implements Surpresa {

	private String tabuleiroX[] = new String[7];
	private String tabuleiroY[] = new String[7];

	private Dado dado = new Dado();
	private GerentePersonagem gp = new GerentePersonagem();
	private GerenteJogador gj = new GerenteJogador();
	private Questionario questionario = new Questionario();

	private boolean contemSurpresa;
	private String respostaPersonagem;
	private boolean responder;
	private boolean resultado;
	private boolean primeiroPersonagemDefinido;
	private boolean iniciouJogo;
	private boolean proximaJogadaX;

	public boolean verificarPersonagemXNoTabuleiro() {
		String personagem = tabuleiroX[getPosicaoPersonagemX()];

		if (personagem == null) {
			throw new ExcecaoJogoTabuleiro("Personagem nulo!");
		}
		if (personagem == "X") {
			return true;

		}
		return false;

	}

	public boolean verificarPersonagemYNoTabuleiro() {
		String personagem = tabuleiroY[getPosicaoPersonagemY()];

		if (personagem == null) {
			throw new ExcecaoJogoTabuleiro("Personagem nulo!");
		}
		if (personagem == "Y") {
			return true;

		}
		return false;

	}

	public void moverPersonagemXNoTabuleiro() {
		String escolha = "X";

		removerPersonagemXDaPosicao(getPosicaoPersonagemX());
		setAvancarPersonagemX();
		inserirPersonagemNaPosicao(getPosicaoPersonagemX(), escolha);
	}

	private void setAvancarPersonagemX() {
		gp.getPersonagem().setPosicaoX(dado.getValorDoDado());
	}

	public void moverPersonagemYNoTabuleiro() {
		String escolha = "Y";

		removerPersonagemYDaPosicao(getPosicaoPersonagemY());
		setAvancarPersonagemY();
		inserirPersonagemNaPosicao(getPosicaoPersonagemY(), escolha);
	}

	private void setAvancarPersonagemY() {
		gp.getPersonagem().setPosicaoY(dado.getValorDoDado());
	}

	public int getPosicaoPersonagemY() {
		return gp.getPersonagem().getPosicaoY();
	}

	public int getPosicaoPersonagemX() {
		return gp.getPersonagem().getPosicaoX();
	}

	public void casaSurpresa() {
		contemSurpresa = true;
	}

	public boolean isSurpresa() {
		return contemSurpresa;
	}

	public void surpresaBoa() {
		gp.getPersonagem().setPosicaoX(1);
	}

	public void surpresaRuim() {
		gp.getPersonagem().setPosicaoX(-1);
	}

	public void removerPersonagemXDaPosicao(int posicao) {
		tabuleiroX[posicao] = null;

	}

	public void removerPersonagemYDaPosicao(int posicao) {
		tabuleiroY[posicao] = null;

	}

	public void inserirPersonagemNaPosicao(int posicao, String personagem) {
		if (personagem == "X") {
			tabuleiroX[posicao] = personagem;
		}
		tabuleiroY[posicao] = personagem;

	}

	public boolean acabou() {

		boolean resultado = false;
		int sizeX = tabuleiroX.length;
		int sizeY = tabuleiroY.length;

		if (gp.getPersonagem().getPosicaoX() == sizeX - 1) {
			resultado = true;
		}

		if (gp.getPersonagem().getPosicaoY() == sizeY - 1) {
			resultado = false;
		}
		return resultado;
	}

	// -------------------------------Colocar codigos abaixo na classe Desafio.

	public void setEscolhaPersonagemX(boolean b) {
		if (iniciouJogo) {
			throw new ExcecaoJogoTabuleiro("O jogo ja foi iniciado!");
		}
		this.primeiroPersonagemDefinido = true;
		proximaJogadaX = b;
	}

	public boolean isEscolhaPersonagemX() {
		return proximaJogadaX;
	}

	public void setRespostaPersonagemX(String alternativa) {

		if (proximaJogadaX == false) {
			throw new ExcecaoJogoTabuleiro("Não é a vez do personagem X!");
		}

		if (!alternativaValida(alternativa)) {
			throw new ExcecaoJogoTabuleiro("Alternativa invalida!");
		}
		if (!isResponder()) {
			throw new ExcecaoJogoTabuleiro(
					"Nao pode responder antes da pergunta ser exibida!");
		}
		if (alternativa.equals(questionario.getQuestao().getGabarito())) {
			resultado = true;
			moverPersonagemXNoTabuleiro();

		} else {

			resultado = false;
		}
		adicionarPontuacao(resultado);

		this.respostaPersonagem = alternativa;
		proximaJogadaX = !proximaJogadaX;
	}

	public void setRespostaPersonagemY(String alternativa) {

		if (proximaJogadaX == true) {
			throw new ExcecaoJogoTabuleiro("Não é a vez do personagem Y!");
		}
		if (!alternativaValida(alternativa)) {
			throw new ExcecaoJogoTabuleiro("Alternativa invalida!");
		}
		if (!isResponder()) {
			throw new ExcecaoJogoTabuleiro(
					"Nao pode responder antes da pergunta ser exibida!");
		}
		if (alternativa.equals(questionario.getQuestao().getGabarito())) {
			resultado = true;
			moverPersonagemYNoTabuleiro();

		} else {

			resultado = false;
		}
		adicionarPontuacao(resultado);

		this.respostaPersonagem = alternativa;
		proximaJogadaX = !proximaJogadaX;
	}

	public int jogarDado() {

		if (acabou()) {
			throw new ExcecaoJogoTabuleiro("O jogo ja foi acabado!");
		}

		if (!primeiroPersonagemDefinido) {
			throw new ExcecaoJogoTabuleiro(" O Personagem nao foi definido!");
		}

		iniciouJogo = true;
		return dado.lancarDado();

	}

	// falta fazer para personagem Y
	public String getRespostaPersonagemX() {
		return respostaPersonagem;
	}

	public int valorDoScore() {
		return gj.getJogador().getScore();
	}

	private void adicionarPontuacao(boolean resultado) {

		if (resultado == true)
			gj.getJogador().aumentarScore();

		if (valorDoScore() != 0 && resultado == false) {
			gj.getJogador().diminuirScore();
		}
		if (valorDoScore() == 0 && resultado == false)
			return;
	}

	public boolean alternativaValida(String alternativa) {
		boolean result = false;
		if ((alternativa.equals("a") || alternativa.equals("b"))
				|| alternativa.equals("c")) {
			result = true;
		}
		return result;
	}

	public boolean isResponder() {
		return responder;
	}

	public void setResponder(boolean responder) {
		this.responder = responder;
	}

	public boolean isResultadoQuestao() {
		return resultado;
	}

	public int getValorDoDado() {
		return dado.getValorDoDado();
	}

	public void setValorDado(int valor) {
		dado.setValorDado(valor);
	}

	public Questao criarQuestao(Questao q) {
		if (getValorDoDado() == 0) {
			throw new ExcecaoJogoTabuleiro(
					"Questao nao pode ser exibida antes de lancar o dado!");
		}
		setResponder(true);
		questionario.getQuestao().setGabarito(q.getGabarito());
		questionario.criarQuestao(q);
		return q;
	}

	public void criarDado(Dado d) {
		this.dado = d;
	}

}

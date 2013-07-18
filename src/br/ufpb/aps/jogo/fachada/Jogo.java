package br.ufpb.aps.jogo.fachada;

import java.util.List;

import br.ufpb.aps.jogo.controle.GerentePersonagem;
import br.ufpb.aps.jogo.controle.Questionario;
import br.ufpb.aps.jogo.controle.Tabuleiro;
import br.ufpb.aps.jogo.entidade.Personagem;
import br.ufpb.aps.jogo.entidade.Questao;

/**
 * Essa classe sera a fachada do jogo.
 * 
 * @authors Jonathas Firmo, Adriano Patricio and Lucas Cruz
 */
public class Jogo {

	private Questionario questionario = new Questionario();
	private GerentePersonagem gerentePersonagem = new GerentePersonagem();
	private Tabuleiro tabuleiro = new Tabuleiro();

	public Questao criarQuestao(Questao questao) {
		return tabuleiro.criarQuestao(questao);
	}

	public void cadastrarQuestao(Questao questao) {
		questionario.cadastrarQuestao(questao);
	}

	public List<Questao> listarQuestoes() {
		return questionario.mostrarQuestoes();
	}

	public void removerQuestao(Questao questao) {
		questionario.removerQuestao(questao);
	}

	public Questao alterarQuestao(Questao questao) {
		return questionario.alterarQuestao(questao);
	}

	public int jogarDado() {
		return tabuleiro.jogarDado();

	}

	public int getValorDoDado() {
		return tabuleiro.getValorDoDado();
	}

	public Personagem getPersonagem() {
		return gerentePersonagem.getPersonagem();
	}

	public void criarPersonagem() {
		Personagem p = new Personagem();
		p.setNome(getNomeJogador());
		gerentePersonagem.adicionarPersonagem(p);
	}

	public boolean isEscolhaPersonagemX() {
		return tabuleiro.isEscolhaPersonagemX();
	}

	public void setEscolhaPersonagemX(boolean b) {
		tabuleiro.setEscolhaPersonagemX(b);
	}

	// só trabalha com o personagem X
	public int getPosicaoPersonagem() {
		return tabuleiro.getPosicaoPersonagemX();
	}

	public boolean personagemNoTabuleiro(){
		return tabuleiro.verificarPersonagemNoTabuleiro();
	}

	public String getRespostaPersonagemX() {
		return tabuleiro.getRespostaPersonagemX();
	}

	public void setRespostaPersonagemX(String alternativa) {

		tabuleiro.setRespostaPersonagemX(alternativa);
	}
	
	public void setRespostaPersonagemY(String alternativa) {

		tabuleiro.setRespostaPersonagemY(alternativa);
	}

	// esse metodo só acaba com personagem x, falta fazer para o Y.
	public boolean acabou() {
		return tabuleiro.acabou();
	}

	public boolean isResultadoQuestao() {
		return tabuleiro.isResultadoQuestao();
	}

	public int verificarScore() {
		return tabuleiro.getScore();
	}

	public boolean isSurpresa() {
		return tabuleiro.isSurpresa();
	}

	public void casaSurpresa() {
		tabuleiro.casaSurpresa();
	}

	public void surpresaBoa() {
		tabuleiro.surpresaBoa();
	}

	public void surpresaRuim() {
		tabuleiro.surpresaRuim();
	}

	public boolean respostaValida(String alternativa) {
		return tabuleiro.respostaValida(alternativa);
	}

	public void setNomeJogador(String nome) {
		gerentePersonagem.alterarNome(nome);
	}

	public String getNomeJogador() {
		return gerentePersonagem.obterNome();
	}

	public boolean encerrarAntesDoTempo() {
		return true;
	}

	public int getTamanhoNome() {
		return gerentePersonagem.getTamanhoNome();

	}

}

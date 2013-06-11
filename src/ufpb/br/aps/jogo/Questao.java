package ufpb.br.aps.jogo;

public class Questao {

	private int id;
	private String pergunta;
	private String[] alternativas;
	private String gabarito;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String[] alternativas) {
		this.alternativas = alternativas;
	}

	public String getGabarito() {

		return this.gabarito;
	}

	public void setGabarito(String resposta) {
		this.gabarito = resposta;
	}
	

}

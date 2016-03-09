package br.edu.unifei.ControlePatrimonio.Modelo.Entidades;

public class Consumo {

	private int id;
	private String Nome;
	private int Status;
	private String Observacao;
	private String Localizacao;

	public Consumo() {
	}

	public Consumo(int id, String nome, int status, String observacao, String localizacao) {
		super();
		this.id = id;
		Nome = nome;
		Status = status;
		Observacao = observacao;
		Localizacao = localizacao;
	}

	public String getLocalizacao() {
		return Localizacao;
	}

	public void setLocalizacao(String localizacao) {
		Localizacao = localizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

	@Override
	public String toString() {
		return "Consumo [id=" + id + ", Nome=" + Nome + ", Status=" + Status + ", Observacao=" + Observacao
				+ ", Localizacao=" + Localizacao + "]";
	}

}

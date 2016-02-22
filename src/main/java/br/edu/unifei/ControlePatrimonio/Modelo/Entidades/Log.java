package br.edu.unifei.ControlePatrimonio.Modelo.Entidades;

import java.sql.Date;

public class Log {

	private int id;
	private String Nome;
	private Date Date_Time;
	private String Historico;
	private int Acesso;
	
	public Log(){}

	public Log(int id, String nome, Date date_Time, String historico, int acesso) {
		super();
		this.id = id;
		Nome = nome;
		Date_Time = date_Time;
		Historico = historico;
		Acesso = acesso;
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

	public Date getDate_Time() {
		return Date_Time;
	}

	public void setDate_Time(Date date_Time) {
		Date_Time = date_Time;
	}

	public String getHistorico() {
		return Historico;
	}

	public void setHistorico(String historico) {
		Historico = historico;
	}

	public int getAcesso() {
		return Acesso;
	}

	public void setAcesso(int acesso) {
		Acesso = acesso;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", Nome=" + Nome + ", Date_Time=" + Date_Time + ", Historico=" + Historico
				+ ", Acesso=" + Acesso + "]";
	}	
}

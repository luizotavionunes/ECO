package br.edu.unifei.ControlePatrimonio.Modelo.Entidades;

import java.sql.Date;
import java.sql.Timestamp;

public class Log {

	private int id;
	private String nome;
	private Timestamp data;
	private String preHistorico;
	private String posHistorico;
	private int Acesso;

	public Log() {
	}

	public Log(int id, String nome, Timestamp data, String preHistorico, String posHistorico, int acesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.preHistorico = preHistorico;
		this.posHistorico = posHistorico;
		Acesso = acesso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getPreHistorico() {
		return preHistorico;
	}

	public void setPreHistorico(String preHistorico) {
		this.preHistorico = preHistorico;
	}

	public String getPosHistorico() {
		return posHistorico;
	}

	public void setPosHistorico(String posHistorico) {
		this.posHistorico = posHistorico;
	}

	public int getAcesso() {
		return Acesso;
	}

	public void setAcesso(int acesso) {
		Acesso = acesso;
	}

}

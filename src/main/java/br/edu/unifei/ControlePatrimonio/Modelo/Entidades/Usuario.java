package br.edu.unifei.ControlePatrimonio.Modelo.Entidades;

public class Usuario {

	private int id;
	private int Tipo;
	private String Senha;
	
	public Usuario(){}

	public Usuario(int id, int tipo, String senha) {
		super();
		this.id = id;
		Tipo = tipo;
		Senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		Tipo = tipo;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", Tipo=" + Tipo + ", Senha=" + Senha + "]";
	}
	
	
}

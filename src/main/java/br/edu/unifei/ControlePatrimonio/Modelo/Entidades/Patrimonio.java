package br.edu.unifei.ControlePatrimonio.Modelo.Entidades;

public class Patrimonio {

	private int id;
	private String descricao_fabricante_modelo;
	private int status;
	private String observacao;
	private String numero_serie;
	private String locacao;
	private String localizacao;
	private String tag_patrimonio;

	public Patrimonio() {
	}

	public Patrimonio(int id, String descricao_fabricante_modelo, int status, String observacao, String numero_serie,
			String locacao, String localizacao, String tag_patrimonio) {
		super();
		this.id = id;
		this.descricao_fabricante_modelo = descricao_fabricante_modelo;
		this.status = status;
		this.observacao = observacao;
		this.numero_serie = numero_serie;
		this.locacao = locacao;
		this.localizacao = localizacao;
		this.tag_patrimonio = tag_patrimonio;
	}

	public String getTag_patrimonio() {
		return tag_patrimonio;
	}

	public void setTag_patrimonio(String tag_patrimonio) {
		this.tag_patrimonio = tag_patrimonio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao_fabricante_modelo() {
		return descricao_fabricante_modelo;
	}

	public void setDescricao_fabricante_modelo(String descricao_fabricante_modelo) {
		this.descricao_fabricante_modelo = descricao_fabricante_modelo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getLocacao() {
		return locacao;
	}

	public void setLocacao(String locacao) {
		this.locacao = locacao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public String toString() {
		return "Patrimonio [id=" + id + ", descricao_fabricante_modelo=" + descricao_fabricante_modelo + ", status="
				+ status + ", observacao=" + observacao + ", numero_serie=" + numero_serie + ", locacao=" + locacao
				+ ", localizacao=" + localizacao + ", tag_patrimonio=" + tag_patrimonio + "]";
	}

}

package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;

/**
 * Classe de acesso aos dados da tabela patrimonio
 * 
 * @author Estagio
 *
 */
public class PatrimonioDAO {

	private Connection con = ConexaoFactory.getConnection();

	/**
	 * Método para inserção de registros na tabela patrimonio
	 * 
	 * @param Objeto
	 *            do tipo patrimonio
	 * @return Verdadeiro caso a inserção seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean inserir(Patrimonio patrimonio) throws SQLException {
		String sql = "INSERT INTO patrimonio (id, numero_serie, descricao_fabricante_modelo, locacao, localizacao, observacao, status, tag_patrimonio) VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {

			preparador.setString(1, patrimonio.getNumero_serie());
			preparador.setString(2, patrimonio.getDescricao_fabricante_modelo());
			preparador.setString(3, patrimonio.getLocacao());
			preparador.setString(4, patrimonio.getLocalizacao());
			preparador.setString(5, patrimonio.getObservacao());
			preparador.setInt(6, patrimonio.getStatus());
			preparador.setString(7, patrimonio.getTag_patrimonio());
			preparador.execute();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}

	/**
	 * Método utilizado
	 * 
	 * @param serial
	 * @return
	 * @throws SQLException
	 */
	public Patrimonio buscaId(int serial) throws SQLException {
		Patrimonio patrimonio = new Patrimonio();

		String sql = "SELECT * FROM patrimonio WHERE id='" + serial + "'";
		PreparedStatement preparador = con.prepareStatement(sql);
		try {
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				patrimonio.setId(resultado.getInt("id"));
				patrimonio.setStatus(resultado.getInt("status"));
				patrimonio.setDescricao_fabricante_modelo(resultado.getString("descricao_fabricante_modelo"));
				patrimonio.setLocalizacao(resultado.getString("localizacao"));
				patrimonio.setObservacao(resultado.getString("observacao"));
				patrimonio.setNumero_serie(resultado.getString("numero_serie"));
				patrimonio.setLocacao(resultado.getString("locacao"));
				patrimonio.setTag_patrimonio(resultado.getString("tag_patrimonio"));

			}
			return patrimonio;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Método utilizado para exclusão de um registro da tabela patrimonio
	 * 
	 * @param Numero
	 *            de identifcaçã odo registro a ser excluido (id)
	 * @return Verdadeiro caso a remoçao seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean remove(int id) throws SQLException {
		String sql = "DELETE FROM patrimonio WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setInt(1, id);
			preparador.execute();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}

	/**
	 * Método utilizado para alterar registros da tabela patrimonio
	 * 
	 * @param Objeto
	 *            do tipo patrimonio
	 * @return Verdadeiro caso a inserção seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean alterar(Patrimonio patrimonio) throws SQLException {
		String sql = "UPDATE patrimonio set numero_serie=?, descricao_fabricante_modelo=?, locacao=?, localizacao=?, observacao=?, status=?, tag_patrimonio=? WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setString(1, patrimonio.getNumero_serie());
			preparador.setString(2, patrimonio.getDescricao_fabricante_modelo());
			preparador.setString(3, patrimonio.getLocacao());
			preparador.setString(4, patrimonio.getLocalizacao());
			preparador.setString(5, patrimonio.getObservacao());
			preparador.setInt(6, patrimonio.getStatus());
			preparador.setString(7, patrimonio.getTag_patrimonio());
			preparador.setInt(8, patrimonio.getId());
			preparador.execute();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}

	/**
	 * Método utilizado para salvar ou inserir registros da tabela patrimonio
	 * 
	 * @param Objeto
	 *            do tipo patrimonio
	 * @throws SQLException
	 */
	public void salvar(Patrimonio pat) throws SQLException {
		if (pat.getId() != 0 && pat != null)
			alterar(pat);
		else
			inserir(pat);

	}

	/**
	 * Método utilizado para listar todos os registros da tabela patrimonio
	 * 
	 * @return Lista de objetos do tipo patrimonio
	 * @throws SQLException
	 */
	public List<Patrimonio> listarTodos() throws SQLException {
		String sql = "SELECT * FROM patrimonio";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Patrimonio> lista = new ArrayList<Patrimonio>();

		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Patrimonio patrimonio = new Patrimonio();
				patrimonio.setId(resultado.getInt("id"));
				patrimonio.setLocacao(resultado.getString("locacao"));
				patrimonio.setLocalizacao(resultado.getString("localizacao"));
				patrimonio.setDescricao_fabricante_modelo(resultado.getString("descricao_fabricante_modelo"));
				patrimonio.setObservacao(resultado.getString("observacao"));
				patrimonio.setNumero_serie(resultado.getString("numero_serie"));
				patrimonio.setStatus(resultado.getInt("status"));
				patrimonio.setTag_patrimonio(resultado.getString("tag_patrimonio"));
				lista.add(patrimonio);

			}
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}

	/**
	 * Método utilizado para filtrar registros da tabela patrimonio
	 * 
	 * @param String
	 *            descricao/fabricante/modelo
	 * @param String
	 *            status
	 * @param String
	 *            numero serie
	 * @param String
	 *            localizacao
	 * @return Lista com objetos do tipo patrimonio
	 * @throws SQLException
	 */
	public List<Patrimonio> listaBusca(String descricao_fabricante_modelo, String status, String numero_serie,
			String localizacao) throws SQLException {
		String sql = null;
		int status_ok;
		if (status.equals("0")) {
			status_ok = 0;
			status = null;
		} else
			status_ok = Integer.parseInt(status);

		if (descricao_fabricante_modelo.equals(""))
			descricao_fabricante_modelo = null;
		if (numero_serie.equals(""))
			numero_serie = null;
		if (localizacao.equals(""))
			localizacao = null;

		if (descricao_fabricante_modelo == null && status == null && numero_serie == null && localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE'%" + localizacao + "%'";

		} else if (descricao_fabricante_modelo == null && status == null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie + "%'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE status='" + status_ok + "'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%'";

		} else if (descricao_fabricante_modelo == null && status == null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND status='" + status_ok
					+ "'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo + "%'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie + "%' AND status='" + status_ok
					+ "'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo + "%'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' AND status='" + status_ok + "'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND status='" + status_ok + "'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND descricao_fabricante_modelo LIKE'%" + descricao_fabricante_modelo + "%'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE status='" + status_ok + "' AND numero_serie LIKE '%" + numero_serie
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo + "%'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND status='" + status_ok
					+ "' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo + "%'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' AND status='" + status_ok + "'";

		}

		PreparedStatement preparador = con.prepareStatement(sql);
		List<Patrimonio> lista = new ArrayList<Patrimonio>();
		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Patrimonio patrimonio = new Patrimonio();
				patrimonio.setId(resultado.getInt("id"));
				patrimonio.setStatus(resultado.getInt("status"));
				patrimonio.setDescricao_fabricante_modelo(resultado.getString("descricao_fabricante_modelo"));
				patrimonio.setLocalizacao(resultado.getString("localizacao"));
				patrimonio.setObservacao(resultado.getString("observacao"));
				patrimonio.setNumero_serie(resultado.getString("numero_serie"));
				patrimonio.setLocacao(resultado.getString("locacao"));
				patrimonio.setTag_patrimonio(resultado.getString("tag_patrimonio"));
				lista.add(patrimonio);

			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * Método utilizado para buscar um registro especifico da tabela patrimonio
	 * 
	 * @param String
	 *            serial do objeto pesquisado
	 * @return Objeto do tipo patrimonio
	 * @throws SQLException
	 */
	public Patrimonio buscaSerial(String serial) throws SQLException {
		Patrimonio patrimonio = new Patrimonio();

		String sql = "SELECT * FROM patrimonio WHERE numero_serie='" + serial + "'";
		PreparedStatement preparador = con.prepareStatement(sql);
		try {
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				patrimonio.setId(resultado.getInt("id"));
				patrimonio.setStatus(resultado.getInt("status"));
				patrimonio.setDescricao_fabricante_modelo(resultado.getString("descricao_fabricante_modelo"));
				patrimonio.setLocalizacao(resultado.getString("localizacao"));
				patrimonio.setObservacao(resultado.getString("observacao"));
				patrimonio.setNumero_serie(resultado.getString("numero_serie"));
				patrimonio.setLocacao(resultado.getString("locacao"));
				patrimonio.setTag_patrimonio(resultado.getString("tag_patrimonio"));

			}
			return patrimonio;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Método utilizado para exportar registros buscados na tabela patrimonio
	 * 
	 * @param String
	 *            descricao/fabricante/modelo
	 * @param String
	 *            status
	 * @param String
	 *            numero serie
	 * @param String
	 *            localizacao
	 * @throws SQLException
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void exportarArquivos(String descricao_fabricante_modelo, String status, String numero_serie,
			String localizacao) throws SQLException, InterruptedException, IOException {

		String sql = null;
		int status_ok;
		if (status.equals("0")) {
			status_ok = 0;
			status = null;
		} else
			status_ok = Integer.parseInt(status);

		if (descricao_fabricante_modelo.equals(""))
			descricao_fabricante_modelo = null;
		if (numero_serie.equals(""))
			numero_serie = null;
		if (localizacao.equals(""))
			localizacao = null;
		
		if (descricao_fabricante_modelo == null && status == null && numero_serie == null && localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE'%" + localizacao
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status == null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status == null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie + "%' AND status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%" + numero_serie
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie == null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' AND status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo == null && status != null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status == null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND descricao_fabricante_modelo LIKE'%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie != null
				&& localizacao == null) {
			sql = "SELECT * from patrimonio WHERE status='" + status_ok + "' AND numero_serie LIKE '%" + numero_serie
					+ "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie == null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND status='" + status_ok
					+ "' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		} else if (descricao_fabricante_modelo != null && status != null && numero_serie != null
				&& localizacao != null) {
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%" + localizacao + "%' AND numero_serie LIKE '%"
					+ numero_serie + "%' AND descricao_fabricante_modelo LIKE '%" + descricao_fabricante_modelo
					+ "%' AND status='" + status_ok
					+ "' INTO OUTFILE '/tmp/arquivoPatrimonio.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		}

		
		// caminho windows 
		
		PreparedStatement preparador = con.prepareStatement(sql);
		try {
			preparador.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.StringValueExp;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;

/**
 * Classe de acesso aos dados da tabela consumo
 * 
 * @author Estagio
 *
 */
public class ConsumoDAO {

	private Connection con = ConexaoFactory.getConnection();

	/**
	 * Método para inserção de registros na tabela consumo
	 * 
	 * @param Objeto
	 *            do tipo consumo
	 * @return Verdadeiro caso a inserção seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean inserir(Consumo consumo) throws SQLException {
		String sql = "INSERT INTO consumo (id, nome, status, observacao, localizacao) VALUES (null, ?, ?, ?, ?)";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {

			preparador.setString(1, consumo.getNome());
			preparador.setInt(2, consumo.getStatus());
			preparador.setString(3, consumo.getObservacao());
			preparador.setString(4, consumo.getLocalizacao());
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
	 * Este método é responsável por realizar a exclusão de um determinado
	 * registro da tabela consumo
	 * 
	 * @param Numero
	 *            de identificação do registro a ser excluido (id)
	 * @return Verdadeiro caso a remoçao seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean remove(int ide) throws SQLException {
		String sql = "DELETE FROM consumo WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setInt(1, ide);
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
	 * Método responsável por fazer o update de dados da tabela consumo
	 * 
	 * @param Objeto
	 *            do tipo consumo
	 * @return Verdadeiro caso a edição seja bem sucedida e falso caso contrário
	 * @throws SQLException
	 */
	public boolean alterar(Consumo consumo) throws SQLException {
		String sql = "UPDATE consumo set nome=?, status=?, localizacao=?, observacao=? WHERE id=" + consumo.getId();
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setString(1, consumo.getNome());
			preparador.setInt(2, consumo.getStatus());
			preparador.setString(3, consumo.getLocalizacao());
			preparador.setString(4, consumo.getObservacao());
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
	 * Método responsável por listar todos registros da tabela consumo
	 * 
	 * @return Lista de objetos do tipo consumo
	 * @throws SQLException
	 */
	public List<Consumo> listarTodos() throws SQLException {
		String sql = "SELECT * FROM consumo";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Consumo> lista = new ArrayList<Consumo>();

		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Consumo consumo = new Consumo();
				consumo.setId(resultado.getInt("id"));
				consumo.setNome(resultado.getString("nome"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setStatus(resultado.getInt("status"));
				consumo.setObservacao(resultado.getString("observacao"));
				lista.add(consumo);

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
	 * Método utilizado para editar ou inserir registros na tabela consumo
	 * 
	 * @param Objeto
	 *            do tipo consumo
	 * @throws SQLException
	 */
	public void salvar(Consumo consumo) throws SQLException {
		// System.out.println("Id do consumougig: " + consumo.getId());
		if (consumo.getId() != 0 && consumo != null) {
			alterar(consumo);

		} else {

			inserir(consumo);
		}

	}

	/**
	 * Método responsável por realizar buscas nos registros da tabela consumo
	 * 
	 * @param String
	 *            nome do obeto a ser pesquisado
	 * @param String
	 *            status do objeto a ser pesquisado
	 * @param String
	 *            Localização do objeto a ser pesquisdado
	 * @return Lista de objetos do tipo consumo
	 * @throws SQLException
	 */
	public List<Consumo> listaBusca(String nome, String status, String localizacao) throws SQLException {
		System.out.println("statusFRomServ " + status);
		String sql = null;
		int status_ok;
		if (status.equals("0")) {
			status_ok = 0;
			status = null;
		} else
			status_ok = Integer.parseInt(status);

		System.out.println("nome " + nome + " status " + status + " localizacao " + localizacao);

		if (nome == null && status == null && localizacao != null) {
			sql = "SELECT * from consumo WHERE localizacao LIKE '%" + localizacao + "%'";
		} else if (nome == null && status != null && localizacao == null) {
			sql = "SELECT * from consumo WHERE status LIKE '%" + status_ok + "%'";
		} else if (nome != null && status == null && localizacao == null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome + "%'";
		} else if (nome == null && status != null && localizacao != null) {
			sql = "SELECT * from consumo WHERE status LIKE '%" + status_ok + "%' AND localizacao LIKE '%" + localizacao
					+ "%'";
		} else if (nome != null && status == null && localizacao != null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome + "%' AND localizacao LIKE '%" + localizacao + "%'";
		} else if (nome != null && status != null && localizacao == null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome + "%' AND status LIKE '%" + status_ok + "%'";
		} else if (nome != null && status != null && localizacao != null) {
			sql = "SELECT * FROM consumo WHERE nome Like '%" + nome + "%' AND status LIKE '%" + status_ok
					+ "%' AND localizacao LIKE '%" + localizacao + "%'";
		}

		PreparedStatement preparador = con.prepareStatement(sql);
		System.out.println("Query " + sql);
		List<Consumo> lista = new ArrayList<Consumo>();
		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Consumo consumo = new Consumo();
				consumo.setId(resultado.getInt("id"));
				consumo.setStatus(resultado.getInt("status"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setObservacao(resultado.getString("observacao"));
				consumo.setNome(resultado.getString("nome"));
				lista.add(consumo);

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
	 * Método utilizado para exportação de registros em csv da tabela consumo
	 * 
	 * @param String
	 *            nome do obeto a ser pesquisado
	 * @param String
	 *            status do objeto a ser pesquisado
	 * @param String
	 *            Localização do objeto a ser pesquisdado
	 * @throws SQLException
	 */
	public void exportarItens(String nome, String status, String localizacao) throws SQLException {

		//System.out.println("statusFRomServ " + status);
		String sql = null;
		int status_ok;
		if (status.equals("0")) {
			status_ok = 0;
			status = null;
		} else
			status_ok = Integer.parseInt(status);

		System.out.println("nome " + nome + " status " + status + " localizacao " + localizacao);

		if (nome == null && status == null && localizacao != null) {
			sql = "SELECT * from consumo WHERE localizacao LIKE '%" + localizacao
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome == null && status != null && localizacao == null) {
			sql = "SELECT * from consumo WHERE status LIKE '%" + status_ok
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome != null && status == null && localizacao == null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome == null && status != null && localizacao != null) {
			sql = "SELECT * from consumo WHERE status LIKE '%" + status_ok + "%' AND localizacao LIKE '%" + localizacao
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome != null && status == null && localizacao != null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome + "%' AND localizacao LIKE '%" + localizacao
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome != null && status != null && localizacao == null) {
			sql = "SELECT * from consumo WHERE nome LIKE '%" + nome + "%' AND status LIKE '%" + status_ok
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		} else if (nome != null && status != null && localizacao != null) {
			sql = "SELECT * FROM consumo WHERE nome Like '%" + nome + "%' AND status LIKE '%" + status_ok
					+ "%' AND localizacao LIKE '%" + localizacao
					+ "%' INTO OUTFILE '/tmp/arquivoConsumo.csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		}

		PreparedStatement preparador = con.prepareStatement(sql);
		//System.out.println("Query " + sql);
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

	/**
	 * Método responsável por realizar a busca de um determinado registro na
	 * tabela consumo
	 * 
	 * @param Numero
	 *            de identificação do objeto a ser buscado em string (id)
	 * @return Objeto do tipo consumo
	 * @throws SQLException
	 */
	public Consumo buscaId(String id) throws SQLException {
		Consumo consumo = new Consumo();

		String sql = "SELECT * FROM consumo WHERE id='" + id + "'";
		PreparedStatement preparador = con.prepareStatement(sql);
		System.out.println("Query " + sql);
		try {
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				consumo.setId(resultado.getInt("id"));
				consumo.setStatus(resultado.getInt("status"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setObservacao(resultado.getString("observacao"));
				consumo.setNome(resultado.getString("nome"));
			}
			return consumo;

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
}

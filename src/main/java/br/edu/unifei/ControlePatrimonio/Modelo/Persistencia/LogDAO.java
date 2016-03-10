package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;

/**
 * Classe de acesso aos dados da tabela de logs do sistema
 * 
 * @author Estagio
 *
 */
public class LogDAO {

	private Connection con = ConexaoFactory.getConnection();

	/**
	 * Método utilizado para registrar um novo log no sistema
	 * 
	 * @param Objeto
	 *            do tipo Log
	 * @return Verdadeiro caso a inserção seja bem sucedida e falso caso
	 *         contrário
	 * @throws SQLException
	 */
	public boolean inserir(Log log) throws SQLException {
		int id = 0;
		String sql = "INSERT INTO log (id, nome, data, preHistorico, posHistorico, acesso) VALUES (null, ?, CURRENT_TIMESTAMP, ? , ? , ?)";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {

			preparador.setString(1, log.getNome());
			preparador.setString(2, log.getPreHistorico());
			preparador.setString(3, log.getPosHistorico());
			preparador.setInt(4, log.getAcesso());
			preparador.execute();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
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
	 * Método responsável por apresentar todos os registros de logs do sistema
	 * 
	 * @return Lista de objetos do tipo Log
	 * @throws SQLException
	 */
	public List<Log> listarTodos() throws SQLException {
		String sql = "SELECT * FROM log";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Log> lista = new ArrayList<Log>();

		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Log log = new Log();
				log.setId(resultado.getInt("id"));
				log.setNome(resultado.getString("nome"));
				log.setPreHistorico(resultado.getString("preHistorico"));
				log.setPosHistorico(resultado.getString("posHistorico"));
				log.setAcesso(resultado.getInt("acesso"));
				log.setData(resultado.getTimestamp("data"));
				lista.add(log);

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
	 * Método utilizado para buscar um patrimonio/consumo em um registro de log
	 * 
	 * @param String
	 *            serial/nome/descrição/ valor do objeto buscado
	 * @return Lista de objetos do tipo Log
	 * @throws SQLException
	 */
	public List<Log> buscaLog(String serial) throws SQLException {
		List<Log> lista = new ArrayList<Log>();

		String sql = "SELECT * FROM log WHERE preHistorico LIKE '%" + serial + "%' OR posHistorico LIKE '%" + serial
				+ "%'";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Log log = new Log();
				log.setId(resultado.getInt("id"));
				log.setNome(resultado.getString("nome"));
				log.setPreHistorico(resultado.getString("preHistorico"));
				log.setPosHistorico(resultado.getString("posHistorico"));
				log.setAcesso(resultado.getInt("acesso"));
				log.setData(resultado.getTimestamp("data"));
				lista.add(log);

			}

			return lista;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			preparador.close();

		}

		return null;

	}

}

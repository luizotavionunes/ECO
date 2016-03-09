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

public class LogDAO {

	private Connection con = ConexaoFactory.getConnection();

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

	public boolean remove(String nome) throws SQLException {
		String sql = "DELETE FROM log WHERE nome=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setString(1, nome);
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

	public boolean alterar(Log log) throws SQLException {
		String sql = "UPDATE log set nome=?, historico=?, acesso=?, date_time=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setString(1, log.getNome());
			preparador.setString(2, log.getPreHistorico());
			preparador.setInt(3, log.getAcesso());
			preparador.setDate(4, log.getData());
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
				log.setData(resultado.getDate("data"));
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

	public List<Log> buscaLog(String serial) throws SQLException {
		List<Log> lista = new ArrayList<Log>();

		String sql = "SELECT * FROM log WHERE preHistorico LIKE '%" + serial + "%' OR posHistorico LIKE '%" + serial
				+ "%'";
		PreparedStatement preparador = con.prepareStatement(sql);
		System.out.println("sql: " + sql);
		try {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Log log = new Log();
				log.setId(resultado.getInt("id"));
				log.setNome(resultado.getString("nome"));
				log.setPreHistorico(resultado.getString("preHistorico"));
				log.setPosHistorico(resultado.getString("posHistorico"));
				log.setAcesso(resultado.getInt("acesso"));
				log.setData(resultado.getDate("data"));
				lista.add(log);

			}
			for (int i = 0; i < lista.size(); i++) {
				System.out.println("Numero de serie: " + lista.get(i).getPreHistorico() + " Descricao: ");
				// + lista.get(i).getDescricao_fabricante_modelo());

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

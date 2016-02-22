package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo;


public class ConsumoDAO {

	private Connection con = ConexaoFactory.getConnection();
	
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

	public boolean remove(String nome) throws SQLException {
		String sql = "DELETE FROM consumo WHERE nome=?";
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
	
	public boolean alterar(Consumo consumo) throws SQLException{
		String sql = "UPDATE consumo set nome=?, status=?, localizacao=?, observacao=?";
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
	
	public List<Consumo> listarTodos() throws SQLException{
		String sql = "SELECT * FROM consumo";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Consumo> lista = new ArrayList<Consumo>();
		

		
		try {
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
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
		
	
	public List<Consumo> buscaPorSala(String localizacao) throws SQLException {
		String sql = "SELECT * FROM consumo where localizacao=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Consumo> lista = new ArrayList<Consumo>();
		try {
			preparador.setString(1, localizacao);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Consumo consumo = new Consumo();
				consumo.setId(resultado.getInt("id"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setNome(resultado.getString("nome"));
				consumo.setObservacao(resultado.getString("observacao"));
				consumo.setStatus(resultado.getInt("status"));
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
	
	public List<Consumo> buscaPorNome(String nome) throws SQLException {
		String sql = "SELECT * FROM consumo where nome='%?%'";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Consumo> lista = new ArrayList<Consumo>();
		try {
			preparador.setString(1, nome);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Consumo consumo = new Consumo();
				consumo.setId(resultado.getInt("id"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setNome(resultado.getString("nome"));
				consumo.setObservacao(resultado.getString("observacao"));
				consumo.setStatus(resultado.getInt("status"));
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
	
	public List<Consumo> buscaPorStatus(int status) throws SQLException {
		String sql = "SELECT * FROM consumo where status=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Consumo> lista = new ArrayList<Consumo>();
		try {
			preparador.setInt(1, status);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Consumo consumo = new Consumo();
				consumo.setId(resultado.getInt("id"));
				consumo.setLocalizacao(resultado.getString("localizacao"));
				consumo.setNome(resultado.getString("nome"));
				consumo.setObservacao(resultado.getString("observacao"));
				consumo.setStatus(resultado.getInt("status"));
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
}

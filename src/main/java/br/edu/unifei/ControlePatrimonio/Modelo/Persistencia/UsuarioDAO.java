package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;


public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();
	
	public boolean inserir(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario (id, tipo, senha) VALUES (null, ?, ?)";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
		
			preparador.setInt(1, usuario.getTipo());
			preparador.setString(2, usuario.getSenha());
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

	public boolean remove(int id) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id=?";
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
	
	public boolean alterar(Usuario usuario) throws SQLException{
		String sql = "UPDATE usuario set tipo=?, senha=? WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);

		try {
			preparador.setInt(1, usuario.getTipo());
			preparador.setString(2, usuario.getSenha());
			preparador.setInt(3, usuario.getId());
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
	
	public List<Usuario> listarTodos() throws SQLException{
		String sql = "SELECT * FROM usuario";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Usuario> lista = new ArrayList<Usuario>();
		

		
		try {
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setTipo(resultado.getInt("tipo"));
				lista.add(usuario);
							
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
	
	public Usuario autenticar(Usuario user) throws SQLException	{
		String sql = "SELECT * FROM usuario WHERE tipo =? AND senha=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		try{
			preparador.setInt(1, user.getTipo());
			preparador.setString(2, user.getSenha());
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setTipo(resultado.getInt("tipo"));
				usuario.setSenha(resultado.getString("senha"));
				return usuario;
			}
		} catch (Exception e){
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
	
	public void salvar(Usuario usu) throws SQLException{
		if(usu.getId() !=0 && usu !=null)
			alterar(usu);
		else
			inserir(usu);		
		
	}
	
	
	public Usuario buscaId(int id) throws SQLException{
		Usuario usu = new Usuario();
		
		String sql = "SELECT * FROM usuario WHERE id='"+id+"'";
		PreparedStatement preparador = con.prepareStatement(sql);
		System.out.println("Query "+ sql);
		try {
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				usu.setId(resultado.getInt("id"));
				usu.setSenha(resultado.getString("senha"));
				usu.setTipo(resultado.getInt("tipo"));
				return usu;
				
			}
			
		
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

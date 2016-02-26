package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SingleSelectionModel;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;

public class PatrimonioDAO {

	private Connection con = ConexaoFactory.getConnection();

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
	
	public boolean alterar(Patrimonio patrimonio) throws SQLException{
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
	
	public void salvar(Patrimonio pat) throws SQLException{
		if(pat.getId() !=0 && pat !=null)
			alterar(pat);
		else
			inserir(pat);		
		
	}
	
	
	public List<Patrimonio> listarTodos() throws SQLException{
		String sql = "SELECT * FROM patrimonio";
		PreparedStatement preparador = con.prepareStatement(sql);
		List<Patrimonio> lista = new ArrayList<Patrimonio>();
		

		
		try {
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
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
	
	public List<Patrimonio> listaBusca(String descricao_fabricante_modelo, String status, String numero_serie, String localizacao) throws SQLException{
		//System.out.println("statusFRomServ " + status);
		String sql = null;
		int status_ok;
		if(status.equals("0")){
			status_ok = 0;
			status=null;
		}else
			status_ok = Integer.parseInt(status);
		
		if(descricao_fabricante_modelo.equals(""))
			descricao_fabricante_modelo=null;
		if(numero_serie.equals(""))
			numero_serie=null;
		if(localizacao.equals(""))
			localizacao=null;
		
		
		System.out.println("descricao "+ descricao_fabricante_modelo + " status " + status + " numero serie " + numero_serie + " localizacao " + localizacao);
		
		if( descricao_fabricante_modelo == null && status == null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE'%"+localizacao+"%'";
			
		}else if(descricao_fabricante_modelo == null && status == null && numero_serie != null && localizacao == null){
			sql  = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE status='"+status_ok+"'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%'";
			
		}else if(descricao_fabricante_modelo == null && status == null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND status='"+status_ok+"'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%' AND status='"+status_ok+"'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' AND status='"+status_ok+"'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND status='"+status_ok+"'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE'%"+descricao_fabricante_modelo+"%'";

		}else if(descricao_fabricante_modelo != null && status != null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE status='"+status_ok+"' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND status='"+status_ok+"' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' AND status='"+status_ok+"'";
			
		}

		PreparedStatement preparador = con.prepareStatement(sql);
		System.out.println("Query "+ sql);
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
	

	public Patrimonio buscaSerial(String serial) throws SQLException{
		Patrimonio patrimonio = new Patrimonio();
		
		String sql = "SELECT * FROM patrimonio WHERE numero_serie='"+serial+"'";
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
	
	public void exportarArquivos(String descricao_fabricante_modelo, String status, String numero_serie, String localizacao) throws SQLException{
			
		String sql = null;
		int status_ok;
		if(status.equals("0")){
			status_ok = 0;
			status=null;
		}else
			status_ok = Integer.parseInt(status);
		
		if(descricao_fabricante_modelo.equals(""))
			descricao_fabricante_modelo=null;
		if(numero_serie.equals(""))
			numero_serie=null;
		if(localizacao.equals(""))
			localizacao=null;
		
		String sqlData="SELECT CURRENT_TIMESTAMP(0)";
		String resultData=null;
		String resultTime = null;
		String nome_arquivo = null;
		Timestamp data=null;
		PreparedStatement preparadorData = con.prepareStatement(sqlData);
		try {
			ResultSet resultado = preparadorData.executeQuery();		
			if (resultado.next()){				
				data = resultado.getTimestamp(1);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
				resultData  = dateFormat.format(data);
				resultTime = timeFormat.format(data);
				nome_arquivo = resultData+"_"+resultTime;
				System.out.println(nome_arquivo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparadorData.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		if( descricao_fabricante_modelo == null && status == null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE'%"+localizacao+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status == null && numero_serie != null && localizacao == null){
			sql  = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status == null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%' AND status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie == null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' AND status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo == null && status != null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status == null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE'%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";

		}else if(descricao_fabricante_modelo != null && status != null && numero_serie != null && localizacao == null){
			sql = "SELECT * from patrimonio WHERE status='"+status_ok+"' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie == null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND status='"+status_ok+"' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
			
		}else if(descricao_fabricante_modelo != null && status != null && numero_serie != null && localizacao != null){
			sql = "SELECT * from patrimonio WHERE localizacao LIKE '%"+localizacao+"%' AND numero_serie LIKE '%"+numero_serie+"%' AND descricao_fabricante_modelo LIKE '%"+descricao_fabricante_modelo+"%' AND status='"+status_ok+"' INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivo"+nome_arquivo+".csv' FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'";
		}
		
		System.out.println(sql);
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

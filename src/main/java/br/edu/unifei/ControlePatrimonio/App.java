package br.edu.unifei.ControlePatrimonio;

import java.sql.SQLException;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        
    	
    	Log log = new Log();
    	log.setAcesso(1);
    	log.setNome("teste log");
    	log.setPreHistorico("antes");
    	inserirLog(log);
    	
    }
    
    
    public static void inserirLog(Log log) throws SQLException{
    	LogDAO logDao = new LogDAO();
    	logDao.inserir(log);
    	
    	
    }
    
    
}

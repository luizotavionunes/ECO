package br.edu.unifei.ControlePatrimonio.Controle;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;
import br.edu.unifei.ControlePatrimonio.util.CopiaArquivo;

@WebServlet("/logcontroller.do")
public class LogController extends HttpServlet {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		if (acao.equals("listar")) {
			LogDAO logDAO = new LogDAO();
			List<Log> lista= null;
			try {
				lista = logDAO.listarTodos();
			} catch (SQLException e) {
				System.out.println("Erro ao carregar lista de logs.");
				e.printStackTrace();
			}
			
		
			req.setAttribute("listaLog", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaLog.jsp");
			dispatcher.forward(req, resp);
		}


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		
		if(acao.equals("busca")){
			String numero_serie = req.getParameter("serial");
			LogDAO logDao = new LogDAO();
			List<Log> lista = null;
			try {
				lista=logDao.buscaLog("numero_serie");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.setAttribute("listaLog", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaLog.jsp");
			dispatcher.forward(req, resp);
			
		}
		




}
	

	
	

	

}

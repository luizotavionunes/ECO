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
import javax.servlet.http.HttpSession;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;
import br.edu.unifei.ControlePatrimonio.util.CopiaArquivo;



/**
 * Servlet responsável por todas operações ligadas aos logs do sistema
 * opções disponiveis: listagem e busca.
 * @author Estagio
 *
 */
@WebServlet("/logcontroller.do")
public class LogController extends HttpServlet {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	
	/*
	 * 
	 * Método responsável por redirecionar as requisições dos usuarios ações
	 * disponiveis: Listagem e Busca(non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		// Ação realizada para apresentar a listagem de todos os logs do sistema
		if (acao.equals("listar")) {
			if (usuAUT.getTipo() == 3) {
				LogDAO logDAO = new LogDAO();
				List<Log> lista = null;
				try {
					lista = logDAO.listarTodos();
				} catch (SQLException e) {
					System.out.println("Erro ao carregar lista de logs.");
					e.printStackTrace();
				}

				req.setAttribute("listaLog", lista);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaLog.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		//// Ação realizada para apresentar a listagem de logs filtrados do sistema
		if (acao.equals("busca")) {
			if (usuAUT.getTipo() == 3) {
				String numero_serie = req.getParameter("serial");
				LogDAO logDao = new LogDAO();
				List<Log> lista = null;
				try {
					lista = logDao.buscaLog(numero_serie);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				req.setAttribute("listaLog", lista);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaLog.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		}

	}

}

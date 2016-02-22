package br.edu.unifei.ControlePatrimonio.Controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;

@WebServlet("/consumo.do")
public class ConsumoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");

		if (acao.equals("cad")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);

		} else if (acao.equals("listar")) {
			PatrimonioDAO patDAO = new PatrimonioDAO();
			List<Patrimonio> lista= null;
			
			try {
				lista = patDAO.listarTodos();
			} catch (SQLException e) {
				System.out.println("Erro ao carregar lista de patrimonio.");
				e.printStackTrace();
			}
		
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaConsumo.jsp");
			dispatcher.forward(req, resp);
			

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String descricao_fabricante_modelo = req.getParameter("descricao");
		String status = req.getParameter("status");
		String observacao = req.getParameter("observacao");
		String numero_serie = req.getParameter("numero_serie");
		String locacao = req.getParameter("locacao");
		String localizacao = req.getParameter("localizacao");

		Patrimonio pat = new Patrimonio();

		pat.setId(0);
		pat.setDescricao_fabricante_modelo(descricao_fabricante_modelo);
		if (status.equals("Ativo")) {
			pat.setStatus(1);
		} else
			pat.setStatus(0);
		pat.setStatus(Integer.parseInt(status));
		pat.setLocacao(locacao);
		pat.setLocalizacao(localizacao);
		pat.setNumero_serie(numero_serie);
		pat.setObservacao(observacao);

		PatrimonioDAO patDAO = new PatrimonioDAO();

		try {
			patDAO.inserir(pat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro na inserção de patrimônio.");
			e.printStackTrace();
		}
		resp.sendRedirect("patrimonio.do?acao=listar");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}

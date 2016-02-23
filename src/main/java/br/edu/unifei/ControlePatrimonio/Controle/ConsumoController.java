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

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO;

@WebServlet("/consumo.do")
public class ConsumoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");

		if (acao.equals("cad")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);

		} else if (acao.equals("listar")) {
			ConsumoDAO conDAO = new ConsumoDAO();
			List<Consumo> lista= null;
			
			try {
				lista = conDAO.listarTodos();
			} catch (SQLException e) {
				System.out.println("Erro ao carregar lista de patrimonio.");
				e.printStackTrace();
			}
		
			req.setAttribute("listaCon", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaConsumo.jsp");
			dispatcher.forward(req, resp);
			

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String status = req.getParameter("status");
		String observacao = req.getParameter("observacao");
		String localizacao = req.getParameter("localizacao");

		Consumo con = new Consumo();

		con.setId(0);
		
		if (status.equals("Ativo")) {
			con.setStatus(1);
		} else
			con.setStatus(0);
		con.setStatus(Integer.parseInt(status));
		con.setNome(nome);
		con.setObservacao(observacao);
		con.setLocalizacao(localizacao);

		ConsumoDAO conDAO = new ConsumoDAO();

		try {
			conDAO.inserir(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro na inserção de patrimônio.");
			e.printStackTrace();
		}
		resp.sendRedirect("consumo.do?acao=listar");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}

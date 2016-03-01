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
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;
import br.edu.unifei.ControlePatrimonio.util.CopiaArquivo;

@WebServlet("/consumo.do")
public class ConsumoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");

		if (acao.equals("cad")) {
			Consumo con=null;
			con= new Consumo();
			con.setNome("");
			con.setLocalizacao("");
			con.setObservacao("");
			con.setId(0);
			con.setStatus(0);
			req.setAttribute("consumoEdit", con);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);

		}
		else if(acao.equals("buscarefinada")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaConsumo.jsp");
			dispatcher.forward(req, resp);	
		}
		else if (acao.equals("listar")) {
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
		else if(acao.equals("alterar")){
			String id= req.getParameter("id");
			Consumo con=null;;
			ConsumoDAO conDAO = new ConsumoDAO();
			try {
				 con = conDAO.buscaId(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Id do produto alt: " + con.getId());
			
			req.setAttribute("consumoEdit", con);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);
		}
		else if(acao.equals("remover")){
			String id= req.getParameter("id");
			int removido=0;
			ConsumoDAO conDAO = new ConsumoDAO();
			try {
				if(conDAO.remove(Integer.parseInt(id)))
						System.out.println("Consumo removido com sucesso.");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if(removido==1)
			resp.getWriter().print("<script> window.alert('Item removido!');</script>");
			resp.sendRedirect("consumo.do?acao=listar");
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");
		
		if (acao.equals("cad")) {		
			String id = req.getParameter("id");
			String nome = req.getParameter("nome");
			String status = req.getParameter("status");
			String observacao = req.getParameter("observacao");
			String localizacao = req.getParameter("localizacao");
			
			Consumo con = new Consumo();

			con.setId(Integer.parseInt(id));
			
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
				conDAO.salvar(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na inserção de consu,o.");
				e.printStackTrace();
			}
			resp.sendRedirect("consumo.do?acao=listar");
		}
		
		else if(acao.equals("busca")){
			List<Consumo> lista;
			String nome = req.getParameter("nome");
			String status = req.getParameter("status");
			String localizacao = req.getParameter("localizacao");
			
			ConsumoDAO conDao = new ConsumoDAO();
			try {
				lista = conDao.listaBusca(nome, status, localizacao);
				File fileOrigem = new File("C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/");
				File fileDestino = new File("C:/Users/Estagio/workspace/ControlePatrimonio/src/main/webapp/dados/");
				File auxFile = new File("C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivoConsumo.csv");
				if (auxFile.exists()) {
					auxFile.delete();
				}
				conDao.exportarItens(nome, status, localizacao);
				CopiaArquivo aux = new CopiaArquivo();

				aux.copyFiles(fileOrigem, fileDestino);
				req.setAttribute("listaConRefinada", lista);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaConsumo.jsp");
			dispatcher.forward(req, resp);
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}

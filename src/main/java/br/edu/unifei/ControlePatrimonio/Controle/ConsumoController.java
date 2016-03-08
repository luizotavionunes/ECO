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
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO;
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
			String id= req.getParameter("serial");
			LogDAO logDao = new LogDAO();
			Log log = new Log();
		
			
			Consumo con=null;;
			ConsumoDAO conDAO = new ConsumoDAO();
			try {
				 con = conDAO.buscaId(id);
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpSession sessao = req.getSession();
			Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
			log.setAcesso(usu.getTipo());
			String historico = "Id: " + con.getId() + " Nome: " + con.getNome() + " Status: " + con.getStatus() + " Localização: " + con.getLocalizacao() + " Observacao: " + con.getObservacao() ;
			log.setPreHistorico(historico);
			String nome = (String) sessao.getAttribute("nomeUsu");
			log.setNome(nome);
			//log.setId(id_log);
		
			
			

			//System.out.println("Id do produto alt: " + con.getId());
			//req.setAttribute("logEdit", log);
			sessao.setAttribute("logEdit", log);
			req.setAttribute("consumoEdit", con);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);
		}
		else if(acao.equals("remover")){
			String id= req.getParameter("id");
			LogDAO logDao = new LogDAO();
			Log log = new Log();
			Consumo con = new Consumo();
			
			ConsumoDAO conDAO = new ConsumoDAO();
			try {
				con = conDAO.buscaId(id);
				if(conDAO.remove(Integer.parseInt(id)))
						System.out.println("Consumo removido com sucesso.");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession sessao = req.getSession();
			Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
			log.setAcesso(usu.getTipo());
			String historico = "Id: " + con.getId() + " Nome: " + con.getNome() + " Status: " + con.getStatus() + " Localização: " + con.getLocalizacao() + " Observacao: " + con.getObservacao() ;
			log.setPreHistorico(historico);
			String nome = (String) sessao.getAttribute("nomeUsu");
			log.setNome(nome);
			log.setPosHistorico("Objeto excluído.");
			
			try {
				if(logDao.inserir(log)){
					System.out.println("Log Registrado com sucesso.");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

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
			HttpSession sessao = req.getSession();
/*
			if (!status.equals("1") || !status.equals("2")) {
				resp.getWriter()
						.print("<script> window.alert('Selecione o Status!'); location.href='consumo.do?acao=alterar&serial="
								+ id + "'; </script>");

			}
			*/
			
			con.setId(Integer.parseInt(id));
			
			if (status.equals("Ativo")) {
				con.setStatus(1);
			} else
				con.setStatus(0);
			con.setStatus(Integer.parseInt(status));
			con.setNome(nome);
			con.setObservacao(observacao);
			con.setLocalizacao(localizacao);
			String historicoPos = "Nome: " + con.getNome() + " Status: " + con.getStatus() + " Localização: " + con.getLocalizacao() + " Observacao: " + con.getObservacao() ;
			
			//System.out.println("");
			
			//System.out.println(historicoPos);
			ConsumoDAO conDAO = new ConsumoDAO();
			Log log = new Log();
			if(con.getId()!=0)
			log = (Log) sessao.getAttribute("logEdit");
			else{
				Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
				String nomeU = (String) sessao.getAttribute("nomeUsu");
				log.setNome(nomeU);
				log.setAcesso(usu.getTipo());	
				log.setPreHistorico("Não existem informações prévias sobre este objeto. Provavelmente é um novo objeto.");			
			}
			log.setPosHistorico(historicoPos);
			//System.out.println("id "+ log.getId());
			LogDAO logDao = new LogDAO();

			try {
				conDAO.salvar(con);
		
				if(logDao.inserir(log))
					System.out.println("Log registrado com sucesso!");
				else System.out.println("Erro ao registrar log!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na inserção de consumo.");
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

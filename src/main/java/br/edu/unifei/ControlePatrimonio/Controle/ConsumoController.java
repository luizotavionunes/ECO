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



/**
 * Servlet responsável por todas operações ligadas a bens de consumo.
 * opções disponiveis: Cadastro, Edição, Remoção, Listagem e Busca
 * @author Estagio
 *
 */
@WebServlet("/consumo.do")
public class ConsumoController extends HttpServlet {

	/*
	 * Método responsável por redirecionar as requisições dos usuarios ações
	 * disponiveis: Cadastro, Edição, Remoção, Listagem e Busca (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		// Acao que redireciona para o método POST para realizar o cadastro de
		// um item de consumo.
		if (acao.equals("cad")) {
			if (usuAUT.getTipo() == 3) {
				Consumo con = null;
				con = new Consumo();
				con.setNome("");
				con.setLocalizacao("");
				con.setObservacao("");
				con.setId(0);
				con.setStatus(0);
				req.setAttribute("consumoEdit", con);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}

			// Ação que redireciona para o método POST para filtragem dos dados
		} else if (acao.equals("buscarefinada")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaConsumo.jsp");
			dispatcher.forward(req, resp);
		// Ação responsável por apresentar todos os itens de consumo
		} else if (acao.equals("listar")) {
			ConsumoDAO conDAO = new ConsumoDAO();
			List<Consumo> lista = null;

			try {
				lista = conDAO.listarTodos();
			} catch (SQLException e) {
				System.out.println("Erro ao carregar lista de patrimonio.");
				e.printStackTrace();
			}

			req.setAttribute("listaCon", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaConsumo.jsp");
			dispatcher.forward(req, resp);
		// Acao que redireciona para o método POST para realizar a edição de
		// um item de consumo de acordo com seu id.
		} else if (acao.equals("alterar")) {
			String id = req.getParameter("serial");
			LogDAO logDao = new LogDAO();
			Log log = new Log();

			Consumo con = null;

			ConsumoDAO conDAO = new ConsumoDAO();
			try {
				con = conDAO.buscaId(id);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
			log.setAcesso(usu.getTipo());
			String historico = "Id: " + con.getId() + " Nome: " + con.getNome() + " Status: " + con.getStatus()
					+ " Localização: " + con.getLocalizacao() + " Observacao: " + con.getObservacao();
			log.setPreHistorico(historico);
			String nome = (String) sessao.getAttribute("nomeUsu");
			log.setNome(nome);

			sessao.setAttribute("logEdit", log);
			req.setAttribute("consumoEdit", con);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraConsumo.jsp");
			dispatcher.forward(req, resp);
			// Ação responsável por remover item de consumo de acordo com o
			// seu id
		} else if (acao.equals("remover")) {
			if (usuAUT.getTipo() == 3) {
				String id = req.getParameter("id");
				LogDAO logDao = new LogDAO();
				Log log = new Log();
				Consumo con = new Consumo();

				ConsumoDAO conDAO = new ConsumoDAO();
				try {
					con = conDAO.buscaId(id);
					if (conDAO.remove(Integer.parseInt(id)))
						System.out.println("Consumo removido com sucesso.");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
				log.setAcesso(usu.getTipo());
				String historico = "Id: " + con.getId() + " Nome: " + con.getNome() + " Status: " + con.getStatus()
						+ " Localização: " + con.getLocalizacao() + " Observacao: " + con.getObservacao();
				log.setPreHistorico(historico);
				String nome = (String) sessao.getAttribute("nomeUsu");
				log.setNome(nome);
				log.setPosHistorico("Objeto excluído.");

				try {
					if (logDao.inserir(log)) {
						System.out.println("Log Registrado com sucesso.");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				resp.getWriter().print("<script> window.alert('Item removido!');</script>");
				resp.sendRedirect("consumo.do?acao=listar");
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		}
	}

	/*
	 * 
	 * Método responsável por realizar postagens no servidor, seja pra buscar ou inserir dados(non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");
		// Açao que realiza o cadastro e a edição de um item de consumo. Em caso
		// de cadastro de um novo item id=0, caso contrario será uma edição
		if (acao.equals("cad")) {

			String id = req.getParameter("id");
			String nome = req.getParameter("nome");
			String status = req.getParameter("status");
			String observacao = req.getParameter("observacao");
			String localizacao = req.getParameter("localizacao");
			if (usuAUT.getTipo() == 3
					|| (((usuAUT.getTipo() == 2 || usuAUT.getTipo() == 1)) && Integer.parseInt(id) != 0)) {
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
				String historicoPos = "Nome: " + con.getNome() + " Status: " + con.getStatus() + " Localização: "
						+ con.getLocalizacao() + " Observacao: " + con.getObservacao();

				ConsumoDAO conDAO = new ConsumoDAO();
				Log log = new Log();
				if (con.getId() != 0)
					log = (Log) sessao.getAttribute("logEdit");
				else {
					Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
					String nomeU = (String) sessao.getAttribute("nomeUsu");
					log.setNome(nomeU);
					log.setAcesso(usu.getTipo());
					log.setPreHistorico(
							"Não existem informações prévias sobre este objeto. Provavelmente é um novo objeto.");
				}
				log.setPosHistorico(historicoPos);
				LogDAO logDao = new LogDAO();

				try {
					conDAO.salvar(con);

					if (logDao.inserir(log))
						System.out.println("Log registrado com sucesso!");
					else
						System.out.println("Erro ao registrar log!");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erro na inserção de consumo.");
					e.printStackTrace();
				}
				resp.sendRedirect("consumo.do?acao=listar");
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		}

		// Açao que realiza a busca de um item de consumo de acordo com seu
		// nome, status ou localizacao
		else if (acao.equals("busca")) {
			List<Consumo> lista;
			String nome = req.getParameter("nome");
			String status = req.getParameter("status");
			String localizacao = req.getParameter("localizacao");

			ConsumoDAO conDao = new ConsumoDAO();
			try {
				lista = conDao.listaBusca(nome, status, localizacao);
				conDao.exportarItens(nome, status, localizacao);
				
				String cmd2[] = {"bash","-c","echo scpECO2016 | sudo -S mv -f /tmp/arquivoConsumo.csv /opt/tomcat8/webapps/ControlePatrimonio/dados/arquivoConsumo.csv"};
				Runtime.getRuntime().exec(cmd2); 
				
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

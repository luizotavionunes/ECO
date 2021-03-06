package br.edu.unifei.ControlePatrimonio.Controle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.SingleSelectionModel;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;


/**
 * Servlet responsável por todas operações referentes ao controle de patrimonios
 * funções diponiveis: Inserção, edição, listagem, busca e remoção
 * @author Estagio
 *
 */
@WebServlet("/patrimonio.do")
public class PatrimonioController extends HttpServlet {

	/*
	 * 
	 * * Método responsável por redirecionar as requisições dos usuarios ações
	 * disponiveis: Cadastro, Edição, Remoção, Listagem e Busca (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtendo ação desejada da URL acessada
		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		//Acao que redireciona para o método POST para realizar o cadastro de
		// um item de patrimonio.
		if (acao.equals("cad")) {
			if (usuAUT.getTipo() == 3) {
				Patrimonio pat = new Patrimonio();
				pat.setId(0);
				pat.setDescricao_fabricante_modelo("");
				pat.setLocacao("");
				pat.setLocalizacao("");
				pat.setNumero_serie("");
				pat.setObservacao("");
				pat.setStatus(0);

				req.setAttribute("patrimonioEdit", pat);

				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraPatrimonio.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		// Ação responsável por remover um item de patrimonio de acordo com o
		// seu serial
		} else if (acao.equals("remover")) {
			if (usuAUT.getTipo() == 3) {
				String id = req.getParameter("id");
				int removido = 0;
				PatrimonioDAO patDAO = new PatrimonioDAO();
				Patrimonio pat = new Patrimonio();
				LogDAO logDao = new LogDAO();
				Log log = new Log();
				try {
					pat = patDAO.buscaId(Integer.parseInt(id));
					if (patDAO.remove(Integer.parseInt(id)))
						System.out.println("Patrimonio removido com sucesso.");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
				log.setAcesso(usu.getTipo());
				String historico = "Id: " + pat.getId() + " Numero de serie: " + pat.getNumero_serie() + " Descrição: "
						+ pat.getDescricao_fabricante_modelo() + " Status: " + pat.getStatus() + " Localização: "
						+ pat.getLocalizacao() + " Observacao: " + pat.getObservacao();
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

				// if(removido==1)
				// resp.getWriter().print("<script> window.alert('Item
				// removido!');</script>");
				resp.sendRedirect("patrimonio.do?acao=listar");
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		// Ação responsável por apresentar todos os itens de patrimonio
		} else if (acao.equals("listar")) {
			PatrimonioDAO patDAO = new PatrimonioDAO();
			List<Patrimonio> lista = null;

			try {
				lista = patDAO.listarTodos();
			} catch (SQLException e) {
				System.out.println("Erro ao carregar lista de patrimonio.");
				e.printStackTrace();
			}

			req.setAttribute("listaPat", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaPatrimonio.jsp");
			dispatcher.forward(req, resp);

		// Ação que redireciona para o método POST para filtragem dos dados
		} else if (acao.equals("buscarefinada")) {

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp");
			dispatcher.forward(req, resp);
		// Acao que redireciona para o método POST para realizar a edição de
		// um item de patrimonio de acordo com seu serial.
		} else if (acao.equals("alterar")) {
			String serial = req.getParameter("serial");
			LogDAO logDao = new LogDAO();
			Log log = new Log();

			Patrimonio pat = null;

			PatrimonioDAO ptDAO = new PatrimonioDAO();
			try {
				pat = ptDAO.buscaSerial(serial);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("Id do produto alt: " + pat.getId());

			Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
			log.setAcesso(usu.getTipo());
			String historico = "Id: " + pat.getId() + " Numero de serie: " + pat.getNumero_serie() + " Descrição: "
					+ pat.getDescricao_fabricante_modelo() + " Status: " + pat.getStatus() + " Localização: "
					+ pat.getLocalizacao() + " Observacao: " + pat.getObservacao();
			log.setPreHistorico(historico);
			String nome = (String) sessao.getAttribute("nomeUsu");
			log.setNome(nome);

			sessao.setAttribute("logEditP", log);

			req.setAttribute("patrimonioEdit", pat);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraPatrimonio.jsp");
			dispatcher.forward(req, resp);

		}

	}

	/*
	 * 
	 * Método responsável por realizar postagens no servidor, seja pra buscar ou inserir dados(non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");
		// Açao que realiza o cadastro e a edição de um item de patrimonio. Em caso
		// de cadastro de um novo item id=0, caso contrario será uma edição
		if (acao.equals("cad")) {

			String id = req.getParameter("id");
			String descricao_fabricante_modelo = req.getParameter("descricao");
			String status = req.getParameter("status");
			String observacao = req.getParameter("observacao");
			String numero_serie = req.getParameter("numero_serie");
			String locacao = req.getParameter("locacao");
			String localizacao = req.getParameter("localizacao");
			if (usuAUT.getTipo() == 3
					|| (((usuAUT.getTipo() == 2 || usuAUT.getTipo() == 1)) && Integer.parseInt(id) != 0)) {

				Patrimonio pat = new Patrimonio();

				// patAux=(Patrimonio)req.getAttribute("patrimonioEdit");
				/*
				 * if (!status.equals("1") || !status.equals("2")) {
				 * resp.getWriter() .print(
				 * "<script> window.alert('Selecione o Status!'); location.href='patrimonio.do?acao=alterar&serial="
				 * + numero_serie + "'; </script>");
				 * 
				 * }
				 */

				// System.out.println("Id do produto: " + patAux.getId());
				if (id != null)
					pat.setId(Integer.parseInt(id));

				pat.setDescricao_fabricante_modelo(descricao_fabricante_modelo);
				pat.setStatus(Integer.parseInt(status));
				pat.setLocacao(locacao);
				pat.setLocalizacao(localizacao);
				pat.setNumero_serie(numero_serie);
				pat.setObservacao(observacao);
				String historicoPos = "Id: " + pat.getId() + " Numero de serie: " + pat.getNumero_serie()
						+ " Descrição: " + pat.getDescricao_fabricante_modelo() + " Status: " + pat.getStatus()
						+ " Localização: " + pat.getLocalizacao() + " Observacao: " + pat.getObservacao();
				Log log = new Log();
				if (pat.getId() != 0)
					log = (Log) sessao.getAttribute("logEditP");
				else {
					Usuario usu = (Usuario) sessao.getAttribute("usuAUT");
					String nomeU = (String) sessao.getAttribute("nomeUsu");
					log.setNome(nomeU);
					log.setAcesso(usu.getTipo());
					log.setPreHistorico(
							"Não existem informações prévias sobre este objeto. Provavelmente é um novo objeto.");
				}
				log.setPosHistorico(historicoPos);

				PatrimonioDAO patDAO = new PatrimonioDAO();

				LogDAO logDao = new LogDAO();

				try {
					patDAO.salvar(pat);
					if (logDao.inserir(log))
						System.out.println("Log registrado com sucesso!");
					else
						System.out.println("Erro ao registrar log!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erro na inserção de patrimônio.");
					e.printStackTrace();
				}
				resp.sendRedirect("patrimonio.do?acao=listar");
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		// Açao que realiza a busca de um item de patrimonio de acordo com seu
		// nome, status ou localizacao
		} else if (acao.equals("buscarefinada")) {
			List<Patrimonio> lista;
			String descricao_fabricante_modelo = req.getParameter("descricao");
			String status = req.getParameter("status");
			String numero_serie = req.getParameter("numero_serie");
			String localizacao = req.getParameter("localizacao");

			
			
			PatrimonioDAO patDao = new PatrimonioDAO();
			try {
				lista = patDao.listaBusca(descricao_fabricante_modelo, status, numero_serie, localizacao);
				
				patDao.exportarArquivos(descricao_fabricante_modelo, status, numero_serie, localizacao);
			
				
				String cmd2[] = {"bash","-c","echo scpECO2016 | sudo -S mv -f /tmp/arquivoPatrimonio.csv /opt/tomcat8/webapps/ControlePatrimonio/dados/arquivoPatrimonio.csv"};
				Runtime.getRuntime().exec(cmd2); 

				req.setAttribute("listaPatRefinada", lista);
			} catch (SQLException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp");
			dispatcher.forward(req, resp);

		}
	}

}

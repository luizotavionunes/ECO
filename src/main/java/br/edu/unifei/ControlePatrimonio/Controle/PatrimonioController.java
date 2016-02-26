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
import javax.swing.SingleSelectionModel;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;
import br.edu.unifei.ControlePatrimonio.util.CopiaArquivo;

@WebServlet("/patrimonio.do")
public class PatrimonioController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtendo ação desejada da URL acessada
		String acao = req.getParameter("acao");

		if (acao.equals("cad")) {
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

		} else if (acao.equals("remover")) {
			String id = req.getParameter("id");
			int removido = 0;
			PatrimonioDAO patDAO = new PatrimonioDAO();
			try {
				if (patDAO.remove(Integer.parseInt(id)))
					System.out.println("Patrimonio removido com sucesso.");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// if(removido==1)
			// resp.getWriter().print("<script> window.alert('Item
			// removido!');</script>");
			resp.sendRedirect("patrimonio.do?acao=listar");

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

		} else if (acao.equals("buscarefinada")) {

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp");
			dispatcher.forward(req, resp);

		} else if (acao.equals("alterar")) {
			String serial = req.getParameter("serial");
			Patrimonio pat = null;
			;
			PatrimonioDAO ptDAO = new PatrimonioDAO();
			try {
				pat = ptDAO.buscaSerial(serial);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("Id do produto alt: " + pat.getId());

			req.setAttribute("patrimonioEdit", pat);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraPatrimonio.jsp");
			dispatcher.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		if (acao.equals("cad")) {

			String id = req.getParameter("id");
			String descricao_fabricante_modelo = req.getParameter("descricao");
			String status = req.getParameter("status");
			String observacao = req.getParameter("observacao");
			String numero_serie = req.getParameter("numero_serie");
			String locacao = req.getParameter("locacao");
			String localizacao = req.getParameter("localizacao");

			Patrimonio pat = new Patrimonio();

			// patAux=(Patrimonio)req.getAttribute("patrimonioEdit");

			if (!status.equals("1") || !status.equals("2")) {
				resp.getWriter()
						.print("<script> window.alert('Selecione o Status!'); location.href='patrimonio.do?acao=alterar&serial="
								+ numero_serie + "'; </script>");

			}

			// System.out.println("Id do produto: " + patAux.getId());
			if (id != null)
				pat.setId(Integer.parseInt(id));

			pat.setDescricao_fabricante_modelo(descricao_fabricante_modelo);
			pat.setStatus(Integer.parseInt(status));
			pat.setLocacao(locacao);
			pat.setLocalizacao(localizacao);
			pat.setNumero_serie(numero_serie);
			pat.setObservacao(observacao);

			PatrimonioDAO patDAO = new PatrimonioDAO();

			try {
				patDAO.salvar(pat);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na inserção de patrimônio.");
				e.printStackTrace();
			}
			resp.sendRedirect("patrimonio.do?acao=listar");

		} else if (acao.equals("buscarefinada")) {
			List<Patrimonio> lista;
			String descricao_fabricante_modelo = req.getParameter("descricao");
			String status = req.getParameter("status");
			String numero_serie = req.getParameter("numero_serie");
			String localizacao = req.getParameter("localizacao");

			PatrimonioDAO patDao = new PatrimonioDAO();
			try {
				lista = patDao.listaBusca(descricao_fabricante_modelo, status, numero_serie, localizacao);

				for (int i = 0; i < lista.size(); i++) {
					System.out.println("Numero de serie: " + lista.get(i).getNumero_serie() + " Descricao: "
							+ lista.get(i).getDescricao_fabricante_modelo());

				}
				req.setAttribute("listaPatRefinada", lista);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp");
			dispatcher.forward(req, resp);

		}

		else if (acao.equals("exportarArquivos")) {
			String descricao_fabricante_modelo = req.getParameter("descricao");
			String status = req.getParameter("status");
			String numero_serie = req.getParameter("numero_serie");
			String localizacao = req.getParameter("localizacao");
			PatrimonioDAO patDao = new PatrimonioDAO();
			try {
				File fileOrigem = new File("C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/");
				File fileDestino = new File("C:/Users/Estagio/workspace/ControlePatrimonio/src/main/webapp/dados/");
				File auxFile = new File(fileOrigem + "arquivo.csv");
				if (auxFile.exists()) {
					auxFile.delete();

				}
				patDao.exportarArquivos(descricao_fabricante_modelo, status, numero_serie, localizacao);
				CopiaArquivo aux = new CopiaArquivo();

				aux.copyFiles(fileOrigem, fileDestino);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}

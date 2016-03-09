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
import javax.servlet.http.HttpSession;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio;
import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.UsuarioDAO;

@WebServlet("/usuario.do")
public class UsuarioController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		if (acao.equals("cad")) {
			if (usuAUT.getTipo() == 3) {
				Usuario usu = new Usuario();
				usu.setId(0);
				usu.setSenha("");
				usu.setTipo(0);

				req.setAttribute("usuEdit", usu);

				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraUsuario.jsp");
				dispatcher.forward(req, resp);

			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}
		} else if (acao.equals("remover")) {
			if (usuAUT.getTipo() == 3) {
				String id = req.getParameter("id");
				UsuarioDAO usuDAO = new UsuarioDAO();

				try {
					if (usuDAO.remove(Integer.parseInt(id)))
						System.out.println("Usuario removido com sucesso.");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp.sendRedirect("usuario.do?acao=listar");
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}

		} else if (acao.equals("listar")) {
			if (usuAUT.getTipo() == 3) {
				UsuarioDAO usuDAO = new UsuarioDAO();

				List<Usuario> lista = null;

				try {
					lista = usuDAO.listarTodos();
				} catch (SQLException e) {
					System.out.println("Erro ao carregar lista de usuarios.");
					e.printStackTrace();
				}

				/*
				 * for (int i = 0; i < lista.size(); i++) { System.out.println(
				 * "Numero de serie: " + lista.get(i).getNumero_serie() +
				 * " Descricao: " +
				 * lista.get(i).getDescricao_fabricante_modelo());
				 * 
				 * }
				 */

				req.setAttribute("listaUsu", lista);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaUsuario.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}

		} else if (acao.equals("alterar")) {
			if (usuAUT.getTipo() == 3) {
				String id = req.getParameter("id");
				Usuario us = null;
				UsuarioDAO usuDAO = new UsuarioDAO();

				try {
					us = usuDAO.buscaId(Integer.parseInt(id));

					// System.out.println("Id do produto senha: " +
					// us.getSenha());
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*
				 * try { usu = usuDAO.buscaId(Integer.parseInt(id)); } catch
				 * (SQLException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 */

				// System.out.println("Id do produto alt: " + usu.getId());

				req.setAttribute("usuEdit", us);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/registraUsuario.jsp");
				dispatcher.forward(req, resp);

			} else {
				resp.getWriter().print(
						"<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String tipo = req.getParameter("tipo");
		String senha = req.getParameter("senha");

		Usuario usuAUT = new Usuario();
		HttpSession sessao = req.getSession();
		if (sessao != null)
			usuAUT = (Usuario) sessao.getAttribute("usuAUT");

		// Usuario usu = (Usuario) req.getAttribute("usuEdit");
		Usuario usu = new Usuario();

		// System.out.println("Id do produto: " + patAux.getId());
		if (id != null)
			usu.setId(Integer.parseInt(id));

		usu.setSenha(senha);
		usu.setTipo(Integer.parseInt(tipo));

		UsuarioDAO usuDAO = new UsuarioDAO();

		try {
			usuDAO.salvar(usu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro na inserção do usuario.");
			e.printStackTrace();
		}
		resp.sendRedirect("usuario.do?acao=listar");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}

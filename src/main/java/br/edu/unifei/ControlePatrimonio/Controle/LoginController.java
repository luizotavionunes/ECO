package br.edu.unifei.ControlePatrimonio.Controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario;
import br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.UsuarioDAO;

/**
 * Servlet responsável pelo gerenciamento de login do sistema
 * @author Estagio
 *
 */
@WebServlet("/logincontroller.do")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Acessando sessão, parametro false, diz que nao sera criada uma sessão
		// caso ela nao exista
		HttpSession sessao = req.getSession(false);
		if (sessao != null) {
			sessao.invalidate();
		}
		resp.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer tipo = Integer.parseInt(req.getParameter("tipo"));
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");

		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();

		usu.setTipo(tipo);
	
		usu.setSenha(senha);

		// Se o nome estiver for diferente de vazio, e o acesso 1, 2 ou 3 e realizada uma tentativa de autenticaçao
		if (!nome.equals("") && (tipo == 1 || tipo == 2 || tipo == 3)) {

			Usuario usuAUT = null;
			try {
				usuAUT = usuDAO.autenticar(usu);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			// Se uma sessão existir, armazena os atributos do usuario nas estruturas "nomeUsu","usuAUT"
			if (usuAUT != null) {
				HttpSession sessao = req.getSession();
				sessao.setAttribute("usuAUT", usuAUT);
				sessao.setAttribute("nomeUsu", nome);
				sessao.setMaxInactiveInterval(60 * 10);
				req.getRequestDispatcher("WEB-INF/buscaPatrimonio.jsp").forward(req, resp);

			} else {
				resp.getWriter().print(
						"<script> window.alert('Usuario não encontrado!'); location.href='login.html'; </script>");

			}
		} else {
			resp.getWriter().print(
					"<script> window.alert('Voce precisa informar todos os campos!'); location.href='login.html'; </script>");
		}
	}

	@Override
	public void destroy() {
		
		super.destroy();
	}

}

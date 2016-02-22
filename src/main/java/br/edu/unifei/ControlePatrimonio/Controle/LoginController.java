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

		//System.out.println("Tipo: " + tipo + " Nome: " + nome + " Senha: " + senha);

		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();

		usu.setTipo(tipo);
		//System.out.println("tipo usuario " + usu.getTipo());
		usu.setSenha(senha);

		Usuario usuAUT=null;
		try {
			usuAUT = usuDAO.autenticar(usu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (usuAUT != null) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAUT", usuAUT);
			  sessao.setMaxInactiveInterval(60*5);
			if(usuAUT.getTipo()==3){
			  req.getRequestDispatcher("WEB-INF/homeADM.jsp").forward(req,
			  resp);
			} else {
				req.getRequestDispatcher("WEB-INF/home.jsp").forward(req,
						  resp);
				
			}
			/*
			 * if(usuAUT.getAcesso()==1){ sessao.setAttribute("funAUT", funAUT);
			 * sessao.setMaxInactiveInterval(60*5);
			 * req.getRequestDispatcher("WEB-INF/menufunc.jsp").forward(req,
			 * resp); } else if(funAUT.getAcesso()==2){
			 * sessao.setAttribute("funAUT", funAUT);
			 * sessao.setMaxInactiveInterval(60*5);
			 * req.getRequestDispatcher("WEB-INF/menuadm.jsp").forward(req,
			 * resp);
			 * 
			 * }
			 */

		} else {
			resp.getWriter()
					.print("<script> window.alert('Usuario não encontrado!'); location.href='login.html'; </script>");

		}
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
}

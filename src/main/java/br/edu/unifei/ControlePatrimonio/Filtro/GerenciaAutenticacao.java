package br.edu.unifei.ControlePatrimonio.Filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.DispatcherType;

/**
 * Servlet responsável por filtrar todas as requisoções do sistema.
 * Apenas usuarios logados com sessão ativa podem acessar as funcionalidades do sistema
 * @author Estagio
 *
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = "/*")
public class GerenciaAutenticacao implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		HttpSession sessao = httpRequest.getSession(false);

		// Esse if especifica o que pode passar pelo filtro sem estar logado no
		// sistema
		if (((sessao != null)) || uri.lastIndexOf("login.html") != -1 || uri.lastIndexOf("logincontroller.do") != -1) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect("login.html");
		}
	}
	
		@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

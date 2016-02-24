<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio"%>
		
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario"%>

<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="css/style.css"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lista de Patrimonio</title>
</head>
<body>

	<div id="footer">
		<h1>SISTEMA DE CONTROLE DE PATRIMÔNIO ECO</h1>
	</div>

	<div id="nav">
	<%Usuario usu = (Usuario) request.getSession().getAttribute("usuAUT");
	if(usu.getTipo()==3){
	
	%>
	<%@include file="menuADM.jsp"%>
	<% } else { %>
		<%@include file="menu.jsp"%>
		<% } %>
	</div>

	<div id="section">
				<h2>Lista de Patrimonio</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>TIPO DE ACESSO</th>
			<th>SENHA </th>
	
		</tr>
		<%
			List<Usuario> listaUsu = (List<Usuario>) request.getAttribute("listaUsu");
		%>

		<%
			for (Usuario f : listaUsu) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%= f.getId() %></td>
			<td><%= f.getTipo() %></td>
			<td><%= f.getSenha() %></td>

		</tr>
		<%
			}
		%>


	</table></p>
	</div>

	<div id="footer">Copyright © Unifei ECO</div>
		


</body>
</html>
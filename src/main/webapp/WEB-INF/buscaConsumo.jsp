<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo"%>
	<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario"%>

<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

<h1>Formulário para Busca de Iténs de Consumo</h1>

	<form action="consumo.do?acao=busca" method="post">
		<fieldset>

			<div class="formLab">Nome:</div>
			<div class="form">
				<input type="text" name="nome" />
			</div>
			<br />
			<br />

			<div class="formLab">Localização:</div>
			<div class="form">
				<input type="text" name="localizacao" />
			</div>
			<br />
			<br />

			<div class="formLab">Status:</div>
			<div class="form">
				<select name="status" size="1">
					<option value="0">Sistema Ativo ou Inativo?

						<option value="1">Ativo

						<option value="2">Inativo
				</select>
			</div>
			<br />
			<br />
			<br /> <div class="formLab"><input type="submit" maxlenght="100" value="Buscar"></div>
		</fieldset>

	</form>
<%
			List<Consumo> listaCon = (List<Consumo>) request.getAttribute("listaConRefinada");
		

if(listaCon!=null){ %>
				<h2>Lista de Iténs de Consumo</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>STATUS</th>
			<th>LOCALIZAÇÃO</th>
			<th>OBSERVAÇÃO</th>
		</tr>
		

		<%
			for (Consumo f : listaCon) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getNome() %></td>
			<td>
			<% if(f.getStatus()==1){
				out.print("Ativo");
			} else{
				out.print("Inativo");
			}
				%>
			</td>
			<td><%=f.getLocalizacao() %></td>
			<td><%=f.getObservacao() %></td>

		</tr>
		<%
			}
		%>


	</table></p>
	
	<%} %>
	</div>
</body>
</html>
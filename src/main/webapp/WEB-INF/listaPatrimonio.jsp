<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio"%>

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
		<%@include file="menu.jsp"%>
	</div>

	<div id="section">
				<h2>Lista de Patrimonio</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>NÚMERO DE SÉRIE</th>
			<th>DESCRIÇÃO / FABRICANTE / MODELO</th>
			<th>LOCALIZAÇÃO</th>
			<th>LOCAÇÃO</th>
			<th>STATUS</th>
			<th>OBSERVAÇÃO</th>
		</tr>
		<%
			List<Patrimonio> listaPat = (List<Patrimonio>) request.getAttribute("listaPat");
		%>

		<%
			for (Patrimonio f : listaPat) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getNumero_serie() %></td>
			<td><%=f.getDescricao_fabricante_modelo() %></td>
			<td><%=f.getLocalizacao() %></td>
			<td><%=f.getLocacao() %></td>
			<td>
			<% if(f.getStatus()==1){
				out.print("Ativo");
			} else{
				out.print("Inativo");
			}
				%>
			</td>
			<td><%=f.getObservacao() %></td>

		</tr>
		<%
			}
		%>


	</table></p>
	</div>

	<div id="footer">Copyright © Unifei ECO</div>
		


</body>
</html>
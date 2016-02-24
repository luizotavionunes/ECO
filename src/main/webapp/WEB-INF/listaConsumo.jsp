<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo"%>

<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@ include file="../css/style.css"%>
</style>
<title>Lista de Consumo</title>
</head>
<body>

	<div id="footer">
		<h1>SISTEMA DE CONTROLE DE PATRIMÔNIO ECO</h1>
	</div>

	<div id="nav">
		<%@include file="menu.jsp"%>
	</div>

	<div id="section">
				<h2>Lista de Consumo</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>STATUS</th>
			<th>OBSERVACAO</th>
			<th>LOCALIZACAO</th>
		</tr>
		<%
			List<Consumo> listaCon = (List<Consumo>) request.getAttribute("listaCon");
		%>

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
			<td><%=f.getStatus() %></td>
			<td><%=f.getObservacao() %></td>
			<td><%=f.getLocalizacao() %></td>
			<td>
			<% if(f.getStatus()==1){
				out.print("Ativo");
			} else{
				out.print("Inativo");
			}
				%>
			</td>

		</tr>
		<%
			}
		%>


	</table></p>
	</div>

	<div id="footer">Copyright © Unifei ECO</div>
		


</body>
</html>
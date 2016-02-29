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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@ include file="../css/style.css"%>
</style>
<title>Lista de Patrimonio</title>
<script type="text/javascript">
	function Exclusao(id) {
		if (window.confirm('Tem certeza que deseja excluir?')) {
			location.href = "patrimonio.do?acao=remover&id=" + id;
		}
	}
	/*function confirmaExclusao(id) {
		if (window.confirm('ATENÇAO!!Após a exlusão deste item, não será possível recupera-lo. Você tem certeza disso?')) {
			location.href="patrimonio.do?acao=remover&serial=" + id;
			
		}
	}*/
	
	
	
</script>

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
				<h2>Lista de Patrimônio</h2>
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
			
			  <% if(usu.getTipo()==3){ %>
			<td><a href="patrimonio.do?acao=alterar&serial=<%= f.getNumero_serie() %>" >Editar</a></td>
			<td><a href="javascript:Exclusao(<%= f.getId() %>)" >Excluir</a></td>
			<%} else { %>
			<td><a href="patrimonio.do?acao=alterar&serial=<%= f.getNumero_serie() %>" >Editar</a></td>
			<%} %> 

		</tr>
		<%
			}
		%>


	</table></p>
	</div>

	<div id="footer">Copyright © Unifei ECO</div>
		


</body>
</html>
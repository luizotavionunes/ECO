<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.LogDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Log"%>
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
<title>Busca - Log</title>

<script type="text/javascript">
	
	function busca() {
		document.formulario_busca.action = "logcontroller.do?acao=busca";
		document.forms.formulario_busca.submit();
	}

	
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

	<form id="formulario_log" name="formulario_busca" method="post">
		
			<div id = "title_form"> Busca - Log</div>
				<div class="row">
				<label class="col1">Serial:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input class="input" type="text" id="serial" tabindex="1" name="serial"/>
				</span>
			</div>

			<div align="center" id="botao">
				<a href="javascript:busca()">
				<img src="<%=request.getContextPath()%>/imagens/icone_buscar_lupa.png"></img></a>
			</div>
			
	</form>
<%
			List<Log> listaLog = (List<Log>) request.getAttribute("listaLog");
		

if(listaLog!=null){ %>
				<h2>Lista de Logs do sistema</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>DATA DA OPERAÇÃO</th>
			<th>VALOR ANTERIOR</th>
			<th>VALOR ATUAL</th>
			<th>ACESSO</th>
		</tr>
		

		<%
			for (Log f : listaLog) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getNome() %></td>
			<td>
			<%= f.getData() %>
			</td>
			<td><%=f.getPreHistorico() %></td>
			<td><%=f.getPosHistorico() %></td>
			<td><%=f.getAcesso() %></td>

		</tr>
		<%
			}
		%>


	</table></p>
	
	<%} %>
	</div>
	
	</div>
	
	<div id="footer">Copyright © Unifei ECO</div>
</body>
</html>
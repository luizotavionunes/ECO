<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio"%>
		
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@ include file="../css/style.css"%>
</style>
<title>Insere - Usuários</title>
<script>
function salva() {
	document.formulario_cadastro.action = "usuario.do";
	document.forms.formulario_cadastro.submit();
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

<% Usuario usuAux = (Usuario) request.getAttribute("usuEdit"); %>

<div id="section">
	<h2>Insere - Usuário</h2>

	<form name="formulario_cadastro" method="post">
		<fieldset>
		
		<div class="formLab">Id:</div>
			<div class="form">
				<input type="text" name="id" readonly="readonly" value="<%= usuAux.getId() %>" />
			</div>
			<br />
			<br />

			<div class="formLab">Tipo:</div>
			<div class="form">
				<input type="text" name="tipo" value="<%= usuAux.getTipo() %>" />
			</div>
			<br />
			<br />

			<div class="formLab">Senha:</div>
			<div class="form">
				<input type="text" name="senha" value="<%= usuAux.getSenha() %>"/>
			</div>
			<br />
			<br />
			<br /> 
			<div class="formLab">
				<a href="javascript:salva()"> <img
						src="<%=request.getContextPath()%>/imagens/save_icon.png"></img></a>
			</div>
		</fieldset>

	</form>
	</div>
	
	<div id="footer">Copyright © Unifei ECO</div>


</body>
</html>
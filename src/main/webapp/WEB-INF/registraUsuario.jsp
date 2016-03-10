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

	<form id="formulario_usuario" name="formulario_cadastro" method="post">
		<fieldset>
		
		
		<div id = "title_form"> Inserir - Usuário</div>
			<div class="row">
				<label class="col1">Id:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input tabindex="1" type="text" name="id" readonly="readonly" value="<%= usuAux.getId() %>" />
				</span>
			</div>
			<div class="row">
				<label class="col1">Acesso:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input tabindex="1" type="text" name="tipo" value="<%= usuAux.getTipo() %>" />
				</span>
			</div>
			<div class="row">
				<label class="col1">Senha:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input tabindex="1" type="password" name="senha" value="<%= usuAux.getSenha() %>"/>
				</span>
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
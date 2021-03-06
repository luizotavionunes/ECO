<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo"%>
	
	<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@ include file="../css/style.css"%>
</style>
<title>Insere - Consumo</title>
<script>
function salva() {
	document.formulario_cadastro.action = "consumo.do?acao=cad";
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
	
<% 	Consumo Con = (Consumo) request.getAttribute("consumoEdit"); %>


	<div id="section">


	<form id="formulario_consumo" name="formulario_cadastro" method="post">
	
	<fieldset>
	<div id = "title_form"> Inserir - Consumo</div>
			<div class="row">
				<label class="col1">Id:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input class="input" type="text" tabindex="1" name="id" readonly="readonly" value="<%= Con.getId() %>" />
				</span>
			</div>
			<div class="row">
				<label class="col1">Nome:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input class="input" type="text" tabindex="1" name="nome" value="<%= Con.getNome() %>"/>
				</span>
			</div>
			<div class="row">
				<label class="col1">Localização:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input class="input" type="text" id="localizacao" tabindex="1" name="localizacao" value="<%= Con.getLocalizacao() %>" />
				</span>
			</div>
			<div class="row">
				<label class="col1">Status:&nbsp;&nbsp;</label>
				<span class="col2">
					<select name="status" size="1" class="input" tabindex="4" id="status">
						<option value="0">Sistema Ativo ou Inativo?</option>
						<option value="1">Ativo</option>
						<option value="2">Inativo</option>
					</select>
				</span>
			</div>
			<div class="row">
				<label class="col1">Observação:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<textarea style="overflow: auto; resize: none" cols=35 rows=3 name="observacao"><%= Con.getObservacao() %></textarea>
				</span>
			</div>
			<br />
			<br /> 
			<div class="formLab">
				<a href="javascript:salva()">
					<img src="<%=request.getContextPath()%>/imagens/save_icon.png"></img></a>
			</div>
		</fieldset>

	</form>
</div>

	<div id="footer">Copyright © Unifei ECO</div>
</body>
</html>
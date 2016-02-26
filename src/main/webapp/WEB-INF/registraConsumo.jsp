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
<% 
	out.print("Nomep: " + Con.getNome());%>

	<div id="section">

	<h1>Formulário para Cadastro de Iténs de Consumo</h1>

	<form action="consumo.do?acao=cad" method="post">
		<fieldset>

			<div class="formLab">Id:</div>
			<div class="form">
				<input type="text" name="id" readonly="readonly" value="<%= Con.getId() %>" />
			</div>
			<br />
			<br />
			
			<% if (usu.getTipo() == 3) { %>
			<div class="formLab">Nome:</div>
			<div class="form">
				<input type="text" name="nome" value="<%= Con.getNome() %>" />
			</div>
			<br />
			<br />
			
			<%
			}
			else{
			%>
			
			<div class="formLab">Nome:</div>
			<div class="form">
				<input type="text" name="nome" readonly="readonly" value="<%= Con.getNome() %>" />
			</div>
			<br />
			<br />
			<%} %>
			
			<div class="formLab">Localização:</div>
			<div class="form">
				<input type="text" name="localizacao" value="<%= Con.getLocalizacao() %>" />
			</div>
			<br />
			<br />


			<div class="formLab">Status:</div>
			<div class="form">
				<select name="status" size="1">
					<option value="#">Sistema Ativo ou Inativo?

						<option value="1">Ativo

						<option value="2">Inativo
				</select>
			</div>
			<br />
			<br />

			<div class="formLab">Observação:</div>
			<div class="form">
				<textarea style="overflow: auto; resize: none" cols=35 rows=3 name="observacao"><%= Con.getObservacao() %>
			</textarea>
			</div>
			<br />
			<br /> <div class="formLab"><input type="submit" maxlenght="100" value="Salvar"></div>
		</fieldset>

	</form>
</div>

	<div id="footer">Copyright © Unifei ECO</div>
</body>
</html>
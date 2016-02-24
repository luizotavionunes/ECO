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

<% Patrimonio Pat = (Patrimonio) request.getAttribute("patrimonioEdit"); %>

<div id="section">
	<h1>Formulário para Cadastro de Patrimônio</h1>

	<form action="patrimonio.do?acao=cad" method="post">
		<fieldset>
		
		<div class="formLab">Id:</div>
			<div class="form">
				<input type="text" name="id" readonly="readonly" value="<%= Pat.getId() %>" />
			</div>
			<br />
			<br />

			<div class="formLab">Número de Série:</div>
			<div class="form">
				<input type="text" name="numero_serie" value="<%= Pat.getNumero_serie() %>" />
			</div>
			<br />
			<br />

			<div class="formLab">Descrição / Fabricante / Modelo:</div>
			<div class="form">
				<input type="text" name="descricao" value="<%= Pat.getDescricao_fabricante_modelo() %>"/>
			</div>
			<br />
			<br />

			<div class="formLab">Localização:</div>
			<div class="form">
				<input type="text" name="localizacao" value="<%= Pat.getLocalizacao() %>" />
			</div>
			<br />
			<br />

			<div class="formLab">Locação:</div>
			<div class="form">
				<input type="text" name="locacao" value="<%= Pat.getLocacao() %>" />
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
				<textarea style="overflow: auto; resize: none" name="observacao"
					cols=35 rows=3 value="<%= Pat.getObservacao() %>">

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
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio"%>

<%@page import="java.util.List"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
<%@ include file="../css/style.css"%>
</style>
	<title>Busca - Patrimônio</title> 
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
			function busca() {
				document.formulario_busca.action = "patrimonio.do?acao=buscarefinada";
				document.forms.formulario_busca.submit();
			}
			function download(){
				document.formulario_busca.action = "dados/arquivoPatrimonio.csv";
				document.forms.formulario_busca.submit();		
			}
		</script>
</head>

<body>

	<div id="footer">
		<h1>SISTEMA DE CONTROLE DE PATRIMÔNIO ECO</h1>
	</div>

	<div id="nav">
		<%
			Usuario usu = (Usuario) request.getSession().getAttribute("usuAUT");
			if (usu.getTipo() == 3) {
		%>
		<%@include file="menuADM.jsp"%>
		<%
			} else {
		%>
		<%@include file="menu.jsp"%>
		<%
			}
		%>
	</div>

	<div id="section">

		<form id = "formulario_busca" name="formulario_busca" method="post">
			<div id = "title_form"> Busca - Patrimônio</div>
			<div class="row">
				<label class="col1">Número de Série:&nbsp;&nbsp;</label> 
				<span class="col2"> 
					<input class="input" type="text" id="numero_serie" tabindex="1" name="numero_serie"/>
				</span>
			</div>
			<div class="row">
				<label class="col1">Descrição/Fabricante/Modelo: &nbsp;&nbsp;</label>
				<span class="col2">
					<input name="descricao" class="input" type="text" id="descricao" tabindex="2"/>
				</span>
			</div>
			<div class="row">
				<label class="col1">Localização:&nbsp;&nbsp;</label>
				<span class="col2">
					<input type="text" name="localizacao" id="localizacao" class="input" tabindex="3"/>
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
			<div align="center" id="botao">
				<a href="javascript:busca()">
				<img src="<%=request.getContextPath()%>/imagens/icone_buscar_lupa.png"></img></a>
				<a href="javascript:download()">
				<img src="<%=request.getContextPath()%>/imagens/export_icon.png"></img></a>
			</div>
		</form>

		<%
			List<Patrimonio> listaPat = (List<Patrimonio>) request.getAttribute("listaPatRefinada");

			if (listaPat != null) {
		%>
		<h2>Lista de Patrimonio</h2>
		<p>
			<table border="1">
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
					for (Patrimonio f : listaPat) {
				%>
				<tr>
					<td>
						<%
							out.print(f.getId());
						%>
					</td>
					<td><%=f.getNumero_serie()%></td>
					<td><%=f.getDescricao_fabricante_modelo()%></td>
					<td><%=f.getLocalizacao()%></td>
					<td><%=f.getLocacao()%></td>
					<td>
						<%
							if (f.getStatus() == 1) {
										out.print("Ativo");
									} else {
										out.print("Inativo");
									}
						%>
					</td>
					<td><%=f.getObservacao()%></td>

					<%
						if (usu.getTipo() == 3) {
					%>
					<td><a
						href="patrimonio.do?acao=alterar&serial=<%=f.getNumero_serie()%>">
							<img src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
					</a></td>
					<td><a href="javascript:Exclusao(<%=f.getId()%>)"> <img
							src="<%=request.getContextPath()%>/imagens/file_delete.png"></img>
					</a></td>
					<%
						} else {
					%>
					<td><a
						href="patrimonio.do?acao=alterar&serial=<%=f.getNumero_serie()%>">
							<img src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
					</a></td>
					<%} %>
				</tr>
				<%
					}
				%>


			</table>
		</p>

		<%} %>

	</div>

	<div id="footer">Copyright © Unifei ECO</div>

</body>

</html>
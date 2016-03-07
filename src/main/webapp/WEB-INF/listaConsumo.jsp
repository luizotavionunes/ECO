<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.ConsumoDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Consumo"%>
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
	<title>Lista - Consumo</title> <script type="text/javascript">
		function Exclusao(id) {
			if (window.confirm('Tem certeza que deseja excluir?')) {
				location.href = "consumo.do?acao=remover&id=" + id;
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
		<h2>Listar - Consumo</h2>
		<p>
			<table border="1">
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
					<td><%=f.getNome()%></td>
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
					<td><%=f.getLocalizacao()%></td>


					<%
						if (usu.getTipo() == 3) {
					%>
					<td><a
						href="consumo.do?acao=alterar&serial=<%=f.getId()%>"> <img
							src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
					</a></td>
					<td><a href="javascript:Exclusao(<%=f.getId()%>)"> <img
							src="<%=request.getContextPath()%>/imagens/file_delete.png"></img>
					</a></td>
					<%
						} else {
					%>
					<td><a
						href="consumo.do?acao=alterar&serial=<%=f.getId()%>"> <img
							src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
					</a></td>
					<%
						}
					%>

				</tr>
				<%
					}
				%>


			</table>
		</p>
	</div>

	<div id="footer">Copyright © Unifei ECO</div>



</body>
</html>
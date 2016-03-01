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
<title>Busca - Consumo</title>

<script type="text/javascript">
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
	
	function busca() {
		document.formulario_busca.action = "consumo.do?acao=busca";
		document.forms.formulario_busca.submit();
	}
	function download(){
		document.formulario_busca.action = "dados/arquivoConsumo.csv";
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

<h2>Consultar - Consumo</h2>

	<form name="formulario_busca" method="post">
		<fieldset>

			<div class="formLab">Nome:</div>
			<div class="form">
				<input type="text" name="nome" />
			</div>
			<br />
			<br />

			<div class="formLab">Localização:</div>
			<div class="form">
				<input type="text" name="localizacao" />
			</div>
			<br />
			<br />

			<div class="formLab">Status:</div>
			<div class="form">
				<select name="status" size="1">
					<option value="0">Sistema Ativo ou Inativo?

						<option value="1">Ativo

						<option value="2">Inativo
				</select>
			</div>
			<br />
			<br />
			<br />
				<div class="formLab">
					<a href="javascript:busca()">
					<img src="<%=request.getContextPath()%>/imagens/icone_buscar_lupa.png"></img></a>
					<a href="javascript:download()">
					<img src="<%=request.getContextPath()%>/imagens/export_icon.png"></img></a>
				</div>
			</fieldset>

	</form>
<%
			List<Consumo> listaCon = (List<Consumo>) request.getAttribute("listaConRefinada");
		

if(listaCon!=null){ %>
				<h2>Lista de Iténs de Consumo</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>STATUS</th>
			<th>LOCALIZAÇÃO</th>
			<th>OBSERVAÇÃO</th>
		</tr>
		

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
			<td>
			<% if(f.getStatus()==1){
				out.print("Ativo");
			} else{
				out.print("Inativo");
			}
				%>
			</td>
			<td><%=f.getLocalizacao() %></td>
			<td><%=f.getObservacao() %></td>
			
		    <% if(usu.getTipo()==3){ %>
			<td><a href="consumo.do?acao=alterar&serial=<%=f.getId()%>">
							<img src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
					</a></td>
					<td><a href="javascript:Exclusao(<%=f.getId()%>)"> <img
							src="<%=request.getContextPath()%>/imagens/file_delete.png"></img>
					</a></td>
			<%} else { %>
			<td><a href="consumo.do?acao=alterar&serial=<%=f.getId()%>">
				<img src="<%=request.getContextPath()%>/imagens/file_edit.png"></img>
			</a></td>
			<%} %> 

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
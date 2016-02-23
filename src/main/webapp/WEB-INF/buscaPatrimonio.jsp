<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Persistencia.PatrimonioDAO"%>
<%@page
	import="br.edu.unifei.ControlePatrimonio.Modelo.Entidades.Patrimonio"%>

<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Formulário para Cadastro de Patrimônio</h1>

	<form action="patrimonio.do?acao=busca" method="post">
		<fieldset>

			<div class="formLab">Número de Série:</div>
			<div class="form">
				<input type="text" name="numero_serie" />
			</div>
			<br />
			<br />

			<div class="formLab">Descrição / Fabricante / Modelo:</div>
			<div class="form">
				<input type="text" name="descricao" />
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
			<br /> <div class="formLab"><input type="submit" maxlenght="100" value="Buscar"></div>
		</fieldset>

	</form>
<%
			List<Patrimonio> listaPat = (List<Patrimonio>) request.getAttribute("listaPatRefinada");
		

if(listaPat!=null){ %>
				<h2>Lista de Patrimonio</h2>
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

		</tr>
		<%
			}
		%>


	</table></p>
	
	<%} %>
</body>
</html>
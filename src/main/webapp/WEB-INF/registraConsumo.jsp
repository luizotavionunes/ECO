<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Cadastro de Funcionários</title>
<%@ include file="css/style.css"%>


</head>
<body>


	<h1>Formulário para Cadastro de Iténs de Consumo</h1>

	<form action="consumo.do?acao=cad" method="post">
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
					cols=35 rows=3>

			</textarea>
			</div>
			<br />
			<br /> <div class="formLab"><input type="submit" maxlenght="100" value="Salvar"></div>
		</fieldset>

	</form>


</body>
</html>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
        startList = function() {
        if (document.all&&document.getElementById) {
        navRoot = document.getElementById("menuDropDown");
        for (i=0; i<navRoot.childNodes.length; i++) {
        node = navRoot.childNodes[i];
        if (node.nodeName=="LI") {
        node.onmouseover=function() {
        this.className+=" over";
          }
          node.onmouseout=function() {
          this.className=this.className.replace
            (" over", "");
           }
           }
          }
         }
        }
        window.onload=startList;
    </script>

<title>Insert title here</title>
<style type="text/css">           <%@ include  file="css/estilo.css"%>         </style>
</head>
<body>



<ul id="nav">
    <li><a href="#">Consultar Patrimônio</a>
      <ul>
        <li><a href="consumo.do?acao=buscarefinada">Consumo</a></li>
        <li><a href="patrimonio.do?acao=buscarefinada">Patrimônio</a></li>
      </ul>
    </li>
    <li><a href="#">Atualizar Patrimônio</a>
      <ul>
        <li><a href="#">Consumo</a></li>
        <li><a href="#">Patrimônio</a></li>
      </ul>
    </li>
    <li><a href="logincontroller.do">Sair</a></li> 
    
  </ul>

</body>
</html>
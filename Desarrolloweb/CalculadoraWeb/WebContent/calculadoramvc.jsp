<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>con servlets</title>
</head>
<body>
	<form method="get">
		<select name="tipo">
			<option ${param.tipo == '2' ? 'selected' : ''}  value="2">Dos casillas</option>
			<option ${param.tipo == '1' ? 'selected' : ''} value="1">Una casilla</option>
		</select>
		<button>mandar</button>
	</form>

	<%
		if (!(request.getParameter("tipo") == null) && request.getParameter("tipo").equals("1")) {
	%>

	<form action="calculadoraCerebro" method="post">
		<input name="n1" type="number" required="required" value="%{param.n1}"> <select name="operacion">
			<option value="1">sin</option>
			<option value="2">cos</option>
			<option value="3">tan</option>
			<option value="4">arctan</option>
			<option value="5">arcsin</option>
			<option value="6">arccos</option>
		</select>
		<button>calcular</button>
	</form>
	<%
		}
	%>
	<%
		if (!(request.getParameter("tipo") == null) && request.getParameter("tipo").equals("2")) {
	%>

	<form action="calculadoraCerebro" method="post">
		<input type="number" required="required" name="n1" value="%{param.n1}">
		 <input type="number" required="required" name="n2" value="%{param.n1}"> <select
			name="operacion">
			<option value="1">+</option>
			<option value="2">-</option>
			<option value="3">*</option>
			<option value="4">/</option>
			<option value="5">^</option>
			<option value="6">%</option>
		</select>
		<button>calcular</button>
	</form>

	<%
		}
	%>
	${resultado}

</body>





</html>
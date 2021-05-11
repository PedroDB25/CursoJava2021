<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sin servelets</title>
</head>
<body>
	<form method="get">
		<select name="tipo">
			<option ${param.tipo == '2' ? 'selected' : ''} value="2">Dos
				casillas</option>
			<option ${param.tipo == '1' ? 'selected' : ''} value="1">Una
				casilla</option>
		</select>
		<button>mandar</button>
	</form>

	<%
		if (!(request.getParameter("tipo") == null) && request.getParameter("tipo").equals("1")) {
	%>

	<form method="get">
		<input name="n1" type="number" required="required" value="%{param.n1}">
		<select name="operacion">
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

	<form method="get">
		<input type="number" required="required" name="n1" value="%{param.n1}">
		<input type="number" required="required" name="n2" value="%{param.n1}">
		<select name="operacion">
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
	<%
		String resultado = "";
		int ope = 0;
		Double ope1 = 0.0;
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");

		if (!(n1 == null) || !(n2 == null)) {

			if (request.getParameter("operacion").equals("1")) {
				ope1 = Math.sin(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("2")) {
				ope1 = Math.cos(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("3")) {
				ope1 = Math.tan(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("4")) {
				ope1 = Math.atan(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("5")) {
				ope1 = Math.asin(Double.valueOf(n1));
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("6")) {
				ope1 = Math.acos(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
		}
		if (!(n1 == null) && !(n2 == null)) {

			if (request.getParameter("operacion").equals("1")) {
				ope = Integer.parseInt(n1) + Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("2")) {
				ope = Integer.parseInt(n1) - Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("3")) {
				ope = Integer.parseInt(n1) * Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("4")) {
				if (!(Integer.parseInt(n2) == 0)) {
					ope = Integer.parseInt(n1) / Integer.parseInt(n2);
					resultado = String.valueOf(ope);
				} else {
					resultado = "no dividas por 0";
				}
			}
			if (request.getParameter("operacion").equals("5")) {
				ope = (int) Math.pow(Integer.parseInt(n1), Integer.parseInt(n2));
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("6")) {
				ope1 = Integer.parseInt(n1) * Integer.parseInt(n2) * 0.01;
				resultado = String.valueOf(ope1);
			}
		}
	%>
	<p>
		<%=resultado%>
	</p>

</body>





</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/BasesHtml/HeaderLogin.jspf"%>

<form action="borrar" method="post">
	<div style="display: inline-grid;">
		<label>Visualizacion de datos a borrar: </label>
			<label>Id:</label>
		<input readonly="readonly" type="number" id="id" name="id" value="${usuario.id}" />
			<label>Nombre:</label>
		<input readonly="readonly" id="nombre" name="nombre" value="${usuario.nombre}" />
				<label>Email:</label>
		<input readonly="readonly" id="email" name="email" value="${usuario.email}" />
			<label>Password:</label>
		<input readonly="readonly" id="password" name="password" value="${usuario.password}" />
				<label>Rol:</label>
		<input readonly="readonly" id="rol" name="rol" value="${usuario.rol}" />

	</div>
	<div>
		<button>Eliminar dato</button>
	</div>
</form>
<form action="listadoUsuarios.jsp" >
		<button>Conservar dato</button>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
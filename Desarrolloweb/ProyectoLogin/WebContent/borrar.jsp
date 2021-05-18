<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/BasesHtml/HeaderLogin.jspf"%>

<form action="borrar" method="post">
	<div style="display: inline-grid;">
		<label>Visualizacion de datos a borrar: </label>
		<label>Id: </label>
		<input readonly="readonly" type="number" id="id" name="id" value="${usuario.id}" />
		<label>Nombre: </label>
		<input readonly="readonly" id="nombre" name="nombre" value="${usuario.usuario}" />
	</div>
	<div>
		<button>Eliminar dato</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
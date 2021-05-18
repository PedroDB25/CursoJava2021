<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="editar" method="post">
	<div>
		<label>Id</label>
		<input type="number" id="id" name="id" value="${usuario.id}" />
	</div>
	<div>
		<label>Usuario</label>
		<input id="nombre" name="nombre" value="${usuario.usuario}" />
	</div>
	<div>
		<button>Aceptar</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
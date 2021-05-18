<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="anadir" method="post">
	<div>
		<label>Id</label>
		<input readonly="readonly" type="number" id="id" name="id" placeholder="Id autogenerado" />
	</div>
	<div>
		<label>Usuario</label>
		<input id="nombre" name="nombre" value="" />
	</div>
	<div>
		<button>Agregar a la DB</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
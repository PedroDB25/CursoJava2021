<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="anadir" method="post" style="display: table-caption;">
	<div>
		<label>Id</label>
		<input readonly="readonly" type="number" id="id" name="id" placeholder="Id autogenerado" />
	</div>
	<div>
		<label>Usuario</label>
		<input id="nombre" name="nombre" value="" />
	</div>
	<div>
		<label>Email</label>
		<input id="email" name="email" value="" />
	</div>
	<div>
		<label>Password</label>
		<input id="password" name="password" value="" />
	</div>
	<div>
		<button>Agregar a la DB</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
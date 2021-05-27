<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="editar" method="post" style="display: table-caption;">
	<div>
		<label>Id</label> <input readonly="readonly" type="number" id="id" name="id"
			value="${usuario.id}" />
	</div>
	<div>
		<label>Usuario</label> <input id="nombre" name="nombre"
			value="${usuario.nombre}" />
	</div>
	<div>
		<label>password</label> <input  id="password" name="password"
			value="${usuario.password}" />
	</div>
	<div>
		<label>email</label> <input id="email" name="email"
			value="${usuario.email}" />
	</div>
	<div>
		<button>Modificar</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/BasesHtml/HeaderLogin.jspf"%>



<form action="login" method="post" style="display: inline-grid"">
	<label>Usuario</label> <input name="usuario" placeholder="Usuario">
	<button style="margin: 5px">entrar</button>
</form>
<c:if test="${error!=null}">
	<p>${error}</p>
	<p>Si no tiene usuario, usted puede registrarse aqui:</p>

	<form action="registro" method="post" style="display: inline-grid"">
		<label>Registro usuario</label> <input name="usuario" placeholder="Usuario">
		<button style="margin: 5px">Registro</button>
	</form>
</c:if>


<%@ include file="/BasesHtml/footer.jspf"%>
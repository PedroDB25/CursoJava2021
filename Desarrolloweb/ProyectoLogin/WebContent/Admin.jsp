<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/BasesHtml/Header.jspf"%>


<form action="admin" method="get">
	<button name="boton" value="ALista">Actualizar lista</button>
</form>
<table>
	<caption>Usuarios</caption>
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.id}</td>
				<td>${usuario.usuario}</td>
				<td>
				<a href="editar?id=${usuario.id}">Modificar</a> 
				<a href="borrar?id=${usuario.id}">Borrar</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<a href="anadir">Añadir</a>
			</td>
		</tr>
	</tfoot>
</table>

<%@ include file="/BasesHtml/footer.jspf"%>
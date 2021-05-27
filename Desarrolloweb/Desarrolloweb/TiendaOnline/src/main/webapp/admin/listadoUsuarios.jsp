<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>
<h1>Lista Usuarios</h1>
<table class="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Email</th>
			<th>Password</th>
			<th>Rol</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.id}</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.email}</td>
				<td>${usuario.password}</td>
				<td>${usuario.rol}</td>

				<td><a href="editar?id=${usuario.id}">Modificar</a> <a
					href="borrar?id=${usuario.id}">Borrar</a> <a
					href="cambiar?id=${usuario.id}">Cambiar Rol</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><a href="anadir">Añadir</a></td>
		</tr>
	</tfoot>
</table>
<h1>Lista productos</h1>
<table class="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>marca</th>
			<th>proveedor</th>
			<th>categoria</th>
			<th>Tipo de mascota</th>
			<th>Edad de mascota</th>
			<th>descuento</th>
			<th>existencia</th>
			<th>caducidad</th>
			<th>precio</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productos}" var="productos">
			<tr>
				<td>${productos.id}</td>
				<td>${productos.nombreProducto}</td>
				<td>${productos.marca}</td>
				<td>${productos.proveedor}</td>
				<td>${productos.categoria}</td>
				<td>${productos.tipoDeMascota}</td>
				<td>${productos.edadDeMascota}</td>
				<td>${productos.descuento}</td>
				<td>${productos.existencia}</td>
				<td>${productos.caducidad}</td>
				<td>${productos.precio}</td>

				<td><a href="editarProducto?id=${productos.id}">Modificar</a> <a
					href="borrarProducto?id=${productos.id}">Borrar</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><a href="anadirProducto">Añadir</a></td>
		</tr>
	</tfoot>
</table>



<%@ include file="/BasesHtml/footer.jspf"%>
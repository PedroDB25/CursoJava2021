<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="editarProducto" method="post"
	style="display: table-caption;">
	<div>
		<label>Id</label> <input readonly="readonly" type="number" id="id"
			name="id" value="${producto.id}" />
	</div>
	<div>
		<label>Nombre</label> <input id="nombre" name="nombre"
			value="${producto.nombreProducto}" />
	</div>
	<div>
		<label>Marca</label> <input id="marca" name="marca"
			value="${producto.marca}" />
	</div>
	<div>
		<label>Proveedor</label> <input id="proveedor" name="proveedor"
			value="${producto.proveedor}" />
	</div>
	<div>
		<label>Categoria</label> <input id="categoria" name="categoria"
			value="${producto.categoria}" />
	</div>
	<div>
		<label>Tipo de mascota</label> <input id="tipoDeMascota"
			name="tipoDeMascota" value="${producto.tipoDeMascota}" />
	</div>
	<div>
		<label>Edad de mascota</label> <input id="edadDeMascota"
			name="edadDeMascota" value="${producto.edadDeMascota}" />
	</div>
	<div>
		<label>Descuento</label> <input id="descuento" name="descuento"
			value="${producto.descuento}" />
	</div>
	<div>
		<label>Existencia</label> <input id="existencia" name="existencia"
			value="${producto.existencia}" />
	</div>
	<div>
		<label>Caducidad</label> <input id="caducidad" name="caducidad"
			value="${producto.caducidad}" />
	</div>
	<div>
		<label>Precio</label> <input id="precio" name="precio"
			value="${producto.precio}" />
	</div>
	<div>
		<button>Modificar</button>
	</div>
</form>


<%@ include file="/BasesHtml/footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="anadirProducto" method="post"
	style="display: table-caption;">
	<div>
		<label>Id</label> <input readonly="readonly" type="number" id="id"
			name="id" value="" placeholder="Id autogenerado"/>
	</div>
	<div>
		<label>Nombre</label> <input id="nombre" name="nombre"
			value="" />
	</div>
	<div>
		<label>Marca</label> <input id="marca" name="marca"
			value="" />
	</div>
	<div>
		<label>Proveedor</label> <input id="proveedor" name="proveedor"
			value="" />
	</div>
	<div>
		<label>Categoria</label> <input id="categoria" name="categoria"
			value="" />
	</div>
	<div>
		<label>Tipo de mascota</label> <input id="tipoDeMascota"
			name="tipoDeMascota" value="" />
	</div>
	<div>
		<label>Edad de mascota</label> <input id="edadDeMascota"
			name="edadDeMascota" value="" />
	</div>
	<div>
		<label>Descuento</label> <input id="descuento" name="descuento"
			value="" />
	</div>
	<div>
		<label>Existencia</label> <input id="existencia" name="existencia"
			value="" />
	</div>
	<div>
		<label>Caducidad</label> <input id="caducidad" name="caducidad"
			value="" />
	</div>
	<div>
		<label>Precio</label> <input id="precio" name="precio"
			value="" />
	</div>
	<div>
		<button>Modificar</button>
	</div>
</form>

<%@ include file="/BasesHtml/footer.jspf"%>
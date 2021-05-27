<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>

<form action="borrarProducto" method="post">
	<div style="display: inline-grid;">
		<label>Visualizacion de datos a borrar: </label>
			<label>Id:</label>
		<input readonly="readonly" type="number" id="id" name="id" value="${producto.id}" />
		
			<label>Nombre:</label>
		<input readonly="readonly" id="nombreProducto" name="nombreProducto" value="${producto.nombreProducto}" />
		
				<label>Marca:</label>
		<input readonly="readonly" id="marca" name="marca" value="${producto.marca}" />
		
			<label>Proveedor:</label>
		<input readonly="readonly" id="proveedor" name="proveedor" value="${producto.proveedor}" />
		
				<label>Categoria:</label>
		<input readonly="readonly" id="categoria" name="categoria" value="${producto.categoria}" />
		
					<label>Tipo De Mascota:</label>
		<input readonly="readonly" id="tipoDeMascota" name="tipoDeMascota" value="${producto.tipoDeMascota}" />
		
			<label>Edad De Mascota:</label>
		<input readonly="readonly" id="edadDeMascota" name="edadDeMascota" value="${producto.edadDeMascota}" />
		
				<label>Descuento:</label>
		<input readonly="readonly" id="descuento" name="descuento" value="${producto.descuento}" />
		
			<label>Existencia:</label>
		<input readonly="readonly" id="existencia" name="existencia" value="${producto.existencia}" />
		
				<label>Caducidad:</label>
		<input readonly="readonly" id="caducidad" name="caducidad" value="${producto.caducidad}" />
		
						<label>Precio:</label>
		<input readonly="readonly" id="precio" name="precio" value="${producto.precio}" />
		

	</div>
	<div>
		<button>Eliminar dato</button>
	</div>
</form>
<form action="listadoUsuarios.jsp" >
		<button>Conservar dato</button>
</form>


<%@ include file="/BasesHtml/footer.jspf"%>
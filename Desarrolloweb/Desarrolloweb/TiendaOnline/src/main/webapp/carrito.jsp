<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>
<table class="table" style="width: 90%; display: table;">
	<thead>
		<tr>
			<th scope="col">Nombre</th>
			<th scope="col">marca</th>
			<th scope="col">descuento</th>
			<th scope="col">precio</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${CarritoProductos}" var="productos">
			<tr>
				<td>${productos.nombreProducto}</td>
				<td>${productos.marca}</td>
				<td>${productos.descuento}</td>
				<td>${productos.precio}</td>
				<td>
				<form action="borrarcarrito" method="get" style="
    width: 150px;">
					<button id="producto" class="btn btn-secondary"
						value="${productos.id}" name="producto">Borrar del
						carrito</button>
				</form>
				</td>
			</tr>
		</c:forEach>
	<tfoot >
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td >Total: € ${totalAPagar}</td>
			<td style="
    width: 165px;
"><a href="factura">Confirmar compra</a></td>
		</tr>
	</tfoot>
	</tbody>
</table>





<%@ include file="/BasesHtml/footer.jspf"%>
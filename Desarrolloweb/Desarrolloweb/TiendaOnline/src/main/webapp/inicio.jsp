<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/BasesHtml/Header.jspf"%>
<div>
	<p>${Mensaje}</p>
	<p>Estas en el inicio ${usuario.nombre}</p>
	<p>Estimado ${usuario.nombre}, su sesion inicio a las,
		${horaInicio} por lo que durara hasta las ${horaFin}</p>
</div >
<div style="position: relative">
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
     Seleccion de categoria	
 </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
  	<li><a class="dropdown-item" href="inicio">Todos</a></li>
    <li><a class="dropdown-item" href="inicio?seleccion=animal">Animales</a></li>
    <li><a class="dropdown-item" href="inicio?seleccion=accesorio">Accesorios</a></li>
    <li><a class="dropdown-item" href="inicio?seleccion=comida">Comidas</a></li>
    <li><a class="dropdown-item" href="inicio?seleccion=juguetes">Juguetes</a></li>
  </ul>
</div>
</div>



<div style="
    display: flex;
    justify-content: center;
">
<table class="table" style="width: 90%; display: table;">
  	<thead>
		<tr>
			 <th scope="col">Nombre</th>
			 <th scope="col">marca</th>
			 <th scope="col">categoria</th>
			 <th scope="col">descuento</th>
			 <th scope="col">existencia</th>
			 <th scope="col">precio</th>
		</tr>
	</thead>
  
  <tbody>
    <c:forEach items="${productos}" var="productos">
			<tr>
				<td>${productos.nombreProducto}</td>
				<td>${productos.marca}</td>
				<td>${productos.categoria}</td>
				<td>${productos.descuento}</td>
				<td>${productos.existencia}</td>
				<td>${productos.precio}</td>
				<form action="anadircarrito" method="get">
				<td><input style="width: 30px" id="cantidad" name="cantidad"></td>
				<td><button id="producto" class="btn btn-secondary"
						value="${productos.id}" name="producto">Añadir
						al carro</button></td>
				</td>
				</form>
			</tr>
		</c:forEach>
  </tbody>
</table>
</div>
<%@ include file="/BasesHtml/footer.jspf"%>
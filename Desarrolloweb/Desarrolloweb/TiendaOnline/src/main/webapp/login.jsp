<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/BasesHtml/HeaderLogin.jspf"%>


<div class="text-center">
    <form action="login" method="post" class="form-signin">
      <img class="mb-4" src="Img/LogoMascotas.png" alt="" width="72" height="72">
      
      <h1 class="h3 mb-3 font-weight-normal">Login</h1>
      <label for="usuario" class="sr-only">Email o Usuario</label>
      <input name= "usuario" id="usuario" class="form-control" placeholder="Email o Usuario" required autofocus>
      
      <label for="password" class="sr-only">Password</label>
      <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
      
      <div class="checkbox mb-3">
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>

    </form>
</div>

<c:if test="${error!=null}">
	<p>${error}</p>
	<p>Si no tiene usuario, usted puede registrarse aqui:</p>
<div class="text-center">
    <form action="registro" method="post" class="form-signin">
      
      <h1 class="h3 mb-3 font-weight-normal">---Registro---</h1>
      
      <label for="email" class="sr-only">Email</label>
      <input name= "email" id="email" class="form-control" placeholder="Email" required autofocus>
      
      <label for="nombre" class="sr-only">Nombre</label>
      <input name="nombre" id="nombre" class="form-control" placeholder="Nombre" required>
      
      <label for="password" class="sr-only">Password</label>
      <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
      
      <div class="checkbox mb-3">
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Registro</button>
    </form>
</div>
</c:if>
<%@ include file="/BasesHtml/footer.jspf"%>


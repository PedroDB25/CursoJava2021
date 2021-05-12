# JspYServlets

Como vimos en la clase anterior podemos utilizar un servidor web para hacer pequeñas páginas web con HTML, 
pero sería bastante útil poder utilizar las herramientas de java para darle mayor personalización.

Para esto se utilizan los Servlets y los Jsp. 
Los servlets son códigos de java que contienen fragmentos de HTML, mientras que los jsp son lo inverso, códigos HTML con fragmentos de java.
aunque suene confuso o redundante veremos unos ejemplos para entenderlo mejor.

Antes que nada, ¿Cómo se crean estas "cosas"?
##Crear proyecto

Como vimos anteriormente debemos crear un "Dynamic web project", acordándonos de lo visto anteriormente.
Ahora con click derecho en la carpeta "WebContent" podemos crear nuestro archivo servlet o jsp.
(cabe decir que, si no los ves puedes abrir "otros" y ahí en el apartado "web" deberías encontrarlos. En caso de no encontrarlos deberías revisar si tienes "eclipse jee"
o en el peor de los casos descargar los plugins.

lo primero que miraremos serán los Jsp

## jsp

al crear el archivo .jsp veremos esto:

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
    </body>
    </html>

Lo que podríamos llamar una estructura típica de HTML con una línea extra, que seria:    

    <%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>

Aquí ya podríamos empezar a trabajar.
Como se mencionó antes, a esto lo podríamos llamar un .HTML al que le podemos poner líneas de java.
las líneas de java tienen que ir entre estas etiquetas <% %>.

Para mirar un ejemplo analizaremos la calculadora hecha en clases por Javier.

    https://github.com/javierlete/java-2021-04/blob/master/webbasica/WebContent/calculadora.jsp
       
Como podemos ver en este código, prácticamente tiene dos zonas, la superior con un HTML y la inferior con un programa en java.

en la zona de HTML podemos ver que tenemos un <form> que tiene un <input> seguido de un <select> y otro <input> finalizando un <button>
o sea, en cristiano tenemos dos lugares para meter texto, un menú desplegable y un botón para realizar el cálculo.
 
Esto sería todo nuestro código HTML, ahora analicemos el código java que se encuentra dentro de los <% %>.

Como todo código java inicia con una zona para definir variables, las cuales extraen los datos de los objetos HTML.

luego sigue con esta línea que contiene todo el código java.

    	if (strOp1 != null && op != null && strOp2 != null) {
    
La cual es más interesante de lo que aparenta, debido a que cuando abrimos la página web por primera vez las variables serán nulas, debido a que no hemos ingresado nada.
por lo que esta línea provoca que siempre en la primera iteración saltemos todo el código java.

luego de esto seguimos con un código normal para el ejercicio de calculadora
Primero pasa los datos de texto a números
y luego con un switch que avanza en cada caso para ver qué hacer.

Con esto tendríamos una calculadora funciona.
Pero sucede que, si tuviéramos un archivo HTML muy grande y otro archivo java muy grande, seria incomodo trabajar ambos a la vez, por lo que, lo más conveniente
para grandes cantidades de código es separar el código, dejando el HTML en un .jsp y el código java en un servlets.

## servlets

Usaremose este link

	https://github.com/javierlete/java-2021-04/blob/master/webbasica/src/com/ipartek/formacion/webbasica/CalculadoraCerebro.java

¿Entonces como creamos un servlets?  
Usaremos click derecho en el SRC, new sevelet.  
(Puede suceder que nuevamente el archivo no este, por lo que tendrás que buscar en otros, en el apartado web).  
(si estas usando la última versión de eclipse los imports te darán error y esto es debido a que el import que viene de javax ahora depende jakarta
por lo que debes modificarlos)  
deben quedar de esta manera:


    import jakarta.servlet.ServletException;
    
donde pone javax debes poner jakarta.

Al crearlo veremos que se crearon bastantes líneas de código, pero para este ejemplo solo usaremos dos métodos, que son los doGet() y doPost().
Por lo que el resto deberemos borrarlo para mantener la vista más limpia.

Quedando el archivo así.

    @WebServlet("/pruebaclase")
    public class pruebaclase extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  }
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  }
    }

De esta manera quitaremos los comentarios y el método inicial.

Ahora volviendo al jsp, copiaremos todo el código java y lo pegaremos en el método doPost(), mientras que en este espacio agregaremos una línea para insertar el resultado
que venga del servlets


    ${resultado}
    
Además, a los formularios que utilizaremos, los dejaremos enviando información a nuestro servlets con

	<form action="calculadoraCerebro" method="post">
    
De esta manera tendríamos el .jsp terminado, y nos tendríamos que enfocar en el servlets.

Antes de empezar a editarlo es conveniente leer las líneas y entender más o menos de donde viene cada cosa.
la primera línea luego de los imports es

		@WebServlet("/servletsDePrueba")
		
Línea que nos da la ubicación de esta ventana (duda).
La siguiente línea nos dice que nuestra clase extiende de HttpServlet la cual es una clase abstracta y por ende debes sobrescribir al menos uno de sus métodos.
En este caso se sobrescribieron dos de los métodos.

Bueno ahora tenemos los métodos doGet() y doPost(), y nosotros hemos rellenado el método doPost(), pero, ¿Que hacen estos métodos?

El método doGet() es el método que por defecto corre cuando llamamos por url o de alguna manera al servlets.  
Mientras que el método doPost() es el método que se utilizara cuando un formulario entra al servlets con un método "post".

En método doGet() contiene esta línea

		request.getRequestDispatcher("paginaRedireccionada.jsp").forward(request, response);
		
La que simplemente redirecciona a la página que introduzcas.

mientras que el método doPost() contiene todo nuestro codigo anterior, pero ahora tenemos dos problemas.
como transportamos la información del servlets a nuestro .jsp y cómo llegamos a nuestro .jsp

Para esto usaremos dos líneas,

			request.setAttribute("resultado", resultado);
			request.getRequestDispatcher("calculadoramvc.jsp").forward(request, response);

la primera trabaja llevando el valor resultado a la clave "resultado" que tengamos en nuestro .jsp
mientras que la segunda es una redirección al igual que la vista antes.

De esta manera ya tendríamos un .jsp que trabaja con un servlets.  

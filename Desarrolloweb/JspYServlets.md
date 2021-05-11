*JspYServlets*

Como vimos en la clase anterior podemos utilizar un servidor web para hacer pequeñas paginas web con html, 
pero seria bastante util poder utilizar las herramientas de java para darle mayor personalizacion.

Para esto se utilizan los Servlets y los Jsp. 
Los servlets son codigos de java que contienen fragmentos de html, mientras que los jsp son lo inverso, codigos html con fragmentos de java.
aunque suene confuso o redundante veremos unos ejemplos para entenderlo mejor.

Antes que nada, ¿Como se crean estas "cosas"?
**Crear proyecto**

Como vimos anteriormente debemos crear un "Dynamic web project", acordandonos de lo visto anteriormente.
Ahora con click derecho en la carpeta "WebContent" podemos crear nuestro archivo servlet o jsp.
(cabe decir que, si no los ves puedes abrir "otros" y ahi en el apartado "web" deberias encontrarlos. En caso de no encontrarlos deberias revisar si tienes "eclipse jee"
o en el peor de los casos descargar los plugins.

lo primero que miraremos seran los Jsp
**jsp**

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

Lo que podriamos llamar una estructura tipica de Html con una linea extra, que seria     

    <%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>

Aqui ya podriamos empezar a trabajar.
Como se menciono antes, a esto lo podriamos llamar un .html al que le podemos poner lineas de java.
las lineas de java tienen que ir entre estas etiquetas <% %>.

Para mirar un ejemplo analizaremos la calculadora hecha en clases por javier.

    https://github.com/javierlete/java-2021-04/blob/master/webbasica/WebContent/calculadora.jsp
       
Como podemos ver en este codigo, practicamente tiene dos zonas, la superior con un html y la inferior con un programa en java.

en la zona de html podemos ver que tenemos un <form> que tiene un <input> seguido de un <select> y otro <input> finalizando un un <button>
o sea, en cristiano tenemos dos lugares para meter texto, un menu desplegable y un boton para realizar el calculo.
 
Esto seria todo nuestro codigo html, ahora analicemos el codigo java que se encuentra dentro de los <% %>.

Como todo codigo java inicia con una zona para definir variables, las cuales extraen los datos de los objetos HTML.

luego sigue con esta linea que contiene todo el codigo java.

    	if (strOp1 != null && op != null && strOp2 != null) {
    
La cual es mas interesante de lo que aparenta, debido a que cuando abrimos la pagina web por primera vez las variables seran nulas, debido a que no hemos ingresado nada.
por lo que esta linea provoca que siempre en la primera iteracion saltemos todo el codigo java.

luego de esto seguimos con un codigo normal para el ejercicio de calculadora
Primero pasa los datos de texto a numeros
y luego con un switch que avanza en cada caso para ver que hacer.

Con esto tendriamos una calculadora funciona.
Pero sucede que si tuvieramos un archivo html muy grande y otro archivo java muy grande seria incomodo trabajar ambos a la vez, por lo que lo mas conveniente
para grandes cantidades de codigo es separar el codigo, dejando el html en un .jsp y el codigo java en un servlets

**servlets**

¿Entonces como creamos un servlets?
Usaremos click derecho en el SRC, new sevelet
(Puede suceder que nuevamente el archivo no este, por lo que tendras que buscar en otros, en el apartado web)
(si estas usando la ultima version de eclipse los imports te daran error y esto es debido a que el import que viene de javax ahora depende jakarta
por lo que debes modificarlos)
deben quedar de esta manera

    import jakarta.servlet.ServletException;
    
donde pone javax debes poner jakarta.

Al crearlo veremos que se crearon bastantes lineas de codigo, pero para este ejemplo solo usaremos dos metodos, que son los doGet() y doPost().
Por lo que el resto deberemos borrarlo para mantener la vista mas limpia.

Quedando el archivo asi.

    @WebServlet("/pruebaclase")
    public class pruebaclase extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  }
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  }
    }

De esta manera quitaremos los comentarios y el metodo inicial.

Ahora volviendo al jsp, copiaremos todo el codigo java y lo pegaremos en el metodo doPost(), mientras que en este espacio agregaremos una linea para insertar el resultado
que venga del servlets

    ${resultado}
    


-----Mañana sigo-----


    

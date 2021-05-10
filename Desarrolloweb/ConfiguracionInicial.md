*Como crear un archivo .html

Lo mas basico seria crear un bloc de notas y en el agregar lo siguiente:

    <!DOCTYPE html>
    <html>
    <head>
          <title>Titulo del archivo</title>
          <meta charset="UTF-8" />
    </head>
    <body>
    <h1>Titulo en el cuerpo</h1>
    <p>Parrafo cualquiera</p>
    </body>
    </html>

--En head:
puso <meta charset="UTF-8" /> (esta etiqueta es para decir en que sistema de caracteres queremos trabajar)

De esta manera al guardar este archivo con la extension .html ya podriamos visualizarlo en un navegador web.

*Servidor para nuestro proyecto
**descargar

Para poder montar la pagina en algun servido, utilizamos Tomcat apache

    http://tomcat.apache.org/
    
Para descargar tomcat miramos el menu a la izquierda, ahi buscamos el menu Download, en este menu elegimos la version que queremos.
En la pagina de la version elegida bajamos hasta el menu de la version y ahi descargamos y descomprimimos el archivo .zip

**Arrancar tomcat

Con la carpeta descomprimida, podemos ver que existen muchas carpetas. De momento nos enfocaremos en la carpeta Bin, ahi buscaremos el archivo startup.bat y lo ejecutaremos.

--
inciso
Puede suceder que el programa no funcione.
¿Como sabremos que no funciona?
-Al abrir el archivo este se cerrara de inmediato.
-al entrar a la direccion localhost:8080, no veremos la pagina de inicio de tomcat.
-Presentara otro error que no hemos visto en clase.

la soluion aqui puede ser que:
-En las variables de sistema no haz agregado el jdk de java.
-En las variables de sistema el jdk estan mal enrutadas.
-En las variables de sistema las variables que usa tomcat estan mal enrutadas.
-Quizas otra aplicacion esta usando el puerto 8080

--

**Primera aplicacion web

En la carpeta de tomcat buscamos la carpeta webapps, aqui crearemos una carpeta con un nombre (en clase pusimos curso), aqui dejaremos el archivo .html creado antes.
(seria recomendable poner el nombte index.html)


Con tomcat funcionando y nuestra pagina introducida, ya podemos verla dentro del servidor accediendo a la url

http://localhost:8080/curso/

si todo a funcionado bien, debes cerrar la ventana para seguir, aunque es recomendable que hagas pruebas antes de cerrar

**Crear pryecto en eclipse

Como en todos los proyectos de eclipse, debemos comenzar creando el proyecto.

para esto creamos un proyecto web con la opcion "Dynamic web project"
Al entrar al menu, debemos elegir nuentro "Target runtime", aqui vamos a darle a "new runtime..."
En la lista escojeremos apache tomcat que descargamos, en este caso el 10.0.5.
En esta vista abajo daremos click en linea para que cree un servidor local "Create a new local server".
y al finalizar el poryecto le damos click a "generate web.xml deployment descriptor"

**Primer HTML en java

Con la configuracion creada tendremos varias carpetas.

De momento solo usaremos la carpeta de WebContent, ahi crearemos un archivo .html
para eso daremos click derecho, new, html File.

(Notar que dentro del html creado el charset es "ISO-8859-1")
(si queremos cambiarlo primero cambiamos la codificacion, dentro de preferencias de eclipce usamos el buscador y escribimos "encoding" ahi en la parte web cambiamos las 3 de la zona web a "UTF-8") y ahora se lo modificamos al archivo .html

Ahora podemos probar que esta funcionando.
(conviene editar el index.html que creamos ahora para poder ver cambios)

le damos al play de eclipse y nos dara unas opciones, apretamos finalizar y se nos abrira una opcion de firewall, le damos permitir y ya deberiamos ver nuestra pagina web.

Ahora queremos poder usar comandos de java en esta pagina, por lo que creamos un "jsp file"
al igual que el "html file" lo encontramos con click derecho, new, "jsp file".

ahora tenemos un nuevo archivo muy similar al anterior
pero podemos poner una nueva etiqueta
<% %>
y entre estas etiquetas podemos escribir lineas de java	

ejemplo(dentro de las etiquetas html y bajo head):

<body>

	<% for(int i=1; i <=5; i++) { %>
		<h1> se repite </h1>
		<% } %>
</body>

esto daria como resultado:

se repite 
se repite 
se repite 
se repite 
se repite 

explico que es posible que te salga un error en la primera linea (este error puede ser ignorado), esto se produce por el cambio de nombres entre algunos ficheros de tomcat que ahora no son javax, si no que son jakarta, pero el programa funcionara de todas maneras.

volvido al tema, si queremos añadir valores de java usamos esta linea

<%=i  %>
(notar que el signo = va pegado al %)
aclaro que nunca deberiamos ver lineas de java en el resultado final.

ahora notamos que tenemos un index.html y un index.jsp, es interesante saber cual es que primero que se llamara, dentro del archivo que generamos al final de la creacion del proyecto el web.xml, podemos ordenar el orden de ejecucion.

Si quisieramos usar algo que necesita import, por ejemplo un arraylist, necesitariamos importarlo dentro de la segunda linea con el comando

import="java.util.ArrayList"

esto tiene que ir dentro de la etiqueta <% %> 

si queremos insertar datos desde la url, primero necesitarmos modificar la url agregando una ? al final y definiendo una variable por ejemplo

http://localhost:8080/Proyectoweb3/index.jsp?nombre=pepe

ahora modificamos el archivo .jsp para introducir los datos.

para esto podemos usar los Expression Language.

agregando esto al .jsp
${param.nombre}
notar que no requiere estar entre las etiquetas <% %>

Creamos un formulario donde al introducir sus datos modificaban la url para ver los cambios.

para esto creamos una etiqueta <form>
con una casilla y un boton

	<form>
		 <input name= "nombre">
		 <button>saludar</button>
	
	</form>

pero al intentar utilizar lo mismo para una contraseña vemos que la contraseña aparece en la url, por lo que la etiqueta form la modificamos dandole un method.

	<form method="post">


y al parecer la clase termino, y nos dio como tarea intentar hacer una clase y haremos como tarea una calculadora.





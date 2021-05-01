
Esta guia es para poder practicar la conexion con la base de datos SQLITE, es importante ir mirando los archivos subidos al github mientras avanzamos.


--Configuracion--


Lo primero que necesitas es descargar el driver que conecta java con SQLITE desde la pagina:
https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
En esta pagina buscas el ultimo, abres la pagina y luego apretas el jar (Esta en la tabla en el campo de FILE)

Este archivo .jar lo dejas dentro de tu proyecto. (En clase creamos una carpeta llamada "lib" y dejamos el .jar ahi)

Con el archivo en el proyecto debes configurar el BuildPath para que java pueda usar el driver de SQLite.
Si tienes eclipse al dar click derecho en el .jar te dara una opcion para agregarlo facilmente.
Se creara un nuevo fichero en el proyecto que te confirmara que el driver se podra usar en tu proyecto.
En VSC cuando creas el proyecto en java abajo en la barra de explorador debe aparecer una barra que dice JAVA proyects ahi puedes refenciar la libreria del .jar 

Con la configuracion creada ya podemos crear la base de datos, para esto usamos en clase DBeaver, pero se puede usar cualquier herramienta para administrar bases de datos por ejemplo SQLBrowser.

--Crear Base de datos--

si no quieres crearla, puedes descargarla de aqui:
https://github.com/javierlete/java-2021-04/tree/master/BasesDeDatos
y ponerla en tu proyecto
el archivo es: tienda.db

Para el ejemplo creamos una tabla "tienda.db" que tiene 3 columnas (id, nombre, apellido) usaremos el dato id de primary key (lo que significa que este es el unico dato que no puede repetirse.) ademas le añadiremos la opcion de ser autoincremental. (por lo que la base de datos lo añadira por si sola, cada vez que agreguemos alguien a la tabla.)

Con la configuracion hecha y una base de datos podemos empezar a programar.


--Crear Entidad--
link: https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/entidades/Cliente.java


lo Primero que haremos es crear una clase para depositar los datos. Esta clase se creara en un paquete dentro de SRC que se llamara Entidades.
La clase debe tener las mismas variables que columnas creadas en la tabla.
Creamos los Gettets y Setters, un constructor con todas las variables y un metodo ToString para visualizar los datos.


--Capa de acceso a datos-- (Dao)
link: https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/accesodatos/DaoCliente.java
--Variables de Conexion


Con la entidad terminada, crearemos la capa de "acceso a datos" la que llamamos "Dao". (data access object)
Para esto crearemos una clase llamada Dao con un sufijo deseado (Puede ser DaoRopa, DaoCasa, DaoClientes, Etc...)
La Clase Dao empieza como todas las clases definiendo las Constantes y variables.

Las primeras 3 Constantes de esta clase son las constantes de conexion las cuales son URL, USUARIO y PASSWORD.
estas se ven asi:

	private static final String URL = "jdbc:sqlite:ejemplo.db";
	private static final String USUARIO = "";
	private static final String PASSWORD = "";

Estas lineas dependen del gestor de base de datos que usemos pero para SQLITE (gestor que usamos en clases), serian estas lineas
hay que tener cuidado con remplazar en la URL la palabra "ejemplo" por el nombre de nuestra base de datos.
(Si quisieramos usar otro gestor de base de datos estas lineas 3 lineas deben ser modificadas
pero el resto del programa permaneceria igual.)


--Variables de Sentencias SQL

Las siguentes 5 lineas son las Sentencias SQL que usaremos para este ejemplo:

	private static final String SQL_SELECT = "SELECT id, nombre, apellidos FROM clientes";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO clientes (nombre, apellidos) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE clientes SET nombre = ?, apellidos = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id = ?";

Notar que las interogaciones "?" son puntos donde luego insertaremos datos.
Para un mejor entendimiento la sintaxis de cada operacion seria:

-Esta parte puedes saltarla si sabes de SQL, puedes saltar a la linea 112-

(La sentencia debe quedar igual a las de arriba, cualquier simbolo extra los agregue para poder separa conceptos)

SQL_SELECT =  "SELECT [CAMPOS_DE_LA_TABLA] FROM [TABLA];

Aqui la sentencia quiere decir:
"Selecciona las columnas que te digo de esta tabla"

SQL_SELECT_ID = SQL_SELECT  + " WHERE id = ?";
                    Y
        (Reutilizamos la linea anterior) 
        
La sentencia WHERE esta para dar la condicion, o sea esta sentencia dice:
"Dame todas las columnas y entre ellas elije la fila que tenga el id que te digo"
Recordar que las interrogaciones son para dejar un espacio donde agregar datos luego.

SQL_INSERT = "INSERT INTO [TABLA] ([Columnas_a_rellenar]) VALUES ([Tantas "?" como columnas pusiste antes separadas por comas])";

Esta es la sentencia de insertar, aqui la sentencia dice:
"Inserta en la tabla y en las siguentes columnas estos valores"

Estas sentencias las pongo juntas debido a que ambas tienen el riesgo de que si no agregas el WHERE del final "destruiras"
la tabla.

SQL_UPDATE = "UPDATE [TABLA] SET [Columna] = ?";
SQL_DELETE = "DELETE FROM [TABLA] WHERE id = ?";

La sentencia UPDATE dice:
" actualiza la tabla con la siguente informacion"

Mientras que la DELETE dice:
"Borra de la tabla, el id siguente."


--Metodos Para acceso a datos
--ObtenerTodos()


Siguiendo en las lineas ahora toca mirar los metodos que utilizo y el porque de cada linea.
El primer metodo de esta capa es ObtenerTodos() :

	public static ArrayList<Cliente> obtenerTodos() {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT);
				ResultSet rs = ps.executeQuery()) {
			ArrayList<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos")));
			}
			return clientes;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los clientes", e);
		}
	}

Lo primero que tienes que ver es que es un metodo:
-public (O sea que se puede acceder desde fuera de la clase).
-Static (o sea que no requiere un objeto para poder llamarlo)
-ArrayList<Clientes> (Significa que para cerrar el metodo debe haber un "return" de un arraylist del objeto clientes)

Entrando en el metodo, lo primero que vemos es que hay un Try-catch pero que es una especial ya que
su sintaxis es:

    try(){
    }catch{}

Esta es una try-with-resources, su gracia es que lo que se encuentra dentro del parentesis se cierra automaticamente
al terminar el bloque, los que nos ahorrara agregar todas las lineas de codigo para cerrar las consulatas Y
todas las excepciones que esto nos dara.

La primera linea dentro del parentesis del try es:
    Connection con = obtenerConexion();
Esto es crear un Objeto Connection con el nombre con, que es iniciado con el metodo obtenerConexion().
Al ir a este metodo vemos que tiene la siguente forma:

	private static Connection obtenerConexion() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		} catch (Exception e) {
			throw new AccesoDatosException("Error en la conexión a clientes", e);
		}
		return con;
	}
Vemos que es un metodo privado (no se puede llamar fuera de esta clase) y devuelve un objeto tipo Connection
Este metodo inicia creando un objeto vacio de tipo Connection el cual rellenaremos en este metodo.
Aqui encontramos un try-catch normal, pero con la particularidad de que le agregamos al bloque catch una
excepciones que creamos nosotros "AccesoDatosException".

(Para no salir mucho del tema esta excepcion la tratare en un anexo al final de este texto.)

Ya dentro del bloque try le damos al objeto "con" las variables que definimos al principio, para que pueda
realizar la conexion. y si es que no se genero una excepcion retornaremos una conexion con la base de datos.

Con esto podemos volver al metodo obtenerTodos().
Siguiendo dentro del bloque try, veremos la siguente linea:

    PreparedStatement ps = con.prepareStatement(SQL_SELECT);

esta linea genera la peticion que se le realizara a la base de datos, con los datos que definimos en el
apartado "Variables de Sentencias SQL", exactamente con la primera "SQL_SELECT"

con la sentencia ya prepara, con la siguente linea la executamos

    ResultSet rs = ps.executeQuery()

ya con esto pasamos a las llaves del try, donde encontramos las siguientes lineas:

    ArrayList<Cliente> clientes = new ArrayList<>();
	while (rs.next()) {
		clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos")));

Lo Primero que hacemos es crear el ArrayList que luego devolveremos en el return 

Recordemos que rs.next() devuelve "true" si existe un campo para avanzar en la tabla y avanza.
En la siguente linea ya utilizamos la Entidad que habiamos creado antes, una clase que almacene todos los datos
de las consultas.

entonces en esta linea decimos que al array que creamos hace 2 lineas le añadimos un objeto de tipo Cliente
Para esto Creamos un nuevo objeto con la palabra "new" y usamos el constructor que le creamos a la entidad.
a los parametros del conetructor le pasamos los datos almacenados en las columnas.

y al ser esto dentro del bloque while, se repetira añadiendo objetos al arraylist hasta que rs.next()
devuelva un false, o sea que ya no queden mas filas en la tabla.

Con esto hemos creado un metodo que devuelve un arraylist de objetos que estan rellenos con la informacion
de la base de datos. 

Ahora avanzaremos al siguente metodo

--obtenerTodosAntesDeJava7()

Este metodo se utilizaria si no existieran los try-with-resources. pero como si existen ya no es necesario
cerrar manualmente las conexiones a la base de datos.

-- obtenerPorId()


El segundo metodo es el ObtenerPorId, que a diferencia del metodo anterior, este devuelve solo una fila
elegida por el id entregado, el metodo tiene esta forma.

public static Cliente obtenerPorId(Integer id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Cliente cliente = null;
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"));
			}
			return cliente;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener cliente por id ha fallado", e);
		}
	}

Este metodo nuevamente es public y static. pero devuelve un solo objeto de tipo Cliente, ademas recibe de
parametro un Integer "id"

Vemos que nuevamente comienza con un try-with-resources, por ende las lineas 

	Connection con = obtenerConexion();
	PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);

funcionan exactamente igual a la explicacion anterior. (la 1º genera la conexion y la segunda genera la sentencia)

La sentencia para darle los valores a la expresion "SQL_SELECT_ID" es la siguiente, en caso de ser un Integer o int. 
(el metodo usado depende del tipo de dato que pasaremos.)

	ps.setInt(1, id);

donde id, es la posicion entregada como parametro y el primer valor es el "parameterIndex" lo que podriamos
traducir como la columna en la que queremos buscar.

para luego ejectar la sentencia con la linea:

	ResultSet rs = ps.executeQuery();

De esta manera ya hemos pedido la informacion a la base de datos y la tenemos guardada en un objeto "ResultSet"
con el nombre "rs" del cual necesitamos extraer la informacion para entregarla al objeto que devuelve este metodo.

Para poder depositar la informacion creamos un objeto de tipo Cliente vacio el cual sera sacado por el return
del metodo.

Finalmente usamos la sentencia "rs.next()" dentro de un if para asegurarnos que haya por lo menos 1 dato con estas
caracteristicas.

	if (rs.next()) {

y seguido de esto al objeto Cliente recien generado se le asignan los valores extraidos del objeto "ResultSet"
como se ve en la siguiente linea:

	cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"));

y terminar el metodo introduciendo al objeto Cliente rellenado en el return

	return cliente;

Al final de todo explicare la excepcion que creamos para el catch.


-- insertar()

Este metodo usa muchos terminos que ya estan explicados en los otros metodos por lo que sera menos explicativo
Aun asi hay que notar que este metodo devuelve un objeto de tipo Cliente, asi mismo como lo recibe como
parametro.

public static Cliente insertar(Cliente cliente) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			rs.next();

			cliente.setId(rs.getInt(1));

			return cliente;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar cliente ha fallado", e);
		}
	}

Nuevamente iniciamos con el try-with-resources para cerrar las operaciones que requieren un cierre explicito.
en las que van el metodo para conectarse y la preparacion de la sentencia SQL.

	Connection con = obtenerConexion();
	PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

(Cabe mencionar que el segundo parametro que se entrega al objeto de tipo PreparedStatement y el return de este metodo
los estamos usando para visualizar el resultado, por lo que podriamos no tenerlos.)

siguendo las lineas ahora utilizamos los .setString para introdicir la informaciona la sentencia SQL
desde el objeto que tenemos en el parametro con sus respectivos metodos .get

	ps.setString(1, cliente.getNombre());
	ps.setString(2, cliente.getApellidos());

siguiendo el orden ejecutamos la sentencia SQL con: 

	ps.executeUpdate();

Las sigueintes lineas son para rescatar el cliente generado y poder mostrarlo.

	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
	cliente.setId(rs.getInt(1));
	return cliente;

en estas lineas estamos solicitando el id autogenerado por la tabla y se lo insertamos con el setter
del objeto cliente, para luego retornarlo y poder visualizarlo.


-- modificar()

Este metodo es muy similar al anterior.

	public static Cliente modificar(Cliente cliente) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setInt(3, cliente.getId());

			ps.executeUpdate();

			return cliente;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}

tenemos un metodo public que retornara un objeto de tipo cliente y recibira de parametro un objeto de tipo cliente.

nuevamente iniciamos con el try-with-resources al cual le pasamos los metodos de apertura de conexion y 
generacion de sentencia SQL.

	try(Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);){

Seguimos entregandole los parametros a la sentencia SQL:

	ps.setString(1, cliente.getNombre());
	ps.setString(2, cliente.getApellidos());
	ps.setInt(3, cliente.getId());

(Cabe mencionar que el 3º parametro lo entregamos en un where, por lo que a diferencia de los otros 2 anteriores
no estamos modificando el id, si no que estamos seleccionando uno.)

finalmente ejecutamos la sentencia SQL

	ps.executeUpdate();

y retornamos el mismo objeto de tipo Cliente que entregamos.
(nuevamente el return es para poder visualizar la infomarcion modificada.)


--borrar ()

Este es el metodo mas corto y comparte casi todo con los metodos vistos antes.

	public static void borrar(Integer id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}

Es un metodo publico, pero esta vez no tiene retorno y recibe un Integer id como parametro.
nuevamente iniciamos con el bloque try-with-resources

que tiene en el parentesis los bloques para la conexion y preparar la sentencia SQL

	try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

luego insertamos el id recibido como parametro con la sentencia 

	ps.setInt(1, id);

y finalmente ejecutamos la sentencia:

	ps.executeUpdate();


--Capa de Presentacion.
link: https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/presentacion/consola/Presentacion.java

Ahora que hemos generado los metodos para acceder a la informacion y la entidad que transportara los metodos
podemos enfocarnos en la capa de presentacion.
(o sea, una capa donde solo ejecutaremos los metodos y podremos acceder a la informacion en muy pocas lineas.)

Como podemos ver esta clase tiene el metodo main, por ende es la unica clase que sera ejecutada, y de la cual
tendremos que llamar a las otras.

Ya comenzando a llamar a los metodos lo primero que se hizo fue crear un metodo mostrarTodos(), con el cual
ir mostrando los cambios que se generan en la base de datos, cuando realizamos las sentencias.

	private static void mostrarTodos() {
		ArrayList<Cliente> clientes = DaoCliente.obtenerTodos();

		System.out.println("MOSTRANDO TODOS");

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

Este es un metodo public, void y que no recibe parametros.
aqui genereramos un ArrayList de tipo Cliente, para recibir el ArrayList que retorna el metodo obtenerTodos()
y para imprimirlo en pantalla utilizamos un bucle for, que recorra el ArrayList y los imprima en pantalla.

Comenzando el metodo main, vemos que la primera linea es un bloque try-catch, que utilizaremos para 
atajar los errores que puedan salir.

ya dentro del bloque lo primero que hacemos es crear un objeto cliente de tipo Cliente, el cual inicializamos
con los valores retornados por el metodo creado antes obtenerPorId() al cual le ponemos el parametro "2".
(Recordar que este metodo lo creamos en el Dao)

	Cliente cliente = DaoCliente.obtenerPorId(2);
	System.out.println(cliente);
	System.out.println(cliente.getNombre());

y como el objetivo es poder verlo por pantalla utilizamos la 2º y 3º linea para ver sus dados.

luego usamos el metodo que creamos en esta clase mostrarTodos() para ver toda la tabla.

	mostrarTodos();

ahora insertamos un objeto de tipo Cliente a la tabla con el metodo insertar() creado en el Dao.

	cliente = DaoCliente.insertar(new Cliente(null, "Juanes", "Teban"));
	System.out.println("INSERTADO -> " + cliente);

Cabe decir que el primer parametro es null, debido a que la base de datos genera el id por si misma, debido
a que le añadimos la opcion de que sea autoincremental.
y ponemos su respectiva linea para mostrar en consola.

nuevamente utilizamos el metodo mostrarTodos() para ver la base de datos con el metodo insertado.

ahora utilizamos el metodo modificar() para editar los valores del objeto insertado.

	DaoCliente.modificar(new Cliente(cliente.getId(), "Modificado", "Modificadez"));

Cabe decir que para poder modificar la misma linea que insertamos, reutilizamos el objeto de tipo Cliente
que creamos para ejecutar la instrucion de obtenerPorId y luego redefinimos en la instruccion de insertar.
por eso, este objeto de tipo Cliente tiene el mismo id que el objeto insertado.
Recordar que en el metodo insertar() retornamos un objeto Cliente al cual le insertamos el id generado por
la tabla.

finalmente utilizamos el metodo borrar() y le pasamos de parametro el id del mismo objeto Cliente que anteriormente
modificamos.

	DaoCliente.borrar(cliente.getId());


Este es el resumen de lo fundamental que vimos en la clase del viernes 30/04/21

--Anexo: Crear nuestra propia ExCEPTION.
Lo primero que hacemos es en un bloque try-catch, en el apartado catch agregar la linea

	throw new AccesoDatosException("No se han podido obtener todos los clientes", e);

Ahora nos saltaran errores por usar AccesoDatosException sin que esta clase exista, pero dentro de las opciones generadas por eclipse o VSC, nos diran que creemos esta clase.
Como el programa detecta que queremos hacer una excepcion nos crea la clase con el herencia de exceptions (si no, nosotros la agregamos), pero necesitamos que esta clase herede de RuntimeException, por lo que lo cambiamos.
Ahora le heredamos los constructores de la clase padre.

y con esto tenemos nuestra Excepcion creada.







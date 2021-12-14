	
## Índice: 

[-Configuración](/#Configuración)

[-Crear base de datos](/#Crear base de datos)

[-Crear Entidades](/#Crear Entidades)

[-Capa de acceso a datos](#Capa de acceso a datos)

[-Variables de conexion](#Variables de conexion)

[-Variables de Sentencias SQL](#Variables de Sentencias SQL)

[-Métodos Para acceso a datos](#Métodos Para acceso a datos)

[-ObtenerTodos()](/#ObtenerTodos)

[-ObtenerPorId()](/#ObtenerPorId)

[-Insertar()](/#Insertar)

[-Modificar()](/#Modificar)

[-Borrar()](/#Borrar)

[-Capa de Presentación](/#Capa de Presentación)

[-Anexo](/#Anexo)

Esta guía es para poder practicar la conexión con la base de datos SQLITE o MySQL, es importante ir mirando los archivos subidos al github mientras avanzamos.


## Configuración


Lo primero que necesitas es descargar el driver que conecta java con SQLITE desde la pagina: 

	https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc 
	https://mvnrepository.com/artifact/mysql/mysql-connector-java/
	
En esta pagina buscas el ultimo, abres la pagina y luego apretas el jar (Esta en la tabla en el campo de FILE)

Este archivo .jar lo dejas dentro de tu proyecto. (En clase creamos una carpeta llamada "lib" y dejamos el .jar ahí)

Con el archivo en el proyecto debes configurar el BuildPath para que java pueda usar el driver de SQLite.

		-Eclipse: al dar click derecho en el .jar te dará una opción para agregarlo fácilmente.
		Se podrá visualizar en como "librerías referenciadas" lo que te confirmará que el driver
		se podrá usar en tu proyecto. 


		-VSC: cuando creas el proyecto Java abajo en la barra de explorador debe aparecer una barra
		que dice JAVA proyects ahi puedes referenciar la librería del .jar

Con la configuración lista, podemos crear la base de datos, para esto usamos en DBeaver, sqlite browser o el CMD en el pero de lo casos, pero se puede usar cualquier herramienta para administrar bases de datos por ejemplo.


## Crear Base de datos

Si no quieres crearla, puedes descargarla de aqui: 

	https://github.com/javierlete/java-2021-04/tree/master/BasesDeDatos 
	
y ponerla en tu proyecto el archivo es: tienda.db, ponla en el raiz de tu proyecto.
Si usas MySQL carga esta base de datos en tu aplicacion Xampp.

Para el ejemplo creamos una tabla "tienda.db" que tiene 3 columnas (id, nombre, apellido) usaremos el dato id de primary key (lo que significa que este es el único dato que no puede repetirse y ademas no puede ser nulo). Además le añadiremos la opción de ser autoincremental. (por lo que la base de datos lo añadirá por sí sola, cada vez que agreguemos alguien a la tabla).

Con la configuración hecha y una base de datos podemos empezar a programar.


## Crear Entidad

	https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/entidades/Cliente.java


Lo primero que haremos es crear una clase para depositar los datos. Esta clase se creará en un paquete dentro de SRC que se llamará "entidades". La clase debe tener las mismas variables que columnas creadas en la tabla. Creamos los Gettets y Setters, un constructor con todas las variables y un método ToString() para visualizar los datos, es recomendable que agregues los métodos "equals()" y "hashcode".

## Capa de acceso a datos (Dal)

	https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/accesodatos/DaoCliente.java

### Variables de conexion

Con la entidad terminada, crearemos la capa de "acceso a datos" la que llamamos "Dal". (data access layer) Para esto crearemos una clase llamada Dao con un sufijo deseado (Puede ser DaoRopa, DaoCasa, DaoClientes, Etc...) La Clase Dao empieza como todas las clases definiendo las Constantes y variables.

Las primeras 3 Constantes de esta clase son las constantes de conexión las cuales son URL, USUARIO y PASSWORD. estas se ven así para SQLite:

	private static final String URL = "jdbc:sqlite:ejemplo.db";
	private static final String USUARIO = "";
	private static final String PASSWORD = "";
	
Y asi para MySQL:
	
	private static final String URL = "jdbc:mysql://localhost/ejemplo";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";
	
Estas líneas dependen del gestor de base de datos que usemos pero para SQLITE y MySQL, serian estas líneas hay que tener cuidado con reemplazar en la URL la palabra "ejemplo" por el nombre de nuestra base de datos. 
(Si quisiéramos usar otro gestor de base de datos estas 3 lineas deben ser modificadas pero el resto del programa permanece igual.)

### Variables de Sentencias SQL

Las siguientes 5 líneas son las Sentencias SQL que usaremos para este ejemplo:

	private static final String SQL_SELECT = "SELECT id, nombre, apellidos FROM clientes";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO clientes (nombre, apellidos) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE clientes SET nombre = ?, apellidos = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id = ?";
	
Notar que las interrogaciones "?" son puntos donde luego insertamos datos. 

Para un mejor entendimiento la sintaxis de cada operación sería: 
(Esta parte puedes saltarla si sabes de SQL, puedes saltar al siguiente punto)

(La sentencia debe quedar igual a las de arriba, cualquier símbolo extra los agregue para poder separar conceptos)

	SQL_SELECT = "SELECT [CAMPOS_DE_LA_TABLA] FROM [TABLA];

Aquí la sentencia quiere decir: "Selecciona las columnas que te digo de esta tabla"

	SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?"; Y (Reutilizamos la línea anterior)

La sentencia WHERE esta para dar la condición, o sea esta sentencia dice: "Dame todas las columnas y entre ellas elige la fila que tenga el id que te digo" Recordar que las interrogaciones son para dejar un espacio donde agregar datos luego.

	SQL_INSERT = "INSERT INTO [TABLA] ([Columnas_a_rellenar]) VALUES ([Tantas "?" como columnas pusiste antes separadas por comas])";

Esta es la sentencia de insertar, aquí la sentencia dice: "Inserta en la tabla y en las siguientes columnas estos valores"

Estas sentencias las pongo juntas debido a que ambas tienen el riesgo de que si no agregas el WHERE del final "destruirás" la tabla.

	SQL_UPDATE = "UPDATE [TABLA] SET [Columna] WHERE id = ?";
	SQL_DELETE = "DELETE FROM [TABLA] WHERE id = ?";

La sentencia UPDATE dice: " actualiza la tabla con la siguiente información"

Mientras que la DELETE dice: "Borra de la tabla, el id siguiente."

### Métodos Para acceso a datos 
#### ObtenerTodos()

Siguiendo en las líneas ahora toca mirar los métodos que utilizó y el porqué de cada línea. El primer método de esta capa es ObtenerTodos() :

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

Lo primero que tienes que ver es que es un método: 

		-public (O sea que se puede acceder desde fuera de la clase). 
		-Static (o sea que no requiere un objeto para poder llamarlo y pertenece a la clase) 
		-ArrayList (Significa que para cerrar el método debe haber un "return" de un arraylist del objeto clientes)

Entrando en el método, lo primero que vemos es que hay un Try-catch pero es uno especial ya que su sintaxis es:

	try(){
	}catch{}

Este es un try-with-resources, su gracia es que lo que se encuentra dentro del paréntesis se cierra automáticamente al terminar el bloque, los que nos ahorrará agregar todas las líneas de código para cerrar las consultas y todas las excepciones que esto nos dará.

La primera línea dentro del paréntesis del try es:

	Connection con = obtenerConexion(); 
		
Esto es crear un Objeto Connection con el nombre con, que es iniciado con el método obtenerConexion(). Al ir a este método vemos que tiene la siguiente forma:

	private static Connection obtenerConexion() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		} catch (Exception e) {
			throw new AccesoDatosException("Error en la conexión a clientes", e);
		}
		return con;
	}

Vemos que es un método privado (no se puede llamar fuera de esta clase) y devuelve un objeto tipo Connection. Este método inicia creando un objeto vacío de tipo Connection el cual rellenaremos en este método. Aquí encontramos un try-catch normal, pero con la particularidad de que le agregamos al bloque catch una excepciones que creamos nosotros "AccesoDatosException". Pero puede ser una excepcion normal.
(Para no salir mucho del tema esta excepción la trataré en un anexo al final de este texto.)

Ya dentro del bloque try le damos al objeto "con" las variables que definimos al principio, para que pueda realizar la conexión. y si es que no se genera una excepción retornaremos una conexión con la base de datos.

Con esto podemos volver al método obtenerTodos(). Siguiendo dentro del bloque try, veremos la siguiente línea:

	PreparedStatement ps = con.prepareStatement(SQL_SELECT);
	
esta línea genera la petición que se le realizara a la base de datos, con los datos que definimos en el apartado "Variables de Sentencias SQL", exactamente con la primera "SQL_SELECT"

con la sentencia ya prepara, con la siguiente línea la ejecutamos

	ResultSet rs = ps.executeQuery()

ya con esto pasamos a las llaves del try, donde encontramos las siguientes líneas:

	ArrayList<Cliente> clientes = new ArrayList<>();
	while (rs.next()) {
		clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos")));
	
Lo Primero que hacemos es crear el ArrayList que luego devolveremos en el return

Recordemos que rs.next() devuelve "true" si existe un campo para avanzar en la tabla y avanza. En la siguiente línea ya utilizamos la Entidad que habíamos creado antes, una clase que almacene todos los datos de las consultas.

entonces en esta línea decimos que al array que creamos hace 2 líneas le añadimos un objeto de tipo Cliente Para esto creamos un nuevo objeto con la palabra "new" y usamos el constructor que le creamos a la entidad. a los parámetros del constructor le pasamos los datos almacenados en las columnas.

y al ser esto dentro del bloque while, se repetirá añadiendo objetos al arraylist hasta que rs.next() devuelva un false, o sea que ya no quedan más filas en la tabla.

Con esto hemos creado un método que devuelve un arraylist de objetos que están rellenos con la información de la base de datos.

Ahora avanzaremos al siguiente método

#### obtenerTodosAntesDeJava7()

Este método se utiliza si no existieran los try-with-resources. pero cómo si existen ya no es necesario cerrar manualmente las conexiones a la base de datos.

#### obtenerPorId()

El segundo método es el ObtenerPorId, que a diferencia del método anterior, este devuelve solo una fila elegida por el id entregado, el método tiene esta forma.

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

Este método nuevamente es public y static. pero devuelve un solo objeto de tipo Cliente, además recibe de parámetro un Integer "id"

Vemos que nuevamente comienza con un try-with-resources, por ende las líneas

	Connection con = obtenerConexion();
	PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
	
funcionan exactamente igual a la explicación anterior. (la 1º genera la conexión y la segunda genera la sentencia)

La sentencia para darle los valores a la expresión "SQL_SELECT_ID" es la siguiente, en caso de ser un Integer o int. (el método usado depende del tipo de dato que pasaremos.)

	ps.setInt(1, id);
	
donde el primer parametro "parameterIndex" indica a que simbolo de interrogacion debe remplazar, mientras que el segundo valor indica por que valor debe remplazarse.

para luego ejecutar la sentencia con la línea:

	ResultSet rs = ps.executeQuery();

De esta manera ya hemos pedido la información a la base de datos y la tenemos guardada en un objeto "ResultSet" con el nombre "rs" del cual necesitamos extraer la información para entregarla al objeto que devuelve este método.

Para poder depositar la información creamos un objeto de tipo Cliente vacío el cual será sacado por el return del método.

Finalmente usamos la sentencia "rs.next()" dentro de un if para asegurarnos que haya por lo menos 1 dato con estas características.

	if (rs.next()) {

y seguido de esto al objeto Cliente recién generado se le asignan los valores extraídos del objeto "ResultSet" como se ve en la siguiente línea:

	cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"));
	
y terminar el método introduciendo al objeto Cliente rellenado en el return

	return cliente;

Al final de todo explicare la excepción que creamos para el catch.

#### insertar()

Este método usa muchos términos que ya están explicados en los otros métodos por lo que será menos explicativo Aun así hay que notar que este método devuelve un objeto de tipo Cliente, así mismo como lo recibe como parámetro.

	public static Cliente insertar(Cliente cliente) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
		
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
Nuevamente iniciamos con el try-with-resources para cerrar las operaciones que requieren un cierre explícito. en las que van el método para conectarse y la preparación de la sentencia SQL.

	Connection con = obtenerConexion();
	PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
	
(Cabe mencionar que el segundo parámetro que se entrega al objeto de tipo PreparedStatement y el return de este método los estamos usando para visualizar el resultado, por lo que podríamos no tenerlos.)

siguiendo las líneas ahora utilizamos los .setString para introducir la informaciona la sentencia SQL desde el objeto que tenemos en el parámetro con sus respectivos métodos .get

	ps.setString(1, cliente.getNombre());
	ps.setString(2, cliente.getApellidos());
	
siguiendo el orden ejecutamos la sentencia SQL con:

	ps.executeUpdate();
	
Las siguientes líneas son para rescatar el cliente generado y poder mostrarlo.

	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
	cliente.setId(rs.getInt(1));
	return cliente;
	
en estas líneas estamos solicitando el id autogenerado por la tabla y se lo insertamos con el setter del objeto cliente, para luego retornarlo y poder visualizarlo.

#### modificar()

Este método es muy similar al anterior.

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
tenemos un método public que retorna un objeto de tipo cliente y recibirá de parámetro un objeto de tipo cliente.

nuevamente iniciamos con el try-with-resources al cual le pasamos los métodos de apertura de conexión y generación de sentencia SQL.

	try(Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);){
	
Seguimos entregando los parámetros a la sentencia SQL:

	ps.setString(1, cliente.getNombre());
	ps.setString(2, cliente.getApellidos());
	ps.setInt(3, cliente.getId());

(Cabe mencionar que el 3º parámetro lo entregamos en un where, por lo que a diferencia de los otros 2 anteriores no estamos modificando el id, si no que estamos seleccionando uno.)

finalmente ejecutamos la sentencia SQL

	ps.executeUpdate();
	
y retornamos el mismo objeto de tipo Cliente que entregamos. (nuevamente el return es para poder visualizar la información modificada.)

#### borrar()

Este es el método más corto y comparte casi todo con los métodos vistos antes.

	public static void borrar(Integer id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}
Es un método public, pero esta vez no tiene retorno y recibe un Integer id como parámetro. nuevamente iniciamos con el bloque try-with-resources

que tiene en el paréntesis los bloques para la conexión y preparar la sentencia SQL

	try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
	
luego insertamos el id recibido como parámetro con la sentencia

	ps.setInt(1, id);
	
y finalmente ejecutamos la sentencia:

	ps.executeUpdate();
	
	
## Capa de Presentación 

	https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/presentacion/consola/Presentacion.java


Ahora que hemos generado los métodos para acceder a la información y la entidad que transporta los métodos podemos enfocarnos en la capa de presentación. (o sea, una capa donde solo ejecutaremos los métodos y podremos acceder a la información en muy pocas líneas.)

Como podemos ver esta clase tiene el método main, por ende es la única clase que será ejecutada, y de la cual tendremos que llamar a las otras.

Ya comenzando a llamar a los métodos lo primero que se hizo fue crear un método mostrarTodos(), con el cual ir mostrando los cambios que se generan en la base de datos, cuando realizamos las sentencias.

	private static void mostrarTodos() {
	ArrayList<Cliente> clientes = DaoCliente.obtenerTodos();
	System.out.println("MOSTRANDO TODOS");
	for (Cliente cliente : clientes) {
		System.out.println(cliente);
	}
	}
Este es un método private, void y que no recibe parámetros. aquí generamos un ArrayList de tipo Cliente, para recibir el ArrayList que retorna el método obtenerTodos() y para imprimirlo en pantalla utilizamos un bucle for, que recorra el ArrayList y los imprima en pantalla.

Comenzando el método main, vemos que la primera línea es un bloque try-catch, que utilizaremos para atajar los errores que puedan salir.

ya dentro del bloque lo primero que hacemos es crear un objeto cliente de tipo Cliente, el cual inicializamos con los valores retornados por el método creado antes obtenerPorId() al cual le ponemos el parámetro "2". (Recordar que este método lo creamos en el Dao)

	Cliente cliente = DaoCliente.obtenerPorId(2);
	System.out.println(cliente);
	System.out.println(cliente.getNombre());
	
y como el objetivo es poder verlo por pantalla utilizamos la 2º y 3º línea para ver sus datos.

luego usamos el método que creamos en esta clase mostrarTodos() para ver toda la tabla.

	mostrarTodos();
	
ahora insertamos un objeto de tipo Cliente a la tabla con el método insertar() creado en el Dao.

	cliente = DaoCliente.insertar(new Cliente(null, "Juanes", "Teban"));
	System.out.println("INSERTADO -> " + cliente);
	
Cabe decir que el primer parámetro es null, debido a que la base de datos genera el id por si misma, debido a que le añadimos la opción de que sea autoincremental. y ponemos su respectiva línea para mostrar en consola.

nuevamente utilizamos el método mostrarTodos() para ver la base de datos con el método insertado.

ahora utilizamos el método modificar() para editar los valores del objeto insertado.

	DaoCliente.modificar(new Cliente(cliente.getId(), "Modificado", "Modificadez"));
	
Cabe decir que para poder modificar la misma línea que insertamos, reutilizamos el objeto de tipo Cliente que creamos para ejecutar la instrucción de obtenerPorId y luego redefinimos en la instrucción de insertar. Por eso, este objeto de tipo Cliente tiene el mismo id que el objeto insertado. Recordar que en el método insertar() retornamos un objeto Cliente al cual le insertamos el id generado por la tabla.

finalmente utilizamos el método borrar() y le pasamos de parámetro el id del mismo objeto Cliente que anteriormente modificamos.

	DaoCliente.borrar(cliente.getId());
	


## **Anexo:**
Crear nuestra propia EXCEPTION. 

	https://github.com/javierlete/java-2021-04/blob/master/BasesDeDatos/src/com/ipartek/formacion/jdbc/clientes/accesodatos/AccesoDatosException.java

Lo primero que hacemos es en un bloque try-catch, en el apartado catch agregar la linea

	throw new AccesoDatosException("No se han podido obtener todos los clientes", e);
	
Ahora nos saltaran errores por usar AccesoDatosException sin que esta clase exista, pero dentro de las opciones generadas por eclipse o VSC, nos dirán que creemos esta clase. Como el programa detecta que queremos hacer una excepción nos crea la clase con el herencia de exceptions (si no, nosotros la agregamos), pero necesitamos que esta clase herede de RuntimeException, por lo que lo cambiamos. Ahora le heredamos los constructores de la clase padre.
Finalmente agregamos nos dara la opcion de agregar el id

y con esto tenemos nuestra Excepción creada.

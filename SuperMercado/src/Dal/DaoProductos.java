package Dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Productos;


public class DaoProductos {

	// Variables de conexion

	private static String url = "jdbc:sqlite:dbsuper";
	private static String usuario = "";
	private static String password = "";

	// Sentencias SQL

	private static final String SQL_SELECT = "SELECT * FROM Productos";
	
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_SELECT_NOMBRE = SQL_SELECT + " WHERE Nombre = ?";
	private static final String SQL_SELECT_CATEGORIA = SQL_SELECT + " WHERE Categoria = ?";
	
	private static final String SQL_INSERT = "INSERT INTO Productos (Nombre, Categoria, Precio) VALUES (?,?,?)";
	
	private static final String SQL_UPDATE_ID = "UPDATE Productos SET Nombre = ?, Categoria = ?, Precio = ? where id = ?";
	private static final String SQL_UPDATE_NOMBRE = "UPDATE Productos SET Nombre = ?, Categoria = ?, Precio = ? where Nombre = ?";
	
	private static final String SQL_DELETE = "DELETE FROM Productos WHERE id = ?";

	// Metodos SQL
	public static ArrayList<Productos> obtenerTodos() {
		try (Connection con = RealizarConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Productos> productos = new ArrayList<>();
			while (rs.next()) {
				productos.add(new Productos(rs.getString("Nombre"), rs.getString("Categoria"), rs.getInt("id"), rs.getInt("Precio")));
			}
			return productos;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los producto", e);
		}
	}

	public static Productos obtenerPorId(Integer id) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Productos producto = null;
			if (rs.next()) {
				producto = new Productos(rs.getString("Nombre"), rs.getString("Categoria"), rs.getInt("id"), rs.getInt("Precio"));
			}
			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por id ha fallado", e);
		}
	}
	public static Productos obtenerPorNombre(String nombre) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_NOMBRE);) {
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			Productos producto = null;
			if (rs.next()) {
				producto = new Productos(rs.getString("Nombre"), rs.getString("Categoria"), rs.getInt("id"), rs.getInt("Precio"));
			}
			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por nombre ha fallado", e);
		}
	}
	public static ArrayList<Productos> obtenerPorCategoria(String categoria) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_CATEGORIA);) {
			ps.setString(1, categoria);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Productos> productos = new ArrayList<>();
			while (rs.next()) {
				productos.add(new Productos(rs.getString("Nombre"), rs.getString("Categoria"), rs.getInt("id"), rs.getInt("Precio")));
			}
			return productos;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por categoria ha fallado", e);
		}
	}

	public static Productos insertar(Productos producto) {
		try (Connection con = RealizarConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getCategoria());
			ps.setInt(3, producto.getPrecio());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			producto.setId(rs.getInt(1));

			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar cliente ha fallado", e);
		}
	}

	public static Productos modificarPorID(Productos producto) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ID);) {
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getCategoria());
			ps.setInt(3, producto.getPrecio());
			ps.setInt(4, producto.getId());

			ps.executeUpdate();

			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}
	public static Productos modificarPorNombre(Productos producto) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_NOMBRE);) {
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getCategoria());
			ps.setInt(3, producto.getPrecio());
			ps.setString(4, producto.getNombre());
			
			ps.executeUpdate();
			
			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}

	public static void borrar(Integer id) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}
	// Metodo De Conexion

	private static Connection RealizarConexion() throws AccesoDatosException {
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la conexion a tabla clientes", e);
		}
		return con;
	}

}

package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Producto;

public class DaoProductos {
	
	// Variables de conexion

	private static String usuario = "";
	private static String password = "";
	
	// Sentencias SQL
	private static final String SQL_SELECT = "SELECT * FROM productos";
	private static final String SQL_SELECT_NOMBRE =  "SELECT * FROM productos WHERE nombreProducto = ?";
	private static final String SQL_SELECT_ID =  "SELECT * FROM productos WHERE id = ?";
	private static final String SQL_SELECT_CATEGORIA =  "SELECT * FROM productos WHERE categoria = ?";

	private static final String SQL_INSERT = "INSERT INTO productos (nombreProducto, marca, proveedor,"
			+ " categoria, tipoDeMascota, edadDeMascota, descuento, existencia, caducidad, precio)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE = "UPDATE productos SET nombreProducto=?, marca=?, proveedor=?,"
			+ " categoria=?, tipoDeMascota=?, edadDeMascota=?, descuento=?, existencia=?, caducidad=?, precio=? "
			+ "WHERE id=?";
	
	private static final String SQL_DELETE_POR_ID = "DELETE FROM productos WHERE id = ?";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//Metodos SQL
	
	public static ArrayList<Producto> obtenerTodos(String db) {
		try (Connection con = RealizarConexion(db);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Producto> productos = new ArrayList<>();
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("id"), rs.getInt("descuento"),
						rs.getInt("existencia"), rs.getInt("caducidad"),  rs.getInt("precio"),
						rs.getString("nombreProducto"),	rs.getString("marca"), rs.getString("proveedor"),
						rs.getString("categoria"), rs.getString("tipoDeMascota"),
						rs.getString("edadDeMascota")));
				}
			return productos;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los productos", e);
		}
	}
	public static Producto obtenerPorNombre(String nombreProducto, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_NOMBRE);) {
			ps.setString(1, nombreProducto);
			ResultSet rs = ps.executeQuery();
			Producto producto = null;
			if (rs.next()) {
				producto = new Producto(rs.getInt("id"), rs.getInt("descuento"),
						rs.getInt("existencia"), rs.getInt("caducidad"),  rs.getInt("precio"),
						rs.getString("nombreProducto"),	rs.getString("marca"), rs.getString("proveedor"),
						rs.getString("categoria"), rs.getString("tipoDeMascota"),
						rs.getString("edadDeMascota"));
						}

			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por nombre ha fallado", e);
		}
	}
	public static Producto obtenerPorId(Integer id, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Producto producto = null;
			if (rs.next()) {
				producto =new Producto(rs.getInt("id"), rs.getInt("descuento"),
					rs.getInt("existencia"), rs.getInt("caducidad"),  rs.getInt("precio"),
					rs.getString("nombreProducto"),	rs.getString("marca"), rs.getString("proveedor"),
					rs.getString("categoria"), rs.getString("tipoDeMascota"),
					rs.getString("edadDeMascota"));
					}

			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por nombre ha fallado", e);
		}
	}
	
	public static ArrayList<Producto> obtenerPorCategoria(String categoria, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_CATEGORIA);) {
			ps.setString(1, categoria);
			ResultSet rs = ps.executeQuery();
			ArrayList<Producto> productos = new ArrayList<>();
			
			
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("id"), rs.getInt("descuento"),
						rs.getInt("existencia"), rs.getInt("caducidad"),  rs.getInt("precio"),
						rs.getString("nombreProducto"),	rs.getString("marca"), rs.getString("proveedor"),
						rs.getString("categoria"), rs.getString("tipoDeMascota"),
						rs.getString("edadDeMascota")));
				}
			return productos;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener producto por nombre ha fallado", e);
		}
	}

	public static void insertar(Producto producto,String db) {
		try (Connection con = RealizarConexion(db);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			
			ps.setString(1, producto.getNombreProducto());
			ps.setString(2, producto.getMarca());
			ps.setString(3, producto.getProveedor());
			ps.setString(4, producto.getCategoria());
			ps.setString(5, producto.getTipoDeMascota());
			ps.setString(6, producto.getEdadDeMascota());
			ps.setInt(7, producto.getDescuento());
			ps.setInt(8, producto.getExistencia());
			ps.setInt(9, producto.getCaducidad());
			ps.setInt(10, producto.getPrecio());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar producto ha fallado", e);
		}
	}
	public static Producto modificar(Producto producto, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			
			ps.setString(1, producto.getNombreProducto());
			ps.setString(2, producto.getMarca());
			ps.setString(3, producto.getProveedor());
			ps.setString(4, producto.getCategoria());
			ps.setString(5, producto.getTipoDeMascota());
			ps.setString(6, producto.getEdadDeMascota());
			ps.setInt(7, producto.getDescuento());
			ps.setInt(8, producto.getExistencia());
			ps.setInt(9, producto.getCaducidad());
			ps.setInt(10, producto.getPrecio());
			ps.setInt(11, producto.getId());

			
			ps.executeUpdate();
			return producto;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar producto ha fallado", e);
		}
	}

	public static void borrarPorId(Integer id, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_DELETE_POR_ID);) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar producto ha fallado", e);
		}
	}

	
	private static Connection RealizarConexion(String db) throws AccesoDatosException {
		String url = "jdbc:sqlite:" + db;
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la conexion a tabla productos", e);
		}
		return con;
	}
}

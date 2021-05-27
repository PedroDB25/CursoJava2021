package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Usuario;

public class DaoUsuarios {
	
	// Variables de conexion

	private static String usuario = "";
	private static String password = "";
	
	// Sentencias SQL
	private static final String SQL_SELECT = "SELECT * FROM usuario";
	private static final String SQL_SELECT_NOMBRE =  "SELECT * FROM usuario WHERE nombre = ?";
	private static final String SQL_SELECT_ID =  "SELECT * FROM usuario WHERE id = ?";
	private static final String SQL_SELECT_EMAIL =  "SELECT * FROM usuario  WHERE email = ?";
	private static final String SQL_SELECT_ROL =  "SELECT * FROM usuario WHERE rol = ?";
	
	private static final String SQL_INSERT = "INSERT INTO usuario (nombre, email, password, rol) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ? , email = ? , password =?  where id = ?";
	private static final String SQL_UPDATE_ROL = "UPDATE usuario SET rol = ? where id = ?";
	
	private static final String SQL_DELETE_POR_ID = "DELETE FROM usuario WHERE id = ?";
	private static final String SQL_DELETE_POR_NOMBRE = "DELETE FROM usuario WHERE nombre = ?";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//Metodos SQL
	
	public static ArrayList<Usuario> obtenerTodos(String db) {
		try (Connection con = RealizarConexion(db);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getString("rol")));
			}
			return usuarios;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los clientes", e);
		}
	}
	public static Usuario obtenerPorNombre(String nombre, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_NOMBRE);) {
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			Usuario usuario = null;
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getString("rol"));
				}

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener usuario por nombre ha fallado", e);
		}
	}
	public static Usuario obtenerPorId(Integer id, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Usuario usuario = null;
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getString("rol"));
				}

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener usuario por nombre ha fallado", e);
		}
	}
	public static Usuario obtenerPorMail(String email, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_EMAIL);) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			Usuario usuario = null;
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getString("rol"));
				}
			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener usuario por email ha fallado", e);
		}
	}
	
	
	public static ArrayList<Usuario> obtenerPorRol(String rol, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ROL);) {
			ps.setString(1, rol);
			ResultSet rs = ps.executeQuery();
			ArrayList<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getString("rol")));
			}
			return usuarios;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener usuario por email ha fallado", e);
		}
	}
	
	public static void insertar(Usuario usuario,String db) {
		try (Connection con = RealizarConexion(db);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setString(4, usuario.getRol());
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar usuario ha fallado", e);
		}
	}
	
	public static Usuario modificar(Usuario usuario, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, usuario.getNombre());
			ps.setInt(4, usuario.getId());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.executeUpdate();
			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}
	public static Usuario modificarRol(Usuario usuario, String db, String rol) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ROL);) {
			ps.setString(1, rol);
			ps.setInt(2, usuario.getId());
			ps.executeUpdate();
			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}

	public static void borrarPorId(Integer id, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_DELETE_POR_ID);) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}
	public static void borrarPorNombre(String nombre, String db) {
		try (Connection con = RealizarConexion(db); PreparedStatement ps = con.prepareStatement(SQL_DELETE_POR_NOMBRE);) {
			ps.setString(1, nombre);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}

	
	private static Connection RealizarConexion(String db) throws AccesoDatosException {
		String url = "jdbc:sqlite:" + db;
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la conexion a tabla clientes", e);
		}
		return con;
	}
}

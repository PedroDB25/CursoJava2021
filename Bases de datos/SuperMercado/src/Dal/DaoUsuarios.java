package Dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Usuario;

public class DaoUsuarios {

	// Variables de conexion

	private static String url = "jdbc:sqlite:dbsuper";
	private static String usuario = "";
	private static String password = "";

	// Sentencias SQL

	private static final String SQL_SELECT = "SELECT * FROM usuarios";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO usuarios (email, password, Nombre) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET email = ?, password = ?, Nombre = ? where id = ?";
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

	// Metodos SQL
	public static ArrayList<Usuario> obtenerTodos() {
		try (Connection con = RealizarConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("password"),
						rs.getString("Nombre")));

			}
			return usuarios;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los clientes", e);
		}
	}

	public static Usuario obtenerPorId(Integer id) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Usuario persona = null;
			if (rs.next()) {
				persona = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("password"),
						rs.getString("Nombre"));
			}

			return persona;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener persona por id ha fallado", e);
		}
	}

	public static Usuario insertar(Usuario usuario) {
		try (Connection con = RealizarConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getNombre());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			usuario.setId(rs.getInt(1));

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar cliente ha fallado", e);
		}
	}

	public static Usuario modificar(Usuario usuario) {
		try (Connection con = RealizarConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getNombre());
			ps.setInt(4, usuario.getId());

			ps.executeUpdate();

			return usuario;
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

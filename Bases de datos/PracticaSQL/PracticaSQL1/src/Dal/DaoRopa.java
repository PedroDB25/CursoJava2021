package src.Dal;

import src.Entidades.Ropa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoRopa {
	// Variables de acceso
	private static final String URL = "jdbc:sqlite:dbPrueba.db";
	private static final String USUARIO = "";
	private static final String PASSWORD = "";

	// Variables con Sentencias SQL
	private static final String SQL_SELECT = "SELECT * FROM Ropa";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_SELECT_PANTALON = SQL_SELECT + " WHERE Pantalon = ?";
	private static final String SQL_SELECT_POLERA = SQL_SELECT + " WHERE Polera = ?";
	private static final String SQL_INSERT = "INSERT INTO Ropa (Polera, Pantalon, PosicionX, PosicionY)  VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE Ropa SET Polera=?, Pantalon=?, PosicionX=?, PosicionY=? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM Ropa WHERE id = ?";

	public static ArrayList<Ropa> obtenerTodos() {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT);
				ResultSet rs = ps.executeQuery()) {

			ArrayList<Ropa> ropas = new ArrayList<>();

			while (rs.next()) {
				ropas.add(new Ropa(rs.getInt("id"), rs.getString("Polera"), rs.getString("Pantalon"),
						rs.getInt("PosicionX"), rs.getInt("PosicionY")));
			}

			return ropas;
		} catch (Exception e) {
			throw new AccesoDatosException("No se han podido obtener todos los clientes", e);
		}
	}

	public static Ropa obtenerPorId(Integer id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Ropa ropa = null;
			if (rs.next()) {
				ropa = new Ropa(rs.getInt("id"), rs.getString("Polera"), rs.getString("Pantalon"),
						rs.getInt("PosicionX"), rs.getInt("PosicionY"));
			}
			return ropa;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener cliente por id ha fallado", e);
		}
	}

	public static Ropa obtenerPorPantalonColor(String pantaloncolor) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_PANTALON);) {
			ps.setString(1, pantaloncolor);
			ResultSet rs = ps.executeQuery();
			Ropa ropa = null;
			if (rs.next()) {
				ropa = new Ropa(rs.getInt("id"), rs.getString("Polera"), rs.getString("Pantalon"),
						rs.getInt("PosicionX"), rs.getInt("PosicionY"));
			}
			return ropa;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener cliente por color de pantalon ha fallado", e);
		}
	}

	public static Ropa obtenerPorPoleraColor(String Polerancolor) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_POLERA);) {
			ps.setString(1, Polerancolor);
			ResultSet rs = ps.executeQuery();
			Ropa ropa = null;
			if (rs.next()) {
				ropa = new Ropa(rs.getInt("id"), rs.getString("Polera"), rs.getString("Pantalon"),
						rs.getInt("PosicionX"), rs.getInt("PosicionY"));
			}
			return ropa;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de obtener cliente por color de polera ha fallado", e);
		}
	}

	public static Ropa insertar(Ropa ropa) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, ropa.getPolera());
			ps.setString(2, ropa.getPantalones());
			ps.setInt(1, ropa.getPosicionX());
			ps.setInt(2, ropa.getPosicionY());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			ropa.setId(rs.getInt(1));
			return ropa;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de insertar ropa de persona ha fallado", e);
		}
	}

	public static Ropa modificar(Ropa ropa) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, ropa.getPolera());
			ps.setString(2, ropa.getPantalones());
			ps.setInt(3, ropa.getPosicionX());
			ps.setInt(4, ropa.getPosicionY());
			ps.setInt(5, ropa.getId());
			ps.executeUpdate();
			return ropa;
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de modificar cliente ha fallado", e);
		}
	}

	public static void borrar(Integer id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new AccesoDatosException("La operación de borrar cliente ha fallado", e);
		}
	}

	private static Connection obtenerConexion() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		} catch (Exception e) {
			throw new AccesoDatosException("Error en la conexión a clientes", e);
		}
		return con;
	}

}

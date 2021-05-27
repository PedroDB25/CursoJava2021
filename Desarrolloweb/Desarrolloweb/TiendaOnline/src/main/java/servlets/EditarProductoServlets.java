package servlets;

import static dal.DaoProductos.modificar;
import static dal.DaoProductos.obtenerPorId;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Producto;
import listener.Config;


@WebServlet("/admin/editarProducto")
public class EditarProductoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		if(id!=null) {
			Producto producto = obtenerPorId(Integer.parseInt(id), Config.db);
			request.setAttribute("producto", producto);
		}
		request.getRequestDispatcher("editarProducto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id= request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String marca= request.getParameter("marca");
		String proveedor = request.getParameter("proveedor");
		String categoria = request.getParameter("categoria");
		String tipoDeMascota= request.getParameter("tipoDeMascota");
		String edadDeMascota = request.getParameter("edadDeMascota");
		String descuento = request.getParameter("descuento");
		String existencia= request.getParameter("existencia");
		String caducidad = request.getParameter("caducidad");
		String precio = request.getParameter("precio");
		
		Producto nuevoproducto = new Producto(Integer.parseInt(id),Integer.parseInt(descuento),
				Integer.parseInt(existencia), Integer.parseInt(caducidad), Integer.parseInt(precio),
				nombre,marca,proveedor,categoria, tipoDeMascota, edadDeMascota);
		modificar(nuevoproducto, Config.db);
		
		request.getRequestDispatcher("listadoUsuarios").forward(request, response);
	}

}

package servlets;

import static dal.DaoProductos.obtenerPorId;
import static dal.DaoProductos.borrarPorId;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Producto;
import listener.Config;

@WebServlet("/admin/borrarProducto")
public class BorrarProductoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id != null) {
			Producto producto = obtenerPorId(Integer.parseInt(id), Config.db);
			request.setAttribute("producto", producto);
		}
		request.getRequestDispatcher("borrarProducto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			borrarPorId(Integer.parseInt(id), Config.db);

			request.getRequestDispatcher("listadoUsuarios").forward(request, response);

		}

	}
}

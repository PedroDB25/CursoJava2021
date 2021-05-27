package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static dal.DaoProductos.obtenerPorId;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Producto;
import listener.Config;

@WebServlet("/carrito")
public class CarritoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String carrito = (String) request.getSession().getAttribute("carrito");
		Integer totalAPagar=0;

		if (carrito != null && !carrito.equals("")) {
			ArrayList<String> listaCarrito = new ArrayList<String>(Arrays.asList(carrito.split(",")));
			ArrayList<Producto> listaProductos = new ArrayList<>();

			for (String id : listaCarrito) {
				if(id!="") {

				Integer idnumerico = Integer.parseInt(id);
				Producto inserto= obtenerPorId(idnumerico, Config.db);
				listaProductos.add(inserto);
				totalAPagar = totalAPagar + (inserto.getPrecio()*(100-inserto.getDescuento())/100);
			}
			}
			
			
		request.getSession().setAttribute("totalAPagar", totalAPagar);
		request.getSession().setAttribute("CarritoProductos", listaProductos);
		request.getRequestDispatcher("/carrito.jsp").forward(request, response);
		return;
		}else {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			totalAPagar=0;
			request.getSession().setAttribute("totalAPagar", totalAPagar);
			request.getSession().setAttribute("CarritoProductos", listaProductos);
			request.getRequestDispatcher("/carrito.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

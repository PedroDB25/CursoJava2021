package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrarcarrito")
public class BorrarCarritoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idProducto = request.getParameter("producto");
		String carrito, carritoauxiliar = "";
		int contador1 = 0, contador2=1;

		if (idProducto != null) {
			if (request.getSession().getAttribute("carrito") != null) {
				carrito = (String) request.getSession().getAttribute("carrito");
				ArrayList<String> listaCarrito = new ArrayList<String>(Arrays.asList(carrito.split(",")));
				for (String producto : listaCarrito) {
					if (producto.equals(idProducto)) {
						listaCarrito.remove(contador1);
						break;
					}
					contador1++;
				}
				if (listaCarrito.size() > 0) {
					carritoauxiliar = listaCarrito.get(0);
					
					if (listaCarrito.size() > 1) {
						for (String producto : listaCarrito) {
							if(producto=="") {
								continue;
							}
							carritoauxiliar = carritoauxiliar+ "," + producto;
							contador2++;
							if(contador2==listaCarrito.size()) {
								break;
							}

						}
					}
				}
				carrito=carritoauxiliar;
				request.getSession().setAttribute("carrito", carrito);
				response.sendRedirect("carrito");
				return;

			}
			response.sendRedirect("carrito");
			return;
		}
		response.sendRedirect("carrito");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

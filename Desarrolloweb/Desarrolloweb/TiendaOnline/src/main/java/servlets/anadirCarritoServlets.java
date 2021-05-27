package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/anadircarrito")
public class anadirCarritoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idProducto = request.getParameter("producto");
		String cantidad = request.getParameter("cantidad");
		String carrito;

		if (cantidad == "") {
			cantidad = "1";
		}
		Integer NCantidad = Integer.valueOf(cantidad);

		if (idProducto != null) {

			if (request.getSession().getAttribute("carrito") == null) {
				carrito = idProducto;
				for (int i = 1; i < NCantidad; i++) {
					carrito = carrito + "," + idProducto;
				}
			} else {
				carrito = (String) request.getSession().getAttribute("carrito");
				for (int i = 0; i < NCantidad; i++) {
					carrito = carrito + "," + idProducto;
				}
			}
			
			request.getSession().setAttribute("carrito", carrito);
			response.sendRedirect("inicio");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

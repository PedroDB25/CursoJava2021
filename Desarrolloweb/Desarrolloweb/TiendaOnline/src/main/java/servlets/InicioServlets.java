package servlets;

import static dal.DaoProductos.obtenerTodos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.DaoProductos;
import entidad.Producto;
import listener.Config;

@WebServlet("/inicio")
public class InicioServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Producto> listaproductos = null;
		String seleccion = request.getParameter("seleccion");

		
		if(seleccion!=null) {
		switch (seleccion) {
		case "animal": {
			listaproductos=DaoProductos.obtenerPorCategoria(seleccion, Config.db);
			
		}
		case "accesorio": {
			listaproductos=DaoProductos.obtenerPorCategoria(seleccion, Config.db);
			
		}
		case "comida": {
			listaproductos=DaoProductos.obtenerPorCategoria(seleccion, Config.db);

		}
		case "juguetes": {
			listaproductos=DaoProductos.obtenerPorCategoria(seleccion, Config.db);
			
		}
		}}else {
			listaproductos = obtenerTodos(Config.db);
		}
		
			
		
		request.getSession().setAttribute("productos", listaproductos);
		response.sendRedirect("inicio.jsp");
		
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

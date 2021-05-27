	package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.DaoProductos;
import dal.DaoUsuarios;
import entidad.Producto;
import entidad.Usuario;
import listener.Config;

@WebServlet("/admin/listadoUsuarios")
public class ListadoUsuarioServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Usuario> listausuarios = DaoUsuarios.obtenerTodos(Config.db);
		request.getSession().setAttribute("usuarios", listausuarios);
		
		ArrayList<Producto> listaproductos = DaoProductos.obtenerTodos(Config.db);
		request.getSession().setAttribute("productos", listaproductos);
		
		
		response.sendRedirect("listadoUsuarios.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

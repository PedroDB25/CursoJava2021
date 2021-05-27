package servlets;

import static dal.DaoUsuarios.insertar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import listener.Config;


@WebServlet("/admin/anadir")
public class AnadirServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("anadir.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Usuario inserto = new Usuario(nombre, email, password);
		
		insertar(inserto, Config.db);
		
		request.getRequestDispatcher("listadoUsuarios").forward(request, response);
	}

}

package servlets;

import static Dal.DaoUsuarios.insertar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;


@WebServlet("/anadir")
public class AnadirServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("anadir.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String nombre = request.getParameter("nombre");
		insertar(nombre, db);
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}

}

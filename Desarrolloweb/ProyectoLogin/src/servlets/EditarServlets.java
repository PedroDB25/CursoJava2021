package servlets;

import static Dal.DaoUsuarios.modificar;
import static Dal.DaoUsuarios.obtenerPorId;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;


@WebServlet("/editar")
public class EditarServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String id= request.getParameter("id");
		if(id!=null) {
			Usuario usuario = obtenerPorId(Integer.parseInt(id), db);
			request.setAttribute("usuario", usuario);
		}
		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String id= request.getParameter("id");
		String nombre = request.getParameter("nombre");
		Usuario nuevoUsuario = new Usuario(Integer.parseInt(id), nombre);
		modificar(nuevoUsuario, db);
		
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}

}

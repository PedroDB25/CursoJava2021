package servlets;

import static Dal.DaoUsuarios.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;

@WebServlet("/borrar")
public class BorrarServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String id= request.getParameter("id");
		if(id!=null) {
			Usuario usuario = obtenerPorId(Integer.parseInt(id), db);
			request.setAttribute("usuario", usuario);
		}
		request.getRequestDispatcher("borrar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String id= request.getParameter("id");
		if(id!=null) {
			borrarPorId(Integer.parseInt(id), db);
			
			request.getRequestDispatcher("Admin.jsp").forward(request, response);
		
		
		}
		
		
		
	}

}

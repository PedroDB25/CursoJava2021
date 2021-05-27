package servlets;

import static dal.DaoUsuarios.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import listener.Config;


@WebServlet("/admin/cambiar")
public class CambiarRolServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Usuario usuario = null;
		
		if(id!=null) {
			usuario = obtenerPorId(Integer.parseInt(id), Config.db);
		}
		String rol = usuario.getRol();
		
		if (rol.equals("admin")){
			String nuevoRol="user";
			modificarRol(usuario, Config.db, nuevoRol);	
		}
		else if (rol.equals("user")){
			String nuevoRol="admin";
			modificarRol(usuario, Config.db, nuevoRol);
		}
		request.getRequestDispatcher("listadoUsuarios").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package servlets;

import static dal.DaoUsuarios.modificar;
import static dal.DaoUsuarios.obtenerPorId;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import listener.Config;


@WebServlet("/admin/editar")
public class EditarServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		if(id!=null) {
			Usuario usuario = obtenerPorId(Integer.parseInt(id), Config.db);
			request.setAttribute("usuario", usuario);
		}
		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String password= request.getParameter("password");
		String email = request.getParameter("email");
		
		Usuario nuevoUsuario = new Usuario(Integer.parseInt(id),nombre,email,password,null);
		modificar(nuevoUsuario, Config.db);
		
		request.getRequestDispatcher("listadoUsuarios").forward(request, response);
	}

}
	
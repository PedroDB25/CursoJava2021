package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import listener.Config;

@WebServlet("/registro")
public class RegistroServlets extends HttpServlet {
	String horaFin = null;
	String horaInicio = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String usuario = request.getParameter("nombre");
		String password = request.getParameter("password");

		Usuario UsuarioBuscado = dal.DaoUsuarios.obtenerPorNombre(usuario, Config.db);

		if (UsuarioBuscado != null) {

			request.setAttribute("error", "Sus datos ya tienen usuario");
			response.sendRedirect("inicio.jsp");

		} else {
			UsuarioBuscado = dal.DaoUsuarios.obtenerPorMail(email, Config.db);
			if (UsuarioBuscado != null) {

				request.setAttribute("error", "Sus datos ya tienen email");
				response.sendRedirect("inicio.jsp");
			} else {
				
				Usuario nuevo = new Usuario(usuario, email, password);

				dal.DaoUsuarios.insertar(nuevo, Config.db);
				request.setAttribute("error", "Se ha registrado su usuario.");

				response.sendRedirect("login.jsp");

			}

		}

	}
}

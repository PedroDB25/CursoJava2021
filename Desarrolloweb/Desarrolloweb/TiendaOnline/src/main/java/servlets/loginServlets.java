	package servlets;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import listener.Config;

@WebServlet("/login")
public class loginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String horaInicio;
	String horaFin;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String dato = request.getParameter("usuario");
		String password = request.getParameter("password");

		Usuario UsuarioBuscado = dal.DaoUsuarios.obtenerPorMail(dato, Config.db);
		

		if (UsuarioBuscado != null && UsuarioBuscado.getPassword().equals(password)) {
			horaMetodo();
			request.getSession().setAttribute("horaInicio", horaInicio);
			request.getSession().setAttribute("horaFin", horaFin);
			request.getSession().setAttribute("usuario", UsuarioBuscado);
			request.getSession().setAttribute("Mensaje", "Hola otra vez");

			response.sendRedirect("inicio");
		} else {
			UsuarioBuscado = dal.DaoUsuarios.obtenerPorNombre(dato, Config.db);
			if (UsuarioBuscado != null && UsuarioBuscado.getPassword().equals(password)) {
				horaMetodo();
				request.getSession().setAttribute("horaInicio", horaInicio);
				request.getSession().setAttribute("horaFin", horaFin);
				request.getSession().setAttribute("usuario", UsuarioBuscado);
				request.getSession().setAttribute("Mensaje", "Hola otra vez");

				response.sendRedirect("inicio");
			} else {
				request.setAttribute("error", "No tenemos a su usuario registrado, revise los datos introducidos.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}

	}

	private void horaMetodo() {
		if ((int) LocalTime.now().getMinute() < 10) {
			horaInicio = LocalTime.now().getHour() + ":0" + LocalTime.now().getMinute();
		} else {
			horaInicio = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
		}

		if ((int) LocalTime.now().getMinute() >= 30) {
			if (((int) (LocalTime.now().getMinute()) - 30) < 10) {
				horaFin = ((int) LocalTime.now().getHour() + 1) + ":0"
						+ ((int) (LocalTime.now().getMinute()) - 30);
			} else {
				horaFin = ((int) LocalTime.now().getHour() + 1) + ":"
						+ ((int) (LocalTime.now().getMinute()) - 30);
			}
		} else {
			horaFin = LocalTime.now().getHour() + ":" + ((int) (LocalTime.now().getMinute()) + 30);
		}
	}

}

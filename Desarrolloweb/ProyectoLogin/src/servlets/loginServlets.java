package servlets;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;

@WebServlet("/login")
public class loginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String db = getServletContext().getRealPath("/WEB-INF/db.db");
		String usuario= request.getParameter("usuario");
		
		Usuario UsuarioBuscado =Dal.DaoUsuarios.obtenerPorNombre(usuario, db);
		String horaFin = null;
		
		if (UsuarioBuscado != null) {
			String horaInicio = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
			
			if((int) LocalTime.now().getMinute() >=30) {
				if(((int)(LocalTime.now().getMinute()) - 30) <10) {
					horaFin = ((int)LocalTime.now().getHour() + 1) + ":0" + ((int)(LocalTime.now().getMinute()) - 30);
				}else {
				horaFin = ((int)LocalTime.now().getHour() + 1) + ":" + ((int)(LocalTime.now().getMinute()) - 30);
				}
			}else {
				horaFin = LocalTime.now().getHour() + ":" + ((int)(LocalTime.now().getMinute()) + 30);
			}
			
			
			request.getSession().setAttribute("horaInicio", horaInicio);
			request.getSession().setAttribute("horaFin", horaFin);
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("Mensaje", "Hola otra vez");
			
			response.sendRedirect("Inicio.jsp");
		}else {
			request.setAttribute("error", "No tenemos a su usuario registrado");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
	}

}



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calculadoraCerebro")
public class calculadoraCerebro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("calculadoramvc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultado = "";
		int ope = 0;
		Double ope1 = 0.0;
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");

		if (!(n1 == null) || !(n2 == null)) {

			if (request.getParameter("operacion").equals("1")) {
				ope1 = Math.sin(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("2")) {
				ope1 = Math.cos(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("3")) {
				ope1 = Math.tan(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("4")) {
				ope1 = Math.atan(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
			if (request.getParameter("operacion").equals("5")) {
				ope1 = Math.asin(Double.valueOf(n1));
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("6")) {
				ope1 = Math.acos(Double.valueOf(n1));
				resultado = String.valueOf(ope1);
			}
		}
		if (!(n1 == null) && !(n2 == null)) {

			if (request.getParameter("operacion").equals("1")) {
				ope = Integer.parseInt(n1) + Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("2")) {
				ope = Integer.parseInt(n1) - Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("3")) {
				ope = Integer.parseInt(n1) * Integer.parseInt(n2);
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("4")) {
				if (!(Integer.parseInt(n2) == 0)) {
					ope = Integer.parseInt(n1) / Integer.parseInt(n2);
					resultado = String.valueOf(ope);
				} else {
					resultado = "no dividas por 0";
				}
			}
			if (request.getParameter("operacion").equals("5")) {
				ope = (int) Math.pow(Integer.parseInt(n1), Integer.parseInt(n2));
				resultado = String.valueOf(ope);
			}
			if (request.getParameter("operacion").equals("6")) {
				ope1 = Integer.parseInt(n1) * Integer.parseInt(n2) * 0.01;
				resultado = String.valueOf(ope1);
			}
		}
		
		request.setAttribute("resultado", resultado);
		request.getRequestDispatcher("calculadoramvc.jsp").forward(request, response);
		

	}

}

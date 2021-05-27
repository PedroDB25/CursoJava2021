package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;

@WebServlet("/factura")
public class FacturaServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
		if (cliente==null) {
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}
		
		request.setAttribute("cliente", cliente);
		
		
		Integer total= (Integer) request.getSession().getAttribute("totalAPagar");
		
		
		Float PagoIva = (float) (total*0.21);
		Float TotalMasIva= total + PagoIva;
		
		request.setAttribute("PagoIva", PagoIva);
		request.setAttribute("TotalMasIva", TotalMasIva);
		
		request.getRequestDispatcher("plantillaFactura.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

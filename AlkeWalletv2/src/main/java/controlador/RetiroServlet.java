package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuentaDAO;
import modelo.Cuenta;
import modelo.Usuario;

/**
 * Servlet implementation class RetiroServlet
 */
@WebServlet("/retiro")
public class RetiroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaDAO cuentaDAO = new CuentaDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			int cuentaId = usuario.getCuenta().getId();
			double monto = Double.parseDouble(request.getParameter("monto"));

			Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);

			if (cuenta != null) {
				if (cuenta.getSaldo() >= monto) { // Verificar si hay saldo suficiente
					double nuevoSaldo = cuenta.getSaldo() - monto;
					boolean actualizado = cuentaDAO.actualizarSaldo(cuentaId, nuevoSaldo);

					if (actualizado) {
						cuenta.setSaldo(nuevoSaldo);
						usuario.setCuenta(cuenta);
						session.setAttribute("usuario", usuario);

						response.sendRedirect("home?retiro=exitoso");
					} else {
						response.sendRedirect("retiro?error=1");
					}
				} else {
					response.sendRedirect("retiro?error=2"); // Saldo insuficiente
				}
			} else {
				response.sendRedirect("retiro?error=3"); // Cuenta no encontrada
			}
		} else {
			response.sendRedirect("login");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/retiro.jsp").forward(request, response);
	}
}

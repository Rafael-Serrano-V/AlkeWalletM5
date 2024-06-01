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
 * Servlet implementation class TransferenciaServlet
 */
@WebServlet("/transferencia")
public class TransferenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaDAO cuentaDAO = new CuentaDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			int cuentaId = usuario.getCuenta().getId();
			int cuentaDestinoId = Integer.parseInt(request.getParameter("cuentaDestino"));
			double monto = Double.parseDouble(request.getParameter("monto"));

			Cuenta cuentaOrigen = cuentaDAO.obtenerCuentaPorId(cuentaId);
			Cuenta cuentaDestino = cuentaDAO.obtenerCuentaPorId(cuentaDestinoId);

			if (cuentaOrigen != null && cuentaDestino != null) {
				if (cuentaOrigen.getSaldo() >= monto) { // Verificar si hay saldo suficiente
					double nuevoSaldoOrigen = cuentaOrigen.getSaldo() - monto;
					double nuevoSaldoDestino = cuentaDestino.getSaldo() + monto;

					boolean actualizadoOrigen = cuentaDAO.actualizarSaldo(cuentaId, nuevoSaldoOrigen);
					boolean actualizadoDestino = cuentaDAO.actualizarSaldo(cuentaDestinoId, nuevoSaldoDestino);

					if (actualizadoOrigen && actualizadoDestino) {
						cuentaOrigen.setSaldo(nuevoSaldoOrigen);
						cuentaDestino.setSaldo(nuevoSaldoDestino);
						usuario.setCuenta(cuentaOrigen);
						session.setAttribute("usuario", usuario);

						response.sendRedirect("home?transferencia=exitosa");
					} else {
						response.sendRedirect("transferencia?error=1");
					}
				} else {
					response.sendRedirect("transferencia?error=2"); // Saldo insuficiente
				}
			} else {
				response.sendRedirect("transferencia?error=3"); // Cuenta destino no encontrada
			}
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/transferencia.jsp").forward(request, response);
	}
}

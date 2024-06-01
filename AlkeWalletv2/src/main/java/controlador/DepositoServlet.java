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
 * Servlet implementation class DepositoServlet
 */
@WebServlet("/deposito")
public class DepositoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaDAO cuentaDAO = new CuentaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            int cuentaId = usuario.getCuenta().getId();
            double monto = Double.parseDouble(request.getParameter("monto"));

            if (monto < 2000) {
                // Si el monto es menor o igual a 2000, redirige con un mensaje de error.
                response.sendRedirect("deposito?error=3");
                return;
            }

            Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);

            if (cuenta != null) {
                double nuevoSaldo = cuenta.getSaldo() + monto;
                boolean actualizado = cuentaDAO.actualizarSaldo(cuentaId, nuevoSaldo);

                if (actualizado) {
                    cuenta.setSaldo(nuevoSaldo);
                    usuario.setCuenta(cuenta);
                    session.setAttribute("usuario", usuario);
                    response.sendRedirect("home?deposito=exitoso");
                } else {
                    response.sendRedirect("deposito?error=1");
                }
            } else {
                response.sendRedirect("deposito?error=2");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/deposito.jsp").forward(request, response);
    }
}

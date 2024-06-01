package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import modelo.Usuario;


@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre"); // gabriel
		String email = request.getParameter("email"); //gabriel@gamil.com
		String password = request.getParameter("password"); //1234
		
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		boolean registroExitoso = usuarioDAO.registrarUsuario(usuario);
		
		if(registroExitoso) { // true == insertar un cliente o false= fallo
			response.sendRedirect("login");
		}else {
			response.sendRedirect("registro");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
	}

}

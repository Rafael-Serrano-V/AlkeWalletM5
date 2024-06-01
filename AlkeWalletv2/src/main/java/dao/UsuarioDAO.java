package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.BDConexion;
import modelo.Cuenta;
import modelo.Usuario;

public class UsuarioDAO {
	
	private Connection conexion;
	
	public UsuarioDAO() {
		try {
			conexion = BDConexion.getConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para registrar un nuevo usuario
	public boolean registrarUsuario(Usuario usuario) {
		
		//Connection conexion = BDConexion.getConexion();
		try {
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			int filasInsertadas = ps.executeUpdate();
			
			if(filasInsertadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int usuarioId = rs.getInt(1);
					usuario.setId(usuarioId);
					
					//Crear una cuenta para el nuevo usuario
					PreparedStatement psCuenta = conexion.prepareStatement("INSERT INTO cuentas (usuario_id, saldo) VALUES (?, ?)");
                    psCuenta.setInt(1, usuarioId);
                    psCuenta.setDouble(2, 0.0);
                    int cuentaInsertada = psCuenta.executeUpdate();
                    return cuentaInsertada > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Método para obtener un usuario por su ID
	public Usuario obtenerUsuarioPorId(int id) {
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setEmail(rs.getString("email"));
				usuario.setPassword(rs.getString("password"));
				
				//Obtener la cuenta del usuario
				CuentaDAO cuentaDAO = new CuentaDAO();
				Cuenta cuenta = cuentaDAO.obtenerCuentaPorUsuarioId(usuario.getId());
				usuario.setCuenta(cuenta);
				
				return usuario;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Método para el login de usuario
	public Usuario loginUsuario(String email, String password) {
		try {
			String consulta = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setEmail(rs.getString("email"));
				usuario.setPassword(rs.getString("password"));
				
				//Obtener la cuenta del usuario
				CuentaDAO cuentaDAO = new CuentaDAO();
				Cuenta cuenta = cuentaDAO.obtenerCuentaPorUsuarioId(usuario.getId());
				usuario.setCuenta(cuenta);
				
				return usuario;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

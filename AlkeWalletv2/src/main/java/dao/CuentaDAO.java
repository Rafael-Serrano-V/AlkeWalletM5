package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.BDConexion;
import modelo.Cuenta;

public class CuentaDAO {

	private Connection conexion;

	public CuentaDAO() {
		try {
			conexion = BDConexion.getConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Cuenta obtenerCuentaPorUsuarioId(int usuarioId) {
		try {
			String consulta = "SELECT * FROM cuentas WHERE usuario_id = ?";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setInt(1, usuarioId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rs.getInt("id"));
				cuenta.setUsuarioId(rs.getInt("usuario_id"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				return cuenta;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean actualizarSaldo(int cuentaId, double nuevoSaldo) {
		try {
			String consulta = "UPDATE cuentas SET saldo = ? WHERE id = ?";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setDouble(1, nuevoSaldo);
			ps.setInt(2, cuentaId);
			int filasActualizadas = ps.executeUpdate();
			return filasActualizadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean crearCuenta(Cuenta cuenta) {
		try {
			String consulta = "INSERT INTO cuentas (usuario_id, saldo) VALUES (?, ?)";
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setInt(1, cuenta.getUsuarioId());
			ps.setDouble(2, cuenta.getSaldo() );
   			int filasInsertadas = ps.executeUpdate();
			return filasInsertadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Cuenta obtenerCuentaPorId(int cuentaId) {
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM cuentas WHERE id = ?");
			ps.setInt(1, cuentaId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Cuenta cuenta =  new Cuenta();
				cuenta.setId(rs.getInt("id"));
				cuenta.setUsuarioId(rs.getInt("usuario_id"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				return cuenta;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

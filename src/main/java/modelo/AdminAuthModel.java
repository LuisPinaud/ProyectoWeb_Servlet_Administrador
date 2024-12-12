package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Adminstradores;
import interfaces.AdminAuthInterface;
import util.MySqlConexion;

public class AdminAuthModel implements AdminAuthInterface {

	@Override
	public Adminstradores authAdmin(String Email, String contrasenia) {
		// TODO Auto-generated method stub
		Adminstradores admin = null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String mysql = "SELECT * FROM ADMINISTRADORES WHERE EMAIL=? AND CONTRASENIA=?";
			psmt = cn.prepareStatement(mysql);
			psmt.setString(1, Email);
			psmt.setString(2, contrasenia);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				admin = new Adminstradores();
				admin.setIDAdmin(rs.getInt("IDAdmin"));
				admin.setNombreAdmin(rs.getString("NombreAdmin"));
				admin.setApellidoAdmin(rs.getString("ApellidoAdmin"));
				admin.setEmail(rs.getString("Email"));
				admin.setContrasenia(rs.getString("Contrasenia"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) rs.close();
				if (psmt!=null) psmt.close();
				if (cn!=null) cn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return admin;
	}

	@Override
	public Adminstradores getAdmin(String Email) {
		// TODO Auto-generated method stub
		return null;
	}

}

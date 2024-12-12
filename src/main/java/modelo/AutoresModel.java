package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Autores;
import interfaces.AutoresInterface;
import util.MySqlConexion;

public class AutoresModel implements AutoresInterface {

	@Override
	public List<Autores> ListAutores() {
		// TODO Auto-generated method stub
		List<Autores> listAutores = new ArrayList<Autores>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM AUTORES";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Autores au = new Autores();
				au.setIDAutor(rs.getInt("IDAutor"));
				au.setNombreAutor(rs.getString("NombreAutor"));
				au.setApellidoAutor(rs.getString("ApellidoAutor"));
				listAutores.add(au);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listAutores;
	}

	@Override
	public int createAutor(Autores autor) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			
			String sql = "INSERT INTO Autores (NombreAutor, ApellidoAutor) VALUES (?,?)";
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, autor.getNombreAutor());
			psm.setString(2,autor.getApellidoAutor());
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		return 0;
	}

	@Override
	public Autores getAutor(int id) {
		// TODO Auto-generated method stub
		Autores autor = null;
		Connection cn=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM AUTORES WHERE IDAutor=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			while (rs.next()) {
				autor = new Autores();
				autor.setIDAutor(rs.getInt("IDAutor"));
				autor.setNombreAutor(rs.getString("NombreAutor"));
				autor.setApellidoAutor(rs.getString("ApellidoAutor"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return autor;
	}

	@Override
	public int updateAutor(Autores autor) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			
			String sql = "UPDATE Autores SET NombreAutor=?, ApellidoAutor=? WHERE IDAutor=?";
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, autor.getNombreAutor());
			psm.setString(2,autor.getApellidoAutor());
			psm.setInt(3, autor.getIDAutor());
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		return 0;
	}

	@Override
	public int deleteAutor(int id) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			
			String sql = "drop Autores where IDAutor=?";
			psm = cn.prepareStatement(sql);
			
			psm.setInt(1, id);
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		return 0;
	}

}

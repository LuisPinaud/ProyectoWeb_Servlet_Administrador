package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Editorial;
import interfaces.EditorialInterface;
import util.MySqlConexion;

public class EditorialModel implements EditorialInterface {

	@Override
	public List<Editorial> ListEditoriales() {
		// TODO Auto-generated method stub
		List<Editorial> listEditoriales = new ArrayList<Editorial>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM EDITORIAL";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Editorial ed = new Editorial();
				ed.setIDEditorial(rs.getInt("IDEditorial"));
				ed.setNombreEditorial(rs.getString("NombreEditorial"));
				listEditoriales.add(ed);
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
		
		return listEditoriales;
	}

	@Override
	public int createEditorial(Editorial editorial) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn=MySqlConexion.getConexion();
			String sql = "INSERT INTO Editorial (NombreEditorial) VALUES (?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1, editorial.getNombreEditorial());
			value = psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public Editorial getEditorial(int id) {
		// TODO Auto-generated method stub
		Editorial editorial = null;
		Connection cn=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "select * from editorial where IDEditorial=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			while (rs.next()) {
				editorial = new Editorial();
				editorial.setIDEditorial(rs.getInt("IDEditorial"));
				editorial.setNombreEditorial(rs.getString("NombreEditorial"));
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
		
		return editorial;
	}

	@Override
	public int updateEditorial(Editorial editorial) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn=MySqlConexion.getConexion();
			String sql = "update editoriales set NombreEditorial=? where IDEditorial=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, editorial.getNombreEditorial());
			psm.setInt(2, editorial.getIDEditorial());
			value = psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int deleteEditorial(int id) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn=MySqlConexion.getConexion();
			String sql = "drop editorial where IDEditorial=?";
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
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return 0;
	}

}

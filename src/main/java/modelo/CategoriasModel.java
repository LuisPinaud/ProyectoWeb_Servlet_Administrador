package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Categorias;
import interfaces.CategoriasInterface;
import util.MySqlConexion;

public class CategoriasModel implements CategoriasInterface {

	@Override
	public List<Categorias> ListCategorias() {
		// TODO Auto-generated method stub
		List<Categorias> listCategorias = new ArrayList<Categorias>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM CATEGORIAS";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Categorias cat = new Categorias();
				cat.setIDCategoria(rs.getInt("IDCategoria"));
				cat.setNombreCategoria(rs.getString("NombreCategoria"));
				listCategorias.add(cat);
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
		
		return listCategorias;
	}

	@Override
	public int createCategoria(Categorias categoria) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "INSERT INTO Categorias (NombreCategoria) VALUES (?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1, categoria.getNombreCategoria());
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
	public Categorias getCategoria(int id) {
		// TODO Auto-generated method stub
		Categorias categoria = null;
		Connection cn=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM CATEGORIAS WHERE IDCategoria=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			while (rs.next()) {
				categoria = new Categorias();
				categoria.setIDCategoria(rs.getInt("IDCategoria"));
				categoria.setNombreCategoria(rs.getString("NombreCategoria"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return categoria;
	}

	@Override
	public int updateCategoria(Categorias categoria) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "UPDATE Categorias SET NombreCategoria = ? WHERE IDCategoria = ?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, categoria.getNombreCategoria());
			psm.setInt(2, categoria.getIDCategoria());
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
	public int deleteCategoria(int id) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "drop Categorias where IDCategoria=?";
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

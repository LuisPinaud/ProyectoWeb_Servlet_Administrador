package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Inventario;
import interfaces.InventarioInteface;
import util.MySqlConexion;

public class InventarioModel implements InventarioInteface {

	@Override
	public List<Inventario> listInventario() {
		// TODO Auto-generated method stub
		List<Inventario> listInventario = new ArrayList<Inventario>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT L.Titulo, L.Stock, CASE WHEN L.Stock > 0 THEN 'En Stock' ELSE 'Fuera de Stock' END AS Estado FROM Libros L;";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Inventario inventario = new Inventario();
				inventario.setTitulo(rs.getString("Titulo"));
				inventario.setStock(rs.getInt("Stock"));
				inventario.setEstado(rs.getString("Estado"));
				listInventario.add(inventario);
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
		
		return listInventario;
	}

}

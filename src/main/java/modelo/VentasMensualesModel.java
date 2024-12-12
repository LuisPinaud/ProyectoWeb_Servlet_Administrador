package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.VentasMensuales;
import interfaces.VentasMensualesInterface;
import util.MySqlConexion;

public class VentasMensualesModel implements VentasMensualesInterface {

	@Override
	public List<VentasMensuales> listVentasMensuales(int mes, int anio) {
		// TODO Auto-generated method stub
		List<VentasMensuales> listVentas = new ArrayList<VentasMensuales>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "CALL SP_ObtenerLibrosVendidosPorMes(?, ?)";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, mes);
			psm.setInt(2, anio);
			rs = psm.executeQuery();
			
			while (rs.next()) {
				VentasMensuales vm = new VentasMensuales();
				vm.setISBN(rs.getString("ISBN"));
				vm.setTitulo(rs.getString("Titulo"));
				vm.setAutor(rs.getString("Autor"));
				vm.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
				vm.setFechaVenta(rs.getString("FechaVenta"));
				vm.setTotalVendidos(rs.getInt("TotalVendidos"));
				vm.setMontoTotalVenta(rs.getDouble("MontoTotalVendido"));
				listVentas.add(vm);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} try {
			
		} finally {
			// TODO: handle finally clause
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listVentas;
	}

}

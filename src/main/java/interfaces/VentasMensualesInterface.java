package interfaces;

import java.util.List;

import entidades.VentasMensuales;

public interface VentasMensualesInterface {
	public List<VentasMensuales> listVentasMensuales(int mes, int anio);
}

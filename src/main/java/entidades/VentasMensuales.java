package entidades;

public class VentasMensuales {
	public String ISBN,Titulo,Autor,FechaVenta;
	public int TotalVendidos;
	public double PrecioUnitario,MontoTotalVenta;
	
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public String getFechaVenta() {
		return FechaVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		FechaVenta = fechaVenta;
	}
	public int getTotalVendidos() {
		return TotalVendidos;
	}
	public void setTotalVendidos(int totalVendidos) {
		TotalVendidos = totalVendidos;
	}
	public double getPrecioUnitario() {
		return PrecioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		PrecioUnitario = precioUnitario;
	}
	public double getMontoTotalVenta() {
		return MontoTotalVenta;
	}
	public void setMontoTotalVenta(double montoTotalVenta) {
		MontoTotalVenta = montoTotalVenta;
	}
}

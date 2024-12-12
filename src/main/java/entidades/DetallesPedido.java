package entidades;

public class DetallesPedido {
	public int IDPedido, Cantidad;
	public double Precio;
	public String ISBN;
	
	public int getIDPedido() {
		return IDPedido;
	}
	public void setIDPedido(int iDPedido) {
		IDPedido = iDPedido;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}

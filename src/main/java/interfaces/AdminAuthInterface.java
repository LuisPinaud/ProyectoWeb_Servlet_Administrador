package interfaces;

import entidades.Adminstradores;

public interface AdminAuthInterface {
	public Adminstradores authAdmin(String Email, String contrasenia);
	public Adminstradores getAdmin(String Email);
}

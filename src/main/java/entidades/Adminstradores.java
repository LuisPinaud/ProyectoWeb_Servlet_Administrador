package entidades;

public class Adminstradores {
	public int IDAdmin;
	public String NombreAdmin, ApellidoAdmin, Email, Contrasenia;
	
	public int getIDAdmin() {
		return IDAdmin;
	}
	public void setIDAdmin(int iDAdmin) {
		IDAdmin = iDAdmin;
	}
	public String getNombreAdmin() {
		return NombreAdmin;
	}
	public void setNombreAdmin(String nombreAdmin) {
		NombreAdmin = nombreAdmin;
	}
	public String getApellidoAdmin() {
		return ApellidoAdmin;
	}
	public void setApellidoAdmin(String apellidoAdmin) {
		ApellidoAdmin = apellidoAdmin;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getContrasenia() {
		return Contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	
}

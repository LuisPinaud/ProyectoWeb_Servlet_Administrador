package dao;

import interfaces.AdminAuthInterface;
import interfaces.AutoresInterface;
import interfaces.CategoriasInterface;
import interfaces.EditorialInterface;
import interfaces.InventarioInteface;
import interfaces.LibrosInterface;
import interfaces.VentasMensualesInterface;
import modelo.AdminAuthModel;
import modelo.AutoresModel;
import modelo.CategoriasModel;
import modelo.EditorialModel;
import modelo.InventarioModel;
import modelo.LibrosModel;
import modelo.VentasMensualesModel;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public CategoriasInterface getCategorias() {
		// TODO Auto-generated method stub
		return new CategoriasModel();
	}

	@Override
	public AutoresInterface getAutores() {
		// TODO Auto-generated method stub
		return new AutoresModel();
	}

	@Override
	public EditorialInterface getEditorial() {
		// TODO Auto-generated method stub
		return new EditorialModel();
	}

	@Override
	public LibrosInterface getLibros() {
		// TODO Auto-generated method stub
		return new LibrosModel();
	}

	@Override
	public AdminAuthInterface getAdminAuth() {
		// TODO Auto-generated method stub
		return new AdminAuthModel();
	}

	@Override
	public InventarioInteface getInventario() {
		// TODO Auto-generated method stub
		return new InventarioModel();
	}

	@Override
	public VentasMensualesInterface getVentasMensuales() {
		// TODO Auto-generated method stub
		return new VentasMensualesModel();
	}

}

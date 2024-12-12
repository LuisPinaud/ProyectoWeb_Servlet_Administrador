package interfaces;

import java.util.List;

import entidades.Categorias;

public interface CategoriasInterface {
	public List<Categorias> ListCategorias();
	public int createCategoria (Categorias categoria);
	public Categorias getCategoria (int id);
	public int updateCategoria (Categorias categoria);
	public int deleteCategoria(int id);
}

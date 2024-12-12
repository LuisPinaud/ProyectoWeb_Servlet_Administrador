package interfaces;

import java.util.List;

import entidades.Libros;

public interface LibrosInterface {
	public List<Libros> listLibros();
	public Libros getLibros(String id);
	public List<Libros> listLibrosCategoria(int id);
	public List<Libros> buscarLibros(String texto);
	public int createLibro(Libros libro);
	public int updateLibros(Libros libro);
	public int deleteLibros(String id);
}

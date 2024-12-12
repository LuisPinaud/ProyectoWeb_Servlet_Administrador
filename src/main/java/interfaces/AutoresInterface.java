package interfaces;

import java.util.List;

import entidades.Autores;

public interface AutoresInterface {
	public List<Autores> ListAutores();
	public int createAutor(Autores autor);
	public Autores getAutor(int id);
	public int updateAutor(Autores autor);
	public int deleteAutor(int id);
}

package interfaces;

import java.util.List;

import entidades.Editorial;

public interface EditorialInterface {
	public List<Editorial> ListEditoriales();
	public int createEditorial (Editorial editorial);
	public Editorial getEditorial (int id);
	public int updateEditorial (Editorial editorial);
	public int deleteEditorial (int id);
}

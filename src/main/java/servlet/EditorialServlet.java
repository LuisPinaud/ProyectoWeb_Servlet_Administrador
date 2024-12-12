package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Editorial;
import interfaces.EditorialInterface;
import modelo.EditorialModel;

/**
 * Servlet implementation class EditorialServlet
 */
@WebServlet("/EditorialServlet")
public class EditorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		switch (type) {
		case "list": listEditoriales(request,response);break;
		case "register": registerEditorial(request,response);break;
		case "info": getEditorial(request,response);break;
		case "edit": editEditorial(request,response);break;
		case "delete": deleteEditorial(request,response);break;
		default:

			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
		}
	}

	private void deleteEditorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		
		EditorialModel model = new EditorialModel();
		int value = model.deleteEditorial(id);
		
		if(value==0) {
			listEditoriales(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listEditoriales.jsp");
		}
	}

	private void editEditorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idEditorial = Integer.parseInt(request.getParameter("txtID"));
		String nomEditorial = request.getParameter("txtNomEditorial");
		
		Editorial editorial = new Editorial();
		editorial.setIDEditorial(idEditorial);
		editorial.setNombreEditorial(nomEditorial);

		EditorialModel model = new EditorialModel();
		int value = model.updateEditorial(editorial);
		
		if(value==0) {
			listEditoriales(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listEditoriales.jsp");
		}
	}

	private void getEditorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idEditorial = Integer.parseInt(request.getParameter("id"));
		EditorialModel model = new EditorialModel();
		
		Editorial editorialData = model.getEditorial(idEditorial);
		
		request.setAttribute("editorialData", editorialData);
		request.getRequestDispatcher("editorialInfo.jsp").forward(request, response);
	}

	private void registerEditorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomEditorial = request.getParameter("txtNomEditorial");
		
		Editorial editorial = new Editorial();
		editorial.setNombreEditorial(nomEditorial);
		
		EditorialModel model = new EditorialModel();
		int value = model.createEditorial(editorial);
		
		if(value==0) {
			listEditoriales(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listEditoriales.jsp");
		}
	}

	private void listEditoriales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		EditorialInterface editorialInterface = daoFactory.getEditorial();
		List<Editorial> dataEditoriales = editorialInterface.ListEditoriales();
		
		request.setAttribute("dataEditoriales", dataEditoriales);
		request.getRequestDispatcher("listEditoriales.jsp").forward(request, response);
	}

}

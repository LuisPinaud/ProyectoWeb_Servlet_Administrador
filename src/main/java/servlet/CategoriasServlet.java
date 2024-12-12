package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Categorias;
import interfaces.CategoriasInterface;
import modelo.CategoriasModel;

/**
 * Servlet implementation class CategoriasServlet
 */
@WebServlet("/CategoriasServlet")
public class CategoriasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		switch (type) {
		case "list": listCategorias(request,response);break;
		case "register": registerCategoria(request,response);break;
		case "info": getCategoria(request,response);break;
		case "edit": editCategoria(request,response);break;
		case "delete": deleteCategoria(request,response);break;
		default:

			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
		}
	}

	private void deleteCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		
		CategoriasModel model = new CategoriasModel();
		int value = model.deleteCategoria(id);
		
		if(value == 0) {
			listCategorias(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listCategorias.jsp");
		}
	}

	private void editCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCategoria = Integer.parseInt(request.getParameter("txtID"));
		String nomCategoria = request.getParameter("txtNomCategoria");
		
		Categorias categoria = new Categorias();
		categoria.setIDCategoria(idCategoria);
		categoria.setNombreCategoria(nomCategoria);
		
		CategoriasModel model = new CategoriasModel();
		int value = model.updateCategoria(categoria);
		
		if(value == 0) {
			listCategorias(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listCategorias.jsp");
		}
	}

	private void getCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCategoria = Integer.parseInt(request.getParameter("id"));
		
		CategoriasModel model = new CategoriasModel();
		Categorias categoriaData = model.getCategoria(idCategoria);
		
		request.setAttribute("categoriaData", categoriaData);
		request.getRequestDispatcher("categoriaInfo.jsp").forward(request, response);
	}

	private void registerCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomCategoria = request.getParameter("txtNomCategoria");
		
		Categorias categoria = new Categorias();
		categoria.setNombreCategoria(nomCategoria);
		
		CategoriasModel model = new CategoriasModel();
		int value = model.createCategoria(categoria);
		
		if(value == 0) {
			listCategorias(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("CategoriasServlet?type=list");
		}
	}

	private void listCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CategoriasInterface categoriasInterface = daoFactory.getCategorias();
		List<Categorias> dataCategorias = categoriasInterface.ListCategorias();
		
		request.setAttribute("dataCategorias", dataCategorias);
		request.getRequestDispatcher("listCategorias.jsp").forward(request, response);
	}

}

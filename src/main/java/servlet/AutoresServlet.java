package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Autores;
import interfaces.AutoresInterface;
import modelo.AutoresModel;

/**
 * Servlet implementation class AutoresServlet
 */
@WebServlet("/AutoresServlet")
public class AutoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		switch (type) {
		case "list": listAutores(request,response);break;
		case "register": registerAutor(request,response);break;
		case "info": getAutor(request,response);break;
		case "edit": editAutor(request,response);break;
		case "delete": deleteAutor(request,response);break;
		default:

			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
		}
	}

	private void deleteAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		
		AutoresModel model = new AutoresModel();
		int value = model.deleteAutor(id);
		
		if(value==0) {
			listAutores(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listAutores.jsp");
		}
	}

	private void editAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idAutor = Integer.parseInt(request.getParameter("txtID"));
		String nomAutor = request.getParameter("txtNombres");
		String apeAutor = request.getParameter("txtApellidos");
		
		Autores autorobj = new Autores();
		autorobj.setIDAutor(idAutor);
		autorobj.setNombreAutor(nomAutor);
		autorobj.setApellidoAutor(apeAutor);
		
		AutoresModel model = new AutoresModel();
		int value = model.updateAutor(autorobj);
		
		if(value == 0) {
			listAutores(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listAutores.jsp");
		}
	}

	private void getAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idAutor = Integer.parseInt(request.getParameter("id"));
		AutoresModel model = new AutoresModel();
		
		Autores autorData = model.getAutor(idAutor);
		
		request.setAttribute("autorData", autorData);
		request.getRequestDispatcher("autorInfo.jsp").forward(request, response);
	}

	private void registerAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomAutor = request.getParameter("txtNombres");
		String apeAutor = request.getParameter("txtApellidos");
		
		Autores autorobj = new Autores();
		autorobj.setNombreAutor(nomAutor);
		autorobj.setApellidoAutor(apeAutor);
		
		AutoresModel model = new AutoresModel();
		int value = model.createAutor(autorobj);
		
		if(value == 0) {
			listAutores(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("AutoresServlet?type=list");
		}
	}

	private void listAutores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		AutoresInterface autoresInterface = daoFactory.getAutores();
		List<Autores> dataAutores = autoresInterface.ListAutores();
		
		request.setAttribute("dataAutores", dataAutores);
		request.getRequestDispatcher("listAutores.jsp").forward(request, response);
	}

}

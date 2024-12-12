package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import interfaces.AdminAuthInterface;
import interfaces.LibrosInterface;
import entidades.Adminstradores;
import entidades.Libros;

/**
 * Servlet implementation class AdminAuthServlet
 */
@WebServlet("/AdminAuthServlet")
public class AdminAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		switch (type) {
		case "login": login(request,response);break;
		case "logout": logout(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.removeAttribute("NombreAdmin");
		session.removeAttribute("ApellidoAdmin");
		session.removeAttribute("email");
		session.invalidate();
		
		response.sendRedirect("loginAdministrador.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Email=request.getParameter("txtEmail");
		String contrasenia=request.getParameter("txtContrasenia");
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		AdminAuthInterface dao = daoFactory.getAdminAuth();
		
		Adminstradores admin = dao.authAdmin(Email, contrasenia);
		if(admin != null) {
			HttpSession session = request.getSession(true);
			String NombreAdmin = admin.getNombreAdmin();
			String ApellidoAdmin = admin.getApellidoAdmin();
			String email = admin.getEmail();
			
			session.setAttribute("NombreAdmin", NombreAdmin);
			session.setAttribute("ApellidoAdmin", ApellidoAdmin);
			session.setAttribute("email", email);
			
			LibrosInterface librosInterface = daoFactory.getLibros();
			List<Libros> dataLibros = librosInterface.listLibros();
			request.setAttribute("dataLibros", dataLibros);
			request.getRequestDispatcher("catalogoAdmin.jsp").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Error en usuario y/clave");
			request.getRequestDispatcher("loginAdministrador.jsp").forward(request, response);
		}
	}

}

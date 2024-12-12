package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

import dao.DAOFactory;
import entidades.Libros;
import interfaces.AdminAuthInterface;
import interfaces.LibrosInterface;
import modelo.LibrosModel;

/**
 * Servlet implementation class LibrosServlet
 */
@WebServlet("/LibrosServlet")
public class LibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		System.out.println("Este es el parametro:"+type);
		
		switch (type) {
		case "list": listLibros(request,response);break;
		case "info": getLibro(request,response);break;
		case "librosCategoria": listLibrosCategoria(request,response);break;
		case "buscar": buscarLibros(request,response);break;
		case "edit": editAutor(request,response);break;
		case "register": librosRegister(request,response);break;
		case "delete": deleteLibro(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
		}
	}

	private void deleteLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ISBN = request.getParameter("id");
		
		LibrosModel model = new LibrosModel();
		int value = model.deleteLibros(ISBN);
		
		if(value==0) {
			listLibros(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("catalogoAdmin.jsp");
		}
	}

	private void editAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ISBN=request.getParameter("txtISBN");
		String titulo=request.getParameter("txtTitulo");
		String resenia=request.getParameter("txtResenia");
		double precio=Double.parseDouble(request.getParameter("txtPrecio"));
		int stock=Integer.parseInt(request.getParameter("txtStock"));
		String fechaPublicacion = request.getParameter("txtFechaPublicacion");
		int idAutor=Integer.parseInt(request.getParameter("cboAutor"));
		int idEditorial=Integer.parseInt(request.getParameter("cboEditorial"));
		int restriccionEdad=Integer.parseInt(request.getParameter("txtRestriccion"));
		
		Libros obj = new Libros();
		obj.setISBN(ISBN);
		obj.setTitulo(titulo);
		obj.setResenia(resenia);
		obj.setPrecio(precio);
		obj.setStock(stock);
		obj.setFechaPublicacion(fechaPublicacion);
		obj.setIDAutor(idAutor);
		obj.setIDEditorial(idEditorial);
		obj.setRestriccionEdad(restriccionEdad);
		
		LibrosModel model = new LibrosModel();
		int value = model.updateLibros(obj);
		
		if(value==0) {
			listLibros(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("catalogoAdmin.jsp");
		}
	}

	private void librosRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ISBN=request.getParameter("txtISBN");
		String titulo=request.getParameter("txtTitulo");
		String resenia=request.getParameter("txtResenia");
		double precio=Double.parseDouble(request.getParameter("txtPrecio"));
		int stock=Integer.parseInt(request.getParameter("txtStock"));
		String fechaPublicacion = request.getParameter("txtFechaPublicacion");
		int idAutor=Integer.parseInt(request.getParameter("cboAutor"));
		int idEditorial=Integer.parseInt(request.getParameter("cboEditorial"));
		int restriccionEdad=Integer.parseInt(request.getParameter("txtRestriccion"));
		
		Libros obj = new Libros();
		obj.setISBN(ISBN);
		obj.setTitulo(titulo);
		obj.setResenia(resenia);
		obj.setPrecio(precio);
		obj.setStock(stock);
		obj.setFechaPublicacion(fechaPublicacion);
		obj.setIDAutor(idAutor);
		obj.setIDEditorial(idEditorial);
		obj.setRestriccionEdad(restriccionEdad);
		
		LibrosModel model = new LibrosModel();
		int value = model.createLibro(obj);
		
		if(value == 0) {
			listLibros(request, response);
			/* try {
				 	// Procesar el archivo de imagen
		            Part filePart = request.getPart("fileImagen");
		            if (filePart != null && filePart.getSize() > 0) {
		            	// Obtener la extensión del archivo original
		                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		                String fileExtension = fileName.substring(fileName.lastIndexOf("."));

		                // Construir el nuevo nombre del archivo usando el ISBN
		                String newFileName = ISBN + fileExtension;

		                InputStream fileContent = filePart.getInputStream();

		                // Ruta donde se guardará el archivo
		                String path = "C:/Users/GEZ.Rafael/eclipse-workspace/ProyectoEFSRT2/src/main/webapp/img/libros";
		                Path filePath = Paths.get(path, newFileName);

		                // Guardar el archivo en la ruta especificada
		                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } */
		} else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("LibrosServlet?type=list");
		}
	}

	private void buscarLibros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dato = request.getParameter("txtBuscar");

		LibrosModel librosModel = new LibrosModel();
		List<Libros> listLibrosBusqueda = librosModel.buscarLibros(dato);
		
		request.setAttribute("dataLibros", listLibrosBusqueda);
		request.getRequestDispatcher("catalogoAdmin.jsp").forward(request, response);
	}

	private void listLibrosCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCategoria = Integer.parseInt(request.getParameter("cboCategoria"));
        System.out.println("Categoria: " + idCategoria);
        
        LibrosModel librosModel = new LibrosModel();
        List<Libros> listLibros = librosModel.listLibrosCategoria(idCategoria);
        
        request.setAttribute("listLibros", listLibros);
        request.getRequestDispatcher("catalogoAdmin.jsp").forward(request, response);
	}

	private void getLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ISBN = request.getParameter("id");
		LibrosModel librosModel = new LibrosModel();
		
		Libros libro = librosModel.getLibros(ISBN);
		List<Libros> dataLibro = librosModel.listLibros();
		
		request.setAttribute("dataLibro", libro);
		request.getRequestDispatcher("libroInfoAdmin.jsp").forward(request, response);
	}

	private void listLibros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		LibrosInterface librosInterface = daoFactory.getLibros();
		List<Libros> dataLibros = librosInterface.listLibros();
		
		request.setAttribute("dataLibros", dataLibros);
		request.getRequestDispatcher("catalogoAdmin.jsp").forward(request, response);
	}

}

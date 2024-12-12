package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOFactory;
import entidades.Inventario;
import entidades.VentasMensuales;
import interfaces.InventarioInteface;
import modelo.VentasMensualesModel;

/**
 * Servlet implementation class ReportesServlet
 */
@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		switch (type) {
		case "inventario": listInventario(request,response);break;
		case "ventasMensuales": listVentasMensuales(request,response);break;
		case "inventarioPDF": inventarioPDF(request,response);break;
		case "ventasMensualesPDF": ventasMensualesPDF(request,response);break;
		default:

			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("reportes.jsp").forward(request, response);
		}
	}

	private void ventasMensualesPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mes = Integer.parseInt(request.getParameter("cboMes"));
		int anio = Integer.parseInt(request.getParameter("txtAnio"));
					
		VentasMensualesModel model = new VentasMensualesModel();
		List<VentasMensuales> listVentas = model.listVentasMensuales(mes, anio);
		
		try {

	        if (listVentas == null || listVentas.isEmpty()) {
	            request.setAttribute("mensaje", "No hay datos de ventas para generar el PDF");
	            request.getRequestDispatcher("reportes.jsp").forward(request, response);
	            return;
	        }

	        // Configurar la respuesta para abrir el PDF en una pestaña aparte
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=Reporte_Ventas_Mensuales.pdf");

	        // Generar el PDF
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);
	        document.open();

	        // Título del documento
	        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
	        Paragraph titulo = new Paragraph("Reporte de Ventas Mensuales"+"\n"+"("+mes+"-"+anio+")", fontTitulo);
	        titulo.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(titulo);

	        // Espacio antes de la tabla
	        document.add(new Paragraph(" "));

	        // Crear tabla
	        PdfPTable tabla = new PdfPTable(7); // 7 columnas (cantidad requerida)
	        tabla.setWidthPercentage(100);

	        // Encabezado de la tabla
	        Font fontEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
	        PdfPCell cellEncabezadoISBN = new PdfPCell(new Phrase("ISBN", fontEncabezado));
	        cellEncabezadoISBN.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoISBN);
	        
	        PdfPCell cellEncabezadoTitulo = new PdfPCell(new Phrase("Título", fontEncabezado));
	        cellEncabezadoTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoTitulo);
	        
	        PdfPCell cellEncabezadoAutor = new PdfPCell(new Phrase("Autor", fontEncabezado));
	        cellEncabezadoAutor.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoAutor);

	        PdfPCell cellEncabezadoPrecioUnitario = new PdfPCell(new Phrase("Precio Unitario", fontEncabezado));
	        cellEncabezadoPrecioUnitario.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoPrecioUnitario);

	        PdfPCell cellEncabezadoFechaVenta = new PdfPCell(new Phrase("Fecha de Venta", fontEncabezado));
	        cellEncabezadoFechaVenta.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoFechaVenta);
	        
	        PdfPCell cellEncabezadoCantidadTotal = new PdfPCell(new Phrase("Cantidad Total", fontEncabezado));
	        cellEncabezadoCantidadTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoCantidadTotal);
	        
	        PdfPCell cellEncabezadoMontoTotal = new PdfPCell(new Phrase("Monto Total", fontEncabezado));
	        cellEncabezadoMontoTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoMontoTotal);

	        // Datos de la tabla
	        Font fontCelda = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
	        for (VentasMensuales item : listVentas) {
	        	tabla.addCell(new Phrase(item.getISBN(), fontCelda));
	            tabla.addCell(new Phrase(item.getTitulo(), fontCelda));
	            tabla.addCell(new Phrase(item.getAutor(), fontCelda));
	            tabla.addCell(new Phrase(String.valueOf(item.getPrecioUnitario()), fontCelda));
	            tabla.addCell(new Phrase(item.getFechaVenta(), fontCelda));
	            tabla.addCell(new Phrase(String.valueOf(item.getTotalVendidos()), fontCelda));
	            tabla.addCell(new Phrase(String.valueOf(item.getMontoTotalVenta()), fontCelda));
	        }

	        // Agregar tabla al documento
	        document.add(tabla);

	        // Cerrar documento
	        document.close();

	        // Escribir el PDF en el OutputStream de la respuesta
	        response.setContentLength(baos.size());
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al generar el PDF");
	        request.getRequestDispatcher("reportes.jsp").forward(request, response);
	    }
	}

	private void listVentasMensuales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mes = Integer.parseInt(request.getParameter("cboMes"));
		int anio = Integer.parseInt(request.getParameter("txtAnio"));
		
		VentasMensualesModel model = new VentasMensualesModel();
		List<VentasMensuales> listVentas = model.listVentasMensuales(mes, anio);
		
		request.setAttribute("dataVentasMensuales", listVentas);
		request.getRequestDispatcher("ventasMensuales.jsp").forward(request, response);
	}

	private void inventarioPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
        InventarioInteface inventarioInteface = daoFactory.getInventario();
        List<Inventario> dataInventario = inventarioInteface.listInventario();

		try {
	        if (dataInventario == null || dataInventario.isEmpty()) {
	            request.setAttribute("mensaje", "No hay datos de inventario para generar el PDF");
	            request.getRequestDispatcher("reportes.jsp").forward(request, response);
	            return;
	        }

	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=Reporte_Inventario.pdf");

	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);
	        document.open();

	        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
	        Paragraph titulo = new Paragraph("Reporte del inventario", fontTitulo);
	        titulo.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(titulo);

	        document.add(new Paragraph(" "));

	        PdfPTable tabla = new PdfPTable(3); 
	        tabla.setWidthPercentage(100);

	        Font fontEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
	        PdfPCell cellEncabezadoTitulo = new PdfPCell(new Phrase("Título", fontEncabezado));
	        cellEncabezadoTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoTitulo);

	        PdfPCell cellEncabezadoStock = new PdfPCell(new Phrase("Stock", fontEncabezado));
	        cellEncabezadoStock.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoStock);

	        PdfPCell cellEncabezadoEstado = new PdfPCell(new Phrase("Estado", fontEncabezado));
	        cellEncabezadoEstado.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tabla.addCell(cellEncabezadoEstado);

	        Font fontCelda = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
	        for (Inventario item : dataInventario) {
	            tabla.addCell(new Phrase(item.getTitulo(), fontCelda));
	            tabla.addCell(new Phrase(String.valueOf(item.getStock()), fontCelda));
	            tabla.addCell(new Phrase(item.getEstado(), fontCelda));
	        }

	        document.add(tabla);

	        document.close();

	        response.setContentLength(baos.size());
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error al generar el PDF");
	        request.getRequestDispatcher("reportes.jsp").forward(request, response);
	    }
	}




	private void listInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		InventarioInteface inventarioInteface = daoFactory.getInventario();
		List<Inventario> dataInventario = inventarioInteface.listInventario();
		
		request.setAttribute("dataInventario", dataInventario);
		request.getRequestDispatcher("inventario.jsp").forward(request, response);
	}

}

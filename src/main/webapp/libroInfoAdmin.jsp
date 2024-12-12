<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="entidades.Libros"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Autores"%>
<%@ page import="entidades.Editorial"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles Libro</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/styleU.css">
<link rel="stylesheet" href="css/carrito.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	referrerpolicy="no-referrer" />
</head>
<body style="background-color: #e3e9f7;">
	<header>
		<div class="logo">
			<img src="img/logo.png" alt="">
			<h1>Ricón Literario</h1>
		</div>
		<input type="checkbox" id="nav_check" hidden>
		<nav>
			<ul>
                <li><a href="LibrosServlet?type=list" style="text-decoration: none;">Catálogo</a></li>
                <li><a href="mantenimiento.jsp" style="text-decoration: none;">Mantenimiento</a></li>
                <li><a href="reportes.jsp" style="text-decoration: none;">Reportes</a></li>
                <li><a href="#" class="active" style="text-decoration: none;">
                    <img src="img/administrador.png" alt="Icono Admin"> ${sessionScope.NombreAdmin}</a>
                </li>
            </ul>
		</nav>
		<label for="nav_check" class="hamburger">
			<div></div>
			<div></div>
			<div></div>
		</label>
	</header>
	<br>
	<div class="col-12 text-center">
		<h3>Editar Datos Del Libro</h3>
	</div>
	<br>
	<div class="container"
		style="background-color: white; border-radius: 2%; height: 10%; margin-top: 20px; padding-top: 10px; padding-bottom: 10px; display: flex;">
		<%
		Libros libro = (Libros) request.getAttribute("dataLibro");
		%>
		<div id="carouselBooks" class="carousel-books carousel slide"
			data-bs-ride="false">
			<div class="carousel-inner carousel-inner-books">
				<div class="carousel-item-books active">
					<div class="card">
						<img src="img/libros/<%=libro.getISBN()%>.webp"
							class="card-img-top" alt="Libro 1">

					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div
					class="container min-vh-100 d-flex align-items-center">
					<form action="LibrosServlet" method="post">
						<input type="hidden" name="type" value="edit">
						<div class="form-group">
							<label>ISBN:</label> <input class="form-control" type="text"
								name="txtISBN"
								value="<%=(libro != null) ? libro.getISBN() : ""%>" readonly>
						</div>
						<div class="form-group">
							<label>Título:</label> <input class="form-control" type="text"
								name="txtTitulo"
								value="<%=(libro != null) ? libro.getTitulo() : ""%>">
						</div>
						<div class="form-group">
							<label>Reseña:</label> <input class="form-control" type="text"
								name="txtResenia"
								value="<%=(libro != null) ? libro.getResenia() : ""%>">
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Autor:</label>
							<%
							List<Autores> listAutores = (List<Autores>) session.getAttribute("listAutores");
							%>
							<select class="form-control" name="cboAutor">
								<%
								if (listAutores != null) {
									for (Autores item : listAutores) {
										boolean selected = (libro != null
										&& String.valueOf(item.getIDAutor()).equals(String.valueOf(libro.getIDAutor())));
								%>
								<option value="<%=item.getIDAutor()%>"
									<%=selected ? "selected" : ""%>>
									<%=item.getNombreAutor()%>
									<%=item.getApellidoAutor()%>
								</option>
								<%
								}
								}
								%>
							</select>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Editorial:</label>
							<%
							List<Editorial> listEditoriales = (List<Editorial>) session.getAttribute("listEditoriales");
							%>
							<select class="form-control" name="cboEditorial">
								<%
								if (listEditoriales != null) {
									for (Editorial item : listEditoriales) {
										boolean selected = (libro != null
										&& String.valueOf(item.getIDEditorial()).equals(String.valueOf(libro.getIDEditorial())));
								%>
								<option value="<%=item.getIDEditorial()%>"
									<%=selected ? "selected" : ""%>>
									<%=item.getNombreEditorial()%>
								</option>
								<%
								}
								}
								%>
							</select>
						</div>

						<div class="form-group">
							<label>Fecha de Publicación:</label> <input class="form-control"
								type="date" name="txtFechaPublicacion"
								value="<%=(libro != null) ? libro.getFechaPublicacion() : ""%>">
						</div>
						<div class="form-group">
							<label>Precio(S/.):</label> <input class="form-control"
								type="number" name="txtPrecio" min="1" step="0.01"
								value="<%=(libro != null) ? libro.getPrecio() : ""%>">
						</div>
						<div class="form-group">
							<label>Stock:</label> <input class="form-control" type="number"
								name="txtStock" min="1"
								value="<%=(libro != null) ? libro.getStock() : ""%>">
						</div>
						<div class="form-group">
							<label>Restricción de Edad:</label> <input class="form-control"
								type="number" name="txtRestriccion" min="0"
								value="<%=(libro != null) ? libro.getRestriccionEdad() : ""%>">
						</div>
						<br> 
						<input type="submit" class="btn btn-warning" value="Actualizar">
						<button type="button" class="btn btn-danger" onclick="location.href='LibrosServlet?type=delete'">Eliminar</button>
						<button type="button" class="btn btn-primary" onclick="location.href='LibrosServlet?type=list'">Regresar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

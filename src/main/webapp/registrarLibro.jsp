<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Autores"%>
<%@ page import="entidades.Editorial"%>
<html>
<head>
<title>Registrar Producto</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/registrarLibro.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
	rel="stylesheet">
</head>
<body>
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

	<div id="content-wrapper" class="d-flex flex-column">
		<div id="content">
			<div class="container">
				<div class="row">
					<div class="col-12 text-center">
						<h3>Registrar Libro</h3>
					</div>
					<div
						class="container min-vh-100 d-flex justify-content-center align-items-center">
						<form action="LibrosServlet" method="post">
							<input type="hidden" name="type" value="register">
							<div class="form-group">
								<label>ISBN:</label> <input class="form-control" type="text"
									name="txtISBN">
							</div>
							<div class="form-group">
								<label>Título:</label> <input class="form-control" type="text"
									name="txtTitulo">
							</div>
							<div class="form-group">
								<label>Reseña:</label> <input class="form-control" type="text"
									name="txtResenia">
							</div>
							<div class="form-group">
								<label>Imagen (webp):</label> <input class="form-control"
									type="file" name="fileImagen" accept="image/webp">
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
									%>
									<option value="<%=item.getIDAutor()%>">
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
									%>
									<option value="<%=item.getIDEditorial()%>">
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
									type="date" name="txtFechaPublicacion">
							</div>
							<div class="form-group">
								<label>Precio(S/.):</label> <input class="form-control"
									type="number" name="txtPrecio" min="1" step="0.01">
							</div>
							<div class="form-group">
								<label>Stock:</label> <input class="form-control" type="number"
									name="txtStock" min="1">
							</div>
							<div class="form-group">
								<label>Restricción de Edad:</label> <input class="form-control"
									type="number" name="txtRestriccion" min="0">
							</div>
							<br> <input type="submit" class="btn btn-success"
								value="Registrar">
							<button type="button" class="btn btn-primary"
								onclick="location.href='mantenimiento.jsp'">Regresar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

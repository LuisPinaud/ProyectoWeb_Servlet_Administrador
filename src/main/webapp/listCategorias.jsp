<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Categorias"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editoriales</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/styleTables.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap">
</head>
<body>
	<header>
		<div class="logo">
			<img src="img/logo.png" alt="Logo">
			<h1>Rincón Literario</h1>
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
					<br>

					<div>
						<h3>Mantenimiento de Categorías</h3>
					</div>
					<br>
					<div class="col-12">
						<button type="button" class="btn btn-outline-primary" onclick="window.location='categoriaRegister.jsp'">Registrar</button>
						<button type="button" class="btn btn-outline-warning" onclick="window.location='mantenimiento.jsp'">Regresar</button>
					</div>
					<br> <br>
					<div class="col-12">
						<table class="table">
							<thead style="background-color: #f4ff91; color: black;">
								<tr>
									<th>ID de la Categoría</th>
									<th>Nombre</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<%
								List<Categorias> listCategorias = (List<Categorias>) request.getAttribute("dataCategorias");
								if (listCategorias != null) {
									for (Categorias item : listCategorias) {
								%>
								<tr>
									<td><%=item.getIDCategoria()%></td>
									<td><%=item.getNombreCategoria()%></td>
									<td><a
										href="CategoriasServlet?type=info&id=<%=item.getIDCategoria()%>">
											<img alt="" src="img/iconos/iconoInfo.png" width="30"
											height="30" title="Editar">
									</a> <a href="CategoriasServlet?type=delete&id=<%=item.getIDCategoria()%>">
											<img alt="" src="img/iconos/iconoEliminar.png" width="30"
											height="30" title="Eliminar">
									</a></td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>
					</div>
				</div>

				<div class="row"></div>
			</div>
		</div>
	</div>

</body>
</html>
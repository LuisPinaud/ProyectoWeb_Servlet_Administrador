<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Inventario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventario</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/styleTables.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
	rel="stylesheet">

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
				<li><a href="LibrosServlet?type=list"
					style="text-decoration: none;">Catálogo</a></li>
				<li><a href="mantenimiento.jsp" style="text-decoration: none;">Mantenimiento</a></li>
				<li><a href="reportes.jsp" style="text-decoration: none;">Reportes</a></li>
				<li><a href="#" class="active" style="text-decoration: none;">
						<img src="img/administrador.png" alt="Icono Admin">
						${sessionScope.NombreAdmin}
				</a></li>
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
						<h3>Inventario</h3>
					</div>
					<br>
					<div class="col-12">
						<div>
							<form action="ReportesServlet?type=inventarioPDF" method="post">
								<input type="hidden" name="action" value="inventarioPDF">
								<button type="submit" class="btn btn-outline-danger">
									Descargar Reporte PDF <img width="15" height="15"
										src="img/iconos/iconoPDF.png">
								</button>
								<button type="button" onclick="window.location='reportes.jsp'"
									class="btn btn-outline-warning">Regresar</button>
							</form>

							<div>
								<%
								String mensaje = (String) request.getAttribute("mensaje");
								%>
								<%
								if (mensaje != null) {
								%>
								<p><%=mensaje%></p>
								<%
								}
								%>
							</div>
							<br> <br>
							<div class="col-12">
								<table class="table">
									<thead style="background-color: #C39BD3;">
										<tr>
											<th>Título</th>
											<th>Stock</th>
											<th>Estado</th>
										</tr>
									</thead>
									<tbody>
										<%
										List<Inventario> listInventario = (List<Inventario>) request.getAttribute("dataInventario");
										if (listInventario != null) {
											for (Inventario item : listInventario) {
												String claseEstado = (item.getStock() == 0) ? "background-color: #ffe4e1;" : "";

												String claseAlerta = (item.getStock() <= 20) ? "background-color: #fdf9c4;" : "";
										%>
										<tr style="<%=claseAlerta%> <%=claseEstado%>">
											<td><%=item.getTitulo()%></td>
											<td><%=item.getStock()%></td>
											<td><%=item.getEstado()%></td>
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
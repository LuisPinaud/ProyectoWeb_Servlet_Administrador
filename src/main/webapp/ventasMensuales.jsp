<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.VentasMensuales"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ventas Mensuales</title>
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
			<h1>Rinc�n Literario</h1>
		</div>
		<input type="checkbox" id="nav_check" hidden>
		<nav>
			<ul>
				<li><a href="LibrosServlet?type=list"
					style="text-decoration: none;">Cat�logo</a></li>
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
						<h3>Ventas Mensuales</h3>
					</div>
					<br>
					<div style="display: flex; flex-direction: row;">
						<form action="ReportesServlet" method="post"
							style="display: flex; flex-direction: row;"
							onsubmit="return validarFormulario()">
							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">Mes:</label>
								<select name="cboMes" class="form-control">
									<option value="01">Enero</option>
									<option value="02">Febrero</option>
									<option value="03">Marzo</option>
									<option value="04">Abril</option>
									<option value="05">Mayo</option>
									<option value="06">Junio</option>
									<option value="07">Julio</option>
									<option value="08">Agosto</option>
									<option value="09">Septiembre</option>
									<option value="10">Octubre</option>
									<option value="11">Noviembre</option>
									<option value="12">Diciembre</option>
								</select>
							</div>
							<div class="form-group" style="margin-left: 10px;">
								<label>A�o:</label> <input class="form-control" type="number"
									name="txtAnio">
							</div>
							<input type="hidden" id="type" name="type">
							<input type="submit" class="btn btn-outline-primary" value="Consultar"
								style="margin-left: 10px;" onclick="setType('ventasMensuales')">
							<input type="submit" class="btn btn-outline-danger" value="Descargar PDF"
								style="margin-left: 10px;" onclick="setType('ventasMensualesPDF')">
							<img width="15" height="15" src="img/iconos/iconoPDF.png"> <br>
							<br>
						</form>
					</div>

					<script>
						function setType(value) {
							document.getElementById('type').value = value;
						}
					</script>

					<script>
						function validarFormulario() {
							var mes = document.forms[0]["cboMes"].value;
							var anio = document.forms[0]["txtAnio"].value;

							if (mes === "" || anio === "") {
								alert("Por favor, complete todos los campos.");
								return false;
							}
							return true;
						}
					</script>

					<br>
					<div class="col-12">
						<div>
							<button type="button" onclick="window.location='reportes.jsp'"
								class="btn btn-outline-warning">Regresar</button>
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
											<th>ISBN</th>
											<th>T�tulo</th>
											<th>Autor</th>
											<th>Precio Unitario</th>
											<th>Fecha de Venta</th>
											<th>Cantidad Total</th>
											<th>Monto Total</th>
										</tr>
									</thead>
									<tbody>
										<%
										List<VentasMensuales> listVentas = (List<VentasMensuales>) request.getAttribute("dataVentasMensuales");
										if (listVentas != null && !listVentas.isEmpty()) {
											for (VentasMensuales item : listVentas) {
										%>
										<tr>
											<td><%=item.getISBN()%></td>
											<td><%=item.getTitulo()%></td>
											<td><%=item.getAutor()%></td>
											<td><%=item.getPrecioUnitario()%></td>
											<td><%=item.getFechaVenta()%></td>
											<td><%=item.getTotalVendidos()%></td>
											<td><%=item.getMontoTotalVenta()%></td>
										</tr>
										<%
										}
										} else {
										%>
										<tr>
											<td colspan="7">No hay ventas registradas en esta fecha.</td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
</body>
</html>
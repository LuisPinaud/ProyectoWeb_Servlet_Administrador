<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar Autor</title>
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
					<div class="col-12 text-center">
						<h3>Registrar Autor</h3>
					</div>
					<div
						class="container min-vh-100 d-flex justify-content-center align-items-center">
						<form action="AutoresServlet?type=register" method="post"
							onsubmit="return validarFormulario()">
							<div class="form-group">
								<label>Nombres:</label> <input class="form-control" type="text"
									name="txtNombres" id="txtNombres">
							</div>
							<div class="form-group">
								<label>Apellidos:</label> <input class="form-control"
									type="text" name="txtApellidos" id="txtApellidos">
							</div>
							<button type="button" class="btn btn-warning" onclick="window.location.href = 'AutoresServlet?type=list';">Regresar</button>
							
							<button type="submit" class="btn btn-success">Registrar</button>
						</form>

						<script>
							function validarFormulario() {
								var nombres = document
										.getElementById('txtNombres').value
										.trim();
								var apellidos = document
										.getElementById('txtApellidos').value
										.trim();

								if (nombres === '' || apellidos === '') {
									alert('Por favor, complete todos los campos.');
									return false;
								}
								return true;
							}
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
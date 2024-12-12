<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reportes</title>
<link rel="stylesheet" href="css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
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
                <li><a href="LibrosServlet?type=list" style="text-decoration: none;">Cat�logo</a></li>
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
	
	<div class="contenedorCategorias">
		<h2>Reportes</h2>
		<div class="categorias">
			<div class="categoriaFiccion" style="background-color: #48C9B0;">
				<img src="img/iconos/iconoVentas.png" alt="">
				<div class="categoriaInfo">
					<h3>Ventas Mensuales</h3>
					<a href="ventasMensuales.jsp" class="boton">Ver</a>
				</div>
			</div>
			<div class="categoriaNoFiccion" style="background-color:#C39BD3;">
				<img src="img/iconos/iconoInventario.png" alt="">
				<div class="categoriaInfo">
					<h3>Inventario</h3>
					<a href="ReportesServlet?type=inventario" class="boton">Ver</a>
				</div>
			</div>

		</div>
	</div>
	
</body>
</html>
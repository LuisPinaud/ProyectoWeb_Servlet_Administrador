<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Categorias"%>
<%@ page import="entidades.Libros"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Catálogo</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styleU.css">
    <link rel="stylesheet" href="css/carrito.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" referrerpolicy="no-referrer" />
</head>
<body style="background-color: #e3e9f7;">
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

    <div class="contenedorCategorias">
    <h2>Catálogo</h2>
    <div class="container mt-5">
        <form action="LibrosServlet" method="post" class="form-inline">
            <input type="hidden" name="type" value="buscar">
                <input type="search" name="txtBuscar" class="form-control" placeholder="Buscar libros...">
                <br>
            <button type="submit" class="btn btn-outline-primary mb-2">Buscar</button>
        </form>
    </div>
    <hr>
    <div class="categorias">
        <%
        List<Libros> listLibros = (List<Libros>) request.getAttribute("dataLibros");
        if (listLibros == null) {
            out.println("¡Error!");
        } else if (listLibros.isEmpty()) {
            out.println("No hay libros disponibles :(");
        } else {
            for (Libros item : listLibros) {
        %>
        <div class="categoriaFiccion">
            <img src="img/libros/<%=item.getISBN()%>.webp" alt="<%=item.getTitulo()%>">
            <div class="categoriaInfo">
                <h3><%=item.getTitulo()%></h3>
                <h5>S/.<%=item.getPrecio()%></h5>
                <a href="LibrosServlet?type=info&id=<%=item.getISBN()%>" class="boton">Detalles</a>
            </div>
        </div>
        <%
            }
        }
        %>
    </div>
</div>

</body>
</html>

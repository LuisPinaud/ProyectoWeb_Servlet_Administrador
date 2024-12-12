<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Admin</title>
	<link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=LXGW+WenKai+TC&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">    
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
	<div class="loginContainer">
        <div class="container-form register">
            <div class="information">
                <div class="info-childs">
                    
                    <div class="logo">
                        <img src="img/logo.png" alt="">
                        <h2>¡Bienvenido!</h2>
                    </div>
                    <p>Para acceder a nuestra interfaz de Administrador por favor Inicia Sesión con
                        tus datos.</p>
                </div>
            </div>
            <div class="form-information">
                <div class="form-information-childs">
                    <h2>Iniciar Sesión</h2>
    
                    <form action="AdminAuthServlet" class="form form-login" novalidate>
                        <input type="hidden" name="type" value="login">
                        <div>
                            <label> <i class='bx bx-envelope'></i> <input type="email"
                                placeholder="Correo Electronico" name="txtEmail">
                            </label>
                        </div>
                        <div>
                            <label> <i class='bx bx-lock-alt'></i> <input
                                type="password" placeholder="Contraseña" name="txtContrasenia">
                            </label>
                        </div>
                        <input type="submit" value="Iniciar Sesión">
                        <div class="alerta-error">Todos los campos son obligatorios</div>
                        <div class="alerta-exito">Te registraste correctamente</div>
                    </form>
                </div>
            </div>
        </div>
</body>
</html>
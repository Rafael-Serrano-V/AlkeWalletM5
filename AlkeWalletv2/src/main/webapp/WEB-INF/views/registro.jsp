<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registro - Alke Wallet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registro.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>ALKE WALLET</h1>
            <nav>
                <ul>
                    <li><a href="index">Inicio</a></li>
                    <li><a href="login">Iniciar sesión</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="background-image">
        <div class="registro-container">
            <h1>Registro</h1>
            <form action="registro" method="post">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Registrar</button>
            </form>
            
            <% if (request.getParameter("error") != null) { %>
                <p style="color:red;">Error al registrar el usuario, por favor intente de nuevo.</p>
            <% } %>
        </div>
    </div>

    <footer>
        <div class="container">
            <p>&copy; 2024 Alke Wallet. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>
</html>

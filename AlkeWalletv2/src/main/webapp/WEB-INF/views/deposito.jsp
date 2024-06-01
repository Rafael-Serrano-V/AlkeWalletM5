<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Usuario"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
if (usuario == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Depositar - Alke Wallet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/deposito.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>ALKE WALLET</h1>
            <nav>
                <ul>
                    <li><a href="index">Inicio</a></li>
                    <li><a href="logout">Cerrar sesión</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <div class="container">
            <h1>Depositar</h1>
            <form action="deposito" method="post">
                <label for="monto">Monto:</label> 
                <input type="number" id="monto" name="monto" required><br>
                <button type="submit">Depositar</button>
            </form>
            <% if (request.getParameter("error") != null) { %>
                <p class="error">
                    <% if ("1".equals(request.getParameter("error"))) { %>
                        Error al procesar el depósito, por favor intente de nuevo.
                    <% } else if ("2".equals(request.getParameter("error"))) { %>
                        Cuenta no encontrada.
                    <% } else if ("3".equals(request.getParameter("error"))) { %>
                        El monto debe ser mayor o igual a 2000.
                    <% } %>
                </p>
            <% } %>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Alke Wallet. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>
</html>

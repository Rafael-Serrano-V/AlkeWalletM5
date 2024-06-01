<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Cuenta"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
if (usuario == null) {
    response.sendRedirect("login.jsp");
    return;
}
Cuenta cuenta = usuario.getCuenta();
// double saldoCLP = (cuenta != null) ? cuenta.getSaldo() * 800 : 0; // Conversión a CLP
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Inicio - Alke Wallet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
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
        <h1>Bienvenido, <%= usuario.getNombre() %></h1>
            <div class="saldo-card">
                <p class="saldo">Saldo: $ <%= cuenta != null ? cuenta.getSaldo() : "Cuenta no encontrada" %></p>
                <img src="${pageContext.request.contextPath}/resources/images/tarjeta-home.png" alt="Tarjeta de crédito">
                <div class="actions">
                    <a href="deposito">Depositar</a>
                    <a href="retiro">Retirar</a>
                    <a href="transferencia">Transferir</a>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Alke Wallet. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>
</html>

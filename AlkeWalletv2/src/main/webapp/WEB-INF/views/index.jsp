<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/index.css">
<title>Alke Wallet - Inicio</title>
</head>
<body>
	<header>
		<div class="container">
			<h1>ALKE WALLET</h1>
			<nav>
				<ul>
					<li><a href="index">Inicio</a></li>
					<c:if test="${not empty usuario}">
						<li><a href="home">Perfil</a></li>
						<li><a href="#">Transacciones</a></li>
						<li><a href="logout">Cerrar sesión</a></li>
					</c:if>
					<c:if test="${empty usuario}">
						<li><a href="login">Iniciar sesión</a></li>
					</c:if>
				</ul>
			</nav>
		</div>

	</header>
	<main>
		<div class="welcome-section">
			<div class="content-left">
				<c:if test="${empty usuario}">
					<h2>Bienvenido a</h2>
					<h1>Alke Wallet</h1>
				</c:if>
				<c:if test="${not empty usuario}">
					<h2>Bienvenido a</h2>
					<h1>Alke Wallet, ${usuario.nombre}!</h1>
				</c:if>
				<p>Tu billetera virtual segura y fácil de usar.</p>
				<div class="buttons">
					<c:if test="${empty usuario}">
						<a href="login" class="btn-primary">Iniciar Sesión</a>
						<a href="registro" class="btn-secondary">Registrarse</a>
					</c:if>
				</div>
			</div>
			<div class="image-container">
				<img
					src="${pageContext.request.contextPath}/resources/images/bg-index.png"
					alt="Fondo de la página de inicio">
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

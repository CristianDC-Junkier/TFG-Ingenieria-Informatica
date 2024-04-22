<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/foroInicioCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Foro</h2>
        <hr color="black">
        <div class="contenedorBusquedaForo">
            <div class="buscadorForo">
                <button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajesAmigos'">Mis Hilos</button>
                <button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajesPerfil'">Hilos Comentados</button>
            </div>
        </div>
        <div class="contenedorForo">
            <a href="/TFG/Foro/temas">
                <figure>
                    <img src="/TFG/img/Temas.png">
                    <figcaption>Temas</figcaption>
                </figure>
            </a>
            <a href="/TFG/Foro/secciones">
                <figure>
                    <img src="/TFG/img/Secciones.png"">
                    <figcaption>Secciones</figcaption>
                </figure>
            </a>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

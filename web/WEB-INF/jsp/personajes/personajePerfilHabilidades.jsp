<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Habilidades</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/habilidadesCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Personaje: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div>
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${requestScope.personaje.id}'">Volver</button>
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Formularios/modificarPersonajeHabilidades?id=${requestScope.personaje.id}'">Modificar</button>
            </div>
            <div class="personajeHabilidades">
                <div class="cajaHabilidades">
                    <ul>
                        <c:forEach var="pHabilidad" items="${listaPHabilidad}" varStatus="status">
                            <li>
                                <p class="tituloHabilidad">${pHabilidad.habilidades.nombre}:</p>
                                <c:choose>
                                    <c:when test="${pHabilidad.competencia == 'Si'}">
                                        <p class="valoresHabilidad">${requestScope.listaValoresHab[status.index]} &nbsp;&nbsp;&nbsp; - Competente </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="valoresHabilidad">${requestScope.listaValoresHab[status.index]} &nbsp;&nbsp;&nbsp; - No Competente </p>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Amigo\Habilidades</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/razaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Personaje: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div class="cajaHabilidades">
                <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personaje?id=${requestScope.personaje.id}'">Volver</button></div>
                <div class="personajeHabilidades">
                    <ul>
                        <c:forEach var="pHabilidad" items="${listaPHabilidad}" varStatus="status">
                            <li>
                                ${pHabilidad.habilidades.nombre}: ${requestScope.listaValoresHab[status.index]} - 
                                <c:choose>
                                    <c:when test="${pHabilidad.competencia == 'Si'}">
                                        Competente
                                    </c:when>
                                    <c:otherwise>
                                        No Competente
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
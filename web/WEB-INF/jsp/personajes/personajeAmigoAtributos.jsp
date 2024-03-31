<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Amigo\Atributos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/atributosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Personaje: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div class="cajaAtributos">
                <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajeAmigo?id=${requestScope.personaje.id}&amigo=${requestScope.personaje.usuario.id}'">Volver</button></div>
                <div class="personajeAtributos">
                    <c:forEach var="patributo" items="${listaPAtributos}" varStatus="index">
                        <div class="puntos">
                            <div class="puntos-contenedor">
                                <label for="Atributo_${patributo.atributos.nombre}" id="atrPersonaje_${patributo.atributos.nombre}">${patributo.atributos.nombre}:</label>
                                <input type="number" name="atributo_${patributo.atributos.nombre}" id="Atributo_${patributo.atributos.nombre}" value="${patributo.valor}" readonly>
                                <div class="modificadores"></div>
                                <div> 
                                    <c:choose>
                                        <c:when test="${patributo.salvacion == 'Si'}">
                                            Competente
                                        </c:when>
                                        <c:otherwise>
                                            No Competente
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
        <script src="/TFG/js/personajes/personajePerfilAtributosJS.js"></script>
    </body>
</html>

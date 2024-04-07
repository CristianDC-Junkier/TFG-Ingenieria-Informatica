<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Atributos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/atributosCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Personaje: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div><button class="botonAtrasPJ" onclick="location.href = '/TFG/Personajes/personaje?id=${requestScope.personaje.id}'">Volver</button></div>
            <div class="personajeAtributos">
                <div class="cajaAtributos">
                    <c:forEach var="patributo" items="${listaPAtributos}" varStatus="status">
                        <div class="puntos">
                            <div class="puntos-contenedor">
                                <c:choose>
                                    <c:when test="${patributo.salvacion == 'Si'}">
                                        <label for="Atributo_${patributo.atributos.nombre}" id="atrPersonaje_${patributo.atributos.nombre}">${patributo.atributos.nombre}:</label>
                                        <input type="number" name="atributo_${patributo.atributos.nombre}" id="Atributo_${patributo.atributos.nombre}" value="${patributo.valor}" readonly>
                                        <div class="modificadores"></div>
                                        <div>
                                            &nbsp;Salvacion: ${requestScope.listaPAtributosSalvacion[status.index]}&nbsp; Competente
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <label for="Atributo_${patributo.atributos.nombre}" id="atrPersonaje_${patributo.atributos.nombre}">${patributo.atributos.nombre}:</label>
                                        <input type="number" name="atributo_${patributo.atributos.nombre}" id="Atributo_${patributo.atributos.nombre}" value="${patributo.valor}" readonly>
                                        <div class="modificadores"></div>
                                        <div>
                                            &nbsp;Salvacion: ${requestScope.listaPAtributosSalvacion[status.index]}&nbsp; No Competente
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
        <script src="/TFG/js/personajes/personajeAtributosJS.js"></script>
    </body>
</html>

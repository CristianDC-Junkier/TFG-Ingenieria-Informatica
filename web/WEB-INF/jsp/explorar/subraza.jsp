<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Razas\Raza</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/razaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">SubRaza: ${requestScope.subraza.nombre}
                <button class="botonArribaSubRazas" onclick="location.href = '/TFG/Explorar/raza?idRaza=${idRaza}'">Volver</button></h2>
            <hr color="black">
            <div class="arribaDatosRaza">
                <div class="datosIzquierdaRaza">
                    <div class="datosIzquierdaArribaRaza">
                        <img src="${requestScope.imagenRaza}"/>
                        <div class="caracteristicasRaza">
                            <h3>Características</h3>
                            <ul>
                                <li>Velocidad: ${requestScope.subraza.velocidad}</li>
                                <li>Tamaño:${requestScope.subraza.tamano}</li>                    
                                <li>Atributos:
                                    <c:choose>
                                        <c:when test="${razaEAtributos == '1'}">
                                            Elige una habilidad con un +2 y otra con un +1
                                        </c:when>
                                        <c:when test="${razaEAtributos == '2'}">
                                            Elige 3 habilidades con un +1
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="atributo" items="${razaAtributos}" varStatus="status">
                                                ${atributo.modificador} ${atributo.atributo}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                            </c:forEach> 
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <c:if test="${requestScope.subraza.descripcion!='-'}">
                        <div class = "datosIzquierdaAbajoRaza">
                            <p>${requestScope.subraza.descripcion}</p>
                        </div>
                    </c:if>
                </div>
                <div class="datosDerechaRaza">
                    <h3>Rasgos</h3>
                    <c:forEach var="rasgos" items="${subrazaRasgos}" varStatus="status">
                        <p>${rasgos.nombre}:${rasgos.descripcion}</p><c:if test="${not status.last}"><br></c:if>
                    </c:forEach>
                    <br>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

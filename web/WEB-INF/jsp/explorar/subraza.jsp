<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Razas\Raza</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/claseCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">SubRaza: ${requestScope.subraza.nombre}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button onclick="location.href = '/TFG/Explorar/raza?idRaza=${idRaza}'">Volver</button></h2>
            <hr color="black">
            <div class="arribaDatosClase">
                <div class="datosIzquierdaClase">
                    <div class="datosIzquierdaArribaClase">
                        <img src="${requestScope.imagenRaza}"/>
                        <div class="caracteristicasClase">
                            <h3>Características</h3>
                            <ul>
                                <li>Velocidad: ${requestScope.subraza.velocidad}</li>
                                <li>Tamaño:${requestScope.subraza.tamano}</li>                    
                                <li>Atributos:
                                    <c:forEach var="atributo" items="${subrazaAtributos}" varStatus="status">
                                        ${atributo.modificador} ${atributo.atributo}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                    </c:forEach> <c:if test="${razaEAtributos!= null}"> ó Elige ${razaEAtributos.elegircan} atributos con ${razaEAtributos.elegirmod}.</c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class = "datosIzquierdaAbajoClase">
                        <c:if test="${requestScope.subraza.descripcion!='-'}"><p>${requestScope.subraza.descripcion}</p></c:if>
                        </div>
                    </div>
                    <div class="datosDerechaClase">
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

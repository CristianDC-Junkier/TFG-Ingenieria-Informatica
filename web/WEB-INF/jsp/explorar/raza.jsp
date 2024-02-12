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
            <h2 class="Titulos">Raza: ${requestScope.raza.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosRaza">
                <div class="datosIzquierdaRaza">
                    <div class="datosIzquierdaArribaRaza">
                        <img src="${requestScope.imagenRaza}"/>
                        <div class="caracteristicasRaza">
                            <h3>Características</h3>
                            <ul>
                                <li>Velocidad: ${requestScope.razaExtra.velocidad}</li>
                                <li>Tamaño:${requestScope.razaExtra.tamano}</li>                    
                                <li>Idiomas:${requestScope.raza.idiomas}</li>
                                <li>Edad:${requestScope.raza.edad}</li>
                                <li>Atributos:
                                    <c:forEach var="atributo" items="${razaAtributos}" varStatus="status">
                                        ${atributo.modificador} ${atributo.atributo}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                    </c:forEach> <c:if test="${razaEAtributos!= null}"> ó Elige ${razaEAtributos.elegircan} atributos con ${razaEAtributos.elegirmod}.</c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class = "datosIzquierdaAbajoRaza">
                            <p>${requestScope.raza.descripcion}</p>
                    </div>
                </div>
                <div class="datosDerechaRaza">
                    <h3>Rasgos</h3>
                    <c:forEach var="rasgos" items="${razaRasgos}" varStatus="status">
                        <p>${rasgos.nombre}:${rasgos.descripcion}</p><c:if test="${not status.last}"><br></c:if>
                    </c:forEach>
                    <br>
                    <c:forEach var="subraza" items="${listaSubRazas}" varStatus="status">
                        <c:if test="${status.first}"><h3>Subrazas</h3></c:if>
                            <br>
                            <button class="botonArriba" onclick="location.href = '/TFG/Explorar/subraza?idRaza=${raza.id}&nombreSubRaza=${subraza.nombre}'">
                            <h4>${subraza.nombre}</h4></button>
                    </c:forEach>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

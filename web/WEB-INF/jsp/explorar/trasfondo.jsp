<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Trasfondos\Trasfondo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/trasfondoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Trasfondo: ${requestScope.trasfondo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosTrasfondo">
                <div class="datosIzquierdaTrasfondo">
                    <div class="datosIzquierdaArribaTrasfondo">
                        <div class="caracteristicasTrasfondo">
                            <h3>Características</h3>
                            <ul>
                                <li>Idiomas: ${requestScope.trasfondo.idiomas}</li>
                                <li>Herramientas: ${requestScope.trasfondo.cherramientas}</li>
                                <li>
                                    <c:choose>
                                        <c:when test="${requestScope.trasfondo.elegirhab == '0'}" >
                                            Competencia en Habilidades:
                                            <c:forEach var="habilidad" items="${habtrasfondo}" varStatus="status">
                                                ${habilidad.nombre}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                            </c:forEach> 
                                        </c:when>
                                        <c:when test="${requestScope.trasfondo.elegirhab == '1'}">
                                            Competencia en Habilidades: 
                                            <c:forEach var="habilidad" items="${habtrasfondo}">
                                                ${habilidad.nombre}.
                                            </c:forEach>
                                            <br>Elige 1 entre: 
                                            <c:forEach var="Ehabilidad" items="${eletrasfondo}" varStatus="status">
                                                ${Ehabilidad.nombre}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                            </c:forEach> 
                                        </c:when>
                                        <c:when test="${requestScope.trasfondo.elegirhab == '2'}">
                                            Competencia en Habilidades:
                                            Elige 2 entre: 
                                            <c:forEach var="Ehabilidad" items="${eletrasfondo}" varStatus="status">
                                                ${Ehabilidad.nombre}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                            </c:forEach> 
                                        </c:when>
                                    </c:choose>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoTrasfondo">
                        <p>${requestScope.trasfondo.descripcion}</p>
                    </div>
                    <br><br>
                    <div class = "datosIzquierdaAbajoFinalTrasfondo"> 
                        <h3>Equipo Inicial</h3>
                        <p>${requestScope.trasfondo.equipo}</p>
                    </div>
                </div>
                <div class="datosDerechaTrasfondo">
                    <h3>Puedes elegir uno: </h3>
                    <br>
                    <c:forEach var="Rtrasfondo" items="${listaRasgosTrasfondos}">
                        <h3>Rasgo: ${Rtrasfondo.nombre}</h3>
                        <p>${Rtrasfondo.descripcion}</p>
                    </c:forEach> 
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Clases\Clase</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/claseCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">SubClase: ${requestScope.subclase.nombre}
                <button class="botonArribaSubClases" onclick="location.href = '/TFG/Explorar/clase?clase=${requestScope.clase.id}'">Volver</button></h2>
            <hr color="black">
            <div class="arribaDatosClase">
                <div class="datosIzquierdaClase">
                    <div class="datosIzquierdaArribaClase">
                        <img src="/TFG/img/clases/${requestScope.imagen}.jfif"/>
                        <div class="caracteristicasClase">
                            <h3>Características</h3>
                            <ul>
                                <li>Dado de golpe: ${requestScope.clase.dpg} + Constitución</li>
                                <li>Habilidad de hechizos: ${requestScope.clase.habhechizos}</li>                    
                                <li>CA Hechizos: 8 + BC + ${requestScope.clase.habhechizos}</li>
                                <li>Tiradas de salvación:
                                    <c:forEach var="salClase" items="${listaSalvacion}" varStatus="status">
                                        ${listaSalvacion}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                    </c:forEach>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class = "datosDerechaClase">
                    <br>
                    <p>${requestScope.subclase.descripcion}</p>
                </div>
            </div>
            <div class="abajoRasgosClase">
                <div>
                    <h3>Rasgos</h3>
                    <div><c:forEach var="rasClase" items="${listarasgos}" varStatus="status">
                            ${rasClase.nombre}: ${rasClase.descripcion} <c:if test="${not status.last}"><br><br></c:if><c:if test="${status.last}"><br></c:if>
                        </c:forEach></div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

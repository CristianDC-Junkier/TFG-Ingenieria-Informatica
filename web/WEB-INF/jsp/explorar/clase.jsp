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
            <h2 class="Titulos">Clase: ${requestScope.clase.nombre}</h2>
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
                    <div class = "datosIzquierdaAbajoClase">
                        <p>${requestScope.clase.descripcion}</p>
                    </div>
                    <br><br>
                    <div>
                        <h3>Competencias</h3>
                        <p>Armaduras: ${requestScope.clase.comparmaduras}</p>
                        <p>Armas: ${requestScope.clase.comparmas}</p>
                        <p>Herramientas: ${requestScope.clase.compherramientas}</p>
                        <p>Elige ${requestScope.clase.elegirhab} entre:</p>
                        <c:forEach var="habClase" items="${listahabilidades}" varStatus="status">
                            ${habClase}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                        </c:forEach>
                    </div>
                    <br><br>
                    <div>
                        <h3>Equipo Inicial</h3>
                        <p>${requestScope.equipoinicial}</p>
                        <p>Si no quieres nada: ${requestScope.clase.oroinicial}</p>
                    </div>
                </div>
                <div class="datosDerechaClase">
                    <c:out value="${tablaHechizos}" escapeXml="false" />  
                </div>
            </div>
            <br><br>
            <div class="abajoSubclasesClase">
                <div>
                    <h3>Subclases</h3>
                    <div><c:forEach var="subClase" items="${listaSubclases}" varStatus="status">
                            <p>${subClase.nombre}</p>
                        </c:forEach></div>
                </div>
            </div>
            <div class="abajoRasgosClase">
                <div>
                    <h3>Rasgos</h3>
                    <div><c:forEach var="rasClase" items="${listarasgos}" varStatus="status">
                            ${rasClase.nombre}:${rasClase.descripcion} <c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                        </c:forEach></div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

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
            <h2 class="Titulos">Raza: ${requestScope.raza.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosClase">
                <div class="datosIzquierdaClase">
                    <div class="datosIzquierdaArribaClase">
                        <img src="/TFG/img/clases/${requestScope.imagen}.jfif"/>
                        <div class="caracteristicasClase">
                            <h3>Características</h3>
                            <ul>
                                <li>Velocidad: ${requestScope.subrazaPrincipal.velocidad}</li>
                                <li>Tamaño:${requestScope.subrazaPrincipal.tamano}</li>                    
                                <li>Idiomas:${requestScope.raza.idiomas}</li>
                                <li>Edad:${requestScope.raza.edad}</li>
                                <li>Competencias:${requestScope.raza.competencias}</li>
                                <li>
                                    <c:forEach var="atributo" items="${listaatributos}" varStatus="status">
                                        ${atributo.modificador} ${atributo.atributo}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                    </c:forEach>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoClase">
                        <p>${requestScope.raza.descripcion}</p>
                    </div>
                    <br>
                    <h3>Rasgos</h3>
                    <c:forEach var="rasgos" items="${subrazaPrincipal}" varStatus="status">
                        <p>${rasgos.nombre}:${rasgos.descripcion}</p><c:if test="${not status.last}"><br></c:if>
                    </c:forEach>
                </div>
                <div class="datosDerechaClase">
                    <h3>Subrazas</h3>
                    <c:forEach var="subraza" items="${listaSubraza}">
                        <br>
                        <h4>${subraza.nombre}</h4>
                        <p>${subraza.descripcion}</p>
                        <br><hr style="border-style: dashed;">
                        <h4>Atributos</h4>
                        <p>
                            <c:forEach var="atributos" items="${subraza.usaraza}" varStatus="status">
                                ${atributos.modificador} ${atributos.atributo}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                            </c:forEach>
                        </p>
                        <br><hr style="border-style: dashed;">
                        <h4>Rasgos</h4>
                        <c:forEach var="rasgos" items="${subraza.usaraza}" varStatus="status">
                            <p>${rasgos.nombre}:${rasgos.descripcion}</p><c:if test="${not status.last}"><br></c:if>
                        </c:forEach>

                    </c:forEach>
                </div>
            </div>
            <br><br>
            <div class="abajoSubclasesClase">
                <div>
                    <h3>Subclases</h3>
                </div>
            </div>
            <div class="abajoRasgosClase">
                <div>
                    <h3>Subclases</h3>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

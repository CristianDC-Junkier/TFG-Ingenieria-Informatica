<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Hechizos\Hechizo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/hechizoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Trasfondo ${requestScope.hechizo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosHechizo">
                <div class="datosIzquierdaHechizo">
                    <div class="datosIzquierdaArribaHechizo">
                        <div class="caracteristicasHechizo">
                            <h3>Hechizo</h3>
                            <ul>
                                <li>Escuela: ${requestScope.hechizo.escuela}</li>
                                <li>{requestScope.hechizo.nivel}</li>                    
                                <li>Componentes:${requestScope.hechizo.componentes}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoHechizo">
                        <h3>Características</h3>
                        <p>Tiempo de lanzamiento: ${requestScope.hechizo.tlanzamiento}.</p>
                        <p>Alcance: ${requestScope.hechizo.alcance}.</p>
                        <p>Duración: ${requestScope.hechizo.duracion}.</p>
                        <c:choose>
                            <c:when test="${requestScope.hechizo.ritual == 0}">
                                <p>No puede ser lanzado como ritual.</p>
                            </c:when>
                            <c:otherwise>
                                <p>Si puede ser lanzado como ritual.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br><br>
                    <div class = "datosIzquierdaAbajoFinalHechizo">
                        <c:choose>
                            <c:when  test="${requestScope.hechizo.dano != '-'}">
                                <p>Daño: ${requestScope.hechizo.dano}.</p>
                            </c:when>
                            <c:otherwise>
                                <p>Este ataque no realiza ningún daño.</p>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${requestScope.hechizo.tsalvacion != '-'}">
                                <p>Tirada Salvación: ${requestScope.hechizo.dano}.</p>
                            </c:when>
                            <c:otherwise>
                                <p>Funciona con una tirada de ataque usando la característica principal del lanzador.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="datosDerechaHechizo">
                    <p>${requestScope.hechizo.descripcion}.</p>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

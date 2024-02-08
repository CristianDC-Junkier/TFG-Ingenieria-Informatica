<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Hechizos\Hechizo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/objetoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Objeto: ${requestScope.equipo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosEquipo">
                <div class="datosIzquierdaEquipo">
                    <div class="datosIzquierdaArribaEquipo">
                        <div class="caracteristicasEquipo">
                            <h3>Características Principales</h3>
                            <ul>
                                <li>Tipo: ${requestScope.equipo.tipo}</li>
                                <li>Categoría: ${requestScope.equipo.categoria}</li>                    
                                <li>Precio: ${requestScope.equipo.precio}</li>
                                <li>Peso: ${requestScope.equipo.peso}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoEquipo">
                        <h3>Características Secundarias</h3>
                        <c:if test="${requestScope.equipo.dano != null}">
                            <p>Daño: ${requestScope.equipo.dano}.</p>
                        </c:if>
                        <c:if test="${requestScope.equipo.carmadura != null}">
                            <p>Daño: ${requestScope.equipo.carmadura}.</p>
                        </c:if>
                        <c:if test="${requestScope.equipo.pgolpe != null}">
                            <p>Puntos de golpe: ${requestScope.equipo.pgolpe}.</p>
                        </c:if>
                        <c:if test="${requestScope.alcance != null}">
                            <p>Alcance Máximo: ${requestScope.alcance.maximo}.</p>
                            <p>Alcance Mínimo: ${requestScope.alcance.minimo}.</p>
                        </c:if>
                        <c:if test="${requestScope.equipo.dano == null && requestScope.equipo.carmadura == null 
                                      && requestScope.equipo.pgolpe == null && requestScope.alcance == null}">
                              <p>No tiene ninguna característica importante.</p>
                        </c:if>
                    </div>
                    <br>
                    <div class="datosIzquierdaAbajoFinalEquipo">
                        <p>${requestScope.equipo.descripcion}.</p>
                    </div>
                </div>
                <c:out value="${proEquipo}" escapeXml="false" /> 
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

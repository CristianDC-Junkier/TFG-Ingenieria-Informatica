<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Monstruos\Monstruo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/monstruoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Monstruo: ${requestScope.monstruo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosMonstruo">
                <div class="datosIzquierdaMonstruo">
                    <div class="datosIzquierdaArribaMonstruo">
                        <div class="caracteristicasMonstruo">
                            <h3>Características</h3>
                            <ul>
                                <li>Tipo: ${requestScope.monstruo.tipo}</li>
                                <li>Valor de Desafío: ${requestScope.monstruo.vdesafio}</li>
                                <li>Alineamiento: ${requestScope.monstruo.alineamiento}</li>
                                <li>Idiomas: ${requestScope.monstruo.idiomas}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoMonstruo">
                        <div>
                            <h3>Valores</h3>
                            <ul>
                                <li>Puntos de golpe: ${requestScope.monstruo.pgolpe}</li>
                                <li>Clase de Armadura: ${requestScope.monstruo.carmadura}</li>
                                <li>Velocidad: ${requestScope.monstruo.velocidad}</li>
                                <li>Tamaño: ${requestScope.monstruo.tamano}</li>
                            </ul>
                        </div>
                    </div>
                    <br>
                    <div class = "datosIzquierdaAbajoFinalMonstruo"> 
                        <div>
                            <h3>Atributos</h3>
                            <ul style="column-count: 2; column-gap: 20px;">
                                <c:forEach var="atrMonstruo" items="${atrMonstruos}">
                                    <li>${atrMonstruo.tienemonstruoPK.atributo}: ${atrMonstruo.valor}${atrMonstruo.modificador}</li>
                                    </c:forEach>
                            </ul>
                            <br><hr style="border-style: dashed;">
                            <ul>
                                <li>Sentidos: ${requestScope.monstruo.sentidos}</li>
                                <li>Habilidades: 
                                    <c:forEach var="habMonstruo" items="${habMonstruos}" varStatus="status">
                                        ${habMonstruo.competentemonstruoPK.habilidad}${habMonstruo.competencia}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                    </c:forEach>
                                </li>
                                <c:if test="${requestScope.estMonstruo != null}"><li>Inmunidad a estados: 
                                        <c:forEach var="estMonstruo" items="${estMonstruos}" varStatus="status">
                                        <li>${estMonstruo.nombre}<c:if test="${not status.last}">, </c:if><c:if test="${status.last}">.</c:if>
                                        </c:forEach>
                                    </li>
                                </c:if>
                                <c:if test="${requestScope.monstruo.resdano != '-'}"><li>Resistencia al daño: ${requestScope.monstruo.resdano}</li></c:if>
                                <c:if test="${requestScope.monstruo.inmdano != '-'}"><li>Inmunidad al daño: ${requestScope.monstruo.inmdano}</li></c:if>
                                <c:if test="${requestScope.monstruo.vuldano != '-'}"><li>Vulnerabilidad al daño: ${requestScope.monstruo.vuldano}</li></c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="datosDerechaMonstruo">
                        <p>${requestScope.monstruo.descripcion}</p>
                    <br><hr style="border-style: dashed;">
                    <h3>Rasgos: </h3>
                    <p><c:forEach var="rasMonstruo" items="${rasMonstruos}">
                            ${rasMonstruo.nombre}: ${rasMonstruo.descripcion}<br>
                        </c:forEach></p>
                    <br><hr style="border-style: dashed;">
                    <h3>Acciones: </h3>
                    <p><c:forEach var="accMonstruo" items="${accMonstruos}">
                            <c:if test="${accMonstruo.tipo == 'Normal'}">
                                ${accMonstruo.nombre}: ${accMonstruo.descripcion}<br>
                            </c:if>
                        </c:forEach></p>
                    <br><hr style="border-style: dashed;">
                    <h3>Acciones Legendarias: </h3>
                    <p><c:forEach var="accMonstruo" items="${accMonstruos}">
                            <c:if test="${accMonstruo.tipo == 'Lengendaria'}">
                                ${accMonstruo.nombre}: ${accMonstruo.descripcion}<br>
                            </c:if>
                        </c:forEach></p>
                    <br><hr style="border-style: dashed;">
                    <h3>Acciones Míticas: </h3>
                    <p><c:forEach var="accMonstruo" items="${accMonstruos}">
                            <c:if test="${accMonstruo.tipo == 'Mitica'}">
                                ${accMonstruo.nombre}: ${accMonstruo.descripcion}<br>
                            </c:if>
                        </c:forEach></p>
                    <br><hr style="border-style: dashed;">
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

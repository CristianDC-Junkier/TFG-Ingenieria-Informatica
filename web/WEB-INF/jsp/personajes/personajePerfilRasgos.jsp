<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Rasgos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/rasgosCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Rasgos: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div>
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${requestScope.personaje.id}'">Volver</button>
            </div>
            <div class="arribaDatosRasgos">
                <div class="datosIzquierdaRasgos">
                    <div class="datosIzquierdaArribaRasgos">
                        <h3>Espacios de hechizo</h3>
                        <table class="tablaHechizos">
                            <tr>
                                <th>Nivel Actual</th>
                                <td>${requestScope.personaje.nivel}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv1</th>
                                <td>${requestScope.pjHechizosClase.nv1}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv2</th>
                                <td>${requestScope.pjHechizosClase.nv2}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv3</th>
                                <td>${requestScope.pjHechizosClase.nv3}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv4</th>
                                <td>${requestScope.pjHechizosClase.nv4}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv5</th>
                                <td>${requestScope.pjHechizosClase.nv5}</td>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv6</th>
                                <td>${requestScope.pjHechizosClase.nv6}</td>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv7</th>
                                <td>${requestScope.pjHechizosClase.nv7}</td>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv8</th>
                                <td>${requestScope.pjHechizosClase.nv8}</td>
                            </tr>
                            <tr>
                                <th>Hechizo nv9</th>
                                <td>${requestScope.pjHechizosClase.nv9}</td>
                            </tr>
                        </table>   
                    </div>
                    <div class="datosDerechaRasgos">
                        <div class="caracteristicasRasgos">
                            <h3>Características</h3>
                            <ul>
                                <li>Nombre: ${requestScope.personaje.nombre}</li>
                                <li>Rasgos por Clase:${requestScope.pjTablaClase.rasgos}</li>                    
                                <li>BC:+${requestScope.pjTablaClase.bc}</li>
                                <li>Trucos:${requestScope.pjTablaClase.trucos}</li>
                                <li>Hechizos:${requestScope.pjTablaClase.hechizos}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoRasgos">
                        <h3>Rasgos por Clase</h3>
                        <c:forEach var="rasgo" items="${pjRasgosRaza}" varStatus="status">
                            ${rasgo.nombre}: ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"><br><br></c:if>
                        </c:forEach>
                        <h3>Rasgos por Subclase</h3>
                        <c:forEach var="rasgo" items="${pjRasgosSubClase}" varStatus="status">
                            ${rasgo.nombre}: ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"><br><br></c:if>
                        </c:forEach> 
                        <h3>Rasgos por Raza</h3>
                        <c:forEach var="rasgo" items="${pjRasgosRaza}" varStatus="status">
                            ${rasgo.nombre}: ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"><br><br></c:if>
                        </c:forEach> 
                        <h3>Rasgos por Subraza</h3>
                        <c:forEach var="rasgo" items="${pjRasgosSubraza}" varStatus="status">
                            ${rasgo.nombre}: ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"><br><br></c:if>
                        </c:forEach>
                        <h3>Rasgos por Trasfondo</h3>
                        <c:forEach var="rasgo" items="${pjRasgosTrasfondos}" varStatus="status">
                            ${rasgo.nombre}: ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"><br><br></c:if>
                        </c:forEach> 
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

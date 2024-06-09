<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Rasgos</title>
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
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Personajes/personaje?id=${requestScope.personaje.id}'">Volver</button>
            </div>
            <div class="arribaDatosRasgos">
                <div class="datosIzquierdaRasgos">
                    <div class="datosIzquierdaArribaRasgos">
                        <h3>Espacios de hechizo</h3>
                        <br>
                        <table class="tablaHechizos">
                            <tr>
                                <th>Nivel Actual - ${requestScope.personaje.nivel}</th>
                            </tr>
                            <tr>
                                <th>Hechizo nv1 - ${requestScope.pjHechizosClase.nv1}</th>
                            </tr>
                            <tr>
                                <th>Hechizo nv2 - ${requestScope.pjHechizosClase.nv2}</th>
                            </tr>
                            <tr>
                                <th>Hechizo nv3 - ${requestScope.pjHechizosClase.nv3}</th>
                            </tr>
                            <tr>
                                <th>Hechizo nv4 - ${requestScope.pjHechizosClase.nv4}</th>

                            </tr>
                            <tr>
                                <th>Hechizo nv5 - ${requestScope.pjHechizosClase.nv5}</th>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv6 - ${requestScope.pjHechizosClase.nv6}</th>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv7 - ${requestScope.pjHechizosClase.nv7}</th>
                            </tr>                            
                            <tr>
                                <th>Hechizo nv8 -${requestScope.pjHechizosClase.nv8} </th>
                            </tr>
                            <tr>
                                <th>Hechizo nv9 - ${requestScope.pjHechizosClase.nv9}</th>
                            </tr>
                        </table>   
                    </div>
                </div>
                <div class="datosDerechaRasgos">
                    <div class="caracteristicasRasgos">
                        <h3>Características</h3>
                        <ul>
                            <li>Nombre: ${requestScope.personaje.nombre}</li>
                            <li>BC: +${requestScope.pjTablaClase.bc}</li>
                            <li>Trucos: ${requestScope.pjTablaClase.trucos}</li>
                            <li>Hechizos: ${requestScope.pjTablaClase.hechizos}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class = "datosIzquierdaAbajoRasgos">
                <h3>Rasgos por Clase</h3>
                <c:forEach var="rasgo" items="${pjRasgosClase}" varStatus="status">
                    <strong>${rasgo.rasgos.nombre}:</strong> ${rasgo.rasgos.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"></c:if>
                </c:forEach>
                <br><br>
                <h3>Rasgos por Subclase</h3>
                <c:forEach var="rasgo" items="${pjRasgosSubClase}" varStatus="status">
                    <strong>${rasgo.rasgos.nombre}:</strong> ${rasgo.rasgos.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"></c:if>
                </c:forEach> 
                <br><br>
                <h3>Rasgos por Raza</h3>
                <c:forEach var="rasgo" items="${pjRasgosRaza}" varStatus="status">
                    <strong>${rasgo.nombre}:</strong> ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"></c:if>
                </c:forEach> 
                <br><br>
                <h3>Rasgos por Subraza</h3>
                <c:forEach var="rasgo" items="${pjRasgosSubraza}" varStatus="status">
                    <strong>${rasgo.nombre}:</strong> ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"></c:if>
                </c:forEach>
                <br><br>
                <h3>Rasgos por Trasfondo</h3>
                <c:forEach var="rasgo" items="${pjRasgosTrasfondos}" varStatus="status">
                    <strong>${rasgo.nombre}:</strong> ${rasgo.descripcion}<c:if test="${not status.last}"><br></c:if><c:if test="${status.last}"></c:if>
                </c:forEach> 
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

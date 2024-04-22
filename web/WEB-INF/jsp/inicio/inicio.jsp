<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Inicio</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/TFG/css/inicio/inicioCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Inicio</h2>
            <div class="BloqueInicio">
                <div class="EleccionInicio">
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <h2>¡Bienvenido de nuevo ${sessionScope.user.apodo}!</h2>
                            <c:if test="${sessionScope.peticiones != null}">
                                <p>Tienes ${sessionScope.peticiones} peticion/es de amistad</p>
                            </c:if>
                            <p>¿Quieres crear algún personaje nuevo?</p>
                            <button class="botonInicioSesion" onclick="location.href = '/TFG/Formularios/crearpersonaje'">Personajes</button>
                            <p>¿Quieres crear alguna mesa nueva?</p>
                            <button class="botonRegistro" onclick="location.href = '/TFG/Formularios/crearmesa'">Mesas</button>
                        </c:when>
                        <c:otherwise>
                            <h2>¡Bienvenido de nuevo!</h2>
                            <p>Inicia sesión para poder disfrutar todas las funciones</p>
                            <button class="botonInicioSesion" onclick="location.href = '/TFG/Formularios/iniciosesion'">Iniciar Sesión</button>
                            <p>¿Aún no tienes cuenta?<br>Únete fácilmente:</p>
                            <button class="botonRegistro" onclick="location.href = '/TFG/Formularios/registro'">Registrarse</button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="FotosTextoInicio">
                    <div class="FotosInicio">
                        <img id="Guide4foto" class="FotoInicio1" src="/TFG/img/Guide4White.bmp" alt="alt"/>
                        <img class="FotoInicio2" src="/TFG/img/luzpng.png" alt="alt"/>
                    </div>
                    <div class="TextoInicio">
                        <p>¿Qué es Dungeons & Dragons y como se juega?</p>
                        <button class="botonIntroducción" onclick="location.href = '/TFG/Principal/introduccion'"> Infórmate aquí</button>
                        <c:if test="${sessionScope.user != null}">
                            <button class="botonIntroducción" onclick="location.href = '/TFG/Foro/inicio'"> Visita nuestro foro</button>
                        </c:if>
                        <hr style="margin-left: -10px; margin-top: 10px;">
                        <p>¿Necesitas algo más?, No dudes en descargar el manual para todo</p>
                        <a href="/TFG/manual/ManualCasiTodo.pdf" download="Manual Casi Todo.pdf">Descargar archivo</a>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/bannerCookies.jsp" />
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

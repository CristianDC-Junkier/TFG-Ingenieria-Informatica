<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Características</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/caracteristicasCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Personaje: ${requestScope.personaje.nombre}</h2>
            <hr color="black">
            <div>
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${requestScope.personaje.id}'">Volver</button>
                <button class="botonAtrasPJ" onclick="location.href = '/TFG/Formularios/modificarPersonajeCaracteristicas?id=${requestScope.personaje.id}'">Modificar</button>
            </div>
            <div class="arribaDatosCaracteristicas">
                <div class="datosIzquierdaCaracteristicas ">
                    <div class="datosIzquierdaArribaCaracteristicas">
                        <img src="${requestScope.personajeImagen}"/>
                        <div class="caracteristicasCaracteristicas">
                            <h3>Características</h3>
                            <ul>
                                <li>Nombre: ${requestScope.personaje.nombre}</li>
                                <li>Alineamiento:${requestScope.personaje.alineamiento}</li>                    
                                <li>Idiomas:${requestScope.personaje.idiomas}</li>
                                <li>Edad:${requestScope.personaje.edad}</li>
                                <li>BC:+${requestScope.personajeBC}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoCaracteristicas">
                        <p>Apariencia: ${requestScope.personaje.apariencia}</p>
                        <p>Historia: ${requestScope.personaje.historia}</p>
                    </div>
                </div>
                <div class="datosDerechaCaracteristicas">
                    <h3>Vinculos</h3>
                    <p>${requestScope.personaje.vinculos}</p>
                    <br>
                    <h3>Rasgos de personalidad</h3>
                    <p>${requestScope.personaje.raspersonalidad}</p>
                    <br>
                    <h3>Defectos</h3>
                    <p>${requestScope.personaje.defectos}</p>
                    <br>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

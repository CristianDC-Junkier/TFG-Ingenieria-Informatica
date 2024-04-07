<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Modificar_Habilidades</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearpersonajeCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroPersonaje">
                <h2 class="TitulosFormulario">Modificar Habilidades</h2>
                <form id = formRegistro action="/TFG/Personajes/modificarPersonajeHabilidades?id=${requestScope.personaje.id}" method="POST">
                    <div>
                        <div id="habilidades">
                            <c:forEach var="pHabilidad" items="${listaPersonajeHabilidades}" varStatus="status">
                                <li>
                                    <p class="tituloHabilidad">${pHabilidad.habilidades.nombre}: ${requestScope.listaValoresHab[status.index]} - </p>
                                    <p class="valoresHabilidad"><label for="competencia_${pHabilidad.habilidades.nombre}">Competencia: </label>
                                        <input type="checkbox" id="competencia_habilidad" name="habilidades" value="${pHabilidad.habilidades.nombre}" 
                                               <c:if test="${pHabilidad.competencia == 'Si'}">checked</c:if>></p>
                                    </li>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.pathname = '/TFG/Personajes/personajePerfilHabilidades'" value="Volver">
                        <input type="submit" id="botonEnviar" value="Modificar Habilidades">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    </body>
</html>

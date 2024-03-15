<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Crear_Mesa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearmesaCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroMesa">
                <h2 class="TitulosFormulario">Crear Personaje</h2>
                <form id = formRegistro action="/TFG/Personajes/crearPersonaje" method="POST">
                    <c:choose>
                        <c:when test="${requestScope.personajesTotales != 10}">
                            <div id="Bloque1">
                                <div>
                                    <label for="nombrePersonaje" id="nombrePersonaje">Nombre:</label>
                                    <input type="text" id="namePersonaje" name="personaje_nombre" onkeyup="realizarBusqueda('namePersonaje', 'namePersonajeInput')" required/>
                                    <span  id="namePersonajeInput">✖</span>
                                </div>
                                <div>
                                    <label for="classPersonaje" id="clasePersonaje">Clase:</label>
                                    <select required name="clase" id="classPersonaje" >
                                        <option value="-" selected>-</option>
                                        <c:forEach var="clase" items="${listaClases}">
                                            <option value="${clase.nombre}">${clase.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label for="subclassPersonaje" id="subclasePersonaje">SubClase:</label>
                                    <select required name="subclase" id="subclassPersonaje" >
                                        <option value="-">-</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="racePersonaje" id="razaPersonaje">Raza:</label>
                                    <select required name="raza" id="racePersonaje" >
                                        <option value="-" selected>-</option>
                                        <c:forEach var="raza" items="${listaRazas}">
                                            <option value="${raza.nombre}">${raza.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div>
                                    <label for="subracePersonaje" id="subrazaPersonaje">SubRaza:</label>
                                    <select required name="subraza" id="subracePersonaje" >
                                        <option value="-">-</option>
                                    </select>
                                </div>
                                <c:if test="${requestScope.msj!=null}">
                                    <div>
                                        ${requestScope.msj}
                                    </div>
                                </c:if>
                            </div>
                            <div id="Bloque2" style="display: none;">
                                <div id="habilidadesRaza">
                                    
                                </div>
                                <div id="habilidadesClase">
                                    
                                </div>
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                                <input id="botonAvanzarFormulario" type="button" onclick="AvanzarFormulario(2)" value="Siguiente">
                                <input  id="botonEnviar" type="hidden" value="Crear Personaje">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                Lo siento, ya tienes demasiados personajes creados.
                                Borra uno para poder crear otro.
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario-Solo" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
        <script src="/TFG/js/formularios/crearpersonajeJS.js"></script>
    </body>
</html>

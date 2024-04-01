<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Subir_Nivel</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearpersonajeCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroPersonaje">
                <h2 class="TitulosFormulario">Crear Personaje</h2>
                <form id = formRegistro action="/TFG/Personajes/crearpersonaje" method="POST">
                    <div id="Bloque1">
                        <div id="tablaClase">
                            Tienes un BC de: ${requestScope.pjTablaClase.bc}<br>
                            Conoces ${requestScope.pjTablaClase.trucos} trucos<br>
                            Conoces ${requestScope.pjTablaClase.hechizos} hechizos<br>
                            <hr>
                        </div>
                        <div id="espacioHechizos">
                            Espacios de hechizos:
                            Nivel 1: ${requestScope.pjHechizosClase.nv1},Nivel 2: ${requestScope.pjHechizosClase.nv2}<br>
                            Nivel 3: ${requestScope.pjHechizosClase.nv3},Nivel 4: ${requestScope.pjHechizosClase.nv4}<br>
                            Nivel 5: ${requestScope.pjHechizosClase.nv5},Nivel 6: ${requestScope.pjHechizosClase.nv6}<br>
                            Nivel 7: ${requestScope.pjHechizosClase.nv7},Nivel 8: ${requestScope.pjHechizosClase.nv8}<br>
                            Nivel 9: ${requestScope.pjHechizosClase.nv9}<br>
                            <hr>
                        </div>
                        <div id="rasgosClase">
                            Rasgos Clase:
                            <c:forEach var="rasgo" items="${pjRasgosClase}">
                                Nombre: ${rasgo.nombre}<br>
                            </c:forEach>
                            <hr>
                        </div>
                        <c:if test="${requestScope.nivelSiguiente == requestScope.nivelSubclase}">
                            <div id="rasgosSubClase">
                                Rasgos SubClase:
                                <c:forEach var="rasgo" items="${pjRasgosSubClase}">
                                    Nombre: ${rasgo.nombre}<br>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <div id="Bloque2" style="display: none;">
                        <div id="eleccionVida">
                            <label for="dadoVida" id="personajeVida">Aumento de  Vida (${requestScope.dadoClase}): </label>
                            <input type="text" id="dadoVida" name="personaje_vida" required/>
                            <button id="IDadoButton" type="button" onclick=""><img id="IDado" src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
                        </div>
                        <c:if test="${requestScope.nivelSiguiente == requestScope.nivelSubclase}">
                            <div id="eleccionSubClase">
                                <label for="subclasePersonaje" id="subclasesPersonaje">Subclase:</label>
                                <select required name="subclase" id="subclasePersonaje" >
                                    <c:forEach var="subclase" items="${listaSubclases}">
                                        <option value="${subclase.id}">${subclase.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <div id="Bloque3-1" style="display: none;">
                        <div id="eleccionDote">
                            <label for="dotePersonaje" id="dotesPersonaje">Dote:</label>
                            <select required name="dote" id="dotePersonaje" >
                                <c:forEach var="dote" items="${listaDotes}">
                                    <option value="${dote.id}">${dote.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div id="Bloque3-2" style="display: none;">
                        <div id="eleccionAtr">
                            <label for="atributoPersonaje" id="dotesPersonaje">Atributo +1:</label>
                            <select required name="atributo1" id="atributoPersonaje" >
                                <c:forEach var="atributo" items="${listaAtributos1}">
                                    <option value="${atributo.id}">${atributo.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="eleccionAtr">
                            <label for="atributoPersonaje" id="dotesPersonaje">Atributo +2:</label>
                            <select required name="atributo2" id="atributoPersonaje" >
                                <c:forEach var="atributo" items="${listaAtributos2}">
                                    <option value="${atributo.id}">${atributo.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="mensajeErrorB3">
                        </div>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                        <c:choose>
                            <c:when test="${requestScope.nivelSiguiente == requestScope.nivelSubclase}">
                                <input id="botonAvanzarFormulario" type="button" onclick="AvanzarFormulario(2)" value="Siguiente">
                            </c:when>
                            <c:otherwise>
                                <input id="botonAvanzarFormulario" type="button" onclick="AvanzarFormulario(3)" value="Siguiente">
                            </c:otherwise>
                        </c:choose>
                        <input  id="botonEnviar" type="hidden" value="Crear Personaje">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
        <script src="/TFG/js/formularios/crearpersonajeJS.js"></script>
    </body>
</html>

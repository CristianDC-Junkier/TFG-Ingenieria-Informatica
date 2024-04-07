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
                <form id = formRegistro action="/TFG/Personajes/personajeSubirNivel?personaje=${requestScope.personaje.id}" method="POST">
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
                    <div id="Bloque2-1" style="display: none;">
                        <div id="eleccionDote">
                            <label for="dotePersonaje" id="dotesPersonaje">Dote:</label>
                            <select required name="dote" id="dotePersonaje" >
                                <c:forEach var="dote" items="${listaDotes}">
                                    <option value="${dote.id}">${dote.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div id="Bloque2-2" style="display: none;">
                        <div id="eleccionAtr">
                            <label for="atributoPersonaje" id="dotesPersonaje">Atributo +1:</label>
                            <select required name="atributo1" id="atributoPersonaje1" >
                                <c:forEach var="atributo" items="${listaAtributos1}">
                                    <option value="${atributo.nombre}">${atributo.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="eleccionAtr">
                            <label for="atributoPersonaje" id="dotesPersonaje">Atributo +2:</label>
                            <select required name="atributo2" id="atributoPersonaje2" >
                                <c:forEach var="atributo" items="${listaAtributos2}">
                                    <option value="${atributo.nombre}">${atributo.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="mensajeErrorB2">
                        </div>
                    </div>
                    <div id="Bloque3" style="display: none;">
                        <input id="eleccionDoteAtr" type="hidden" name="eleccionDoteAtr">
                        <div id="eleccionVida">
                            <label for="dadoVida" id="personajeVida">Aumento de  Vida (${requestScope.dadoClase}): </label>
                            <input type="number" id="dadoVida" name="personaje_vida" max="${requestScope.dadoClaseInteger}" min="1" required/>
                            <button id="IDadoButton" type="button" onclick="tirarDado(${requestScope.dadoClaseInteger})"><img src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
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
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button"
                               onclick="location.pathname = '/TFG/Personajes/personajePerfil'" value="Volver">
                        <div id="valoresEleccion">
                            <input id="botonDoteFormulario" type="button" onclick="AvanzarFormulario(2.1)" value="Elegir Dote">
                            <input id="botoAtributorFormulario" type="button" onclick="AvanzarFormulario(2.2)" value="Elegir Atributos">
                        </div>
                        <input id="botonAvanzarFormulario" type="hidden" onclick="AvanzarFormulario(3)" value="Siguiente">
                        <input  id="botonEnviar" type="hidden" value="Subir de nivel">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/personajeSubirNivelJS.js"></script>
    </body>
</html>

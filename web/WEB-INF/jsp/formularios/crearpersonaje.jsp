<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Crear_Personaje</title>
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
                                    <label for="nombrePersonaje" id="namePersonaje">Nombre:</label>
                                    <input type="text" id="nombrePersonaje" name="personaje_nombre" onkeyup="realizarBusqueda('namePersonaje', 'namePersonajeInput')" required/>
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
                                <div>
                                    <label for="trasPersonaje" id="trasfondoPersonaje">Raza:</label>
                                    <select required name="trasfondo" id="trasPersonaje" >
                                        <option value="-" selected>-</option>
                                        <c:forEach var="trasfondo" items="${listaTrasfondos}">
                                            <option value="${trasfondo.nombre}">${trasfondo.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div id="mensajeError">
                                    <c:if test="${requestScope.msj!=null}">
                                        ${requestScope.msj}
                                    </c:if>
                                </div>
                            </div>
                            <div id="Bloque2" style="display: none;">
                                <div id="habilidadesRaza">
                                </div>
                                <div id="habilidadesClase">
                                </div>
                            </div>
                            <div id="Bloque3" style="display: none;">
                                <c:forEach var="atributo" items="${listaAtributos}">
                                    <div class="puntos">
                                        <div class="number-container">
                                            <label for="Atributo_${atributo.nombre}" id="atrPersonaje_${atributo.nombre}">${atributo.nombre}</label>
                                            <input type="number" id="Atributo_${atributo.nombre}" value="8" min="8" max="15" readonly>
                                            <div class="modificadores">-1</div>
                                        </div>
                                        <div class="btn-container">
                                            <button type="button" class="botonaumentar">+</button>
                                            <button type="button" class="botondecrementar">-</button>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div id="suma">La suma total es: 0 <br> Lo normal para un héroe es tener 21 puntos</div>
                            </div>
                            <div id="Bloque4" style="display: none;">
                                <label for="alinPersonaje" id="alineamientoPersonaje">Alineamiento:</label>
                                <select required name="alineamiento" id="alinPersonaje" >
                                    <option value="Legal Bueno">Legal Bueno</option>
                                    <option value="Neutral Bueno">Neutral Bueno</option>
                                    <option value="Caótico Bueno">Caótico Bueno</option>
                                    <option value="Caótico Neutral">Caótico Neutral</option>
                                    <option value="Neutral">Neutral</option>
                                    <option value="Legal Neutral">Legal Neutral</option>
                                    <option value="Caótico Malvado">Caótico Malvado</option>
                                    <option value="Neutral Malvado">Neutral Malvado</option>
                                    <option value="Legal Malvado">Legal Malvado</option>
                                </select>
                                <label for="edadPersonaje" id="agePersonaje">Edad:</label>
                                <input type="number" id="edadPersonaje" name="personaje_edad" min="8" required/>
                                <label for="aparienciaPersonaje" id="lookPersonaje">Apariencia:</label>
                                <textarea id="aparienciaPersonaje" name="personaje_apariencia" required/></textarea>
                                <label for="rasgosPersonaje" id="rasPersonaje">Rasgos:</label>
                                <textarea id="rasgosPersonaje" name="personaje_rasgos" required/></textarea>
                                <label for="defectosPersonaje" id="defPersonaje">Defectos:</label>
                                <textarea id="defectosPersonaje" name="personaje_defectos" required/></textarea>
                                <label for="vinculosPersonaje" id="vinPersonaje">Vinculos:</label>
                                <textarea id="vinculosPersonaje" name="personaje_vinculos" required/></textarea>
                                <label for="idiomasPersonaje" id="idiPersonaje">Idiomas:</label>
                                <input type="text" id="idiomasPersonaje" name="personaje_idiomas"required/>
                                <label for="historiaPersonaje" id="historyPersonaje">Historia:</label>
                                <textarea id="historiaPersonaje" name="personaje_historia" required/></textarea>
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

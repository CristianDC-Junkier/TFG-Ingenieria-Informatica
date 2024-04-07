<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje\Modificar_Caracteristicas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearpersonajeCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroPersonaje">
                <h2 class="TitulosFormulario">Modificar Caracteristicas</h2>
                <form id = formRegistro action="/TFG/Personajes/modificarPersonajeCaracteristicas?id=${requestScope.personaje.id}" method="POST">
                    <div>
                        <div>
                            <label for="nombrePersonaje" id="namePersonaje">Nombre:</label>
                            <input type="text" id="nombrePersonajeMod" name="personaje_nombre" value="${requestScope.personaje.nombre}"
                                   onkeyup="realizarBusqueda('nombrePersonajeMod', 'namePersonajeInput')" required/>
                            <span  id="namePersonajeInput">✓</span>
                        </div>
                        <div>
                            <label for="alinPersonaje" id="alineamientoPersonaje">Alineamiento:</label>
                            <select required name="alineamiento" id="alinPersonaje" >
                                <option value="Legal Bueno"<c:if test="${requestScope.personaje.alineamiento == 'Legal Bueno'}">selected</c:if>>Legal Bueno</option>
                                <option value="Neutral Bueno"<c:if test="${requestScope.personaje.alineamiento == 'Neutral Bueno'}">selected</c:if>>Neutral Bueno</option>
                                <option value="Caótico Bueno"<c:if test="${requestScope.personaje.alineamiento == 'Caótico Bueno'}">selected</c:if>>Caótico Bueno</option>
                                <option value="Caótico Neutral"<c:if test="${requestScope.personaje.alineamiento == 'Caótico Neutral'}">selected</c:if>>Caótico Neutral</option>
                                <option value="Neutral"<c:if test="${requestScope.personaje.alineamiento == 'Neutral'}">selected</c:if>>Neutral</option>
                                <option value="Legal Neutral"<c:if test="${requestScope.personaje.alineamiento == 'Legal Neutral'}">selected</c:if>>Legal Neutral</option>
                                <option value="Caótico Malvado"<c:if test="${requestScope.personaje.alineamiento == 'Caótico Malvado'}">selected</c:if>>Caótico Malvado</option>
                                <option value="Neutral Malvado"<c:if test="${requestScope.personaje.alineamiento == 'Neutral Malvado'}">selected</c:if>>Neutral Malvado</option>
                                <option value="Legal Malvado"<c:if test="${requestScope.personaje.alineamiento == 'Legal Malvado'}">selected</c:if>>Legal Malvado</option>
                                </select>
                            </div>
                            <div>
                                <label for="idiomasPersonaje" id="idiPersonaje">Idiomas:</label>
                                <input type="text" id="idiomasPersonaje" name="personaje_idiomas" value="${requestScope.personaje.idiomas}"/>
                        </div>
                        <div>
                            <label for="edadPersonaje" id="agePersonaje">Edad:</label>
                            <input type="number" id="edadPersonaje" name="personaje_edad" min="0" value="${requestScope.personaje.edad}"/>
                        </div>
                        <div>
                            <label for="aparienciaPersonaje" id="lookPersonaje">Apariencia:</label>
                            <textarea id="aparienciaPersonaje" name="personaje_apariencia" />${requestScope.personaje.apariencia}</textarea>
                        </div>
                        <div>
                            <label for="rasgosPersonaje" id="rasPersonaje">Rasgos:</label>
                            <textarea id="rasgosPersonaje" name="personaje_rasgos" />${requestScope.personaje.raspersonalidad}</textarea>
                        </div>
                        <div>
                            <label for="defectosPersonaje" id="defPersonaje">Defectos:</label>
                            <textarea id="defectosPersonaje" name="personaje_defectos" />${requestScope.personaje.defectos}</textarea>
                        </div>
                        <div>
                            <label for="vinculosPersonaje" id="vinPersonaje">Vinculos:</label>
                            <textarea id="vinculosPersonaje" name="personaje_vinculos" />${requestScope.personaje.vinculos}</textarea>
                        </div>
                        <div>
                            <label for="historiaPersonaje" id="historyPersonaje">Historia:</label>
                            <textarea id="historiaPersonaje" name="personaje_historia" />${requestScope.personaje.historia}</textarea>
                        </div>
                    </div>
                    <c:if test="${requestScope.msj!=null}">
                        <div id="mensajeError">
                            ${requestScope.msj}
                        </div>
                    </c:if>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.pathname = '/TFG/Personajes/personajePerfilCaracteristicas'" value="Volver">
                        <input type="submit" id="botonEnviar" value="Modificar Caracteristicas">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
    </body>
</html>

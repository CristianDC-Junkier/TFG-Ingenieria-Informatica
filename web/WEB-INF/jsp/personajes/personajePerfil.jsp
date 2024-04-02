<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Mi_Personaje</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/perfilCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main class="mainPerfil">
            <div class="cajaGeneral">
                <div class="cajaPersonaje">
                    <h2>PERSONAJE: ${requestScope.personaje.nombre}</h2>
                    <<div class="personaje">
                        <div class="personaje-fotoboton">
                            <div class="personaje-fotoG">
                                <img src="${requestScope.imagenpersonaje}">
                            </div>
                            <button class="cambiarPersonaje" onclick="mostrarRecuadro()">Cambiar imagen personaje</button>
                            <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Añada la foto
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                    </div>
                                    <hr>
                                    <form id = "formRegistro" action="/TFG/Imagenes/actualizarFotoPersonaje" 
                                          method="POST" enctype="multipart/form-data">
                                        <input type="hidden" name="id" value="${requestScope.personaje.id}">
                                        <input class="botonDentro" type="file" name="imagen" id="imagen" accept="image/*">
                                        <br>
                                        <input class="botonDentro" type="submit" value="Subir Imagen">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="datosPersonaje">
                            <div>Clase:&nbsp;${requestScope.personaje.clase.nombre}</div>
                            <div>Sub-Clase:&nbsp; 
                                <c:choose>
                                    <c:when test="${empty requestScope.personaje.subclase}">
                                        Aún no tiene
                                    </c:when>
                                    <c:otherwise>
                                        ${requestScope.personaje.subclase.nombre}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>Raza: ${requestScope.personaje.clase.nombre}</div>
                            <div>Sub-Raza:&nbsp;
                                <c:choose>
                                    <c:when test="${requestScope.personaje.subraza.nombre == requestScope.personaje.raza.nombre}">
                                        No tiene
                                    </c:when>
                                    <c:otherwise>
                                        ${requestScope.personaje.subraza.nombre}
                                    </c:otherwise>
                                </c:choose></div>
                            <div>Trasfondo:&nbsp;${requestScope.personaje.trasfondo.nombre} </div>
                            <br><hr><br>
                            <div>Nivel:&nbsp;${requestScope.personaje.nivel}&nbsp;/&nbsp;20</div>
                            <div>Clase de Armadura:&nbsp;${requestScope.personaje.clasearmadura} <button type="button" class="botonfinal" onclick="mostrarRecuadro2()">Cambiar</button></div>
                            <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro"> <label for="classArmor">Clase de Armadura:</label>
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
                                    </div>
                                    <form id = formEstadisticas action="/TFG/Personajes/claseArmadura?id=${requestScope.personaje.id}" method="POST">
                                        <input type="number" id="classArmor" name="claseArmadura" min="8" required>
                                        <hr>
                                        <button type="submit" class="botonDentro" >Aceptar</button>
                                        <button type="button" class="botonDentro" onclick="cerrarRecuadro2()">Volver</button>
                                    </form>
                                </div>
                            </div>
                            <div>Puntos de vida:&nbsp;${requestScope.personaje.pvidaactuales}&nbsp;/&nbsp;${requestScope.personaje.pvida}
                                <button type="button" class="botonfinal" onclick="mostrarRecuadro3()">Cambiar vida atual</button>
                                <button type="button" class="botonfinal" onclick="mostrarRecuadro4()">Cambiar vida total</button>
                            </div>
                            <div class="opcionRecuadro" id="recuadro3" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro"> <label for="pointsHP">Puntos de vida Actual:</label>
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro3()">X</span>
                                    </div>
                                    <form id = formEstadisticas action="/TFG/Personajes/puntosVidaActual?id=${requestScope.personaje.id}" method="POST">
                                        <input type="number" id="pointsHP" name="puntosHP" min="0" required>
                                        <hr>
                                        <button type="submit" class="botonDentro" >Aceptar</button>
                                        <button type="button" class="botonDentro" onclick="cerrarRecuadro3()">Volver</button>
                                    </form>
                                </div>
                            </div>
                            <div class="opcionRecuadro" id="recuadro4" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro"> <label for="pointsHP">Puntos de vida Total:</label>
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro4()">X</span>
                                    </div>
                                    <form id = formEstadisticas action="/TFG/Personajes/puntosVidaTotal?id=${requestScope.personaje.id}" method="POST">
                                        <input type="number" id="pointsHP" name="puntosHP" min="1" required>
                                        <hr>
                                        <button type="submit" class="botonDentro" >Aceptar</button>
                                        <button type="button" class="botonDentro" onclick="cerrarRecuadro4()">Volver</button>
                                    </form>
                                </div>
                            </div>
                            <div>Puntos de experiencia:&nbsp;${requestScope.personaje.pexp} <button type="button" class="botonfinal" onclick="mostrarRecuadro5()">Cambiar</button></div>
                            <div class="opcionRecuadro" id="recuadro5" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro"> <label for="pointsEX">Puntos de experiencia:</label>
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro5()">X</span>
                                    </div>
                                    <form id = formEstadisticas action="/TFG/Personajes/puntosEXP?id=${requestScope.personaje.id}" method="POST">
                                        <input type="number" id="pointsEX" name="puntosEXP" min="0" required>
                                        <hr>
                                        <button type="submit" class="botonDentro" >Aceptar</button>
                                        <button type="button" class="botonDentro" onclick="cerrarRecuadro5()">Volver</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="usuarioDatos">
                    <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfilRasgos?id=${requestScope.personaje.id}'">Rasgos</button></div>
                    <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfilHabilidades?id=${requestScope.personaje.id}'">Habilidades</button></div>
                    <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfilAtributos?id=${requestScope.personaje.id}'">Atributos</button></div>
                    <div><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfilCaracteristicas?id=${requestScope.personaje.id}'">Características</button></div>
                </div>
                <div class="botones">
                    <c:if test="${requestScope.personaje.nivel > 1}">
                        <button class="boton" onclick="location.href = '/TFG/Personajes/personajeBajarNivel?id=${requestScope.personaje.id}'">Bajar al Nivel 1</button>
                    </c:if>
                    <c:if test="${requestScope.personaje.nivel < 20}">
                        <button class="boton" onclick="location.href = '/TFG/Formularios/personajeSubirNivel?id=${requestScope.personaje.id}'">Subir de Nivel</button>
                    </c:if>
                    <button class="boton" onclick="location.href = '/TFG/Personajes/personajeHechizos?id=${requestScope.personaje.id}'">Hechizos</button>
                    <button class="boton" onclick="location.href = '/TFG/Personajes/personajeEquipo?id=${requestScope.personaje.id}'">Equipo</button>
                    <button class="boton" onclick="location.href = '/TFG/Personajes/personajeDotes?id=${requestScope.personaje.id}'">Dotes</button>
                    <button class="botonfinal" onclick="mostrarRecuadro()">Eliminar</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Personajes/personajesPerfil'">Volver</button>
                </div>
                <div class="opcionRecuadro" id="recuadro6" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">¿Está seguro de que quieres Borrarlo?
                            <span class="cierreRecuadro" onclick="cerrarRecuadro6()">X</span>
                        </div>
                        <hr>
                        <button class="botonDentro" onclick= "location.href = '/TFG/Personajes/eliminarPersonaje?id=${requestScope.personaje.id}'">Si</button>
                        <button class="botonDentro" onclick="cerrarRecuadro6()">No</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

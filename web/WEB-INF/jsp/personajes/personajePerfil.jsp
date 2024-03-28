<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\MiPersonaje</title>
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
                            <button class="cambiarPersonaje" onclick="mostrarRecuadro2()">Cambiar imagen personaje</button>
                            <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Añada la foto
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
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
                            <div>Clase:&nbsp;${requestScope.personaje.clase.nombre}:</div>
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
                            <div>Clase de Armadura:&nbsp;${requestScope.personaje.clasearmadura} 
                                falta boton para cambiarlo</div>
                            <div>Puntos de vida:&nbsp;${requestScope.personaje.pvidaactuales}&nbsp;/&nbsp;${requestScope.personaje.pvida}
                                falta boton para cambiarlo</div>
                            <div>Puntos de experiencia:&nbsp;${requestScope.personaje.pexp}
                                falta boton para cambiarlo</div>
                        </div>
                    </div>
                </div>

                <div class="usuarioDatos">
                    <div><span>Rasgos:  </span>${requestScope.personaje.nombre} </div>
                    <div><span>Habilidades:  </span>${requestScope.personaje.nombre}</div>
                    <div><span>Atributos:  </span>${requestScope.personaje.nombre}</div>
                    <div><span>Características:  </span>${requestScope.personaje.nombre}</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/'">Subir de Nivel</button>
                    <button class="boton" onclick="location.href = '/TFG/'">Hechizos</button>
                    <button class="boton" onclick="location.href = '/TFG/'">Equipo</button>
                    <button class="boton" onclick="location.href = '/TFG/'">Dotes</button>
                    <button class="botonfinal" onclick="mostrarRecuadro()">Eliminar</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Personajes/personajesPerfil'">Volver</button>
                </div>
                <div class="opcionRecuadro" id="recuadro" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">¿Está seguro de que quieres Borrarlo?
                            <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                        </div>
                        <hr>
                        <button class="botonDentro" onclick="location.href = '/TFG/Personajes/eliminarPersonaje?id=${requestScope.personaje.id}'">Si</button>
                        <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

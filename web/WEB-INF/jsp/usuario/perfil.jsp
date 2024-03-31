
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/perfilCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/comunUsuariosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main class="mainPerfil">
            <div class="cajaGeneral">
                <div class="cajaPersonaje">
                    <c:choose>
                        <c:when test="${sessionScope.user.personajeactual == null}">
                            <h2>SIN PERSONAJE ACTUAL</h2>
                            <div class="personaje">
                                <div class="personaje-fotoboton">
                                    <div class="personaje-fotoG">
                                        <img src="/TFG/img/iconos/IMGNEGRO.png">
                                    </div>
                                    <button class="cambiarPersonaje" onclick="location.href = '/TFG/Personajes/elegirPJActual'">Cambiar personaje Actual</button>
                                </div>
                                <div class="datosPersonaje">
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h2>PERSONAJE ACTUAL: ${requestScope.personajeactual.nombre}</h2>
                            <div class="personaje">
                                <div class="personaje-fotoboton">
                                    <div class="personaje-fotoG">
                                        <img src="${requestScope.imagenactual}">
                                    </div>
                                    <button class="cambiarPersonaje" onclick="location.href = '/TFG/Personajes/elegirPJActual'">Cambiar personaje Actual</button>
                                </div>
                                <div class="datosPersonaje">
                                    <div>Clase:&nbsp;${requestScope.personajeactual.clase.nombre}</div>
                                    <div>Sub-Clase:&nbsp; 
                                        <c:choose>
                                            <c:when test="${empty requestScope.personajeactual.subclase}">
                                                Aún no tiene
                                            </c:when>
                                            <c:otherwise>
                                                ${requestScope.personajeactual.subclase.nombre}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div>Raza: ${requestScope.personajeactual.clase.nombre}</div>
                                    <div>Sub-Raza:&nbsp;
                                        <c:choose>
                                            <c:when test="${requestScope.personajeactual.subraza.nombre == requestScope.personajeactual.raza.nombre}">
                                                No tiene
                                            </c:when>
                                            <c:otherwise>
                                                ${requestScope.personajeactual.subraza.nombre}
                                            </c:otherwise>
                                        </c:choose></div>
                                    <div>Trasfondo:&nbsp;${requestScope.personajeactual.trasfondo.nombre} </div>
                                    <br><hr><br>
                                    <div>Nivel:&nbsp;${requestScope.personajeactual.nivel}&nbsp;/&nbsp;20</div>
                                    <div>Clase de Armadura:&nbsp;${requestScope.personajeactual.clasearmadura}</div>
                                    <div>Puntos de vida:&nbsp;${requestScope.personajeactual.pvidaactuales}&nbsp;/&nbsp;${requestScope.personajeactual.pvida}</div>
                                    <div>Puntos de experiencia:&nbsp;${requestScope.personajeactual.pexp}</div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="usuarioDatos">
                    <div><span>Nombre:  </span>${sessionScope.user.nombre} </div>
                    <div><span>Nombre de usuario:  </span>${sessionScope.user.apodo}</div>
                    <div><span>Correo:  </span>${sessionScope.user.correo}</div>
                    <div><span>Teléfono:  </span>${sessionScope.user.telefono}</div>
                    <div><span>Fecha de nacimiento:  </span>${sessionScope.user.fechanac.getDate()}/${sessionScope.user.fechanac.getMonth() + 1}/${sessionScope.user.fechanac.getYear()+1900}</div>
                    <div><span>Provincia:  </span>${sessionScope.user.provincia}</div>
                    <div><span>Genero:  </span>${sessionScope.user.genero}</div>
                    <div><span>Personajes:  </span>${requestScope.numpersonajes}/10</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Usuarios/mostrarAmigos'">Amigos</button>
                    <button class="boton" onclick="location.href = '/TFG/Mesas/mostrarMesasUsuario'">Mesas</button>
                    <button class="boton" onclick="location.href = '/TFG/Personajes/personajesPerfil'">Personajes</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Formularios/modificarusuario'">Modificar Datos</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Usuarios/cerrarSesion'">Salir</button>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

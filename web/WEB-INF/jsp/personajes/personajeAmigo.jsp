<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\PersonajeAmigo</title>
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
                    <h2>PERSONAJE ACTUAL</h2>
                    <div class="personaje">
                        <div class="personaje-fotoboton">
                            <div class="personaje-fotoG">
                                <img src="${requestScope.imagenpersonaje}">
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
                    <div><span>Creador:  </span>${requestScope.personaje.usuario.apodo}</div>
                </div>
                <div class="botones">
                    <button class="botonfinal" onclick="location.href = '/TFG/Personajes/personajesAmigos'">Volver</button>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

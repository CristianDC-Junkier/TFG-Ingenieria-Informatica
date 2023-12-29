<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Amigos\Perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/perfilCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
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
                            <div class="personaje-foto">
                                <img src="/TFG/img/iconos/IMGNEGRO.png">
                            </div>
                            <button class="cambiarPersonaje" onclick="agregarArchivo()">Mirar sus personajes</button>
                        </div>

                        <div class="datosPersonaje">
                            <div >Nombre de Usuario</div>
                            <div >Apodo: Usuario123</div>
                            <div >Correo: usuario@example.com</div>
                        </div>
                    </div>
                </div>

                <div class="usuarioDatos">
                    <c:if test="${requestScope.sonAmigos == 1}">
                        <div><span>Nombre:  </span>${requestScope.amigo.nombre} </div>
                    </c:if>
                    <div><span>Nombre de usuario:  </span>${requestScope.amigo.apodo}</div>
                    <c:if test="${requestScope.sonAmigos == 1}">
                        <div><span>Fecha de nacimiento:  </span>${requestScope.amigo.fechanac.getDate()}/${requestScope.amigo.fechanac.getMonth() + 1}/${requestScope.amigo.fechanac.getYear()+1900}</div>
                        <div><span>Provincia:  </span>${requestScope.amigo.provincia}</div>
                        <div><span>Genero:  </span>${requestScope.amigo.genero}</div>
                    </c:if>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Usuarios/mostrarAmigos'">Atras</button>
                    <c:choose>
                        <c:when test="${requestScope.sonAmigos == 1}">
                            <button class="boton" onclick="location.href ='/TFG/Mesas/mostrarMesasAmigo?amigo=${requestScope.amigo.id}'">Mesas</button>
                            <button class="boton" onclick="location.href ='/TFG/Personajes/personajesAmigo?amigo=${requestScope.amigo.id}">Personajes</button>
                            <button class="botonfinal" onclick="mostrarRecuadro()">Eliminar Amigo</button>
                        </c:when>
                        <c:otherwise>
                            <button id="pediramigo" class="botonfinal" onclick="location.href ='/TFG/Usuarios/enviarPeticion?pamistad=${requestScope.amigo.id}'">Añadir Amigo</button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="opcionRecuadro" id="recuadro" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">¿Esta seguro que quiere Eliminarlo?
                            <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                        </div>
                        <hr>
                        <button class="boton" onclick="location.href = '/TFG/Usuarios/eliminarAmigo?amigo=${requestScope.amigo.id}'">Si</button>
                        <button class="boton" onclick="cerrarRecuadro()">No</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Mesas\Mesa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesaCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/listaUsuariosenMesaCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/comunMesasCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main class="mainMesa">
            <div class="cajaGeneral">
                <div class="cajaMesa">
                    <h2>${requestScope.mesa.titulo}</h2>
                    <div class="mesa">
                        <div class="mesa-fotoboton">
                            <div class="mesa-foto">
                                <img src="/TFG/img/iconos/IMGNEGRO.png">
                            </div>
                            <c:if test="${requestScope.mesa.creador == user.apodo}">
                                <button class="cambiarMesa" onclick="agregarArchivo()">Cambiar de imagen</button>
                            </c:if>
                        </div>
                        <div class="descripcionMesa">
                            <div><span>Descripcion:  </span>${requestScope.mesa.descripcion}</div>
                        </div>
                        <div class="datosMesa">
                            <table>
                                <c:forEach var="usuario" items="${listaUsuarios}" varStatus="status">
                                    <tr>
                                        <td>${listaRoles[status.index].rol}</td>
                                        <td>${usuario.apodo}</td>
                                        <c:choose>
                                            <c:when test="${usuario.id != user.id and usuario.apodo != user.apodo}">
                                                <td><button class="botonUsuarios" onclick="location.href = '/TFG/Usuarios/mostrarAmigo?amigo=${usuario.id}'">Detalles</button></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>&nbsp;</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.dm != usuario.apodo and requestScope.mesa.creador == user.apodo}">
                                                <td><button class="botonUsuarios" onclick="location.href = '/TFG/Mesas/cambiarlider?id=${requestScope.mesa.id}&usuario=${usuario.id}'">Pasar DM</button></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>&nbsp;</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.mesa.creador == user.apodo and usuario.apodo != user.apodo}">
                                                <td><button class="botonUsuarios" onclick="mostrarRecuadro2()">Eliminar</button></td>
                                            <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                                                <div class="contenidoRecuadro">
                                                    <div class="tituloRecuadro">¿Esta seguro que quiere Eliminarlo?
                                                        <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
                                                    </div>
                                                    <hr>
                                                    <button class="botonDentro" onclick="location.href = '/TFG/Mesas/eliminardeMesa?id=${requestScope.mesa.id}&usuario=${usuario.id}'">Si</button>
                                                    <button class="botonDentro" onclick="cerrarRecuadro2()">No</button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <td>&nbsp;</td>
                                        </c:otherwise>
                                    </c:choose>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="mesaDatos">
                    <div><span>Dungeon Master:  </span>${requestScope.dm}</div>
                    <div><span>Creador:  </span>${requestScope.mesa.creador}</div>
                    <div><span>Comunidad:  </span>${requestScope.mesa.comunidad}</div>
                    <div><span>Usuarios:  </span>${requestScope.listaRoles.size()}/${requestScope.mesa.tamano}</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Mesas/mostrarMesasUsuario'">Atras</button>
                    <button class="boton" onclick="agregarArchivo()">Chat</button>
                    <c:choose> 
                        <c:when test="${requestScope.mesa.creador == user.apodo}">
                            <button class="botonfinal" onclick="location.href = '/TFG/Formularios/modificarmesa?id=${requestScope.mesa.id}'">Modificar Mesa</button>
                            <button class="botonfinal" onclick="mostrarRecuadro()">Borrar Mesa</button>
                        </c:when>
                        <c:otherwise>
                            <button class="botonfinal" onclick="mostrarRecuadro()">Salir</button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:choose> 
                    <c:when test="${requestScope.mesa.creador == user.apodo}">
                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                            <div class="contenidoRecuadro">
                                <div class="tituloRecuadro">¿Esta seguro que quiere Eliminarla?
                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                </div>
                                <hr>
                                <button class="botonDentro" onclick="location.href = '/TFG/Mesas/eliminarMesa?id=${requestScope.mesa.id}'">Si</button>
                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                            <div class="contenidoRecuadro">
                                <div class="tituloRecuadro">¿Esta seguro que quiere Salir?
                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                </div>
                                <hr>
                                <button class="botonDentro" onclick="location.href = '/TFG/Mesas/salirdeMesa?id=${requestScope.mesa.id}'">Si</button>
                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title class="titulosPag">Guidance4\Mesas\Mesa\Chat</title>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/chatMesaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main class="mainMesa">
            <div class="cajaGeneralMesa">
                <div class="izquierdaChatMesa">
                    <div class="contedorDescriptor" id="infoDescriptor"> 
                        <div class="imagenDescriptor">
                            <c:choose>
                                <c:when test="${requestScope.descriptor.imagendescriptor == null}">
                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                </c:when>
                                <c:otherwise>
                                    <img src="/TFG/Imagenes/mostrarImagenDescriptor?id=${requestScope.descriptor.mesa}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="descripcionDescriptor">
                            <c:if test="${requestScope.descriptor.descripcion != null}">
                                <p>${requestScope.descriptor.descripcion}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="contenedorChat">
                        <div class="contenedorMensajesChat" id="MensajesChat">
                        </div>
                        <div class="contenedorBotonesChat">
                            <form>
                                <input type="text" maxlength="255" id="mensajeChatMesa" name="mensaje" placeholder="Enviar mensaje...">
                                <button id="botonEnviar" type="button" onclick="enviarMensaje()">Enviar</button>
                                <select id="SDados">
                                    <option value="4">Dado-4</option>
                                    <option value="6">Dado-6</option>
                                    <option value="8">Dado-8</option>
                                    <option value="10">Dado-10</option>
                                    <option value="12">Dado-12</option>
                                    <option value="20">Dado-20</option>
                                    <option value="100">Dado-100</option>
                                </select>
                                <button id="IDadoButton" type="button" onclick="enviarTirada()"><img id="IDado" src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="derechaChatMesa">
                    <div class="contenedorAudioChatMesa">
                        <div id="contenedorCancion"> 
                            <c:choose>
                                <c:when test="${requestScope.musica.nombre == 'Ninguna'}">
                                    <p>Ahora mismo no hay musica puesta</p>
                                    <audio id="reproductorCancion">
                                        <source id="cancion" src="/TFG/musica/${requestScope.musica.nombre}.mp3" type="audio/mpeg">
                                    </audio>
                                </c:when>
                                <c:otherwise>
                                    <p>Ahora mismo está sonando:</p>
                                    <audio id="reproductorCancion" name="${requestScope.musica.nombre}" >
                                        <source id="cancion" src="/TFG/musica/${requestScope.musica.nombre}.mp3" type="audio/mpeg">
                                        Tu navegador no soporta la reproducción de audio.
                                    </audio>
                                    <p>${requestScope.musica.nombre}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="botonesAudio">
                            <button type="button" class="botonArriba" onclick="ajustarVolumen(-0.1)">Disminuir volumen</button>
                            <button type="button" class="botonArriba" onclick="ajustarVolumen(+0.1)">Aumentar volumen</button>
                            <button type="button" class="botonArriba" onclick="mostrarRecuadro()
                                    <c:if test="${requestScope.rol != 'Dungeon Master'}">  style ="display: none;"</c:if>">Cambiar</button>
                                    <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                        <div class="contenidoRecuadro">
                                            <div class="tituloRecuadro">Eliga la Musica
                                                <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                            </div>
                                            <hr>
                                            <form id="formMusica" method="POST">
                                                <label for="opciones">Selecciona una musica:</label>
                                                <select id="opcionMusica" name="opciones">
                                                <c:forEach var="musicaL" items="${listaMusica}">
                                                    <option value="${musicaL.id}" 
                                                            <c:if test="${requestScope.musica.nombre == musicaL.nombre}">selected</c:if>>
                                                        ${musicaL.nombre}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                            <input class="botonDentro" type="submit" value="Elegir">
                                            <button class="botonDentro" type="button" onclick="cerrarRecuadro()">Volver</button>
                                        </form>
                                    </div>
                            </div>

                        </div>
                    </div>
                    <div class="listaChatMesa" id="tablaJugadores">
                        <table border="1">
                            <tr>
                                <th>Jugador</th>
                                <th>Personaje</th>
                                <th>Clase</th>
                                <th>Nivel</th>
                                <th>Detalles</th>
                            </tr>
                            <c:forEach var="personaje" items="${requestScope.listaPersonajes}" varStatus="status">
                                <tr>
                                    <c:choose>
                                        <c:when test="${personaje != null}">
                                            <c:choose>
                                                <c:when test="${requestScope.listaUsuariosRol[status.index].rol != 'Dungeon Master'}">
                                                    <td>${requestScope.listaUsuarios[status.index].apodo}</td>
                                                    <td>${personaje.nombre}</td>
                                                    <td>${personaje.clase.nombre}</td>
                                                    <td>${personaje.nivel}</td>
                                                    <td><button  class="botonfinal" onclick="location.href = '/TFG/Personajes/personaje?id=${personaje.id}'">Detalles</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${requestScope.listaUsuarios[status.index].apodo}</td>
                                                    <td>Dungeon Master</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${requestScope.listaUsuarios[status.index].apodo}</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="contenedorBotonesMesaChat">
                        <c:choose>
                            <c:when test="${requestScope.rol == 'Dungeon Master'}">
                                <button type="button" class="botonArriba" onclick="mostrarRecuadro2()">Cambiar Descriptor</button>
                                <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                                    <div class="contenidoRecuadro">
                                        <div class="tituloRecuadro">Añadir informacion
                                            <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
                                        </div>
                                        <hr>
                                        <form id = "formDescriptor" action="/TFG/Imagenes/actualizarFotoyDescripcionDescriptor" 
                                              method="POST" enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${requestScope.mesa.id}">
                                            <input class="botonDentro" type="file" name="imagen" id="imagen" accept="image/*">
                                            <textarea id="descripcion" name="descripcion" rows="5" cols="10"></textarea>
                                            <br>
                                            <input class="botonDentro" type="submit" value="Actualizar">
                                            <button class="botonDentro" type="button" onclick="cerrarRecuadro2()">Volver</button>
                                        </form>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="botonArriba" onclick="mostrarRecuadro3()">Cambiar Vida</button>
                                <div class="opcionRecuadro" id="recuadro3" style="display: none;">
                                    <div class="contenidoRecuadro">
                                        <div class="tituloRecuadro"> <label for="pointsHP">Puntos de vida Actual:</label>
                                            <span class="cierreRecuadro" onclick="cerrarRecuadro3()">X</span>
                                        </div>
                                        <form id = "formVida" action="/TFG/Chats/ChatMesaPuntosVidaActualCambio" method="POST">
                                            <input type="hidden" name="id" value="${requestScope.personajeactual.id}">
                                            <input type="number" id="pointsHP" name="puntosHP" min="0" required>
                                            <hr>
                                            <input class="botonDentro" type="submit" value="Actualizar">
                                            <button type="button" class="botonDentro" onclick="cerrarRecuadro3()">Volver</button>
                                        </form>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <button  class="botonArriba" onclick="location.href = '/TFG/Mesas/mostrarMesa?id=${requestScope.mesa.id}'">Volver</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script>
            let chatM = '${requestScope.mesa.id}';
            let rol = '${requestScope.rol}';
        </script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/chats/mesasChatJS.js"></script>
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>
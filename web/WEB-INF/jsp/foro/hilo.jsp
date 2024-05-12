<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Hilos\Hilo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/comunForoCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/hiloMensajeCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos" style="margin-right:10px">Hilo<button class="botonArriba" onclick="location.href = '/TFG/Foro/hilos'">Volver</button></h2>
        <hr color="black">
        <div class="contenedoresForo"> 
            <div class="listasForo" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaForo">
                            <h3>${requestScope.hilo.tema.nombre}</h3>
                            <h4>${requestScope.hilo.seccion.titulo}</h4>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <tr>
                                        <td>
                                            <div class="hilo-foto">
                                                <img src="${fotoInicial}">
                                            </div>
                                        </td>
                                        <td>${hilo.creador.apodo}</td>
                                        <td >${fechaInicial}</td>
                                        <c:if test="${hilo.creador.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                            <td><button class="botonDentro" onclick="mostrarRecuadro6()">Eliminar Hilo</button></td>
                                        </c:if>
                                    </tr>
                                    <tr>
                                        <td colspan="4">${hilo.mensajeinicial}</td>
                                    </tr>
                                    </tr>
                                    <c:if test="${hilo.creador.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                        <div class="opcionRecuadro" id="recuadro6" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <form id = form  action="/TFG/Foro/borrarHilo" method="POST">
                                                    <input type="hidden" name="hilo" value="${hiloID}">
                                                    <label class="tituloRecuadro" id="titulodelRecuadro">¿Está seguro?</label>
                                                    <input class="botonDentro" type="submit" value="Aceptar">
                                                    <input class="botonDentro" type="button" onclick="cerrarRecuadro6()" value="Volver">
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:forEach var="mensaje" items="${listaMensajes}" varStatus="status">
                                        <tr>
                                            <td>
                                                <div class="hilo-foto">
                                                    <img src="${urlImagenes[status.index]}">
                                                </div>
                                            </td>
                                            <td>${mensaje.escritor.apodo}</td>
                                            <td>${fechasHilo[status.index]}</td>
                                            <c:if test="${hilo.creador.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                                <td><button class="botonDentro" onclick="mostrarRecuadro${status.index+1}()">Eliminar</button></td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td colspan="4">${mensaje.mensaje}</td>
                                        </tr>
                                        <c:if test="${mensaje.escritor.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                            <div class="opcionRecuadro" id="recuadro${status.index+1}" style="display: none;">
                                                <div class="contenidoRecuadro">
                                                    <form  action="/TFG/Foro/eliminarMensajeHilo" method="POST">
                                                        <label class="tituloRecuadro" id="titulodelRecuadro">¿Está seguro?</label>
                                                        <input type="hidden" name="hilo" value="${hilo.id}">
                                                        <input type="hidden" name="mensaje" value="${mensaje.id}">
                                                        <input class="botonDentro" type="submit" value="Aceptar">
                                                        <input class="botonDentro" type="button" onclick="cerrarRecuadro${status.index+1}()" value="Volver">
                                                    </form>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorMensajeHilo">
                <div class="formularioMensajeHilo">
                    <form id="formMensaje" action="/TFG/Foro/añadirMensajeHilo" method="POST">
                        <input type="hidden" name="hilo" value="${hilo.id}">
                        <input type="text" maxlength="255" id="mensajeHilo" name="mensaje" placeholder="Comentar...">
                        <input class="botonDentro" type="submit" value="Enviar">
                    </form>
                </div>
                <div class="formularioMensajeHilo">
                    <form id="formTirada" action="/TFG/Foro/añadirTiradaHilo" method="POST">
                        <input type="hidden" name="hilo" value="${hilo.id}">
                        <select name="dado" id="SDados">
                            <option value="4">Dado-4</option>
                            <option value="6">Dado-6</option>
                            <option value="8">Dado-8</option>
                            <option value="10">Dado-10</option>
                            <option value="12">Dado-12</option>
                            <option value="20">Dado-20</option>
                            <option value="100">Dado-100</option>
                        </select>
                        <button id="IDadoButton" type="submit"><img id="IDado" src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
                    </form>
                </div>
            </div>
            <div class="contenedorBotonesForo">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script>
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        //Recoger Datos
        let hilo = '<%= request.getAttribute("hiloID")%>';
        //Tabla
        let tabla = document.getElementById('Tabla');
        let tablaInicial = tabla.innerHTML;
    </script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/TFG/js/busquedasAJAXJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/foro/hiloJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

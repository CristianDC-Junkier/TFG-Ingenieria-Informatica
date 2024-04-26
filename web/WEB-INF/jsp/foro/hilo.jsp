<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Hilos\Hilo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesasCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/comunMesasCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Hilo</h2>
        <button class="botonDentro" onclick="">Volver</button>
        <hr color="black">
        <div class="contenedoresMesa"> 
            <div class="listasMesa" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaMesa">
                            <h3>${requestScope.hilo.tema.nombre}</h3>
                            <h4>${requestScope.hilo.seccion.titulo}</h4>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <c:forEach var="mensaje" items="${listaMensajes}" varStatus="status">
                                        <tr>
                                            <td>
                                                <div class="mesa-foto">
                                                    <img src="${urlImagenes[status.index]}">
                                                </div>
                                            </td>
                                            <td>${mensaje.escritor.apodo}</td>
                                            <td>${mensaje.fecha}</td>
                                            <td>${mensaje.mensaje}</td>
                                            <c:if test="${hilo.creador.id == requestSession.user.id || requestSession.user.admin == 1}">
                                                <td><button class="botonDentro" onclick="">Eliminar</button></td>
                                            </c:if>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <form id = form  action="" method="POST">
                                                    <label class="tituloRecuadro" for="anadirMesa" id="titulodelRecuadro">¿Está seguro?</label>
                                                    <input class="recuadroDentro" type="password" id="anadirMesa" name="contrasena_anadirmesa" required>
                                                    <input class="botonDentro" type="submit" value="Aceptar">
                                                    <input class="botonDentro" type="button" onclick="cerrarRecuadro()" value="Volver">
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesMesa">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    <script>
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        //Tabla
        let tabla = document.getElementById('Tabla');
        let tablaInicial = tabla.innerHTML;
    </script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/TFG/js/busquedasAJAXJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/mesas/mesasJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

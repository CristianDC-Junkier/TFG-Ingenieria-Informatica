<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Hilos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/comunForoCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/listaForoCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos" style="margin-right:10px">Hilos<button class="botonArribaForo" onclick="location.href = '/TFG/Foro/inicio'">Volver</button></h2>
        <hr color="black">
        <div class="contenedoresForo"> 
            <div class="contenedorForo">
                <div class="titulobuscadorForo">Explorar Hilos</div>
                <div class="buscadorForo">
                    <div>Busca por titulo: <input id="buscador" onkeyup="realizarBusqueda('Hilos')" onsearch="realizarBusqueda('Hilos')" type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Tema:
                        <br>
                        <select id="elegirTema">
                            <c:choose>
                                <c:when test="${requestScope.tema == 'Cualquiera'}">
                                    <option value="Cualquiera" selected>Cualquiera</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Cualquiera">Cualquiera</option>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="tema" items="${listaTemas}" varStatus="status">
                                <c:choose>
                                    <c:when test="${requestScope.tema == tema.nombre}">
                                        <option value="${tema.id}" selected>${tema.nombre}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tema.id}">${tema.nombre}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <br>
                        Seccion:
                        <select id="elegirSeccion" name="seccion">
                            <c:choose>
                                <c:when test="${requestScope.seccion == 'Cualquiera'}">
                                    <option value="Cualquiera" selected>Cualquiera</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Cualquiera" selected>Cualquiera</option>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="seccion" items="${listaSeccion}" varStatus="status">
                                <c:choose>
                                    <c:when test="${requestScope.seccion == seccion.titulo}">
                                        <option value="${seccion.id}" selected>${seccion.titulo}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${seccion.id}">${seccion.titulo}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <button id="botonMio" class="${requestScope.mio == "true" ? 'estalleno' : 'noestalleno'}">Mio</button>
                    <button id="botonComentado" class="${requestScope.comentado == "true" ? 'estalleno' : 'noestalleno'}">Has comentado</button>
                    <c:if test="${requestScope.numHilos < 3 }">
                        <button class="botonDentro" onclick="location.href = '/TFG/Formularios/crearHilo'" >Crear Hilo</button>
                    </c:if>
                </div>
            </div>
            <div class="listasForo" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaForo">
                            <h3>Hilos</h3>
                            <h4>Encuentra tu solución, o pregunta</h4>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <c:forEach var="hilo" items="${listaHilos}" varStatus="status">
                                        <tr>
                                            <td>
                                                <div class="hilo-foto">
                                                    <img src="${urlImagenes[status.index]}">
                                                </div>
                                            </td>
                                            <td>${hilo.titulo}</td>
                                            <td>${fechasHilos[status.index]}</td>
                                            <td>${hilo.creador.apodo}</td>
                                            <td>${hilo.tema.nombre}</td>
                                            <td>${hilo.seccion.titulo}</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Foro/hilo?hilo=${hilo.id}'">Entrar</button></td>
                                            <c:if test="${hilo.creador.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                                <td><button class="botonDentro" onclick="mostrarRecuadro${status.index+1}()">Eliminar</button></td>
                                            </c:if>
                                        </tr>
                                        <c:if test="${hilo.creador.id == sessionScope.user.id || sessionScope.user.admin == 1}">
                                            <div class="opcionRecuadro" id="recuadro${status.index+1}" style="display: none;">
                                                <div class="contenidoRecuadro">
                                                    <form id = form  action="/TFG/Foro/borrarHilo" method="POST">
                                                        <input type="hidden" name="hilo" value="${hilo.id}">
                                                        <label class="tituloRecuadro" id="titulodelRecuadro">¿Está seguro?</label>
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
            <div class="contenedorBotonesForo">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script>
        //Recoger Datos
        let tema = '<%= request.getAttribute("tema")%>';
        let seccion = '<%= request.getAttribute("seccion")%>';
        // Convertir los valores a booleanos
        let mio = '<%= request.getAttribute("mio")%>' === 'true';
        let comentado = '<%= request.getAttribute("comentado")%>' === 'true';
        //Valores
        let valorSeleccionado1 = '<%= request.getAttribute("seccion")%>';
        let valorSeleccionado2 = '<%= request.getAttribute("tema")%>';
        let valorSeleccionado3 = mio;
        let valorSeleccionado4 = comentado;
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
    <script src="/TFG/js/foro/foroJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

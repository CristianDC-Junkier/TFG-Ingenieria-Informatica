<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Hilos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesasCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/comunMesasCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Hilos </h2>
        <button class="botonArriba" onclick="location.href = '/TFG/Foro/inicio'">Volver</button>
        <hr color="black">
        <div class="contenedoresMesa"> 
            <div class="contenedorMesa">
                <div class="tituloBuscadorMesa">Explorar Hilos</div>
                <div class="buscadorMesa">
                    <div>Busca por titulo: <input id="buscador" onkeyup="realizarBusqueda('Hilos')" type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Tema:
                        <select id="elegirTemas">
                            <c:choose>
                                <c:when test="${requestScope.tema == 'Cualquiera'}">
                                    <option value="tema" selected>Cualquiera</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="tema">Cualquiera</option>
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
                        Seccion:
                        <select id="elegirSeccion" name="seccion">
                            <c:choose>
                                <c:when test="${requestScope.seccion == 'Cualquiera'}">
                                    <option value="seccion" selected>Cualquiera</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="seccion" selected>Cualquiera</option>
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
                    <button id="botonArriba" onclick="location.href = '/TFG/Formularios/crearHilo'" >Crear Hilo</button>
                </div>
            </div>
            <div class="listasMesa" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaMesa">
                            <h3>Hilos</h3>
                            <h4>Encuentra tu solución, o pregunta</h4>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <c:forEach var="hilo" items="${listaHilos}" varStatus="status">
                                        <tr>
                                            <td>
                                                <div class="mesa-foto">
                                                    <img src="${urlImagenes[status.index]}">
                                                </div>
                                            </td>
                                            <td>${hilo.titulo}</td>
                                            <td>${hilo.fecha}</td>
                                            <td>${hilo.creador.apodo}</td>
                                            <td>${hilo.tema.nombre}</td>
                                            <td>${hilo.seccion.titulo}</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Foro/hilo?hilo=${hilo.id}'">Entrar</button></td>
                                            <c:if test="${hilo.creador.id == requestSession.user.id || requestSession.user.admin == 1}">
                                                <td><button class="botonDentro" onclick="">Eliminar</button></td>
                                            </c:if>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <form id = form  action="/TFG/Foro/borrarHilo" method="POST">
                                                    <input type="hidden" name="seccion" value="${seccion.id}">
                                                    <label class="tituloRecuadro" id="titulodelRecuadro">¿Está seguro?</label>
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
        //Recoger Datos
        let tema = '<%= request.getAttribute("tema")%>';
        let seccion = '<%= request.getAttribute("seccion")%>';
        // Convertir los valores a booleanos
        let mio = '<%= request.getAttribute("mio")%>' === 'true';
        let comentado = '<%= request.getAttribute("comentado")%>' === 'true';
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

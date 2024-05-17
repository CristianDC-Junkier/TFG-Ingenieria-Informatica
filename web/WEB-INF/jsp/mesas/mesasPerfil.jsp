<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Mis_Mesas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesasPerfilCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/comunMesasCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Mis Mesas</h2>
        <hr color="black">
        <div class="contenedoresMesa"> 
            <div class="contenedorMesa">
                <div class="tituloBuscadorMesa">Mis Mesas</div>
                <div class="buscadorMesa">
                    <div>Busca por nombre: 
                        <input id="buscador" onkeyup="realizarBusqueda('MesasPerfil')" onsearch="realizarBusqueda('MesasPerfil')" type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarMesa">
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <option value="ordenar1" selected>Titulo (A-Z)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar1">Titulo (A-Z)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar2'}">
                                    <option value="ordenar2" selected>Titulo (Z-A)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar2">Titulo (Z-A)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar3'}">
                                    <option value="ordenar3" selected>Personas (Max)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar3">Personas (Max)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar4'}">
                                    <option value="ordenar4" selected>Personas (Min)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar4">Personas (Min)</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <div><button onclick="location.href = '/TFG/Formularios/crearmesa'">Crear Mesa</button></div>
                </div>
            </div>
            <div class="listasMesa" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaMesa">
                            <h3>Mis Mesas</h3>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <h4>Ordenado por nombre (A-Z)</h4>
                                </c:when>
                                <c:when test="${requestScope.orden == 'ordenar2'}">
                                    <h4>Ordenado por nombre (Z-A)</h4>
                                </c:when>
                                <c:when test="${requestScope.orden == 'ordenar3'}">
                                    <h4>Ordenado por personas (Max)</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4>Ordenado por personas (Min)</h4>
                                </c:otherwise>
                            </c:choose>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <c:forEach var="mesa" items="${listaMesas}" varStatus="status">
                                        <tr>
                                            <td><div class="mesa-foto">
                                                    <c:choose>
                                                        <c:when test="${mesa.imagenmesa == null}">
                                                            <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${urlImagenes[status.index]}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div></td>
                                            <td>${mesa.titulo}</td>
                                            <td>${mesa.comunidad}</td>
                                            <td>${listaCantidad[status.index]}/${mesa.tamano}</td>
                                            <td>${mesa.creador}</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Mesas/mostrarMesa?id=${mesa.id}'">Detalles</button></td>
                                            <c:choose> 
                                                <c:when test="${sessionScope.user.apodo == listaLideres[status.index]}">
                                                    <td><button class="botonDentro" onclick="mostrarRecuadroX('/TFG/Mesas/eliminarMesa?id=',${mesa.id})">Borrar Mesa</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><button class="botonDentro" onclick="location.href = '/TFG/Mesas/salirdeMesa?id=${mesa.id}'">Salir</button></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                    <div class="opcionRecuadro" id="recuadroX" style="display: none;">
                                        <div class="contenidoRecuadro">
                                            <div class="tituloRecuadro">¿Esta seguro que quieres Borrarla?
                                                <span class="cierreRecuadro" onclick="cerrarRecuadroX()">X</span>
                                            </div>
                                            <hr>
                                            <button class="botonDentro" id="eliminar" >Si</button>
                                            <button class="botonDentro" onclick="cerrarRecuadroX()">No</button>
                                        </div>
                                    </div>
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
        let orden = '<%= request.getAttribute("orden")%>';
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
    <script src="/TFG/js/mesas/mesasPerfilJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

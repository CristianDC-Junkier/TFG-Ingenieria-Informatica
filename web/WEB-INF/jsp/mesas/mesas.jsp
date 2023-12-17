<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Mesas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesasCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/listasMesasCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Mesas</h2>
        <hr color="black">
        <div class="contenedoresMesa"> 
            <div class="contenedorMesa">
                <div class="tituloBuscadorMesa">Explorar Mesas</div>
                <div class="buscadorMesa">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
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
                        </select>
                    </div>
                    <button id="botonLleno" class="${requestScope.lleno == "true" ? 'estalleno' : 'noestalleno'}">Llenas</button>
                </div>
            </div>
            <div class="listasMesa" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaMesa">
                            <h3>Mesas</h3>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <h4>Ordenado por nombre (A-Z)</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4>Ordenado por nombre (Z-A)</h4>
                                </c:otherwise>
                            </c:choose>
                            <div class="diseñoTabla">
                                <table>
                                    <c:forEach var="mesa" items="${listaMesas}" varStatus="status">
                                        <tr>
                                            <td><div class="mesa-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${mesa.titulo}</td>
                                            <td>${mesa.comunidad}</td>
                                            <td>${listacantidad[status.index]}/${mesa.tamano}</td>
                                            <td>${listalideres[status.index]}</td>
                                            <c:choose> 
                                                <c:when test="${mesa.contrasena != null}">
                                                    <td>Con contraseña</td>
                                                    <td><button class="botonDentro" onclick="mostrarRecuadro()">Entrar</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>Sin contraseña</td>
                                                    <td><button class="botonDentro" onclick="location.href = '/TFG/Mesas/anadiraMesa?titulo=${mesa.titulo}&contrasena_anadirmesa='">Entrar</button></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <form id = form  action="/TFG/Mesas/anadiraMesa?titulo=${mesa.titulo}" method="POST">
                                                    <label class="tituloRecuadro" for="anadirMesa" id="titulodelRecuadro">Contraseña:</label>
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
    <footer>
        &copy; 2023 Cristian Delgado Cruz
    </footer>
    <script>
        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        let lleno = '<%= request.getAttribute("lleno")%>';
        // Convertir el valor de mesa a un booleano
        let llenoBool = '<%= request.getAttribute("lleno")%>' === 'true';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
    </script>
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mesas/mesasJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>
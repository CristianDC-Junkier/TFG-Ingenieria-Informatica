
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Amigos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/amigosCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Amigos</h2>
        <hr color="black">
        <div class="contenedoresAmigos"> 
            <div class="contenedorAmigos">
                <div class="tituloBuscadorAmigos">Buscar Amigos </div>
                <div class="buscadorAmigos">
                    <div>Busca por nombre: <input id="buscador" onkeyup="realizarBusqueda()" type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarAmigos">
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <option value="ordenar1" selected>Nombre (A-Z)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar1">Nombre (A-Z)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar2'}">
                                    <option value="ordenar2" selected>Nombre (Z-A)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar2">Nombre (Z-A)</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <button id="botonMesa" class="${requestScope.mesa == "true" ? 'existeMesa' : 'noExisteMesa'}">Mesa</button>
                    <button onclick="location.href = '/TFG/Usuarios/mostrarPeticionesRecibidas'">Peticiones</button> 
                </div>
            </div>
            <div class="listasAmigos" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaAmigo">
                            <h3>Amigos</h3>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <h4>Ordenado por nombre (A-Z)</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4>Ordenado por nombre (Z-A)</h4>
                                </c:otherwise>
                            </c:choose>
                            <div class="diseñoTabla" id ="Tabla">
                                <table>
                                    <c:forEach var="usuario" items="${listaUsuarios}">
                                        <tr>
                                            <td><div class="personaje-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${usuario.apodo}</td>
                                            <td>${usuario.provincia}</td>
                                            <td>${usuario.genero}</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/mostrarAmigo?amigo=${usuario.id}'">Detalles</button></td>
                                            <td><button class="botonDentro" onclick="mostrarRecuadro()">Eliminar Amigo</button></td>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <div class="tituloRecuadro">¿Esta seguro que quiere Eliminarlo?
                                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                                </div>
                                                <hr>
                                                <button class="botonDentro" onclick="location.href = '/TFG/Usuarios/eliminarAmigo?amigo=${usuario.id}'">Si</button>
                                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesAmigos">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    <script>
        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        let mesa = '<%= request.getAttribute("mesa")%>';
        // Convertir el valor de mesa a un booleano
        let mesaBool = '<%= request.getAttribute("mesa")%>' === 'true';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        
        // Obtener el contenedor de la tabla
        let tabla = document.getElementById('Tabla');
        // Recoger el contenido inicial de la tabla
        let tablaInicial = tabla.innerHTML;
    </script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/TFG/js/usuarios/amigosJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
    <script src="/TFG/js/principalAJAXJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>
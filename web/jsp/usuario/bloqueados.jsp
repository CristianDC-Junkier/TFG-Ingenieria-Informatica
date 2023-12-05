
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Amigos\Bloqueados</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/bloqueadosCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Bloqueados</h2>
        <hr color="black">
        <div class="contenedoresBloqueados"> 
            <div class="contenedorBloqueados">
                <div class="tituloBuscadorBloqueados">Bloqueados</div>
                <div class="buscadorBloqueados">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarBloqueados">
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
                    <button onclick="location.href = '/TFG/Usuarios/mostrarUsuarios'">Atras</button>
                </div>
            </div>
            <div class="listasBloqueados" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaBloqueado">
                            <h3>Bloqueados</h3>
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
                                    <c:forEach var="usuario" items="${listaUsuarios}">
                                        <tr>
                                            <td><div class="personaje-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${usuario.apodo}</td>
                                            <td>${usuario.provincia}</td>
                                            <td>${usuario.genero}</td>
                                            <td><button class="botonDentro" onclick="mostrarRecuadro()">Desbloquear</button></td>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <div class="tituloRecuadro">¿Esta seguro que quiere desbloquearlo?
                                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                                </div>
                                                <hr>
                                                <button class="botonDentro" onclick="location.href = '/TFG/Usuarios/desbloquearUsuario?bloqueado=${usuario.apodo}'">Si</button>
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
            <div class="contenedorBotonesBloqueados">
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
         //Datos de las páginas
         let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
         let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
    </script>
    <script src="/TFG/js/usuario/bloqueadosJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

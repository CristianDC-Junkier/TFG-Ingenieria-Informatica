
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Amigos\Jugadores</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/usuariosCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Jugadores</h2>
        <hr color="black">
        <div class="contenedoresUsuarios"> 
            <div class="contenedorUsuarios">
                <div class="tituloBuscadorUsuarios">Explorar Jugadores</div>
                <div class="buscadorUsuarios">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarUsuarios">
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
                    <button onclick="location.href = '/TFG/Usuarios/mostrarAmigos'">Amigos</button> 
                    <button onclick="location.href = '/TFG/Usuarios/mostrarBloqueados'">Bloqueados</button> 
                </div>
            </div>
            <div class="listasUsuarios" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaUsuario">
                            <h3>Usuarios</h3>
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
                                            <td>Compartir Mesa o No</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/enviarPeticion?pamistad=${usuario.apodo}'">Añadir Amigo</button></td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/bloquearUsuario?bloqueado=${usuario.apodo}'">Bloquear Usuario</button></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesUsuarios">
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
        let mesa = '<%= request.getAttribute("mesa")%>';
        // Convertir el valor de mesa a un booleano
        let mesaBool = '<%= request.getAttribute("mesa")%>' === 'true';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
    </script>
    <script src="/TFG/js/usuarios/usuariosJS.js"></script>
    <script src="/TFG/js/mostrarBotonesJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

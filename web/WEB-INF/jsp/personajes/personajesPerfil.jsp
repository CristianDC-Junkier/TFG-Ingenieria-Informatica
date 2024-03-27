<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Personajes\Perfil</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/personajesCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/personajes/comunPersonajesCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Mis Personajes</h2>
        <hr color="black">
        <div class="contenedoresPersonaje"> 
            <div class="contenedorPersonaje">
                <div class="tituloBuscadorPersonaje">Mis Personajes</div>
                <div class="buscadorPersonaje">
                    <div>Busca por nombre: <input id="buscador" onkeyup="realizarBusqueda('PersonajesPerfil')" type="search" placeholder="Introduce el nombre"/>  </div>
                    <div>
                        Ordenar:
                        <select id="ordenarNombre">
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
                    <div>
                        Elección de Raza:
                        <select id="filtroRaza">
                            <option value="Raza" selected>Raza</option>
                            <c:forEach var="raza" items="${listaRazas}">
                                <option value="${raza.id}" >${raza.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        Elección de Clase:
                        <select id="filtroClase">
                            <option value="Clase" selected>Clase</option>
                            <c:forEach var="clase" items="${listaClases}">
                                <option value="${clase.id}" >${clase.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        Nivel:
                        <select id="filtroNivel">
                            <option value="0" selected>Nivel</option>
                            <c:forEach var="i" begin="1" end="20">
                                <c:choose>
                                    <c:when test="${requestScope.filtronivel == i}">
                                        <option value="${i}" selected>Nivel ${i}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i}">Nivel ${i}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajes'">Explorar</button>
                </div>
            </div>
            <div class="listasPersonaje" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaPersonaje">
                            <h3>Personajes</h3>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <h4>Ordenado por nombre (A-Z)</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4>Ordenado por nombre (Z-A)</h4>
                                </c:otherwise>
                            </c:choose>
                            <div class="diseñoTabla" id="Tabla">
                                <table>
                                    <c:forEach var="personaje" items="${listaPersonajes}" varStatus="status">
                                        <tr>
                                            <td>
                                                <div class="personaje-foto">
                                                    <c:choose>
                                                        <c:when test="${personaje.imagenpersonaje == null}">
                                                            <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${urlImagenes[status.index]}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </td>
                                            <td>${personaje.nombre}</td>
                                            <td>${personaje.clase.nombre}</td>
                                            <td>${personaje.raza.nombre}</td>
                                            <td>${personaje.nivel}</td>
                                            <td>${sessionScope.user.apodo}</td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${personaje.id}'">Detalles</button></td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${personaje.id}'">Copiar</button></td>
                                            <td><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajePerfil?id=${personaje.id}'">Eliminar</button></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesPersonaje">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    <script>
        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        let raza = '<%= request.getAttribute("filtroRaza")%>';
        let clase = '<%= request.getAttribute("filtroClase")%>';
        let nivel = '<%= request.getAttribute("filtroNivel")%>';
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
    <script src="/TFG/js/personajes/personajesPerfilJS.js"></script>
</body>
</html>
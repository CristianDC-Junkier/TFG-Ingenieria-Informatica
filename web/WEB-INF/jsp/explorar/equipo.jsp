<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Equipo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/explorarlistaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Equipo</h2>
            <hr color="black">
            <div class="explicacionesPestañas">
                <p>Todo lo explicado aquí solo es una guía, es decir,
                    el DM puede moldear o ajustar según su propio criterio.</p>
                <p>Los valores pueden cambiar según que tipo de vendedor o en que
                    se especialice, todo ello se narra en la pestaña de precios, aún así
                    el DM puede seguir ajustando estos valores según necesite, o inventar
                    nuevas armas ya sea utilizando la base de los objetos ya establecidos
                    o no, por ejemplo objetos mágicos.</p>
                <p>Lo importante es divertirse.</p>
            </div>
            <div class="contenedoresExplorar"> 
                <div class="contenedorExplorarListas">
                    <div class="tituloBuscadorExplorador">Buscar Equipo 
                    </div>
                    <form class="buscadorExplorar">
                        <input type="text" id=SelectName placeholder="Busqueda por nombre">
                        <select id="Selecttipo">
                            <option value="Tipo" selected>Tipo de objeto</option>
                            <option value="Arma">Armas</option>
                            <option value="Armadura">Armaduras</option>
                            <option value="Otros">Otros</option>
                        </select>
                        <select id="Selectcategoria">
                            <option value="Categoria" selected>Categoría</option>
                            <option value="Arma sencilla">Armas Sencillas</option>
                            <option value="Arma marcial">Armas Marciales</option>
                            <option value="Escudo">Escudos</option>
                            <option value="Armadura ligera">Armadura Ligera</option>
                            <option value="Armadura intermedia">Armadura Intermedia</option>
                            <option value="Armadura pesada">Armadura Pesada</option>
                            <option value="Herramienta">Herramientas</option>
                            <option value="Instrumentos musical">Instrumentos Musicales</option>
                            <option value="Juego">Juegos</option>
                            <option value="Montura">Monturas</option>
                            <option value="Munición">Munición</option>
                            <option value="Paquete de Equipo">Paquetes de Equipo</option>
                            <option value="Vehículo">Vehículos</option>
                        </select>
                        <select id="Selectpropiedad">
                            <option value="Propiedad" selected>Propiedad</option>
                            <option value="Alcance">Alcance</option>
                            <option value="Arrojadiza">Arrojadiza</option>
                            <option value="De Carga">De Carga</option>
                            <option value="Desventaja">Desventaja</option>
                            <option value="Distancia">Distancia</option>
                            <option value="Dos Manos">Dos Manos</option>
                            <option value="Fuerza">Fuerza</option>
                            <option value="Ligera">Ligera</option>
                            <option value="Munición">Munición</option>
                            <option value="Pesada">Pesada</option>
                            <option value="Sutil">Sutil</option>
                            <option value="Versátil">Versátil</option>
                        </select>
                    </form>
                </div>
                <div class="listasExplorador" id="pestañasSeccion">
                    <div class="pestañasNavegacion">
                        <div class="pestaña" id="pestaña1">
                            <div class="ListaEquipo">
                                <h3>${requestScope.titulo}</h3>
                                <h4>${requestScope.subtitulo}</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr class="titulosTabla">
                                            <th>Nombre</th>
                                            <th>Daño</th>
                                            <th>Propiedades</th>
                                            <th>Precio</th>
                                            <th>Peso</th>
                                        </tr>
                                        <c:forEach var="i" begin="0" end="14">
                                            <c:set var="equipo" value="${listaEquipo[i]}" />
                                            <c:choose>
                                                <c:when test="${equipo != null}">
                                                    <tr>
                                                        <td>${equipo.nombre}</td>
                                                        <td>${equipo.dano}</td>
                                                        <td>&nbsp;</td>
                                                        <td>${equipo.precio}</td>
                                                        <td>${equipo.peso}</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="contenedorBotonesExplorador">
                    <div class="pestañasBotones" id="pestañaBotones">
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script>
            //Datos de las páginas
            let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
            let numpag = parseInt('<%= request.getAttribute("numpag")%>', 10);
            let valorSeleccionado1 = '<%= request.getAttribute("vTipo")%>'
            let valorSeleccionado2 = '<%= request.getAttribute("vCat")%>'
            let valorSeleccionado3 = '<%= request.getAttribute("vPro")%>'
        </script>
        <script src="/TFG/js/mostrarBotonesJS.js"></script>
        <script src="/TFG/js/explorar/equipoJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

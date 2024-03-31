<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Personajes\Personaje_Hechizos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/explorarlistaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Hechizos</h2>
            <hr color="black">
            <div class="explicacionesPestañas">
                <p>Todo lo explicado aquí solo es una guía, es decir,
                    el DM puede moldear o ajustar según su propio criterio.</p>
                <p></p>
                <p>Lo importante es divertirse.</p>
            </div>
            <div class="contenedoresExplorar"> 
                <div class="contenedorExplorarListas">
                    <div class="tituloBuscadorExplorador">Buscar Hechizos 
                    </div>
                    <form class="buscadorExplorar">
                        <input  id="buscador" onkeyup="realizarBusqueda('personajeHechizosElegir')" onsearch="realizarBusqueda('personajeHechizosElegir')" type="search" placeholder="Busqueda por nombre">
                        <select id="Selectescuela">
                            <option value="Escuela" selected>Escuela</option>
                            <option value="Abjuración">Abjuración</option>
                            <option value="Adivinación">Adivinación</option>
                            <option value="Conjuración">Conjuración</option>
                            <option value="Encantamiento">Encantamiento</option>
                            <option value="Evocación">Evocación</option> 
                            <option value="Ilusión">Ilusión</option>
                            <option value="Nigromancia">Nigromancia</option>
                            <option value="Transmutación">Transmutación</option>
                        </select>
                        <select id="Selectnivel">
                            <option value="Nivel" selected>Nivel</option>
                            <option value="Trucos">Trucos</option>
                            <option value="Nivel 1">Nivel 1</option>
                            <option value="Nivel 2">Nivel 2</option>
                            <option value="Nivel 3">Nivel 3</option>
                            <option value="Nivel 4">Nivel 4</option>
                            <option value="Nivel 5">Nivel 5</option>
                            <option value="Nivel 6">Nivel 6</option>
                            <option value="Nivel 7">Nivel 7</option>
                            <option value="Nivel 8">Nivel 8</option>
                            <option value="Nivel 9">Nivel 9</option>
                            <option value="Nivel 10">Nivel 10</option>
                        </select>
                        <select id="Selectclase">
                            <option value="Clase" selected>Clase</option>
                            <option value="Artificiero">Artificiero</option>
                            <option value="Bardo">Bardo</option>
                            <option value="Brujo">Brujo</option>
                            <option value="Caballero de la Muerte">Caballero de la Muerte</option>
                            <option value="Clérigo">Clérigo</option>
                            <option value="Druida">Druida</option>
                            <option value="Explorador">Explorador</option>
                            <option value="Hechicero">Hechicero</option>
                            <option value="Mago">Mago</option>
                            <option value="Paladin">Paladin</option>
                        </select>
                         <button type="button" onclick="location.href = '/TFG/Personajes/personajeHechizos?id=${requestScope.id}'">Volver</button>
                    </form>
                </div>
                <div class="listasExplorador" id="pestañasSeccion">
                    <div class="pestañasNavegacion">
                        <div class="pestaña" id="pestaña1">
                            <div class="ListaEquipo">
                                <h3>Hechizos</h3>
                                <h4>${requestScope.subtitulo}</h4>
                                <div class="diseñoTablaHechizos">
                                    <table id="Tabla">
                                        <tr class="titulosTabla">
                                            <th>Nombre</th>
                                            <th>Nivel</th>
                                            <th>Escuela</th>
                                            <th>Tiempo Lanzamiento</th>
                                            <th>Duración</th>
                                            <th>Alcance</th>
                                            <th>Componentes</th>
                                            <th>&nbsp;</th>
                                        </tr>
                                        <c:forEach var="i" begin="0" end="14">
                                            <c:set var="hechizo" value="${listaHechizos[i]}"/>
                                            <c:choose>
                                                <c:when test="${hechizo != null}">
                                                    <tr>
                                                        <td>${hechizo.nombre}</td>
                                                        <td>${hechizo.nivel}</td>
                                                        <td>${hechizo.escuela}</td>
                                                        <td>${hechizo.tlanzamiento}</td>
                                                        <td>${hechizo.duracion}</td>
                                                        <td>${hechizo.alcance}</td>
                                                        <td>${hechizo.componentes}</td>
                                                        <td><button class="botonDentro" onclick="location.href = '/TFG/Personajes/personajeHechizoAnadir?hechizo=${hechizo.id}&personaje=${requestScope.id}'">Añadir</button></td>
                                                    </tr>
                                                    <tr class="tablaHechizosTR" onclick="window.location = '/TFG/Explorar/hechizo?idHechizo=${hechizo.id}'">
                                                        <td colspan="8" >${hechizo.descripcion}</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="8" >&nbsp;</td>
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
            let valorSeleccionado1 = '<%= request.getAttribute("vEscu")%>'
            let valorSeleccionado2 = '<%= request.getAttribute("vNiv")%>'
            let valorSeleccionado3 = '<%= request.getAttribute("vClas")%>'
            let idPJ = '<%= request.getAttribute("id")%>'
            //Tabla
            let tabla = document.getElementById('Tabla');
            let tablaInicial = tabla.innerHTML;
        </script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/busquedasAJAXJS.js"></script>
        <script src="/TFG/js/mostrarBotonesJS.js"></script>
        <script src="/TFG/js/personajes/personajeHechizosElegirJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

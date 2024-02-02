<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Monstruos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/explorarlistaCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Monstruos</h2>
            <hr color="black">
            <div class="explicacionesPestañas">
                <p>Todo lo explicado aquí solo es una guía, es decir,
                    el DM puede moldear o ajustar según su propio criterio.</p>
                <p>La mayoría de monstruos estan predefinidos pero el DM puede elegir
                    modificar daños, vida, mezclar monstruos o cambiarles el nombre a
                    los que ya existen.</p>
                <p>Lo importante es divertirse.</p>
            </div>
            <div class="contenedoresExplorar"> 
                <div class="contenedorExplorarListas">
                    <div class="tituloBuscadorExplorador">Buscar Monstruos </div>
                    <form class="buscadorExplorar">
                        <input type="text" id=SelectName placeholder="Busqueda por nombre">
                        <select id="Selectdesafio">
                            <option value="Valor de Desafio" selected>Valor de Desafio</option>
                            <option value="0">0</option>
                            <option value="1/8">1/8</option>
                            <option value="1/4">1/4</option>
                            <option value="1/2">1/2</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                        </select>
                        <select id="Selecttipo">
                            <option value="Tipo" selected>Tipo</option>
                            <option value="Aberración">Aberración</option>
                            <option value="Bestia">Bestia</option>
                            <option value="Celestial">Celestial</option>
                            <option value="Cieno">Cieno</option>
                            <option value="Constructo">Constructo</option>
                            <option value="Infernal">Infernal</option>
                            <option value="Dragón">Dragón</option>
                            <option value="Elemental">Elemental</option>
                            <option value="Enjambres">Enjambres</option>
                            <option value="Fata">Fata</option>
                            <option value="Gigante">Gigante</option>
                            <option value="Humanoide">Humanoide</option>
                            <option value="Monstruosidad">Monstruosidad</option>
                            <option value="No-Muerto">No-Muerto</option>
                            <option value="Planta">Planta</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </form>
                </div>
                <div class="listasExplorador" id="pestañasSeccion">
                    <div class="pestañasNavegacion">
                        <div class="pestaña" id="pestaña1">
                            <div class="ListaEquipo">
                                <h3>Monstruos</h3>
                                <h4>${requestScope.subtitulo}</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr class="titulosTabla">
                                            <th>VD</th>
                                            <th>Nombre</th>
                                            <th>Tipo</th>
                                            <th>Alineamiento</th>
                                            <th>Tamaño</th>
                                        </tr>
                                        <c:forEach var="i" begin="0" end="14">
                                            <c:set var="monstruo" value="${listaMonstruos[i]}"/>
                                            <c:choose>
                                                <c:when test="${monstruo != null}">
                                                    <tr>
                                                        <td>${monstruo.vdesafio}</td>
                                                        <td>${monstruo.nombre}</td>
                                                        <td>${monstruo.tipo}</td>
                                                        <td>${monstruo.alineamiento}</td>
                                                        <td>${monstruo.tamano}</td>
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
            let valorSeleccionado1 = '<%= request.getAttribute("vVD")%>'
            let valorSeleccionado2 = '<%= request.getAttribute("vTipo")%>'
        </script>
        <script src="/TFG/js/mostrarBotonesJS.js"></script>
        <script src="/TFG/js/explorar/monstruosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

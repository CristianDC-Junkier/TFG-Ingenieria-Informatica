<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <select id="Selectorigen">
                            <option value="origen" selected>Origen</option>
                            <option value="origen1">Reglas Básicas</option>
                            <option value="origen2">Manual de Monstruos</option>
                            <option value="origen3">Monstruos de Volo</option>
                            <option value="origen4">La Tesorería de Dragones de Fizban</option>
                            <option value="origen5">Guía del Maestro de Gremio de Rávnica</option>
                            <option value="origen6">Caldero de Tasha para todo</option>
                        </select>
                        <select id="Selecttipo">
                            <option value="tipo" selected>Tipo</option>
                            <option value="tipo1">Aberración</option>
                            <option value="tipo2">Bestia</option>
                            <option value="tipo3">Celestial</option>
                            <option value="tipo4">Cieno</option>
                            <option value="tipo5">Constructo</option>
                            <option value="tipo6">Infernal</option>
                            <option value="tipo7">Dragón</option>
                            <option value="tipo8">Elemental</option>
                            <option value="tipo9">Enjambres</option>
                            <option value="tipo10">Fata</option>
                            <option value="tipo11">Gigante</option>
                            <option value="tipo12">Humanoide</option>
                            <option value="tipo13">Monstruosidad</option>
                            <option value="tipo14">No-Muerto</option>
                            <option value="tipo15">Planta</option>
                            <option value="tipo16">Otro</option>
                        </select>
                        <select id="Selectorden">
                            <option value="orden" selected>Ordenar por Nombre</option>
                            <option value="orden1">Ordenar por Desafio</option>
                        </select>
                        <button onclick="guardarSeleccion()">Buscar</button>
                    </form>
                </div>
                <div class="listasExplorador" id="pestañasSeccion">
                    <div class="pestañasNavegacion">
                        <div class="pestaña" id="pestaña1">
                            <div class="ListaEquipo">
                                <h3>Monstruo</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr class="titulosTabla">
                                            <th>VD</th>
                                            <th>Nombre</th>
                                            <th>Tipo</th>
                                            <th>Alineamiento</th>
                                            <th>Origen</th>
                                        </tr>
                                        <tr>
                                            <td>Arco corto</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco corto</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta ligera</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia</td>
                                            <td>25 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Bastón</td>
                                            <td>1d6 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>2 pp</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Clava</td>
                                            <td>1d4 Contundente(Fue)</td>
                                            <td>Ligera</td>
                                            <td>1 pp</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Daga</td>
                                            <td>1d4 Perforante(Des)</td>
                                            <td>Arrojadiza, Ligera, Sutil</td>
                                            <td>2 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Dardo</td>
                                            <td>1d4 Perforante(Des)</td>
                                            <td>Arrojadiza, Distancia, Sutil</td>
                                            <td>5 pc</td>
                                            <td>0,11 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran clava</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Dos manos</td>
                                            <td>2 pp</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hacha de mano</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Honda</td>
                                            <td>1d4 Contundente(Des)</td>
                                            <td>Distancia, Munición</td>
                                            <td>1 pp</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hoz</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Ligera</td>
                                            <td>1 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Jabalina</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza</td>
                                            <td>5 pp</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lanza</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo ligero</td>
                                            <td>1d4 Contundente(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>2 pp</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Maza</td>
                                            <td>1d6 Contundente(Fue)</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
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
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

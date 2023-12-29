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
                    <div class="tituloBuscadorExplorador">Buscar Equipo </div>
                    <form class="buscadorExplorar">
                        <input type="text" id=SelectName placeholder="     Busqueda por nombre">
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
                        <div class="pestaña" id="pestaña0">
                            <div class="ListaEquipo">
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>No existe equipo con ese grupo de cualidades.</td>
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

        <script>

            let botonTipoClick = false;
            let botonOriClick = false;
            let botonOrdClick = false;

            let pestañasActual = 1;
            let pestañasNum = 1;

            let primerapestaña = 1;
            let últimapestaña = 1;

            let valorGuardadoTipo = "tipo";
            let valorGuardadoOri = "origen";
            let valorGuardadoOrd = "orden";

            function eleccionPestañas() {

                //Nos aseguramos de que no sea la primera vez
                if (sessionStorage.getItem('seleccionGuardadaTipo') !== null)
                {

                    valorGuardadoTipo = sessionStorage.getItem('seleccionGuardadaTipo');
                    valorGuardadoCat = sessionStorage.getItem('seleccionGuardadaCat');
                    valorGuardadoPro = sessionStorage.getItem('seleccionGuardadaPro');
                }

                if (valorGuardadoCat === 'categoria' && valorGuardadoPro === 'propiedad')
                {
                    if (valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 1;
                        últimapestaña = 3;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 4;
                        últimapestaña = 4;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 5;
                        últimapestaña = 9;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    }
                } else if (valorGuardadoPro === 'propiedad' && valorGuardadoCat !== 'categoria')
                {
                    if (valorGuardadoCat === 'categoria1' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria1' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 1;
                        últimapestaña = 1;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria2' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria2' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 2;
                        últimapestaña = 3;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria3' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria3' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 10;
                        últimapestaña = 10;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria4' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria4' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 11;
                        últimapestaña = 11;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria5' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria5' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 12;
                        últimapestaña = 12;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria6' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria6' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 13;
                        últimapestaña = 13;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria7' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria7' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 14;
                        últimapestaña = 15;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria8' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria8' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 16;
                        últimapestaña = 16;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria9' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria9' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 17;
                        últimapestaña = 17;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria10' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria10' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 18;
                        últimapestaña = 18;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria11' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria11' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 19;
                        últimapestaña = 19;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria12' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria12' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 20;
                        últimapestaña = 20;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoCat === 'categoria13' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoCat === 'categoria13' && valorGuardadoTipo === 'tipo3')
                    {
                        primerapestaña = 21;
                        últimapestaña = 21;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else
                    {
                        primerapestaña = 0;
                        últimapestaña = 0;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    }
                } else if (valorGuardadoCat === 'categoria' && valorGuardadoPro !== 'propiedad')
                {
                    if (valorGuardadoPro === 'propiedad1' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad1' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 22;
                        últimapestaña = 22;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad2' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad2' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 23;
                        últimapestaña = 23;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad3' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad3' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 24;
                        últimapestaña = 24;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad4' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad4' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 25;
                        últimapestaña = 25;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad5' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad5' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 26;
                        últimapestaña = 26;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad6' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad6' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 27;
                        últimapestaña = 27;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad7' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad7' && valorGuardadoTipo === 'tipo2')
                    {
                        primerapestaña = 28;
                        últimapestaña = 28;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad8' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad8' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 29;
                        últimapestaña = 29;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad9' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad9' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 30;
                        últimapestaña = 30;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad10' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad10' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 31;
                        últimapestaña = 31;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad11' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad11' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 32;
                        últimapestaña = 32;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if (valorGuardadoPro === 'propiedad12' && valorGuardadoTipo === 'tipo' ||
                            valorGuardadoPro === 'propiedad12' && valorGuardadoTipo === 'tipo1')
                    {
                        primerapestaña = 33;
                        últimapestaña = 33;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else
                    {
                        primerapestaña = 0;
                        últimapestaña = 0;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    }
                } else if (valorGuardadoPro !== 'propiedad')
                {
                    if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad1')
                    {
                        primerapestaña = 22;
                        últimapestaña = 22;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad2')
                    {
                        primerapestaña = 34;
                        últimapestaña = 34;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad2')
                    {
                        primerapestaña = 35;
                        últimapestaña = 35;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad3')
                    {
                        primerapestaña = 36;
                        últimapestaña = 36;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad3')
                    {
                        primerapestaña = 37;
                        últimapestaña = 37;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo2')
                            && valorGuardadoCat === 'categoria4'
                            && valorGuardadoPro === 'propiedad4')
                    {
                        primerapestaña = 38;
                        últimapestaña = 38;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo2')
                            && valorGuardadoCat === 'categoria5'
                            && valorGuardadoPro === 'propiedad4')
                    {
                        primerapestaña = 39;
                        últimapestaña = 39;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo2')
                            && valorGuardadoCat === 'categoria6'
                            && valorGuardadoPro === 'propiedad4')
                    {
                        primerapestaña = 13;
                        últimapestaña = 13;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad4')
                    {
                        primerapestaña = 40;
                        últimapestaña = 40;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad4')
                    {
                        primerapestaña = 41;
                        últimapestaña = 41;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad5')
                    {
                        primerapestaña = 42;
                        últimapestaña = 42;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad5')
                    {
                        primerapestaña = 43;
                        últimapestaña = 43;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad6')
                    {
                        primerapestaña = 44;
                        últimapestaña = 44;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad6')
                    {
                        primerapestaña = 45;
                        últimapestaña = 45;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo2')
                            && valorGuardadoCat === 'categoria6'
                            && valorGuardadoPro === 'propiedad7')
                    {
                        primerapestaña = 28;
                        últimapestaña = 28;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad8')
                    {
                        primerapestaña = 46;
                        últimapestaña = 46;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad8')
                    {
                        primerapestaña = 47;
                        últimapestaña = 47;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad9')
                    {
                        primerapestaña = 48;
                        últimapestaña = 48;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad9')
                    {
                        primerapestaña = 49;
                        últimapestaña = 49;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad10')
                    {
                        primerapestaña = 31;
                        últimapestaña = 31;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad11')
                    {
                        primerapestaña = 50;
                        últimapestaña = 50;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad11')
                    {
                        primerapestaña = 51;
                        últimapestaña = 51;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria1'
                            && valorGuardadoPro === 'propiedad12')
                    {
                        primerapestaña = 52;
                        últimapestaña = 52;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else if ((valorGuardadoTipo === 'tipo' || valorGuardadoTipo === 'tipo1')
                            && valorGuardadoCat === 'categoria2'
                            && valorGuardadoPro === 'propiedad12')
                    {
                        primerapestaña = 53;
                        últimapestaña = 53;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    } else {
                        primerapestaña = 0;
                        últimapestaña = 0;
                        pestañasNum = últimapestaña - primerapestaña + 1;
                    }

                }
                pestañaInicial();
            }

            //Para Poner visible solo la pestaña inicial
            function pestañaInicial() {

                //Si la pestaña inicial no es la primera
                if (primerapestaña !== pestañasActual)
                {
                    let pestañasAnteriorElemento = document.getElementById('pestaña' + pestañasActual);
                    pestañasAnteriorElemento.style.display = 'none';

                    let pestañaElemento = document.getElementById('pestaña' + primerapestaña);
                    pestañaElemento.style.display = 'block';

                    pestañasActual = primerapestaña;
                }
            }

            //Funcion que actualiza los botones
            function actualizarBotones() {

                var pestañasBotones = document.getElementById('pestañaBotones');
                pestañasBotones.innerHTML = '';

                if (pestañasActual > primerapestaña) {
                    pestañasBotones.innerHTML += '<button class="botonArriba" onclick="mostrarPestaña(-1,-1)">Inicio</button>';
                }
                for (let i = Math.max(primerapestaña, pestañasActual - 1); i <= Math.min(últimapestaña, pestañasActual + 1); i++) {
                    if (i === pestañasActual) {
                        pestañasBotones.innerHTML += '<button class="botonArriba" id="Actual" >Actual</button>';
                    } else {
                        pestañasBotones.innerHTML += '<button class="botonArriba" onclick="mostrarPestaña(' + i + ',0)">'
                                + (i - primerapestaña + 1) + '</button>';
                    }
                }

                if (pestañasActual < últimapestaña) {
                    pestañasBotones.innerHTML += '<button class="botonArriba" onclick="mostrarPestaña(1,+1)">Final</button>';
                }
            }

            //Funcion que muestra la pestaña seleccionada
            function mostrarPestaña(pestaña, tipo) {
                if (tipo === -1) {
                    pestaña = primerapestaña;
                } else if (tipo === +1) {
                    pestaña = últimapestaña;
                }
                var pestañasAnterior = pestañasActual;
                pestañasActual = Math.max(primerapestaña, Math.min(últimapestaña, pestaña));
                if (pestañasActual !== pestañasAnterior) {
                    let pestañasAnteriorElemento = document.getElementById('pestaña' + pestañasAnterior);
                    pestañasAnteriorElemento.style.display = 'none';

                    let pestañaElemento = document.getElementById('pestaña' + pestañasActual);
                    pestañaElemento.style.display = 'block';

                    actualizarBotones();
                }
            }

            // Función para guardar la selección en sessionStorage
            function guardarSeleccion() {
                let selectTipo = document.getElementById('Selecttipo');
                let valorSeleccionadoTipo = selectTipo.value;
                sessionStorage.setItem('seleccionGuardadaTipo', valorSeleccionadoTipo);
                let selectOri = document.getElementById('Selectorigen');
                let valorSeleccionadoOri = selectOri.value;
                sessionStorage.setItem('seleccionGuardadaOri', valorSeleccionadoOri);
                let selectOrd = document.getElementById('Selectorden');
                let valorSeleccionadoOrd = selectOrd.value;
                sessionStorage.setItem('seleccionGuardadaOrd', valorSeleccionadoOrd);

                botonTipoClick = true;
                botonOriClick = true;
                botonOrdClick = true;
            }

            // Al cargar la página, restaura el valor seleccionado si está almacenado
            window.onload = function () {
                let selectTipo = document.getElementById('Selecttipo');
                let selectOri = document.getElementById('Selectorigen');
                let selectOrd = document.getElementById('Selectorder');

                valorGuardadoTipo = sessionStorage.getItem('seleccionGuardadaTipo');
                valorGuardadoOri = sessionStorage.getItem('seleccionGuardadaOri');
                valorGuardadoOrd = sessionStorage.getItem('seleccionGuardadaOrd');

                if (valorGuardadoTipo) {
                    selectTipo.value = valorGuardadoTipo;
                } else {
                    selectTipo.value = 'tipo';
                }

                if (valorGuardadoOri) {
                    selectOri.value = valorGuardadoOri;
                } else {
                    selectOri.value = 'origen';
                }

                if (valorGuardadoOrd) {
                    selectOrd.value = valorGuardadoOrd;
                } else {
                    selectOrd.value = 'orden';
                }

                pestañaInicial();
            };
            // Limpiar la selección guardada al abandonar la página si no se hizo clic en el botón
            window.addEventListener('beforeunload', function (event) {
                if (!botonTipoClick) {
                    sessionStorage.removeItem('seleccionGuardadaTipo');
                }
                if (!botonOriClick) {
                    sessionStorage.removeItem('seleccionGuardadaOri');
                }
                if (!botonOrdClick) {
                    sessionStorage.removeItem('seleccionGuardadaOrd');
                }
            });

            //eleccionPestañas();
            actualizarBotones();
        </script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

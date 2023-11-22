
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Guidance4\Perfil\Amigos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/amigosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Amigos</h2>
        <hr color="black">
        <div class="ContenedoresEquipo"> 
            <div class="ContenedorEquipo">
                <div class="TituloBuscadorEquipo">Buscar Equipo 
                    <button onclick="location.href = './propiedades.jsp'">Propiedades Explicación</button> 
                </div>
                <form class="BuscadorEquipo">
                    <select id="Selecttipo">
                        <option value="tipo" selected>Tipo de objeto</option>
                        <option value="tipo1">Armas</option>
                        <option value="tipo2">Armaduras</option>
                        <option value="tipo3">Otros</option>
                    </select>
                    <select id="Selectcategoría">
                        <option value="categoria" selected>Provincia</option>
                        <option value="categoria1">Armas Sencillas</option>
                        <option value="categoria2">Armas Marciales</option>
                        <option value="categoria3">Escudos</option>
                        <option value="categoria4">Armadura Ligera</option>
                        <option value="categoria5">Armadura Intermedia</option>
                        <option value="categoria6">Armadura Pesada</option>
                        <option value="categoria7">Herramientas</option>
                        <option value="categoria8">Instrumentos Musicales</option>
                        <option value="categoria9">Juegos</option>
                        <option value="categoria10">Monturas</option>
                        <option value="categoria11">Munición</option>
                        <option value="categoria12">Paquete de Equipo</option>
                        <option value="categoria13">Vehículos</option>
                    </select>
                    <select id="SelectOrden">
                        <option value="propiedad" selected>Propiedad</option>
                        <option value="propiedad1">Alcance</option>
                        <option value="propiedad2">Arrojadiza</option>
                        <option value="propiedad3">De Carga</option>
                        <option value="propiedad4">Desventaja</option>
                        <option value="propiedad5">Distancia</option>
                        <option value="propiedad6">Dos Manos</option>
                        <option value="propiedad7">Fuerza</option>
                        <option value="propiedad8">Ligera</option>
                        <option value="propiedad9">Munición</option>
                        <option value="propiedad10">Pesada</option>
                        <option value="propiedad11">Sutil</option>
                        <option value="propiedad12">Versátil</option>
                    </select>
                    <button onclick="guardarSeleccion()">Buscar</button>
                </form>
            </div>
            <div class="ListasEquipo" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="ListaEquipo">
                            <h3>Armas</h3>
                            <h4>Armas sencillas</h4>
                            <div class="diseñoTabla">
                                <table>
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
                                        <td>De carga, Distancia, Dos manos, Munición</td>
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
                    <div class="pestaña" id="pestaña2">
                        <div class="ListaEquipo">
                            <h3>Armas</h3>
                            <h4>Armas sencillas</h4>
                            <div class="diseñoTabla">
                                <table>
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
                                        <td>De carga, Distancia, Dos manos, Munición</td>
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
    </main>
    <footer>
        &copy; 2023 Cristian Delgado Cruz
    </footer>
</body>
</html>

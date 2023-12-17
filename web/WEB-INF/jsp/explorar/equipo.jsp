<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Equipo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/equipoCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/jsp/menuNav.jsp" />
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
                            <option value="categoria" selected>Categoría</option>
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
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Alabarda</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cimitarra</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>25 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espada corta</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>10 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espada larga</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espadón</td>
                                            <td>2d6 cortante</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>50 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Estoque</td>
                                            <td>1d8 Perforante(Fue)</td>
                                            <td>Sutil</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran hacha</td>
                                            <td>1d12 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>30 po</td>
                                            <td>3,18 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hacha de batalla</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>10 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lanza de caballería</td>
                                            <td>1d12 Perforante(Fue)</td>
                                            <td>Alcance, Especial</td>
                                            <td>10 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña3">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Látigo</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Alcance, Sutil</td>
                                            <td>2 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lucero del alba</td>
                                            <td>1d8 Perforante(Fue)</td>
                                            <td>-</td>
                                            <td>15 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mangual</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo de guerra</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mazo de guerra</td>
                                            <td>2d6 Contundente(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>  
                                        <tr>
                                            <td>Pica</td>
                                            <td>1d10 Perforante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>5 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pico de guerra</td>
                                            <td>1d8 Perforante(Fue)</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Red</td>
                                            <td>-</td>
                                            <td>Arrojadiza, Especial</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tridente</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>5 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña4">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura ligera</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Acolchada</td>
                                            <td>CA 11 + Des</td>
                                            <td>Desventaja</td>
                                            <td>5 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuero</td>
                                            <td>CA 11 + Des</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuero tachonado</td>
                                            <td>CA 12 + Des</td>
                                            <td>-</td>
                                            <td>45 po</td>
                                            <td>5,90 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armadura intermedia</h5>
                                    <table>
                                        <tr>
                                            <td>Camisote de malla</td>
                                            <td>CA 13 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Coraza</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>400 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de escamas</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pieles</td>
                                            <td>CA 12 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>5,44 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Placas y malla</td>
                                            <td>CA 15 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>750 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armadura pesada</h5>
                                    <table>
                                        <tr>
                                            <td>Armadura de bandas</td>
                                            <td>CA 17</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>200 po</td>
                                            <td>27,22 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Armadura de placas</td>
                                            <td>CA 18</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>1500 po</td>
                                            <td>29,48 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de malla</td>
                                            <td>CA 16</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>75 po</td>
                                            <td>24,95 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota guarnecida</td>
                                            <td>CA 14</td>
                                            <td>Desventaja</td>
                                            <td>30 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Escudos</h5>
                                    <table>
                                        <tr>
                                            <td>Escudo</td>
                                            <td>CA 2</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña5">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Herramientas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Herramientas de albañil</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de alfarero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de carpintero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>8 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de cartógrafo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>15 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de constructor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de herrero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de joyero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de ladrón</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de navegante</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de peletero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de pintor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de soplador de vidrio</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de tallador de madera</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de tejedor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña6">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Herramientas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Herramientas de zapatero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de disfraz</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>1.36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de envenenador</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>0.91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de falsificación</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>15 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de herborista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>1.36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de alquimista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>3.63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de calígrafo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de cervecero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>4.08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Utensilios de cocinero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>3.63 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Instrumentos musicales</h5>
                                    <table>
                                        <tr>
                                            <td>Caramillo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>2 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuerno</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>3 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Dulcimer</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Flauta</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>2 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Flauta de pan</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>12 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña7">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Instrumentos musicales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Gaita</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran piano</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>400 po</td>
                                            <td>350 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Laúd</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>35 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lira</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Piano</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>3 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tambor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>6 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Viola</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Juegos</h5>
                                    <table>
                                        <tr>
                                            <td>Juego de cartas</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 pp</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Juego de dados</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 pp</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                    <h5>Monturas</h5>
                                    <table>
                                        <tr>
                                            <td>Silla de montar de carga</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>6,8 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar de monta</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>11,3 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar exótica</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>60 po</td>
                                            <td>18,1 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar militar</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>13,6 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña8">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Monturas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Burro o mula</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>8 po</td>
                                            <td>190,5 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de tiro</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>50 po</td>
                                            <td>244,9 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de monta</td>
                                            <td>-</td>
                                            <td>60 pies</td>
                                            <td>75 po</td>
                                            <td>217,7 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de guerra</td>
                                            <td>-</td>
                                            <td>60 pies</td>
                                            <td>400 po</td>
                                            <td>244,9 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Camello</td>
                                            <td>-</td>
                                            <td>50 pies</td>
                                            <td>50 po</td>
                                            <td>217,7 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Elefante</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>200 po</td>
                                            <td>598,7 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Munición</h5>
                                    <table>
                                        <tr>
                                            <td>Balas de palma</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>3 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de pistola</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>5 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de mosquete</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>8 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>50 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Dardos</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 pp</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Flechas</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Flechas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>5 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Saetas</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Saetas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>2 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña9">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Paquetes de Equipo</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Paquete de artista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>40 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de diplomático</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>39 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de erudito</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>40 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de explorador</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de explorador de mazmorras</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>12 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de ladrón</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>16 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de sacerdote</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>19 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                    <h5>Vehículos</h5>
                                    <table>
                                        <tr>
                                            <td>Barcaza</td>
                                            <td>-</td>
                                            <td>1 mph</td>
                                            <td>3000 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Bote de remos</td>
                                            <td>-</td>
                                            <td>1,5 mph</td>
                                            <td>50 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carreta</td>
                                            <td>-</td>
                                            <td>600 lb</td>
                                            <td>100 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carro</td>
                                            <td>-</td>
                                            <td>200 lb</td>
                                            <td>15 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carruaje</td>
                                            <td>-</td>
                                            <td>100 lb</td>
                                            <td>250 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Galera</td>
                                            <td>-</td>
                                            <td>4 mph</td>
                                            <td>30000 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Velero</td>
                                            <td>-</td>
                                            <td>2 mph</td>
                                            <td>10000 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña10">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Escudos</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Escudo</td>
                                            <td>CA 2</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña11">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura ligera</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Acolchada</td>
                                            <td>CA 11 + Des</td>
                                            <td>Desventaja</td>
                                            <td>5 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuero</td>
                                            <td>CA 11 + Des</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuero tachonado</td>
                                            <td>CA 12 + Des</td>
                                            <td>-</td>
                                            <td>45 po</td>
                                            <td>5,90 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña12">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura intermedia</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Camisote de malla</td>
                                            <td>CA 13 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Coraza</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>400 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de escamas</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pieles</td>
                                            <td>CA 12 + Des (máx. 2)</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>5,44 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Placas y malla</td>
                                            <td>CA 15 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>750 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña13">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura pesada</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Armadura de bandas</td>
                                            <td>CA 17</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>200 po</td>
                                            <td>27,22 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Armadura de placas</td>
                                            <td>CA 18</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>1500 po</td>
                                            <td>29,48 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de malla</td>
                                            <td>CA 16</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>75 po</td>
                                            <td>24,95 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota guarnecida</td>
                                            <td>CA 14</td>
                                            <td>Desventaja</td>
                                            <td>30 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña14">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Herramientas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Herramientas de albañil</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de alfarero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de carpintero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>8 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de cartógrafo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>15 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de constructor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de herrero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de joyero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de ladrón</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de navegante</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de peletero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de pintor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de soplador de vidrio</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de tallador de madera</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Herramientas de tejedor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña15">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Herramientas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Herramientas de zapatero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de disfraz</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>1.36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de envenenador</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>0.91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de falsificación</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>15 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Kit de herborista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>1.36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de alquimista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>3.63 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de calígrafo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>2.27 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Suministros de cervecero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>4.08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Utensilios de cocinero</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>3.63 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña16">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Instrumentos Musicales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Caramillo</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>2 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cuerno</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>3 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Dulcimer</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>25 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Flauta</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>2 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Flauta de pan</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>12 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gaita</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran piano</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>400 po</td>
                                            <td>350 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Laúd</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>35 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lira</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Piano</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>50 po</td>
                                            <td>3 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tambor</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>6 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Viola</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>30 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña17">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Juegos</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Juego de cartas</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 pp</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Juego de dados</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>1 pp</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña18">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Monturas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Burro o mula</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>8 po</td>
                                            <td>190,5 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de tiro</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>50 po</td>
                                            <td>244,9 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de monta</td>
                                            <td>-</td>
                                            <td>60 pies</td>
                                            <td>75 po</td>
                                            <td>217,7 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Caballo, de guerra</td>
                                            <td>-</td>
                                            <td>60 pies</td>
                                            <td>400 po</td>
                                            <td>244,9 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Camello</td>
                                            <td>-</td>
                                            <td>50 pies</td>
                                            <td>50 po</td>
                                            <td>217,7 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Elefante</td>
                                            <td>-</td>
                                            <td>40 pies</td>
                                            <td>200 po</td>
                                            <td>598,7 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar de carga</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>5 po</td>
                                            <td>6,8 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar de monta</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>11,3 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar exótica</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>60 po</td>
                                            <td>18,1 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Silla de montar militar</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>20 po</td>
                                            <td>13,6 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña19">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Munición</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Balas de palma</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>3 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de pistola</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>5 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de mosquete</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>8 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Balas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>50 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Dardos</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 pp</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Flechas</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Flechas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>5 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Saetas</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>1 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Saetas de plata</td>
                                            <td>-</td>
                                            <td>20 unidades</td>
                                            <td>2 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña20">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Paquete de equipo</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Paquete de artista</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>40 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de diplomático</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>39 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de erudito</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>40 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de explorador</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>10 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de explorador de mazmorras</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>12 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de ladrón</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>16 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Paquete de sacerdote</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>19 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña21">
                            <div class="ListaEquipo">
                                <h3>Otros</h3>
                                <h4>Vehículos</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Barcaza</td>
                                            <td>-</td>
                                            <td>1 mph</td>
                                            <td>3000 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Bote de remos</td>
                                            <td>-</td>
                                            <td>1,5 mph</td>
                                            <td>50 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carreta</td>
                                            <td>-</td>
                                            <td>600 lb</td>
                                            <td>100 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carro</td>
                                            <td>-</td>
                                            <td>200 lb</td>
                                            <td>15 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Carruaje</td>
                                            <td>-</td>
                                            <td>100 lb</td>
                                            <td>250 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Galera</td>
                                            <td>-</td>
                                            <td>4 mph</td>
                                            <td>30000 po</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>Velero</td>
                                            <td>-</td>
                                            <td>2 mph</td>
                                            <td>10000 po</td>
                                            <td>-</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña22">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Alabarda</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lanza de caballería</td>
                                            <td>1d12 Perforante(Fue)</td>
                                            <td>Alcance, Especial</td>
                                            <td>10 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>  
                                            <td>Látigo</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Alcance, Sutil</td>
                                            <td>2 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pica</td>
                                            <td>1d10 Perforante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>5 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña23">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                            <td>Hacha de mano</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Jabalina</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza,</td>
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
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Red</td>
                                            <td>-</td>
                                            <td>Arrojadiza, Especial</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tridente</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>5 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña24">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Ballesta ligera</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>25 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña25">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura ligera</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Acolchada</td>
                                            <td>CA 11 + Des</td>
                                            <td>Desventaja</td>
                                            <td>5 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armadura intermedia</h5>
                                    <table>
                                        <tr>
                                            <td>Cota de escamas</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Placas y malla</td>
                                            <td>CA 15 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>750 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armadura pesada</h5>
                                    <table>
                                        <tr>
                                            <td>Armadura de bandas</td>
                                            <td>CA 17</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>200 po</td>
                                            <td>27,22 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Armadura de placas</td>
                                            <td>CA 18</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>1500 po</td>
                                            <td>29,48 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de malla</td>
                                            <td>CA 16</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>75 po</td>
                                            <td>24,95 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota guarnecida</td>
                                            <td>CA 14</td>
                                            <td>Desventaja</td>
                                            <td>30 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña26">
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
                                            <td>Dardo</td>
                                            <td>1d4 Perforante(Des)</td>
                                            <td>Arrojadiza, Distancia, Sutil</td>
                                            <td>5 pc</td>
                                            <td>0,11 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña27">
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
                                            <td>Gran clava</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Dos manos</td>
                                            <td>2 pp</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Alabarda</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espadón</td>
                                            <td>2d6 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>50 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran hacha</td>
                                            <td>1d12 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>30 po</td>
                                            <td>3,18 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mazo de guerra</td>
                                            <td>2d6 Contundente(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pica</td>
                                            <td>1d10 Perforante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>5 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña28">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura pesada</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Armadura de bandas</td>
                                            <td>CA 17</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>200 po</td>
                                            <td>27,22 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Armadura de placas</td>
                                            <td>CA 18</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>1500 po</td>
                                            <td>29,48 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cota de malla</td>
                                            <td>CA 16</td>
                                            <td>Desventaja, Fuerza</td>
                                            <td>75 po</td>
                                            <td>24,95 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña29">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                            <td>Hacha de mano</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hoz</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Ligera</td>
                                            <td>1 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo ligero</td>
                                            <td>1d4 Contundente(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>2 pp</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cimitarra</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>25 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña30">
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
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña31">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Espadón</td>
                                            <td>2d6 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>50 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran hacha</td>
                                            <td>1d12 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>30 po</td>
                                            <td>3,18 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mazo de guerra</td>
                                            <td>2d6 Contundente(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña32">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Cimitarra</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>25 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espada corta</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>10 po</td>
                                            <td>0,91 kg</td>
                                        </tr>

                                        <tr>
                                            <td>Estoque</td>
                                            <td>1d8 Perforant(Fue)</td>
                                            <td>Sutil</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Látigo</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Alcance, Sutil</td>
                                            <td>2 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña33">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Bastón</td>
                                            <td>1d6 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>2 pp</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lanza</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                    <h5>Armas marciales</h5>
                                    <table>
                                        <tr>
                                            <td>Espada larga</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hacha de batalla</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>10 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo de guerra</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tridente</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>5 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña34">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                            <td>Hacha de mano</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Jabalina</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza,</td>
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
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña35">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Red</td>
                                            <td>-</td>
                                            <td>Arrojadiza, Especial</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tridente</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>5 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña36">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Ballesta ligera</td>
                                            <td>1d8 Perforante(Fue)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>25 po</td>
                                            <td>2,27 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña37">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña38">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura ligera</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Acolchada</td>
                                            <td>CA 11 + Des</td>
                                            <td>Desventaja</td>
                                            <td>5 po</td>
                                            <td>3,63 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña39">
                            <div class="ListaEquipo">
                                <h3>Armaduras</h3>
                                <h4>Armadura intermedia</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Cota de escamas</td>
                                            <td>CA 14 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>50 po</td>
                                            <td>9,07 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Placas y malla</td>
                                            <td>CA 15 + Des (máx. 2)</td>
                                            <td>Desventaja</td>
                                            <td>750 po</td>
                                            <td>18,14 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña40">
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
                                            <td>Dardo</td>
                                            <td>1d4 Perforante(Des)</td>
                                            <td>Arrojadiza, Distancia, Sutil</td>
                                            <td>5 pc</td>
                                            <td>0,11 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña41">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña42">
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
                                            <td>Gran clava</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Dos manos</td>
                                            <td>2 pp</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña43">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Alabarda</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espadón</td>
                                            <td>2d6 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>50 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran hacha</td>
                                            <td>1d12 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>30 po</td>
                                            <td>3,18 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mazo de guerra</td>
                                            <td>2d6 Contundente(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pica</td>
                                            <td>1d10 Perforante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>5 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña44">
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
                                            <td>Gran clava</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Dos manos</td>
                                            <td>2 pp</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña45">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Alabarda</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espadón</td>
                                            <td>2d6 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>50 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Gran hacha</td>
                                            <td>1d12 Cortante(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>30 po</td>
                                            <td>3,18 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Guja</td>
                                            <td>1d10 Cortante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>20 po</td>
                                            <td>2,72 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mazo de guerra</td>
                                            <td>2d6 Contundente(Fue)</td>
                                            <td>Dos manos, Pesada</td>
                                            <td>10 po</td>
                                            <td>4,54 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pica</td>
                                            <td>1d10 Perforante(Fue)</td>
                                            <td>Alcance, Dos manos, Pesada</td>
                                            <td>5 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña46">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                            <td>Hacha de mano</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>5 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hoz</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Ligera</td>
                                            <td>1 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo ligero</td>
                                            <td>1d4 Contundente(Fue)</td>
                                            <td>Arrojadiza, Ligera</td>
                                            <td>2 pp</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña47">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Cimitarra</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>25 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de Palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña48">
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
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña49">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Aguja de cerbatana</td>
                                            <td>1 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>10 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Arco largo</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>Distancia, Dos manos, Munición, Pesada</td>
                                            <td>50 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta de mano</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>75 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Ballesta pesada</td>
                                            <td>1d10 Perforante(Des)</td>
                                            <td>De carga, Distancia, Dos manos, Munición</td>
                                            <td>50 po</td>
                                            <td>8,16 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Mosquete de repetición</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición, Dos manos</td>
                                            <td>75 po</td>
                                            <td>4,08 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola de palma</td>
                                            <td>1d6 Perforante(Des)</td>
                                            <td>De carga, Distancia, Ligera, Munición</td>
                                            <td>50 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Pistola</td>
                                            <td>1d8 Perforante(Des)</td>
                                            <td>De carga, Distancia, Munición</td>
                                            <td>50 po</td>
                                            <td>0,45 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña50">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
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
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña51">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas marciales</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Cimitarra</td>
                                            <td>1d6 Cortante(Fue)</td>
                                            <td>Ligera, Sutil</td>
                                            <td>25 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Espada corta</td>
                                            <td>1d6 Perforante</td>
                                            <td>Ligera, Sutil(Fue)</td>
                                            <td>10 po</td>
                                            <td>0,91 kg</td>
                                        </tr>

                                        <tr>
                                            <td>Estoque</td>
                                            <td>1d8 Perforante(Fue)</td>
                                            <td>Sutil</td>
                                            <td>25 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Látigo</td>
                                            <td>1d4 Cortante(Fue)</td>
                                            <td>Alcance, Sutil</td>
                                            <td>2 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña52">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Bastón</td>
                                            <td>1d6 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>2 pp</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Lanza</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
                                            <td>1 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="pestaña" id="pestaña53">
                            <div class="ListaEquipo">
                                <h3>Armas</h3>
                                <h4>Armas sencillas</h4>
                                <div class="diseñoTabla">
                                    <table>
                                        <tr>
                                            <td>Espada larga</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>1,36 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Hacha de batalla</td>
                                            <td>1d8 Cortante(Fue)</td>
                                            <td>Versátil</td>
                                            <td>10 po</td>
                                            <td>1,81 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Martillo de guerra</td>
                                            <td>1d8 Contundente(Fue)</td>
                                            <td>Versátil</td>
                                            <td>15 po</td>
                                            <td>0,91 kg</td>
                                        </tr>
                                        <tr>
                                            <td>Tridente</td>
                                            <td>1d6 Perforante(Fue)</td>
                                            <td>Arrojadiza, Versátil</td>
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
                <div class="ContenedorBotonesEquipo">
                    <div class="pestañasBotones" id="pestañaBotones">
                    </div>
                </div>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
        </footer>

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
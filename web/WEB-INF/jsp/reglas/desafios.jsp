<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Valores de Desafio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/desafiosCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>       
            <div class="resumenDesafios">
                <h2 class="Titulos">Valor de Desafio
                    <select id="selectMostrar">
                        <option value="mostrar" selected>Mostrar Todo</option>
                        <option value="mostrar1">Niveles</option>
                        <option value="mostrar2">Taberna</option>
                        <option value="mostrar3">Vendedor</option>
                    </select>
                    <button onclick="guardarSeleccion()">Buscar</button></h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Incluso las monedas pueden ser cambiadas por otras.
                        añadir más o cambiar los valores de cada una, </p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div class="listaDesafios">
                    <div id="pestaña1">
                        <h3>Niveles aceptables</h3>
                        <div class="niveles">
                            <table>
                                <tr class="nivelesPrimeraFila">
                                    <th>Nivel</th>
                                    <th>Fácil</th>
                                    <th>Medio</th>
                                    <th>Difícil</th>
                                    <th>Mortal </th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>25</td>
                                    <td>50</td>
                                    <td>75</td>
                                    <td>100</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td>150</td>
                                    <td>200</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>75</td>
                                    <td>150</td>
                                    <td>225</td>
                                    <td>400</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>125</td>
                                    <td>250</td>
                                    <td>375</td>
                                    <td>500</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>250</td>
                                    <td>500</td>
                                    <td>750</td>
                                    <td>1100</td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>300</td>
                                    <td>600</td>
                                    <td>900</td>
                                    <td>1400</td>
                                </tr>
                                <tr>
                                    <td>7</td>
                                    <td>350</td>
                                    <td>750</td>
                                    <td>1100</td>
                                    <td>1700</td>
                                </tr>
                                <tr>
                                    <td>8</td>
                                    <td>450</td>
                                    <td>900</td>
                                    <td>1400</td>
                                    <td>2100</td>
                                </tr>
                                <tr>
                                    <td>9</td>
                                    <td>550</td>
                                    <td>1100</td>
                                    <td>1600</td>
                                    <td>2400</td>
                                </tr>
                                <tr>
                                    <td>10</td>
                                    <td>600</td>
                                    <td>1200</td>
                                    <td>1900</td>
                                    <td>2800</td>
                                </tr>
                                <tr>
                                    <td>11</td>
                                    <td>800</td>
                                    <td>1600</td>
                                    <td>2400</td>
                                    <td>3200</td>
                                </tr>
                                <tr>
                                    <td>12</td>
                                    <td>1000</td>
                                    <td>2000</td>
                                    <td>3000</td>
                                    <td>4000</td>
                                </tr>
                                <tr>
                                    <td>13</td>
                                    <td>1100</td>
                                    <td>2200</td>
                                    <td>3400</td>
                                    <td>4500</td>
                                </tr>
                                <tr>
                                    <td>14</td>
                                    <td>1250</td>
                                    <td>2500</td>
                                    <td>3800</td>
                                    <td>5100</td>
                                </tr>
                                <tr>
                                    <td>15</td>
                                    <td>1400</td>
                                    <td>2800</td>
                                    <td>4300</td>
                                    <td>5700</td>
                                </tr>
                                <tr>
                                    <td>16</td>
                                    <td>1600</td>
                                    <td>3200</td>
                                    <td>4800</td>
                                    <td>6400</td>
                                </tr>
                                <tr>
                                    <td>17</td>
                                    <td>2000</td>
                                    <td>3900</td>
                                    <td>5900</td>
                                    <td>7900</td>
                                </tr>
                                <tr>
                                    <td>18</td>
                                    <td>2100</td>
                                    <td>4200</td>
                                    <td>6300</td>
                                    <td>8500</td>
                                </tr>
                                <tr>
                                    <td>19</td>
                                    <td>2400</td>
                                    <td>4900</td>
                                    <td>7300</td>
                                    <td>9700</td>
                                </tr>
                                <tr>
                                    <td>20</td>
                                    <td>2800</td>
                                    <td>5700</td>
                                    <td>8500</td>
                                    <td>12700</td>
                                </tr>
                            </table>
                            <div class="nivelesExplicacion">
                                <p>Texto de ejemplo largo, ejemplo largo, ejemplo largo, ejemplo largo,
                                    ejemplo largo, ejemplo largo, ejemplo largo, ejemplo largo</p>
                            </div>
                        </div>
                    </div>
                </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

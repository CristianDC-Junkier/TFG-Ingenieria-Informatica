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
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>       
            <div class="resumenDesafios">
                <h2 class="Titulos">Valor de Desafio</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div class="listaDesafios">
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
                            <p>A nuestra izquierda, tenemos la lista de los niveles
                                con las dificultades para cada jugador, la lista está
                                pensada para 1 jugador, es decir, si tienes 4 jugadores de nivel 1
                                y quieres que sea una dificultad normal tendras que añadir enemigos
                                hasta llegar a 4 x 50 = 200 de dificultad, o directamente 
                                un enemigo de 200 de dificultad, esta puntuación de dificultad
                                puede cambiar según cuanto tus jugadores sepan del juego o que
                                libertad le des tu en sus movimientos, a veces, si un jugador
                                nunca ha jugado, la dificultad normal puede ser complicada para él,
                                y lo contrario un jugador experimentado puede encontrar
                                la dificultad normal como una dificultad facilona.<br>
                                Hay que mencionar, que esta tabla cuanto más se acerca a niveles altos,
                                a partir del 14 ó 15, puede resultar complicada de que sea exacta,
                                pues los enemigos cuentan con númerosas habilidades que según el grupo
                                puede hacer que este sea muy fácil de derrotar o por otra parte
                                imposible de bajarle los puntos de golpe.<br><br>
                                Si hablamos de las pruebas de características, la dificultad es más fácil
                                de entender, teniendo en cuenta de que se usa un dado de 20 caras, 10 hacia abajo
                                será una prueba fácil y 10 hacia arriba dificil, cuanto más se acerque a los extremos
                                más o menos dificicultad estará dandole el DM a esa prueba.<br>
                                Algunas pruebas valga la redundancia, no necesitan prueba de habilidad, por ejemplo
                                si un gran guerrero intenta levantar un carro poco pesado, quizá no necesite la tirada,
                                pero a veces al DM puede interesarle que si se haga aunque salga lo que salga
                                sea que si, para levantar la moral de sus jugadores en situaciones adversas, 
                                del mismo modos existen situaciones imposibles, como saltar 20 metros de largo, puedes
                                dejar que tus jugadores tiren los dados aunque no lleguen a ningún puerto, para darles
                                la sensación de que al menos lo han intentado.</p>
                        </div>
                    </div>
                </div>                    
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

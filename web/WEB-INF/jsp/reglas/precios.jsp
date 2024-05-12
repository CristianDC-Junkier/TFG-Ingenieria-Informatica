<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Precios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/preciosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>       
            <div class="ResumenPrecios">
                <h2 class="Titulos">Precios
                    <select id="selectMostrar">
                        <option value="mostrar" selected>Mostrar Todo</option>
                        <option value="mostrar1">Monedas</option>
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
                <div class="ListaPrecios">
                    <div id="pestaña1">
                        <h3>Valor de las Monedas</h3>
                        <div class="Monedas">
                            <table>
                                <tr class="MonedasPrimeraFila">
                                    <th>Piezas</th>
                                    <th>Pc</th>
                                    <th>Pp</th>
                                    <th>Pe</th>
                                    <th>Po</th>
                                    <th>Ppt</th>
                                </tr>
                                <tr>
                                    <th>Piezas de Cobre</th>
                                    <td>1</td>
                                    <td>1/10</td>
                                    <td>1/50</td>
                                    <td>1/100</td>
                                    <td>1/1000</td>
                                </tr>
                                <tr>
                                    <th>Piezas de Plata</th>
                                    <td>10</td>
                                    <td>1</td>
                                    <td>1/5</td>
                                    <td>1/10</td>
                                    <td>1/100</td>
                                </tr>
                                <tr>
                                    <th>Piezas de Electro</th>
                                    <td>50</td>
                                    <td>5</td>
                                    <td>1</td>
                                    <td>1/2</td>
                                    <td>1/20</td>
                                </tr>
                                <tr>
                                    <th>Piezas de Oro</th>
                                    <td>100</td>
                                    <td>10</td>
                                    <td>2</td>
                                    <td>1</td>
                                    <td>1/10</td>
                                </tr>
                                <tr>
                                    <th>Piezas de Platino</th>
                                    <td>1000</td>
                                    <td>100</td>
                                    <td>20</td>
                                    <td>10</td>
                                    <td>1</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div id="pestaña2">
                        <h3>Precios de Taberna</h3>
                        <div class="ListaPreciosTipo">

                            <h4>Taberna Sencilla</h4>
                            <div class="TipoListaPrecio">
                                <h5>Alojamiento</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Alojamiento Sencillo: 5 de plata</li>
                                        <li>Alojamiento Normal: 1 de oro</li>
                                    </ul>
                                </div>
                                <h5>Comidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla: 1 de plata</li>
                                        <li>Comida Elaborada: 3 de plata</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Bebida Sencilla: 2 de cobre</li>
                                        <li>Bebida Elaborada: 5 de cobre</li>
                                        <li>Bebida De Lujo: 1 de plata</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Taberna Sofisticada</h4>
                            <div class="TipoListaPrecio">
                                <h5>Alojamiento</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Alojamiento Sencillo: 8 de plata</li>
                                        <li>Alojamiento Normal: 2 de oro</li>
                                    </ul>
                                </div>
                                <div class="ListaPrecio">
                                    <h5>Comidas</h5>
                                    <ul>
                                        <li>Comida Sencilla: 2 de plata</li>
                                        <li>Comida Elaborada: 5 de plata</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Bebida Sencilla: 3 de cobre</li>
                                        <li>Bebida Elaborada: 1 de plata</li>
                                        <li>Bebida De Lujo: 5 de plata</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Taberna de Lujo</h4>
                            <div class="TipoListaPrecio">
                                <h5>Alojamiento</h5>                                
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Alojamiento Sencillo: 3 de oro</li>
                                        <li>Alojamiento Normal: 5 de oro</li>
                                    </ul>
                                </div>
                                <div class="ListaPrecio">
                                    <h5>Comidas</h5>
                                    <ul>
                                        <li>Comida Sencilla: 1 de oro</li>
                                        <li>Comida Elaborada: 2 de oro</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Bebida Sencilla: 1 de plata</li>
                                        <li>Bebida Elaborada: 8 de plata</li>
                                        <li>Bebida De Lujo: 1 de oro</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="pestaña3">
                        <h3>Vendedores</h3>
                        <div class="ListaPreciosTipo">

                            <h4>Vendedor sencillo</h4>
                            <div class="TipoListaPrecio">
                                <h5>Objetos comunes</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: -10 de valor</li>
                                        <li>Objeto mejorado: -5 de valor</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: -15 de valor</li>
                                        <li>Objeto poco-común: -10 de valor</li>
                                        <li>Objeto raro: -5 de valor</li>
                                        <li>Objeto epico: valor normal</li>
                                        <li>Objeto legendario: valor normal</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes: -15 de valor</li>
                                        <li>Pociones raras: -5 de valor</li>
                                        <li>Pociones legendarias: valor normal</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Vendedor normal</h4>
                            <div class="TipoListaPrecio">
                                <h5>Objetos comunes</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: valor normal</li>
                                        <li>Objeto mejorado: valor normal</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: valor normal</li>
                                        <li>Objeto poco-común: valor normal</li>
                                        <li>Objeto raro:  valor normal</li>
                                        <li>Objeto epico:  +5 de valor</li>
                                        <li>Objeto legendario: +10 de valor</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes: valor normal</li>
                                        <li>Pociones raras: valor normal </li>
                                        <li>Pociones legendarias: +5 de valor</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Vendedores de lujo</h4>
                            <div class="TipoListaPrecio">
                                <h5>Objetos comunes</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: +5 de valor</li>
                                        <li>Objeto mejorado: +10 de valor</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común: +5 de valor</li>
                                        <li>Objeto poco-común: +10 de valor</li>
                                        <li>Objeto raro: +15 de valor</li>
                                        <li>Objeto epico: +25 de valor</li>
                                        <li>Objeto legendario: +50 de valor</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes:+5 de valor</li>
                                        <li>Pociones raras:+10 de valor</li>
                                        <li>Pociones legendarias:+20 de valor</li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    <script src="/TFG/js/reglas/preciosJS.js"></script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

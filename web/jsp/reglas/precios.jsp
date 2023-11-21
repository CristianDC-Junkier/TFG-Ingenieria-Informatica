<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guidance4\Reglas\Precios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/preciosCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/jsp/menuNav.jsp" />
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
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Comidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Taberna Sofisticada</h4>
                            <div class="TipoListaPrecio">
                                <h5>Alojamiento</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Comidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Taberna de Lujo</h4>
                            <div class="TipoListaPrecio">
                                <h5>Alojamiento</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Comidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
                                    </ul>
                                </div>
                                <h5>Bebidas</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Comida Sencilla:</li>
                                        <li>Comida Elaborada:</li>
                                        <li>Comida De Lujo</li>
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
                                        <li>Objeto común:</li>
                                        <li>Objeto mejorado:</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común:</li>
                                        <li>Objeto poco-común:</li>
                                        <li>Objeto raro:</li>
                                        <li>Objeto epico:</li>
                                        <li>Objeto legendario:</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes:</li>
                                        <li>Pociones raras:</li>
                                        <li>Pociones legendarias:</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Vendedor normal</h4>
                            <div class="TipoListaPrecio">
                                <h5>Objetos comunes</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común:</li>
                                        <li>Objeto mejorado:</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común:</li>
                                        <li>Objeto poco-común:</li>
                                        <li>Objeto raro:</li>
                                        <li>Objeto epico:</li>
                                        <li>Objeto legendario:</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes:</li>
                                        <li>Pociones raras:</li>
                                        <li>Pociones legendarias:</li>
                                    </ul>
                                </div>
                            </div>

                            <h4>Vendedores de lujo</h4>
                            <div class="TipoListaPrecio">
                                <h5>Objetos comunes</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común:</li>
                                        <li>Objeto mejorado:</li>
                                    </ul>
                                </div>
                                <h5>Objetos mágicos</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Objeto común:</li>
                                        <li>Objeto poco-común:</li>
                                        <li>Objeto raro:</li>
                                        <li>Objeto epico:</li>
                                        <li>Objeto legendario:</li>
                                    </ul>
                                </div>
                                <h5>Pociones</h5>
                                <div class="ListaPrecio">
                                    <ul>
                                        <li>Pociones comunes:</li>
                                        <li>Pociones raras:</li>
                                        <li>Pociones legendarias:</li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <footer>
        &copy; 2023 Cristian Delgado Cruz
    </footer>

    <script>

        var botonMostrarClick = false;

        //Funcion que muestra la pestaña seleccionada
        function mostrarPestaña() {

            var seleccionMostrar = document.getElementById('selectMostrar');
            var valorseleccionado = seleccionMostrar.value;

            if (valorseleccionado === 'mostrar')
            {
                for (var i = 1; i <= 3; i++)
                {

                    document.getElementById('pestaña' + i).style.display = 'inline';
                }
            } else {
                for (var i = 1; i <= 3; i++)
                {
                    if (valorseleccionado !== ('mostrar' + i)) {

                        document.getElementById('pestaña' + i).style.display = 'none';
                    } else if (valorseleccionado === ('mostrar' + i))
                    {
                        document.getElementById('pestaña' + i).style.display = 'inline';
                    }
                }
            }
        }
        // Función para guardar la selección en sessionStorage
        function guardarSeleccion() {
            var seleccionMostrar = document.getElementById('selectMostrar');
            var valorseleccionado = seleccionMostrar.value;
            sessionStorage.setItem('SelectGuardadoMostrar', valorseleccionado);

            botonMostrarClick = true;
            mostrarPestaña();
        }

        // Al cargar la página, restaura el valor seleccionado si está almacenado
        window.onload = function () {

            var selectMostrar = document.getElementById('selectMostrar');
            valorGuardadoMostrar = sessionStorage.getItem('SelectGuardadoMostrar');

            if (valorGuardadoMostrar) {
                selectMostrar.value = valorGuardadoMostrar;
            } else {
                selectMostrar.value = 'mostrar';
            }
        };

        // Limpiar la selección guardada al abandonar la página si no se hizo clic en el botón
        window.addEventListener('beforeunload', function (event) {
            if (!botonMostrarClick) {
                sessionStorage.removeItem('SelectGuardadoMostrar');
            }
        }
        );

        mostrarPestaña();
    </script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

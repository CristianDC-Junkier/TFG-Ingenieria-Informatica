<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Objetos_Mágicos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/objetosmagicosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <div class="resumenObjetosMagicos">
                <h2 class="Titulos">Objetos Mágicos</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div class="listaObjetosMagicos">
                    <h3>Que es un objeto mágico, ejemplos</h3>
                    <div class="objetosMagicos">
                        <table>
                            <tr class="objetosMagicosPrimerafila">
                                <th>Objeto</th>
                                <th>Magia</th>
                                <th>Valor aumentado</th>
                                <th>Niveles recomendados</th>
                            </tr>
                            <tr>
                                <td>Armas y Armaduras</td>
                                <td>+1 en daño o protección</td>
                                <td>20 de oro</td>
                                <td>3-8</td>
                            </tr>
                            <tr>
                                <td>Armas y Armaduras</td>
                                <td>+2 en daño o protección</td>
                                <td>40 de oro</td>
                                <td>6-10</td>
                            </tr>
                            <tr>
                                <td>Armas</td>
                                <td>+Dado de elemento</td>
                                <td>40 de oro por dado</td>
                                <td>5-12</td>
                            </tr>
                            <tr>
                                <td>Armaduras</td>
                                <td>+Resistencia a elemento</td>
                                <td>50 de oro por elemento</td>
                                <td>5-12</td>
                            </tr>
                            <tr>
                                <td>Anillos</td>
                                <td>Aura, o hechizo</td>
                                <td>10 por nivel del hechizo</td>
                                <td>3-20</td>
                            </tr>
                            <tr>
                                <td>Pociones</td>
                                <td>Aura, o hechizo</td>
                                <td>5 por nivel del hechizo</td>
                                <td>3-20</td>
                            </tr>
                            <tr>
                                <td>Utilidades</td>
                                <td>Cualquier otra cosa</td>
                                <td>+10, según lo que sea</td>
                                <td>1-20</td>
                            </tr>
                        </table>
                        <div class="objetosMagicosexplicacion">
                            <p>Si tenemos en cuenta las posibilidades infinitas de D&D
                                cualquier objeto mágico se puede encontrar por el mundo, desde
                                objetos que generan llamas a objetos que destruyen universos, o que
                                simplementes, son mas resistentes que la media, o la combinación
                                de todos estos<br><br>
                                Aqui se dan algunos ejemplos de todos los tipos con sus valores
                                para poder dar a entender como se debería llevar a cabo los aumentos de precio
                                y cuando darlos.</p>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

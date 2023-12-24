<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Dotes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/dotesCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main> 
            <div>
                <h6 id="Arriba" class = "BusquedaDote">
                    <a href="#Normal" id="A">&nbsp;&nbsp;Normales</a>
                    <a href="#Racial">&nbsp;&nbsp;Raciales</a>
                    <a href="#Draconido" id="P">&nbsp;&nbsp;Dracónidos&nbsp;</a>
                </h6>
            </div>
            <div class="ResumenDotes">
                <h2 class="Titulos">Dotes</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Los Dotes pueden ser usados o no por los jugadores, el 
                        DM puede decidir si se permite usarse o no, cuantos se usan
                        y cuales combinaciones no estan permitidas, o definir
                        otros nuevos.</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div class="ListaDotes">
                    <h4 id = Normal>Normales</h4>
                    <div class="ResumenDote">
                        <div class="tituloDote"><h5>Acechador</h5></div>
                        <div class="ContenidoDote">
                            <ul>
                                <li class="RequisitoDote">Requisito: Destreza 13 o superior</li>
                                <li>Puedes intentar esconderte cuando estás en 
                                    penumbra de la criatura de la cual te estás 
                                    ocultando.</li>
                                <li>Cuando estás escondido de una criatura y 
                                    fallas con un arma a distancia, hacer el 
                                    ataque no delatará tu posición.</li>
                                <li>La luz tenue no supone una desventaja para 
                                    tus tiradas de Sabiduría (Percepción) si estas 
                                    se basan en la vista. 
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="ResumenDote">
                        <div class="tituloDote"><h5>Actor</h5></div>
                        <div class="ContenidoDote">
                            <ul>
                                <li>Incrementa tu puntuación de Carisma en 1, 
                                    hasta un máximo de 20.</li>
                                <li> Tienes ventaja en las tiradas de Carisma 
                                    (Engañar) y Carisma (Interpretar) cuando 
                                    intentas hacerte pasar por otra persona.</li>
                                <li>Puedes imitar el habla de otra persona o los 
                                    sonidos hechos por otras criaturas. Debes 
                                    haber oído a la persona hablar, o haber 
                                    escuchado a la criatura haber hecho el 
                                    sonido, por al menos un minuto. Una tirada 
                                    exitosa de Sabiduría (Perspicacia) 
                                    contra tu tirada de Carisma (Engañar) 
                                    permite a alguien o algo que escucha 
                                    determinar que el efecto es falso.
                                </li>
                            </ul>
                        </div>
                    </div>



                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

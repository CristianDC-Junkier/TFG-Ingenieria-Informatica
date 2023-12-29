<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Interpretar</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/interpretacionCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Interpretar</h2>
            <hr color="black">
            <div class="bloqueInterpretar">
                <h3>Actuar</h3>
                <div class="infoInterpretar">
                    <div>
                        <p>
                            Para interpretar bien a un personaje, se debe entender
                            que D&D es un juego, y se actuar en base a eso.<br> 
                            Para empezar hay que separar tu propia personalidad con 
                            la que deseas que  tenga tu personaje, si estás comenzando
                            esto puede ser un poco complicado, pero según vayas jugando
                            se te hará más y más fácil, si sigues los siguientes consejos
                            podrás ayudarte a ti mismo a encaminar tu actuación:</p>
                        <p> No tengas miedo a hablar, el juego consiste en eso, no 
                            te preocupes por la vergüenza, es algo normal al principio.<br><br>
                            Especifica con mucho detalle tus acciones, investigar las páginas
                            de un libro o mirar solo la portada, puede hacer que cumplas o no
                            una misión.<br><br>
                            No intentes hacer cosas inverosímiles, si tu personaje jamas
                            ha visto un barco, es imposible que sepa que partes tiene.<br><br>
                            No pises los turnos de los demás, si alguien esta hablando o 
                            realizando una acción, no puedes hacer o decir algo por encima,
                            si quereis hacer algo que os haga enfretaros, hacedlo ordenadamente.<br><br>
                            Ten en cuenta siempre la personalidad de tu personaje, un personaje 
                            bueno, no mataría para conseguir oro, y uno tímido nunca sería la
                            cara del grupo.<br><br>
                            No le compliques la vida al DM, poder hacer de todo no significa
                            que debas, entrar en todas las casas, hablar con todos los npc,
                            intentar hacerlo todo, alargará la partida y hará más complicado
                            que sigaís un hilo, lo mismo si matas a todos los personajes de 
                            la historia o la misión.<br><br>
                            Primero especifica que quieres hacer, luego tira los dados, no puedes
                            tirar hasta que te salga un número alto y luego decir que ibas a hacer,
                            igualmente no puedes tirar un dado y decir "Quiero realizar una prueba
                            de percepción". El DM indica que pruebas debes hacer, los jugadores
                            solo especifican la acción.<br><br>
                            No hagas trampas, es lo más importante, si las cosas salen mal
                            o bien por los dados, es lo normal, la historia también debe ser
                            un poco aleatoria.</p>
                        <p>  Si realizas una buena actuación, tu DM te podrá recompensar con inspiración,
                            la inspiración permite al jugador darse ventaja en cualquier tirada que 
                            quiera.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="2">En que se basan las habilidades</th>
                            </tr>
                            <tr>
                                <th>Atributo</th>
                                <th>Funcionamiento</th>
                            </tr>
                            <tr>
                                <td>Fuerza</td>
                                <td>Potencia física y Capacidades deportivas</td>
                            </tr>
                            <tr>
                                <td>Destreza</td>
                                <td>Agilidad y Reflejos.</td>
                            </tr>
                            <tr>
                                <td>Constitución</td>
                                <td>Resistencia y Salud</td>
                            </tr>
                            <tr>
                                <td>Inteligencia</td>
                                <td>Razonamiento y Memoria</td>
                            </tr>
                            <tr>
                                <td>Sabiduría</td>
                                <td>Intuición y Presentimientos</td>
                            </tr>
                            <tr>
                                <td>Carisma</td>
                                <td>Fuerza de la personalidad y Presencia</td>
                            </tr>
                        </table>
                        <br>
                        <p style="font-size: 1.5vmin">
                            Hay una manera divertida de explicar los atributos, y es
                            utilizando un tomate:<br>
                            Fuerza: Capacidad de espachurrar un tomate <br>
                            Destreza: Capacidad de esquivar un tomatazo<br>
                            Constitución: Capacidad de no ser envenado con un tomate 
                            podrido.<br>
                            Inteligencia: Capacidad de saber que el tomate es una fruta.<br>
                            Sabiduría: Capacidad de saber que una ensalada lleva tomate<br>
                            Carisma: Capacidad de vender un tomate.
                        </p>
                    </div>
                </div>
                <h3>Habilidades</h3>
                <div class="infoInterpretar">
                    <div>
                        <table>
                            <tr>
                                <th colspan="3">Descripción de Habilidades</th>
                            </tr>
                            <tr>
                                <th>Habilidad</th>
                                <th>Atributo</th>
                                <th>Descripción</th>
                            </tr>
                            <tr>
                                <td>Acrobacias</td>
                                <td>Destreza</td>
                                <td>Pruebas de agilidad como dar una voltereta,
                                    caer bien, pasar entre dos trampas etc</td>
                            </tr>
                            <tr>
                                <td>Atletismo</td>
                                <td>Fuerza</td>
                                <td>Pruebas de fuerza bruta como mover algo,
                                    levantar algo pesado, empujar a alguien etc</td>
                            </tr>
                            <tr>
                                <td>Conocimiento Arcano</td>
                                <td>Inteligencia</td>
                                <td>Pruebas de inteligencia como saber sobre un hechizo,
                                    sobre un objeto mágico, o una maldición etc</td>
                            </tr>
                            <tr>
                                <td>Engañar</td>
                                <td>Carisma</td>
                                <td>Pruebas de diálogo como engañar sobre un precio,
                                    sobre algo que has hecho o que no has hecho etc</td>
                            </tr>
                            <tr>
                                <td>Interpretar</td>
                                <td>Carisma</td>
                                <td>Pruebas de actuación como hacerte pasar por otra
                                    persona, por un animal, por un dios etc</td>
                            </tr>
                            <tr>
                                <td>Intimidar</td>
                                <td>Carisma</td>
                                <td>Pruebas de presencia como obligar a alguien a
                                    que haga algo por ti, hacer que alguien huya
                                    de una pelea etc</td>
                            </tr>
                            <tr>
                                <td>Investigación</td>
                                <td>Inteligencia</td>
                                <td>Pruebas de memoria como en algo que ves
                                    buscar nueva información, descubrir nuevas pistas
                                    o seguir las que ya tienes etc</td>
                            </tr>
                            <tr>
                                <td>Medicina</td>
                                <td>Sabiduría</td>
                                <td>Pruebas de salud como curar a alguien herido,
                                    vendar a una persona, descubrir una enfermedad etc</td>
                            </tr>
                            <tr>
                                <td>Naturaleza</td>
                                <td>Inteligencia</td>
                                <td>Pruebas de entorno como descubrir un tipo de planta,
                                    saber si algo es venenoso, predecir el clima etc</td>
                            </tr>
                            <tr>
                                <td>Historia</td>
                                <td>Inteligencia</td>
                                <td>Pruebas de memoria como acordarse de un acontecimiento,
                                    recordar una fecha, comprender la sociedad etc</td>
                            </tr>
                            <tr>
                                <td>Juego de manos</td>
                                <td>Destreza</td>
                                <td>Pruebas de reflejos como mover un objeto sin que nadie
                                    se de cuenta, hacer malabares, hacer trampas a las cartas etc</td>
                            </tr>
                            <tr>
                                <td>Percepción</td>
                                <td>Sabiduría</td>
                                <td>Pruebas de entorno como derse cuenta de algo que no se ve
                                    a simple vista, reconocer algo oculto etc</td>
                            </tr>
                            <tr>
                                <td>Perspicacia</td>
                                <td>Sabiduría</td>
                                <td>Pruebas de comprensión como saber si alguien te esta 
                                    mintiendo, o saber sobre las intenciones de alguien etc</td>
                            </tr>
                            <tr>
                                <td>Persuadir</td>
                                <td>Carisma</td>
                                <td>Pruebas de diálogo como convecer a los personajes
                                    sin recurrir a la intimidación para hacer algo, para
                                    que bajen los precios etc</td>
                            </tr>
                            <tr>
                                <td>Religión</td>
                                <td>Inteligencia</td>
                                <td>Pruebas de memoria como saber sobre dioses y mitología,
                                    sobre esculturas divinas, sobre textos sagrados etc</td>
                            </tr>
                            <tr>
                                <td>Sigilo</td>
                                <td>Destreza</td>
                                <td>Pruebas de agilidad como esconderte entre la maleza,
                                    que no se te escuche al pasar, o que no te vean en la 
                                    oscuridad etc</td>
                            </tr>
                            <tr>
                                <td>Supervivencia</td>
                                <td>Sabiduría</td>
                                <td>Pruebas de todo tipo como encontrar presas, seguir huellas
                                    quitar o poner trampas, no perderse, encontrar agua y comida etc</td>
                            </tr>
                            <tr>
                                <td>Trato con animales</td>
                                <td>Sabiduría</td>
                                <td>Pruebas de entendimiento como comprender el comportamiento
                                    animal, ser capaz de trasmitir a los animales cosas, 
                                    calmar bestias etc</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <h3>Tiradas y Ventajas</h3>
                <div class="infoInterpretar">
                    <div>
                        <p>
                            Cuando el DM te indica que debes realizar una prueba de una habilidad,
                            por ejemplo percepción para comprobar si una habitación tiene
                            algún enemigo se realiza una tirada con el dado de 20 caras,
                            al cual deberás sumarle tu modificador de tu habilidad de percepción,
                            tras ello el DM habrá decidido un Valor de Desafio según la dificultad
                            el que deberás de igualar o superar con tu tirada, habrás 
                            superado la prueba</p>
                        <p> El modificador de las habilidades se consigue de una forma simple, 
                            cada habilidad depende de un atributo, en nuestro caso percepción
                            depende de la sabiduría del personaje, es decir, si el modificador de
                            sabiduría es de +2 , teniendo 14 o 15 de sabiduría, nuestro modificador
                            de percepción será de +2, a menos que , tengamos competencia en esa habilidad.<br>
                            Tener competencia significa que estas especializado en esa habilidad, tu raza,
                            tu clase y tu trasfondo además de algunos dotes confieren esas competencias,
                            al tener competencia, tu modificador de habilidad será el del atributo más
                            el bonificador de competencia, el cual escala con el nivel, o el nivel de la
                            clase más alta si estas realizando una multiclase. Por ejemplo un personaje
                            de nivel 1 tendrá un bonificador de competencia de +2 y con un modificador 
                            +2 de sabiduría tendrá lo que saque en el d20 + 4 en total.</p>
                        <p> Por otra parte existen situaciones donde el personaje puede enfrentarse
                            a la tirada de otro personaje, por ejemplo, para empujar a tu compañero
                            si el quiere enfrentarse a tí, los dos tirariaís un d20, tú en atletismo
                            y el por ejemplo, si quiere empujar de vuelta sería también atletismo o
                            si quiere esquivar el empujón, sería con acrobacias, y quien gane 
                            sería el que realiza la acción.</p>
                        <p> Por otra parte pueden haber situaciones donde por culpa de cosas
                            externas tu intento sea más fácil o más dificil, por ejemplo,
                            si intentas seguir huellas en la lluvia será más dificil, y tendrás
                            desventaja, si tienes desventaja, debes tirar dos d20 y te quedrás
                            con el más bajo, por lo contrario si buscas las huellas de un animal
                            que por ejemplo, conoces de toda la vida, será más fácil y podrías
                            tener ventaja, tanto una cosa como la otra la decida el DM. </p>
                        <p> Por último y no menos importante, están los críticos y las pífias,
                            un crítico en una tirada d20 es sacar un 20 en el dado, un 20 natural,
                            esto hace que aunque el VD de la prueba sea de 99, se supere
                            directamente sin tener que mirar nada más, una pífia, sacar 1 natural,
                            por su parte hace lo contrario, la prueba queda no superada, aunque 
                            su VD sea de 1, o de 2 y llegues con el modificador.
                        </p>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

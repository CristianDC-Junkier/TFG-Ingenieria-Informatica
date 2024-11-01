<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Diseño_de_PJs</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/comocrearpersonajesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Diseño de Personajes</h2>
            <hr color="black">
            <div class="bloqueDiseñoPersonaje">
                <h3>Elección de raza y clase</h3>
                <div class="infoDiseñoPersonaje">
                    <div>
                        <p>
                            Para empezar a diseñar un personaje debes elegir una
                            raza y una clase, el orden no es importante, pero en recomendación
                            saber que clase vas a utilizar antes de elegir una raza es lo mejor.
                            Muchas razas aportan beneficios que pueden resultar útiles en diferentes
                            características, por ejemplo, los enanos tienen bonificaciones de 
                            resistencia, mientras que los medianos en destreza, ese tipo de cosas
                            puede hacer que tu clase salga beneficiada o no, ya que a una clase
                            sigilosa, véase el picaro, le aportará más la destreza que la resistencia
                            o la fuerza.</p>
                        <p> Tras esta pequeña introducción empezamos con las clases, existen un total
                            de 13 clases completamente oficiales, aunque existen más, no están incluidas
                            al no ser información de la cual se pueda sacar un punto en común por ser
                            hecha por fans.<br>
                            Cada clase tiene a su vez subclases, la subclases se eligen según la clase, 
                            algunos la asumen en el nivel 2, como los Magos, otros la 
                            escogen al nivel 3 como los Luchadores y otros la tienen desde 
                            el principio como los Clérigos. De estas, solo se puede escoger una de ellas,
                            cada una aporta un beneficio sobre la clase en cuestión y sirven, para
                            centrarnos en una dirección, por ejemplo un Mago podría elegir 
                            una subclase centrada en las explosiones o en los encantamientos, un 
                            Luchador en el arco; las espadas o los escudos, y un Clérigo en curar a sus 
                            aliados; molestar a los enemigos, o incluso hacer mucho daño divino.<br><br>
                            Podemos dividir las clases de dos formas, sobre que atributo es su principal,
                            o sobre que tipo de daño hacen:<br>
                            Por atributos: Carisma, Destreza, Sabiduría, Fuerza, Constitución e Inteligencia<br>
                            Por el tipo de daño: Lanzador de Hechizos, Marciales e Híbridas.<br><br>
                            Además de esto las clases definen algunas cosas más como el hecho de como
                            se sube de vida, la mayoría de objetos de inicio o en su defecto, riquezas, 
                            y la posibilidad de tener bonificador en las tiradas de las diferentes 
                            habilidades que existen, como Sigilo, Percepción etc.</p>
                        <p> Una vez explicadas las clases podemos empezar a hablar de las razas, la raza 
                            es mucho más que solo un añadido cosmético, las razas aportan habilidades, mejoras
                            en los atributos, los idiomas, y algunos conjuros, o funciones únicas, además
                            cada una también puede tener sus propias subrazas que diversifican más las 
                            características y dan mucha unicidad a cada personaje, teniendo en cuenta
                            lo explicado arriba, si quieres un personaje más fuerte, deberías mezclar
                            una raza y una clase que se potencien, o si simplemente te da igual, podrías
                            elegir la raza por como se ve, o por una habilidad que te resulte divertida.
                            <br> Cada raza también aporta un tamaño, que será impresindible pasa saber
                            cuanto espacio ocupas o cuanto puedes llevar, y la velocidad de movimiento, que
                            definirá cuanto puedes moverte, en combate, y en menor medida, fuera de este.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th>Clases</th>
                                <th>Atributo Principal</th>
                                <th>Categoría</th>
                            </tr>
                            <tr>
                                <td>Artificiero</td>
                                <td>Inteligencia</td>
                                <td>Híbrida</td>
                            </tr>
                            <tr>
                                <td>Bárbaro</td>
                                <td>Fuerza</td>
                                <td>Marcial</td>
                            </tr>
                            <tr>
                                <td>Bardo</td>
                                <td>Carisma</td>
                                <td>Lanzador de Conjuros</td>
                            </tr>
                            <tr>
                                <td>Brujo</td>
                                <td>Carisma</td>
                                <td>Lanzador de Conjuros</td>
                            </tr> 
                            <tr>
                                <td>Caballero de la Muerte</td>
                                <td>Fuerza y Constitución</td>
                                <td>Híbrida</td>
                            </tr>
                            <tr>
                                <td>Cazador de Sangre</td>
                                <td>Fuerza o Destreza y Sabiduría</td>
                                <td>Híbrida</td>
                            </tr>
                            <tr>
                                <td>Clérigo</td>
                                <td>Sabiduría</td>
                                <td>Lanzador de Conjuros</td>
                            </tr>
                            <tr>
                                <td>Druida</td>
                                <td>Sabiduría</td>
                                <td>Lanzador de Conjuros</td>
                            </tr>
                            <tr>
                                <td>Explorador</td>
                                <td>Destreza y Sabiduría</td>
                                <td>Híbrida</td>
                            </tr>
                            <tr>
                                <td>Hechicero</td>
                                <td>Carisma</td>
                                <td>Lanzador de Conjuros</td>
                            </tr>
                            <tr>
                                <td>Luchador</td>
                                <td>Fuerza o Destreza</td>
                                <td>Marcial</td>
                            </tr>
                            <tr>
                                <td>Mago</td>
                                <td>Inteligencia</td>
                                <td>Lanzador de Conjuros</td>
                            </tr>
                            <tr>
                                <td>Monje</td>
                                <td>Destreza y Sabiduría</td>
                                <td>Marcial</td>
                            </tr>
                            <tr>
                                <td>Paladín</td>
                                <td>Fuerza y Carisma</td>
                                <td>Híbrida</td>
                            </tr>
                            <tr>
                                <td>Pícaro</td>
                                <td>Destreza</td>
                                <td>Marcial</td>
                            </tr>
                            <tr>
                                <td>Pistolero</td>
                                <td>Destreza</td>
                                <td>Marcial</td>
                            </tr>
                        </table>
                        <p style="font-size: 1.5vmin;">
                            Lanzador de Conjuros: Consigue muchos conjuros por nivel y 
                            puede lanzarlos a si gusto, no suele ser muy buen luchador
                            a corta distancia a menos que se especialice en ello.<br>
                            Híbrido: Conoce conjuros pero no suelen ser su fuerte, 
                            la mayoría de conjuros es para mejorar sus posibilidades
                            en el combate o fuera de el.<br>
                            Marcial: No puede hacer conjuros a menos que se especialice en ello 
                            si es posible, donde pasará a ser Híbrido, pero nunca Lanzador.
                        </p>
                    </div>
                </div>
                <h3>Ajustar los atributos</h3>
                <div class="infoDiseñoPersonaje">
                    <div>
                        <p>
                            Cuando creas un personaje tienes que asignarle los puntos de atributo,
                            puedes añadir un total de 27 puntos, que se deben repartir entre los números
                            8 hasta el 15.<br>
                            Otra forma de hacerlo es tirar un dado d20 y asignar ese numero a un atributo,
                            asi con todos, pero no es recomendable, porque pueden salir 
                            personajes muy desequilibrados.<br>
                            De una forma o de otra puntos se colocan antes de los que dan por la
                            raza, esta elección es muy importante, porque de ella dependen los modificadores
                            que son puntos que añadirán o reducirán los números tiradas, tanto en el combate
                            como en la interpretación, por ejemplo, si tu Destreza es de 16, tendrás un 
                            total de +3 de modificador de Destreza, y todas las habilidades o armas
                            que dependan de la destreza también, por ejemplo Sigilo o Acrobacias.</p>
                        <p> La máxima puntuación de forma normal a la  que se puede llegar es de 20 
                            en todos los atributos, y dependiendo de tu clase cada varios niveles, que
                            suelen ser cada cuatro, te permite subir dos puntos en un atributo 
                            o un punto en dos, o si no te interesa ninguna de las dos opciones si tu DM
                            lo permite, puedes elegir una dote.<br><br>
                            Los modificadores suben o bajan cada numero par, es decir 10 es 0 y 11 también
                            pues es impar, pero 12 ya sería +1 al igual que 13 que es impar, y lo mismo en 
                            numeros negativos, siendo -1 con 8 puntos lo mínimo, y +5 con 20 lo máximo si subes
                            de nivel tus personajes.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="3">Costes de Atributos</th>
                            </tr>
                            <tr>
                                <th>Puntuaciones</th>
                                <th>Costes</th>
                                <th>Modificador</th>
                            </tr>
                            <tr>
                                <td>8</td>
                                <td>0</td>
                                <td>-1</td>
                            </tr>
                            <tr>
                                <td>9</td>
                                <td>1</td>
                                <td>-1</td>
                            </tr>
                            <tr>
                                <td>10</td>
                                <td>2</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <td>11</td>
                                <td>3</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <td>12</td>
                                <td>4</td>
                                <td>+1</td>
                            </tr>
                            <tr>
                                <td>13</td>
                                <td>5</td>
                                <td>+1</td>
                            </tr>
                            <tr>
                                <td>14</td>
                                <td>7</td>
                                <td>+2</td>
                            </tr>
                            <tr>
                                <td>15</td>
                                <td>9</td>
                                <td>+2</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <h3>Trasfondo y Alineamiento</h3>
                <div class="infoDiseñoPersonaje">
                    <div>
                        <p>
                            El transfondo es una parte importante de tu personaje, complementa
                            a tu historia personal o van muy de la mano. Te aportan
                            bonificadores en habilidades, equipo o riquezas, idiomas, e incluso una habilidad
                            única como por ejemplo, tener acceso a una red de ladrones a los que puede
                            la elección de trasfondo se debería hacer junto a tu DM, pues en 
                            un mundo donde no existen los piratas,no tendría sentido 
                            que eligieras ese tipo de transfondo, además si no te convence
                            ninguno, junto a tu DM podeis adaptar uno, o crearlo.<br><br>
                            Por otra parte el transfondo puede ayudar a tu DM a colocarte en una posición
                            inicial para juntarte con el grupo, o para definir las misiones iniciales,
                            esto al igual que el alineamiento, que es, como tu personaje se va a 
                            comportar en con los NPC, con los monstruos o con el grupo, ayuda al DM
                            a elegir bien que decisiones debe hacerte tomar, o como deben comportarse los
                            NPC.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="3">Alineamiento</th>
                            </tr>
                            <tr>
                                <td>Legal Bueno</td>
                                <td>Neutral Bueno</td>
                                <td>Cáotico Bueno</td>

                            </tr>
                            <tr>
                                <td>Legal Neutral</td>
                                <td>Neutral Neutral</td>
                                <td>Caótico Neutral</td>
                            </tr>
                            <tr>
                                <td>Legal Malo</td>
                                <td>Neutral Malo</td>
                                <td>Caótico Malo</td>
                            </tr>
                        </table>
                        <br>
                        <p style="font-size: 1.5vmin;">
                            Según el Alineamiento tus acciones:<br>
                            Legal: Siguen las normas establecidas en la sociedad.<br>
                            Cáotico: Se Inventan las normas o no las siguen.<br>
                            Malo: El Objetivo sea hacer daño o ser egoista.<br>
                            Bueno: El Objetivo es ayudar a los demás o solucionar problemas<br>
                            Neutral: Está entre una u otra en mayor o menor medida.
                        </p>
                    </div>
                </div>
                <h3>Personalidad y Apariencia</h3>
                <div class="infoDiseñoPersonaje">
                    <div >
                        <p>
                            La personalidad y apariencia es algo completamente del jugador, puedes
                            hacer cada personaje diferente o todos iguales, lo importante es divertirse,
                            pero hay varias cosas que habría que definir para ayudar al DM y a ti mismo
                            a poder interpretar:<br><br> 
                            Rasgos de personalidad: Tus cosas buenas, ser amabable, sincero etc <br>
                            Ideales: Lo que tu personaje quiere hacer, sus sueños, sus metas, 
                            reglas en las que se basa<br>
                            Vinculos: Con que NPCs, personajes o animales etc te conoces, tu familia,
                            amigos, compañeros de trabajo etc<br>
                            Defectos: Lo malo de ti, decir más de lo que debes, enfadarte rapido, 
                            enamorarte demasiado rapido etc<br>
                            Lengua materna: En principio será común que es equivalente
                            a humano, pero puedes modificar un poco, si quieres tener por ejemplo
                            acento<br> 
                            Edad: tiene que tener sentido con la raza elegida<br>
                            Altura: tiene que tener sentido con el tamaño elegido<br>
                            Peso: Puede tenerse en consideración para pruebas de habilidad o para
                            la cantidad de cosas que puedes llevar<br>
                            Ropa: Que llevas, de que color, de que forma etc<br>
                            Ojos: Color, si te falta alguno, su forma etc<br>
                            Pelo: Que tipo, hasta donde llega, el color etc<br>
                            Cara: Cicatrices, forma de la nariz, los labios, piel, pecas etc </p>
                        <p> Todo estas cosas si bien no son imprescindibles, si es verdad
                            que pueden ser que ayuden al DM a crear la historia, aunque se puede realizar
                            sin tener definido completamente este tipo de cosas.
                        </p>
                    </div>
                </div>
                <h3>Subiendo de Nivel</h3>
                <div class="infoDiseñoPersonaje">
                    <div>
                        <p>
                            Algunas razas consiguen mejoras mientras van escalando de nivel
                            por ejemplo el simic-híbrido puede elegir diferentes habilidades
                            según va escalando, quitando eso, la mayoría de mejoras las dan las clases
                            que van dando poderes según el nivel de la clase, hay una característica
                            importante que los jugadores pueden utilizar, y es multiclasear, es algo
                            complicado para jugadores nuevos, y no es recomendable en la primera partida.</p>
                        <p> Los personajes tienen niveles de personaje y niveles de clase, por ejemplo,
                            puedes tener nivel 3 de personaje y ser Brujo-Luchador, entonces, podrías tener
                            2 niveles de brujo, y 1 de luchador, o 2 de luchador y 1  de brujo.<br>
                            Esto hace que los aunque seas nivel 3, solo tengas los poderes de hasta nivel 2 de
                            la clase que tengas nivel 2 y los de nivel 1 de la clase de nivel 1.</p> 
                        <p> Si cumples con los requisitos de los atributos, puedes realizar una multiclase al 
                            subir de nivel, esto es algo que no se puede deshacer, además nunca podrás
                            subir más de 1 nivel de personaje, es decir, tendras que decidir entre una u 
                            otra, pero nunca subirán las dos con el mismo nivel.<br>
                            Puedes tener más de una clase multiclaseada, por ejemplo 4 niveles y ser, Luchador 1,
                            Mago 1 y Monje 2, pero es algo complicado de llevar, y puede volverse demasiado poderoso,
                            el DM debe decidir si se puede hacer multiclase  y con cuantas.<br><br>
                            Por otra parte, cada clase te da unos poderes y habilidades que pueden no ser unicos,
                            por ejemplo, si eres Luchador y puedes golpear dos veces por turno, aunque seas Pícaro y
                            también puedas por ser Pícaro, esto no se acumula, solo te quedarías con la mejor de las dos.<br><br>
                            Además cuando se sube de nivel si subes en Pícaro tiraras el dado de Picaro (d8) y si
                            subes en Luchador tiraras el dado de Luchador (d10) para sumar tu vida.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="2">Requisitos de Multiclase</th>
                            </tr>
                            <tr>
                                <th>Clase</th>
                                <th>Puntuación mínima de Característica</th>
                            </tr>
                            <tr>
                                <td>Artificiero</td>
                                <td>Inteligencia 13</td>
                            </tr>
                            <tr>
                                <td>Bárbaro</td>
                                <td>Fuerza 13</td>
                            </tr>
                            <tr>
                                <td>Bardo</td>
                                <td>Carisma 13</td>
                            </tr>
                            <tr>
                                <td>Brujo</td>
                                <td>Carisma 13</td>
                            </tr>
                            <tr>
                                <td>Caballero de la Muerte</td>
                                <td>Fuerza 13 y Constitución 13</td>
                            </tr>
                            <tr>
                                <td>Cazador de Sangre</td>
                                <td>Fuerza 13 o Destreza 13</td>
                            </tr><tr>
                                <td>Clérigo</td>
                                <td>Sabiduría 13</td>
                            </tr>
                            <tr>
                                <td>Druida</td>
                                <td>Sabiduría 13</td>
                            </tr>
                            <tr>
                                <td>Explorador</td>
                                <td>Destreza 13 y Sabiduría 13</td>
                            </tr>
                            <tr>
                                <td>Hechicero</td>
                                <td>Carisma 13</td>
                            </tr>
                            <tr>
                                <td>Luchador</td>
                                <td>Fuerza 13 o Destreza 13</td>
                            </tr>
                            <tr>
                                <td>Mago</td>
                                <td>Inteligencia 13</td>
                            </tr>
                            <tr>
                                <td>Monje</td>
                                <td>Destreza 13 y Sabiduría 13</td>
                            </tr>
                            <tr>
                                <td>Paladín</td>
                                <td>Fuerza 13 y Carisma 13</td>
                            </tr>
                            <tr>
                                <td>Pícaro</td>
                                <td>Destreza 13</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Combate</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/combateCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Combate</h2>
            <hr color="black">
            <div class="bloqueCombate">
                <h3>Iniciativa</h3>
                <div class="infoCombate">
                    <div>
                        <p>
                            Lo primero que hay que saber sobre combatir, es quien
                            va el primero, y cuanto dura los turnos de cada personaje.<br>
                            El orden es muy fácil de elegir, se tira un d20 y se le suma el valor
                            de la iniciativa, que es igual al modificador de destreza,
                            siempre que no tengas un dote o mejora que cambie el valor.<br>
                            Si tu tirada coincide con la tirada de un compañero, podeis poneros
                            de acuerdo para ver quien va primero y quien va segundo, si por otra
                            parte coincide con un enemigo, se debe realizar una segunda tirada
                            entre aquellos que hayan salido empatados para considerar quien va
                            antes</p>
                        <p> Una ronda son seis segundos, en una misma ronda ocurren todos
                            los turnos de los personajes, es decir, en los mismos seis segundos
                            tu personaje podría estar saltando, mientras otro ataca y un enemigo 
                            lanza un hechizo, lo único que puede hacer que un turno de otra persona
                            no se juegue, es que en el orden haya hecho que el que vaya antes  
                            incapacite, ya sea con un hechizo o golpe, bajando la vida a 0,
                            o matando a alguien que vaya después.
                            Por esto los hechizos que duran 10 turnos durarán tus 10 turnos,
                            es decir, 60 segundos, no 10 turnos entre tus compañeros, los enemigos y tú.
                        </p>
                    </div>
                    <div>
                        <p style="border:none;">
                            Como DM es complicado llevar los turnos, una forma de hacerlo es
                            utilizando cartas con números y dejandote el último para tí, indicando
                            que es el turno del entorno, rayos; agua; lava; temperaturas frías;
                            cualquier cosa, y así en vez de llevarlo tú puedes simplemente ir diciendo
                            que número es el siguiente.<br><br>
                            Otra forma de hacerlo es por supuesto apuntando el orden y con una ficha
                            una marca o cualquier cosa ir pasando por todos los turnos hasta que vuelves
                            al principio<br><br>
                            Si no te convence ninguna, aquí tienes una aplicación que solo
                            necesita java para funcionar donde puedes ordenar los turnos fácilmente:<br><br>
                            <a href="/TFG/manual/OrdenadorDeTurnos.jar" download="OrdenadorDeTurnos.jar">Descargar aplicación</a>
                        </p>
                    </div>
                </div>
                <h3>Daños</h3>
                <div class="infoCombate">
                    <div>
                        <p>
                            La vida en D&D se recuenta en puntos de golpe, porque para
                            enemigos no-muertos, como zombies, o vampiros, no tendría 
                            sentido que tuviesen puntos de vida, así que se decidió llamarse así.<br>
                            Estos se calculan, en caso de los personajes, depende de una tirada
                            del dado de su clase más el modificador de constitución cada nivel, 
                            menos en el nivel 1, que será si o sí, el máximo de su dado de clase, más
                            el modificador de constitución.<br>
                            En el caso de los NPC, o los enemigos, hay tres formas de calcular sus
                            puntos de golpe totales, utilizando los puntos totales que indica el manual,
                            tirando los dados y sumando el modificador que indica el manual,
                            o simplemente el DM puede decidir viendo estas dos cosas, asignandole el valor
                            que él considere.</p>
                        <p> El daño podemos dividirlo en dos si tenemos en cuenta la forma de como
                            acertar el daño, tenemos aquellos daños, como armas y algunos hechizos
                            que para dar tienes que lanzar un d20 y sumarle el modificador pertinente, para 
                            igualar o superar la CA del objetivo, para estos, si tienes competencia 
                            con tu ataque, es decir, tienes competencia con el arma, o el hechizo, 
                            también se sumará el bonificador de competencia.<br>
                            El otro grupo es el daño el cual son los golpeados los que tienen que
                            tirar un d20 también sumando su bonificador pertinente, contra la salvación
                            del ataque en cuestión, es decir, deberan igualar o superar la salvación, ya sea
                            del conjuro, veneno etc.<br><br>
                            Todos los daños del primer grupo, hechizos o no, pueden realizar crítico, para
                            realizar crítico, debe salir en el d20 un 20, es decir, 20 natural, tras ello
                            se tirarán todos los dados de daño dos veces, es decir, si tu arma hace 2d6 de base
                            harás 4d6 más una vez el modificador, que no se duplica.<br>
                            Por otra parte sacar 1 natural en el d20 hace que el ataque falle instantáneamente</p>
                        <p> En algunos momentos, te merecerá la pena no matar al enemigo, para interrogarlo,
                            utilizarlo de rehén, o cualquier otra cosa, para ello utilizando un ataque cuerpo
                            a cuerpo, puede ser cualquier daño, ya sea con armas o no, puedes decidir noquear
                            a tu objetivo, haciendo que si reduces sus puntos de golpe a 0 quedaría noqueado.</p>
                        <p> A veces tu objetivo será invisible, o tus sentidos como la vista o el oido no servirán
                            para acertar a tu objetivo, o simplemente tienes el brazo de tu arma dañado, o una altura baja
                            podrías tener desventaja, entonces, deberás tirar dos dados d20 y quedarte con el inferior.<br>
                            Por otra parte si es tu enemigo el que sufre esto, tendras ventaja y de la misma forma,
                            lanzarás dos dados y te quedarás con el más alto, los enemigos también podrán tener ventajas
                            o desventajas, pero todo dependerá de la situación y el DM.<br>
                            Por otra parte, cuando estas oculto tendrás ventaja para dar, pero cualquier ataque revelará tu
                            posición, dejando de ganar esa ventaja de daño, además si entras en un combate dando el golpe 
                            de forma oculta, el primer no contará como turno y la tirada de iniciativa comenzará después de tu daño.                 
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="2">Puntos de golpe por Clase</th>
                            </tr>
                            <tr>
                                <th>Clases</th>
                                <th>Dado de puntos de golpe</th>
                            </tr>
                            <tr>
                                <td>Artificiero</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Bárbaro</td>
                                <td>d12</td>
                            </tr>
                            <tr>
                                <td>Bardo</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Brujo</td>
                                <td>d8</td>
                            </tr> 
                            <tr>
                                <td>Caballero de la Muerte</td>
                                <td>d10</td>
                            </tr>
                            <tr>
                                <td>Cazador de Sangre</td>
                                <td>d10</td>
                            </tr>
                            <tr>
                                <td>Clérigo</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Druida</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Explorador</td>
                                <td>d10</td>
                            </tr>
                            <tr>
                                <td>Hechicero</td>
                                <td>d6</td>
                            </tr>
                            <tr>
                                <td>Luchador</td>
                                <td>d10</td>
                            </tr>
                            <tr>
                                <td>Mago</td>
                                <td>d6</td>
                            </tr>
                            <tr>
                                <td>Monje</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Paladín</td>
                                <td>d10</td>
                            </tr>
                            <tr>
                                <td>Pícaro</td>
                                <td>d8</td>
                            </tr>
                            <tr>
                                <td>Pistolero</td>
                                <td>d8</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <h3>Tipos de Daño</h3>
                <div class="infoCombate">
                    <div>
                        <p>
                            En D&D existen varios tipos de daño, los más comunes son aquellos que hacen
                            las armas, que son: contundente, cortante y perforante.<br>
                            Después tenemos los tipos de daño mágicos que son muchos más, un arma
                            encantada, hace el daño del tipo del que fue encantada, o en su defecto
                            daño de fuerza.</p>
                        <p> Podemos hablar de dos formas de combatir, cuerpo a cuerpo o a distancia, los ataques cuerpo
                            a cuerpo, dependen del arma que portes, cada arma tiene la capacidad de hacer daño a una 
                            distancia distinta, hay algunos hechizos que también son cuerpo a cuerpo<br>
                            Por su parte el combate a distancia se divide en utilizando armas, como arcos, cada arma
                            tiene una distancia a la que llega sin problemas y una distancia donde llega con desventaja.<br>
                            Por otro lado, tenemos los hechizos, cada hechizo tiene su propio rango de ataque, y su forma,
                            algunos son en cono, otros a un objetivo, otros en cubo, en circulo, etc, está explicado
                            en cada hechizo.<br>
                            Intentar atacar a distancia a un objetivo que está muy cerca, o cuando tenemos a un objetivo
                            que esta a rango cuerpo a cuerpo nos hace tener desventaja siempre.</p>
                        <p> Una gran parte de hechizos tienen la característica de mantenerse durante el combate, por ejemplo
                            crear una zona de oscuridad, para ello, debes mantener la concentración en ese hechizo, los hechizos
                            indican cuanto dura cada uno y si debes mantener concentración o no, solo puedes mantener una concentración de base, 
                            cuando mantienes concentración, un golpe puede romperla y perder el hechizo, para ello debes tirar 
                            un d20 y superar la mitad daño del golpe que te dieron, o como mínimo superar 10 en la tirada, sin sumar
                            nigún bonificador.</p>
                        <p> Combatir bajo el agua hace que todas las armas a distancia tengan desventaja en rango normal 
                            y fallen siempre a mayor rango, además las armas cuerpo a cuerpo, si no tienes velocidad de nado
                            tendrás desventaja a menos que el arma sea una daga, una jabalina, una espada corta, una lanza o un tridente.
                            también habrá resistencia al fuego de todos los sumergidos, y vulnerabilidad al rayo para todos.</p>
                        <p> Combatir en una montura también es algo normal, subirse a una montura cuesta 15 pies de movimiento,
                            lo mismo que bajarse, si intentan tirarte del caballo, o tu caballo es empujado o desplazado contra su
                            voluntad debes igualar o superar una tirada de salvación CD 10 de destreza con un d20, si tu montura queda
                            noqueada puedes usar tu reacción para desmontar y terminar de pie, o si no, quedarás tumbado a 5 pies
                            de la misma.<br>
                            La montura por su parte, también tiene su turno de iniciativa, en principio compartirá el del jinete,
                            este puede hacer que la montura haga lo que ella quiera o controlar sus acciones, esto solo es posible
                            si la montura fue entrenada para ello. La mayoría de monturas tienen tres acciones posibles, correr, 
                            retirarse o esquivar, si una montura pierde a su jinete, puede volverse inestable o no, según su
                            entrenamiento.<br>
                            Una montura libre actua como un NPC, o enemigo normal, incluso si alguien la monta.<br>
                            Cuando alguien realiza un ataque de oportunidad contra una montura, el atacante puede golpear a la montura
                            o al jinete.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="2">Tipos de daño</th>
                            </tr>
                            <tr>
                                <th>Tipo</th>
                                <th>Ejemplos</th>
                            </tr>
                            <tr>
                                <td>Ácido</td>
                                <td>Daño corrosivo de una sustancia química, el aliento
                                    de un dragón negro, etc</td>
                            </tr>
                            <tr>
                                <td>Contundente</td>
                                <td>Mazas, martillos, caídas, compresión, etc</td>
                            </tr>
                            <tr>
                                <td>Cortante</td>
                                <td>Espadas, hachas, garras, etc</td>
                            </tr>
                            <tr>
                                <td>Frío</td>
                                <td>Nevadas, temperaturas bajas, elementales de hielo,
                                    el aliento de un dragón blanco, etc</td>
                            </tr> 
                            <tr>
                                <td>Fuego</td>
                                <td>Lava, fuego, Demonios, elementales de fuego, 
                                    el aliento de un dragón rojo, etc</td>
                            </tr>
                            <tr>
                                <td>Fuerza</td>
                                <td>Energía mágica pura en forma de daño</td>
                            </tr>
                            <tr>
                                <td>Necrótico</td>
                                <td>Mordidas de zombie, ataques de vampiros, maldiciones,
                                    fantasmas, etc</td>
                            </tr>
                            <tr>
                                <td>Perforante</td>
                                <td>Lanzas, picas, mordiscos, trampas, etc</td>
                            </tr>
                            <tr>
                                <td>Psíquico</td>
                                <td>Ataques hacia el cerebro, utilizando miedos del oponente,
                                    daños psicológicos, etc</td>
                            </tr>
                            <tr>
                                <td>Radiante</td>
                                <td>Daño de los ángeles, los poderes divinos, luz intensa, etc</td>
                            </tr>
                            <tr>
                                <td>Relámpago</td>
                                <td>Rayos, electricidad, el aliento de un dragón azul, etc</td>
                            </tr>
                            <tr>
                                <td>Sónico/Trueno</td>
                                <td>Ataques hacia el oido, explosiones de ruido, empujones violentos, etc</td>
                            </tr>
                            <tr>
                                <td>Veneno</td>
                                <td>Picotazos venenosos, gas tóxico, aliento de un dragón de veneno, etc</td>
                            </tr>
                        </table>
                        <br>
                        <p>
                            La mayoría de criaturas resisten de la misma forma todos los tipos de daño, pero
                            algunas como los elementales de fuego, tienen resistencias o inmunidades especificas.<br>
                            La resistencia a un elemento hace que todo el daño que te hacen de ese elemento
                            se reduzca a la mitad. Por otra parte la inmunidad hace que no te afecte en absoluto<br>
                            La vulnerabilidad por su parte, hace que tu daño se duplique contra la criatura.
                        </p>
                    </div>
                </div>
                <h3>Curación y Muerte</h3>
                <div class="infoCombate">
                    <div>
                        <p>
                            Hay varias formas de curar a un personaje herido, utilizando un kit de 
                            primeros auxilios y una prueba de medicina es una de ellas, por otra parte
                            también existen los hechizos de curación, estos siempre curan como mucho
                            hasta el total de los puntos de golpe, es decir, si curas 10 puntos de golpe
                            a un personaje que solo le faltan 8 puntos, esos 2 restantes se perderán.<br><br>
                            Por otra parte existen los descansos cortos y largos, un descanso largo restaura
                            todos los puntos de golpe, hechizos y habilidades, y se realizan al dormir las horas
                            necesarias, el descanso corto, es un parón de los personajes de media hora o hora, donde
                            cada personaje puede usar sus dados de golpe, que dependeran de la clase y el nivel
                            para restaurar su vida, y dependiendo de la clase recuperaran hechizos y habilidades 
                            si estas lo indican así.</p>
                        <p> Si te quedas a 0 puntos de golpe, pueden pasar dos cosas, si el daño que te han realizado
                            supera tus puntos de golpe totales, es decir, si tienes 7 puntos de golpe totales y 
                            2 actualmente, si te realizan 9 de daño, te habrían realizado 2 para dejarte a 0 y los
                            suficientes para igualar tu puntos de golpe, ocurriría que tu personaje habría muerto
                            un personaje muerto no puede recuperar puntos de golpe, a menos que se utilice un hechizo
                            o un objeto que lo reviva, si esto no pasa, el jugador deberá crear otro personaje.</p>
                        <p> Por otro lado, si esto no ocurre, tu personaje caería noqueado y a menos que sea estabilizado
                            o se le recupere con un hechizo o poción de vida puntos de golpe comenzaría la ronda de 
                            tiradas de salvación de muerte.<br><br>
                            En cada turno a partir de estar noqueado, deberás lanzar un d20, por cada 10 o más que saques
                            conseguirás una marca de exito, si sacas de menos de 9, tendrías una marca de fallo,
                            con un 1 tendrías 2 marcas de fallo, y con un 20, recuperas 1 punto de vida directamente.<br>
                            Si llegas a 3 fallos, el personaje muere, y si llega a 3 éxitos el personaje se queda estabilizado
                            pero inconsciente, si por algún casual te dan un golpe mientras estás en este estado, ganas una
                            marca de fallo.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="4">Pociones de vida</th>
                            </tr>
                            <tr>
                                <th>Poción</th>
                                <th>Curación</th>
                                <th>Rareza</th>
                                <th>Precio</th>
                            </tr>
                            <tr>
                                <td>Poción de Curación</td>
                                <td>2d4+2</td>
                                <td>Común</td>
                                <td>50 po</td>
                            </tr>
                            <tr>
                                <td>Poción de Curación mayor</td>
                                <td>4d4+4</td>
                                <td>Poco Común</td>
                                <td>100 po</td>
                            </tr>
                            <tr>
                                <td>Poción de Curación superior</td>
                                <td>8d4+8</td>
                                <td>Rara</td>
                                <td>500 po</td>
                            </tr>
                            <tr>
                                <td>Poción de Curación suprema</td>
                                <td>10d4+20</td>
                                <td>Muy Rara</td>
                                <td>5000 po</td>
                            </tr>
                        </table>
                        <br>
                        <p>  
                            Puedes tomar la mitad de la poción de vida, simplemente divide entre 2
                            el modificador, y utiliza la mitad de los dados cada vez, usar las pociones
                            gasta una acción en combate, puedes darle una poción a alguien inconsciente.</p>
                        <p> A veces los personajes podrán conseguir puntos de golpe temporales,
                            estos funcionan como escudos, es decir, se colocan por encima
                            de los puntos de golpe, si recibes daño primero perderas los puntos
                            pertinentes en el escudo, y luego los puntos de golpe si sobra daño.
                            Si no te hacen suficiente daño para quitarte todos los puntos de golpe
                            temporales, estos sobrantes se mantendrían hasta que terminase la duración
                            del conjuro, poción, o lo que fuese que los proporcionó.
                        </p>
                    </div>
                </div>
                <h3>Movimiento y Posición</h3>
                <div class="infoCombate">
                    <div>
                        <p>
                            Puedes moverte solo una cantidad de tu movimiento, no tienes porque gastarlo todo.
                            También puedes moverte la mitad de tu movimiento, realizar una acción
                            y luego moverte lo que falta o solo una parte, y realizar la acción adicional.<br>
                            Pasar por algunos sitios el terreno será difícil, como matorrales y por cada pie
                            que te muevas te costará 2 pies.<br>
                            Puedes tumbarte, haciendo que sea más difíciles darte golpes a distancia,
                            si gateas, te mueves con 2 pies de velocidad por pie, levantarte si cuesta la mitad
                            de tu velocidad.</p>
                        <p> Interactuar con algunos objetos, como abrir una puerta, desenvainar una espada, 
                            sacar una poción, no cuesta acción ni movimiento. </p>
                        <p> Cuando te mueves cerca del rango de ataque de un enemigo este puede realizar un
                            golpe contra tí, como reacción, a menos que utilices como acción, retirada.</p>
                        <p> En algunos puntos del terreno pueden haber sitios donde cubrirse, llamados 
                            en D&D coberturas, existen en resumen 3 tipos, corbertura media que da 2 de CA, 
                            cobertura 3/4 da 5 de CA, tanto una como la otra también de los puntos a la 
                            salvación de destreza y total, que no puede ser objetivo de ataque o conjuro
                            salvo si el conjuro es de area.
                        </p>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th colspan="4">Pociones de vida</th>
                            </tr>
                            <tr>
                                <th>Tamaño</th>
                                <th>Espacio</th>
                            </tr>
                            <tr>
                                <td>Diminuto</td>
                                <td>2,5 por 2,5 pies</td>
                            </tr>
                            <tr>
                                <td>Pequeño</td>
                                <td>5 por 5 pies</td>
                            </tr>
                            <tr>
                                <td>Mediano</td>
                                <td>5 por 5 pies</td>
                            </tr>
                            <tr>
                                <td>Grande</td>
                                <td>10 por 10 pies</td>
                            </tr>
                            <tr>
                                <td>Enorme</td>
                                <td>15 por 15 pies</td>
                            </tr>
                            <tr>
                                <td>Gargantuesco</td>
                                <td>20 por 20 pies o más</td>
                            </tr>
                        </table>
                        <br>
                        <p>
                            Cada criatura ocupa una cantidad de espacio diferente,
                            si tu tamaño es dos tallas mayor o menor puedes pasar por
                            el espacio ocupado por otra criatura como terreno difícil.
                        </p>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

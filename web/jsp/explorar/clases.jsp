<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Clases</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/clasesCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/jsp/menuNav.jsp" />
        <main> 
            <div>
                <h6 id="Arriba" class = "BusquedaClase">
                    <a href="#Aa" id="A">&nbsp;&nbsp;A</a>
                    <a href="#B">&nbsp;&nbsp;B</a>
                    <a href="#C">&nbsp;&nbsp;C</a>
                    <a href="#D">&nbsp;&nbsp;D</a>
                    <a href="#E">&nbsp;&nbsp;E</a>
                    <a href="#H">&nbsp;&nbsp;H</a>
                    <a href="#L">&nbsp;&nbsp;L</a>
                    <a href="#M">&nbsp;&nbsp;M</a>
                    <a href="#Pp" id="P">&nbsp;&nbsp;P&nbsp;</a>
                </h6>
            </div>
            <div class="ResumenClases">
                <h2 class="Titulos">Clases</h2>
                <hr color="black">
                <div class="ListaClases">
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="Aa">Artificiero</h5>
                            <li> 
                                <img src="/TFG/img/clases/artificiero.jfif"/>
                                <p> Los artificieros son maestros de la invención y la magia basada 
                                    en la tecnología. Son capaces de construir y modificar objetos mágicos, 
                                    así como de crear dispositivos que imitan los hechizos de otras clases. 
                                    Algunos pueden hacer torres que ayudan al equipo, disparan o exparcen 
                                    fuegos en masa, otros pueden hacer armas poderosas, otros armaduras
                                    y algunos pueden invocar aliados mecánicos.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="B">Bárbaro</h5>
                            <li> 
                                <img src="/TFG/img/clases/bárbaro.jfif"/>
                                <p> Los bárbaros son guerreros feroces que canalizan su 
                                    ira para obtener un poder de combate increíble. 
                                    Son conocidos por su resistencia y capacidad para 
                                    entrar en un estado de frenesí que los hace más 
                                    letales en combate cuerpo a cuerpo, se envuelven de ira
                                    para atacar furiosamente a sus enemigos y reducir el daño recibido.
                                    Algunos pueden hacerse parecer animales otros pueden usar mágias antiguas
                                    y otros simplemente dependen de su sed de sangre.
                                </p>
                            </li>
                        </div>
                        <div class="ResumenClase">
                            <h5>Bardo</h5>
                            <li>              
                                <img src="/TFG/img/img/clases/bardo.jfif"/>
                                <p> Los bardos son artistas versátiles y manipuladores de la magia. 
                                    Pueden inspirar a sus aliados con su música, contar historias que 
                                    alteran la realidad y lanzar hechizos de apoyo para ayudar a su 
                                    grupo en diversas situaciones, incluso poner la suerte a su favor.
                                    Algunos pueden crear grandes ilusiones, otros encantan con su magia
                                    otros defienden a los aliados, y otros emulan a los magos.
                                </p>
                            </li>
                        </div>
                        <div class="ResumenClase">
                            <h5>Brujo</h5>
                            <li>        
                                <img src="/TFG/img/clases/brujo.jfif"/>
                                <p> Los brujos obtienen su poder mágico a través de 
                                    pactos con seres poderosos, como entidades extraplanarias 
                                    o seres místicos. A diferencia de los magos, los brujos 
                                    tienen un número muy limitado de hechizos, pero pueden lanzarlos 
                                    muchisimas mas veces. Algunos invocan monstruos demoniacos, otros
                                    lanzan hechizos de empujan a los enemigos, otros no tienen
                                    la necesidad de respirar e infunden miedo a sus enemigos.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="C">Caballero de la Muerte</h5>
                            <li>      
                                <img src="/TFG/img/clases/caballeromuerte.jfif"/>
                                <p>Los caballeros de la muerte son expertos en el combate cuerpo a cuerpo
                                    que pueden invocar esbirros no muertos para que les ayuden en la batalla,
                                    son capaces de utilizar las energias de la oscuridad para crear pestes que
                                    matan a sus enemigos y absorven su vida vital. Algunos focalizan su poder
                                    en relentizar y helar a sus enemigos, otros usan la sangre de los muertos
                                    para restablecer sus energías y otros potencian sus plagas para destruir 
                                    desde dentro a sus rivales.
                                </p>
                            </li>
                        </div>
                        <div class="ResumenClase">
                            <h5>Cazador de Sangre</h5>
                            <li> 
                                <img src="/TFG/img/clases/cazadorsangre.jfif"/>
                                <p> Los cazadores de sangre son rastreadores letales y cazadores expertos, 
                                    con una conexión innata con la bestia interior que les otorga poderes 
                                    sobrenaturales. Son conocidos por su capacidad para rastrear a sus presas, 
                                    así como por sus habilidades mejoradas de combate y supervivencia.
                                    Algunos pueden utilizar viales de los monstruos que han cazado para
                                    canalizar sus poderes, otros son expertos caza espiritus, y 
                                    otros utilizan sus poderes de la noche para transformarse en bestias.
                                </p>
                            </li>
                        </div>
                        <div class="ResumenClase">
                            <h5>Clérigo</h5>
                            <li> 
                                <img src="/TFG/img/clases/clérigo.jfif"/>
                                <p> Los clérigos son caballeros divinos que canalizan el poder de su dios 
                                    para realizar milagros y lanzar hechizos sagrados. Pueden actuar como 
                                    sanadores, guerreros sagrados o manipuladores de la energía divina según 
                                    su deidad. Algunos se centran en curar a todos sus aliados, otros lanzan
                                    descargas de daño divino, otros predican la paz con sus conjuros, y otros
                                    toman dioses violentos para aumentar sus fuerzas.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="D">Druida</h5>
                            <li> 
                                <img src="/TFG/img/clases/druida.jfif"/>
                                <p> Los druidas son guardianes de la naturaleza y tienen una conexión 
                                    profunda con el mundo natural. Pueden transformarse en animales, 
                                    controlar elementos naturales y lanzar hechizos relacionados con 
                                    la naturaleza y la vida silvestre. Algunos pueden realizar conjuros
                                    de la luna y el sol, otros invocan aliados animales, otros canalizan su
                                    poder transformandose en poderosos elementales, otros canalizan la 
                                    destrucción de la naturaleza.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="E">Explorador</h5>
                            <li> 
                                <img src="/TFG/img/clases/explorador.jfif"/>
                                <p> Los exploradores son expertos en sobrevivir en entornos 
                                    hostiles y explorar tierras desconocidas. Son hábiles 
                                    rastreadores y arqueros expertos, capaces de adaptarse 
                                    a diversas situaciones y entornos naturales. Algunos son
                                    capaces de invocar animales poderosos, otros se hacen 
                                    invisibles en la oscuridad, otros son expertos cazadores
                                    de monstruos solitarios.
                                </p>
                            </li>
                        </div>
                    </ul>

                    <ul>
                        <div class="ResumenClase">
                            <h5 id="H">Hechizero</h5>
                            <li> 
                                <img src="/TFG/img/clases/hechizero.jfif"/>
                                <p> Los hechiceros son lanzadores de conjuros que obtienen su 
                                    poder mágico de su sangre o de un acontecimiento sobrenatural. 
                                    Tienen una afinidad innata para manipular la magia y pueden 
                                    lanzar hechizos poderosos de manera espontánea.
                                    Algunos tienen antepasados dragones que le dan su fuerza, otros
                                    la consiguen de la propia naturaleza, otros de las tormentas
                                    y otros de seres mágicos.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="L">Luchador</h5>
                            <li> 
                                <img src="/TFG/img/clases/luchador.jfif"/>
                                <p> Los luchadores son expertos en el combate cuerpo a cuerpo y 
                                    el manejo de armas. Son maestros en el uso de una variedad de 
                                    armas y armaduras incluso algunos pueden hacer magia, 
                                    lo que los convierte en la opción ideal para 
                                    todas las personas que comiencen a jugar.
                                    Algunos pueden considerar el arco su mejor arma, otros 
                                    pueden pegar a puños, otros utilizan montura, otros
                                    magia, y todos pueden controlar todas las armas del juego.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="M">Mago</h5>
                            <li> 
                                <img src="/TFG/img/clases/mago.jfif"/>
                                <p> Los magos son usuarios expertos de la magia arcana, capaces 
                                    de lanzar una amplia gama de hechizos ofensivos, defensivos y 
                                    de utilidad. A menudo son intelectuales curiosos que buscan el 
                                    conocimiento místico y el poder mágico. Algunas escuelas de 
                                    magia se fundamentan en destruir a muchos objetivos, otros
                                    en encantarlos, otros en levantar muertos vivientes y otros
                                    simplemente en utilizar sus hechizos para mejorar sus aptitudes
                                    físicas.</p>
                            </li>
                        </div>
                        <div class="ResumenClase">                                
                            <h5>Monje</h5>
                            <li> 
                                <img src="/TFG/img/clases/monje.jfif"/>
                                <p> Los monjes son combatientes maestros del cuerpo y la mente. 
                                    Son expertos en artes marciales y disciplinas espirituales 
                                    que les permiten realizar hazañas sobrehumanas, como correr 
                                    por paredes o caer desde grandes alturas sin sufrir daño.
                                    Algunos monjes han masterizado la capacidad de hacer daño con
                                    sus manos, otros son combatientes borrachos y otros invocan el
                                    poder de los elementos, los más atrevidos se fusionan con las
                                    sombras, y los más valientes curan a sus aliados.
                                </p>
                            </li>
                        </div>
                    </ul>
                    <ul>
                        <div class="ResumenClase">
                            <h5 id="Pp">Paladin</h5>
                            <li> 
                                <img src="/TFG/img/clases/paladin.jfif"/>
                                <p> Los paladines son guerreros sagrados comprometidos con la justicia 
                                    y la protección de los débiles. Poseen habilidades de combate 
                                    sobresalientes y pueden canalizar energía divina para sanar a los 
                                    heridos y castigar a los malvados. Algunos son capaces de 
                                    destruir seres malvados de un solo golpe, otros son capaces de resistir
                                    muchisimo daño enemigo, otros curan como si el mejor sanador se tratara
                                    y los peores son capaces de controlar muertos vivientes.</p>
                            </li>
                        </div>
                        <div class="ResumenClase">                                
                            <h5>Picaro</h5>
                            <li> 
                                <img src="/TFG/img/clases/pícaro.jfif"/>
                                <p>Los pícaros son maestros del sigilo y la astucia, 
                                    hábiles en el arte del engaño y el robo. Se especializan 
                                    en emboscar a sus enemigos, desarmar trampas y realizar actos de sigilo. 
                                    además son carismáticos e inteligentes, permitiendoles estar
                                    al tanto de todo. Algunos son asesinos seriales que actuan junto
                                    a venenos, otros roban todo lo que ven, otros son capaces de organizar
                                    planes maestros y otros son combatientes especializados en golpear
                                    y escapar.
                                </p>
                            </li>
                        </div>
                        <div class="ResumenClase">
                            <h5>Pistolero</h5>
                            <li>      
                                <img src="/TFG/img/clases/pistolero.jfif"/>
                                <p>Los pistoleros suelen ser hábiles en la fabricación y 
                                    el mantenimiento de sus armas, y son conocidos por su 
                                    habilidad para realizar disparos precisos y letales, 
                                    a menudo con un énfasis en la velocidad y la agilidad 
                                    en el campo de batalla. Algunos son capaces de lanzar
                                    bombas, otros atacan desde distancias espectaculares
                                    y otros realizan daño mixto intercalando la espada y la
                                    pistola.
                                </p>
                            </li>
                        </div>
                    </ul>
                </div>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
            <a href="#Arriba" id="A">Subir Arriba</a>
        </footer>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

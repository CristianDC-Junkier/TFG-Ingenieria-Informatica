<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Reglas\Turnos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/reglas/turnosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Turnos</h2>
            <hr color="black">
            <div class="contenedorTurnos">
                <div class="titulosTurnos">
                    Desplazamiento <span>limitado por la velocidad de movimiento</span>
                </div>
                <div class="contenidoTurnos">
                    <div class="subtitulosTurnos">
                        Puedes desplazarte antes o después de realizar una acción, e intercalar movimiento-acción-movimiento.
                    </div>
                    <div class="opcionesTurnos">
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(1)">        
                                <div class="tituloOpcion">Moverse</div>       
                                <div class="descripcionOpcion">Coste: 5 pies por 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro1" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Moverse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(1)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 5 pies por cada 5 pies</p>
                                    <p class="textoRecuadro">Si posees más de una velocidad, nado, vuelo, escalada,
                                        puedes cambiar entre una y otra durante tu movimiento.</p>
                                    <p class="textoRecuadro">Puedes moverte atravesando el espacio ocupado por 
                                        otra criatura no hostil.</p>
                                    <p class="textoRecuadro">Puedes moverte atravesando el espacio ocupado por 
                                        otra criatura hostil si es dos tallas más grande o más pequeño que tú.</p>
                                    <p class="textoRecuadro">Atravesar a otra criatura es terreno dificil.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">     
                            <div class="contendorOpcion" onclick="mostrarRecuadro(2)">        
                                <div class="tituloOpcion">Escalar</div>        
                                <div class="descripcionOpcion">Coste: 10 pies por 5 pies</div>
                            </div>
                            <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Escalar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(2)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 10 pies por cada 5 pies</p>
                                    <p class="textoRecuadro">El DM puede pedir una prueba de Fuerza (Atletismo) 
                                        si la subida es difícil.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(3)">        
                                <div class="tituloOpcion">Nadar</div>       
                                <div class="descripcionOpcion">Coste: 5 pies por 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro3" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Nadar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(3)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 10 pies por cada 5 pies</p>
                                    <p class="textoRecuadro">El DM puede pedir una prueba una prueba de Fuerza (Atletismo) 
                                        si es difícil nadar.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(4)">        
                                <div class="tituloOpcion">Tumbarse</div>       
                                <div class="descripcionOpcion">Coste: 0 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro4" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Tumbarse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(4)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 0 pies</p>
                                    <p class="textoRecuadro">Para moverte mientras estas tumbado, debes arrastrate o 
                                        usar magia de teletrasporte.</p>
                                    <p class="textoRecuadro">Cuando te tumbas quedas con el estado derribado.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(5)">        
                                <div class="tituloOpcion">Arrastrarse</div>       
                                <div class="descripcionOpcion">Coste: 10 pies por 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro5" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Arrastrarse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(5)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 10 pies por cada 5 pies</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(6)">        
                                <div class="tituloOpcion">Levantarse</div>       
                                <div class="descripcionOpcion">Coste: mitad de tu velocidad total</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro6" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Levantarse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(6)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: mitad de tu velocidad total</p>
                                    <p class="textoRecuadro">No puedes levantarte si no te queda la mitad de tu movimiento 
                                        o si tu velocidad es 0 por algún estado o hechizo.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(7)">        
                                <div class="tituloOpcion">Terreno Difícil</div>       
                                <div class="descripcionOpcion">Coste: 10 pies por cada 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro7" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Terreno Difícil
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(7)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 10 pies por cada 5 pies</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(8)">        
                                <div class="tituloOpcion">Terreno Muy Difícil</div>       
                                <div class="descripcionOpcion">Coste: 20 pies por cada 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro8" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Terreno Muy Difícil
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(8)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 20 pies por cada 5 pies</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(9)">        
                                <div class="tituloOpcion">Salto Vertical</div>       
                                <div class="descripcionOpcion">Coste: 5 pies por cada 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro9" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Salto Vertical
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(9)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 5 pies por cada 5 pies</p>
                                    <p class="textoRecuadro">Saltas horizontalmente una cantidad de pies igual a 3 + tu modificador de Fuerza 
                                        pero debes moverte al menos 10 pies a pie antes del salto.</p>
                                    <p class="textoRecuadro">Si no recorrers 10 pies, solo saltarás la mitad de la distancia</p>
                                    <p class="textoRecuadro">El DM puede permitirte hacer una prueba de Fuerza (Atletismo) 
                                        para saltar más alto de lo que normalmente puedes.</p> 
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(10)">        
                                <div class="tituloOpcion">Salto Horizontal</div>       
                                <div class="descripcionOpcion">Coste: 5 pies por cada 5 pies</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro10" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Salto Horizontal
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(10)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: 5 pies por cada 5 pies</p>
                                    <p class="textoRecuadro">Saltas una cantidad de pies hasta tu puntaje de fuerza pero debes moverte 
                                        al menos 10 pies a pie antes del salto</p>
                                    <p class="textoRecuadro">Si no recorrers 10 pies, solo saltarás la mitad de la distancia</p>
                                    <p class="textoRecuadro">El DM puede permitirte hacer una prueba de Fuerza (Atletismo) 
                                        para saltar más alto de lo que normalmente puedes.</p>
                                    <p class="textoRecuadro">El DM puede pedirte una prueba de Fuerza (Atletismo) CD 10 para librar 
                                        un obstáculo bajo (no más alto que un cuarto de la distancia del salto). 
                                        Pegas contra el obstáculo en una prueba fallida.</p> 
                                    <p class="textoRecuadro">El DM puede pedirte prueba de Destreza (Acrobacia) CD 10 para 
                                        aterrizar de pie en terrenos difíciles. ganas el estado derribado si fallas.</p> 
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(11)">        
                                <div class="tituloOpcion">Desplazar criatura agarrada</div>       
                                <div class="descripcionOpcion">Coste: velocidad reducida a la mitad</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro11" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Desplazar criatura agarrada
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(11)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Coste: velocidad reducida a la mitad</p>
                                    <p class="textoRecuadro">Puedes arrastrar o desplazar a la criatura agarrada, pero tu velocidad se 
                                        reducirá a la mitad a menos que la presa sea dos o más categorías de tamaño 
                                        por debajo de la tuya.</p> 
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(12)">        
                                <div class="tituloOpcion">Improvisar</div>       
                                <div class="descripcionOpcion">Cualquier cosa</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro12" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Improvisar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(12)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Cualquier cosa</p>
                                    <p class="textoRecuadro">Describe un tipo de movimiento que no se detalla en ninguna otra 
                                        parte de las reglas, el DM te dirá si es posible y qué tipo de tirada necesitas hacer, 
                                        si el lo ve necesario, para determinar si lo consigues o no</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorTurnos">
                <div class="titulosTurnos">
                    Acciones <span>1 por Turno</span>
                </div>
                <div class="contenidoTurnos">
                    <div class="subtitulosTurnos">
                        Puedes interaccionar con algún objeto o característica del entorno sin usar una acción,
                        además de soltar un par de frases.
                    </div>
                    <div class="opcionesTurnos">
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(13)">        
                                <div class="tituloOpcion">Atacar</div>       
                                <div class="descripcionOpcion">Ataque cuerpo a cuerpo o a distancia</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro13" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Atacar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(13)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Realiza con tu arma un ataque cuerpo a cuerpo o a distancia</p>
                                    <p class="textoRecuadro">La habilidad Ataque Adicional del luchador entre otras, te da la posibilidad de 
                                        realizar más de un ataque en una misma acción. Cada uno de estos ataques es una tirada a parte 
                                        y puede ser a diferentes criaturas, además, puedes moverte entre estos ataques.</p>
                                    <p class="textoRecuadro">Tienes ventaja en el ataque si golpeas contra objetivos cegados, 
                                        paralizados, petrificados, apresados, aturdidos, inconscientes , derribados (cuerpo a cuerpo) o 
                                        si realizas ataques invisible o sin que te vean.</p>
                                    <p class="textoRecuadro">Tienes desventaja en el ataque si golpeas enemigos invisibles u ocultos,
                                        o derribados (a distancia), si estás cegado, asustado, envenenado o apresado.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">     
                            <div class="contendorOpcion" onclick="mostrarRecuadro(14)">        
                                <div class="tituloOpcion">Desarmar</div>        
                                <div class="descripcionOpcion">Ataque especial</div>
                            </div>
                            <div class="opcionRecuadro" id="recuadro14" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Desarmar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(14)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Intentas desarmar a una criatura</p>
                                    <p class="textoRecuadro">Realiza una tirada de ataque enfrentada por la Fuerza (Atletismo) 
                                        o la Destreza (Acrobacia) del oponente, según el decida.</p>
                                    <p class="textoRecuadro">El atacante tiene desventaja en la tirada de ataque si el defensor 
                                        está sosteniendo el objeto con dos o más manos.</p>
                                    <p class="textoRecuadro">El defensor tiene ventaja en la tirada de habilidad si es más grande
                                        que el atacante o desventaja si es más pequeño.</p>

                                </div>
                            </div>
                        </div>
                        <div class="opcion">     
                            <div class="contendorOpcion" onclick="mostrarRecuadro(15)">        
                                <div class="tituloOpcion">Agarrar</div>        
                                <div class="descripcionOpcion">Ataque especial</div>
                            </div>
                            <div class="opcionRecuadro" id="recuadro15" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Agarrar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(15)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Intentas agarrar a una criatura</p>
                                    <p class="textoRecuadro">Puedes usar la acción Atacar para realizar un agarre. 
                                        Si eres capaz de realizar múltiples ataques con la acción Atacar, 
                                        este ataque reemplaza solo a uno de ellos.</p>
                                    <p class="textoRecuadro">El objetivo del agarre debe estar dentro del alcance 
                                        y tener un tamaño de menos de dos categorías por encima del apresador.</p>
                                    <p class="textoRecuadro">Es necesaria una mano libre con la que sujetar al objetivo 
                                        mediante una prueba de Fuerza (Atletismo) enfrentada a una prueba de Fuerza 
                                        (Atletismo) o Destreza (Acrobacia) del objetivo, según elija.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(16)">        
                                <div class="tituloOpcion">Escapar de un agarre</div>       
                                <div class="descripcionOpcion">Perder el estado apresado</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro16" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Escapar de un agarre
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(16)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Escapar de un agarre, perdiendo el estado apresado</p>
                                    <p class="textoRecuadro">Debes tener éxito en una prueba de Fuerza (Atletismo) 
                                        o Destreza (Acrobacia), lo que tu prefieras, enfrentado por la Fuerza (Atletismo) 
                                        de quien te apresa.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(17)">        
                                <div class="tituloOpcion">Empujar</div>       
                                <div class="descripcionOpcion">Ataque especial</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro17" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Empujar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(17)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Empujar a una criatura, para derribarla o alejarla</p>
                                    <p class="textoRecuadro">Puedes usar la acción Atacar para empujar. 
                                        Si eres capaz de realizar múltiples ataques con la acción Atacar, 
                                        este ataque reemplaza solo a uno de ellos.</p>
                                    <p class="textoRecuadro">El objetivo del empuje debe estar dentro del alcance 
                                        y tener un tamaño de menos de dos categorías por encima del apresador.</p>
                                    <p class="textoRecuadro">Se realiza  prueba de Fuerza (Atletismo) enfrentada a una 
                                        prueba de Fuerza (Atletismo) o Destreza (Acrobacia) del objetivo, según elija.</p>
                                    <p class="textoRecuadro">Si tiene éxito, derribas al objetivo o lo 
                                        empujas 5 pies lejos de ti, según decida el que empuja.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(18)">        
                                <div class="tituloOpcion">Lanzar un hechizo</div>       
                                <div class="descripcionOpcion">Hechizos de 1 acción</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro18" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Lanzar un hechizo
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(18)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Lanza un hechizo con un tiempo de lanzamiento de 1 acción</p>
                                    <p class="textoRecuadro">Puedes usar la acción Atacar para empujar. 
                                        Si eres capaz de realizar múltiples ataques con la acción Atacar, 
                                        este ataque reemplaza solo a uno de ellos.</p>
                                    <p class="textoRecuadro">El objetivo de un hechizo debe estar dentro del rango. 
                                        además si es de un solo objetivo debes tener un camino visible hacia él</p>
                                    <p class="textoRecuadro">Los hechizos con componentes materiales no consumen 
                                        el material a menos que se indique explícitamente. Si el componente
                                        no tiene valor indicado, se puede encontrar en una bolsa de componentes.</p>
                                    <p class="textoRecuadro">Algunos hechizos requieren que mantengas la concentración para mantenerlo. 
                                        Pierdes la concentración si lanzas otro hechizo que requiere concentración o
                                        cuando estás incapacitado. Si te inflingen daño, debes hacer una tirada de salvación 
                                        de Constitución para mantener tu concentración. La CD es igual como mínimo a 10 o la mitad 
                                        del daño que sufriste.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(19)">        
                                <div class="tituloOpcion">Correr</div>       
                                <div class="descripcionOpcion">Aumentar tu velocidad al doble</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro19" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Correr
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(19)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Obtienes el doble de pies de desplazamiento en el turno</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(20)">        
                                <div class="tituloOpcion">Retirarse</div>       
                                <div class="descripcionOpcion">Para que no te ataquen huyendo</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro20" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Retirarse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(20)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Tu desplazamiento no provoca ataques de 
                                        oportunidad durante el turno</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(21)">        
                                <div class="tituloOpcion">Ocultarse</div>       
                                <div class="descripcionOpcion">Ganar ventaja en el combate</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro21" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Ocultarse
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(21)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro"> Intentar esconderse de los enemigos</p>
                                    <p class="textoRecuadro">Debes tener una cobertura total, estar en un área muy oscura, 
                                        ser invisible o bloquear la visión del enemigo.</p>
                                    <p class="textoRecuadro">Hacer ruido como gritar, tirar algo o lanzar un objeto, te hará visible.</p>
                                    <p class="textoRecuadro">Realiza una prueba de Destreza (Sigilo) y anota el resultado. 
                                        Hasta que dejes de ocultarte, el total de esa tirada será enfrentado 
                                        por una prueba de Sabiduría (Percepción) de cualquier criatura que intente encontrarte.</p>
                                    <p class="textoRecuadro">Si la Percepción pasiva del enemigo es mayor a tu  tirada de Sigilo, tu intento
                                        de ocultación se convierte en un fallo directamente.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(22)">        
                                <div class="tituloOpcion">Esquivar</div>       
                                <div class="descripcionOpcion">Confiere ventaja frente a los golpes</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro22" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Esquivar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(22)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Te concentras por completo en esquivar ataques</p>
                                    <p class="textoRecuadro">Hasta el comienzo del siguiente turno, si no está incapacitado
                                        ni inmovilizado, cualquier tirada de ataque en contra tiene desventaja  
                                        y las tiradas salvación de Destreza del personaje
                                        tienen ventaja, para ello debes poder ver al atacante.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(23)">        
                                <div class="tituloOpcion">Ayudar</div>       
                                <div class="descripcionOpcion">Creas una ventana de oportunidad a un aliado</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro23" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Ayudar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(23)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Aportas ventaja en una prueba de habilidad o de ataque</p>
                                    <p class="textoRecuadro">Alternativamente, si el objetivo esta lejos de ti, puedes hacerle ganar 
                                        ventaja en la siguiente tirada de ataque contra una criatura a 5 pies de ti.</p>
                                    <p class="textoRecuadro">La ventaja dura hasta el comienzo de tu próximo turno.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(24)">        
                                <div class="tituloOpcion">Preparar una acción</div>       
                                <div class="descripcionOpcion">Elige una circunstancia y una acción</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro24" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Preparar una acción
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(24)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Elige una circunstancia y una acción</p>
                                    <p class="textoRecuadro">Cuando ocurra la circunstancia elegida, 
                                        podrás elegir entre usar tu reacción para realizar la acción
                                        preparada.</p> 
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(25)">        
                                <div class="tituloOpcion">Equipar escudo</div>       
                                <div class="descripcionOpcion">Te equipas o desequipas un escudo</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro25" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Equipar escudo
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(25)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Te equipas o desequipas un escudo</p>
                                    <p class="textoRecuadro">Un escudo siempre toma una acción para equipar o desequipar</p>
                                    <p class="textoRecuadro">La armadura tarda varios minutos en ponerse o quitarse</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(26)">        
                                <div class="tituloOpcion">Usar un objeto</div>       
                                <div class="descripcionOpcion">Utilizar un objeto</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro26" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Usar un objeto
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(26)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Utilizar un objeto</p>
                                    <p class="textoRecuadro">Utilizar un segundo objeto, como abrir otra puerta, otra ventana
                                        sacar otra cosa de la mochila, etc.</p>
                                    <p class="textoRecuadro">Algunos objetos, como las pociones de vida, necesitan una acción
                                        para poder ser utilizados.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(27)">        
                                <div class="tituloOpcion">Busqueda</div>       
                                <div class="descripcionOpcion">Buscar algo en el campo de batalla</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro27" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Busqueda
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(27)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Buscar algo en el campo de batalla</p>
                                    <p class="textoRecuadro">El DM puede pedirte que realices una prueba de 
                                        Sabiduría (Percepción) o una prueba de Inteligencia (Investigación).</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(28)">        
                                <div class="tituloOpcion">Usar un rasgo de personaje</div>       
                                <div class="descripcionOpcion">Hay rasgos que cuestan 1 acción</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro28" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Usar un rasgo de personaje
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(28)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Utiliza un rasgo racial o de clase que use una acción</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(29)">        
                                <div class="tituloOpcion">Estabilizar</div>       
                                <div class="descripcionOpcion">Reanimación de moribundos</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro29" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Estabilizar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(29)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Evita que un moribundo tenga que realizar tiradas de salvacion contra muerte</p>
                                    <p class="textoRecuadro">Realiza un prueba de Sabiduría (Medicina) CD 10.</p>
                                    <p class="textoRecuadro">Un personaje estable recupera 1 punto de vida después de 1d4 horas.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(30)">        
                                <div class="tituloOpcion">Realizar una tirada de habilidad</div>       
                                <div class="descripcionOpcion">Intentar algo diferente</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro30" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Realizar una tirada de habilidad
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(30)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Intentar algo diferente</p>
                                    <p class="textoRecuadro">Intentas realizar una prueba de Carisma, para convencer
                                        a un enemigo, engañarlo, o hacer que se centré en ti, o de Destreza para distraerlo
                                        o Ocultarle algo, o de Sabiduría para saber sus intenciones, etc.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(31)">        
                                <div class="tituloOpcion">Improvisar</div>       
                                <div class="descripcionOpcion">Cualquier cosa que no este aquí</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro31" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Improvisar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(31)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Cualquier cosa</p>
                                    <p class="textoRecuadro">Describe un tipo de movimiento que no se detalla en ninguna otra 
                                        parte de las reglas, el DM te dirá si es posible y qué tipo de tirada necesitas hacer, 
                                        si el lo ve necesario, para determinar si lo consigues o no</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorTurnos">
                <div class="titulosTurnos">
                    Acciones Adicionales <span>Máx 1 por Turno</span>
                </div>
                <div class="contenidoTurnos">
                    <div class="subtitulosTurnos">
                        Puedes realizar un ataque, usar un rasgo o lanzar un hechizo si este se indica que se
                        puede usar como acción adicional.
                    </div>
                    <div class="opcionesTurnos">
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(32)">        
                                <div class="tituloOpcion">Ataque con la segunda arma</div>       
                                <div class="descripcionOpcion">Junto a la acción de atacar</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro32" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Ataque con la segunda arma
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(32)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Se realiza junto a la acción de atacar</p>
                                    <p class="textoRecuadro">Debes usar la acción de Atacar, aunque puedas atacar más de una vez por
                                        algún rasgo, solo puedes atacar con la mano secundaria una vez, esta debe tener la propiedad ligera, en otro caso
                                        el golpe tendrá desventaja.</p>
                                    <p class="textoRecuadro">No se agrega el modificador a la tirada de daño, a menos que sea negativo.</p>
                                    <p class="textoRecuadro">Si alguna de las armas tiene la propiedad de arrojadiza, puedes lanzarla.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(33)">        
                                <div class="tituloOpcion">Lanzar hechizo</div>       
                                <div class="descripcionOpcion">Hechizos de acción adicional</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro33" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Lanzar hechizo
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(33)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Lanza un hechizo con un tiempo de lanzamiento de 1 acción adicional</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(34)">        
                                <div class="tituloOpcion">Usar un rasgo de personaje</div>       
                                <div class="descripcionOpcion">Hay rasgos que cuestan 1 acción adicional</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro34" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Usar un rasgo de personaje
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(34)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Utiliza un rasgo racial o de clase que use una acción adicional</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(35)">        
                                <div class="tituloOpcion">Abrirse paso</div>       
                                <div class="descripcionOpcion">Atravesar al objetivo de forma bruta</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro35" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Abrirse paso
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(35)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Tratas de pasar a través del espacio que ocupa una criatura hostil</p>
                                    <p class="textoRecuadro"> Realizas una tirada de Fuerza (Atletismo) opuesta por la Fuerza (Atletismo) 
                                        de la criatura hostil.</p>
                                    <p class="textoRecuadro"> Tienes ventaja si la criatura es una talla menor, o desventaja si es
                                        una talla mayor.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(36)">        
                                <div class="tituloOpcion">Rodar</div>       
                                <div class="descripcionOpcion">Atravesar al objetivo de forma artística</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro36" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Rodar
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(36)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Tratas de pasar a través del espacio que ocupa una criatura hostil</p>
                                    <p class="textoRecuadro">Realizas una tirada de Destreza (Acrobacias) 
                                        opuesta por la Destreza (Acrobacias) de la criatura hostil.</p>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorTurnos">
                <div class="titulosTurnos">
                    Reacciones <span>Máx 1 por Ronda</span>
                </div>
                <div class="contenidoTurnos">
                    <div class="subtitulosTurnos">
                        Una reacción es una respuesta instantánea a una acción de otro personaje
                        que puede ocurrir en su turno o en el de otra persona.
                    </div>
                    <div class="opcionesTurnos">
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(37)">        
                                <div class="tituloOpcion">Ataque de oportunidad</div>       
                                <div class="descripcionOpcion">Un enemigo sale de tu rango de ataque</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro37" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Ataque de oportunidad
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(37)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Si un enemigo sale del rango de tu ataque sin usar retirada</p>
                                    <p class="textoRecuadro">Se podrá realizar una unica tirada de ataque contra el enemigo que salga
                                        del rango.</p>
                                    <p class="textoRecuadro">No se provoca ataques de oportunidad cuando se teletransporta 
                                        o cuando alguien o algo les mueve sin usar su movimiento, acción o reacción.</p>
                                </div>
                            </div>
                        </div>
                        <div class="opcion">    
                            <div class="contendorOpcion" onclick="mostrarRecuadro(38)">        
                                <div class="tituloOpcion">Lanzar hechizo</div>       
                                <div class="descripcionOpcion">Hay hechizos que cuestan 1 reacción</div>    
                            </div>
                            <div class="opcionRecuadro" id="recuadro38" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Lanzar hechizo
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro(38)">X</span>
                                    </div>
                                    <hr>
                                    <p class="subtituloRecuadro">Algunos hechizos cuestan 1 reacción</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/reglas/turnosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

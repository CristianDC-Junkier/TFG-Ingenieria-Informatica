
//Bloques
let Bloque_1 = document.getElementById("Bloque1");
let Bloque_2 = document.getElementById("Bloque2");
let Bloque_3 = document.getElementById("Bloque3");
let Bloque_4 = document.getElementById("Bloque4");

//Bloque 1
let Nombre = document.getElementById('namePersonaje');
let Raza = document.getElementById('racePersonaje');
let Clase = document.getElementById('classPersonaje');
let SubRaza = document.getElementById('subracePersonaje');
let SubClase = document.getElementById('subclassPersonaje');
let Trasfondo = document.getElementById('trasPersonaje');
let NombreError = document.getElementById('namePersonajeInput');

//Bloque 3
let puntos = document.querySelectorAll('.puntos input');
let sumaVariable = document.getElementById('suma');
let modificadores = document.querySelectorAll('.modificadores');
let elecAtr = document.getElementById('eleccionAtr');

//Botones
let Avanzar = document.getElementById("botonAvanzarFormulario");
let Volver = document.getElementById("botonVolverFormulario");
let Enviar = document.getElementById("botonEnviar");

//Error
let MensajeError = document.getElementById("mensajeError");
let MensajeErrorB3 = document.getElementById("mensajeErrorB3");

/*Colocar SubClase si tiene*/
Clase.addEventListener('change', function () {
    escogiendoPersonaje("CrearPersonajesSubclase", Clase.value, SubClase);

});

/*Colocar SubRaza si tiene*/
Raza.addEventListener('change', function () {
    escogiendoPersonaje("CrearPersonajesSubraza", Raza.value, SubRaza);
});


function AvanzarFormulario(Pag) {
    switch (Pag) {
        case 2:
            //Compruebo que elegí cosas
            if (Clase.value === "-" || Raza.value === "-" || Nombre.value === "" || Trasfondo.value === "") {
                MensajeError.innerHTML = "";
                MensajeError.innerHTML = "<br> Debes introducir nombre y elegir raza , clase y trasfondo";
            }else if(NombreError.innerHTML === "✖"){
                MensajeError.innerHTML = "";
                MensajeError.innerHTML = "<br> El nombre introducido ya está en uso";
            }
                else {
                //Borro Mensaje de Error si había
                MensajeError.innerHTML = "";
                //Mostrar Bloques
                Bloque_1.style.display = "none";
                Bloque_2.style.display = "block";
                //Cambiar botones
                Avanzar.onclick = function () {
                    AvanzarFormulario(3);
                };
                Volver.onclick = function () {
                    RetrocederFormulario(1);
                };
                //Mostrar bien la Pestaña
                cambiarHabilidades(SubRaza.value, Clase.value, Bloque_2);
            }
            break;
        case 3:
            //Mostrar Bloques
            Bloque_2.style.display = "none";
            Bloque_3.style.display = "block";
            //Mostrar Atributos que ganas
            indicarAtr(SubRaza.value, elecAtr);
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(4);
            };
            Volver.onclick = function () {
                RetrocederFormulario(2);
            };
            break;
        case 4:
            //Recojo los datos
            let atributo1 = document.getElementById('atr1');
            let atributo2 = document.getElementById('atr2');
            let atributos1 = document.getElementById('atrs1');
            let atributos2 = document.getElementById('atrs2');
            let atributos3 = document.getElementById('atrs3');
            //Compruebo el tipo
            if (atributo1 !== null) {
                if (atributo1.value === atributo2.value) {
                    MensajeErrorB3.innerHTML = "";
                    MensajeErrorB3.innerHTML = "<br> Debes elegir diferentes atributos";
                } else {
                    //Borro Mensaje de Error si había
                    MensajeErrorB3.innerHTML = "";
                    //Mostrar Bloques
                    Bloque_3.style.display = "none";
                    Bloque_4.style.display = "block";
                    //Cambiar botones
                    Avanzar.type = "hidden";
                    Enviar.type = "submit";
                    Volver.onclick = function () {
                        RetrocederFormulario(3);
                    };
                }
            } else if (atributos1 !== null) {
                if (atributos1.value === atributos2.value || atributos2.value === atributos3.value || atributos1.value === atributos3.value) {
                    MensajeErrorB3.innerHTML = "";
                    MensajeErrorB3.innerHTML = "<br> Debes elegir diferentes atributos";
                } else {
                    //Borro Mensaje de Error si había
                    MensajeErrorB3.innerHTML = "";
                    //Mostrar Bloques
                    Bloque_3.style.display = "none";
                    Bloque_4.style.display = "block";
                    //Cambiar botones
                    Avanzar.type = "hidden";
                    Enviar.type = "submit";
                    Volver.onclick = function () {
                        RetrocederFormulario(3);
                    };
                }
            } else {
                //Mostrar Bloques
                Bloque_3.style.display = "none";
                Bloque_4.style.display = "block";
                //Cambiar botones
                Avanzar.type = "hidden";
                Enviar.type = "submit";
                Volver.onclick = function () {
                    RetrocederFormulario(3);
                };
            }
            break;
    }
}

function RetrocederFormulario(Pag) {
    switch (Pag) {
        case 1:
            //Mostrar Bloques
            Bloque_1.style.display = "block";
            Bloque_2.style.display = "none";
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(2);
            };
            Volver.onclick = "location.pathname = 'TFG/Principal/inicio'";
            break;
        case 2:
            //Mostrar Bloques
            Bloque_2.style.display = "block";
            Bloque_3.style.display = "none";
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(3);
            };
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            break;
        case 3:
            //Mostrar Bloques
            Bloque_3.style.display = "block";
            Bloque_4.style.display = "none";
            //Cambiar botones
            Enviar.type = "hidden";
            Avanzar.type = "submit";
            Volver.onclick = function () {
                RetrocederFormulario(2);
            };
            break;
    }
}

//Bloque 3

function calcularSuma() {
    let suma = 0;

    puntos.forEach(function (input) {

        var value = parseInt(input.value);
        if (value === 14) {
            suma += (value - 8) + 1;
        } else if (value === 15) {
            suma += (value - 8) + 2;
        } else {
            suma += (value - 8);
        }
    });
    sumaVariable.innerHTML = "La suma total es: " + suma + "<br> Lo normal para un héroe es tener 21 puntos";
}

function calcularModificadores() {
    modificadores.forEach(function (modificador, index) {
        let valorMod = puntos[index];
        let valor = parseInt(valorMod.value);

        if (valor === 8 || valor === 9) {
            modificador.innerHTML = "-1";
        } else if (valor === 10 || valor === 11) {
            modificador.innerHTML = "0";
        } else if (valor === 12 || valor === 13) {
            modificador.innerHTML = "+1";
        } else {
            modificador.innerHTML = "+2";
        }
    });
}

puntos.forEach(function (input) {
    input.addEventListener('change', calcularSuma);
});

document.querySelectorAll('.puntos').forEach(function (container) {
    let valorCuadro = container.querySelector('input');
    let botonAumentar = container.querySelector('.botonaumentar');
    let botonDecrementar = container.querySelector('.botondecrementar');

    botonAumentar.addEventListener('click', function () {
        let valor = parseInt(valorCuadro.value);
        let max = parseInt(valorCuadro.getAttribute('max'));
        if (valor < max) {
            valorCuadro.value = valor + 1;
            calcularSuma();
            calcularModificadores();
        }
    });

    botonDecrementar.addEventListener('click', function () {
        var valor = parseInt(valorCuadro.value);
        var min = parseInt(valorCuadro.getAttribute('min'));
        if (valor > min) {
            valorCuadro.value = valor - 1;
            calcularSuma();
            calcularModificadores();
        }
    });
});




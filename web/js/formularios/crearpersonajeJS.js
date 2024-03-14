
//Bloques
let Bloque_1 = document.getElementById("Bloque1");
let Bloque_2 = document.getElementById("Bloque2");

//Bloque 1
let Raza = document.getElementById('racePersonaje');
let Clase = document.getElementById('classPersonaje');
let SubRaza = document.getElementById('subracePersonaje');
let SubClase = document.getElementById('subclassPersonaje');

//Botones
let Avanzar = document.getElementById("botonAvanzarFormulario");
let Volver = document.getElementById("botonVolverFormulario");
let Enviar = document.getElementById("botonEnviar");

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
            Bloque_1.style.display = "none";
            Bloque_2.style.display = "block";
            Avanzar.onclick = function() { AvanzarFormulario(3); };
            Volver.onclick = function() { RetrocederFormulario(1); };
            break;
        case 3:
            break;
    }
}

function RetrocederFormulario(Pag) {
    switch (Pag) {
        case 1:
            Bloque_1.style.display = "block";
            Bloque_2.style.display = "none";
            Avanzar.onclick = function() { AvanzarFormulario(2); };
            Volver.onclick = "location.pathname = 'TFG/Principal/inicio'";
            break;
        case 2:
            break;
    }
}



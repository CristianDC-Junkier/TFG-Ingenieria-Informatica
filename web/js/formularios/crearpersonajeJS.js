
//Bloques
let Bloque_1 = document.getElementById("Bloque1");
let Bloque_2 = document.getElementById("Bloque2");

//Bloque 1
let Raza = document.getElementById('racePersonaje');
let Clase = document.getElementById('classPersonaje');
let SubRaza = document.getElementById('subracePersonaje');
let SubClase = document.getElementById('subclassPersonaje');

//Bloque 2


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
            //Mostrar Bloques
            Bloque_1.style.display = "none";
            Bloque_2.style.display = "block";
            //Cambiar botones
            Avanzar.onclick = function() { AvanzarFormulario(3); };
            Volver.onclick = function() { RetrocederFormulario(1); };
            //Mostrar bien la Pestaña
            //function() { cambiarHabilidades(SubRaza.value, Clase.value, Bloque_2); };
            break;
        case 3:
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
            Avanzar.onclick = function() { AvanzarFormulario(2); };
            Volver.onclick = "location.pathname = 'TFG/Principal/inicio'";
            break;
        case 2:
            break;
    }
}

function cambiarHabilidades(num,etiqueta){
    
}



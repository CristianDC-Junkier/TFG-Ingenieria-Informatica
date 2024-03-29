//Select y botones
let Escuela = document.getElementById('Selectescuela');
let Nivel = document.getElementById('Selectnivel');
let Clase = document.getElementById('Selectclase');

let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;


for (var i = 0; i < Escuela.options.length; i++) {
    if (Escuela.options[i].value === valorSeleccionado1) {
        Escuela.options[i].selected = true;
        break;
    } else {
        Escuela.options[i].selected = false;
    }
}
for (var i = 0; i < Nivel.options.length; i++) {
    if (Nivel.options[i].value === valorSeleccionado2) {
        Nivel.options[i].selected = true;
        break;
    } else {
        Nivel.options[i].selected = false;
    }
}
for (var i = 0; i < Clase.options.length; i++) {
    if (Clase.options[i].value === valorSeleccionado3) {
        Clase.options[i].selected = true;
        break;
    } else {
        Clase.options[i].selected = false;
    }
}

Escuela.addEventListener('change', function () {
    let valorSeleccionado = Escuela.value;
    let urlDestinoOrden = "/TFG/Personajes/personajeHechizos?escuela=" + valorSeleccionado + "&nivel=" + Nivel.value + "&clase=" + Clase.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});
Nivel.addEventListener('change', function () {
    let valorSeleccionado = Nivel.value;
    let urlDestinoOrden = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + valorSeleccionado + "&clase=" + Clase.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});
Clase.addEventListener('change', function () {
    let valorSeleccionado = Clase.value;
    let urlDestinoOrden = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + Nivel.value + "&clase=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});


//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + Nivel.value + "&clase=" + Clase.value + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + Nivel.value + "&clase=" + Clase.value + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + Nivel.value + "&clase=" + Clase.value + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Personajes/personajeHechizos?escuela=" + Escuela.value + "&nivel=" + Nivel.value + "&clase=" + Clase.value + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}


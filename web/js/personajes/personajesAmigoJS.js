//Select y botones
let Orden = document.getElementById('ordenarNombre');
let Raza = document.getElementById('filtroRaza');
let Clase = document.getElementById('filtroClase');
let Nivel = document.getElementById('filtroNivel');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');

let totalaux = numpag - pag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + valorSeleccionado + "&raza=" + raza + "&clase=" + raza + "&nivel=" + raza + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Raza.addEventListener('change', function () {
    let valorSeleccionado = Raza.value;
    let urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + valorSeleccionado + "&clase=" + raza + "&nivel=" + raza + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Clase.addEventListener('change', function () {
    let valorSeleccionado = Clase.value;
    let urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden="+ orden + "&raza=" + raza + "&clase=" + valorSeleccionado + "&nivel=" + raza + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Nivel.addEventListener('change', function () {
    let valorSeleccionado = Nivel.value;
    let urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + raza + "&clase=" + raza + "&nivel=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + raza + "&clase=" + raza + "&nivel=" + raza + "&pag=" + "1";
        window.location.href = urlDestinoPagIni;
    });
}
//Pag Final
if (totalaux > 1) {
    Bfinal.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + raza + "&clase=" + raza + "&nivel=" + raza + "&pag=" + numpag;
        window.location.href = urlDestinoPagIni;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + raza + "&clase=" + raza + "&nivel=" + raza + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPagIni;
    });
}
//Pag posterior
if (totalaux > 2) {
    BPosterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajesAmigo?amigo= "+ amigo.id + "&orden=" + orden + "&raza=" + raza + "&clase=" + raza + "&nivel=" + raza + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPagIni;
    });
}


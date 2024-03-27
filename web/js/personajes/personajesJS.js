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

for (var i = 0; i < Raza.options.length; i++) {
    if (Raza.options[i].value === raza) {
        Raza.options[i].selected = true;
        break;
    } else {
        Raza.options[i].selected = false;
    }
}
for (var i = 0; i < Clase.options.length; i++) {
    if (Clase.options[i].value === clase) {
        Clase.options[i].selected = true;
        break;
    } else {
        Nivel.options[i].selected = false;
    }
}
for (var i = 0; i < Nivel.options.length; i++) {
    if (Nivel.options[i].value === nivel) {
        Nivel.options[i].selected = true;
        break;
    } else {
        Nivel.options[i].selected = false;
    }
}

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + valorSeleccionado + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Raza.addEventListener('change', function () {
    let valorSeleccionado = Raza.value;
    let urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + valorSeleccionado + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Clase.addEventListener('change', function () {
    let valorSeleccionado = Clase.value;
    let urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + valorSeleccionado + "&nivel=" + Nivel.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Nivel.addEventListener('change', function () {
    let valorSeleccionado = Nivel.value;
    let urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + "1";
        window.location.href = urlDestinoPagIni;
    });
}
//Pag Final
if (totalaux > 1) {
    Bfinal.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + numpag;
        window.location.href = urlDestinoPagIni;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPagIni;
    });
}
//Pag posterior
if (totalaux > 2) {
    BPosterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoOrden = "/TFG/Personajes/personajes?orden=" + Orden.value + "&raza=" + Raza.value + "&clase=" + Clase.value + "&nivel=" + Nivel.value + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPagIni;
    });
}


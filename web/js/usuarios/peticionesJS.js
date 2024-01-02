//Select y botones
let Orden = document.getElementById('ordenarPeticiones');
let Mesa = document.getElementById('botonMesa');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let Bpeticion = document.getElementById('botonPeticiones');
let urlDestinoPag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + valorSeleccionado + "&mesa=" + mesa + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Mesa.addEventListener('click', function () {
    let urlDestinoMesa;
    if (mesaBool) {
        urlDestinoMesa = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + "false" + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + "true" + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});

Bpeticion.addEventListener('click', function () {
    let urlDestinoBoton;
    if (peticion === "Enviadas") {
        urlDestinoBoton = "/TFG/Usuarios/mostrarPeticionesRecibidas?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag;
    } else {
        urlDestinoBoton = "/TFG/Usuarios/mostrarPeticionesEnviadas?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag;
    }
    window.location.href = urlDestinoBoton;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}



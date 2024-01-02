//Select y botones
let Orden = document.getElementById('ordenarAmigos');
let Mesa = document.getElementById('botonMesa');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Usuarios/mostrarAmigos?orden=" + valorSeleccionado + "&mesa=" + mesa + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Mesa.addEventListener('click', function () {
    let urlDestinoMesa;
    if (mesaBool) {
        urlDestinoMesa = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + "false" + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + "true" + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});
//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + mesa + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (urlDestinoPag > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + mesa + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (urlDestinoPag > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarAmigos?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}


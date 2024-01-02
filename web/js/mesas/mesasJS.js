//Select y botones
let Orden = document.getElementById('ordenarMesa');
let Lleno = document.getElementById('botonLleno');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Mesas/mostrarMesas?orden=" + valorSeleccionado + "&lleno=" + lleno + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Lleno.addEventListener('click', function () {
    let urlDestinoMesa;
    if (llenoBool) {
        urlDestinoMesa = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + "false" + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + "true" + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});
//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}


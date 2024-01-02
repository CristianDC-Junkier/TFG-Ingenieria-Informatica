//Select y botones
let Orden = document.getElementById('ordenarBloqueados');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Usuarios/mostrarBloqueados?orden=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}



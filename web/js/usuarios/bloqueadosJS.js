//Select y botones
let Orden = document.getElementById('ordenarBloqueados');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');

let totalaux = numpag - pag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Usuarios/mostrarBloqueados?orden=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + "1";
        window.location.href = urlDestinoPagIni;
    });
}
//Pag Final
if (totalaux > 1) {
    Bfinal.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + numpag;
        window.location.href = urlDestinoPagIni;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPagIni;
    });
}
//Pag posterior
if (totalaux > 2) {
    BPosterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPagIni;
    });
}



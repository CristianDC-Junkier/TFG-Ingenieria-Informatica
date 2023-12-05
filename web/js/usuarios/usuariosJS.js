//Select y botones
let Orden = document.getElementById('ordenarUsuarios');
let Mesa = document.getElementById('botonMesa');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');


let totalaux = numpag - pag;

Orden.addEventListener('change', function () {
    let valorSeleccionado = Orden.value;
    let urlDestinoOrden = "/TFG/Usuarios/mostrarUsuarios?orden=" + valorSeleccionado + "&mesa=" + mesa + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Mesa.addEventListener('click', function () {
    let urlDestinoMesa;
    if (mesaBool) {
        urlDestinoMesa = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + "false" + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + "true" + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + mesa + "&pag=" + "1";
        window.location.href = urlDestinoPagIni;
    });
}
//Pag Final
if (totalaux > 1) {
    Bfinal.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + mesa + "&pag=" + numpag;
        window.location.href = urlDestinoPagIni;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPagIni;
    });
}
//Pag posterior
if (totalaux > 2) {
    BPosterior.addEventListener('click', function () {
        let urlDestinoPagIni;
        urlDestinoPagIni = "/TFG/Usuarios/mostrarUsuarios?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPagIni;
    });
}



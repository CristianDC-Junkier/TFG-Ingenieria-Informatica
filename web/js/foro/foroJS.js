//Select y botones
let Tema = document.getElementById('elegirTema');
let Seccion = document.getElementById('elegirSeccion');
let Mio = document.getElementById('botonMio');
let Comentado = document.getElementById('botonComentado');
let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;

if (tema !== "" && tema !== null) {
    Tema.value = tema;
}
if (seccion !== "" && seccion !== null) {
    Seccion.value = seccion;
}

Tema.addEventListener('change', function () {
    let valorSeleccionado = Tema.value;
    let urlDestinoOrden = "/TFG/Foro/hilos?tema=" + valorSeleccionado + "&seccion=" + Seccion.value + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Seccion.addEventListener('change', function () {
    let valorSeleccionado = Seccion.value;
    let urlDestinoOrden = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + valorSeleccionado + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});

Mio.addEventListener('click', function () {
    let urlDestinoMesa;
    if (mio) {
        urlDestinoMesa = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + "false" + "&comentado=" + comentado + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + "true" + "&comentado=" + comentado + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});

Comentado.addEventListener('click', function () {
    let urlDestinoMesa;
    if (comentado) {
        urlDestinoMesa = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + mio + "&comentado=" + "false" + "&pag=" + pag;
    } else {
        urlDestinoMesa = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + mio + "&comentado=" + "true" + "&pag=" + pag;
    }
    window.location.href = urlDestinoMesa;
});

//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + Seccion.value + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + seccion + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Foro/hilos?tema=" + Tema.value + "&seccion=" + seccion + "&mio=" + mio + "&comentado=" + comentado + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}


//Select y botones
let VD = document.getElementById('Selectdesafio');
let Tipo = document.getElementById('Selecttipo');

let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;



for (var i = 0; i < VD.options.length; i++) {
    if (VD.options[i].value === valorSeleccionado1) {
        VD.options[i].selected = true;
        break;
    } else {
        VD.options[i].selected = false;
    }
}
for (var i = 0; i < Tipo.options.length; i++) {
    if (Tipo.options[i].value === valorSeleccionado2) {
        Tipo.options[i].selected = true;
        break;
    } else {
        Tipo.options[i].selected = false;
    }
}

function asignarEventos() {
    let Tfilas = document.querySelectorAll("#Tabla tr");

    Tfilas.forEach(function (fila) {
        fila.addEventListener("click", function () {
            let idMonstruo = fila.dataset.id;
            if (idMonstruo !== undefined) {
                let urlDestinoOrden = "/TFG/Explorar/monstruo?idMonstruo=" + idMonstruo;
                window.location.href = urlDestinoOrden;
            }
        });
    });
}
asignarEventos();

VD.addEventListener('change', function () {
    let valorSeleccionado = VD.value;
    let urlDestinoOrden = "/TFG/Explorar/monstruos?vd=" + valorSeleccionado + "&tipo=" + Tipo.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});
Tipo.addEventListener('change', function () {
    let valorSeleccionado = Tipo.value;
    let urlDestinoOrden = "/TFG/Explorar/monstruos?vd=" + VD.value + "&tipo=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});



//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/monstruos?vd=" + vd.value + "&tipo=" + Nivel.value + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/monstruos?vd=" + vd.value + "&tipo=" + Nivel.value + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/monstruos?vd=" + vd.value + "&tipo=" + Nivel.value + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/monstruos?vd=" + vd.value + "&tipo=" + Nivel.value + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}


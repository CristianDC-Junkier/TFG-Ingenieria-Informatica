//Select y botones
let Tipo = document.getElementById('Selecttipo');
let Categoria = document.getElementById('Selectcategoria');
let Propiedad = document.getElementById('Selectpropiedad');

let Binicio = document.getElementById('pagInicio');
let Bfinal = document.getElementById('pagFinal');
let BAnterior = document.getElementById('pagAnterior');
let BPosterior = document.getElementById('pagPosterior');
let urlDestinoPag;



for (var i = 0; i < Tipo.options.length; i++) {
    if (Tipo.options[i].value === valorSeleccionado1) {
        Tipo.options[i].selected = true;
        break;
    } else {
        Tipo.options[i].selected = false;
    }
}
for (var i = 0; i < Categoria.options.length; i++) {
    if (Categoria.options[i].value === valorSeleccionado2) {
        Categoria.options[i].selected = true;
        break;
    } else {
        Categoria.options[i].selected = false;
    }
}
for (var i = 0; i < Propiedad.options.length; i++) {
    if (Propiedad.options[i].value === valorSeleccionado3) {
        Propiedad.options[i].selected = true;
        break;
    } else {
        Propiedad.options[i].selected = false;
    }
}

Tipo.addEventListener('change', function () {
    let valorSeleccionado = Tipo.value;
    let urlDestinoOrden = "/TFG/Explorar/equipo?tipo=" + valorSeleccionado + "&categoria=" + Categoria.value + "&propiedad=" + Propiedad.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});
Categoria.addEventListener('change', function () {
    let valorSeleccionado = Categoria.value;
    let urlDestinoOrden = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + valorSeleccionado + "&propiedad=" + Propiedad.value + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});
Propiedad.addEventListener('change', function () {
    let valorSeleccionado = Propiedad.value;
    let urlDestinoOrden = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + Categoria.value + "&propiedad=" + valorSeleccionado + "&pag=" + pag;
    window.location.href = urlDestinoOrden;
});


//Pag Inicio
if (pag > 2) {
    Binicio.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + Categoria.value + "&propiedad=" + Propiedad.value + "&pag=" + "1";
        window.location.href = urlDestinoPag;
    });
}
//Pag Final
if (pagpordelante > 1) {
    Bfinal.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + Categoria.value + "&propiedad=" + Propiedad.value + "&pag=" + numpag;
        window.location.href = urlDestinoPag;
    });
}
//Pag anterior
if (pag > 1) {
    BAnterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + Categoria.value + "&propiedad=" + Propiedad.value + "&pag=" + (pag - 1);
        window.location.href = urlDestinoPag;
    });
}
//Pag posterior
if (pagpordelante > 0) {
    BPosterior.addEventListener('click', function () {
        urlDestinoPag = "/TFG/Explorar/equipo?tipo=" + Tipo.value + "&categoria=" + Categoria.value + "&propiedad=" + Propiedad.value + "&pag=" + (pag + 1);
        window.location.href = urlDestinoPag;
    });
}



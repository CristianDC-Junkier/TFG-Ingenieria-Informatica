
function actualizarBotones() {

    let pestañasBotones = document.getElementById('pestañaBotones');
    pestañasBotones.innerHTML = '';

    if (pag > 2) {
        pestañasBotones.innerHTML += '<button class="botonArriba" id="pagInicio">Inicio</button>';
    }
    if (pag > 1) {
        pestañasBotones.innerHTML += '<button class="botonArriba" id="pagAnterior">' + (pag - 1) + '</button>';
    }

    pestañasBotones.innerHTML += '<button class="botonArriba" id="pagActual"> Actual </button>';

    if (totalaux > 2) {
        pestañasBotones.innerHTML += '<button class="botonArriba" id="pagPosterior">' + (pag + 1) + '</button>';
    }
    if (totalaux > 1) {
        pestañasBotones.innerHTML += '<button class="botonArriba" id="pagFinal">Final</button>';
    }
}
actualizarBotones();



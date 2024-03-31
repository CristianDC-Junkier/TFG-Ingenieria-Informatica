let puntos = document.querySelectorAll('.puntos input');
let modificadores = document.querySelectorAll('.modificadores');

function calcularModificadores() {
    modificadores.forEach(function (modificador, index) {
        let valorMod = puntos[index];
        let valor = parseInt(valorMod.value);

        switch (valor) {
            case 8:
            case 9:
                modificador.innerHTML = "-1";
                break;
            case 10:
            case 11:
                modificador.innerHTML = "0";
                break;
            case 12:
            case 13:
                modificador.innerHTML = "+1";
                break;
            case 14:
            case 15:
                modificador.innerHTML = "+2";
                break;
            case 16:
            case 17:
                modificador.innerHTML = "+3";
                break;
            case 18:
            case 19:
                modificador.innerHTML = "+4";
                break;
            case 20:
                modificador.innerHTML = "+5";
                break;
        }
    });
}

document.querySelectorAll('.puntos').forEach(function (container) {
    let valorCuadro = container.querySelector('input');
    let botonAumentar = container.querySelector('.botonaumentar');
    let botonDecrementar = container.querySelector('.botondecrementar');

    botonAumentar.addEventListener('click', function () {
        let valor = parseInt(valorCuadro.value);
        let max = parseInt(valorCuadro.getAttribute('max'));
        if (valor < max) {
            valorCuadro.value = valor + 1;
            calcularModificadores();
        }
    });

    botonDecrementar.addEventListener('click', function () {
        var valor = parseInt(valorCuadro.value);
        var min = parseInt(valorCuadro.getAttribute('min'));
        if (valor > min) {
            valorCuadro.value = valor - 1;
            calcularModificadores();
        }
    });
});

calcularModificadores();




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

calcularModificadores();

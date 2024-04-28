
function mostrarRecuadro() {
    document.getElementById('recuadro').style.display = 'flex';
}

function cerrarRecuadro() {
    document.getElementById('recuadro').style.display = 'none';
}

function mostrarRecuadro1() {
    document.getElementById('recuadro1').style.display = 'flex';
}

function cerrarRecuadro1() {
    document.getElementById('recuadro1').style.display = 'none';
}

function mostrarRecuadro2() {
    document.getElementById('recuadro2').style.display = 'flex';
}

function cerrarRecuadro2() {
    document.getElementById('recuadro2').style.display = 'none';
}
function mostrarRecuadro3() {
    document.getElementById('recuadro3').style.display = 'flex';
}

function cerrarRecuadro3() {
    document.getElementById('recuadro3').style.display = 'none';
}
function mostrarRecuadro4() {
    document.getElementById('recuadro4').style.display = 'flex';
}

function cerrarRecuadro4() {
    document.getElementById('recuadro4').style.display = 'none';
}
function mostrarRecuadro5() {
    document.getElementById('recuadro5').style.display = 'flex';
}

function cerrarRecuadro5() {
    document.getElementById('recuadro5').style.display = 'none';
}
function mostrarRecuadro6() {
    document.getElementById('recuadro6').style.display = 'flex';
}

function cerrarRecuadro6() {
    document.getElementById('recuadro6').style.display = 'none';
}

function mostrarRecuadroX(href,id) {

    document.getElementById('recuadroX').style.display = 'flex';

    let boton = document.getElementById('eliminar');
    boton.onclick = function () {
        location.href = href + id;
    };
}

function cerrarRecuadroX() {
    document.getElementById('recuadroX').style.display = 'none';
}


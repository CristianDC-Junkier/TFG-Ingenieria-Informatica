
function mostrarRecuadro() {
    document.getElementById('recuadro').style.display = 'flex';
}

function cerrarRecuadro() {
    document.getElementById('recuadro').style.display = 'none';
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


function mostrarRecuadroX(href,id) {

    document.getElementById('recuadroX').style.display = 'flex';

    let botonEliminar = document.getElementById('eliminar');
    botonEliminar.onclick = function () {
        // Redirigir a la página de eliminación con el ID adecuado
        location.href = href + id;
    };
}

function cerrarRecuadroX() {
    document.getElementById('recuadroX').style.display = 'none';
}


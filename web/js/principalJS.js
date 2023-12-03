
function dondeEstasHorizontal()
{
    const Titulos = document.querySelectorAll('.barraHorizontalContenido');

    // Resaltar el enlace correspondiente a la página actual
    Titulos.forEach(Titulos => {
        if (window.location.href.includes(Titulos.getAttribute('href'))) {
            Titulos.classList.add('active');
        } else if (window.location.href.includes('introduccion') && Titulos.getAttribute('href').includes('index')) {
            Titulos.classList.add('active');
        }
    });
}
function dondeEstasVertical()
{
    const Titulos = document.querySelectorAll('.barraVerticalContenido');

    // Resaltar el enlace correspondiente a la página actual
    Titulos.forEach(Titulos => {
        if (window.location.href.includes(Titulos.getAttribute('href'))) {
            Titulos.classList.add('active');
        } else if (window.location.href.includes('introduccion') && Titulos.getAttribute('href').includes('inicio')) {
            Titulos.classList.add('active');
        }
    });
}

function abrirMenuVertical() {
    var botonMenuVertical = document.querySelector('.botonMenuVertical');
    var barraVertical = document.querySelector('.barraVertical');

    botonMenuVertical.addEventListener('click', function () {

        if (barraVertical.style.display === 'none' || barraVertical.style.display === '') {
            barraVertical.style.display = 'block';
        } else {
            barraVertical.style.display = 'none';
        }
    });
}

function abrirMenuVerticalAncho() {
    var anchoVentana = window.innerWidth;

    if (anchoVentana > 850) {
        var barraVertical = document.querySelector('.barraVertical');
        if (barraVertical) {
            barraVertical.style.display = 'none';
        }
    }
}


//Eventos
document.addEventListener('DOMContentLoaded', abrirMenuVertical);

//Ejecución
dondeEstasHorizontal();
dondeEstasVertical();

window.onresize = abrirMenuVerticalAncho;
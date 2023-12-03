
function dondeEstasHorizontal()
{
    let tituloPaginaH = document.querySelector(".titulosPag").innerText;
    
    // Obtén todos los elementos de la barra
    let elementosBarraHorizontal = document.querySelectorAll(".barraHorizontalContenido");
    
    // Itera sobre cada elemento de la barra
    elementosBarraHorizontal.forEach(function(elemento) {
        // Obtén el texto del elemento de la barra
        let textoElementoBarraH = elemento.innerText.trim();
        
        // Verifica si la palabra clave del título está presente en el texto del elemento de la barra
        if (tituloPaginaH.includes(textoElementoBarraH)) {
            // Agrega la clase 'active' al elemento de la barra si hay coincidencia
            elemento.classList.add('active');
        }
    });
}
function dondeEstasVertical()
{
    let tituloPaginaV = document.querySelector(".titulosPag").innerText;
    
    // Obtén todos los elementos de la barra
    let elementosBarraVertical = document.querySelectorAll(".barraVerticalContenido");
    
    // Itera sobre cada elemento de la barra
    elementosBarraVertical.forEach(function(elemento) {
        // Obtén el texto del elemento de la barra
        let textoElementoBarraV = elemento.innerText.trim();
        
        // Verifica si la palabra clave del título está presente en el texto del elemento de la barra
        if (tituloPaginaV.includes(textoElementoBarraV)) {
            // Agrega la clase 'active' al elemento de la barra si hay coincidencia
            elemento.classList.add('active');
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
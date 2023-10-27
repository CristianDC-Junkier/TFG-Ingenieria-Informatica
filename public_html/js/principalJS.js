
function dondeEstas()
{
    const Titulos = document.querySelectorAll('.BarraHorizontalContenido');  

    // Resaltar el enlace correspondiente a la página actual
    Titulos.forEach(Titulos => {
        if (window.location.href.includes(Titulos.getAttribute('href'))) {
            Titulos.classList.add('active');
        } else if (window.location.href.includes('introduccion') && Titulos.getAttribute('href').includes('inicio')) {
            Titulos.classList.add('active');
        }
    });
}


//Ejecución
dondeEstas();


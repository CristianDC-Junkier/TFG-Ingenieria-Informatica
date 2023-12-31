/*Colocar la comunidad*/
var comunidad = document.getElementById("comunityMesa");
for (let i = 0; i < comunidad.options.length; i++) {
    if (comunidad.options[i].value === comunidadActual) {
        comunidad.options[i].selected = true;
        break;
    }
}



document.addEventListener('DOMContentLoaded', function () {
    const checkbox = document.getElementById('Color');
    const stylesheetLink = document.getElementById('ColorSeleccionado');
    
    checkbox.addEventListener('change', function () {

        if (stylesheetLink) {
            const nuevoEstilo = checkbox.checked ?'/TFG/css/colorOscuroCss.css' : '/TFG/css/colorClaroCss.css' ;
            stylesheetLink.href = nuevoEstilo;
        }
    });
});



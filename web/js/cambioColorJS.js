
const checkbox = document.getElementById('Color');
const stylesheetLink = document.getElementById('ColorSeleccionado');
const estiloPredeterminado = '/TFG/css/colorClaroCss.css';
const estiloPredeterminado2 = '/TFG/css/colorOscuroCss.css';

//Si quiere cookies
if (getCookie("cookies")) {

    if (getCookie("colorPreferido") !== null) {
        if (getCookie("colorPreferido") === 'false') {
            stylesheetLink.href = estiloPredeterminado;
            checkbox.checked = false;
        } else {
            stylesheetLink.href = estiloPredeterminado2;
            checkbox.checked = true;
        }
    } else {
        stylesheetLink.href = estiloPredeterminado;
        checkbox.checked = false;
    }
} else {
    stylesheetLink.href = estiloPredeterminado;
    checkbox.checked = false;
}

checkbox.addEventListener('change', function () {
    if (stylesheetLink) {
        const nuevoEstilo = checkbox.checked ? '/TFG/css/colorOscuroCss.css' : '/TFG/css/colorClaroCss.css';
        stylesheetLink.href = nuevoEstilo;

        if (getCookie("cookies")) {
            // Guarda la preferencia de color en una cookie
            if (nuevoEstilo === '/TFG/css/colorOscuroCss.css') {
                setCookie('colorPreferido', "true", 365);
            } else {
                setCookie('colorPreferido', "false", 365);
            }
        }
    }
});

function setCookie(nombre, valor, expiracionEnDias) {
    var fechaExpiracion = new Date();
    fechaExpiracion.setDate(fechaExpiracion.getDate() + expiracionEnDias);

    var cookie = nombre + "=" + encodeURIComponent(valor) + "; expires=" + fechaExpiracion.toUTCString() + "; path=/";

    document.cookie = cookie;
}

function getCookie(nombre) {
    var nombreEQ = nombre + "=";
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        while (cookie.charAt(0) === ' ')
            cookie = cookie.substring(1, cookie.length);
        if (cookie.indexOf(nombreEQ) === 0)
            return decodeURIComponent(cookie.substring(nombreEQ.length));
    }
    return null;
}



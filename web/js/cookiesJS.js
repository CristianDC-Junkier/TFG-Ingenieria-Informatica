let cookieBanner = document.getElementById("cookie-banner");
let aceptarCookiesButton = document.getElementById("aceptar-cookies");
let rechazarCookiesButton = document.getElementById("rechazar-cookies");

// Verifica si la cookie de aceptación ya está presente
if (!getCookie("cookies")) {
    // Muestra el banner de cookies si la cookie no está presente
    cookieBanner.style.display = "block";
}

// Configura el evento click para el botón de aceptar cookies
aceptarCookiesButton.addEventListener("click", function () {
    // Establece la cookie de aceptación y oculta el banner
    setCookie("cookies", "true", 365);
    cookieBanner.style.display = "none";
});
rechazarCookiesButton.addEventListener("click", function () {
    cookieBanner.style.display = "none";
});

// Funciones auxiliares para manejar cookies
function setCookie(nombre, valor, expiracionEnDias) {
    let fechaExpiracion = new Date();
    fechaExpiracion.setDate(fechaExpiracion.getDate() + expiracionEnDias);

    let cookie = nombre + "=" + encodeURIComponent(valor) + "; expires=" + fechaExpiracion.toUTCString() + "; path=/";

    document.cookie = cookie;
}

function getCookie(nombre) {
    let nombreEQ = nombre + "=";
    let cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i];
        while (cookie.charAt(0) === ' ')
            cookie = cookie.substring(1, cookie.length);
        if (cookie.indexOf(nombreEQ) === 0)
            return cookie.substring(nombreEQ.length, cookie.length);
    }
    return null;
}

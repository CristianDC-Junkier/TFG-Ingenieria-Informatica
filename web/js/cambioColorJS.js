
const checkbox = document.getElementById('Color');
const stylesheetLink = document.getElementById('ColorSeleccionado');
const logoImg = document.getElementById('Logo');
const G4Img = document.getElementById('Guide4foto');

//Iconos:
const ICasa = document.getElementById('ICasa');
const ILupa = document.getElementById('ILupa');
const IMesa = document.getElementById('IMesa');
const IPerfil = document.getElementById('IPerfil');
const IReglas = document.getElementById('IReglas');
const IAmigos = document.getElementById('IAmigos');
const IPersonaje = document.getElementById('IPersonaje');
const IChat = document.getElementById('IChat');
const IDado = document.getElementById('IDado');



const estiloPredeterminado = '/TFG/css/colorClaroCss.css';
const estiloPredeterminado2 = '/TFG/css/colorOscuroCss.css';


//Antes de que cargue la pag
document.addEventListener("DOMContentLoaded", function () {
    //Si tiene cookies
    if (getCookie("cookies")) {
        if (getCookie("colorPreferido") !== null) {
            if (getCookie("colorPreferido") === 'false') {
                stylesheetLink.href = estiloPredeterminado;
                checkbox.checked = false;
            } else {
                stylesheetLink.href = estiloPredeterminado2;
                checkbox.checked = true;
                if (logoImg !== null) {
                    logoImg.src = '/TFG/img/dnd-banner.jpg';
                }
                if (G4Img !== null) {
                    G4Img.src = '/TFG/img/Guide4.bmp';
                }
                if (ICasa !== null) {
                    ICasa.src = '/TFG/img/iconos/Casa.png';
                }
                if (ILupa !== null) {
                    ILupa.src = '/TFG/img/iconos/Lupa.png';
                }
                if (IMesa !== null) {
                    IMesa.src = '/TFG/img/iconos/Mesa.png';
                }
                if (IPerfil !== null) {
                    IPerfil.src = '/TFG/img/iconos/Perfil.png';
                }
                if (IReglas !== null) {
                    IReglas.src = '/TFG/img/iconos/Reglas.png';
                }
                if (IAmigos !== null) {
                    IAmigos.src = '/TFG/img/iconos/Amigos.png';
                }
                if (IPersonaje !== null) {
                    IPersonaje.src = '/TFG/img/iconos/Personajes.png';
                }
                if (IChat !== null) {
                    IChat.src = '/TFG/img/iconos/Chat.png';
                }
                if (IDado !== null) {
                    IDado.src = '/TFG/img/iconos/d20.png';
                }
            }
        } else {
            stylesheetLink.href = estiloPredeterminado;
            checkbox.checked = false;
        }
    } else {
        stylesheetLink.href = estiloPredeterminado;
        checkbox.checked = false;
    }
});


checkbox.addEventListener('change', function () {
    if (stylesheetLink) {
        const nuevoEstilo = checkbox.checked ? '/TFG/css/colorOscuroCss.css' : '/TFG/css/colorClaroCss.css';
        stylesheetLink.href = nuevoEstilo;

        if (getCookie("cookies")) {
            //Guarda la preferencia de color en una cookie
            if (nuevoEstilo === '/TFG/css/colorOscuroCss.css') {
                setCookie('colorPreferido', "true", 365);
            } else {
                setCookie('colorPreferido', "false", 365);
            }
        }

        //Cambia las imagenes según el estado del checkbox
        if (logoImg !== null) {
            let nuevaImagen = checkbox.checked ? '/TFG/img/dnd-banner.jpg' : '/TFG/img/dnd-bannerWhite.jpg';
            logoImg.src = nuevaImagen;
        }
        if (G4Img !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/Guide4.bmp' : '/TFG/img/Guide4White.bmp';
            G4Img.src = nuevaImagen;
        }
        if (ICasa !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Casa.png' : '/TFG/img/iconos/CasaWhite.png';
            ICasa.src = nuevaImagen;
        }
        if (ILupa !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Lupa.png' : '/TFG/img/iconos/LupaWhite.png';
            ILupa.src = nuevaImagen;
        }
        if (IMesa !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Mesa.png' : '/TFG/img/iconos/MesaWhite.png';
            IMesa.src = nuevaImagen;
        }
        if (IPerfil !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Perfil.png' : '/TFG/img/iconos/PerfilWhite.png';
            IPerfil.src = nuevaImagen;
        }
        if (IReglas !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Reglas.png' : '/TFG/img/iconos/ReglasWhite.png';
            IReglas.src = nuevaImagen;
        }
        if (IAmigos !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Amigos.png' : '/TFG/img/iconos/AmigosWhite.png';
            IAmigos.src = nuevaImagen;
        }
        if (IPersonaje !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Personajes.png' : '/TFG/img/iconos/PersonajesWhite.png';
            IPersonaje.src = nuevaImagen;
        }
        if (IChat !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/Chat.png' : '/TFG/img/iconos/ChatWhite.png';
            IChat.src = nuevaImagen;
        }
        if (IDado !== null) {
            nuevaImagen = checkbox.checked ? '/TFG/img/iconos/d20.png' : '/TFG/img/iconos/d20White.png';
            IDado.src = nuevaImagen;
        }
    }
});

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
            return decodeURIComponent(cookie.substring(nombreEQ.length));
    }
    return null;
}



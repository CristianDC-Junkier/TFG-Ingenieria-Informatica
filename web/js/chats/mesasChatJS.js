
function ajustarVolumen(volumen) {
    let audio = document.getElementById('reproductorCancion');
    if ((!(audio.volume === 1.0 && volumen === +0.1) && !(audio.volume === 0.0 && volumen === -0.1))) {
        audio.volume = audio.volume + volumen;
        if (audio.volume < 1) {
            audio.volume = 0;
        }
    }
}



/////////////////////////////////////
///////////Chat de grupo/////////////
/////////////////////////////////////

let mensajeInput = document.getElementById("mensajeChatMesa");

function cargaChat() {

    let msjChat = document.getElementById('MensajesChat');

    let urlAJAX = "/TFG/Chats/ChatCargaMesa";
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mesa: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            let htmlResultados = data;
            // Limpiar el contenido actual de la tabla
            msjChat.innerHTML = '';
            // Insertar el nuevo HTML en el contenedor
            msjChat.innerHTML = htmlResultados;
        },
        error: function (error) {
            console.log("no");

            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

function recargaChat() {

    let msjChat = document.getElementById('MensajesChat');

    let urlAJAX = "/TFG/Chats/ChatRecargaMesa";
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mesa: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            let htmlResultados = data;
            msjChat.innerHTML = msjChat.innerHTML + htmlResultados;
            timeoutChat = setTimeout(bucleChat, 5000);
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}
function enviarMensaje() {

    let urlAJAX = "/TFG/Chats/enviarmensajeMesa?mesa=" + chatM;

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mensaje: mensajeInput.value},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            mensajeInput.value = "";
            clearTimeout(timeoutChat);
            recargaChat();
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
            mensajeInput.value = "";
        }
    });
}

function enviarTirada() {

    let urlAJAX = "/TFG/Chats/enviartiradaMesa?mesa=" + chatM;

    let SDados = document.getElementById("SDados").value;

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {dado: SDados},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            clearTimeout(timeoutChat);
            recargaChat();
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

/////////////////////////////////////
///////////////Descriptor////////////
/////////////////////////////////////

document.getElementById("formDescriptor").addEventListener("submit", function (event) {
    event.preventDefault(); 
    clearTimeout(timeoutDescriptor);
    recargaDescriptor();
});


function recargaDescriptor() {

    let descriptorChat = document.getElementById('infoDescriptor');

    let urlAJAX = "/TFG/Chats/ChatDescripcionMesa";
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mesa: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            descriptorChat.innerHTML = data;
            timeoutDescriptor = setTimeout(bucleDescriptor, 5000);
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

/////////////////////////////////////
/////////////Vida Actual/////////////
/////////////////////////////////////

document.getElementById("formVida").addEventListener("submit", function (event) {
    event.preventDefault();
    clearTimeout(timeoutVida);
    recargaVida();
});

function recargaVida() {

    let jugadoresChat = document.getElementById('tablaJugadores');

    let urlAJAX = "/TFG/Chats/ChatDescripcionMesa";
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mesa: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            jugadoresChat.innerHTML = data;
            timeoutVida = setTimeout(bucleVida, 5000);
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

/////////////////////////////////////
////////////////Musica///////////////
/////////////////////////////////////

function recargaMusica() {

    let musicaChat = document.getElementById('contenedorCancion');
    let musica = document.getElementById('opcionMusica').value;

    let urlAJAX = "/TFG/Chats/ChatMesaMusica";
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mesa: chatM, musica: musica},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            musicaChat.innerHTML = data;
            timeoutMusica = setTimeout(bucleMusica, 5000);
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

/////////////////////////////////////
////////////////Bucles///////////////
/////////////////////////////////////

function bucleChat() {
    try {
        recargaChat();
    } catch (error) {
        console.error('Error en la recarga del chat:', error);
    }
}

function bucleDescriptor() {
    try {
        recargaDescriptor();
    } catch (error) {
        console.error('Error en la recarga del descriptor:', error);
    }
}

function bucleVida() {
    try {
        recargaVida();
    } catch (error) {
        console.error('Error en la recarga de la musica:', error);
    }
}

function bucleMusica() {
    try {
        recargaMusica();
    } catch (error) {
        console.error('Error en la recarga de la musica:', error);
    }
}

/////////////////////////////////////
////////////////Inicio///////////////
/////////////////////////////////////

cargaChat();

timeoutChat = setTimeout(bucleChat, 5000);
timeoutDescriptor = setTimeout(bucleDescriptor,5000);
timeoutVida = setTimeout(bucleVida, 5000);
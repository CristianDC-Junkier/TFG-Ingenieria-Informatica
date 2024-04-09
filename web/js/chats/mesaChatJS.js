
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
    console.log(chatM);
    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {busqueda: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            console.log(data);
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
        data: {busqueda: chatM},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            let htmlResultados = data;
            msjChat.innerHTML = msjChat.innerHTML + htmlResultados;
            timeoutRecarga = setTimeout(bucle, 5000);
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
            //clearTimeout(timeoutRecarga);
            //recargaChat();
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
            //clearTimeout(timeoutRecarga);
            //recargaChat();
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

function bucle() {
    try {
        recargaChat();
    } catch (error) {
        console.error('Error en la recarga del chat:', error);
    }
}

cargaChat();

//timeoutRecarga = setTimeout(bucle, 5000);
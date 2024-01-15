

var mensajeInput = document.getElementById("mensajeInput");
var chatP = "-1";

function remostrarChat() {

    let tabla = document.getElementById('TablaChat');

    urlAJAX = "/TFG/ControladorPeticionesAJAX/ChatAmigos";

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {busqueda: chatP},
        dataType: "text",
        success: function (data) {
            let htmlResultados = data;
            // Limpiar el contenido actual de la tabla
            tabla.innerHTML = '';
            // Insertar el nuevo HTML en el contenedor
            tabla.innerHTML = htmlResultados;
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

function mostrarChat() {

    let chat = document.getElementById('chatAmigos');
    chat.style.display = (chat.style.display === "block") ? "none" : "block";

    if (chat.style.display === "block") {
        remostrarChat();
        recargaChat();
    } else {
        chatP = "-1";
    }
}

function recargaChat() {

    let msjChat = document.getElementById('MensajesChat');

    let urlAJAX = "/TFG/ControladorPeticionesAJAX/ChatRecarga";

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {busqueda: chatP},
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
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

function cambiarChat(valor) {

    chatP = valor;
    remostrarChat();
    recargaChat();
}

function enviarMensaje() {

    let urlAJAX = "/TFG/Chats/enviarmensaje?amigo=" + chatP;

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mensaje: mensajeInput.value},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            //console.log("Mensaje Enviado:", data);
            mensajeInput.value = "";
        },
        error: function (error) {
            //console.error("Error en la solicitud AJAX:", error);
            mensajeInput.value = "";
        }
    });
}

function enviarTirada() {

    let urlAJAX = "/TFG/Chats/enviartirada?amigo=" + chatP;

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {mensaje: "20"},
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            //console.log("Mensaje Enviado:", data);
        },
        error: function (error) {
            //console.error("Error en la solicitud AJAX:", error);
        }
    });
}

  function manejarCambioDeTamaño() {
    let anchoVentana = window.innerWidth;
    
    let chat = document.getElementById('chatAmigos');

    if (anchoVentana <= 400) {
      chat.style.display = 'none';
    }
  }


setInterval(function() {
    let chatActual = document.getElementById('chatActual');


    if (chatActual !== null) {
        setInterval(function() {
            try {
                recargaChat();
            } catch (error) {
                console.error('Error en la recarga del chat:', error);
            }
        }, 1000);
    }
}, 1000);

  window.addEventListener('resize', manejarCambioDeTamaño);
  manejarCambioDeTamaño();


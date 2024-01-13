
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

let chatActual = document.getElementById('chatActual');

if (chatActual !== null) {
    setInterval(recargaChat(), 1000);
}



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <div id = "chatAmigos">
        <div class="contenedorChatAmigos">
            <div class="contenedorMensajesAmigos" id ="MensajesChat">
            </div>
            <div class="contenedorlistaAmigos" id ="TablaChat">
                <table>
                    <tr>
                        <td>Nombre1</td>
                        <td>Apellido1</td>
                    </tr>
                    <tr>
                        <td>Nombre2</td>
                        <td>Apellido2</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="contenedorEnviarMensajeAmigo">
            <form action="/TFG/Chats/mensajeamigo" method="POST">
                <input type="text" maxlength="255" id="mensajeInput" name="mensaje" placeholder="Enviar mensaje...">
                <button id="botonEnviar" type="button" onclick="enviarMensaje()">Enviar</button>
                <button id="IDadoButton" type="button" onclick="enviarTirada()"><img id="IDado" src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
            </form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/TFG/js/mostrarChatJS.js"></script>
</html>

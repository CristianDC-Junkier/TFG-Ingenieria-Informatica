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
                </table>
            </div>
        </div>
        <div class="contenedorEnviarMensajeAmigo">
            <form action="/TFG/Chats/mensajeamigo" method="POST">
                <input type="text" maxlength="255" id="mensajeInput" name="mensaje" placeholder="Enviar mensaje...">
                <button id="botonEnviar" type="button" onclick="enviarMensaje()">Enviar</button>
                <select id="SDados">
                    <option value="4">Dado-4</option>
                    <option value="6">Dado-6</option>
                    <option value="8">Dado-8</option>
                    <option value="10">Dado-10</option>
                    <option value="12">Dado-12</option>
                    <option value="20">Dado-20</option>
                    <option value="100">Dado-100</option>
                </select>
                <button id="IDadoButton" type="button" onclick="enviarTirada()"><img id="IDado" src="/TFG/img/iconos/d20White.png" alt="alt"/></button>
            </form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/TFG/js/mostrarChatJS.js"></script>
</html>

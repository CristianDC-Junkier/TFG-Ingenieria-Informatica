<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title class="titulosPag">Guidance4\Mesas\Mesa\Chat</title>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesaCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/listaUsuariosenMesaCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/comunMesasCss.css"/>
        <style>
            #chat-container {
                display: flex;
                flex-direction: row-reverse;
                border: 1px solid #ccc;
                margin: 10px;
                padding: 10px;
            }

            #right-column {
                display: flex;
                flex-direction: column;
                width: 40%;
            }

            #table-container {
                border: 1px solid #000;
                margin: auto;
                padding: 10px;
            }

            #button-container {
                display: flex;
                flex-wrap: wrap;
                gap: 5px;
                margin-left: 20px;
            }

            .button {
                flex-basis: calc(33.33% - 5px);
                margin-bottom: 10px;
            }

            #message-box {
                flex: 1;
                height: 600px; /* Aumentado la altura del contenedor de mensajes */
                width: 100%; /* Aumentado el ancho del message-box */
                overflow-y: auto;
                border: 1px solid #000;
                margin-left: 10px;
                padding: 10px;
            }

            #input-container {
                display: flex;
                margin-top: 10px;
            }

            #message-input {
                flex: 1;
                padding: 5px;
            }

            #send-button {
                margin-left: 10px;
                padding: 5px 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main class="mainMesa">
            <div class="cajaGeneral">
                <div class="cajaMesa">
                    <div id="chat-container">
                        <div id="right-column">
                            <div id="table-container">
                                <table border="1">
                                    <tr>
                                        <td>Jugador</td>
                                        <td>Personaje</td>
                                        <td>Clase</td>
                                        <td>Nivel</td>
                                        <td>Detalles</td>
                                    </tr>
                                    <tr>
                                        <td>Fila 1</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Detalles</td>
                                    </tr>
                                    <tr>
                                        <td>Fila 2</td>
                                        <td>Más datos</td>
                                        <td>Más datos</td>
                                        <td>Más datos</td>
                                        <td>Detalles</td>
                                    </tr>
                                    <tr>
                                        <td>Fila 3</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Detalles</td>
                                    </tr>
                                    <tr>
                                        <td>Fila 4</td>
                                        <td>Más datos</td>
                                        <td>Más datos</td>
                                        <td>Más datos</td>
                                    </tr>
                                    <tr>
                                        <td>Fila 5</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Datos</td>
                                        <td>Detalles</td>
                                    </tr>
                                </table>
                            </div>
                            <div id="button-container">
                                <button class="button">Lanzar D4</button>
                                <button class="button">Lanzar D6</button>
                                <button class="button">Lanzar D8</button>
                                <button class="button">Lanzar D10</button>
                                <button class="button">Lanzar D12</button>
                                <button class="button">Lanzar D100</button>
                                <button class="button">Lanzar D20</button>
                            </div>
                        </div>
                        <div id="message-box">
                        </div>
                    </div>
                    <div id="input-container">
                        <input type="text" id="message-input" placeholder="Escribe tu mensaje...">
                        <button id="send-button">Enviar</button>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>
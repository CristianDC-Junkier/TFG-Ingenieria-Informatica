<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Recuperar_Contraseña</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/recuperardatosCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRecuperar">
                <h2 class="TitulosFormulario">Recuperar Contraseña</h2>
                <form>
                    <div>
                        <div>
                            <label for="userRC" id="nombreUsuarioRC">Nombre de Usuario:</label>
                            <input type="text" id="userRC" name="nombre_usuario_rc" onkeyup="realizarBusqueda('userRC', 'userRCInput')" required/>
                            <span id="userRCInput">✖</span>
                        </div>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.href = '/TFG/Formularios/iniciosesion'" value="Volver">
                        <input id="botonEnviar" type="submit" value="Enviar">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
    </body>
</html>

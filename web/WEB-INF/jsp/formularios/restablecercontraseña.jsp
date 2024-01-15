<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Restablecer_Contraseña</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/recuperardatosCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRecuperar">
                <h2 class="TitulosFormulario">Restablecer Contraseña</h2>
                <form>
                    <div>
                        <div>
                            <label for="passwordRC" id="contrasenaRC">Contraseña:</label>
                            <input type="text" id="passwordRC" name="contrasena_usuario_rc" required/>
                            <span class="validity"></span>
                        </div>
                        <div>
                            <label for="passwordrpRC" id="contrasenarpRC">Repetir Contraseña:</label>
                            <input type="text" id="passwordrpRC" name="contrasenarp_usuario_rc" 
                                   onchange="realizarBusquedaContrasenas('passwordrpRC', 'passwordrpRCoInput', 'passwordRC')"required/>
                            <span id="passwordrpRCoInput">✖</span>
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

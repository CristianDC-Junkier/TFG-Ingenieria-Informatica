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
                <h2 class="TitulosFormulario">Restablecer Contraseñaa</h2>
                <form>
                    <div>
                        <div>
                            <label for="passwordRC" id="contraseÃ±aRC">Contraseña:</label>
                            <input type="text" id="passwordRC" name="contraseña_usuario_rc" required/>
                            <span class="validity"></span>
                        </div>
                        <div>
                            <label for="passwordrpRC" id="contraseÃ±arpRC">Repetir Contraseña:</label>
                            <input type="text" id="passwordrpRC" name="contraseñarp_usuario_rc" required/>
                            <span class="validity"></span>
                        </div>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.href = '/TFG/Formularios/iniciosesion'" value="Volver">
                        <input id="botonEnviar" type="submit" value="Enviar">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    </body>
</html>

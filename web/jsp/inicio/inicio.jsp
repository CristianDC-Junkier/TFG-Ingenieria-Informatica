<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/TFG/css/inicioCss.css"/>
        <title>Guidance4/Inicio</title>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo" /> </h1>
        </header>
        <jsp:include page="/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Inicio</h2>
            <div class="BloqueInicio">
                <div class="EleccionInicio">
                    <h2>¡Bienvenido de nuevo!</h2>
                    <p>Inicia sesión para poder disfrutar todas las funciones</p>
                    <button class="botonInicioSesion" onclick="location.href = '/TFG/jsp/formularios/iniciosesion.jsp'">Iniciar Sesión</button>
                    <p>¿Aún no tienes cuenta?<br>Únete fácilmente:</p>
                    <button class="botonRegistro" onclick="location.href = '/TFG/jsp/formularios/registro.jsp'">Registrarse</button>
                </div>
                <div class="FotosTextoInicio">
                    <div class="FotosInicio">
                        <img class="FotoInicio1" src="/TFG/img/Guide4.bmp" alt="alt"/>
                        <img class="FotoInicio2" src="/TFG/img/luzpng.png" alt="alt"/>
                    </div>
                    <div class="TextoInicio">
                        <p><button class="botonIntroducción" onclick="location.href = '/TFG/jsp/inicio/introduccion.jsp'"> Infórmate aquí</button>Qué es Dungeons & Dragons y como se juega</p>
                        <hr style="margin-left: -10px;">
                        <p>¿Necesitas algo más?, No dudes en descargar el manual para todo</p>
                        <a href="/TFG/manual/ManualCasiTodo.pdf" download="Manual Casi Todo.pdf">Descargar archivo</a>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
        </footer>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

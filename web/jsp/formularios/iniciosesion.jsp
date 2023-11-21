<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Guidance4\Iniciar_Sesión</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/iniciosesionCss.css"/>   
    </head>

    <body>
        <main class="contenedorFormulario">
            <div class="bloqueInicioSesion">
                <h2 class="TitulosFormulario">Inicio de Sesión</h2>
                <form id = formInicioSesion action="/TFG/Usuarios/iniciarSesion" method="POST">
                    <div>
                        <div>
                            <label for="nameIden" id="nombreUsuarioIden">Nombre de Usuario:</label>
                            <input type="text" id="nameIden" name="nombre_usuario" required/>
                        </div>
                        <div>
                            <label for="passwordIden" id="contraUsuarioIden">Contraseña:</label>
                            <input type="password" id="passwordIden" name="usuario_contrasena" required>
                        </div>
                        <c:if test="${requestScope.msj!=null}">
                            <div>
                                ${requestScope.msj}
                            </div>
                        </c:if>
                        <div>
                            <p>¿Olvidaste tu usuario o tu contraseña?</p>
                            <input type="button" onclick="location.href = './usuarioperdido.jsp'"
                                   id="recuperarUsuario" value="Recuperar Usuario">
                            <input type="button" onclick="location.href = './contraseñaperdida.jsp'"
                                   id="recuperarContrasena" value="Recuperar Contraseña">
                        </div>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.href = '/TFG/jsp/inicio/inicio.jsp'" value="Volver">
                        <input id="botonEnviar" type="submit" value="Identificarse">
                    </div>
                </form>
            </div>
        </main>
        <footer class="footerFormulario">
            &copy; 2023 Cristian Delgado Cruz
        </footer>
    </body>
</html>

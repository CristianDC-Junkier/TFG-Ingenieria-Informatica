
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guidance4\Perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/perfilCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/jsp/menuNav.jsp" />
        <main>
            <div class="cajaGeneral">
                <div class="cajaPersonaje">
                    <h2>PERSONAJE ACTUAL</h2>
                    <div class="personaje">
                        <div class="personaje-fotoboton">
                            <div class="personaje-foto">
                                <img src="tu-imagen-de-perfil.jpg">
                            </div>
                            <button class="cambiarPersonaje" onclick="agregarArchivo()">Cambiar personaje Actual</button>
                        </div>

                        <div class="datosPersonaje">
                            <div >Nombre de Usuario</div>
                            <div >Apodo: Usuario123</div>
                            <div >Correo: usuario@example.com</div>
                        </div>
                    </div>
                </div>

                <div class="usuarioDatos">
                    <div><span>Nombre:  </span>${sessionScope.user.nombre} </div>
                    <div><span>Nombre de usuario:  </span>${sessionScope.user.apodo}</div>
                    <div><span>Correo:  </span>${sessionScope.user.correo}</div>
                    <div><span>Teléfono:  </span>${sessionScope.user.telefono}</div>
                    <div><span>Fecha de nacimiento:  </span>${sessionScope.user.fechanac.getDate()}/${sessionScope.user.fechanac.getMonth() + 1}/${sessionScope.user.fechanac.getYear()+1900}</div>
                    <div><span>Provincia:  </span>${sessionScope.user.provincia}</div>
                    <div><span>Genero:  </span>${sessionScope.user.genero}</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Usuarios/mostrarAmigos'">Amigos</button>
                    <button class="boton" onclick="agregarArchivo()">Mesas</button>
                    <button class="boton" onclick="agregarArchivo()">Personajes</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/jsp/formularios/modificarusuario.jsp'">Modificar Datos</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Usuarios/cerrarSesion'">Salir</button>
                </div>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
        </footer>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

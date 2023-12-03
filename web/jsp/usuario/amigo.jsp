<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guidance4\Amigo</title>
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
                                <img src="/TFG/img/iconos/IMGNEGRO.png">
                            </div>
                            <button class="cambiarPersonaje" onclick="agregarArchivo()">Mirar sus personajes</button>
                        </div>

                        <div class="datosPersonaje">
                            <div >Nombre de Usuario</div>
                            <div >Apodo: Usuario123</div>
                            <div >Correo: usuario@example.com</div>
                        </div>
                    </div>
                </div>

                <div class="usuarioDatos">
                    <div><span>Nombre:  </span>${requestScope.amigo.nombre} </div>
                    <div><span>Nombre de usuario:  </span>${requestScope.amigo.apodo}</div>
                    <div><span>Fecha de nacimiento:  </span>${requestScope.amigo.fechanac.getDate()}/${requestScope.amigo.fechanac.getMonth() + 1}/${requestScope.amigo.fechanac.getYear()+1900}</div>
                    <div><span>Provincia:  </span>${requestScope.amigo.provincia}</div>
                    <div><span>Genero:  </span>${requestScope.amigo.genero}</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Usuarios/mostrarAmigos'">Atras</button>
                    <button class="boton" onclick="agregarArchivo()">Mesas</button>
                    <button class="boton" onclick="agregarArchivo()">Personajes</button>
                    <button class="botonfinal" onclick="location.href = '/TFG/Usuarios/eliminarAmigo?amigo=${requestScope.amigo.apodo}'">Eliminar Amigo</button>
                </div>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
        </footer>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

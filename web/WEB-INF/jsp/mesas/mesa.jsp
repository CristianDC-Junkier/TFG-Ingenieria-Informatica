<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Mesas\Mesa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/perfilCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
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
                    <div><span>Titulo:  </span>${requestScope.mesa.titulo}</div>
                    <div><span>Creador:  </span>${requestScope.mesa.creador}</div>
                    <div><span>Comunidad:  </span>${requestScope.mesa.comunidad}</div>
                    <div><span>Descripcion:  </span>${requestScope.mesa.descripcion}</div>
                </div>
                <div class="botones">
                    <button class="boton" onclick="location.href = '/TFG/Mesas/mostrarMesasUsuario'">Atras</button>
                    <button class="boton" onclick="agregarArchivo()">Usuarios</button>
                    <c:choose> 
                        <c:when test="${requestScope.rol == 'Lider'}">
                            <button class="boton" onclick="location.href = '/TFG/Formularios/modificarmesa?id=${requestScope.mesa.id}'">Modificar Mesa</button>
                            <button class="botonfinal" onclick="mostrarRecuadro()">Borrar Mesa</button>
                        </c:when>
                        <c:otherwise>
                            <button class="botonfinal" onclick="mostrarRecuadro()">Salir</button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:choose> 
                    <c:when test="${requestScope.rol == 'Lider'}">
                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                            <div class="contenidoRecuadro">
                                <div class="tituloRecuadro">¿Esta seguro que quiere Eliminarla?
                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                </div>
                                <hr>
                                <button class="botonDentro" onclick="location.href = '/TFG/Mesas/eliminarMesa?id=${requestScope.mesa.id}'">Si</button>
                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                            <div class="contenidoRecuadro">
                                <div class="tituloRecuadro">¿Esta seguro que quiere Salir?
                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                </div>
                                <hr>
                                <button class="botonDentro" onclick="location.href = '/TFG/Mesas/salirdeMesa?id=${requestScope.mesa.id}'">Si</button>
                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
        <footer>
            &copy; 2023 Cristian Delgado Cruz
        </footer>
        <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

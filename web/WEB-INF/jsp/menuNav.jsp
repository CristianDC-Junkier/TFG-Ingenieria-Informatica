<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
</head>
<header>
    <h1> <img id="Logo" class="logo" src="/TFG/img/dnd-bannerWhite.jpg" alt="Logo" /> </h1>
</header>
<nav class="menus">
    <div class="menuHorizontal">
        <div class = "barraHorizontal">
            <div class="barraHorizontalDrop">
                <div class = "barraHorizontalContenido">
                    <img src="/TFG/img/iconos/CasaWhite.png" alt="alt" id="ICasa"/>  Inicio</div>
                <div class="barraHorizontalContenidoDrop">
                    <a href="/TFG/Principal/inicio">Inicio</a>
                    <a href="/TFG/Principal/introduccion">Introducción</a>
                </div>
            </div>
            <div class="barraHorizontalDrop">
                <div class = "barraHorizontalContenido">
                    <img src="/TFG/img/iconos/ReglasWhite.png" alt="alt" id="IReglas"/>  Reglas&nbsp;</div>
                <div class="barraHorizontalContenidoDrop">
                    <a href="/TFG/Reglas/comocrearpersonajes">Diseño de Personajes</a>
                    <a href="/TFG/Reglas/interpretacion">Interpretar</a>
                    <a href="/TFG/Reglas/combate">Combate</a>
                    <a href="/TFG/Reglas/turnos">Turnos</a>
                    <a href="/TFG/Reglas/desafios">Valores de Desafío</a>
                    <a href="/TFG/Reglas/misiones">Misiones</a>
                    <a href="/TFG/Reglas/objetosmagicos">Objetos Mágicos</a>
                    <a href="/TFG/Reglas/precios">Precios</a>
                </div>
            </div>
            <div class="barraHorizontalDrop">
                <div class = "barraHorizontalContenido">
                    <img src="/TFG/img/iconos/LupaWhite.png" alt="alt" id="ILupa"/>  Explorar</div>
                <div class="barraHorizontalContenidoDrop">
                    <a href="/TFG/Explorar/clases">Clases</a>
                    <a href="/TFG/Explorar/razas">Razas</a>
                    <a href="/TFG/Explorar/trasfondos">Trasfondos</a>
                    <a href="/TFG/Explorar/dotes">Dotes</a>
                    <a href="/TFG/Explorar/estados">Estados</a>
                    <a href="/TFG/Explorar/equipo">Equipo</a>
                    <a href="/TFG/Explorar/hechizos">Hechizos</a>
                    <a href="/TFG/Explorar/monstruos">Monstruos</a>

                </div>
            </div>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <div class="barraHorizontalDrop">
                        <div class = "barraHorizontalContenido">
                            <img src="/TFG/img/iconos/PersonajesWhite.png" alt="alt" id="IPersonaje"/>  Personajes</div>
                        <div class="barraHorizontalContenidoDrop">
                            <a href= "/TFG/Personajes/personajes">Explorar</a>
                            <a href = "/TFG/Personajes/personajesAmigos">De Amigos</a>
                            <a href= "/TFG/Personajes/personajesPerfil">Mis Personajes</a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <a class="barraHorizontalContenido" href="/TFG/Personajes/personajes">
                        <img src="/TFG/img/iconos/PersonajesWhite.png" alt="alt" id="IPersonaje"/>  Personajes</a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${sessionScope.user != null}">
                <div class="barraHorizontalDrop">
                    <c:choose>
                        <c:when test="${sessionScope.peticiones != null}">
                            <div class = "barraHorizontalContenido tienesPeticion">
                                <img src="/TFG/img/iconos/AmigosWhite.png" alt="alt" id="IAmigos"/>  Amigos</div>
                            <div class="barraHorizontalContenidoDrop">
                                <a href="/TFG/Usuarios/mostrarUsuarios">Explorar</a>
                                <a href="/TFG/Usuarios/mostrarPeticionesRecibidas" class="tienesPeticion">Peticiones</a>
                                <a href="/TFG/Usuarios/mostrarAmigos">Mis Amigos</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class = "barraHorizontalContenido">
                                <img src="/TFG/img/iconos/AmigosWhite.png" alt="alt" id="IAmigos"/>  Amigos</div>
                            <div class="barraHorizontalContenidoDrop">
                                <a href="/TFG/Usuarios/mostrarUsuarios">Explorar</a>
                                <a href="/TFG/Usuarios/mostrarPeticionesRecibidas">Peticiones</a>
                                <a href="/TFG/Usuarios/mostrarAmigos">Mis Amigos</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <div class="barraHorizontalDrop">
                    <div class = "barraHorizontalContenido">
                        <img src="/TFG/img/iconos/MesaWhite.png" alt="alt" id="IMesa"/>  Mesas</div>
                    <div class="barraHorizontalContenidoDrop">
                        <a href="/TFG/Formularios/crearmesa">Crear Mesa</a>
                        <a href="/TFG/Mesas/mostrarMesas">Explorar</a>
                        <a href="/TFG/Mesas/mostrarMesasUsuario">Mis Mesas</a>
                    </div>
                </div>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <a class="BarraHorizontalContenidoFinal" href="/TFG/Usuarios/perfil">
                        <img src="/TFG/img/iconos/PerfilWhite.png" alt="alt" id="IPerfil"/>  ${sessionScope.user.apodo}</a>
                    </c:when>
                    <c:otherwise>
                    <a class="BarraHorizontalContenidoFinal" href="/TFG/Formularios/iniciosesion">
                        <img src="/TFG/img/iconos/PerfilWhite.png" alt="alt" id="IPerfil"/>  Iniciar Sesión</a>
                    </c:otherwise>
                </c:choose>
        </div>
    </div>
    <div class="menuVertical">
        <div class="barraHorizontal">
            <button class="botonMenuVertical">Menu</button>
        </div>
        <div class = "barraVertical">
            <ul class="barraVerticalUno">
                <li class="unoDrop primero">
                    <div class = "barraVerticalContenido">
                        Inicio
                    </div>
                    <ul class="barraVerticalDos">
                        <li><a href="/TFG/Principal/inicio">Inicio</a></li>
                        <li><a href="/TFG/Principal/introduccion">Introducción</a></li>
                    </ul>
                </li>
                <li class="unoDrop segundo">
                    <div class = "barraVerticalContenido">
                        Reglas
                    </div>
                    <ul class="barraVerticalDos">
                        <li><a href="/TFG/Reglas/comocrearpersonajes">D. de Personajes</a></li>
                        <li><a href="/TFG/Reglas/interpretacion">Interpretación</a></li>
                        <li><a href="/TFG/Reglas/combate">Combate</a></li>
                        <li><a href="/TFG/Reglas/turnos">Turnos</a></li>
                        <li><a href="/TFG/Reglas/desafios">Valores de Desafío</a></li>
                        <li><a href="/TFG/Reglas/misiones">Misiones</a></li>
                        <li><a href="/TFG/Reglas/objetosmagicos">Objetos Mágicos</a></li>
                        <li><a href="/TFG/Reglas/precios">Precios</a></li>
                    </ul>
                </li>
                <li class="unoDrop tercero">
                    <div class = "barraVerticalContenido">
                        Explorar
                    </div>
                    <ul class="barraVerticalDos">
                        <li><a href="/TFG/Explorar/clases">Clases</a></li>
                        <li><a href="/TFG/Explorar/razas">Razas</a><li>
                        <li><a href="/TFG/Explorar/trasfondos">Trasfondos</a></li>
                        <li><a href="/TFG/Explorar/dotes">Dotes</a></li>
                        <li><a href="/TFG/Explorar/estados">Estados</a></li>
                        <li><a href="/TFG/Explorar/equipo">Equipo</a></li>
                        <li><a href="/TFG/Explorar/hechizos">Hechizos</a></li>
                        <li><a href="/TFG/Explorar/monstruos">Monstruos</a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <li class="unoDrop cuarto">
                            <div class = "barraVerticalContenido">
                                <a href="/TFG/Personajes/personajes">Personajes</a>
                            </div>
                            <ul class="barraVerticalDos">
                                <li><a href="/TFG/Personajes/personajes">Explorar</a></li>
                                <li><a href = "/TFG/Personajes/personajesAmigos">De Amigos</a></li>
                                <li><a href="/TFG/Personajes/personajesPerfil">Mis Personajes</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a class="barraVerticalContenido" href="/TFG/Personajes/personajes">Personajes</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:if test="${sessionScope.user != null}">
                    <li class="unoDrop cuarto">
                        <div class = "barraVerticalContenido">Amigos</div>
                        <ul class="barraVerticalDos">
                            <li><a href="/TFG/Usuarios/mostrarUsuarios">Explorar</a></li>
                            <li><a href="/TFG/Usuarios/mostrarPeticionesRecibidas">Peticiones</a></li>
                            <li><a href="/TFG/Usuarios/mostrarAmigos">Mis Amigos</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="unoDrop cuarto">
                        <div class = "barraVerticalContenido">Mesas</div>
                        <ul class="barraVerticalDos">
                            <li><a href="/TFG/Formularios/crearmesa">Crear Mesa</a></li>
                            <li><a href="/TFG/Mesas/mostrarMesas">Explorar Mesas</a></li>
                            <li><a href="/TFG/Mesas/mostrarMesasUsuario">Mis Mesas</a></li>
                        </ul>
                    </li>
                </c:if>
                <li>
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <div class = "barraVerticalContenido">
                                <a href="/TFG/Usuarios/perfil">${sessionScope.user.apodo}</a>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <div class = "barraVerticalContenido">
                                <a href="/TFG/Formularios/iniciosesion">Iniciar Sesión</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
    </head>
    <body>
        <nav class="menus">
            <div class="menuHorizontal">
                <div class = "barraHorizontal">
                    <div class="barraHorizontalDrop">
                        <Menu class = "barraHorizontalContenido" href=inicio>
                            <a href="/TFG/jsp/inicio/inicio.jsp"><img src="/TFG/img/iconos/casa.png" alt="alt"/>Inicio</a></Menu>
                        <div class="barraHorizontalContenidoDrop">
                            <a href="/TFG/jsp/inicio/inicio.jsp">Inicio</a>
                            <a href="/TFG/jsp/inicio/introduccion.jsp">Introducción</a>
                        </div>
                    </div>
                    <div class="barraHorizontalDrop">
                        <Menu class = "barraHorizontalContenido" href=reglas>
                            <img src="/TFG/img/iconos/casa.png" alt="alt"/>Reglas&nbsp;</Menu>
                        <div class="barraHorizontalContenidoDrop">
                            <a href="/TFG/jsp/reglas/comocrearpersonajes.jsp">Diseño de Personajes</a>
                            <a href="/TFG/jsp/reglas/interpretacion.jsp">Interpretar</a>
                            <a href="/TFG/jsp/reglas/combate.jsp">Combate</a>
                            <a href="/TFG/jsp/reglas/turnos.jsp">Turnos</a>
                            <a href="/TFG/jsp/reglas/desafios.jsp">Valores de Desafío</a>
                            <a href="/TFG/jsp/reglas/misiones.jsp">Misiones</a>
                            <a href="/TFG/jsp/reglas/objetosmagicos.jsp">Objetos Mágicos</a>
                            <a href="/TFG/jsp/reglas/precios.jsp">Precios</a>
                        </div>
                    </div>
                    <div class="barraHorizontalDrop">
                        <Menu class = "barraHorizontalContenido" href=explorar>
                            <img src="/TFG/img/iconos/lupablanca.png" alt="alt"/>Explorar</Menu>
                        <div class="barraHorizontalContenidoDrop">
                            <a href="/TFG/jsp/explorar/clases.jsp">Clases</a>
                            <a href="/TFG/jsp/explorar/razas.jsp">Razas</a>
                            <a href="/TFG/jsp/explorar/trasfondos.jsp">Trasfondos</a>
                            <a href="/TFG/jsp/explorar/dotes.jsp">Dotes</a>
                            <a href="/TFG/jsp/explorar/estados.jsp">Estados</a>
                            <a href="/TFG/jsp/explorar/equipo.jsp">Equipo</a>
                            <a href="/TFG/jsp/explorar/hechizos.jsp">Hechizos</a>
                            <a href="/TFG/jsp/explorar/monstruos.jsp">Monstruos</a>

                        </div>
                    </div>
                    <a class="barraHorizontalContenido" href="/TFG/jsp/articulos.jsp">Personajes</a>
                    <c:choose>
                        <c:when test="${requestScope.user != null}">
                            <a class="BarraHorizontalContenidoFinal" href="/TFG/jsp/formularios/iniciosesion.jsp">
                                <img src="/TFG/img/iconos/usuario.png" alt="alt"/>${requestScope.user.apodo}</a>
                            </c:when>
                            <c:otherwise>
                            <a class="BarraHorizontalContenidoFinal" href="/TFG/jsp/formularios/iniciosesion.jsp">
                                <img src="/TFG/img/iconos/usuario.png" alt="alt"/>Iniciar Sesión</a>
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
                            <div class = "barraVerticalContenido" href="inicio">
                                <a href="/TFG/jsp/inicio.jsp">Inicio</a>
                            </div>
                            <ul class="barraVerticalDos">
                                <li><a href="/TFG/jsp/inicio.jsp">Inicio</a></li>
                                <li><a href="/TFG/jsp/introduccion.jsp">Introducción</a></li>
                            </ul>
                        </li>
                        <li class="unoDrop segundo">
                            <div class = "barraVerticalContenido"href="reglas">
                                Reglas
                            </div>
                            <ul class="barraVerticalDos">
                                <li><a href="/TFG/jsp/reglas/comocrearpersonajes.jsp">D. de Personajes</a></li>
                                <li><a href="/TFG/jsp/reglas/interpretacion.jsp">Interpretación</a></li>
                                <li><a href="/TFG/jsp/reglas/combate.jsp">Combate</a></li>
                                <li><a href="/TFG/jsp/reglas/turnos.jsp">Turnos</a></li>
                                <li><a href="/TFG/jsp/reglas/desafios.jsp">Valores de Desafío</a></li>
                                <li><a href="/TFG/jsp/reglas/misiones.jsp">Misiones</a></li>
                                <li><a href="/TFG/jsp/reglas/objetosmagicos.jsp">Objetos Mágicos</a></li>
                                <li><a href="/TFG/jsp/reglas/precios.jsp">Precios</a></li>
                            </ul>
                        </li>
                        <li class="unoDrop tercero">
                            <div class = "barraVerticalContenido" href="explorar">
                                Explorar
                            </div>
                            <ul class="barraVerticalDos">
                                <li><a href="/TFG/jsp/explorar/clases.jsp">Clases</a></li>
                                <li><a href="/TFG/jsp/explorar/razas.jsp">Razas</a><li>
                                <li><a href="/TFG/jsp/explorar/trasfondos.jsp">Trasfondos</a></li>
                                <li><a href="/TFG/jsp/explorar/dotes.jsp">Dotes</a></li>
                                <li><a href="/TFG/jsp/explorar/estados.jsp">Estados</a></li>
                                <li><a href="/TFG/jsp/explorar/equipo.jsp">Equipo</a></li>
                                <li><a href="/TFG/jsp/explorar/hechizos.jsp">Hechizos</a></li>
                                <li><a href="/TFG/jsp/explorar/monstruos.jsp">Monstruos</a></li>
                            </ul>
                        </li>
                        <li>
                            <div class = "barraVerticalContenido" href="personajes">
                                <a href="/TFG/jsp/articulos.jsp">Personajes</a>
                            </div>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${requestScope.user != null}">
                                    <div class = "barraVerticalContenido" href="iniciarsesion">
                                        <a href="/TFG/jsp/formularios/iniciosesion.jsp">${requestScope.user}</a>
                                    </div>

                                </c:when>
                                <c:otherwise>
                                    <div class = "barraVerticalContenido" href="iniciarsesion">
                                        <a href="/TFG/jsp/formularios/iniciosesion.jsp">Iniciar Sesión</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>

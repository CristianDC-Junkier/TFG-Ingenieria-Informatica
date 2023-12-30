<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Crear_Mesa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearmesaCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroMesa">
                <h2 class="TitulosFormulario">Crear Mesa</h2>
                <form id = formRegistro action="/TFG/Mesas/crearMesa" method="POST">
                    <c:choose>
                        <c:when test="${requestScope.mesasTotales != 5}">
                            <div>
                                <div>
                                    <label for="tituloMesa" id="tituloMesa">Título:</label>
                                    <input type="text" id="tittleMesa" name="mesa_titulo" required/>
                                    <span class="validity"></span>
                                </div>
                                <div>
                                    <label for="passwordMesa" id="contraMesa">Contraseña:</label>
                                    <input type="password" id="passwordMesa" name="mesa_contrasena">
                                </div>
                                <div>
                                    <label for="numUsuarios" id="numUsuariosMesa">Jugadores:</label>
                                    <input type="number" id="numUsuarios" min="2" max="5" name="mesa_jugadores" required/>
                                    <span class="validity"></span>
                                </div>
                                <div>
                                    <label for="comunidadMesa" id="comunidadMesa">Comunidad:</label>
                                    <select required name="comunidad" id="comunityMesa" >
                                        <option value="Andalucía">Andalucía</option>
                                        <option value="Aragón">Aragón</option>
                                        <option value="Asturias">Asturias</option>
                                        <option value="Islas Baleares">Islas Baleares</option>
                                        <option value="Canarias">Canarias</option>
                                        <option value="Cantabria">Cantabria</option>
                                        <option value="Castilla y León">Castilla y León</option>
                                        <option value="Castilla-La Mancha">Castilla-La Mancha</option>
                                        <option value="Cataluña">Cataluña</option>
                                        <option value="Extremadura">Extremadura</option>
                                        <option value="Galicia">Galicia</option>
                                        <option value="Madrid">Madrid</option>
                                        <option value="Murcia">Murcia</option>
                                        <option value="Navarra">Navarra</option>
                                        <option value="La Rioja">La Rioja</option>
                                        <option value="País Vasco">País Vasco</option>
                                        <option value="Comunidad Valenciana">Comunidad Valenciana</option>
                                        <option value="Ceuta">Ceuta</option>
                                        <option value="Melilla">Melilla</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="desMesa" id="descripcionMesa">Descripción</label>
                                    <textarea id="desMesa" name="mesa_descripcion"></textarea>
                                </div>
                                <c:if test="${requestScope.msj!=null}">
                                    <div>
                                        ${requestScope.msj}
                                    </div>
                                </c:if>
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                                <input id="botonEnviar" type="submit" value="Crear Mesa">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                Lo siento, ya tienes demasiadas mesas creadas.
                                Borra una para poder crear otra.
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
    </body>
</html>

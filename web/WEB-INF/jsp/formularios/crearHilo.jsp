<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Foro\Crear_Hilo</title>
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
                <form id = formRegistro action="/TFG/Foro/crearHilo" method="POST">
                    <c:choose>
                        <c:when test="${requestScope.numHilos != 3}">
                            <div>
                                <div>
                                    <label for="tittleHilo" id="tituloHilo">Título:</label>
                                    <input type="text" id="tittleHilo" name="titulo" onkeyup="realizarBusqueda('tittleHilo', 'tittleHiloInput')" required/>
                                    <span  id="tittleHiloInput">✖</span>
                                </div>
                                <div>
                                    <label for="themeHilo" id="temaHilo">Tema:</label>
                                    <select required name="tema" id="themeHilo" >
                                        <c:forEach var="tema" items="${listaTemas}" varStatus="status">
                                            <option value="${tema.id}" selected>${tema.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label for="secHilo" id="seccionHilo">Sección:</label>
                                    <select required name="seccion" id="secHilo" >
                                        <c:forEach var="seccion" items="${listaSecciones}" varStatus="status">
                                            <option value="${seccion.id}" selected>${seccion.titulo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label for="msjInicialHilo" id="mensajeInicialHilo">Mensaje Inicial:</label>
                                    <textarea id="msjInicialHilo" name="mensaje"></textarea>
                                </div>
                                <c:if test="${requestScope.msj!=null}">
                                    <div>
                                        ${requestScope.msj}
                                    </div>
                                </c:if>
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Foro/inicio'" value="Volver">
                                <input id="botonEnviar" type="submit" value="Crear Hilo">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                Lo siento, ya tienes demasiados hilos.
                                Borra una para poder crear otra.
                            </div>
                            <div class="contenedorBotonFormulario">
                                <input id="botonVolverFormulario-Solo" type="button" onclick="location.pathname = 'TFG/Foro/inicio'" value="Volver">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
    </body>
</html>

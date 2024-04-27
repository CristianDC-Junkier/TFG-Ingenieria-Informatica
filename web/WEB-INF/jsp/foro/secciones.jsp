<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Secciones</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/temasyseccionesCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/comunForoCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos" style="margin-right:10px">Secciones<button class="botonArriba" onclick="location.href = '/TFG/Foro/inicio'">Volver</button></h2>
        <hr color="black">
        <c:if test="${sessionScope.user.admin == 1}">
            <div class="buscadorForo">
                <button class="botonArriba" onclick="mostrarRecuadro2()">Añadir</button>
                <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">Crea la Seccion 
                            <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
                        </div>
                        <form id = "formRegistro" action="/TFG/Foro/añadirSeccion" method="POST">
                            <label for="titulo">Titulo:</label>
                            <input type="text" id="nombre" name="titulo" required>
                            <label for="hilos">Hilos permitidos:</label>
                            <input type="number" id="nombre" name="hilos" min="1" max="10000" required>
                            <br>
                            <input class="botonDentro" type="submit" value="Crear">
                            <button class="botonDentro" onclick="cerrarRecuadro2()">Salir</button>
                        </form>
                    </div>
                </div>
                <button class="botonArriba" onclick="mostrarRecuadro()">Eliminar</button>
                <div class="opcionRecuadro" id="recuadro" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">¿Está seguro de que quieres Borrarlo?
                            <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                        </div>
                        <form id = "formRegistro" action="/TFG/Foro/eliminarSeccion" method="POST">
                            <select id="selectSeccion" name="seccion">
                                <c:forEach var="seccion" items="${listaSecciones}" varStatus="status">
                                    <option value="${seccion.id}">${seccion.titulo}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <input class="botonDentro" type="submit" value="Si">
                            <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="Listatera"id="Listatera">
            <div class="ListateraTipo">
                <c:forEach var="seccion" items="${listaSecciones}" varStatus="status">
                    <div class="Resumentera">
                        <div  id="Secciones" data-id="${seccion.id}">
                            <img src="/TFG/img/temasysecciones/Seccion_${status.index % 4 + 1}.bmp">
                            <div class="Contenidotera">
                                <div class="Titulotera">${seccion.titulo}</div>
                                <div class="Descripciontera">${requestScope.seccionNumero[status.index]} / ${seccion.numerohilosmax}</div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.user.admin == 1}">
                            <button class="botonArriba" onclick="mostrarRecuadro3()">Modificar Hilos totales</button>
                            <div class="opcionRecuadro" id="recuadro3" style="display: none;">
                                <div class="contenidoRecuadro">
                                    <div class="tituloRecuadro">Modifica la Seccion 
                                        <span class="cierreRecuadro" onclick="cerrarRecuadro3()">X</span>
                                    </div>
                                    <form id = "formRegistro" action="/TFG/Foro/modificarSeccion" method="POST">
                                        <input type="hidden" name="seccion" value="${seccion.id}">
                                        <label for="hilos">Hilos permitidos:</label>
                                        <input type="number" id="nombre" name="hilos" min="${seccion.numerohilosmax}" max="10000" required>
                                        <br>
                                        <input class="botonDentro" type="submit" value="Modificar">
                                        <button class="botonDentro" onclick="cerrarRecuadro3()">Salir</button>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
    <script src="/TFG/js/foro/seccionesJS.js"></script>
</body>
</html>

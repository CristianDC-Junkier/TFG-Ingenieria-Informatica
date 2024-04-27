<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Temas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/temasyseccionesCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/comunForoCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos" style="margin-right:10px">Temas <button class="botonArriba" onclick="location.href = '/TFG/Foro/inicio'">Volver</button></h2>
        <hr color="black">
        <c:if test="${sessionScope.user.admin == 1}">
            <div class="buscadorForo">
                <button class="botonArriba" onclick="mostrarRecuadro2()">Añadir</button>
                <div class="opcionRecuadro" id="recuadro2" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">Crea el Tema 
                            <span class="cierreRecuadro" onclick="cerrarRecuadro2()">X</span>
                        </div>
                        <form id = "formRegistro" action="/TFG/Foro/añadirTema" method="POST">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" required>
                            <br>
                            <input class="botonDentro" type="submit" value="Crear">
                            <button class="botonDentro" onclick="cerrarRecuadro2()">Salir</button>
                        </form>
                    </div>
                </div>
                <button  class="botonArriba" onclick="mostrarRecuadro()">Eliminar</button>
                <div class="opcionRecuadro" id="recuadro" style="display: none;">
                    <div class="contenidoRecuadro">
                        <div class="tituloRecuadro">¿Está seguro de que quieres Borrarlo?
                            <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                        </div>
                        <form id = "formRegistro" action="/TFG/Foro/eliminarTema" method="POST">
                            <select id="selectSeccion" name="tema">
                                <c:forEach var="tema" items="${listaTemas}" varStatus="status">
                                    <option value="${tema.id}">${tema.nombre}</option>
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
                <c:forEach var="tema" items="${listaTemas}" varStatus="status">
                    <div class="Resumentera" id="Temas" data-id="${tema.id}">
                        <img src="/TFG/img/temasysecciones/Tema_${status.index % 4 + 1}.bmp">
                        <div class="Contenidotera">
                            <div class="Titulotera">${tema.nombre}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div> 
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
    <script src="/TFG/js/foro/temasJS.js"></script>
</body>
</html>

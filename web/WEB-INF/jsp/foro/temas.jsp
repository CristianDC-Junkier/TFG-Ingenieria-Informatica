<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Foro\Temas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/foro/foroInicioCss.css"/>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Temas</h2>
        <hr color="black">
        <div class="ListaRazas"id="ListaRazas">
            <h3 id="Normales">Normales</h3>
            <div class="ListaRazasTipo">
                <c:forEach var="razasN" items="${listaRazasNormales}" varStatus="status">
                    <div class="ResumenRaza" id="RazasNormales" data-id="${razasN.id}">
                        <img src="${listaRazasImagenesNormales[status.index]}">
                        <div class="ContenidoRaza">
                            <div class="TituloRaza">${razasN.nombre}</div>
                            <div class="DescripcionRaza">${razasN.resumen}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>
    <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
    <script src="/TFG/js/principalJS.js"></script>
    <script src="/TFG/js/mostrarRecuadrosJS.js"></script>
</body>
</html>

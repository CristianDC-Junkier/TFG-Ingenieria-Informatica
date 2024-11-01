<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/TFG/css/colorClaroCss.css" id="ColorSeleccionado"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/principalFooterCss.css"/>
</head>
<footer>
    <div>
        <div>&copy; 2023 Cristian Delgado Cruz</div>
        <div class="color-seleccion" id="color-seleccion_tema">
            <input type="checkbox" id="Color">
            <span></span>
        </div>
        <c:if test="${sessionScope.user != null}">
            <div><a href="/TFG/Foro/inicio" id="enlaceForo">FORO</a></div>
            <div class="chat-seleccion">
                <button onclick="mostrarChat()"><img src="/TFG/img/iconos/ChatWhite.png" alt="alt" id="IChat"/></button>
            </div>
            <jsp:include page="/WEB-INF/jsp/chat.jsp" />
        </c:if>
    </div>
</footer>
<script src="/TFG/js/cambioColorJS.js"></script>


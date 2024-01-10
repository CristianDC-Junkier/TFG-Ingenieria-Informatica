<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Estados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/estadosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main> 
            <div>
                <h2 class="Titulos">Estados</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Los estados son condiciones que afectan a los personajes,
                        jugadores o no y a los monstruos.<br> 
                        Que imponen problemas en el combate o en todo los
                        aspectos de la aventura, hastaque son eliminados de una 
                        forma o de otra.</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div>
                    <c:forEach var="estado" items="${listaEstados}">
                        <div class="ResumenEstado">
                            <div class="NombreEstado">
                                <h5>${estado.nombre}</h5>
                            </div>
                            <div class="TextoEstado">
                                <p>${estado.descripcion}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

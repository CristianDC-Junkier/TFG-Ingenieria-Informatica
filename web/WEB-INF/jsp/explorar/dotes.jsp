<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Dotes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/dotesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main> 
            <div class="ResumenDotes">
                <h2 class="Titulos">Dotes</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Los Dotes pueden ser usados o no por los jugadores, el 
                        DM puede decidir si se permite usarse o no, cuantos se usan
                        y cuales combinaciones no estan permitidas, o definir
                        otros nuevos.</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div class="ListaDotes">
                    <c:out value="${listaDotes}" escapeXml="false" />
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Trasfondos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/trasfondosCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main> 
            <div>
                <h6 id="Arriba" class = "BusquedaTrasfondos">
                    <a href="#Aa" id="A">&nbsp;&nbsp;A</a>
                    <a href="#B">&nbsp;&nbsp;B</a>
                    <a href="#C">&nbsp;&nbsp;C</a>
                    <a href="#D">&nbsp;&nbsp;D</a>
                    <a href="#E">&nbsp;&nbsp;E</a>
                    <a href="#H">&nbsp;&nbsp;H</a>
                    <a href="#M">&nbsp;&nbsp;M</a>
                    <a href="#Pp" id="P">&nbsp;&nbsp;P&nbsp;</a>
                </h6>
            </div>
            <div>
                <h2 class="Titulos">Trasfondos</h2>
                <hr color="black">
                <div class="explicacionesPestañas">
                    <p>Todo lo explicado aquí solo es una guía, es decir,
                        el DM puede moldear o ajustar según su propio criterio.</p>
                    <p>Los tranfondos son una parte importante del juego, ya que
                        obligatoriamente todos los personajes deben tener uno, además
                        de que tiene que calzar siempre con su historia personal,
                        la recomendación es elegir un trasfondo conjuntamente, DM y
                        personaje, pues el trasfondo puede ser fundamental para el inicio 
                        del juego</p>
                    <p>Lo importante es divertirse.</p>
                </div>
                <div>
                    <c:forEach var="trasfondo" items="${listaTrasfondo}">
                        <div class="ResumenTrasfondo">
                            <div class="NombreTrasfondo">
                                <a href="/TFG/Explorar/trasfondo?idTrasfondo=${trasfondo.id}">
                                    <h5>${trasfondo.nombre}</h5>
                                </a>
                            </div>
                            <div class="TextoTrasfondo">
                                <a href="/TFG/Explorar/trasfondo?idTrasfondo=${trasfondo.id}">
                                    <p>${trasfondo.descripcion}</p>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
<script src="/TFG/js/principalJS.js"></script>
</body>
</html>

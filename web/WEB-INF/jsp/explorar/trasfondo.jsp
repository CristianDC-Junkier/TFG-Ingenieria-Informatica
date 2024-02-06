<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Trasfondos\Trasfondo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/trasfondoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Trasfondo ${requestScope.trasfondo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosTrasfondo">
                <div class="datosIzquierdaTrasfondo">
                    <div class="datosIzquierdaArribaTrasfondo">
                        <div class="caracteristicasTrasfondo">
                            <h3>Características</h3>
                            <ul>
                                <li>Idiomas: ${requestScope.trasfondo.idiomas}</li>
                                <li>Herramientas: ${requestScope.trasfondo.cherramientas}</li>                    
                                <li>Habilidades:${requestScope.htrasfondo1},${requestScope.htrasfondo2}.</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoTrasfondo">
                        <p>${requestScope.trasfondo.descripcion}</p>
                    </div>
                    <br><br>
                    <div class = "datosIzquierdaAbajoFinalTrasfondo"> 
                        <h3>Equipo Inicial</h3>
                        <p>${requestScope.trasfondo.equipo}</p>
                    </div>
                </div>
                <div class="datosDerechaTrasfondo">
                    <h3>Puedes elegir uno: </h3>
                    <br>
                    <c:forEach var="Rtrasfondo" items="${listaRasgosTrasfondos}">
                        <h3>Rasgo: ${Rtrasfondo.nombre}</h3>
                        <p>${Rtrasfondo.descripcion}</p>
                    </c:forEach> 
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

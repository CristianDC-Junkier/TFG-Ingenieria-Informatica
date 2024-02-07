<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Monstruos\Monstruo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/trasfondoCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Monstruo ${requestScope.monstruo.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosTrasfondo">
                <div class="datosIzquierdaTrasfondo">
                    <div class="datosIzquierdaArribaTrasfondo">
                        <div class="caracteristicasTrasfondo">
                            <h3>Características</h3>
                            <ul>
                                <li>Tipo: ${requestScope.monstruo.tipo}</li>
                                <li>Valor de Desafío: ${requestScope.monstruo.vdesafio}</li>
                                <li>Alineamiento: ${requestScope.monstruo.alineamiento}</li>
                                <li>Idiomas: ${requestScope.monstruo.idiomas}</li>
                            </ul>
                        </div>
                    </div>
                    <div class = "datosIzquierdaAbajoTrasfondo">
                        <div>
                            <h3>Valores</h3>
                            <ul>
                                <li>Puntos de golpe: ${requestScope.monstruo.pgolpe}</li>
                                <li>Clase de Armadura: ${requestScope.monstruo.carmadura}</li>
                                <li>Velocidad: ${requestScope.monstruo.velocidad}</li>
                                <li>Tamaño: ${requestScope.monstruo.tamano}</li>
                            </ul>
                        </div>
                    </div>
                    <br>
                    <div class = "datosIzquierdaAbajoFinalTrasfondo"> 
                        <div>
                            <h3>Atributos</h3>
                            <ul style="column-count: 2; column-gap: 20px;">
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                                <li>Fuerza: ${requestScope.monstruo.sentidos}</li>
                            </ul>
                            <br><hr style="border-style: dashed;">
                            <ul>
                                <li>Sentidos: ${requestScope.monstruo.sentidos}</li>
                                <li>Habilidades: <li>
                                <li>Inmunidad a estados: ${requestScope.monstruo.vuldano}</li>
                                <li>Resistencia al daño: ${requestScope.monstruo.resdano}</li>
                                <li>Inmunidad al daño: ${requestScope.monstruo.inmdano}</li>
                                <li>Vulnerabilidad al daño: ${requestScope.monstruo.vuldano}</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="datosDerechaTrasfondo">
                    <p>${requestScope.monstruo.descripcion}</p>
                    <br>
                    <h3>Rasgos: </h3>
                    <p></p>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Razas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/razasCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>       
            <h6 class = "BusquedaRazas">
                <a href="#Normales">&nbsp;&nbsp;Normales</a>
                <a href="#Monstruosas">&nbsp;&nbsp;Monstruosas</a>
            </h6>
            <div class="ResumenRazas">
                <h2 class="Titulos">Razas</h2>
                <hr color="black">
                <h3>Busca por nombre: <input id="buscador" onkeyup="realizarBusqueda('Razas')" onsearch="realizarBusqueda('Razas')" type="search" placeholder="Busqueda por nombre"/> </h3>
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
                    <h3 id="Monstruosas">Monstruosas</h3>
                    <div class="ListaRazasTipo">
                        <c:forEach var="razasM" items="${listaRazasMonstruosas}" varStatus="status">
                            <div class="ResumenRaza" id="RazasMonstuosas" data-id="${razasM.id}">
                                <img src="${listaRazasImagenesMonstruosas[status.index]}">
                                <div class="ContenidoRaza">
                                    <div class="TituloRaza">${razasM.nombre}</div>
                                    <div class="DescripcionRaza">${razasM.resumen}</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script>
            //Lista
            let tabla = document.getElementById('ListaRazas');
            let tablaInicial = tabla.innerHTML;
        </script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/busquedasAJAXJS.js"></script>
        <script src="/TFG/js/explorar/razasJS.js"></script>
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

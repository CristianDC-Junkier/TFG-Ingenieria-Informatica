<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Clases</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/clasesCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main> 
            <div>
                <h6 id="Arriba" class = "BusquedaClase">
                    <a href="#Aa" id="A">&nbsp;&nbsp;A</a>
                    <a href="#B">&nbsp;&nbsp;B</a>
                    <a href="#C">&nbsp;&nbsp;C</a>
                    <a href="#D">&nbsp;&nbsp;D</a>
                    <a href="#E">&nbsp;&nbsp;E</a>
                    <a href="#H">&nbsp;&nbsp;H</a>
                    <a href="#L">&nbsp;&nbsp;L</a>
                    <a href="#M">&nbsp;&nbsp;M</a>
                    <a href="#Pp" id="P">&nbsp;&nbsp;P&nbsp;</a>
                </h6>
            </div>
            <div class="ResumenClases">
                <h2 class="Titulos">Clases</h2>
                <hr color="black">
                <div class="ListaClases">
                   <c:out value="${listaClases }" escapeXml="false" />    
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

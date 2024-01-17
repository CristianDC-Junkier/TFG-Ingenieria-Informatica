<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Clases</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/claseCss.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>
            <h2 class="Titulos">Clase: ${requestScope.clase.nombre}</h2>
            <hr color="black">
            <div class="arribaDatosClase">
                <div class="datosIzquierdaClase">
                    <img src="/TFG/img/clases/${requestScope.imagen}.jfif"/>
                    <div>
                        <p>${requestScope.clase.descripcion}</p>
                    </div>
                </div>
                <div class="datosDerechaClase">
                    <table class="tablaHechizos">
                        <thead>
                            <tr>
                                <th>Columna 1</th>
                                <th>Columna 2</th>
                                <th>Columna 3</th>
                                <th>Columna 4</th>
                                <th>Columna 5</th>
                                <th>Columna 6</th>
                                <th>Columna 7</th>
                                <th>Columna 8</th>
                                <th>Columna 9</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Filas de la tabla (puedes ajustar según tus necesidades) -->
                            <tr>
                                <td>Dato 1</td>
                                <td>Dato 2</td>
                                <td>Dato 3</td>
                                <td>Dato 4</td>
                                <td>Dato 5</td>
                                <td>Dato 6</td>
                                <td>Dato 7</td>
                                <td>Dato 8</td>
                                <td>Dato 9</td>
                            </tr>
                            <!-- Otras filas... -->
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Crear_Personaje</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/crearpersonajeCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistroPersonaje">
                <h2 class="TitulosFormulario">Modificar Atributos</h2>
                <form id = formRegistro action="/TFG/Personajes/modificarPersonajeAtributos?id=${requestScope.personaje.id}" method="POST">
                    <div>
                        <c:forEach var="Personajeatributo" items="${listaPersonajeAtributos}">
                            <div class="puntos">
                                <div class="puntos-contenedor">
                                    <label for="Atributo_${Personajeatributo.atributos.nombre}" id="atrPersonaje_${Personajeatributo.atributos.nombre}">
                                        ${Personajeatributo.atributos.nombre}:</label>
                                    <input type="number" name="atributo_${Personajeatributo.atributos.nombre}" id="Atributo_${Personajeatributo.atributos.nombre}" 
                                           value="${Personajeatributo.valor}" min="8" max="20" readonly>
                                    <div class="modificadores"></div>
                                </div>
                                <div class="botones-contenedor">
                                    <button type="button" class="botonaumentar">+</button>
                                    <button type="button" class="botondecrementar">-</button>
                                </div>
                            </div>
                            <label for="salvacion_${Personajeatributo.atributos.nombre}">Salvación</label>
                            <input type="checkbox" id="salvacion_${Personajeatributo.atributos.nombre}" name="salvacion_${Personajeatributo.atributos.nombre}" 
                                   <c:if test="${Personajeatributo.salvacion == 'Si'}">checked</c:if>>
                        </c:forEach>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.pathname = '/TFG/Personajes/personajePerfilAtributos'" value="Volver">
                        <input  id="botonEnviar" type="submit" value="Actualizar atributos">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footerNoChat.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/modificarPersonajeAtributosJS.js"></script>
    </body>
</html>

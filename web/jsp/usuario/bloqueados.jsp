
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Guidance4\Perfil\Bloqueados</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/bloqueadosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Bloqueados</h2>
        <hr color="black">
        <div class="contenedoresBloqueados"> 
            <div class="contenedorBloqueados">
                <div class="tituloBuscadorBloqueados">Bloqueados</div>
                <div class="buscadorBloqueados">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarBloqueados">
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <option value="ordenar1" selected>Nombre (A-Z)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar1">Nombre (A-Z)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar2'}">
                                    <option value="ordenar2" selected>Nombre (Z-A)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar2">Nombre (Z-A)</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div> 
                </div>
            </div>
            <div class="listasBloqueados" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaBloqueado">
                            <h3>Bloqueados</h3>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <h4>Ordenado por nombre (A-Z)</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4>Ordenado por nombre (Z-A)</h4>
                                </c:otherwise>
                            </c:choose>
                            <div class="diseñoTabla">
                                <table>
                                    <tr>
                                        <c:forEach var="usuario" items="${listaUsuarios}">
                                            <td><div class="personaje-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${usuario.apodo}</td>
                                            <td>Compartir Mesa o No</td>
                                            <td>El Boton añadir</td>
                                            <td>El Boton bloquear</td>
                                        </c:forEach>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesBloqueados">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <footer>
        &copy; 2023 Cristian Delgado Cruz
    </footer>
    <script>
        //Select y botones
        let Orden = document.getElementById('ordenarBloqueados');
        let Binicio = document.getElementById('pagInicio');
        let Bfinal = document.getElementById('pagFinal');
        let BAnterior = document.getElementById('pagAnterior');
        let BPosterior = document.getElementById('pagPosterior');

        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        let totalaux = numpag - pag;

        Orden.addEventListener('change', function () {
            let valorSeleccionado = Orden.value;
            let urlDestinoOrden = "/TFG/Usuarios/mostrarBloqueados?orden=" + valorSeleccionado + "&pag=" + pag;
            window.location.href = urlDestinoOrden;
        });

        //Funcion que actualiza los botones
        function actualizarBotones() {


            let pestañasBotones = document.getElementById('pestañaBotones');
            pestañasBotones.innerHTML = '';

            if (pag > 2) {
                pestañasBotones.innerHTML += '<button class="botonArriba" id="pagInicio">Inicio</button>';
            }
            if (pag > 1) {
                pestañasBotones.innerHTML += '<button class="botonArriba" id="pagAnterior">' + (pag - 1) + '</button>';
            }

            pestañasBotones.innerHTML += '<button class="botonArriba" id="pagActual"> Actual </button>';

            if (totalaux > 2) {
                pestañasBotones.innerHTML += '<button class="botonArriba" id="pagPosterior">' + (pag + 1) + '</button>';
            }
            if (totalaux > 1) {
                pestañasBotones.innerHTML += '<button class="botonArriba" id="pagFinal">Final</button>';
            }
        }
        actualizarBotones();

        //Pag Inicio
        if (pag > 2) {
            Binicio.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden  + "&pag=" + "1";
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag Final
        if (totalaux > 1) {
            Bfinal.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + numpag;
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag anterior
        if (pag > 1) {
            BAnterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden  + "&pag=" + (pag - 1);
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag posterior
        if (totalaux > 2) {
            BPosterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarBloqueados?orden=" + orden + "&pag=" + (pag + 1);
                window.location.href = urlDestinoPagIni;
            });
        }
    </script>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Amigos\Peticiones</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/peticionesCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/listasUsuariosCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Peticiones</h2>
        <hr color="black">
        <div class="contenedoresPeticiones"> 
            <div class="contenedorPeticiones">
                <div class="tituloBuscadorPeticiones">Peticiones</div>
                <div class="buscadorPeticiones">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarPeticiones">
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
                    <button id="botonMesa" class="${requestScope.mesa == "true" ? 'existeMesa' : 'noExisteMesa'}">Mesa</button>
                    <c:choose>
                        <c:when test="${requestScope.peticiones == 'Enviadas'}">
                            <button id = "botonPeticiones">Recibidas</button> 
                        </c:when>
                        <c:otherwise>
                            <button id = "botonPeticiones">Enviadas</button> 
                        </c:otherwise>
                    </c:choose> 
                </div>
            </div>
            <div class="listasPeticiones" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaPeticion">
                            <c:choose>
                                <c:when test="${requestScope.peticiones == 'Enviadas'}">
                                    <h3>Peticiones Enviadas</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3>Peticiones Recibidas</h3>
                                </c:otherwise>
                            </c:choose>
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
                                    <c:forEach var="usuario" items="${listaUsuarios}">
                                        <tr>
                                            <td><div class="personaje-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${usuario.apodo}</td>
                                            <td>Compartir Mesa o No</td>
                                            <c:choose>
                                                <c:when test="${requestScope.peticiones == 'Enviadas'}">
                                                    <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/eliminarPeticion?pamistad=${usuario.apodo}'">Eliminar</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/aceptarPeticion?pamistad=${usuario.apodo}'">Aceptar</button></td>
                                                    <td><button class="botonDentro" onclick="location.href = '/TFG/Usuarios/rechazarPeticion?pamistad=${usuario.apodo}'">Rechazar</button></td>
                                                </c:otherwise>
                                            </c:choose> 
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesPeticiones">
                <div class="pestañasBotones" id="pestañaBotones">
                </div>
            </div>
        </div> 
    </main>
    <footer>
        &copy; 2023 Cristian Delgado Cruz
    </footer>
    <script src="/TFG/js/principalJS.js"></script>
    <script>
        //Select y botones
        let Orden = document.getElementById('ordenarPeticiones');
        let Mesa = document.getElementById('botonMesa');
        let Binicio = document.getElementById('pagInicio');
        let Bfinal = document.getElementById('pagFinal');
        let BAnterior = document.getElementById('pagAnterior');
        let BPosterior = document.getElementById('pagPosterior');
        let Bpeticion = document.getElementById('botonPeticiones');

        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        let mesa = '<%= request.getAttribute("mesa")%>';
        // Convertir el valor de mesa a un booleano
        let mesaBool = '<%= request.getAttribute("mesa")%>' === 'true';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        let totalaux = numpag - pag;
        //Peticion
        let peticion = '<%= request.getAttribute("peticiones")%>'

        Orden.addEventListener('change', function () {
            let valorSeleccionado = Orden.value;
            let urlDestinoOrden = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + valorSeleccionado + "&mesa=" + mesa + "&pag=" + pag;
            window.location.href = urlDestinoOrden;
        });

        Mesa.addEventListener('click', function () {
            let urlDestinoMesa;
            if (mesaBool) {
                urlDestinoMesa = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + "false" + "&pag=" + pag;
            } else {
                urlDestinoMesa = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + "true" + "&pag=" + pag;
            }
            window.location.href = urlDestinoMesa;
        });

        Bpeticion.addEventListener('click', function () {
            let urlDestinoBoton;
            if (peticion === "Enviadas") {
                urlDestinoBoton = "/TFG/Usuarios/mostrarPeticionesRecibidas?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag;
            } else {
                urlDestinoBoton = "/TFG/Usuarios/mostrarPeticionesEnviadas?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag;
            }
            window.location.href = urlDestinoBoton;
        });

        //Funcion que actualiza los botones para pasar de pag
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
                urlDestinoPagIni = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + "1";
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag Final
        if (totalaux > 1) {
            Bfinal.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + numpag;
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag anterior
        if (pag > 1) {
            BAnterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag - 1);
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag posterior
        if (totalaux > 2) {
            BPosterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Usuarios/mostrarPeticiones" + peticion + "?orden=" + orden + "&mesa=" + mesa + "&pag=" + (pag + 1);
                window.location.href = urlDestinoPagIni;
            });
        }
    </script>
</body>
</html>
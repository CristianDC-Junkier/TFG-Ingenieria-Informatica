<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title class="titulosPag">Guidance4\Mesas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/TFG/css/usuario/amigosCss.css"/>
    <link rel="stylesheet" type="text/css" href="/TFG/css/mesas/mesasCss.css"/>
</head>
<body>
    <header>
        <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
    </header>
    <jsp:include page="/jsp/menuNav.jsp" />
    <main>
        <h2 class="Titulos">Mesas</h2>
        <hr color="black">
        <div class="contenedoresMesa"> 
            <div class="contenedorMesa">
                <div class="tituloBuscadorMesa">Explorar Mesas</div>
                <div class="buscadorMesa">
                    <div>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </div>
                    <div>
                        Ordenar:
                        <select id="ordenarMesa">
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar1'}">
                                    <option value="ordenar1" selected>Titulo (A-Z)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar1">Titulo (A-Z)</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${requestScope.orden == 'ordenar2'}">
                                    <option value="ordenar2" selected>Titulo (Z-A)</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="ordenar2">Titulo (Z-A)</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <button id="botonLleno" class="${requestScope.lleno == "true" ? 'estalleno' : 'noestalleno'}">Llenas</button>
                </div>
            </div>
            <div class="listasMesa" id="pestañasSeccion">
                <div class="pestañasNavegacion">
                    <div class="pestaña" id="pestaña1">
                        <div class="listaMesa">
                            <h3>Mesas</h3>
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
                                    <c:forEach var="mesa" items="${listaMesas}" varStatus="status">
                                        <tr>
                                            <td><div class="mesa-foto">
                                                    <img src="/TFG/img/iconos/IMGNEGRO.png">
                                                </div></td>
                                            <td>${mesa.titulo}</td>
                                            <td>${mesa.comunidad}</td>
                                            <td>/${mesa.tamano}</td>
                                            <td>${listaPerteneceMesa[status.index].usuario}</td>
                                            <td><button class="botonDentro" onclick="mostrarRecuadro()">Entrar</button></td>
                                        </tr>
                                        <div class="opcionRecuadro" id="recuadro" style="display: none;">
                                            <div class="contenidoRecuadro">
                                                <div class="tituloRecuadro">¿Esta seguro que quieres unirte?
                                                    <span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>
                                                </div>
                                                <hr>
                                                <button class="botonDentro" onclick="location.href = '/TFG/Usuarios/anadiraMesa?titulo=${mesa.titulo}'">Si</button>
                                                <button class="botonDentro" onclick="cerrarRecuadro()">No</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contenedorBotonesMesa">
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
        let Orden = document.getElementById('ordenarMesa');
        let Lleno = document.getElementById('botonLleno');
        let Binicio = document.getElementById('pagInicio');
        let Bfinal = document.getElementById('pagFinal');
        let BAnterior = document.getElementById('pagAnterior');
        let BPosterior = document.getElementById('pagPosterior');

        //Recoger Datos
        let orden = '<%= request.getAttribute("orden")%>';
        let lleno = '<%= request.getAttribute("lleno")%>';
        // Convertir el valor de mesa a un booleano
        let llenoBool = '<%= request.getAttribute("lleno")%>' === 'true';
        //Datos de las páginas
        let numpag = parseInt('<%= request.getAttribute("numPag")%>', 10);
        let pag = parseInt('<%= request.getAttribute("pag")%>', 10);
        let totalaux = numpag - pag;

        Orden.addEventListener('change', function () {
            let valorSeleccionado = Orden.value;
            let urlDestinoOrden = "/TFG/Mesas/mostrarMesas?orden=" + valorSeleccionado + "&lleno=" + lleno + "&pag=" + pag;
            window.location.href = urlDestinoOrden;
        });

        Lleno.addEventListener('click', function () {
            let urlDestinoMesa;
            if (llenoBool) {
                urlDestinoMesa = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + "false" + "&pag=" + pag;
            } else {
                urlDestinoMesa = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + "true" + "&pag=" + pag;
            }
            window.location.href = urlDestinoMesa;
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
                urlDestinoPagIni = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + "1";
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag Final
        if (totalaux > 1) {
            Bfinal.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + numpag;
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag anterior
        if (pag > 1) {
            BAnterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + (pag - 1);
                window.location.href = urlDestinoPagIni;
            });
        }
        //Pag posterior
        if (totalaux > 2) {
            BPosterior.addEventListener('click', function () {
                let urlDestinoPagIni;
                urlDestinoPagIni = "/TFG/Mesas/mostrarMesas?orden=" + orden + "&lleno=" + lleno + "&pag=" + (pag + 1);
                window.location.href = urlDestinoPagIni;
            });
        }
        function mostrarRecuadro() {
            document.getElementById('recuadro').style.display = 'flex';
        }

        function cerrarRecuadro() {
            document.getElementById('recuadro').style.display = 'none';
        }
    </script>
    <script src="/TFG/js/principalJS.js"></script>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Explorar\Razas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/explorar/razasCss.css"/>
    </head>
    <body>
        <header>
            <h1> <img class="Logo" src="/TFG/img/dnd-banner.jpg" alt="Logo"/> </h1>
        </header>
        <jsp:include page="/WEB-INF/jsp/menuNav.jsp" />
        <main>       
            <h6 class = "BusquedaRazas">
                <a href="#A">&nbsp;&nbsp;A</a>
                <a href="#B">&nbsp;&nbsp;B</a>
                <a href="#C">&nbsp;&nbsp;C</a>
                <a href="#D">&nbsp;&nbsp;D</a>
                <a href="#E">&nbsp;&nbsp;E</a>
                <a href="#H">&nbsp;&nbsp;H</a>
                <a href="#M">&nbsp;&nbsp;M</a>
                <a href="#P">&nbsp;&nbsp;P&nbsp;</a>
            </h6>
            <div class="ResumenRazas">
                <h2 class="Titulos">Razas</h2>
                <hr color="black">
                <h3>Busca por nombre: <input type="search" placeholder="Introduce el nombre"/> </h3>
                <div class="ListaRazas">
                    <h3>Normales</h3>
                    <div class="ListaRazasTipo">
                        <div class="ResumenRaza">
                            <img src="/TFG/img/razas/enano.jpg" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Enano</div>
                                <div class="DescripcionRaza">Los enanos son to raros man</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                    </div>
                    <h3>Monstruosas</h3>
                    <div class="ListaRazasTipo">
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Enano</div>
                                <div class="DescripcionRaza">Los enanos son to raros man</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                        <div class="ResumenRaza">
                            <img src="https://via.placeholder.com/300" alt="Placeholder Image">
                            <div class="ContenidoRaza">
                                <div class="TituloRaza">Elfo</div>
                                <div class="DescripcionRaza">texto</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="/TFG/js/principalJS.js"></script>
    </body>
</html>

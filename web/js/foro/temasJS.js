let temas = document.querySelectorAll("#Temas");

temas.forEach(function (fila) {
    fila.addEventListener("click", function () {
        let idTema = fila.dataset.id;
        let urlDestinoOrden = "/TFG/Foro/hilos?tema=" + idTema + "&seccion=" + "Cualquiera" + "&mio=" + "false" + "&comentado=" + "false" + "&pag=" + 1;
        window.location.href = urlDestinoOrden;
    });
});




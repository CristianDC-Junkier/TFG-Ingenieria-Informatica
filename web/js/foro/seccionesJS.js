let secciones = document.querySelectorAll("#Secciones");

secciones.forEach(function (fila) {
    fila.addEventListener("click", function () {
        let idSeccion = fila.dataset.id;
        let urlDestinoOrden = "/TFG/Foro/hilos?tema=" + "Cualquiera" + "&seccion=" + idSeccion + "&mio=" + "false" + "&comentado=" + "false" + "&pag=" + 1;
        window.location.href = urlDestinoOrden;
    });
});





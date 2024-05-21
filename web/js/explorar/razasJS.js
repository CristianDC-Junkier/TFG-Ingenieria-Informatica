


function asignarEventos() {

    let RN = document.querySelectorAll("#RazasNormales");
    let RM = document.querySelectorAll("#RazasMonstuosas");

    RN.forEach(function (fila) {
        fila.addEventListener("click", function () {
            let idRaza = fila.dataset.id;
            let urlDestinoOrden = "/TFG/Explorar/raza?idRaza=" + idRaza;
            window.location.href = urlDestinoOrden;
        });
    });

    RM.forEach(function (fila) {
        fila.addEventListener("click", function () {
            let idRaza = fila.dataset.id;
            let urlDestinoOrden = "/TFG/Explorar/raza?idRaza=" + idRaza;
            window.location.href = urlDestinoOrden;

        });
    });
}

asignarEventos();

function realizarBusqueda(valor) {
    //Obtener el valor de la búsqueda
    let valorBusqueda = document.getElementById("buscador").value;
    let urlAJAX = "";

    if (valorBusqueda === "") {
        // Limpiar el contenido actual de la tabla
        tabla.innerHTML = '';
        // Insertar el nuevo HTML en el contenedor
        tabla.innerHTML = tablaInicial;
    } else {
        switch (valor) {
            case "Usuarios":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Usuarios?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag;
                break;
        }

        // Realizar la solicitud AJAX
        $.ajax({
            type: "GET",
            url: urlAJAX,
            data: {busqueda: valorBusqueda},
            dataType: "text",
            success: function (data) {
                let htmlResultados = data;
                // Limpiar el contenido actual de la tabla
                tabla.innerHTML = '';
                // Insertar el nuevo HTML en el contenedor
                tabla.innerHTML = htmlResultados;
            },
            error: function (error) {
                console.error("Error en la solicitud AJAX:", error);
            }
        });
    }
}


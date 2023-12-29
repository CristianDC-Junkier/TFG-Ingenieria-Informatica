function realizarBusqueda() {
    // Obtener el valor de la búsqueda
    let valorBusqueda = document.getElementById("buscador").value;


    if (valorBusqueda == "") {
        // Limpiar el contenido actual de la tabla
        tabla.innerHTML = '';
        // Insertar el nuevo HTML en el contenedor
        tabla.innerHTML = tablaInicial;
    } else {
        // Realizar la solicitud AJAX
        $.ajax({
            type: "GET",
            url: "/TFG/ControladorPeticionesAJAX/buscadorAmigos?orden=" + orden + "&mesa=" + mesa + "&pag=" + pag,
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



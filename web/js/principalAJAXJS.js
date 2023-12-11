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
            dataType: "json",
            success: function (data) {
                let ListaAux = data; 
                let htmlResultados = '';
                for (let i = 0; i < ListaAux.length; i++) {
                    let usuario = ListaAux[i];
                    htmlResultados = 
                            '<table>'+
                                '<tr>' +
                                    '<td><div class="personaje-foto">' + '<img src="/TFG/img/iconos/IMGNEGRO.png">' + '</div></td>' +
                                    '<td>' + usuario.apodo + '</td>' +
                                    '<td>' + usuario.provincia + '</td>' +
                                    '<td>' + usuario.genero + '</td>' +
                                     '<td><button class="botonDentro" onclick="location.href = \'/TFG/Usuarios/mostrarAmigo?amigo=' + usuario.apodo + '\'">Detalles</button></td>' +
                                    '<td><button class="botonDentro" onclick="mostrarRecuadro()">Eliminar Amigo</button></td>' +
                                '</tr>' +
                                '<div class="opcionRecuadro" id="recuadro" style="display: none;">' +
                                    '<div class="contenidoRecuadro">' +
                                        '<div class="tituloRecuadro">¿Está seguro que quiere Eliminarlo?' +
                                            '<span class="cierreRecuadro" onclick="cerrarRecuadro()">X</span>' +
                                        '</div>' +
                                        '<hr>' +
                                        '<button class="botonDentro" onclick="location.href = \'/TFG/Usuarios/eliminarAmigo?amigo=' + usuario.apodo + '\'">Si</button>' +
                                        '<button class="botonDentro" onclick="cerrarRecuadro()">No</button>' +
                                    '</div>' +
                                '</div>'+
                            '<table>';
                }      
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



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
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Usuarios?orden=" + orden + "&mesa=" + mesa;
                break;
            case "Amigos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Amigos?orden=" + orden + "&mesa=" + mesa;
                break;
            case "Bloqueados":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Bloqueados?orden=" + orden;
                break;
            case "PeticionesEnviadas":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PeticionesEnviadas?orden=" + orden + "&mesa=" + mesa;
                break;
            case "PeticionesRecibidas":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PeticionesRecibidas?orden=" + orden + "&mesa=" + mesa;
                break;
            case "Mesas":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Mesas?orden=" + orden + "&lleno=" + lleno;
                break;
            case "MesasAmigos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/MesasAmigos?orden=" + orden;
                break;
            case "MesasPerfil":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/MesasPerfil?orden=" + orden;
                break;
            case "Personajes":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Personajes?orden=" + orden;
                break;
            case "PersonajesAmigo":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesAmigo?orden=" + orden;
                break;
            case "PersonajesAmigos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesAmigos?orden=" + orden;
                break;
            case "PersonajesPerfil":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesPerfil?orden=" + orden;
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



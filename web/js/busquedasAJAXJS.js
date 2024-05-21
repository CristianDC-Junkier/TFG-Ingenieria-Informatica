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
                urlAJAX = "/TFG/ControladorPeticionesAJAX/MesasAmigos?amigo=" + amigo + "&orden=" + orden;
                break;
            case "MesasPerfil":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/MesasPerfil?orden=" + orden;
                break;
            case "Personajes":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Personajes?orden=" + orden + "&raza=" + raza + "&clase=" + clase + "&nivel=" + nivel;
                break;
            case "PersonajesAmigo":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesAmigo?amigo=" + amigo + "&orden=" + orden + "&raza=" + raza + "&clase=" + clase + "&nivel=" + nivel;
                break;
            case "PersonajesAmigos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesAmigos?orden=" + orden + "&raza=" + raza + "&clase=" + clase + "&nivel=" + nivel;
                break;
            case "PersonajesPerfil":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesPerfil?orden=" + orden + "&raza=" + raza + "&clase=" + clase + "&nivel=" + nivel;
                break;
            case "PersonajesPerfilElegir":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/PersonajesPerfilElegir?orden=" + orden + "&raza=" + raza + "&clase=" + clase + "&nivel=" + nivel;
                break;
            case "Hechizos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Hechizos?vEscu=" + valorSeleccionado1 + "&vNiv=" + valorSeleccionado2 + "&vClas=" + valorSeleccionado3;
                break;
            case "Monstruos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Monstruos?vVD=" + valorSeleccionado1 + "&vTipo=" + valorSeleccionado2;
                break;
            case "Equipo":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Equipo?vTipo=" + valorSeleccionado1 + "&vCat=" + valorSeleccionado2 + "&vPro=" + valorSeleccionado3;
                break;
            case "personajeHechizos":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/personajeHechizos?vEscu=" + valorSeleccionado1 + "&vNiv=" + valorSeleccionado2 + "&vClas=" + valorSeleccionado3 + "&id=" + idPJ;
                break;
            case "personajeEquipo":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/personajeEquipo?vTipo=" + valorSeleccionado1 + "&vCat=" + valorSeleccionado2 + "&vPro=" + valorSeleccionado3 + "&id=" + idPJ;
                break;
            case "personajeHechizosElegir":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/personajeHechizosElegir?vEscu=" + valorSeleccionado1 + "&vNiv=" + valorSeleccionado2 + "&vClas=" + valorSeleccionado3 + "&id=" + idPJ;
                break;
            case "personajeEquipoElegir":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/personajeEquipoElegir?vTipo=" + valorSeleccionado1 + "&vCat=" + valorSeleccionado2 + "&vPro=" + valorSeleccionado3 + "&id=" + idPJ;
                break;
            case "Razas":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/Razas";
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

                switch (valor) {

                    case "Razas":
                    case "Equipo":
                    case "Monstruos":
                        asignarEventos();
                        break;
                }
            },
            error: function (error) {
                console.error("Error en la solicitud AJAX:", error);
            }
        });
    }
}



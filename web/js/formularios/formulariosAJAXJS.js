function realizarBusqueda(inputID, spanID) {
    //Obtener el valor de la búsqueda
    let input = document.getElementById(inputID);
    let valorBusqueda = input.value;
    let spanInput = document.getElementById(spanID);
    let urlAJAX = "";

    if (valorBusqueda === "") {
        spanInput.innerHTML = "";
        if (inputID === "phoneReg" || inputID === "phoneMod") {
            spanInput.innerHTML = "✓";
        } else {
            spanInput.innerHTML = "✖";
        }
    } else {
        switch (inputID) {
            case "nameReg":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RegistroApodo";
                break;
            case "emailReg":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RegistroEmail";
                break;
            case "phoneReg":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RegistroTelefono";
                break;
            case "bornReg":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RegistroNacimiento";
                break;
            case "userRC":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RecuperarUser";
                break;
            case "emailRC":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/RecuperarEmail";
                break;
            case "nameMod":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/ModificarApodo";
                break;
            case "emailMod":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/ModificarEmail";
                break;
            case "phoneMod":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/ModificarTelefono";
                break;
            case "bornMod":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/ModificarNacimiento";
                break;
            case "tittleMesa":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearMesa";
                break;
            case "tittleMesaMod":
                var tituloMesaJS = '<%= request.getAttribute("mesa.titulo") %>';
                urlAJAX = "/TFG/ControladorPeticionesAJAX/ModificarMesa?mesaTitulo=" + tituloMesaJS;
                break;
            case "nombrePersonaje":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearPersonaje";
                break;
        }
        
        console.log("llega " + inputID);
        
        // Realizar la solicitud AJAX
        $.ajax({
            type: "GET",
            url: urlAJAX,
            data: {busqueda: valorBusqueda},
            dataType: "text",
            success: function (data) {
                let v = data;
                spanInput.innerHTML = "";
                if (v !== "Encontrado" && input.checkValidity()) {
                    spanInput.innerHTML = "✓";
                } else {
                    if (inputID === "tittleMesaMod" || inputID === "tittleMesa") {
                        input.title = "La mesa esta repetido o no es valido";
                    } else if (inputID === "namePersonaje") {
                        input.title = "El personaje esta repetido o no es valido";
                    } else {
                        input.title = "El usuario esta repetido o no es valido";
                    }
                    spanInput.innerHTML = "✖";

                }
            },
            error: function (error) {
                console.error("Error en la solicitud AJAX:", error);
            }

        });
    }
}

function realizarBusquedaContrasenas(inputID, spanID, inputID2) {

    let input = document.getElementById(inputID);
    let valorBusqueda = input.value;
    let input2 = document.getElementById(inputID2);
    let valorObjetivo = input2.value;

    let spanInput = document.getElementById(spanID);

    if (valorBusqueda === "") {
        spanInput.innerHTML = "";
        spanInput.innerHTML = "✖";
    } else if (valorBusqueda === valorObjetivo) {
        spanInput.innerHTML = "";
        spanInput.innerHTML = "✓";
    } else {
        spanInput.innerHTML = "";
        spanInput.innerHTML = "✖";
    }
}

function escogiendoPersonaje(valor, valorBusqueda, etiqueta) {

    if (valorBusqueda !== "-") {

        switch (valor) {
            case "CrearPersonajesSubraza":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearPersonajesSubraza";
                break;
            case "CrearPersonajesSubclase":
                urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearPersonajesSubclase";
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
                etiqueta.innerHTML = '';
                // Insertar el nuevo HTML en el contenedor
                etiqueta.innerHTML = htmlResultados;
            },
            error: function (error) {
                console.error("Error en la solicitud AJAX:", error);
            }
        });
    } else {
        etiqueta.innerHTML = '';
        etiqueta.innerHTML = '<option value="-">-</option>';
    }
}

function cambiarHabilidades(valorBusqueda, valorBusqueda2, etiqueta) {

    urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearPersonajesHabilidades";

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {busqueda: valorBusqueda, busqueda2: valorBusqueda2},
        dataType: "text",
        success: function (data) {
            let htmlResultados = data;
            // Limpiar el contenido actual de la tabla
            etiqueta.innerHTML = '';
            // Insertar el nuevo HTML en el contenedor
            etiqueta.innerHTML = htmlResultados;
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

function indicarAtr(valorBusqueda, etiqueta) {
    urlAJAX = "/TFG/ControladorPeticionesAJAX/CrearPersonajesAtributos";

    // Realizar la solicitud AJAX
    $.ajax({
        type: "GET",
        url: urlAJAX,
        data: {busqueda: valorBusqueda},
        dataType: "text",
        success: function (data) {
            let htmlResultados = data;
            // Limpiar el contenido actual de la tabla
            etiqueta.innerHTML = '';
            // Insertar el nuevo HTML en el contenedor
            etiqueta.innerHTML = htmlResultados;
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
        }
    });
}

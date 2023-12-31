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
        }

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
                    input.title = "El usuario esta repetido o no es valido";
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

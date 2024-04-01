

//Bloque 3-2
let atributo1 = document.getElementById("atributo1").value;
let atributo2 = document.getElementById("atributo2").value;
//Error
let MensajeErrorB3 = document.getElementById("mensajeErrorB3");


function AvanzarFormulario(Pag) {
    switch (Pag) {
        case 2:
            //Borro Mensaje de Error si había
            MensajeErrorB3.innerHTML = "";
            //Mostrar Bloques
            Bloque_1.style.display = "none";
            Bloque_2.style.display = "block";
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(3);
            };
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            break;
        case 3.1:
            //Mostrar Bloques
            Bloque_2.style.display = "none";
            Bloque_31.style.display = "block";
            //Cambiar botones
            Avanzar.type = "hidden";
            Enviar.type = "submit";
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            break;
        case 3.2:
            //Mostrar Bloques
            Bloque_2.style.display = "none";
            Bloque_32.style.display = "block";
            //Mostrar Atributos que ganas
            indicarAtr(SubRaza.value, elecAtr);
            //Cambiar botones
            //Cambiar botones
            Avanzar.type = "hidden";
            Enviar.type = "submit";
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            break;
    }
}

function RetrocederFormulario(Pag) {
    switch (Pag) {
        case 1:
            //Mostrar Bloques
            Bloque_1.style.display = "block";
            Bloque_2.style.display = "none";
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(2);
            };
            Volver.onclick = "location.pathname = 'TFG/Principal/inicio'";
            break;
        case 2:
            //Mostrar Bloques
            Bloque_2.style.display = "block";
            Bloque_3.style.display = "none";
            //Cambiar botones
            Avanzar.onclick = function () {
                AvanzarFormulario(3);
            };
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            break;
    }
}

function validarFormulario() {

    if (atributo1 === atributo2) {
        MensajeErrorB3.innerHTML = "";
        MensajeErrorB3.innerHTML = "<br> Debes elegir diferentes atributos";
        return false;
    }
    return true;
}



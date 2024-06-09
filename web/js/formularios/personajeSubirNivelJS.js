
//Bloques
let Bloque_1 = document.getElementById("Bloque1");
let Bloque_21 = document.getElementById("Bloque2-1");
let Bloque_22 = document.getElementById("Bloque2-2");
let Bloque_3 = document.getElementById("Bloque3");

//Bloque 3
let inputVida = document.getElementById("dadoVida");

//Error
let MensajeErrorB2 = document.getElementById("mensajeErrorB2");

//Botones
let Avanzar = document.getElementById("botonAvanzarFormulario");
let Volver = document.getElementById("botonVolverFormulario");
let Enviar = document.getElementById("botonEnviar");

let valoresEleccion = document.getElementById("valoresEleccion");
let eleccionDoteAtr = document.getElementById("eleccionDoteAtr");

//Valores
let idPersonajeJS = '<%= request.getAttribute("personaje.id") %>';
let subclaseNivelJS = '<%= request.getAttribute("nivelSubclase") %>';
let nivelPersonajeJS = '<%= request.getAttribute("nivelSiguiente") %>';

function AvanzarFormulario(Pag) {
    switch (Pag) {
        case 2.1:
            //Mostrar Bloques
            Bloque_1.style.display = "none";
            Bloque_21.style.display = "block";
            //Cambiar botones
            if (subclaseNivelJS === nivelPersonajeJS) {
                Enviar.type = "submit";
            } else {
                Avanzar.type = "button";
            }
            valoresEleccion.style.display = "none";
            Avanzar.onclick = function () {
                AvanzarFormulario(3);
            };
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            //Eleccion 
            eleccionDoteAtr.value = "Dote";
            break;
        case 2.2:
            //Borro Mensaje de Error si había
            MensajeErrorB2.innerHTML = "";
            //Mostrar Bloques
            Bloque_1.style.display = "none";
            Bloque_22.style.display = "block";
            //Cambiar botones
            if (subclaseNivelJS === nivelPersonajeJS) {
                Enviar.type = "submit";
            } else {
                Avanzar.type = "button";
            }
            valoresEleccion.style.display = "none";
            Avanzar.onclick = function () {
                validarFormulario();
            };
            Volver.onclick = function () {
                RetrocederFormulario(1);
            };
            //Eleccion 
            eleccionDoteAtr.value = "Atributos";
            break;
        case 3:
            //Mostrar Bloques
            Bloque_21.style.display = "none";
            Bloque_22.style.display = "none";
            Bloque_1.style.display = "none";
            Bloque_3.style.display = "block";
            //Cambiar botones
            Avanzar.type = "hidden";
            Enviar.type = "submit";
            break;
    }
}

function RetrocederFormulario(Pag) {
    switch (Pag) {
        case 1:
            //Mostrar Bloques
            Bloque_1.style.display = "block";
            Bloque_21.style.display = "none";
            Bloque_22.style.display = "none";
            Bloque_3.style.display = "none";
            //Cambiar botones
            Avanzar.type = "hidden";
            Enviar.type = "hidden";
            valoresEleccion.style.display = "block";
            Volver.onclick = function ()
            {
                location.pathname = '/TFG/Personajes/personajePerfil'
            };
            break;
    }
}

function validarFormulario() {
    let atributo1 = document.getElementById("atributoPersonaje1");
    let atributo2 = document.getElementById("atributoPersonaje2");

    console.log(atributo1.value);
    console.log(atributo2.value);

    if (atributo1.value === atributo2.value) {
        MensajeErrorB2.innerHTML = "";
        MensajeErrorB2.innerHTML = " <br> Debes elegir diferentes atributos";
    } else {
        AvanzarFormulario(3);
    }
}

function tirarDado(dadoClaseJS) {
    let tirada = Math.floor(Math.random() * dadoClaseJS) + 1;
    inputVida.value = tirada;
}



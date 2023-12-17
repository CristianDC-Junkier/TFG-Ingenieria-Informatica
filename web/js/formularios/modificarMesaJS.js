/*Colocar la fecha*/
fechaNacimiento.setDate(fechaNacimiento.getDate() + 1);
let formattedDate = fechaNacimiento.toISOString().split('T')[0];
document.getElementById("bornReg").value = formattedDate;

/*Colocar la provincia*/
var provincias = document.getElementById("provinceReg");
for (let i = 0; i < provincias.options.length; i++) {
    if (provincias.options[i].value === provinciaActual) {
        provincias.options[i].selected = true;
        break;
    }
}

/*Colocar el genero*/
var generos = document.getElementById("genderReg");
for (let i = 0; i < generos.options.length; i++) {
    if (generos.options[i].value === generoActual) {
        generos.options[i].selected = true;
        break;
    }
}


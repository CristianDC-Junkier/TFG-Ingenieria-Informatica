<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <title class="titulosPag">Guidance4\Registro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/TFG/css/principalCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/comunformularioCss.css"/>
        <link rel="stylesheet" type="text/css" href="/TFG/css/formularios/registroCss.css"/>
    </head>
    <body>
        <main class="contenedorFormulario">
            <div class="bloqueRegistro">
                <h2 class="TitulosFormulario">Registro</h2>
                <form id = formRegistro action="/TFG/Usuarios/crearUsuario" method="POST">
                    <div>
                        <div>
                            <label for="realnameReg" id="nombreUsuarioRealReg">Nombre:</label>
                            <input type="text" id="realnameReg" name="nombre_real" required/>
                            <span class="validity" ></span>
                        </div>
                        <div>
                            <label for="nameReg" id="nombreUsuarioReg">Nombre de Usuario:</label>
                            <input type="text" id="nameReg" name="nombre_usuario" onkeyup="realizarBusqueda('nameReg', 'nameRegInput')" required/>
                            <span  id="nameRegInput">✖</span>
                        </div>
                        <div>
                            <label for="emailReg" id="correoUsuarioReg">Correo Electrónico:</label>
                            <input type="email" id="emailReg" name="correo_usuario" onkeyup="realizarBusqueda('emailReg', 'emailRegInput')" required/>
                            <span  id="emailRegInput">✖</span>
                        </div>
                        <div>
                            <label for="passwordReg" id="contraUsuarioReg">Contraseña:</label>
                            <input type="password" id="passwordReg" name="usuario_contrasena" required>
                            <span class="validity" id="passwordRegInput"></span>
                        </div>
                        <div>
                            <label for="passwordConfirmReg" id="contraconUsuarioReg">Repetir Contraseña:</label>
                            <input type="password" id="passwordConfirmReg" name="usuario_contrasena_confirmada" 
                                   onkeyup="realizarBusquedaContrasenas('passwordConfirmReg', 'passwordConfirmRegInput', 'passwordReg')" required>
                            <span id="passwordConfirmRegInput">✖</span>
                        </div>
                        <div>
                            <label for="phoneReg" id="telefonoUsuarioReg">Teléfono de Contacto:</label>
                            <input type="tel" id="phoneReg" pattern="[0-9]{9}" 
                                   name="usuario_telefono" onkeyup="realizarBusqueda('phoneReg', 'phoneRegInput')"/>
                            <span id="phoneRegInput">✓</span>
                        </div>
                        <div>
                            <label for="bornReg" id="nacimientoUsuarioReg">Fecha de Nacimiento:</label>
                            <input type="date" id="bornReg" name="usuario_nacimiento" onkeyup="realizarBusqueda('bornReg', 'bornRegInput')" required>
                            <span id="bornRegInput">✖</span>
                        </div>
                        <div>
                            <label for="provinceReg" id="provinciaUsuarioReg">De donde eres:</label>
                            <select required name="provincia" id="provinceReg" >
                                <option value="Prefiero no decirlo">Prefiero no decirlo</option>
                                <option value="Álava/Araba">Álava/Araba</option>
                                <option value="Albacete">Albacete</option>
                                <option value="Alicante">Alicante</option>
                                <option value="Almerría">Almería</option>
                                <option value="Asturias">Asturias</option>
                                <option value="Ávila">Ávila</option>
                                <option value="Badajoz">Badajoz</option>
                                <option value="Baleares">Baleares</option>
                                <option value="Barcelona">Barcelona</option>
                                <option value="Burgos">Burgos</option>
                                <option value="Cáceres">Cáceres</option>
                                <option value="Cádiz">Cádiz</option>
                                <option value="Cantabria">Cantabria</option>
                                <option value="Castellón">Castellón</option>
                                <option value="Ceuta">Ceuta</option>
                                <option value="Ciudad Real">Ciudad Real</option>
                                <option value="Córdoba">Córdoba</option>
                                <option value="Cuenca">Cuenca</option>
                                <option value="Gerona/Girona">Gerona/Girona</option>
                                <option value="Granada">Granada</option>
                                <option value="Guadalajara">Guadalajara</option>
                                <option value="Guipúzcoa/Gipuzkoa">Guipúzcoa/Gipuzkoa</option>
                                <option value="Huelva">Huelva</option>
                                <option value="Huesca">Huesca</option>
                                <option value="Jaén">Jaén</option>
                                <option value="La Coruña/A Coruña">La Coruña/A Coruña</option>
                                <option value="La Rioja">La Rioja</option>
                                <option value="Las Palmas">Las Palmas</option>
                                <option value="León">León</option>
                                <option value="Lérida/Lleida">Lérida/Lleida</option>
                                <option value="Lugo">Lugo</option>
                                <option value="Madrid">Madrid</option>
                                <option value="Málaga">Málaga</option>
                                <option value="Melilla">Melilla</option>
                                <option value="Murcia">Murcia</option>
                                <option value="Navarra">Navarra</option>
                                <option value="Orense/Ourense">Orense/Ourense</option>
                                <option value="Palencia">Palencia</option>
                                <option value="Pontevedra">Pontevedra</option>
                                <option value="Salamanca">Salamanca</option>
                                <option value="Segovia">Segovia</option>
                                <option value="Sevilla">Sevilla</option>
                                <option value="Soria">Soria</option>
                                <option value="Tarragona">Tarragona</option>
                                <option value="Tenerife">Tenerife</option>
                                <option value="Teruel">Teruel</option>
                                <option value="Toledo">Toledo</option>
                                <option value="Valencia">Valencia</option>
                                <option value="Valladolid">Valladolid</option>
                                <option value="Vizcaya/Bizkaia">Vizcaya/Bizkaia</option>
                                <option value="Zamora">Zamora</option>
                                <option value="Zaragoza">Zaragoza</option>
                            </select>
                        </div>
                        <div>
                            <label for="genderReg" id="generoUsuarioReg">Cual es tu género:</label>
                            <select required name="genero" id="genderReg" >
                                <option value="Prefiero no decirlo">Prefiero no decirlo</option>
                                <option value="Hombre">Hombre</option>
                                <option value="Mujer">Mujer</option>
                                <option value="Otro">Otro</option>
                            </select >
                        </div>
                        <c:if test="${requestScope.msj!=null}">
                            <div>
                                ${requestScope.msj}
                            </div>
                        </c:if>
                    </div>
                    <div class="contenedorBotonFormulario">
                        <input id="botonVolverFormulario" type="button" onclick="location.pathname = 'TFG/Principal/inicio'" value="Volver">
                        <input id="botonEnviar" type="submit" value="Registrarse">
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="/TFG/js/formularios/formulariosAJAXJS.js"></script>
    </body>
</html>

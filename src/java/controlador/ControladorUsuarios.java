package controlador;

import entidades.Amigo;
import entidades.Bloqueado;
import entidades.Personaje;
import entidades.Pertenecemesa;
import entidades.Pideamistad;
import entidades.Usuario;
import java.io.IOException;
import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/Usuarios/*"})
public class ControladorUsuarios extends HttpServlet {

    @PersistenceContext(unitName = "PersistenceLocalOracle")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion;
        accion = request.getPathInfo();
        String vista = "";

        HttpSession session;
        boolean conseguido;
        String msj;
        Object result;

        Usuario user;
        Usuario useraux;
        Amigo amigo;
        Bloqueado bloqueado;
        Pideamistad pamistad;
        Personaje personaje;

        TypedQuery<Usuario> queryUsuarios;
        TypedQuery<Amigo> queryAmigos;
        TypedQuery<Bloqueado> queryBloqueados;
        TypedQuery<Pideamistad> queryPideAmistad;
        TypedQuery<Pertenecemesa> queryPMesas;
        TypedQuery<Personaje> queryPersonajes;

        Query queryAUX;

        List<Usuario> listaUsuarios;
        List<Pideamistad> listaPideAmistad;
        List<Pertenecemesa> listaPerteneceMesa;
        List<String> pertenecemesaUsuarios;
        List<String> imagenesUsuarios;

        String id;
        String apodo;
        String nombre;
        String correo;
        String contrasena;
        String contrasenaAuxiliar;
        String contrasenahash;
        String telefono;
        String fechaNacimientoString;
        String provincia;
        String genero;
        String contrasenaAntigua;

        String ordenar;
        String mesa;
        String peticiones;
        String numString;
        int num;
        int numPag;

        String sql;

        switch (accion) {
            case "/crearUsuario":
                user = null;
                conseguido = false;
                msj = "";

                apodo = request.getParameter("nombre_usuario");
                nombre = request.getParameter("nombre_real");
                correo = request.getParameter("correo_usuario");
                contrasena = request.getParameter("usuario_contrasena");
                telefono = request.getParameter("usuario_telefono");
                fechaNacimientoString = request.getParameter("usuario_nacimiento");
                provincia = request.getParameter("provincia");
                genero = request.getParameter("genero");

                if (nombre != null && apodo != null && correo != null && contrasena != null && fechaNacimientoString != null
                        && provincia != null && genero != null) {

                    try {

                        //////////////////
                        //////NOMBRE//////
                        ////////////////// 
                        comprobarCadena(nombre, "El nombre no es válido");

                        //////////////////////
                        //////CONTRASEÑA//////
                        //////////////////////
                        comprobarCadena(contrasena, "La contraseña no es válida");

                        //////////////////////
                        /////////HASH/////////
                        //////////////////////
                        contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

                        /////////////////
                        //////FECHA//////
                        /////////////////
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = formatoFecha.parse(fechaNacimientoString);

                        Date fechaActual = new Date();
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -12);
                        fechaActual = calendarioAux.getTime();

                        // Comparar las fechas
                        if (!fechaNacimiento.before(fechaActual)) {
                            throw new Exception("La fecha de nacimiento debe ser más pequeña que " + fechaActual.getDate()
                                    + "/" + (fechaActual.getMonth() + 1) + "/" + (fechaActual.getYear() + 1900));
                        }

                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -120);
                        fechaActual = calendarioAux.getTime();
                        if (!fechaActual.before(fechaNacimiento)) {
                            throw new Exception("La fecha de nacimiento debe ser más grande que " + fechaActual.getDate()
                                    + "/" + (fechaActual.getMonth() + 1) + "/" + (fechaActual.getYear() + 1900));
                        }

                        /////////////////////
                        /////////APODO///////
                        /////////////////////
                        comprobarCadena(apodo, "El nombre de usuario no es válido");

                        queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                        queryUsuarios.setParameter("apodo", apodo);
                        listaUsuarios = queryUsuarios.getResultList();

                        if (!listaUsuarios.isEmpty()) {
                            throw new Exception("El Nombre de usuario debe ser único ");
                        }
                        //////////////////////////
                        //////////CORREO//////////
                        //////////////////////////
                        
                        comprobarCadena(correo, "El correo no es válido");
                        
                        
                        queryUsuarios = em.createNamedQuery("Usuario.findByCorreo", Usuario.class);
                        queryUsuarios.setParameter("correo", correo);
                        listaUsuarios = queryUsuarios.getResultList();

                        if (!listaUsuarios.isEmpty()) {
                            throw new Exception("El Correo debe ser único ");
                        }

                        ///////////////////////////
                        //////////TELEFONO/////////
                        ///////////////////////////
                        BigInteger telefonoBI;

                        if (telefono != null && !telefono.equals("")) {
                            telefonoBI = new BigInteger(telefono);

                            queryUsuarios = em.createNamedQuery("Usuario.findByTelefono", Usuario.class);
                            queryUsuarios.setParameter("telefono", telefonoBI);
                            listaUsuarios = queryUsuarios.getResultList();

                            if (!listaUsuarios.isEmpty()) {
                                throw new Exception("El Teléfono debe ser único ");
                            }

                        } else {
                            telefonoBI = null;
                        }

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        user = new Usuario(apodo, nombre, correo, contrasenahash, fechaNacimiento, provincia, genero, (short) 0);
                        user.setTelefono(telefonoBI);
                        persist(user);
                        System.out.println("Registrado: " + nombre);
                        conseguido = true;

                    } catch (NumberFormatException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible registrar en este momento: " + nombre);
                        System.out.println("NumberFormatException: " + ex.getMessage());
                    } catch (ParseException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible registrar en este momento: " + nombre);
                        System.out.println("ParseException: " + ex.getMessage());
                    } catch (RuntimeException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible registrar en este momento: " + nombre);
                        System.out.println("ParseException: " + ex.getMessage());
                    } catch (Exception ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                        System.out.println("Exception: " + ex.getMessage());
                    }
                } else {
                    msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                    System.out.println("Error: Introduzca los campos de forma correcta ");
                }
                if (conseguido == true) {

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    session.setAttribute("user", user);
                    vista = "/Principal/inicio";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/Formularios/registro";
                }
                break;
            case "/iniciarSesion":
                        
                try {
                apodo = request.getParameter("nombre_usuario");
                contrasena = request.getParameter("usuario_contrasena");

                /////////////////////
                /////////APODO///////
                /////////////////////
                comprobarCadena(apodo, "El nombre de usuario no es válido");

                queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                queryUsuarios.setParameter("apodo", apodo);
                listaUsuarios = queryUsuarios.getResultList();

                if (listaUsuarios.isEmpty()) {
                    throw new Exception("El nombre de usuario no se encuentra ");
                } 
                //////////////////////
                //////CONTRASEÑA//////
                //////////////////////
                else {
                    comprobarCadena(contrasena, "La cadena no es válida");

                    user = listaUsuarios.remove(0);
                    System.out.println("Contraseña que doy: " + contrasena);
                    System.out.println("Contraseña que aporto: " + user.getContrasena());
                    if (!BCrypt.checkpw(contrasena, user.getContrasena())) {
                        throw new Exception("La contraseña no es correcta");
                    } else {
                        /////////////////////////
                        /////////SESION//////////
                        /////////////////////////
                        session = request.getSession();

                        //////////////////////////////
                        //////////PETICIONES//////////
                        //////////////////////////////
                        queryPideAmistad = em.createNamedQuery("Pideamistad.findByAcepta", Pideamistad.class);
                        queryPideAmistad.setParameter("acepta", user.getId());
                        listaPideAmistad = queryPideAmistad.getResultList();

                        if (listaPideAmistad.isEmpty() == false) {
                            peticiones = String.valueOf(listaPideAmistad.size());
                            session.setAttribute("peticiones", peticiones);
                        }

                        //////////////////////////
                        /////////USUARIO//////////
                        //////////////////////////
                        session.setAttribute("user", user);

                        vista = "/Principal/inicio";
                        System.out.println("Identificado: " + user.getNombre());
                    }
                }
            } catch (Exception ex) {
                vista = "/Formularios/iniciosesion";
                if (!ex.getMessage().equals("null")) {
                    msj = "<p style=\"margin-top: 25px\"> Error: " + ex.getMessage() + "</p>";
                    request.setAttribute("msj", msj);
                }
                System.out.println("Exception: " + ex.getMessage());
            }
            break;
            case "/cerrarSesion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                session.invalidate();
                vista = "/Principal/inicio";
                break;
            case "/modificarUsuario":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    conseguido = false;
                    msj = "";
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    id = user.getId();
                    apodo = request.getParameter("nombre_usuario");
                    nombre = request.getParameter("nombre_real");
                    correo = request.getParameter("correo_usuario");
                    contrasena = request.getParameter("usuario_contrasena");
                    telefono = request.getParameter("usuario_telefono");
                    fechaNacimientoString = request.getParameter("usuario_nacimiento");
                    provincia = request.getParameter("provincia");
                    genero = request.getParameter("genero");

                    if (nombre != null && apodo != null && correo != null && contrasena != null && fechaNacimientoString != null
                            && provincia != null && genero != null) {

                        try {
                            /////////////////
                            //////FECHA//////
                            /////////////////

                            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaNacimiento = formatoFecha.parse(fechaNacimientoString);

                            Date fechaActual = new Date();
                            Calendar calendarioAux = Calendar.getInstance();
                            calendarioAux.setTime(fechaActual);
                            calendarioAux.add(Calendar.YEAR, -10);

                            fechaActual = calendarioAux.getTime();

                            // Comparar las fechas
                            if (!fechaNacimiento.before(fechaActual)) {
                                throw new Exception("La fecha de nacimiento debe ser más pequeña que " + fechaActual.getDate()
                                        + "/" + (fechaActual.getMonth() + 1) + "/" + (fechaActual.getYear() + 1900));
                            }

                            calendarioAux.setTime(fechaActual);
                            calendarioAux.add(Calendar.YEAR, -120);
                            fechaActual = calendarioAux.getTime();
                            if (!fechaActual.before(fechaNacimiento)) {
                                throw new Exception("La fecha de nacimiento debe ser más grande que " + fechaActual.getDate()
                                        + "/" + (fechaActual.getMonth() + 1) + "/" + (fechaActual.getYear() + 1900));
                            }

                            /////////////////////
                            ////////NOMBRE///////
                            /////////////////////
                            comprobarCadena(nombre, "El nombre no es válida");

                            /////////////////////
                            ////////APODO////////
                            /////////////////////
                            comprobarCadena(apodo, "El nombre de usuario no es válida");

                            queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            listaUsuarios = queryUsuarios.getResultList();

                            if (!listaUsuarios.isEmpty() && !listaUsuarios.get(0).getId().equals(user.getId())) {
                                throw new Exception("El Nombre de usuario debe ser único ");
                            }
                            //////////////////////////
                            //////////CORREO//////////
                            //////////////////////////
                            comprobarCadena(correo, "El correo no es válido");

                            queryUsuarios = em.createNamedQuery("Usuario.findByCorreo", Usuario.class);
                            queryUsuarios.setParameter("correo", correo);
                            listaUsuarios = queryUsuarios.getResultList();

                            if (!listaUsuarios.isEmpty() && !listaUsuarios.get(0).getCorreo().equals(user.getCorreo())) {
                                throw new Exception("El Correo debe ser único ");
                            }

                            ///////////////////////////
                            //////////TELEFONO/////////
                            ///////////////////////////
                            BigInteger telefonoBI;

                            if (telefono != null && !telefono.equals("")) {
                                telefonoBI = new BigInteger(telefono);

                                queryUsuarios = em.createNamedQuery("Usuario.findByTelefono", Usuario.class);
                                queryUsuarios.setParameter("telefono", telefonoBI);
                                listaUsuarios = queryUsuarios.getResultList();

                                if (!listaUsuarios.isEmpty() && !listaUsuarios.get(0).getTelefono().equals(user.getTelefono())) {
                                    throw new Exception("El Teléfono debe ser único ");
                                }

                            } else {
                                telefonoBI = null;
                            }

                            //////////////////////
                            //////CONTRASEÑA//////
                            //////////////////////
                            comprobarCadena(contrasena, "La contraseña no es válida");

                            //////////////////////
                            /////////HASH/////////
                            //////////////////////
                            contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

                            //////////////////////////////
                            //////////MODIFICAMOS/////////
                            //////////////////////////////
                            user = new Usuario(apodo, nombre, correo, contrasenahash, fechaNacimiento, provincia, genero, (short) 0);
                            user.setTelefono(telefonoBI);
                            user.setId(id);

                            update(user);
                            System.out.println("Modificado: " + nombre);
                            conseguido = true;

                        } catch (NumberFormatException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible modificar en este momento: " + nombre);
                            System.out.println("NumberFormatException: " + ex.getMessage());
                            conseguido = false;
                        } catch (ParseException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible modificar en este momento: " + nombre);
                            System.out.println("ParseException: " + ex.getMessage());
                            conseguido = false;
                        } catch (RuntimeException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible modificar en este momento: " + nombre);
                            System.out.println("ParseException: " + ex.getMessage());
                            conseguido = false;
                        } catch (Exception ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                            System.out.println("Exception: " + ex.getMessage());
                            conseguido = false;
                        }
                    } else {
                        msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                        System.out.println("Error: Introduzca los campos de forma correcta ");
                    }
                    if (conseguido == true) {

                        /////////////////////////
                        /////////SESION//////////
                        /////////////////////////
                        session.invalidate();
                        session = request.getSession();
                        session.setAttribute("user", user);
                        vista = "/Usuarios/perfil";
                    } else {
                        request.setAttribute("msj", msj);
                        vista = "/Formularios/modificarusuario";
                    }
                }
                break;

            case "/mostrarUsuarios":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega mesa: " + mesa);

                    /////////////////////////////////////
                    /////////NUMERO DE USUARIOS//////////
                    /////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM USUARIO u "
                                + "WHERE u.APODO <> '" + user.getApodo() + "' "
                                + "AND u.ID NOT IN ("
                                + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')";
                    } else {
                        sql = "SELECT DISTINCT COUNT(*) FROM USUARIO u "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u.id = p2.usuario "
                                + "WHERE u.APODO <> '" + user.getApodo() + "'" + " AND p1.mesa = p2.mesa  "
                                + "AND u.ID NOT IN ("
                                + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                + "    UNION "
                                + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u.* FROM USUARIO u "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo ASC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";

                            } else {
                                sql = "SELECT DISTINCT u.* FROM USUARIO u "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u.id = p2.usuario "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'" + " AND p1.mesa = p2.mesa  "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo ASC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u.* FROM USUARIO u "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo DESC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u.* FROM USUARIO u "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u.id = p2.usuario "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'" + " AND p1.mesa = p2.mesa  "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADO b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADO b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGO a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGO a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo DESC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();
                    imagenesUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaUsuarios.get(i).getPersonajeactual() != null) {
                            imagenesUsuarios.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaUsuarios.get(i).getPersonajeactual().getId());
                        } else {
                            imagenesUsuarios.add("-");
                        }

                        boolean encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale mesa:" + mesa);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaUsuarios", listaUsuarios);
                    request.setAttribute("listaImagenes", imagenesUsuarios);
                    request.setAttribute("listaMesas", pertenecemesaUsuarios);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("mesa", mesa);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/usuario/usuarios.jsp";
                }
                break;

            case "/perfil":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    request.setAttribute("numpersonajes", (user.getPersonajesList() != null) ? user.getPersonajesList().size() : 0);
                    if (user.getPersonajeactual() != null) {
                        request.setAttribute("personajeactual", user.getPersonajeactual());
                        request.setAttribute("imagenactual", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + user.getPersonajeactual().getId());
                    } else {
                        request.setAttribute("personajeactual", user.getPersonajeactual());
                    }
                    vista = "/WEB-INF/jsp/usuario/perfil.jsp";
                }
                break;

            ///////////////////////////////////////////////////////////////////////////
            //////////////////////////////PEDIR AMISTAD////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            case "/mostrarPeticionesEnviadas":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega mesa: " + mesa);

                    ///////////////////////////////////////
                    /////////NUMERO DE PETICIONES//////////
                    ///////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                + "INNER JOIN Usuario u2 ON p.acepta = u2.apodo "
                                + "WHERE p.pide = '" + user.getId() + "'";
                    } else {
                        sql = "SELECT DISTINCT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                + "INNER JOIN Usuario u2 ON p.acepta = u2.apodo "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE p.pide = '" + user.getId() + "' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    peticiones = "Enviadas";

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuario u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuario u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuario u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuario u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();
                    imagenesUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaUsuarios.get(i).getPersonajeactual() != null) {
                            imagenesUsuarios.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaUsuarios.get(i).getPersonajeactual().getId());
                        } else {
                            imagenesUsuarios.add("-");
                        }

                        boolean encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {

                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale mesa:" + mesa);
                    System.out.println("Sale npag:" + numPag);
                    System.out.println(peticiones);

                    request.setAttribute("listaUsuarios", listaUsuarios);
                    request.setAttribute("listaImagenes", imagenesUsuarios);
                    request.setAttribute("listaMesas", pertenecemesaUsuarios);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("mesa", mesa);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag
                    request.setAttribute("peticiones", peticiones);//peticiones

                    vista = "/WEB-INF/jsp/usuario/peticiones.jsp";
                }
                break;

            case "/mostrarPeticionesRecibidas":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    if (session.getAttribute("peticiones") != null) {
                        session.removeAttribute("peticiones");
                    }

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega mesa: " + mesa);

                    ///////////////////////////////////////
                    /////////NUMERO DE PETICIONES//////////
                    ///////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                + "WHERE p.acepta = '" + user.getId() + "'";
                    } else {
                        sql = "SELECT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE p.acepta = '" + user.getId() + "' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    peticiones = "Recibidas";

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuario u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuario u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();
                    imagenesUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaUsuarios.get(i).getPersonajeactual() != null) {
                            imagenesUsuarios.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaUsuarios.get(i).getPersonajeactual().getId());
                        } else {
                            imagenesUsuarios.add("-");
                        }

                        boolean encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale mesa:" + mesa);
                    System.out.println("Sale npag:" + numPag);
                    System.out.println(peticiones);

                    request.setAttribute("listaUsuarios", listaUsuarios);
                    request.setAttribute("listaImagenes", imagenesUsuarios);
                    request.setAttribute("listaMesas", pertenecemesaUsuarios);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("mesa", mesa);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag
                    request.setAttribute("peticiones", peticiones);//para saber si es enviadas o recibidas

                    vista = "/WEB-INF/jsp/usuario/peticiones.jsp";
                }
                break;
            case "/enviarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("pamistad");

                    queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                    queryPideAmistad.setParameter("pide", id);
                    queryPideAmistad.setParameter("acepta", user.getId());
                    listaPideAmistad = queryPideAmistad.getResultList();

                    //Si te la han mandado a ti, lo añadimos a amigos directamente.
                    if (listaPideAmistad.isEmpty() == false) {
                        delete(listaPideAmistad.get(0));

                        amigo = new Amigo(user.getId(), id);
                        persist(amigo);
                        amigo = new Amigo(id, user.getId());
                        persist(amigo);
                    } else {
                        pamistad = new Pideamistad(user.getId(), id);
                        persist(pamistad);
                    }

                    vista = "/Usuarios/mostrarUsuarios";
                }
                break;
            case "/eliminarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("pamistad");

                    queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                    queryPideAmistad.setParameter("pide", user.getId());
                    queryPideAmistad.setParameter("acepta", id);
                    pamistad = queryPideAmistad.getResultList().get(0);

                    delete(pamistad);

                    vista = "/Usuarios/mostrarPeticionesEnviadas";
                }
                break;
            case "/aceptarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("pamistad");

                    amigo = new Amigo(user.getId(), id);
                    persist(amigo);
                    amigo = new Amigo(id, user.getId());
                    persist(amigo);

                    queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                    queryPideAmistad.setParameter("pide", id);
                    queryPideAmistad.setParameter("acepta", user.getId());
                    pamistad = queryPideAmistad.getResultList().get(0);

                    delete(pamistad);

                    vista = "/Usuarios/mostrarPeticionesRecibidas";
                }
                break;
            case "/rechazarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("pamistad");

                    queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                    queryPideAmistad.setParameter("pide", id);
                    queryPideAmistad.setParameter("acepta", user.getId());
                    pamistad = queryPideAmistad.getResultList().get(0);

                    delete(pamistad);

                    vista = "/Usuarios/mostrarPeticionesRecibidas";
                }
                break;

            ///////////////////////////////////////////////////////////////////////////
            //////////////////////////////////AMIGOS///////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            case "/mostrarAmigos":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega mesa: " + mesa);

                    ///////////////////////////////////
                    /////////NUMERO DE AMIGOS//////////
                    ///////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                + "WHERE a.amigo1 = '" + user.getId() + "'";
                    } else {
                        sql = "SELECT DISTINCT COUNT(*) FROM Usuario u "
                                + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE a.amigo1 = '" + user.getId() + "' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuario u "
                                        + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE a.amigo1 = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuario u "
                                        + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuario u "
                                        + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE a.amigo1 = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
                    listaUsuarios = queryAUX.getResultList();

                    imagenesUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        if (listaUsuarios.get(i).getPersonajeactual() != null) {
                            imagenesUsuarios.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaUsuarios.get(i).getPersonajeactual().getId());
                        } else {
                            imagenesUsuarios.add("-");
                        }
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale mesa:" + mesa);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaUsuarios", listaUsuarios);
                    request.setAttribute("listaImagenes", imagenesUsuarios);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("mesa", mesa);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/usuario/amigos.jsp";
                }
                break;

            case "/mostrarAmigo":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("amigo");

                    queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                    queryUsuarios.setParameter("id", id);
                    useraux = queryUsuarios.getSingleResult();

                    queryAmigos = em.createNamedQuery("Amigo.findByAmigos", Amigo.class);
                    queryAmigos.setParameter("amigo1", id);
                    queryAmigos.setParameter("amigo2", user.getId());

                    try {
                        amigo = queryAmigos.getSingleResult();
                        System.out.println("Somos amigos");
                        request.setAttribute("sonAmigos", 1);
                    } catch (Exception ex) {
                        request.setAttribute("sonAmigos", 0);
                    }

                    request.setAttribute("numpersonajes", (useraux.getPersonajesList() != null) ? useraux.getPersonajesList().size() : 0);
                    if (useraux.getPersonajeactual() != null) {
                        request.setAttribute("personajeactual", useraux.getPersonajeactual());
                        request.setAttribute("imagenactual", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + useraux.getPersonajeactual().getId());
                    }
                    request.setAttribute("amigo", useraux);

                    vista = "/WEB-INF/jsp/usuario/amigo.jsp";
                }
                break;

            case "/eliminarAmigo":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("amigo");

                    queryAmigos = em.createNamedQuery("Amigo.findByAmigos", Amigo.class);
                    queryAmigos.setParameter("amigo1", user.getId());
                    queryAmigos.setParameter("amigo2", id);
                    amigo = queryAmigos.getResultList().get(0);

                    delete(amigo);

                    queryAmigos = em.createNamedQuery("Amigo.findByAmigos", Amigo.class);
                    queryAmigos.setParameter("amigo1", id);
                    queryAmigos.setParameter("amigo2", user.getId());
                    amigo = queryAmigos.getResultList().get(0);

                    delete(amigo);

                    vista = "/Usuarios/mostrarAmigos";
                }
                break;

            /////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////BLOQUEAR///////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////
            case "/mostrarBloqueados":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);

                    /////////////////////////////////////
                    /////////NUMERO DE BLOQUEADOS////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM Usuario u "
                            + "INNER JOIN Bloqueado b ON u.id = b.bloqueador "
                            + "INNER JOIN Usuario u2 ON b.bloqueado = u2.id "
                            + "WHERE b.bloqueador = '" + user.getId() + "'";

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    if (ordenar == null || numString == null) {

                        ordenar = "ordenar1";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT u2.* FROM Usuario u "
                                    + "INNER JOIN Bloqueado b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuario u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "ORDER BY u2.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT u2.* FROM Usuario u "
                                    + "INNER JOIN Bloqueado b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuario u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "ORDER BY u2.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
                    listaUsuarios = queryAUX.getResultList();

                    imagenesUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        if (listaUsuarios.get(i).getPersonajeactual() != null) {
                            imagenesUsuarios.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaUsuarios.get(i).getPersonajeactual().getId());
                        } else {
                            imagenesUsuarios.add("-");
                        }
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaUsuarios", listaUsuarios);
                    request.setAttribute("listaImagenes", imagenesUsuarios);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/usuario/bloqueados.jsp";
                }
                break;

            case "/desbloquearUsuario":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("bloqueado");

                    queryBloqueados = em.createNamedQuery("Bloqueado.findByBloqueados", Bloqueado.class);
                    queryBloqueados.setParameter("bloqueador", user.getId());
                    queryBloqueados.setParameter("bloqueado", id);
                    bloqueado = queryBloqueados.getResultList().get(0);

                    delete(bloqueado);

                    vista = "/Usuarios/mostrarBloqueados";
                }
                break;
            case "/bloquearUsuario":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("bloqueado");

                    bloqueado = new Bloqueado(user.getId(), id);

                    persist(bloqueado);

                    vista = "/Usuarios/mostrarUsuarios";
                }
                break;

            /////////////////////////////////////////////////////////////////////////////
            ////////////////////////////PERSONAJE ACTUAL/////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////
            case "/personajeActual":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                id = request.getParameter("id");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Comprobamos que sea suyo
                    queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                    queryPersonajes.setParameter("id", id);
                    queryPersonajes.setParameter("creador", user);

                    try {
                        personaje = queryPersonajes.getSingleResult();
                        user.setPersonajeactual(personaje);
                        update(user);

                        vista = "/Usuarios/perfil";

                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            /////////////////////////////////////////////////////////////////////////////
            /////////////////////////RESTABLECER CONTRASEÑA//////////////////////////////
            /////////////////////////////////////////////////////////////////////////////
            case "/restablecerContraseña":

                id = request.getParameter("id");
                contrasenaAntigua = request.getParameter("contrasenaAntigua");
                contrasena = request.getParameter("contrasena_usuario_rc");
                contrasenaAuxiliar = request.getParameter("contrasenarp_usuario_rc");

                try {
                    //////////////////////
                    //////CONTRASEÑA//////
                    //////////////////////
                    comprobarCadena(contrasena, "La contraseña no es válida");

                    if (!contrasenaAuxiliar.equals(contrasena)) {
                        throw new Exception("Las contraseñas no son iguales");
                    }

                    //////////////////////
                    /////////HASH/////////
                    //////////////////////
                    contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

                    try {
                        queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                        queryUsuarios.setParameter("id", id);
                        user = queryUsuarios.getSingleResult();

                        user.setContrasena(contrasenahash);
                        update(user);

                        vista = "/Principal/inicio";
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                    msj = "<p style=\"margin-left: 10px\"> Error:" + ex.getMessage() + "</p>";
                    vista = "/Formularios/restablecercontraseña?msj=" + msj + "&id=" + id + "&password=" + contrasenaAntigua;
                }

                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);

        rd.forward(request, response);
    }

    //Lanza error por escribir un valor malicioso
    protected void comprobarCadena(String Cadena, String Mensaje) throws Exception {

        if (Cadena.toUpperCase().contains("UPDATE") || Cadena.toUpperCase().contains("CREATE")
                || Cadena.toUpperCase().contains("DELETE") || Cadena.toUpperCase().contains("SELECT")
                || Cadena.toUpperCase().contains("DROP")) {
            throw new Exception(Mensaje);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(Object object) {
        try {
            utx.begin();
            object = (Object) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void update(Object object) {
        try {
            utx.begin();
            em.merge((Usuario) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

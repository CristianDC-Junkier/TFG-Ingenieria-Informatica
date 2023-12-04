package controlador;

import entidades.Amigos;
import entidades.Bloqueados;
import entidades.Pideamistad;
import entidades.Usuarios;
import java.io.IOException;
import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

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

        Usuarios user = null;
        Amigos amigo = null;
        Bloqueados bloqueado = null;
        Pideamistad pamistad = null;

        TypedQuery<Usuarios> queryUsuarios;
        TypedQuery<Amigos> queryAmigos;
        TypedQuery<Bloqueados> queryBloqueados;
        TypedQuery<Pideamistad> queryPideAmistad;

        Query queryAUX;

        List<Usuarios> listaUsuarios;
        List<Pideamistad> listaPideAmistad;

        String id;
        String apodo;
        String nombre;
        String correo;
        String contrasena;
        String telefono;
        String fechaNacimientoString;
        String provincia;
        String genero;

        String ordenar;
        String mesa;
        String peticiones;
        String numString;
        int num;
        int numPag;

        String sql;

        switch (accion) {
            case "/crearUsuario":
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
                        if (nombre.toUpperCase().startsWith("UPDATE") || nombre.toUpperCase().startsWith("CREATE")
                                || nombre.toUpperCase().startsWith("DELETE") || nombre.toUpperCase().startsWith("SELECT")) {
                            throw new Exception("El Nombre no es válido");
                        }

                        //////////////////////
                        //////CONTRASEÑA//////
                        //////////////////////
                        if (contrasena.toUpperCase().startsWith("UPDATE") || contrasena.toUpperCase().startsWith("CREATE")
                                || contrasena.toUpperCase().startsWith("DELETE") || contrasena.toUpperCase().startsWith("SELECT")) {
                            throw new Exception("La contrasena no es válida");
                        }

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
                        if (fechaNacimiento.before(fechaActual)) {
                            conseguido = true;
                        } else {
                            throw new Exception("La fecha de nacimiento debe ser más pequeña que " + fechaActual);
                        }

                        /////////////////////
                        /////////APODO///////
                        /////////////////////
                        if (apodo.toUpperCase().startsWith("UPDATE") || apodo.toUpperCase().startsWith("CREATE")
                                || apodo.toUpperCase().startsWith("DELETE") || apodo.toUpperCase().startsWith("SELECT")) {
                            throw new Exception("El Nombre de usuario no es válido");
                        }

                        queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                        queryUsuarios.setParameter("apodo", apodo);
                        listaUsuarios = queryUsuarios.getResultList();

                        if (!listaUsuarios.isEmpty()) {
                            throw new Exception("El Nombre de usuario debe ser único ");
                        }
                        //////////////////////////
                        //////////CORREO//////////
                        //////////////////////////

                        queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                        queryUsuarios.setParameter("correo", apodo);
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

                            queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                            queryUsuarios.setParameter("correo", apodo);
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
                        user = new Usuarios(apodo, nombre, correo, contrasena, fechaNacimiento, provincia, genero, (short) 0);
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
                    vista = "/jsp/inicio/inicio.jsp";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/registro.jsp";
                }
                break;
            case "/iniciarSesion":
                        
                try {
                apodo = request.getParameter("nombre_usuario");
                contrasena = request.getParameter("usuario_contrasena");

                /////////////////////
                /////////APODO///////
                /////////////////////
                if (apodo.toUpperCase().startsWith("UPDATE") || apodo.toUpperCase().startsWith("CREATE")
                        || apodo.toUpperCase().startsWith("DELETE") || apodo.toUpperCase().startsWith("SELECT")) {
                    throw new Exception("El Nombre de usuario no es válido");
                }

                queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                queryUsuarios.setParameter("apodo", apodo);
                listaUsuarios = queryUsuarios.getResultList();

                if (listaUsuarios.isEmpty()) {
                    throw new Exception("El Nombre de usuario no se encuentra ");
                } //////////////////////
                //////CONTRASEÑA//////
                //////////////////////
                else if (contrasena.toUpperCase().startsWith("UPDATE") || contrasena.toUpperCase().startsWith("CREATE")
                        || contrasena.toUpperCase().startsWith("DELETE") || contrasena.toUpperCase().startsWith("SELECT")) {
                    throw new Exception("La contrasena no es válida");
                } else {
                    user = listaUsuarios.remove(0);

                    if (!user.getContrasena().equals(contrasena)) {
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
                        queryPideAmistad.setParameter("acepta", apodo);
                        listaPideAmistad = queryPideAmistad.getResultList();

                        if (listaPideAmistad.isEmpty() == false) {
                            peticiones = String.valueOf(listaPideAmistad.size());
                            session.setAttribute("peticiones", peticiones);
                        }

                        //////////////////////////
                        /////////USUARIO//////////
                        //////////////////////////
                        session.setAttribute("user", user);

                        vista = "/jsp/inicio/inicio.jsp";
                        System.out.println("Identificado: " + user.getNombre());
                    }
                }
            } catch (Exception ex) {
                vista = "/jsp/formularios/iniciosesion.jsp";
                msj = "<p style=\"margin-top: 25px\"> Error: " + ex.getMessage() + "</p>";
                request.setAttribute("msj", msj);
                System.out.println("Exception: " + ex.getMessage());
            }
            break;
            case "/cerrarSesion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                session.invalidate();
                vista = "/jsp/inicio/inicio.jsp";
                break;
            case "/modificarUsuario":

                conseguido = false;
                msj = "";
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

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
                        if (fechaNacimiento.before(fechaActual)) {
                            conseguido = true;
                        } else {

                            throw new Exception("La fecha de nacimiento debe ser más pequeña que " + fechaActual.getDate() + "/" + (fechaActual.getMonth() + 1) + "/" + (fechaActual.getYear() + 1900));
                        }

                        /////////////////////
                        /////////APODO///////
                        /////////////////////
                        if (apodo.toUpperCase().startsWith("UPDATE") || apodo.toUpperCase().startsWith("CREATE")
                                || apodo.toUpperCase().startsWith("DELETE") || apodo.toUpperCase().startsWith("SELECT")) {
                            throw new Exception("El Nombre de usuario no es válido");
                        }
                        queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                        queryUsuarios.setParameter("apodo", apodo);
                        listaUsuarios = queryUsuarios.getResultList();

                        if (!listaUsuarios.isEmpty() && !listaUsuarios.get(0).getId().equals(user.getId())) {
                            throw new Exception("El Nombre de usuario debe ser único ");
                        }
                        //////////////////////////
                        //////////CORREO//////////
                        //////////////////////////
                        queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                        queryUsuarios.setParameter("correo", apodo);
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

                            queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                            queryUsuarios.setParameter("correo", apodo);
                            listaUsuarios = queryUsuarios.getResultList();

                            if (!listaUsuarios.isEmpty() && !listaUsuarios.get(0).getId().equals(user.getId())) {
                                throw new Exception("El Teléfono debe ser único ");
                            }

                        } else {
                            telefonoBI = null;
                        }

                        //////////////////////
                        //////CONTRASEÑA//////
                        //////////////////////
                        if (contrasena.toUpperCase().startsWith("UPDATE") || contrasena.toUpperCase().startsWith("CREATE")
                                || contrasena.toUpperCase().startsWith("DELETE") || contrasena.toUpperCase().startsWith("SELECT")) {
                            throw new Exception("La contrasena no es válida");
                        }

                        //////////////////////////////
                        //////////MODIFICAMOS/////////
                        //////////////////////////////
                        user = new Usuarios(apodo, nombre, correo, contrasena, fechaNacimiento, provincia, genero, (short) 0);
                        user.setTelefono(telefonoBI);
                        user.setId(id);

                        update(user);
                        System.out.println("Modificado: " + nombre);
                        conseguido = true;

                    } catch (NumberFormatException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible modificar en este momento: " + nombre);
                        System.out.println("NumberFormatException: " + ex.getMessage());
                    } catch (ParseException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible modificar en este momento: " + nombre);
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
                    session.setAttribute("user", user);
                    vista = "/jsp/usuario/perfil.jsp";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/modificarusuario.jsp";
                }
                break;

            case "/mostrarUsuarios":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////////////
                /////////NUMERO DE USUARIOS//////////
                /////////////////////////////////////
                sql = "SELECT COUNT(*) FROM USUARIOS u "
                        + "WHERE u.APODO <> '" + user.getApodo() + "'"
                        + "AND u.APODO NOT IN ("
                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getApodo() + "'"
                        + "    UNION "
                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getApodo() + "'"
                        + "    UNION "
                        + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getApodo() + "'"
                        + "    UNION "
                        + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getApodo() + "'"
                        + "    UNION "
                        + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getApodo() + "')";

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                mesa = request.getParameter("mesa");//si filtramos por mesa o no
                peticiones = "Enviadas";

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega mesa: " + mesa);

                if (ordenar == null || mesa == null || numString == null) {

                    ordenar = "ordenar1";
                    mesa = "false";
                    numString = "1";
                    num = 0;

                } else {

                    num = (Integer.valueOf(numString) - 1) * 10;//offset
                }

                switch (ordenar) {
                    case "ordenar1":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u.* FROM USUARIOS u "
                                    + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                    + "AND u.APODO NOT IN ("
                                    + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getApodo() + "')"
                                    + "    ORDER BY u.apodo DESC "
                                    + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u.* FROM USUARIOS u "
                                    + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                    + "AND u.APODO NOT IN ("
                                    + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getApodo() + "')"
                                    + "    ORDER BY u.apodo DESC "
                                    + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u.* FROM USUARIOS u "
                                    + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                    + "AND u.APODO NOT IN ("
                                    + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getApodo() + "')"
                                    + "    ORDER BY u.apodo ASC "
                                    + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u.* FROM USUARIOS u "
                                    + "WHERE u.APODO <> '" + user.getApodo() + "'"
                                    + "AND u.APODO NOT IN ("
                                    + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getApodo() + "'"
                                    + "    UNION "
                                    + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getApodo() + "')"
                                    + "    ORDER BY u.apodo ASC "
                                    + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar3":
                        break;
                    case "ordenar4":
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaUsuarios = queryAUX.getResultList();

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale mesa:" + mesa);
                System.out.println("Sale npag:" + numPag);
                System.out.println(peticiones);

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.setAttribute("orden", ordenar);
                request.setAttribute("mesa", mesa);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/jsp/usuario/usuarios.jsp";
                break;

            ///////////////////////////////////////////////////////////////////////////
            //////////////////////////////PEDIR AMISTAD////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            case "/mostrarPeticionesEnviadas":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                ///////////////////////////////////////
                /////////NUMERO DE PETICIONES//////////
                ///////////////////////////////////////
                sql = "SELECT COUNT(*) FROM Usuarios u "
                        + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                        + "WHERE p.pide = '" + user.getApodo() + "'";

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                mesa = request.getParameter("mesa");//si filtramos por mesa o no
                peticiones = "Enviadas";

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega mesa: " + mesa);

                if (ordenar == null || mesa == null || numString == null) {

                    ordenar = "ordenar1";
                    mesa = "false";
                    numString = "1";
                    num = 0;

                } else {

                    num = (Integer.valueOf(numString) - 1) * 10;//offset
                }

                switch (ordenar) {
                    case "ordenar1":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                    + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                    + "WHERE p.pide = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                    + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                    + "WHERE p.pide = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                    + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                    + "WHERE p.pide = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                    + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                    + "WHERE p.pide = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar3":
                        break;
                    case "ordenar4":
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaUsuarios = queryAUX.getResultList();

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale mesa:" + mesa);
                System.out.println("Sale npag:" + numPag);
                System.out.println(peticiones);

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.setAttribute("orden", ordenar);
                request.setAttribute("mesa", mesa);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag
                request.setAttribute("peticiones", peticiones);//peticiones

                vista = "/jsp/usuario/peticiones.jsp";
                break;

            case "/mostrarPeticionesRecibidas":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                session.invalidate();

                session = request.getSession();
                session.setAttribute("user", user);

                ///////////////////////////////////////
                /////////NUMERO DE PETICIONES//////////
                ///////////////////////////////////////
                sql = "SELECT COUNT(*) FROM Usuarios u "
                        + "INNER JOIN Pideamistad p ON u.apodo = p.acepta "
                        + "INNER JOIN Usuarios u2 ON p.pide = u2.apodo "
                        + "WHERE p.acepta = '" + user.getApodo() + "'";

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                mesa = request.getParameter("mesa");//si filtramos por mesa o no
                peticiones = "Recibidas";

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega mesa: " + mesa);

                if (ordenar == null || mesa == null || numString == null) {

                    ordenar = "ordenar1";
                    mesa = "false";
                    numString = "1";
                    num = 0;

                } else {

                    num = (Integer.valueOf(numString) - 1) * 10;//offset
                }

                switch (ordenar) {
                    case "ordenar1":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.acepta "
                                    + "INNER JOIN Usuarios u2 ON p.pide = u2.apodo "
                                    + "WHERE p.acepta = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.acepta "
                                    + "INNER JOIN Usuarios u2 ON p.pide = u2.apodo "
                                    + "WHERE p.acepta = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.acepta "
                                    + "INNER JOIN Usuarios u2 ON p.pide = u2.apodo "
                                    + "WHERE p.acepta = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Pideamistad p ON u.apodo = p.acepta "
                                    + "INNER JOIN Usuarios u2 ON p.pide = u2.apodo "
                                    + "WHERE p.acepta = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar3":
                        break;
                    case "ordenar4":
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaUsuarios = queryAUX.getResultList();

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale mesa:" + mesa);
                System.out.println("Sale npag:" + numPag);
                System.out.println(peticiones);

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.setAttribute("orden", ordenar);
                request.setAttribute("mesa", mesa);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag
                request.setAttribute("peticiones", peticiones);//para saber si es enviadas o recibidas

                vista = "/jsp/usuario/peticiones.jsp";
                break;
            case "/enviarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("pamistad");

                queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                queryPideAmistad.setParameter("pide", nombre);
                queryPideAmistad.setParameter("acepta", user.getApodo());
                listaPideAmistad = queryPideAmistad.getResultList();

                //Si te la han mandado a ti, lo añadimos a amigos directamente.
                if (listaPideAmistad.isEmpty() == false) {
                    delete(listaPideAmistad.get(0));

                    amigo = new Amigos(user.getApodo(), nombre);
                    persist(amigo);
                    amigo = new Amigos(nombre, user.getApodo());
                    persist(amigo);
                } else {
                    pamistad = new Pideamistad(user.getApodo(), nombre);
                    persist(pamistad);
                }

                vista = "/Usuarios/mostrarUsuarios";
                break;
            case "/eliminarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("pamistad");

                queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                queryPideAmistad.setParameter("pide", user.getApodo());
                queryPideAmistad.setParameter("acepta", nombre);
                pamistad = queryPideAmistad.getResultList().get(0);

                delete(pamistad);

                vista = "/Usuarios/mostrarPeticionesEnviadas";
                break;
            case "/aceptarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("pamistad");

                amigo = new Amigos(user.getApodo(), nombre);
                persist(amigo);
                amigo = new Amigos(nombre, user.getApodo());
                persist(amigo);

                queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                queryPideAmistad.setParameter("pide", nombre);
                queryPideAmistad.setParameter("acepta", user.getApodo());
                pamistad = queryPideAmistad.getResultList().get(0);

                delete(pamistad);

                vista = "/Usuarios/mostrarPeticionesRecibidas";
                break;
            case "/rechazarPeticion":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("pamistad");

                queryPideAmistad = em.createNamedQuery("Pideamistad.findByAceptar", Pideamistad.class);
                queryPideAmistad.setParameter("pide", nombre);
                queryPideAmistad.setParameter("acepta", user.getApodo());
                pamistad = queryPideAmistad.getResultList().get(0);

                delete(pamistad);

                vista = "/Usuarios/mostrarPeticionesRecibidas";
                break;

            ///////////////////////////////////////////////////////////////////////////
            //////////////////////////////////AMIGOS///////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            case "/mostrarAmigos":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                ///////////////////////////////////
                /////////NUMERO DE AMIGOS//////////
                ///////////////////////////////////
                sql = "SELECT COUNT(*) FROM Usuarios u "
                        + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                        + "WHERE a.amigo1 = '" + user.getApodo() + "'";

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                mesa = request.getParameter("mesa");//si filtramos por mesa o no

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega mesa: " + mesa);

                if (ordenar == null || mesa == null || numString == null) {

                    ordenar = "ordenar1";
                    mesa = "false";
                    numString = "1";
                    num = 0;

                } else {

                    num = (Integer.valueOf(numString) - 1) * 10;//offset
                }

                switch (ordenar) {
                    case "ordenar1":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                    + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                    + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                    + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                    + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (mesa.equalsIgnoreCase("false")) {
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                    + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                    + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                    + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                    + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                    + "ORDER BY u.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar3":
                        break;
                    case "ordenar4":
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaUsuarios = queryAUX.getResultList();

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale mesa:" + mesa);
                System.out.println("Sale npag:" + numPag);

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.setAttribute("orden", ordenar);
                request.setAttribute("mesa", mesa);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/jsp/usuario/amigos.jsp";
                break;

            case "/mostrarAmigo":

                apodo = (String) request.getParameter("amigo");
                System.out.println(apodo);

                queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                queryUsuarios.setParameter("apodo", apodo);
                user = queryUsuarios.getResultList().get(0);

                request.setAttribute("amigo", user);

                vista = "/jsp/usuario/amigo.jsp";
                break;

            case "/eliminarAmigo":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("amigo");

                queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                queryAmigos.setParameter("amigo1", user.getApodo());
                queryAmigos.setParameter("amigo2", nombre);
                amigo = queryAmigos.getResultList().get(0);

                delete(amigo);

                queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                queryAmigos.setParameter("amigo1", nombre);
                queryAmigos.setParameter("amigo2", user.getApodo());
                amigo = queryAmigos.getResultList().get(0);

                delete(amigo);

                vista = "/Usuarios/mostrarAmigos";
                break;

            /////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////BLOQUEAR///////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////
            case "/mostrarBloqueados":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////////////
                /////////NUMERO DE USUARIOS//////////
                /////////////////////////////////////
                sql = "SELECT COUNT(*) FROM Usuarios u "
                        + "INNER JOIN Bloqueados b ON u.apodo = b.bloqueador "
                        + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.apodo "
                        + "WHERE b.bloqueador = '" + user.getApodo() + "'";

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);

                if (ordenar == null || numString == null) {

                    ordenar = "ordenar1";
                    numString = "1";
                    num = 0;

                } else {

                    num = (Integer.valueOf(numString) - 1) * 10;//offset
                }

                switch (ordenar) {
                    case "ordenar1":
                        sql = "SELECT u2.* FROM Usuarios u "
                                + "INNER JOIN Bloqueados b ON u.apodo = b.bloqueador "
                                + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.apodo "
                                + "WHERE b.bloqueador = '" + user.getApodo() + "' "
                                + "ORDER BY u.apodo ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        break;
                    case "ordenar2":
                        sql = "SELECT u2.* FROM Usuarios u "
                                + "INNER JOIN Bloqueados b ON u.apodo = b.bloqueador "
                                + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.apodo "
                                + "WHERE b.bloqueador = '" + user.getApodo() + "' "
                                + "ORDER BY u.apodo DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        break;
                    case "ordenar3":
                        break;
                    case "ordenar4":
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaUsuarios = queryAUX.getResultList();

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale npag:" + numPag);

                request.setAttribute("listaUsuarios", listaUsuarios);
                request.setAttribute("orden", ordenar);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/jsp/usuario/bloqueados.jsp";
                break;

            case "/desbloquearUsuario":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("bloqueado");

                queryBloqueados = em.createNamedQuery("Bloqueados.findByBloqueados", Bloqueados.class);
                queryBloqueados.setParameter("bloqueador", user.getApodo());
                queryBloqueados.setParameter("bloqueado", nombre);
                bloqueado = queryBloqueados.getResultList().get(0);

                delete(bloqueado);

                vista = "/Usuarios/mostrarBloqueados";
                break;
            case "/bloquearUsuario":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();

                user = (Usuarios) session.getAttribute("user");
                nombre = request.getParameter("bloqueado");

                bloqueado = new Bloqueados(user.getApodo(), nombre);

                persist(bloqueado);

                vista = "/Usuarios/mostrarUsuarios";
                break;

        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
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
            em.merge((Usuarios) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package controlador;

import entidades.Clases;
import entidades.Personajes;
import entidades.Razas;
import entidades.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ControladorPersonajes", urlPatterns = {"/Personajes/*"})
public class ControladorPersonajes extends HttpServlet {

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

        TypedQuery<Usuarios> queryUsuarios;
        TypedQuery<Clases> queryClases;
        TypedQuery<Razas> queryRazas;
        TypedQuery<Personajes> queryPersonajes;

        List<Personajes> listaPersonajes;
        List<Usuarios> listaUsuariosAmigos;
        List<Usuarios> listaUsuarios;

        Query queryAUX;

        Usuarios user;
        Usuarios useraux;

        String id;

        String ordenar;
        String raza;
        String clase;
        String nivelString;
        String numString;
        String razaSQL;
        String claseSQL;
        String nivelSQL;

        String personaje_nombre;
        String personaje_clase;
        String personaje_subclase;
        String personaje_raza;
        String personaje_subraza;
        String personaje_trasfondo;
        String[] personaje_habilidades;
        String personaje_alineamiento;
        int personaje_edad;
        String personaje_apariencia;
        String personaje_rasgos;
        String personaje_defectos;
        String personaje_vinculos;
        String personaje_idiomas;
        String personaje_historia;

        int num;
        int numPag;
        int nivel;

        String sql;

        switch (accion) {
            case "/crearpersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                conseguido = false;
                msj = "";

                //Bloque 1
                personaje_nombre = request.getParameter("personaje_nombre");
                personaje_clase = request.getParameter("clase");
                personaje_subclase = request.getParameter("subclase");
                personaje_raza = request.getParameter("raza");
                personaje_subraza = request.getParameter("subraza");
                personaje_trasfondo = request.getParameter("trasfondo");
                //Bloque 2
                personaje_habilidades = request.getParameterValues("habilidades");
                //Bloque 3
                //Atributos
                //Bloque 4
                personaje_alineamiento = request.getParameter("alineamiento");
                personaje_edad = Integer.valueOf(request.getParameter("personaje_edad"));
                personaje_apariencia = request.getParameter("personaje_apariencia");
                personaje_rasgos = request.getParameter("personaje_rasgos");
                personaje_defectos = request.getParameter("personaje_defectos");
                personaje_vinculos = request.getParameter("personaje_vinculos");
                personaje_idiomas = request.getParameter("personaje_idiomas");
                personaje_historia = request.getParameter("personaje_historia");
                
                /*if (nombre != null && apodo != null && correo != null && contrasena != null && fechaNacimientoString != null
                        && provincia != null && genero != null) {

                    try {

                        //////////////////
                        //////NOMBRE//////
                        ////////////////// //CONTEXT
                        if (nombre.toUpperCase().contains("UPDATE") || nombre.toUpperCase().contains("CREATE")
                                || nombre.toUpperCase().contains("DELETE") || nombre.toUpperCase().contains("SELECT")
                                || nombre.toUpperCase().contains("DROP")) {
                            throw new Exception("El Nombre no es válido");
                        }

                        //////////////////////
                        //////CONTRASEÑA//////
                        //////////////////////
                        if (contrasena.toUpperCase().contains("UPDATE") || contrasena.toUpperCase().contains("CREATE")
                                || contrasena.toUpperCase().contains("DELETE") || contrasena.toUpperCase().contains("SELECT")
                                || contrasena.toUpperCase().contains("DROP")) {
                            throw new Exception("La contrasena no es válida");
                        }

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
                        if (apodo.toUpperCase().contains("UPDATE") || apodo.toUpperCase().contains("CREATE")
                                || apodo.toUpperCase().contains("DELETE") || apodo.toUpperCase().contains("SELECT")
                                || apodo.toUpperCase().contains("DROP")) {
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

                            queryUsuarios = em.createNamedQuery("Usuarios.findByTelefono", Usuarios.class);
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
                        user = new Usuarios(apodo, nombre, correo, contrasenahash, fechaNacimiento, provincia, genero, (short) 0);
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
                    vista = "/Personajes/personajes";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/Formularios/crearpersonaje";
                }
                 */
                break;
            case "/personajes":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Para saber si estamos conectados o no
                if (user == null) {
                    id = "nulo";
                } else {
                    id = user.getId();
                }

                //Recogemos los datos
                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                raza = request.getParameter("raza");//como ordenar
                clase = request.getParameter("clase");//como ordenar
                nivelString = request.getParameter("nivel");//como ordenar

                //Comprobamos los datos
                if (ordenar == null) {

                    ordenar = "ordenar1";
                    raza = " ";
                    clase = " ";
                    nivelString = " ";
                    numString = "1";
                    num = 0;

                } else {
                    //Pagina actual
                    num = (Integer.valueOf(numString) - 1) * 6;//offset
                    //Nivel
                    nivel = Integer.parseInt(nivelString);

                    if (raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }
                }

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega raza: " + raza);
                System.out.println("Llega clase: " + clase);
                System.out.println("Llega nivel: " + nivelString);

                /////////////////////////////////////
                ////////NUMERO DE PERSONAJES/////////
                /////////////////////////////////////
                sql = "SELECT COUNT(*) FROM PERSONAJES p "
                        + "WHERE p.USUARIO <> '" + id + "' "
                        + nivelString
                        + clase
                        + raza;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                numPag = (((Number) result).intValue() / 7) + 1;

                switch (ordenar) {
                    case "ordenar1":
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelString
                                + clase
                                + raza
                                + "ORDER BY  p.NOMBRE ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                    case "ordenar2":
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelString
                                + clase
                                + raza
                                + "ORDER BY  p.NOMBRE DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaPersonajes = queryAUX.getResultList();

                listaUsuarios = new ArrayList();

                for (int i = 0; i < listaPersonajes.size(); i++) {
                    listaUsuarios.add(listaPersonajes.get(i).getUsuario());
                }
                request.setAttribute("listaPersonajes", listaPersonajes);
                request.setAttribute("listaCreador", listaUsuarios);

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale raza:" + raza);
                System.out.println("Sale clase:" + clase);
                System.out.println("Sale nivel:" + nivelString);
                System.out.println("Sale npag:" + numPag);

                queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                request.setAttribute("listaClases", queryClases.getResultList());
                queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                request.setAttribute("listaRazas", queryRazas.getResultList());

                request.setAttribute("orden", ordenar);
                request.setAttribute("filtroRaza", raza);
                request.setAttribute("filtroClase", clase);
                request.setAttribute("filtroNivel", nivelString);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/WEB-INF/jsp/personajes/personajes.jsp";

                break;
            case "/personajesAmigos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                        //Nivel
                        nivel = Integer.parseInt(nivelString);

                        if (raza.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + raza + "' ";
                        }
                        if (clase.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + clase + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + raza);
                    System.out.println("Llega clase: " + clase);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
                            + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                            + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                            + "p.USUARIO <> '" + user.getId() + "' "
                            + nivelSQL
                            + claseSQL
                            + razaSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY  p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaPersonajes = queryAUX.getResultList();

                    listaUsuarios = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        listaUsuarios.add(listaPersonajes.get(i).getUsuario());
                    }
                    request.setAttribute("listaPersonajes", listaPersonajes);
                    request.setAttribute("listaCreador", listaUsuarios);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + raza);
                    System.out.println("Sale clase:" + clase);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", raza);
                    request.setAttribute("filtroClase", clase);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesAmigos.jsp";
                }
                break;
            case "/personajesAmigo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || raza == null || clase == null || nivelString == null || numString == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                        //Nivel
                        nivel = Integer.parseInt(nivelString);

                        if (raza.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + raza + "' ";
                        }
                        if (clase.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + clase + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + raza);
                    System.out.println("Llega clase: " + clase);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
                            + "WHERE p.USUARIO = '" + id + "' "
                            + nivelString
                            + clase
                            + raza;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaPersonajes = queryAUX.getResultList();
                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + raza);
                    System.out.println("Sale clase:" + clase);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                    queryUsuarios.setParameter("id", id);
                    request.setAttribute("amigo", queryUsuarios.getSingleResult());

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", raza);
                    request.setAttribute("filtroClase", clase);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesAmigo.jsp";
                }
                break;
            case "/personajesPerfil":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || raza == null || clase == null || nivelString == null || numString == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                        //Nivel
                        nivel = Integer.parseInt(nivelString);

                        if (raza.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + raza + "' ";
                        }
                        if (clase.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + clase + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + raza);
                    System.out.println("Llega clase: " + clase);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
                            + "WHERE p.USUARIO = '" + user.getId() + "' "
                            + nivelSQL
                            + claseSQL
                            + razaSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaPersonajes = queryAUX.getResultList();

                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + raza);
                    System.out.println("Sale clase:" + clase);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", raza);
                    request.setAttribute("filtroClase", clase);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesPerfil.jsp";
                }
                break;
            case "/personaje":
                vista = "/WEB-INF/jsp/personaje/personaje.jsp";
                break;
            case "/personajeAmigo":
                vista = "/WEB-INF/jsp/personaje/personajeAmigo.jsp";
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

}

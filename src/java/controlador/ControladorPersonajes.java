package controlador;

import entidades.Clases;
import entidades.Personajes;
import entidades.Razas;
import entidades.Subclases;
import entidades.Subrazas;
import entidades.Trasfondos;
import entidades.Usuarios;
import java.io.IOException;
import java.math.BigInteger;
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
        TypedQuery<Subclases> querySubclases;
        TypedQuery<Razas> queryRazas;
        TypedQuery<Subrazas> querySubrazas;
        TypedQuery<Trasfondos> queryTrasfondos;
        TypedQuery<Personajes> queryPersonajes;

        List<Personajes> listaPersonajes;
        List<Usuarios> listaUsuariosAmigos;
        List<Usuarios> listaUsuarios;
        List<String> fotosPersonajes;

        Query queryAUX;

        Usuarios user;
        Usuarios useraux;
        Personajes personaje;

        String id;

        String ordenar;
        String razaString;
        String claseString;
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

        Clases clase;
        Subclases subclase;
        Razas raza;
        Subrazas subraza;
        Trasfondos transfondo;

        String[] personaje_habilidades;

        String atributo_constitucion;
        String atributo_fuerza;
        String atributo_destreza;
        String atributo_sabiduria;
        String atributo_inteligencia;
        String atributo_carisma;

        String personaje_alineamiento;
        String personaje_edad;
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
                atributo_constitucion = request.getParameter("atributo_constitución");
                atributo_fuerza = request.getParameter("atributo_fuerza");
                atributo_destreza = request.getParameter("atributo_destreza");
                atributo_sabiduria = request.getParameter("atributo_sabiduría");
                atributo_inteligencia = request.getParameter("atributo_inteligencia");
                atributo_carisma = request.getParameter("atributo_carisma");
                //Bloque 4
                personaje_alineamiento = request.getParameter("alineamiento");
                personaje_edad = request.getParameter("personaje_edad");
                personaje_apariencia = request.getParameter("personaje_apariencia");
                personaje_rasgos = request.getParameter("personaje_rasgos");
                personaje_defectos = request.getParameter("personaje_defectos");
                personaje_vinculos = request.getParameter("personaje_vinculos");
                personaje_idiomas = request.getParameter("personaje_idiomas");
                personaje_historia = request.getParameter("personaje_historia");

                if (personaje_nombre != null && personaje_alineamiento != null && personaje_clase != null && personaje_raza != null
                        && personaje_subraza != null && personaje_trasfondo != null) {
                    try {

                        //////////////////
                        //////NOMBRE//////
                        ////////////////// 
                        if (personaje_nombre.toUpperCase().contains("UPDATE") || personaje_nombre.toUpperCase().contains("CREATE")
                                || personaje_nombre.toUpperCase().contains("DELETE") || personaje_nombre.toUpperCase().contains("SELECT")
                                || personaje_nombre.toUpperCase().contains("DROP")) {
                            throw new Exception("El Nombre no es válido");
                        }

                        queryPersonajes = em.createNamedQuery("Personajes.findByNombre", Personajes.class);
                        queryPersonajes.setParameter("nombre", personaje_nombre);
                        listaPersonajes = queryPersonajes.getResultList();

                        if (!listaPersonajes.isEmpty()) {
                            throw new Exception("El Nombre del personaje debe ser único ");
                        }
                        //////////////////
                        //////CLASE///////
                        //////////////////
                        queryClases = em.createNamedQuery("Clases.findByNombre", Clases.class);
                        queryClases.setParameter("nombre", personaje_clase);
                        clase = queryClases.getSingleResult();
                        //////////////////
                        ///////RAZA///////
                        //////////////////
                        queryRazas = em.createNamedQuery("Razas.findByNombre", Razas.class);
                        queryRazas.setParameter("nombre", personaje_raza);
                        raza = queryRazas.getSingleResult();
                        //////////////////
                        //////SUBRAZA/////
                        //////////////////
                        querySubrazas = em.createNamedQuery("Subrazas.findByNombre", Subrazas.class);
                        querySubrazas.setParameter("nombre", personaje_subraza);
                        subraza = querySubrazas.getSingleResult();
                        //////////////////
                        ////TRASFONDO/////
                        //////////////////
                        queryTrasfondos = em.createNamedQuery("Trasfondos.findByNombre", Trasfondos.class);
                        queryTrasfondos.setParameter("nombre", personaje_trasfondo);
                        transfondo = queryTrasfondos.getSingleResult();

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        //FALTA
                        personaje = new Personajes(personaje_nombre, personaje_alineamiento, BigInteger.valueOf(1), BigInteger.valueOf(clase.getDpg().charAt(1)),
                                BigInteger.valueOf(clase.getDpg().charAt(1)), BigInteger.valueOf(10));
                        personaje.setClase(clase);
                        personaje.setRaza(raza);
                        personaje.setSubraza(subraza);
                        personaje.setTrasfondo(transfondo);

                        if (!personaje_subclase.equalsIgnoreCase("-")) {
                            //////////////////
                            /////SUBCLASE/////
                            //////////////////
                            querySubclases = em.createNamedQuery("Subclases.findByNombre", Subclases.class);
                            querySubclases.setParameter("nombre", personaje_trasfondo);
                            subclase = querySubclases.getSingleResult();
                            personaje.setSubclase(subclase);
                        }

                        if (personaje_edad != null) {
                            personaje.setEdad(BigInteger.valueOf(Integer.valueOf(personaje_edad)));
                        }
                        if (personaje_apariencia != null) {
                            personaje.setApariencia(personaje_apariencia);
                        }
                        if (personaje_rasgos != null) {
                            personaje.setRaspersonalidad(personaje_rasgos);
                        }
                        //FALTAN
                        if (personaje_edad != null) {
                            personaje.setEdad(BigInteger.valueOf(Integer.valueOf(personaje_edad)));
                        }
                        if (personaje_edad != null) {
                            personaje.setEdad(BigInteger.valueOf(Integer.valueOf(personaje_edad)));
                        }
                        if (personaje_edad != null) {
                            personaje.setEdad(BigInteger.valueOf(Integer.valueOf(personaje_edad)));
                        }

                        persist(personaje);
                        System.out.println("Creado el personaje: " + personaje_nombre + " de " + user.getId());
                        conseguido = true;

                    } catch (NumberFormatException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible crear un personaje en este momento");
                        System.out.println("NumberFormatException: " + ex.getMessage());
                    } catch (RuntimeException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible crear un personaje en este momento: ");
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
                razaString = request.getParameter("raza");//como ordenar
                claseString = request.getParameter("clase");//como ordenar
                nivelString = request.getParameter("nivel");//como ordenar

                //Comprobamos los datos
                if (ordenar == null) {

                    ordenar = "ordenar1";
                    razaString = " ";
                    claseString = " ";
                    nivelString = " ";
                    numString = "1";
                    num = 0;

                } else {
                    //Pagina actual
                    num = (Integer.valueOf(numString) - 1) * 6;//offset
                    //Nivel
                    nivel = Integer.parseInt(nivelString);

                    if (razaString.equals("Raza")) {
                        razaString = " ";
                    } else {
                        razaString = "AND p.RAZA = '" + razaString + "' ";
                    }
                    if (claseString.equals("Clase")) {
                        claseString = " ";
                    } else {
                        claseString = "AND p.CLASE = '" + claseString + "' ";
                    }
                    if (nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }
                }

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega raza: " + razaString);
                System.out.println("Llega clase: " + claseString);
                System.out.println("Llega nivel: " + nivelString);

                /////////////////////////////////////
                ////////NUMERO DE PERSONAJES/////////
                /////////////////////////////////////
                sql = "SELECT COUNT(*) FROM PERSONAJES p "
                        + "WHERE p.USUARIO <> '" + id + "' "
                        + nivelString
                        + claseString
                        + razaString;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                numPag = (((Number) result).intValue() / 7) + 1;

                switch (ordenar) {
                    case "ordenar1":
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelString
                                + claseString
                                + razaString
                                + "ORDER BY  p.NOMBRE ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                    case "ordenar2":
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelString
                                + claseString
                                + razaString
                                + "ORDER BY  p.NOMBRE DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Usuarios.class);
                listaPersonajes = queryAUX.getResultList();

                fotosPersonajes = new ArrayList();
                listaUsuarios = new ArrayList();

                for (int i = 0; i < listaPersonajes.size(); i++) {

                    listaUsuarios.add(listaPersonajes.get(i).getUsuario());

                    if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                        fotosPersonajes.add("-");
                    } else {
                        fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                    }
                }

                request.setAttribute("listaPersonajes", listaPersonajes);
                request.setAttribute("listaCreador", listaUsuarios);

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale raza:" + razaString);
                System.out.println("Sale clase:" + claseString);
                System.out.println("Sale nivel:" + nivelString);
                System.out.println("Sale npag:" + numPag);

                queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                request.setAttribute("listaClases", queryClases.getResultList());
                queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                request.setAttribute("listaRazas", queryRazas.getResultList());

                request.setAttribute("orden", ordenar);
                request.setAttribute("filtroRaza", razaString);
                request.setAttribute("filtroClase", claseString);
                request.setAttribute("filtroNivel", nivelString);
                request.setAttribute("urlImagenes", fotosPersonajes);
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
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
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

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
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

                    fotosPersonajes = new ArrayList();
                    listaUsuarios = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        listaUsuarios.add(listaPersonajes.get(i).getUsuario());

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("listaPersonajes", listaPersonajes);
                    request.setAttribute("listaCreador", listaUsuarios);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
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
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || razaString == null || claseString == null || nivelString == null || numString == null) {

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

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
                            + "WHERE p.USUARIO = '" + id + "' "
                            + nivelString
                            + claseString
                            + razaString;

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
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                    queryUsuarios.setParameter("id", id);
                    request.setAttribute("amigo", queryUsuarios.getSingleResult());

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
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
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || razaString == null || claseString == null || nivelString == null || numString == null) {

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

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("Nivel")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
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
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePersonajes(Object object) {
        try {
            utx.begin();
            object = (Personajes) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePersonajes(Object object) {
        try {
            utx.begin();
            em.merge((Personajes) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package controlador;

import entidades.Hilo;
import entidades.Mensajehilo;
import entidades.Seccion;
import entidades.Tema;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Random;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
@WebServlet(name = "ControladorForo", urlPatterns = {"/Foro/*"})
public class ControladorForo extends HttpServlet {

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
        Usuario user;

        Hilo hilo;
        Seccion seccion;
        Tema tema;
        Mensajehilo msjHilo;
        Object result;

        TypedQuery<Hilo> queryHilo;
        TypedQuery<Seccion> querySeccion;
        TypedQuery<Tema> queryTema;
        TypedQuery<Mensajehilo> queryMensajeHilo;

        Query queryAUX;

        List<Hilo> listaHilos;
        List<String> listaFotosHilo;
        List<String> fechasHilo;
        List<Seccion> listaSecciones;
        List<Integer> listaHilosporSeccion;
        List<Tema> listaTemas;
        List<Mensajehilo> listaMensajesHilo;

        String sql;

        String hilo_id;
        String msjHilo_id;

        String tema_nombre;
        String seccion_titulo;
        String seccion_hilos;
        Integer seccion_nHilos;

        String hilo_tema;
        String hilo_seccion;
        String hilo_mio;
        String hilo_comentado;
        String hilo_temaSQL;
        String hilo_seccionSQL;
        String hilo_mioSQL;
        String hilo_comentadoSQL;
        String numString;
        Integer num;
        Integer numPag;

        String hilo_mensaje;
        String hilo_titulo;
        Date hilo_fecha;

        String tema_id;
        String seccion_id;

        String msj;
        String dado;

        switch (accion) {
            case "/inicio":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    vista = "/WEB-INF/jsp/foro/foroInicio.jsp";
                }
                break;
            case "/temas":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";

                } else {
                    queryTema = em.createNamedQuery("Tema.findAll", Tema.class);
                    listaTemas = queryTema.getResultList();

                    request.setAttribute("listaTemas", listaTemas);

                    vista = "/WEB-INF/jsp/foro/temas.jsp";
                }
                break;
            case "/secciones":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    querySeccion = em.createNamedQuery("Seccion.findAll", Seccion.class);
                    listaSecciones = querySeccion.getResultList();

                    listaHilosporSeccion = new ArrayList();

                    //Guardamos de cada seccion los hilos que tiene
                    for (int i = 0; i < listaSecciones.size(); i++) {
                        listaHilosporSeccion.add(listaSecciones.get(i).getHiloList().size());
                    }

                    request.setAttribute("listaSecciones", listaSecciones);
                    request.setAttribute("seccionNumero", listaHilosporSeccion);

                    vista = "/WEB-INF/jsp/foro/secciones.jsp";
                }
                break;
            case "/hilos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    hilo_seccion = request.getParameter("seccion");//si filtramos por seccion
                    hilo_tema = request.getParameter("tema");//si filtramos por tema
                    hilo_mio = request.getParameter("mio");//si filtramos por mio
                    hilo_comentado = request.getParameter("comentado");//si filtramos por comentados

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega tema: " + hilo_tema);
                    System.out.println("Llega seccion: " + hilo_seccion);
                    System.out.println("Llega mio: " + hilo_mio);
                    System.out.println("Llega comentado: " + hilo_comentado);

                    //Comprobamos los datos
                    if (numString == null) {
                        hilo_tema = "Cualquiera";
                        hilo_seccion = "Cualquiera";
                        hilo_comentado = "false";
                        hilo_mio = "false";
                        hilo_seccionSQL = " ";
                        hilo_temaSQL = " ";
                        hilo_mioSQL = " ";
                        hilo_comentadoSQL = " ";
                        numString = "1";
                        num = 0;
                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset

                        if (hilo_seccion.equals("Cualquiera")) {
                            hilo_seccionSQL = " ";
                        } else {
                            hilo_seccionSQL = "AND h.seccion = '" + hilo_seccion + "' ";
                        }
                        if (hilo_tema.equals("Cualquiera")) {
                            hilo_temaSQL = " ";
                        } else {
                            hilo_temaSQL = "AND h.tema = '" + hilo_tema + "' ";
                        }
                        if (hilo_mio.equals("false")) {
                            hilo_mioSQL = " ";
                        } else {
                            hilo_mioSQL = "AND h.creador = '" + user.getId() + "' ";
                        }
                        if (hilo_comentado.equals("false")) {
                            hilo_comentadoSQL = " ";
                        } else {
                            hilo_comentadoSQL = "AND m.escritor = '" + user.getId() + "' ";
                        }
                    }

                    /////////////////////////////////////
                    ///////////NUMERO DE HILOS///////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(DISTINCT h.id) FROM HILO h "
                            + "LEFT JOIN MENSAJEHILO m ON h.id = m.hilo "
                            + "WHERE h.id <> 'null' "
                            + hilo_seccionSQL
                            + hilo_temaSQL
                            + hilo_mioSQL
                            + hilo_comentadoSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 Hilos POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    sql = "SELECT DISTINCT h.* FROM HILO h "
                            + "LEFT JOIN MENSAJEHILO m ON h.id = m.hilo "
                            + "WHERE h.id <> 'null' "
                            + hilo_seccionSQL
                            + hilo_temaSQL
                            + hilo_mioSQL
                            + hilo_comentadoSQL
                            + "ORDER BY h.FECHA ASC "
                            + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";

                    queryAUX = em.createNativeQuery(sql, Hilo.class);
                    listaHilos = queryAUX.getResultList();

                    listaFotosHilo = new ArrayList();
                    fechasHilo = new ArrayList();

                    for (int i = 0; i < listaHilos.size(); i++) {

                        hilo = listaHilos.get(i);

                        fechasHilo.add(hilo.getFecha().getDate() + "-" + (hilo.getFecha().getMonth() + 1) + "-" + (hilo.getFecha().getYear() + 1900));

                        if (hilo.getCreador().getPersonajeactual().getImagenpersonaje() == null) {
                            listaFotosHilo.add("-");
                        } else {
                            listaFotosHilo.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + hilo.getCreador().getPersonajeactual().getId());
                        }
                    }

                    queryTema = em.createNamedQuery("Tema.findAll", Tema.class);
                    request.setAttribute("listaTemas", queryTema.getResultList());
                    querySeccion = em.createNamedQuery("Seccion.findAll", Seccion.class);
                    request.setAttribute("listaSeccion", querySeccion.getResultList());

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale tema:" + hilo_tema);
                    System.out.println("Sale seccion:" + hilo_seccion);
                    System.out.println("Sale mio:" + hilo_mio);
                    System.out.println("Sale comentado:" + hilo_comentado);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("fechasHilos", fechasHilo);
                    request.setAttribute("numHilos", user.getHiloList().size());//Cantidad de hilos
                    request.setAttribute("listaHilos", listaHilos);
                    request.setAttribute("comentado", hilo_comentado);
                    request.setAttribute("tema", hilo_tema);
                    request.setAttribute("seccion", hilo_seccion);
                    request.setAttribute("mio", hilo_mio);
                    request.setAttribute("urlImagenes", listaFotosHilo);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/foro/hilos.jsp";
                }
                break;
            case "/hilo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                hilo_id = request.getParameter("hilo");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    queryHilo = em.createNamedQuery("Hilo.findById", Hilo.class);
                    queryHilo.setParameter("id", hilo_id);
                    try {

                        hilo = queryHilo.getSingleResult();

                        numString = request.getParameter("pag");//numero de pag en la que estoy
                        if (numString == null) {
                            numString = "1";
                            num = 0;
                        } else {
                            num = (Integer.valueOf(numString) - 1) * 4;//offset
                        }

                        ////////////////////////////////////////
                        ///////////NUMERO DE MENSAJES///////////
                        ////////////////////////////////////////
                        sql = "SELECT COUNT(DISTINCT m.id) FROM MENSAJEHILO m "
                                + "WHERE m.hilo = '" + hilo_id + "' ";

                        queryAUX = em.createNativeQuery(sql);
                        result = queryAUX.getSingleResult();

                        //PAGINAS QUE HAY (4 Mensajes POR PAGINA)
                        numPag = (((Number) result).intValue() / 4) + 1;

                        sql = "SELECT m.* FROM MENSAJEHILO m "
                                + "WHERE m.hilo = '" + hilo_id + "' "
                                + "ORDER BY m.FECHA ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 4 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Mensajehilo.class);
                        listaMensajesHilo = queryAUX.getResultList();

                        listaFotosHilo = new ArrayList();
                        fechasHilo = new ArrayList();

                        for (int i = 0; i < listaMensajesHilo.size(); i++) {

                            msjHilo = listaMensajesHilo.get(i);

                            fechasHilo.add(msjHilo.getFecha().getDate() + "-" + (msjHilo.getFecha().getMonth() + 1) + "-" + (msjHilo.getFecha().getYear() + 1900));

                            if (msjHilo.getEscritor().getPersonajeactual().getImagenpersonaje() == null) {
                                listaFotosHilo.add("-");
                            } else {
                                listaFotosHilo.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + msjHilo.getEscritor().getPersonajeactual().getId());
                            }
                        }

                        if (hilo.getCreador().getPersonajeactual() != null) {
                            request.setAttribute("fotoInicial", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + hilo.getCreador().getPersonajeactual().getId());
                        } else {
                            request.setAttribute("fotoInicial", "-");
                        }

                        request.setAttribute("hilo", hilo);
                        request.setAttribute("hiloID", hilo.getId());
                        request.setAttribute("fechaInicial", hilo.getFecha().getDate() + "-" + (hilo.getFecha().getMonth() + 1) + "-" + (hilo.getFecha().getYear() + 1900));
                        request.setAttribute("fechasHilo", fechasHilo);
                        request.setAttribute("listaMensajes", listaMensajesHilo);
                        request.setAttribute("urlImagenes", listaFotosHilo);
                        request.setAttribute("pag", numString);//numero de la pag
                        request.setAttribute("numPag", numPag);//numero total de pag

                        vista = "/WEB-INF/jsp/foro/hilo.jsp";

                    } catch (Exception ex) {
                        System.out.println("No exite el hilo de id: " + hilo_id);
                        vista = "/Foro/hilos";
                    }
                }
                break;
            case "/crearHilo":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    if (user.getHiloList().size() < 3) {
                        hilo_titulo = request.getParameter("titulo");
                        hilo_seccion = request.getParameter("seccion");
                        hilo_tema = request.getParameter("tema");
                        hilo_mensaje = request.getParameter("mensaje");

                        if (hilo_titulo != null && hilo_seccion != null && hilo_tema != null && hilo_mensaje != null) {
                            try {

                                /////////////////////
                                /////HILO TITULO/////
                                /////////////////////
                                comprobarCadena(hilo_titulo, "El título no es válido");

                                /////////////////////
                                /////HILO MENSAJE////
                                /////////////////////
                                comprobarCadena(hilo_mensaje, "El mensaje inicial no es válido");

                                try {
                                    queryHilo = em.createNamedQuery("Hilo.findByTitulo", Hilo.class);
                                    queryHilo.setParameter("titulo", hilo_titulo);
                                    queryHilo.getSingleResult();//Si salta exception es que no existe

                                    throw new Exception("El título está repetido");
                                } catch (NoResultException ex) {

                                    queryTema = em.createNamedQuery("Tema.findById", Tema.class);
                                    queryTema.setParameter("id", hilo_tema);
                                    tema = queryTema.getSingleResult();

                                    querySeccion = em.createNamedQuery("Seccion.findById", Seccion.class);
                                    querySeccion.setParameter("id", hilo_seccion);
                                    seccion = querySeccion.getSingleResult();

                                    hilo_fecha = new Date();

                                    hilo = new Hilo(hilo_titulo, hilo_mensaje, hilo_fecha, user, tema, seccion);

                                    persist(hilo);

                                    vista = "/Foro/hilos";
                                }
                            } catch (Exception ex) {
                                msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + " </p>";
                                System.out.println("Error: Imposible registrar en este momento: " + hilo_titulo);

                                request.setAttribute("msj", msj);

                                vista = "/Formularios/crearHilo";
                            }
                        } else {
                            msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                            System.out.println("Error: Introduzca los campos de forma correcta ");

                            request.setAttribute("msj", msj);

                            vista = "/Formularios/crearHilo";
                        }
                    } else {
                        msj = "<p style=\"margin-left: 10px\"> Error: Borre un hilo para crear otro </p>";
                        System.out.println("Error: tienes el maximo de hilos ");

                        request.setAttribute("msj", msj);

                        vista = "/Formularios/crearHilo";
                    }

                }
                break;
            case "/borrarHilo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                hilo_id = request.getParameter("hilo");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    queryHilo = em.createNamedQuery("Hilo.findById", Hilo.class);
                    queryHilo.setParameter("id", hilo_id);
                    try {
                        hilo = queryHilo.getSingleResult();

                        if (hilo.getCreador().getId().equals(user.getId()) || user.getAdmin() == 1) {

                            delete(hilo);

                            vista = "/Foro/hilos";
                        } else {
                            vista = "/Foro/hilos";
                        }

                    } catch (Exception ex) {
                        System.out.println("Mensaje Hilo no encontrado para borrar");
                        vista = "/Foro/hilo";
                    }
                }
                break;
            case "/añadirMensajeHilo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    hilo_id = request.getParameter("hilo");
                    hilo_mensaje = request.getParameter("mensaje");

                    try {

                        comprobarCadena(hilo_mensaje, "");

                        request.setAttribute("hilo", hilo_id);

                        queryHilo = em.createNamedQuery("Hilo.findById", Hilo.class);
                        queryHilo.setParameter("id", hilo_id);

                        try {
                            hilo = queryHilo.getSingleResult();

                            hilo_fecha = new Date();

                            msjHilo = new Mensajehilo(hilo_mensaje, hilo_fecha, user, hilo);

                            persist(msjHilo);

                            request.setAttribute("hilo", hilo_id);
                            vista = "/Foro/hilo";

                        } catch (Exception ex) {

                            System.out.println("Hilo no encontrado para escribir un mensaje");
                            vista = "/Foro/hilos";
                        }
                    } catch (Exception ex) {
                        System.out.println("El mensaje era perjudicial");
                        vista = "/Foro/hilos";
                    }
                }
                break;
            case "/añadirTiradaHilo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    hilo_id = request.getParameter("hilo");
                    dado = request.getParameter("dado");

                    queryHilo = em.createNamedQuery("Hilo.findById", Hilo.class);
                    queryHilo.setParameter("id", hilo_id);

                    try {
                        hilo = queryHilo.getSingleResult();

                        long tiempoActual = System.currentTimeMillis();
                        Random random = new Random(tiempoActual);
                        msj = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                        hilo_fecha = new Date();

                        msjHilo = new Mensajehilo(msj, hilo_fecha, user, hilo);

                        persist(msjHilo);

                        request.setAttribute("hilo", hilo_id);
                        vista = "/Foro/hilo";

                    } catch (Exception ex) {
                        System.out.println("Hilo no encontrado para escribir un mensaje");
                        vista = "/Foro/hilos";
                    }
                }
                break;
            case "/eliminarMensajeHilo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Foro/hilo";
                } else {
                    msjHilo_id = request.getParameter("mensaje");
                    hilo_id = request.getParameter("hilo");

                    queryMensajeHilo = em.createNamedQuery("Mensajehilo.findById", Mensajehilo.class);
                    queryMensajeHilo.setParameter("id", msjHilo_id);
                    try {
                        msjHilo = queryMensajeHilo.getSingleResult();

                        request.setAttribute("hilo", hilo_id);

                        if (msjHilo.getEscritor().getId().equals(user.getId()) || user.getAdmin() == 1) {

                            delete(msjHilo);

                            vista = "/Foro/hilo";
                        } else {
                            vista = "/Foro/hilo";
                        }

                    } catch (Exception ex) {
                        System.out.println("Mensaje Hilo no encontrado para borrar");
                        vista = "/Foro/hilos";
                    }
                }
                break;
            //////////////////////////////////////////////////////////////////
            /////////////////////////ADMINISTRACIÓN///////////////////////////
            //////////////////////////////////////////////////////////////////
            case "/añadirTema":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    if (user.getAdmin() == 0) {

                        tema_nombre = request.getParameter("nombre");

                        try {
                            comprobarCadena(tema_nombre, "");

                            queryTema = em.createNamedQuery("Tema.findByNombre", Tema.class);
                            queryTema.setParameter("nombre", tema_nombre);
                            try {
                                queryTema.getSingleResult();
                            } catch (Exception ex) {
                                tema = new Tema(tema_nombre);
                                persist(tema);
                            }
                        } catch (Exception ex) {

                        }

                        vista = "/Foro/temas";
                    } else {

                        vista = "/Foro/temas";
                    }
                }
                break;
            case "/eliminarTema":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {//ESTA MAL
                    vista = "/Principal/inicio";
                } else {
                    if (user.getAdmin() == 0) {

                        tema_id = request.getParameter("tema");

                        queryTema = em.createNamedQuery("Tema.findById", Tema.class);
                        queryTema.setParameter("id", tema_id);
                        try {
                            tema = queryTema.getSingleResult();
                            delete(tema);
                        } catch (Exception ex) {
                            System.out.println("Error en la creacion de tema");
                        }
                        vista = "/Foro/temas";
                    } else {
                        vista = "/Foro/temas";
                    }
                }
                break;
            case "/añadirSeccion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    if (user.getAdmin() == 0) {

                        seccion_titulo = request.getParameter("titulo");
                        seccion_hilos = request.getParameter("hilos");
                        seccion_nHilos = Integer.valueOf(seccion_hilos);
                        try {
                            comprobarCadena(seccion_titulo, "");

                            querySeccion = em.createNamedQuery("Seccion.findByTitulo", Seccion.class);
                            querySeccion.setParameter("titulo", seccion_titulo);
                            try {
                                querySeccion.getSingleResult();
                            } catch (Exception ex) {
                                seccion = new Seccion(seccion_titulo, seccion_nHilos);
                                persist(seccion);
                            }
                        } catch (Exception ex) {
                            System.out.println("Error en la creacion de seccion");
                        }
                        vista = "/Foro/secciones";
                    } else {

                        vista = "/Foro/secciones";
                    }
                }
                break;
            case "/modificarSeccion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    if (user.getAdmin() == 0) {

                        seccion_id = request.getParameter("seccion");
                        seccion_hilos = request.getParameter("hilos");
                        seccion_nHilos = Integer.valueOf(seccion_hilos);

                        querySeccion = em.createNamedQuery("Seccion.findById", Seccion.class);
                        querySeccion.setParameter("id", seccion_id);
                        try {
                            seccion = querySeccion.getSingleResult();
                            seccion.setNumerohilosmax(seccion_nHilos);
                            updateSeccion(seccion);
                        } catch (Exception ex) {
                        }
                        vista = "/Foro/secciones";
                    } else {
                        vista = "/Foro/secciones";
                    }
                }
                break;
            case "/eliminarSeccion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    if (user.getAdmin() == 0) {
                        seccion_id = request.getParameter("seccion");

                        querySeccion = em.createNamedQuery("Seccion.findById", Seccion.class);
                        querySeccion.setParameter("id", seccion_id);
                        try {
                            seccion = querySeccion.getSingleResult();
                            delete(seccion);
                        } catch (Exception ex) {
                        }
                        vista = "/Foro/secciones";
                    } else {

                        vista = "/Foro/secciones";
                    }
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

    private void updateSeccion(Object object) {
        try {
            utx.begin();
            em.merge((Seccion) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

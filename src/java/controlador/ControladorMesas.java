package controlador;

import entidades.Mesa;
import entidades.Musica;
import entidades.Musicamesa;
import entidades.Personaje;
import entidades.Pertenecemesa;
import entidades.Usuario;
import java.io.IOException;
import java.text.ParseException;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ControladorMesas", urlPatterns = {"/Mesas/*"})
public class ControladorMesas extends HttpServlet {

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
        Mesa mesa;
        Pertenecemesa pmesa;
        Pertenecemesa pmesaaux;
        Personaje personaje;


        TypedQuery<Mesa> queryMesas;
        TypedQuery<Pertenecemesa> queryPertenecemesas;
        TypedQuery<Usuario> queryUsuarios;
        TypedQuery<Personaje> queryPersonajes;
        TypedQuery<Musica> queryMusica;

        Query queryAUX;

        List<String> fotosMesas;
        List<Integer> listaCantidad;
        List<Usuario> listaUsuarios;
        List<Pertenecemesa> listaPerteneceMesa;
        List<Mesa> listaMesas;
        List<Personaje> listaPersonajes;
        List<Musica> listaMusica;

        String personaje_id;

        String id;
        String apodo;
        String creador;
        String comunidad;
        String tamanoString;
        String contrasena;
        String contrasenahash;
        Integer tamano;
        String titulo;
        String descripcion;
        String urlImagen;

        String ordenar;
        String lleno;
        String numString;
        int num;
        int numPag;
        
        int cantidad;

        String sql;

        switch (accion) {
            case "/crearMesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    conseguido = false;
                    mesa = null;
                    msj = "";

                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    creador = user.getApodo();
                    comunidad = request.getParameter("comunidad");
                    titulo = request.getParameter("mesa_titulo");
                    descripcion = request.getParameter("mesa_descripcion");
                    tamanoString = request.getParameter("mesa_jugadores");
                    contrasena = request.getParameter("mesa_contrasena");

                    if (creador != null && comunidad != null && titulo != null && tamanoString != null) {

                        try {
                            //////////////////
                            //////TAMANO//////
                            //////////////////
                            tamano = Integer.parseInt(tamanoString);
                            if (tamano < 2 || tamano > 5) {
                                throw new Exception("El Tamaño debe estar entre 2 y 5 ");
                            }

                            //////////////////////////
                            //////////TITULO//////////
                            //////////////////////////
                            if (titulo.toUpperCase().contains("UPDATE") || titulo.toUpperCase().contains("CREATE")
                                    || titulo.toUpperCase().contains("DELETE") || titulo.toUpperCase().contains("SELECT")
                                    || titulo.toUpperCase().contains("DROP")) {
                                throw new Exception("El Titulo no es válido");
                            }

                            queryMesas = em.createNamedQuery("Mesa.findByTitulo", Mesa.class);
                            queryMesas.setParameter("titulo", titulo);
                            listaMesas = queryMesas.getResultList();

                            if (!listaMesas.isEmpty()) {
                                throw new Exception("El Titulo debe ser único ");
                            }

                            ////////////////////////////
                            //////////NUM MESAS/////////
                            ////////////////////////////
                            queryMesas = em.createNamedQuery("Mesa.CountByCreador", Mesa.class);
                            queryMesas.setParameter("creador", creador);
                            listaMesas = queryMesas.getResultList();

                            if (listaMesas.size() == 5) {
                                throw new Exception("Ya tienes el limite de mesas");
                            }
                            //////////////////////////////
                            //////////CONTRASEÑA//////////
                            //////////////////////////////
                            if (contrasena != null) {
                                if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                        || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                        || descripcion.toUpperCase().contains("DROP")) {
                                    throw new Exception("La contrasena no es válida");
                                }
                                if (!contrasena.equals("")) {
                                    //////////////////////
                                    /////////HASH/////////
                                    //////////////////////
                                    contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                                } else {
                                    contrasenahash = null;
                                }
                            } else {
                                contrasenahash = null;
                            }
                            ///////////////////////////////
                            //////////DESCRIPCION//////////
                            ///////////////////////////////
                            if (descripcion != null) {
                                if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                        || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                        || descripcion.toUpperCase().contains("DROP")) {
                                    throw new Exception("El Titulo no es válido");
                                }
                            } else if (descripcion.equals("")) {
                                descripcion = null;
                            }

                            //////////////////////////
                            //////////CREAMOS/////////
                            //////////////////////////
                            mesa = new Mesa(creador, comunidad, tamano, titulo, descripcion, contrasenahash);

                            mesa.setMusicamesa(new Musicamesa(mesa.getId(),new Musica("561F81109A494CECB61DC8FDB9ECAF02", "Ninguna")));

                            persist(mesa);
                            System.out.println("Registrada la mesa: " + titulo);
                            conseguido = true;

                        } catch (NumberFormatException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible registrar en este momento: " + titulo);
                            System.out.println("NumberFormatException: " + ex.getMessage());
                        } catch (ParseException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible registrar en este momento: " + titulo);
                            System.out.println("ParseException: " + ex.getMessage());
                        } catch (RuntimeException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible registrar en este momento: " + titulo);
                            System.out.println("ParseException: " + ex.getMessage());
                            conseguido = false;
                        } catch (Exception ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                            System.out.println("Exception: " + ex.getMessage());
                        }
                    } else {
                        msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                        System.out.println("Error: Introduzca los campos de forma correcta ");
                    }
                    if (conseguido == true) {
                        pmesa = new Pertenecemesa(user.getId(), mesa.getId(), "Dungeon Master");
                        persist(pmesa);

                        vista = "/Mesas/mostrarMesasUsuario";
                    } else {
                        request.setAttribute("msj", msj);
                        vista = "/Formularios/crearmesa";
                    }
                }
                break;
            case "/eliminarMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    id = request.getParameter("id");

                    queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                    queryMesas.setParameter("id", id);
                    mesa = queryMesas.getSingleResult();

                    deleteMesas(mesa);
                    vista = "/Mesas/mostrarMesasUsuario";
                }
                break;
            case "/modificarMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                queryMesas.setParameter("id", id);
                mesa = queryMesas.getSingleResult();

                if (user == null || !mesa.getCreador().equals(user.getApodo())) {
                    vista = "/Principal/inicio";
                } else {
                    conseguido = false;
                    msj = "";

                    creador = user.getApodo();
                    comunidad = request.getParameter("comunidad");
                    titulo = request.getParameter("mesa_titulo");
                    descripcion = request.getParameter("mesa_descripcion");
                    tamanoString = request.getParameter("mesa_jugadores");
                    contrasena = request.getParameter("mesa_contrasena");

                    if (creador != null && comunidad != null && titulo != null && tamanoString != null) {

                        try {
                            //////////////////
                            //////TAMANO//////
                            //////////////////
                            tamano = Integer.parseInt(tamanoString);
                            if (tamano < 2 || tamano > 5) {
                                throw new Exception("El Tamaño debe estar entre 2 y 5 ");
                            }

                            //////////////////////////
                            //////////TITULO//////////
                            //////////////////////////
                            if (titulo.toUpperCase().contains("UPDATE") || titulo.toUpperCase().contains("CREATE")
                                    || titulo.toUpperCase().contains("DELETE") || titulo.toUpperCase().contains("SELECT")
                                    || titulo.toUpperCase().contains("DROP")) {
                                throw new Exception("El Titulo no es válido");
                            }

                            queryMesas = em.createNamedQuery("Mesa.findByTitulo", Mesa.class);
                            queryMesas.setParameter("titulo", titulo);
                            listaMesas = queryMesas.getResultList();

                            if (!listaMesas.isEmpty() && !listaMesas.get(0).getId().equals(id)) {
                                throw new Exception("El Titulo debe ser único ");
                            }

                            //////////////////////////////
                            //////////CONTRASEÑA//////////
                            //////////////////////////////
                            if (contrasena != null) {
                                if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                        || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                        || descripcion.toUpperCase().contains("DROP")) {
                                    throw new Exception("La contrasena no es válida");
                                }
                                if (!contrasena.equals("")) {
                                    //////////////////////
                                    /////////HASH/////////
                                    //////////////////////
                                    contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                                } else {
                                    contrasenahash = null;
                                }
                            } else {
                                contrasenahash = null;
                            }

                            ///////////////////////////////
                            //////////DESCRIPCION//////////
                            ///////////////////////////////
                            if (descripcion != null) {
                                if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                        || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                        || descripcion.toUpperCase().contains("DROP")) {
                                    throw new Exception("El Titulo no es válido");
                                }
                            } else {
                                descripcion = null;
                            }

                            //////////////////////////////
                            //////////MODIFICAMOS/////////
                            //////////////////////////////
                            mesa = new Mesa(creador, comunidad, tamano, titulo, descripcion, contrasenahash);
                            mesa.setId(id);

                            updateMesas(mesa);
                            System.out.println("Modificada la mesa: " + titulo);
                            conseguido = true;

                        } catch (NumberFormatException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible registrar en este momento: " + titulo);
                            System.out.println("NumberFormatException: " + ex.getMessage());
                            conseguido = false;
                        } catch (ParseException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible registrar en este momento: " + titulo);
                            System.out.println("ParseException: " + ex.getMessage());
                            conseguido = false;
                        } catch (RuntimeException ex) {
                            msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                            System.out.println("Error: Imposible modificar en este momento: " + titulo);
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
                        vista = "/Mesas/mostrarMesa?titulo=" + titulo;
                    } else {
                        request.setAttribute("msj", msj);
                        vista = "/Formularios/modificarmesa";
                    }
                }
                break;
            case "/mostrarMesas":

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
                    lleno = request.getParameter("lleno");//si filtramos por lleno o no

                    if (ordenar == null || lleno == null || numString == null) {

                        ordenar = "ordenar1";
                        lleno = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    if (lleno.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) "
                                + "FROM MESA M "
                                + "WHERE NOT EXISTS ( "
                                + "    SELECT * "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.ID "
                                + "      AND P.USUARIO = '" + user.getId() + "' "
                                + ") "
                                + "AND M.TAMANO > ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.ID "
                                + ") ";
                    } else {
                        sql = "SELECT COUNT(*) "
                                + "FROM MESA M "
                                + "WHERE NOT EXISTS ( "
                                + "    SELECT * "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.ID "
                                + "      AND P.USUARIO = '" + user.getId() + "' "
                                + ") "
                                + "AND M.TAMANO = ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.ID "
                                + ") ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 MESAS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega lleno: " + lleno);

                    sql = "";

                    switch (ordenar) {
                        case "ordenar1":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESA M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO > ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "ORDER BY M.TITULO ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            } else {
                                sql = "SELECT M.* "
                                        + "FROM MESA M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO = ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "ORDER BY M.TITULO ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESA M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO > ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "ORDER BY M.TITULO DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            } else {
                                sql = "SELECT M.* "
                                        + "FROM MESA M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO = ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "ORDER BY M.TITULO DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesa.class);
                    listaMesas = queryAUX.getResultList();

                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    request.setAttribute("urlImagenes", fotosMesas);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listacantidad", listaCantidad);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("lleno", lleno);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/mesas/mesas.jsp";
                }
                break;
            case "/mostrarMesasUsuario":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    sql = "SELECT COUNT(*) "
                            + "FROM MESA M "
                            + "WHERE EXISTS ( "
                            + "    SELECT * "
                            + "    FROM PERTENECEMESA P "
                            + "    WHERE P.MESA = M.ID "
                            + "      AND P.USUARIO = '" + user.getId() + "' "
                            + ") "
                            + "AND M.TAMANO >= ( "
                            + "    SELECT COUNT(*) "
                            + "    FROM PERTENECEMESA P "
                            + "    WHERE P.MESA = M.ID "
                            + ") ";
                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (7 MESAS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);

                    sql = "";

                    if (ordenar == null || numString == null) {

                        ordenar = "ordenar1";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TITULO ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TITULO DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TAMANO DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TAMANO ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesa.class);
                    listaMesas = queryAUX.getResultList();

                    //System.out.println("Esto: " + listaMesas.size());
                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    request.setAttribute("urlImagenes", fotosMesas);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listacantidad", listaCantidad);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/mesas/mesasPerfil.jsp";
                }
                break;
            case "/mostrarMesasAmigo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    request.setAttribute("amigo", id);

                    queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                    queryUsuarios.setParameter("id", id);
                    useraux = queryUsuarios.getSingleResult();

                    request.setAttribute("usuario", useraux);

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    sql = "SELECT COUNT(*) "
                            + "FROM MESA M "
                            + "WHERE EXISTS ( "
                            + "    SELECT * "
                            + "    FROM PERTENECEMESA P "
                            + "    WHERE P.MESA = M.ID "
                            + "      AND P.USUARIO = '" + id + "' "
                            + ") "
                            + "AND M.TAMANO >= ( "
                            + "    SELECT COUNT(*) "
                            + "    FROM PERTENECEMESA P "
                            + "    WHERE P.MESA = M.ID "
                            + ") ";
                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (7 MESAS POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);

                    sql = "";

                    if (ordenar == null || numString == null) {

                        ordenar = "ordenar1";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 6;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TITULO ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";

                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TITULO DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TAMANO DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESA M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "ORDER BY M.TAMANO ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesa.class);
                    listaMesas = queryAUX.getResultList();

                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    request.setAttribute("urlImagenes", fotosMesas);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listacantidad", listaCantidad);
                    request.setAttribute("orden", ordenar);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/mesas/mesasPerfilAmigo.jsp";
                }
                break;
            case "/anadiraMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");
                    contrasena = request.getParameter("contrasena_anadirmesa");

                    queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                    queryMesas.setParameter("id", id);
                    mesa = queryMesas.getSingleResult();

                    if (!contrasena.equals("")) {
                        if (!BCrypt.checkpw(contrasena, mesa.getContrasena())) {
                            vista = "/Mesas/mostrarMesas";
                        } else {
                            pmesa = new Pertenecemesa(user.getId(), id, "Jugador");
                            persist(pmesa);
                            vista = "/Mesas/mostrarMesa";
                        }
                    } else {
                        pmesa = new Pertenecemesa(user.getId(), id, "Jugador");
                        persist(pmesa);
                        vista = "/Mesas/mostrarMesa";
                    }
                }
                break;
            case "/salirdeMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");

                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", user.getId());
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();

                    if (pmesa.getRol().equals("Dungeon Master")) {

                        queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                        queryUsuarios.setParameter("apodo", id);
                        useraux = queryUsuarios.getSingleResult();

                        //Modificamos nuevo DM si es necesario (Se sale el que era)
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("usuario", useraux.getId());
                        queryPertenecemesas.setParameter("mesa", id);
                        pmesaaux = queryPertenecemesas.getSingleResult();
                        pmesaaux.setRol("Dungeon Master");
                        updatePMesas(pmesaaux);
                    }
                    deletePMesas(pmesa);

                    vista = "/Mesas/mostrarMesasUsuario";
                }
                break;
            case "/eliminardeMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                queryMesas.setParameter("id", id);
                mesa = queryMesas.getSingleResult();

                if (user == null || !mesa.getCreador().equals(user.getApodo())) {
                    vista = "/Principal/inicio";
                } else {
                    apodo = request.getParameter("usuario");
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", apodo);
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();

                    if (pmesa.getRol().equals("Dungeon Master")) {
                        //Modificamos nuevo DM si es necesario (Se sale el que era)
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("usuario", user.getId());
                        queryPertenecemesas.setParameter("mesa", id);
                        pmesaaux = queryPertenecemesas.getSingleResult();
                        pmesaaux.setRol("Dungeon Master");
                        updatePMesas(pmesaaux);
                    }

                    deletePMesas(pmesa);

                    request.setAttribute("id", id);
                    vista = "/Mesas/mostrarMesa";
                }
                break;
            case "/cambiarlider":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                queryMesas.setParameter("id", id);
                mesa = queryMesas.getSingleResult();

                if (user == null || !mesa.getCreador().equals(user.getApodo())) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");
                    apodo = request.getParameter("usuario");

                    //AntiguoLider
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("rol", "Dungeon Master");
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();
                    creador = pmesa.getPertenecemesaPK().getUsuario();

                    //Modificamos nuevo DM
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", apodo);
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();
                    pmesa.setRol("Dungeon Master");
                    updatePMesas(pmesa);

                    //Modificamos antiguo DM
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", creador);
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();
                    pmesa.setRol("Jugador");
                    updatePMesas(pmesa);

                    vista = "/Mesas/mostrarMesa";
                }
                break;
            case "/mostrarMesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ///////////////////////////////
                    //////PERTENECES A LA MESA/////
                    ///////////////////////////////
                    id = request.getParameter("id");

                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", user.getId());
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();

                    if (pmesa == null) {
                        vista = "/Principal/inicio";
                    } else {

                        id = request.getParameter("id");

                        /////////////////////
                        /////////MESA////////
                        /////////////////////
                        queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        //////////////////////////
                        ////////////DM////////////
                        //////////////////////////
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("rol", "Dungeon Master");
                        queryPertenecemesas.setParameter("mesa", id);
                        pmesa = queryPertenecemesas.getSingleResult();

                        queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                        queryUsuarios.setParameter("id", pmesa.getPertenecemesaPK().getUsuario());
                        useraux = queryUsuarios.getSingleResult();

                        request.setAttribute("dm", useraux.getApodo());

                        /////////////////////
                        //////////ROL////////
                        /////////////////////
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("usuario", user.getId());
                        queryPertenecemesas.setParameter("mesa", id);
                        pmesa = queryPertenecemesas.getSingleResult();

                        //////////////////////////
                        //////////USUARIOS////////
                        //////////////////////////
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("mesa", id);
                        listaPerteneceMesa = queryPertenecemesas.getResultList();

                        listaUsuarios = new ArrayList();

                        for (int i = 0; i < listaPerteneceMesa.size(); i++) {
                            queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                            queryUsuarios.setParameter("id", listaPerteneceMesa.get(i).getPertenecemesaPK().getUsuario());
                            useraux = queryUsuarios.getSingleResult();
                            listaUsuarios.add(useraux);
                        }

                        //Generar la URL de la imagen para mostrarla
                        urlImagen = "/TFG/Imagenes/mostrarImagenMesa?id=" + mesa.getId();

                        request.setAttribute("urlImagen", urlImagen);
                        request.setAttribute("mesa", mesa);
                        request.setAttribute("rol", pmesa.getRol());
                        request.setAttribute("personajeActual", pmesa.getPersonajemesa());
                        request.setAttribute("listaRoles", listaPerteneceMesa);
                        request.setAttribute("listaUsuarios", listaUsuarios);

                        vista = "/WEB-INF/jsp/mesas/mesa.jsp";
                    }
                }
                break;
            ///////////////////////////////////////////////////////////////////////////
            //////////////////////////CHAT Y SUS CAMBIOS///////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            case "/mostrarMesaChat":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ///////////////////////////////
                    //////PERTENECES A LA MESA/////
                    ///////////////////////////////
                    id = request.getParameter("id");

                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", user.getId());
                    queryPertenecemesas.setParameter("mesa", id);

                    try {
                        pmesa = queryPertenecemesas.getSingleResult();

                        //////////////////////////
                        //////////USUARIOS////////
                        //////////////////////////
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("mesa", id);
                        listaPerteneceMesa = queryPertenecemesas.getResultList();

                        listaUsuarios = new ArrayList();
                        listaPersonajes = new ArrayList();

                        for (int i = 0; i < listaPerteneceMesa.size(); i++) {
                            listaUsuarios.add(listaPerteneceMesa.get(i).getUsuarios());
                            listaPersonajes.add(listaPerteneceMesa.get(i).getPersonajemesa());
                        }

                        queryMusica = em.createNamedQuery("Musica.findAll", Musica.class);
                        listaMusica = queryMusica.getResultList();

                        request.setAttribute("descriptor", pmesa.getMesas().getDescriptormesa());
                        request.setAttribute("musica", pmesa.getMesas().getMusicamesa().getMusica());
                        request.setAttribute("listaMusica", listaMusica);
                        request.setAttribute("listaUsuariosRol", listaPerteneceMesa);
                        request.setAttribute("listaPersonajes", listaPersonajes);
                        request.setAttribute("listaUsuarios", listaUsuarios);
                        request.setAttribute("mesa", pmesa.getMesas());
                        request.setAttribute("rol", pmesa.getRol());
                        vista = "/WEB-INF/jsp/mesas/mesaChat.jsp";

                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
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
                personaje_id = request.getParameter("personaje");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Comprobamos que sea suyo
                    queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    queryPersonajes.setParameter("creador", user);

                    personaje = queryPersonajes.getSingleResult();

                    ///////////////////////////////
                    //////PERTENECES A LA MESA/////
                    ///////////////////////////////
                    id = request.getParameter("id");

                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", user.getId());
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();
                    try {
                        pmesa.setPersonajemesa(personaje);
                        updatePMesas(pmesa);

                        vista = "/Mesas/mostrarMesa?id=" + id;

                    } catch (Exception ex) {
                        vista = "/Mesas/mostrarMesa?id=" + id;
                    }
                }
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

    private void deleteMesas(Object object) {
        try {
            utx.begin();
            object = (Mesa) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateMesas(Object object) {
        try {
            utx.begin();
            em.merge((Mesa) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePMesas(Object object) {
        try {
            utx.begin();
            object = (Pertenecemesa) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePMesas(Object object) {
        try {
            utx.begin();
            em.merge((Pertenecemesa) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

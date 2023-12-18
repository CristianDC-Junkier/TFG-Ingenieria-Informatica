package controlador;

import entidades.Mesas;
import entidades.Pertenecemesa;
import entidades.Usuarios;
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

        Usuarios user = null;
        Usuarios useraux = null;
        Mesas mesa = null;
        Pertenecemesa pmesa = null;
        Pertenecemesa pmesaaux = null;
        int cantidad;

        TypedQuery<Mesas> queryMesas;
        TypedQuery<Pertenecemesa> queryPertenecemesas;
        TypedQuery<Usuarios> queryUsuarios;

        Query queryAUX;

        ArrayList<String> listaLideres;
        ArrayList<Integer> listaCantidad;
        ArrayList<Usuarios> listaUsuarios;
        List<Pertenecemesa> listaPerteneceMesa;
        List<Mesas> listaMesas;

        String id;
        String apodo;
        String creador;
        String comunidad;
        String tamanoString;
        String contrasena;
        String contrasenahash;
        short tamano;
        String titulo;
        String descripcion;

        String ordenar;
        String lleno;
        String numString;
        int num;
        int numPag;

        String sql;

        switch (accion) {
            case "/crearMesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    conseguido = false;
                    msj = "";

                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

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
                            tamano = Short.parseShort(tamanoString);
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

                            queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                            queryMesas.setParameter("titulo", titulo);
                            listaMesas = queryMesas.getResultList();

                            if (!listaMesas.isEmpty()) {
                                throw new Exception("El Titulo debe ser único ");
                            }

                            ////////////////////////////
                            //////////NUM MESAS/////////
                            ////////////////////////////
                            queryMesas = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
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
                            mesa = new Mesas(creador, comunidad, tamano, titulo, descripcion, contrasenahash);
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
                            System.out.println("Error: Imposible modificar en este momento: " + titulo);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    id = request.getParameter("id");

                    queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
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
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
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
                            tamano = Short.parseShort(tamanoString);
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

                            queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
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
                            mesa = new Mesas(creador, comunidad, tamano, titulo, descripcion, contrasenahash);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    queryAUX = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
                    queryAUX.setParameter("creador", user.getApodo());
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    lleno = request.getParameter("lleno");//si filtramos por lleno o no

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega lleno: " + lleno);

                    sql = "";

                    if (ordenar == null || lleno == null || numString == null) {

                        ordenar = "ordenar1";
                        lleno = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }
                    switch (ordenar) {
                        case "ordenar1":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
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
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
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
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
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
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
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
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    listaLideres = new ArrayList();
                    listaCantidad = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("rol", "Dungeon Master");
                        queryPertenecemesas.setParameter("mesa", listaMesas.get(i).getId());
                        pmesa = queryPertenecemesas.getSingleResult();
                        queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                        queryUsuarios.setParameter("id", pmesa.getPertenecemesaPK().getUsuario());
                        useraux = queryUsuarios.getSingleResult();
                        listaLideres.add(useraux.getApodo());

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listalideres", listaLideres);
                    request.setAttribute("listacantidad", listaCantidad);
                    request.setAttribute("orden", ordenar);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    queryAUX = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
                    queryAUX.setParameter("creador", user.getApodo());
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 MESAS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

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

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";

                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "ORDER BY ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "ORDER BY ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    System.out.println("Esto: " + listaMesas.size());

                    listaLideres = new ArrayList();
                    listaCantidad = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("rol", "Dungeon Master");
                        queryPertenecemesas.setParameter("mesa", listaMesas.get(i).getId());
                        pmesa = queryPertenecemesas.getSingleResult();

                        queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                        queryUsuarios.setParameter("id", pmesa.getPertenecemesaPK().getUsuario());
                        useraux = queryUsuarios.getSingleResult();
                        listaLideres.add(useraux.getApodo());

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);

                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listalideres", listaLideres);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                    queryUsuarios.setParameter("id", id);
                    useraux = queryUsuarios.getSingleResult();

                    ////////////////////////////
                    //////////NUM MESAS/////////
                    ////////////////////////////
                    queryMesas = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
                    queryMesas.setParameter("creador", useraux.getApodo());
                    result = queryMesas.getSingleResult();

                    //PAGINAS QUE HAY (10 MESAS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

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

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";

                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "ORDER BY ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
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
                                    + "ORDER BY ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    listaLideres = new ArrayList();
                    listaCantidad = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("rol", "Dungeon Master");
                        queryPertenecemesas.setParameter("mesa", listaMesas.get(i).getId());
                        pmesa = queryPertenecemesas.getSingleResult();

                        queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                        queryUsuarios.setParameter("id", pmesa.getPertenecemesaPK().getUsuario());
                        useraux = queryUsuarios.getSingleResult();
                        listaLideres.add(useraux.getApodo());

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);
                    }

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale npag:" + numPag);
                    
                    request.setAttribute("usuario", useraux);
                    request.setAttribute("listaMesas", listaMesas);
                    request.setAttribute("listalideres", listaLideres);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");
                    contrasena = request.getParameter("contrasena_anadirmesa");

                    queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
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
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");

                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("usuario", user.getId());
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();

                    if (pmesa.getRol().equals("Dungeon Master")) {

                        queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
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
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
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
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");
                queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
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
                user = (Usuarios) session.getAttribute("user");

                ///////////////////////////////
                //////PERTENECES A LA MESA/////
                ///////////////////////////////
                id = request.getParameter("id");

                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getId());
                queryPertenecemesas.setParameter("mesa", id);
                pmesa = queryPertenecemesas.getSingleResult();

                if (user == null || pmesa == null) {
                    vista = "/Principal/inicio";
                } else {

                    id = request.getParameter("id");

                    /////////////////////
                    /////////MESA////////
                    /////////////////////
                    queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                    queryMesas.setParameter("id", id);
                    mesa = queryMesas.getSingleResult();

                    //////////////////////////
                    ////////////DM////////////
                    //////////////////////////
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("rol", "Dungeon Master");
                    queryPertenecemesas.setParameter("mesa", id);
                    pmesa = queryPertenecemesas.getSingleResult();

                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
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
                        queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                        queryUsuarios.setParameter("id", listaPerteneceMesa.get(i).getPertenecemesaPK().getUsuario());
                        useraux = queryUsuarios.getSingleResult();
                        listaUsuarios.add(useraux);
                    }

                    request.setAttribute("mesa", mesa);
                    request.setAttribute("rol", pmesa.getRol());
                    request.setAttribute("listaRoles", listaPerteneceMesa);
                    request.setAttribute("listaUsuarios", listaUsuarios);

                    vista = "/WEB-INF/jsp/mesas/mesa.jsp";
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
            object = (Mesas) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateMesas(Object object) {
        try {
            utx.begin();
            em.merge((Mesas) object);
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

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
        Mesas mesa = null;
        Pertenecemesa pmesa = null;
        int cantidad;

        TypedQuery<Mesas> queryMesas;
        TypedQuery<Pertenecemesa> queryPertenecemesas;

        Query queryAUX;

        ArrayList<String> listaLideres;
        ArrayList<Integer> listaCantidad;
        List<Pertenecemesa> listaPerteneceMesa;
        List<Mesas> listaMesas;

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
                        }

                        //////////////////////
                        /////////HASH/////////
                        //////////////////////
                        contrasenahash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

                        //////////////////////////////
                        //////////DESCIPCION//////////
                        //////////////////////////////
                        if (descripcion != null) {
                            if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                    || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                    || descripcion.toUpperCase().contains("DROP")) {
                                throw new Exception("El Titulo no es válido");
                            }
                        }

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        if (descripcion != null && contrasena != null) {

                            mesa = new Mesas(creador, comunidad, tamano, titulo, descripcion, contrasenahash);

                        } else if (descripcion != null && contrasena == null) {

                            mesa = new Mesas(creador, comunidad, tamano, titulo, descripcion, 0);

                        } else if (descripcion == null && contrasena != null) {

                            mesa = new Mesas(creador, comunidad, tamano, titulo, contrasenahash, 1);
                        } else {

                            mesa = new Mesas(creador, comunidad, tamano, titulo);
                        }

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
                    } catch (Exception ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                        System.out.println("Exception: " + ex.getMessage());
                    }
                } else {
                    msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                    System.out.println("Error: Introduzca los campos de forma correcta ");
                }
                if (conseguido == true) {
                    pmesa = new Pertenecemesa(user.getApodo(), titulo, "Lider");
                    persist(pmesa);

                    vista = "/Mesas/mostrarMesasUsuario";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/Formularios/crearmesa";
                }
                break;
            case "/modificarMesa":
                conseguido = false;
                msj = "";

                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                creador = user.getApodo();
                comunidad = request.getParameter("comunidad");
                titulo = request.getParameter("titulo");
                descripcion = request.getParameter("descripcion");
                tamanoString = request.getParameter("tamano");

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
                        queryAUX = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
                        queryAUX.setParameter("creador", user.getApodo());
                        result = queryAUX.getResultList();

                        if (((Number) result).intValue() == 5) {
                            throw new Exception("Ya tienes el limite de mesas");
                        }

                        //////////////////////////////
                        //////////DESCIPCION//////////
                        //////////////////////////////
                        if (descripcion != null) {
                            if (descripcion.toUpperCase().contains("UPDATE") || descripcion.toUpperCase().contains("CREATE")
                                    || descripcion.toUpperCase().contains("DELETE") || descripcion.toUpperCase().contains("SELECT")
                                    || descripcion.toUpperCase().contains("DROP")) {
                                throw new Exception("El Titulo no es válido");
                            }
                        }

                        //////////////////////////////
                        //////////MODIFICAMOS/////////
                        //////////////////////////////
                        mesa = new Mesas(creador, comunidad, tamano, titulo);
                        mesa.setDescripcion(descripcion);

                        updateMesas(mesa);
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
                    } catch (Exception ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                        System.out.println("Exception: " + ex.getMessage());
                    }
                } else {
                    msj = "<p style=\"margin-left: 10px\"> Error: Introduzca los campos de forma correcta </p>";
                    System.out.println("Error: Introduzca los campos de forma correcta ");
                }
                if (conseguido == true) {

                    vista = "/Mesas/mostrarMesa";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/Formularios/crearmesa";
                }
                break;
            case "/eliminarMesa":
                titulo = request.getParameter("titulo");

                queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                queryMesas.setParameter("titulo", titulo);
                mesa = queryMesas.getSingleResult();

                deleteMesas(mesa);
                vista = "/Mesas/mostrarMesasUsuario";
                break;
            case "/mostrarMesas":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

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
                                    + "    WHERE P.MESA = M.TITULO "
                                    + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                    + ") "
                                    + "AND M.TAMANO > ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
                                    + ") "
                                    + "ORDER BY M.TITULO DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE NOT EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
                                    + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                    + ") "
                                    + "AND M.TAMANO = ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
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
                                    + "    WHERE P.MESA = M.TITULO "
                                    + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                    + ") "
                                    + "AND M.TAMANO > ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
                                    + ") "
                                    + "ORDER BY M.TITULO ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE NOT EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
                                    + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                    + ") "
                                    + "AND M.TAMANO = ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.TITULO "
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
                    queryPertenecemesas.setParameter("rol", "Lider");
                    queryPertenecemesas.setParameter("mesa", listaMesas.get(i).getTitulo());
                    pmesa = queryPertenecemesas.getSingleResult();
                    listaLideres.add(pmesa.getPertenecemesaPK().getUsuario());

                    queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                    queryAUX.setParameter("mesa", listaMesas.get(i).getTitulo());
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
                break;
            case "/mostrarMesasUsuario":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

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
                                + "    WHERE P.MESA = M.TITULO "
                                + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                + ") "
                                + "AND M.TAMANO > ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
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
                                + "    WHERE P.MESA = M.TITULO "
                                + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                + ") "
                                + "AND M.TAMANO > ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
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
                                + "    WHERE P.MESA = M.TITULO "
                                + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                + ") "
                                + "AND M.TAMANO > ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
                                + ") "
                                + "ORDER BY ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
                                + ") DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        break;
                    case "ordenar4":
                        sql = "SELECT M.* "
                                + "FROM MESAS M "
                                + "WHERE EXISTS ( "
                                + "    SELECT * "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
                                + "      AND P.USUARIO = '" + user.getApodo() + "' "
                                + ") "
                                + "AND M.TAMANO > ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
                                + ") "
                                + "ORDER BY ( "
                                + "    SELECT COUNT(*) "
                                + "    FROM PERTENECEMESA P "
                                + "    WHERE P.MESA = M.TITULO "
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
                    queryPertenecemesas.setParameter("rol", "Lider");
                    queryPertenecemesas.setParameter("mesa", listaMesas.get(i).getTitulo());
                    pmesa = queryPertenecemesas.getSingleResult();
                    listaLideres.add(pmesa.getPertenecemesaPK().getUsuario());

                    queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                    queryAUX.setParameter("mesa", listaMesas.get(i).getTitulo());
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
                break;
            case "/anadiraMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                titulo = request.getParameter("titulo");
                contrasena = request.getParameter("contrasena_anadirmesa");

                queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                queryMesas.setParameter("titulo", titulo);
                mesa = queryMesas.getSingleResult();

                if (!contrasena.equals("")) {
                    if (!BCrypt.checkpw(contrasena, mesa.getContrasena())) {
                        vista = "/Mesas/mostrarMesas";
                    } else {
                        pmesa = new Pertenecemesa(user.getApodo(), titulo, "Miembro");
                        persist(pmesa);
                        vista = "/Mesas/mostrarMesa";
                    }
                } else {
                    pmesa = new Pertenecemesa(user.getApodo(), titulo, "Miembro");
                    persist(pmesa);
                    vista = "/Mesas/mostrarMesa";
                }
                break;
            case "/salirdeMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                titulo = request.getParameter("titulo");
                
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getApodo());
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                deletePMesas(pmesa);

                vista = "/Mesas/mostrarMesasUsuario";
                break;
            case "/eliminardeMesa":
                titulo = request.getParameter("titulo");
                apodo = request.getParameter("usuario");
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", apodo);
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                deletePMesas(pmesa);

                vista = "/Mesas/mostrarMesa";
                break;
            case "/cambiarlider":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                titulo = request.getParameter("titulo");
                apodo = request.getParameter("usuario");

                //NuevoLider
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", apodo);
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                pmesa.setRol("Lider");
                updateMesas(pmesa);
                //AntiguoLider
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getApodo());
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                pmesa.setRol("Miembro");
                updateMesas(pmesa);

                vista = "/Mesas/mostrarMesa";
                break;
            case "/mostrarMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                titulo = request.getParameter("titulo");

                /////////////////////
                /////////MESA////////
                /////////////////////
                queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                queryMesas.setParameter("titulo", titulo);
                mesa = queryMesas.getSingleResult();

                /////////////////////
                //////////ROL////////
                /////////////////////
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getApodo());
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();

                request.setAttribute("mesa", mesa);
                request.setAttribute("pmesa", pmesa);

                vista = "/WEB-INF/jsp/mesas/mesa.jsp";
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

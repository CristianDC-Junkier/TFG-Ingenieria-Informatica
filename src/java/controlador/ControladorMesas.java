
package controlador;

import entidades.Mesas;
import entidades.Pertenecemesa;
import entidades.Usuarios;
import java.io.IOException;
import java.text.ParseException;
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

        List<Pertenecemesa> listaPerteneceMesa;
        List<Mesas> listaMesas;
        List<Integer> listaCantidad;

        String apodo;
        String creador;
        String comunidad;
        String tamanoString;
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
                        queryMesas = em.createNamedQuery("Mesas.CountByCreador", Mesas.class);
                        queryMesas.setParameter("creador", creador);
                        listaMesas = queryMesas.getResultList();

                        if (listaMesas.size() == 5) {
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

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        mesa = new Mesas(creador, comunidad, tamano, titulo);
                        mesa.setDescripcion(descripcion);

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

                    vista = "/jsp/Mesas/mostrarMesas";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/crearmesa.jsp";
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

                        update(mesa);
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

                    vista = "/Mesas/mostrarMesas";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/crearmesa.jsp";
                }
                break;
            case "/eliminarMesa":
                titulo = request.getParameter("titulo");

                queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                queryMesas.setParameter("titulo", titulo);
                listaMesas = queryMesas.getResultList();

                delete(listaMesas.get(0));
                vista = "/Mesas/mostrarMesas";
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
                System.out.println("Llega mesa: " + lleno);

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
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO <> '" + user.getApodo() + "' "
                                    + " AND m.TAMANO = (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo DESC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO <> '" + user.getApodo() + "' "
                                    + " AND m.TAMANO > (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo DESC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (lleno.equalsIgnoreCase("false")) {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO <> '" + user.getApodo() + "' "
                                    + " AND m.TAMANO = (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.creador ASC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO = '" + user.getApodo() + "' "
                                    + " AND m.TAMANO > (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.creador ASC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Mesas.class);
                listaMesas = queryAUX.getResultList();

                if (!listaMesas.isEmpty()) {
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("rol", "Lider");
                    queryPertenecemesas.setParameter("mesa", listaMesas.get(0));
                    listaPerteneceMesa = queryPertenecemesas.getResultList();

                    queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", int.class);
                    queryAUX.setParameter("rol", "Lider");
                    queryAUX.setParameter("mesa", listaMesas.get(0));
                    listaCantidad = queryAUX.getResultList();

                    for (int i = 1; i < listaMesas.size(); i++) {
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("mesa", listaMesas.get(i));
                        pmesa = queryPertenecemesas.getSingleResult();
                        listaPerteneceMesa.add(pmesa);

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(0));
                        cantidad = (int) queryAUX.getSingleResult();
                        listaCantidad.add(i);
                    }
                } else {
                    listaPerteneceMesa = null;
                    listaCantidad = null;
                }

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale lleno:" + lleno);
                System.out.println("Sale npag:" + numPag);

                request.setAttribute("listaMesas", listaMesas);
                request.setAttribute("listaPerteneceMesa", listaPerteneceMesa);
                request.setAttribute("listaMesas", listaCantidad);
                request.setAttribute("orden", ordenar);
                request.setAttribute("lleno", lleno);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/jsp/mesas/mesas.jsp";
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

                //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                numPag = (((Number) result).intValue() / 10) + 1;

                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                lleno = request.getParameter("lleno");//si filtramos por lleno o no

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega mesa: " + lleno);

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
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO = '" + user.getApodo() + "' "
                                    + " AND m.TAMANO = (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo DESC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO = '" + user.getApodo() + "' "
                                    + " AND m.TAMANO > (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo DESC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                    case "ordenar2":
                        if (lleno.equalsIgnoreCase("false")) {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO = '" + user.getApodo() + "' "
                                    + " AND m.TAMANO = (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo ASC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        } else {
                            sql = "SELECT m.*"
                                    + " FROM MESAS m JOIN PERTENECEMESA p ON m.TITULO = p.MESA"
                                    + " WHERE p.USUARIO = '" + user.getApodo() + "' "
                                    + " AND m.TAMANO > (SELECT COUNT(*) FROM PERTENECEMESA WHERE MESA = m.TITULO)"
                                    + " ORDER BY m.titulo ASC"
                                    + " OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                        }
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Mesas.class);
                listaMesas = queryAUX.getResultList();

                if (!listaMesas.isEmpty()) {
                    queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                    queryPertenecemesas.setParameter("rol", "Lider");
                    queryPertenecemesas.setParameter("mesa", listaMesas.get(0));
                    listaPerteneceMesa = queryPertenecemesas.getResultList();

                    queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", int.class);
                    queryAUX.setParameter("rol", "Lider");
                    queryAUX.setParameter("mesa", listaMesas.get(0));
                    listaCantidad = queryAUX.getResultList();

                    for (int i = 1; i < listaMesas.size(); i++) {
                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByRolMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("mesa", listaMesas.get(i));
                        pmesa = queryPertenecemesas.getSingleResult();
                        listaPerteneceMesa.add(pmesa);

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(0));
                        cantidad = (int) queryAUX.getSingleResult();
                        listaCantidad.add(i);
                    }
                } else {
                    listaPerteneceMesa = null;
                    listaCantidad = null;
                }

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale lleno:" + lleno);
                System.out.println("Sale npag:" + numPag);

                request.setAttribute("listaMesas", listaMesas);
                request.setAttribute("listaPerteneceMesa", listaPerteneceMesa);
                request.setAttribute("orden", ordenar);
                request.setAttribute("mesa", lleno);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/jsp/mesas/mesasPerfil.jsp";
                break;
            case "/anadiraMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                titulo = request.getParameter("titulo");
                pmesa = new Pertenecemesa(user.getApodo(), titulo, "Miembro");
                persist(pmesa);
                vista = "/jsp/mesas/mesasPerfil.jsp";
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
                delete(pmesa);

                vista = "/jsp/mesas/mesasPerfil.jsp";
                break;
            case "/eliminardeMesa":
                titulo = request.getParameter("titulo");
                apodo = request.getParameter("usuario");
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", apodo);
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                delete(pmesa);

                vista = "/jsp/mesas/mesasPerfil.jsp";
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
                update(pmesa);
                //AntiguoLider
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getApodo());
                queryPertenecemesas.setParameter("mesa", titulo);
                pmesa = queryPertenecemesas.getSingleResult();
                pmesa.setRol("Miembro");
                update(pmesa);

                vista = "/jsp/mesas/mesasPerfil.jsp";
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
                queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                queryPertenecemesas.setParameter("usuario", user.getApodo());
                pmesa = queryPertenecemesas.getSingleResult();

                request.setAttribute("mesa", mesa);
                request.setAttribute("pmesa", pmesa);

                vista = "/jsp/mesas/mesa.jsp";
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
            object = (Mesas) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void update(Object object) {
        try {
            utx.begin();
            em.merge((Mesas) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import entidades.Mesas;
import entidades.Usuarios;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        Mesas mesas = null;

        TypedQuery<Usuarios> queryUsuarios;
        TypedQuery<Mesas> queryMesas;

        Query queryAUX;

        List<Usuarios> listaUsuarios;
        List<Mesas> listaMesas;

        String id;
        String apodo;
        String creador;
        String comunidad;
        String tamanoString;
        short tamano;
        String titulo;
        String descripcion;

        String ordenar;
        String mesa;
        String peticiones;
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
                        if (titulo.toUpperCase().startsWith("UPDATE") || titulo.toUpperCase().startsWith("CREATE")
                                || titulo.toUpperCase().startsWith("DELETE") || titulo.toUpperCase().startsWith("SELECT")) {
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
                        queryMesas = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                        queryMesas.setParameter("creador", creador);
                        listaMesas = queryMesas.getResultList();

                        if (listaMesas.size() == 5) {
                            throw new Exception("Ya tienes el limite de mesas");
                        }
                        
                        //////////////////////////////
                        //////////DESCIPCION//////////
                        //////////////////////////////
                        if (descripcion != null) {
                            if (descripcion.toUpperCase().startsWith("UPDATE") || descripcion.toUpperCase().startsWith("CREATE")
                                    || descripcion.toUpperCase().startsWith("DELETE") || descripcion.toUpperCase().startsWith("SELECT")) {
                                throw new Exception("El Titulo no es válido");
                            }
                        }

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        mesas = new Mesas(creador, comunidad, tamano, titulo);
                        mesas.setDescripcion(descripcion);

                        persist(mesas);
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
                    
                    vista = "/jsp/Mesas/mostrarMesas";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/crearmesa.jsp";
                }
                break;
            case "/modificarMesa":
                break;
            case "/eliminarMesa":
                break;
            case "/mostrarMesas":
                break;
            case "/anadiraMesa":
                break;
            case "/eliminardeMesa":
                break;
            case "/cambiarlider":
                break;
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
            em.merge((Mesas) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

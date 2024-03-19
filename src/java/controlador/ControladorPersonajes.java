package controlador;

import entidades.Atributos;
import entidades.Clases;
import entidades.Razas;
import entidades.Subclases;
import entidades.Subrazas;
import entidades.Trasfondos;
import entidades.Usuarios;
import java.io.IOException;
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
        TypedQuery<Atributos> queryAtributos;
        TypedQuery<Trasfondos> queryTrasfondos;
        TypedQuery<Subclases> querySubClases;
        TypedQuery<Subrazas> querySubRazas;

        Query queryAUX;

        List<Usuarios> listaUsuarios;
        List<Clases> listaClases;
        List<Razas> listaRazas;
        List<Subclases> listaSubClases;
        List<Subrazas> listaSubRazas;

        Usuarios user;
        Usuarios useraux;

        String id;

        switch (accion) {
            case "/crearpersonaje":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    
                    //Numero total de personajes
                    request.setAttribute("personajesTotales", 0);
                    
                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryAtributos = em.createNamedQuery("Atributos.findAll", Atributos.class);
                    request.setAttribute("listaAtributos", queryAtributos.getResultList());
                    queryTrasfondos = em.createNamedQuery("Trasfondos.findAll", Trasfondos.class);
                    request.setAttribute("listaTrasfondos", queryAtributos.getResultList());

                    vista = "/WEB-INF/jsp/formularios/crearpersonaje.jsp";
                }
                break;
            case "/personajes":

                queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                request.setAttribute("listaClases", queryClases.getResultList());

                queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                request.setAttribute("listaRazas", queryRazas.getResultList());

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

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());

                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

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

                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                    queryUsuarios.setParameter("id", id);
                    useraux = queryUsuarios.getSingleResult();

                    request.setAttribute("amigo", useraux);

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

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());

                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

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

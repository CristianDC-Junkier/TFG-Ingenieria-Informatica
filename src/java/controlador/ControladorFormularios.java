package controlador;

import entidades.Atributos;
import entidades.Clases;
import entidades.Mesas;
import entidades.Razas;
import entidades.Trasfondos;
import entidades.Usuarios;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@WebServlet(name = "ControladorFormularios", urlPatterns = {"/Formularios/*"})
public class ControladorFormularios extends HttpServlet {

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

        HttpSession session;

        Usuarios user;
        Mesas mesa;

        TypedQuery<Mesas> queryMesas;
        TypedQuery<Clases> queryClases;
        TypedQuery<Razas> queryRazas;
        TypedQuery<Atributos> queryAtributos;
        TypedQuery<Trasfondos> queryTrasfondos;

        String id;
        int numMesasCreadas;

        String accion;
        accion = request.getPathInfo();
        String vista = "";

        switch (accion) {
            case "/contraseñaperdida":

                vista = "/WEB-INF/jsp/formularios/contraseñaperdida.jsp";
                break;
            case "/crearmesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    /////////////////////
                    /////////MESA////////
                    /////////////////////
                    queryMesas = em.createNamedQuery("Mesas.findByCreador", Mesas.class);
                    queryMesas.setParameter("creador", user.getApodo());
                    numMesasCreadas = queryMesas.getResultList().size();

                    request.setAttribute("mesasTotales", numMesasCreadas);

                    vista = "/WEB-INF/jsp/formularios/crearmesa.jsp";
                }
                break;

            case "/iniciosesion":
                vista = "/WEB-INF/jsp/formularios/iniciosesion.jsp";
                break;
            case "/modificarmesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    id = request.getParameter("id");

                    /////////////////////
                    /////////MESA////////
                    /////////////////////
                    queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                    queryMesas.setParameter("id", id);
                    mesa = queryMesas.getSingleResult();

                    request.setAttribute("mesa", mesa);
                    vista = "/WEB-INF/jsp/formularios/modificarmesa.jsp";
                    break;
                }
            case "/modificarusuario":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    vista = "/WEB-INF/jsp/formularios/modificarusuario.jsp";
                }
                break;
            case "/registro":
                vista = "/WEB-INF/jsp/formularios/registro.jsp";
                break;
            case "/restablecercontraseña":
                vista = "/WEB-INF/jsp/formularios/restablecercontraseña.jsp";
                break;
            case "/usuarioperdido":
                vista = "/WEB-INF/jsp/formularios/usuarioperdido.jsp";
                break;
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
                    request.setAttribute("listaTrasfondos", queryTrasfondos.getResultList());

                    vista = "/WEB-INF/jsp/formularios/crearpersonaje.jsp";
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

}


package controlador;

import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ControladorExplorar", urlPatterns = {"/Explorar/*"})
public class ControladorExplorar extends HttpServlet {

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
        
        String accion;
        accion = request.getPathInfo();
        String vista = "";
        
        switch (accion) {
            case "/clases":
                vista = "/jsp/explorar/clases.jsp";
                break;
            case "/dotes":
                vista = "/jsp/explorar/dotes.jsp";
                break;
            case "/equipo":
                vista = "/jsp/explorar/equipo.jsp";
                break;
            case "/estados":
                vista = "/jsp/explorar/estados.jsp";
                break;
            case "/hechizos":
                vista = "/jsp/explorar/hechizos.jsp";
                break;
            case "/monstruos":
                vista = "/jsp/explorar/monstruos.jsp";
                break;
            case "/propiedades":
                vista = "/jsp/explorar/propiedades.jsp";
                break;
            case "/razas":
                vista = "/jsp/explorar/razas.jsp";
                break;
            case "/trasfondos":
                vista = "/jsp/explorar/trasfondos.jsp";
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

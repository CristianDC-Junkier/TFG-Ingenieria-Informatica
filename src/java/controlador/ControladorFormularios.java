
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
        
        String accion;
        accion = request.getPathInfo();
        String vista = "";
         
        switch (accion) {
            case "/contraseñaperdida":
                vista = "/jsp/formularios/contraseñaperdida.jsp";
                break;
            case "/crearmesa":
                vista = "/jsp/formularios/crearmesa.jsp";
                break;
            case "/iniciosesion":
                vista = "/jsp/formularios/iniciosesion.jsp";
                break;
            case "/modificarmesa":
                vista = "/jsp/formularios/modificarmesa.jsp";
                break;
            case "/modificarusuario":
                vista = "/jsp/formularios/modificarusuario.jsp";
                break;
            case "/registro":
                vista = "/jsp/formularios/registro.jsp";
                break;
            case "/restablecercontraseña":
                vista = "/jsp/formularios/restablecercontraseña.jsp";
                break;
            case "/usuarioperdido":
                vista = "/jsp/formularios/usuarioperdido.jsp";
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

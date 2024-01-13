package controlador;

import entidades.Amigos;
import entidades.Mensajesamigos;
import entidades.Usuarios;
import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@WebServlet(name = "ControladorChats", urlPatterns = {"/Chats/*"})
public class ControladorChats extends HttpServlet {

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

        HttpSession session;

        Usuarios user = null;
        Usuarios useraux = null;
        Amigos amigo = null;
        Mensajesamigos msjA = null;

        TypedQuery<Usuarios> queryUsuarios;
        TypedQuery<Amigos> queryAmigos;

        String msj;
        String id;

        Date fecha;

        switch (accion) {
            //////////////////////////////////////////////////////////////////////////
            //////////////////////////////CHAT NORMAL/////////////////////////////////
            //////////////////////////////////////////////////////////////////////////
            case "/enviarmensaje":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    //No hacemos nada
                } else {
                    id = request.getParameter("amigo");

                    queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                    queryAmigos.setParameter("amigo1", user.getId());
                    queryAmigos.setParameter("amigo2", id);

                    if (queryAmigos.getResultList().size() != 1) {
                        //No hacemos nada
                    } else {

                        msj = request.getParameter("mensaje");

                        fecha = new Date();

                        msjA = new Mensajesamigos(msj, fecha, id, user.getId());

                        persist(msjA);
                    }
                }
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
}

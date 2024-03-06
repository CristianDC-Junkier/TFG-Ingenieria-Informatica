package controlador;

import entidades.Amigos;
import entidades.Mensajesamigos;
import entidades.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
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

        request.setCharacterEncoding("UTF-8");

        try ( PrintWriter out = response.getWriter()) {

            String accion;
            accion = request.getPathInfo();

            HttpSession session;

            Usuarios user = null;
            Usuarios useraux = null;
            Mensajesamigos msjA = null;

            TypedQuery<Usuarios> queryUsuarios;
            TypedQuery<Amigos> queryAmigos;

            String msj = "";
            String id;
            String apodo;
            String dado;

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
                        msj = "No Conseguido";
                    } else {
                        apodo = request.getParameter("amigo");

                        if (apodo.equalsIgnoreCase("-1")) {
                            msj = "No Conseguido";
                        } else {
                            queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                            queryAmigos.setParameter("amigo1", user.getId());
                            queryAmigos.setParameter("amigo2", useraux.getId());

                            if (queryAmigos.getResultList().size() != 1) {
                                msj = "No Conseguido";
                            } else {

                                msj = request.getParameter("mensaje");

                                if (msj.toUpperCase().contains("UPDATE") || msj.toUpperCase().contains("CREATE")
                                        || msj.toUpperCase().contains("DELETE") || msj.toUpperCase().contains("SELECT")
                                        || msj.toUpperCase().contains("DROP")) {
                                    msj = "*****";
                                }

                                fecha = new Date();

                                msjA = new Mensajesamigos(msj, fecha, useraux, user);

                                persist(msjA);

                                msj = "Conseguido";
                            }
                        }
                    }
                    break;
                case "/enviartirada":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    dado = request.getParameter("dado");

                    if (user == null) {
                        msj = "No Conseguido";
                    } else {
                        apodo = request.getParameter("amigo");

                        if (apodo.equalsIgnoreCase("-1")) {
                            msj = "No Conseguido";
                        } else {
                            queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                            queryAmigos.setParameter("amigo1", user.getId());
                            queryAmigos.setParameter("amigo2", useraux.getId());

                            if (queryAmigos.getResultList().size() != 1) {
                                msj = "No Conseguido";
                            } else {

                                //msj = request.getParameter("tirada");
                                long tiempoActual = System.currentTimeMillis();
                                Random random = new Random(tiempoActual);
                                msj = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                                fecha = new Date();

                                msjA = new Mensajesamigos(msj, fecha, useraux, user);

                                persist(msjA);

                                msj = "Conseguido";
                            }
                        }
                    }
                    break;
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(msj);
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

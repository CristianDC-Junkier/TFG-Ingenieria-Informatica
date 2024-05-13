package controlador;

import entidades.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ControladorCorreo", urlPatterns = {"/Recuperacion/*"})
public class ControladorCorreo extends HttpServlet {

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

        Properties propiedades;
        MimeMessage message;
        Session session;
        
        Usuario user;
        
        TypedQuery<Usuario> queryUsuarios;

        String correo;
        String apodo;

        String msj;

        switch (accion) {
            case "/enviarUsuario":

                correo = request.getParameter("correo_usuario_rc");

                queryUsuarios = em.createNamedQuery("Usuario.findByCorreo", Usuario.class);
                queryUsuarios.setParameter("correo", correo);
                try {
                    user = queryUsuarios.getSingleResult();

                    // Datos del remitente
                    String remitente = "cristiandelgadoinformatica@gmail.com";
                    String contraseña = "krkuiqxzoxksqdcz";

                    propiedades = new Properties();
                    propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
                    propiedades.setProperty("mail.smtp.starttls.enable", "true");
                    propiedades.setProperty("mail.smtp.port", "587");
                    propiedades.setProperty("mail.smtp.user", remitente);
                    propiedades.setProperty("mail.smtp.auth", "true");

                    // Preparamos la sesion
                    session = Session.getDefaultInstance(propiedades);

                    try {
                        // Generar mensaje
                        message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(remitente));
                        message.addRecipient(
                                Message.RecipientType.TO,
                                new InternetAddress(correo));
                        message.setSubject("Guidance 4 - Recuperación de Usuario");
                        message.setText("Si le llega este mensaje es porque ha pedido recuperar su usuario, recuerde"
                                + " guardar su usuario para no perderlo"
                                + "\nSu usuario es: " + user.getApodo());

                        try {
                            // Enviar el mensaje
                            Transport transport = session.getTransport("smtp");
                            transport.connect(remitente, contraseña);
                            transport.sendMessage(message, message.getAllRecipients());
                            transport.close();

                            System.out.println("Correo electrónico enviado exitosamente.");

                            vista = "/Formularios/iniciosesion";

                        } catch (NoSuchProviderException ex) {
                            System.out.println("Error al enviar el mensaje: " + ex.getMessage());
                            msj = "<p style=\"margin-left: 10px\"> Error: No se puede enviar el mensaje</p>";
                            vista = "/Formularios/usuarioperdido?msj=" + msj;
                        }
                    } catch (Exception ex) {
                        System.out.println("Error al generar el mensaje: " + ex.getMessage());
                        msj = "<p style=\"margin-left: 10px\"> Error: No se puede generar el mensaje</p>";
                        vista = "/Formularios/usuarioperdido?msj=" + msj;
                    }
                } catch (Exception ex) {
                    System.out.println("Error, ese correo no existe");
                    msj = "<p style=\"margin-left: 10px\"> Error: Ese correo no existe</p>";
                    vista = "/Formularios/usuarioperdido?msj=" + msj;
                }
                break;
            case "/enviarContraseña":
                apodo = request.getParameter("nombre_usuario_rc");

                queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                queryUsuarios.setParameter("apodo", apodo);
                try {
                    user = queryUsuarios.getSingleResult();

                    // Datos del remitente
                    String remitente = "cristiandelgadoinformatica@gmail.com";
                    String contraseña = "krkuiqxzoxksqdcz";

                    propiedades = new Properties();
                    propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
                    propiedades.setProperty("mail.smtp.starttls.enable", "true");
                    propiedades.setProperty("mail.smtp.port", "587");
                    propiedades.setProperty("mail.smtp.user", remitente);
                    propiedades.setProperty("mail.smtp.auth", "true");

                    // Preparamos la sesion
                    session = Session.getDefaultInstance(propiedades);

                    try {
                        //Localmente:
                        //"http://localhost:8080/TFG/Formularios/restablecercontraseña?id="
                        //Online:
                        //"http://34.0.197.254:8080/TFG/Formularios/restablecercontraseña?id="
                        
                        // Generar mensaje
                        message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(remitente));
                        message.addRecipient(
                                Message.RecipientType.TO,
                                new InternetAddress(user.getCorreo()));
                        message.setSubject("Guidance 4 - Restablecimiento de Contraseña");
                        message.setText("Si le llega este mensaje es porque ha pedido recuperar su contraseña, recuerde "
                                + "guardar su contraseña para no perderla, haga click en el siguiente enlace para poder "
                                + "restablecer la contraseña"
                                + "\nSu usuario es: " + user.getApodo()
                                + "\nRecuperar contraseña: "
                                + "http://34.0.197.254:8080/TFG/Formularios/restablecercontraseña?id="
                                + user.getId() + "&password=" + user.getContrasena());

                        try {
                            // Enviar el mensaje
                            Transport transport = session.getTransport("smtp");
                            transport.connect(remitente, contraseña);
                            transport.sendMessage(message, message.getAllRecipients());
                            transport.close();

                            System.out.println("Correo electrónico enviado exitosamente.");

                            vista = "/Formularios/iniciosesion";

                        } catch (NoSuchProviderException ex) {
                            System.out.println("Error al enviar el mensaje: " + ex.getMessage());
                            msj = "<p style=\"margin-left: 10px\"> Error: No se puede enviar el mensaje</p>";
                            vista = "/Formularios/contraseñaperdida?msj=" + msj;
                        }
                    } catch (Exception ex) {
                        System.out.println("Error al generar el mensaje: " + ex.getMessage());
                        msj = "<p style=\"margin-left: 10px\"> Error: No se puede generar el mensaje</p>";
                        vista = "/Formularios/contraseñaperdida?msj=" + msj;
                    }
                } catch (Exception ex) {
                    System.out.println("Error, ese usuario no existe");
                    msj = "<p style=\"margin-left: 10px\"> Error: Ese usuario no existe</p>";
                    vista = "/Formularios/contraseñaperdida?msj=" + msj;
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

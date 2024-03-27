package controlador;

import entidades.Mesas;
import entidades.Personajes;
import entidades.Usuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ControladorImagenes", urlPatterns = {"/Imagenes/*"})
@MultipartConfig
public class ControladorImagenes extends HttpServlet {

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

        Usuarios user;
        Mesas mesa;
        Personajes personaje;

        TypedQuery<Mesas> queryMesas;
        TypedQuery<Personajes> queryPersonajes;

        Part filePart;

        String id;
        InputStream fileContent;
        byte[] imageData;

        boolean cambiarVista = true;

        switch (accion) {
            case "/actualizarFotoMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////
                /////////ES TU MESA//////////
                /////////////////////////////
                id = request.getParameter("id");

                queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                queryMesas.setParameter("id", id);
                mesa = queryMesas.getSingleResult();

                if (user == null || !mesa.getCreador().equals(user.getApodo())) {
                    vista = "/TFG/Principal/inicio";
                } else {

                    filePart = request.getPart("imagen");
                    fileContent = filePart.getInputStream();

                    //Leer el contenido y almacenarlo en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }

                    //Convertir el contenido a un array de bytes
                    imageData = byteArrayOutputStream.toByteArray();

                    mesa.setImagenmesa(imageData);

                    updateMesas(mesa);

                    request.setAttribute("id", id);

                    vista = "/Mesas/mostrarMesa";
                }
                break;
            case "/mostrarImagenMesa":

                id = request.getParameter("id");

                /////////////////////
                /////////MESA////////
                /////////////////////
                queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                queryMesas.setParameter("id", id);
                mesa = queryMesas.getSingleResult();

                imageData = mesa.getImagenmesa();

                // Configurar la respuesta para que sea una imagen
                response.setContentType("image/jpeg");

                // Escribir los bytes de la imagen en la respuesta
                try ( ServletOutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(imageData);
                    cambiarVista = false;
                }
                break;
            case "/actualizarFotoPersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                /////////////////////////////
                ///////ES TU PERSONAJE///////
                /////////////////////////////
                id = request.getParameter("id");

                queryPersonajes = em.createNamedQuery("Personajes.findById", Personajes.class);
                queryPersonajes.setParameter("id", id);
                personaje = queryPersonajes.getSingleResult();

                if (user == null || !personaje.getUsuario().getId().equals(user.getId())) {
                    vista = "/TFG/Principal/inicio";
                } else {

                    filePart = request.getPart("imagen");
                    fileContent = filePart.getInputStream();

                    //Leer el contenido y almacenarlo en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }

                    //Convertir el contenido a un array de bytes
                    imageData = byteArrayOutputStream.toByteArray();

                    personaje.setImagenpersonaje(imageData);

                    updatePersonajes(personaje);

                    request.setAttribute("id", id);

                    vista = "/Personajes/personaje";
                }
                break;
            case "/mostrarImagenPersonaje":

                id = request.getParameter("id");

                ///////////////////////
                ///////PERSONAJE///////
                ///////////////////////
                queryPersonajes = em.createNamedQuery("Personajes.findById", Personajes.class);
                queryPersonajes.setParameter("id", id);
                personaje = queryPersonajes.getSingleResult();

                imageData = personaje.getImagenpersonaje();

                // Configurar la respuesta para que sea una imagen
                response.setContentType("image/jpeg");

                // Escribir los bytes de la imagen en la respuesta
                try ( ServletOutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(imageData);
                    cambiarVista = false;
                }
                break;
        }
        if (cambiarVista == true) {
            RequestDispatcher rd = request.getRequestDispatcher(vista);
            rd.forward(request, response);
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

    private void updateMesas(Object object) {
        try {
            utx.begin();
            em.merge((Mesas) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePersonajes(Object object) {
        try {
            utx.begin();
            em.merge((Personajes) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

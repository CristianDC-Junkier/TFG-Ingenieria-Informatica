package controlador;

import entidades.Usuarios;
import java.io.IOException;
import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

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
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/Usuarios/*"})
public class ControladorUsuarios extends HttpServlet {

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

        boolean conseguido;
        String msj;
        Usuarios user = null;

        TypedQuery<Usuarios> query;
        List<Usuarios> list;

        String apodo;
        String nombre;
        String correo;
        String contrasena;
        String telefono;
        String fechaNacimientoString;
        String provincia;
        String genero;

        switch (accion) {
            case "/mostrarUsuario":
                break;
            case "/crearUsuario":

                conseguido = false;
                msj = "";

                apodo = request.getParameter("nombre_usuario");
                nombre = request.getParameter("nombre_real");
                correo = request.getParameter("correo_usuario");
                contrasena = request.getParameter("usuario_contrasena");
                telefono = request.getParameter("usuario_telefono");
                fechaNacimientoString = request.getParameter("usuario_nacimiento");
                provincia = request.getParameter("provincia");
                genero = request.getParameter("genero");

                if (nombre != null && apodo != null && correo != null && contrasena != null && fechaNacimientoString != null
                        && provincia != null && genero != null) {

                    try {
                        //***********************************************************************************//
                        //************************************FECHA******************************************//
                        //***********************************************************************************//

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = formatoFecha.parse(fechaNacimientoString);

                        // Obtener la fecha actual
                        Date fechaActual = new Date();
                        // Crear un objeto Calendar y establecer la fecha actual
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        // Restar 10 años
                        calendarioAux.add(Calendar.YEAR, -10);
                        // Obtener la nueva fecha después de restar 10 años
                        fechaActual = calendarioAux.getTime();

                        // Comparar las fechas
                        if (fechaNacimiento.before(fechaActual)) {
                            conseguido = true;
                        } else {
                            throw new Exception("La fecha de nacimiento debe ser más pequeña que " + fechaActual);
                        }

                        //***********************************************************************************//
                        //************************************APODO******************************************//
                        //***********************************************************************************//
                        query = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                        query.setParameter("apodo", apodo);
                        list = query.getResultList();

                        if (!list.isEmpty()) {
                            throw new Exception("El Nombre de usuario debe ser único ");
                        }
                        //***********************************************************************************//
                        //************************************CORREO*****************************************//
                        //***********************************************************************************//

                        query = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                        query.setParameter("correo", apodo);
                        list = query.getResultList();

                        if (!list.isEmpty()) {
                            throw new Exception("El Correo debe ser único ");
                        }

                        //***********************************************************************************//
                        //************************************TELEFONO***************************************//
                        //***********************************************************************************//
                        BigInteger telefonoBI;

                        if (telefono != null && !telefono.equals("")) {
                            telefonoBI = new BigInteger(telefono);

                            query = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                            query.setParameter("correo", apodo);
                            list = query.getResultList();

                            if (!list.isEmpty()) {
                                throw new Exception("El Teléfono debe ser único ");
                            }

                        } else {
                            telefonoBI = null;
                        }

                        //***********************************************************************************//
                        //************************************CREAMOS****************************************//
                        //***********************************************************************************//
                        user = new Usuarios(apodo, nombre, correo, contrasena, fechaNacimiento, provincia, genero, (short) 0);
                        user.setTelefono(telefonoBI);

                        persist(user);
                        System.out.println("Registrado: " + nombre);
                        conseguido = true;

                    } catch (NumberFormatException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible registrar en este momento: " + nombre);
                        System.out.println("NumberFormatException: " + ex.getMessage());
                    } catch (ParseException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible registrar en este momento </p>";
                        System.out.println("Error: Imposible registrar en este momento: " + nombre);
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

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    vista = "/jsp/inicio/inicio.jsp";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/jsp/formularios/registro.jsp";
                }
                break;
            case "/iniciarSesion":
                        
                try {
                apodo = request.getParameter("nombre_usuario");
                contrasena = request.getParameter("usuario_contrasena");

                //***********************************************************************************//
                //************************************APODO******************************************//
                //***********************************************************************************//
                query = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                query.setParameter("apodo", apodo);
                list = query.getResultList();

                if (list.isEmpty()) {
                    throw new Exception("El Nombre de usuario no se encuentra ");
                } //***********************************************************************************//
                //*********************************CONTRASEÑA****************************************//
                //***********************************************************************************//
                else {
                    user = list.remove(0);

                    if (!user.getContrasena().equals(contrasena)) {
                        throw new Exception("La contraseña no es correcta");
                    } else {
                        /////////////////////////
                        /////////SESION//////////
                        /////////////////////////
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);

                        vista = "/jsp/inicio/inicio.jsp";
                        System.out.println("Identificado: " + user.getNombre());
                    }
                }
            } catch (Exception ex) {
                vista = "/jsp/formularios/iniciosesion.jsp";
                msj = "<p style=\"margin-top: 25px\"> Error: " + ex.getMessage() + "</p>";
                request.setAttribute("msj", msj);
                System.out.println("Exception: " + ex.getMessage());
            }
            break;
            case "/cerrarSesion":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                HttpSession session = request.getSession();
                session.invalidate();
                vista = "/jsp/inicio/inicio.jsp";
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

}

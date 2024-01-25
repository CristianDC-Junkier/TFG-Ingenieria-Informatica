package controlador;

import entidades.Clases;
import entidades.Dotes;
import entidades.Estados;
import entidades.Mejorasdote;
import entidades.Requisitosdote;
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

        request.setCharacterEncoding("UTF-8");

        String accion;
        accion = request.getPathInfo();
        String vista = "";

        Estados estado = null;

        Query queryAUX;
        TypedQuery<Estados> queryEstados;
        TypedQuery<Clases> queryClases;
        TypedQuery<Dotes> queryDotes;
        TypedQuery<Mejorasdote> queryMDotes;
        TypedQuery<Requisitosdote> queryRDotes;

        List<Estados> listaEstados;
        List<Clases> listaClases;
        List<Dotes> listaDotes;
        List<Mejorasdote> listaMDotes;
        List<Requisitosdote> listaRDotes;

        Clases clase;

        String sql;
        String nombre;
        String resultado;

        switch (accion) {
            case "/clases":

                queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                listaClases = queryClases.getResultList();

                resultado = "";

                for (int i = 0; i < listaClases.size(); i++) {
                    resultado
                            = resultado
                            + "<ul>\n <div class=\"ResumenClase\">"
                            + "<h5";
                    switch (i) {
                        case 0:
                            resultado
                                    = resultado + " id=\"Aa\"";
                            break;
                        case 1:
                            resultado
                                    = resultado + " id=\"B\"";
                            break;
                        case 4:
                            resultado
                                    = resultado + " id=\"C\"";
                            break;
                        case 7:
                            resultado
                                    = resultado + " id=\"D\"";
                            break;
                        case 8:
                            resultado
                                    = resultado + " id=\"E\"";
                            break;
                        case 9:
                            resultado
                                    = resultado + " id=\"H\"";
                            break;
                        case 10:
                            resultado
                                    = resultado + " id=\"L\"";
                            break;
                        case 11:
                            resultado
                                    = resultado + " id=\"M\"";
                        case 13:
                            resultado
                                    = resultado + " id=\"Pp\"";
                            break;
                    }
                    resultado
                            = resultado
                            + ">" + "<a href=\"/TFG/Explorar/clase?clase=" + listaClases.get(i).getNombre() + "\">" + listaClases.get(i).getNombre() + " </a></h5> "
                            + "<li>" + "<a href=\"/TFG/Explorar/clase?clase=" + listaClases.get(i).getNombre() + "\"> "
                            + "<img src=\"/TFG/img/clases/" + listaClases.get(i).getNombre().replaceAll("\\s", "") + ".jfif\"/> </a> "
                            + "<p>" + listaClases.get(i).getDescripcion()
                            + "</p> </li> </div> </ul>";
                }

                request.setAttribute("listaClases", resultado);

                vista = "/WEB-INF/jsp/explorar/clases.jsp";
                break;
            case "/clase":

                nombre = request.getParameter("clase");

                queryClases = em.createNamedQuery("Clases.findByNombre", Clases.class);
                queryClases.setParameter("nombre", nombre);
                clase = queryClases.getSingleResult();

                resultado = "<table class=\"tablaHechizos\">"
                        + "<thead>"
                        + "<tr>"
                        + "<th>Nivel</th>"
                        + "<th>BC</th>"
                        + "<th>H nv1</th>"
                        + "<th>H nv2</th>"
                        + "<th>H nv3</th>"
                        + "<th>H nv4</th>"
                        + "<th>H nv5</th>"
                        + "<th>H nv6</th>"
                        + "<th>H nv7</th>"
                        + "<th>H nv8</th>"
                        + "<th>H nv9</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody";

                for (int i = 0; i < 20; i++) {
                    resultado
                            = resultado
                            + "<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "<td>" + "-" + "</td>"
                            + "</tr>";

                }
                resultado = resultado
                        + "</tbody>"
                        + "</table>";

                request.setAttribute("tablaHechizos", resultado);

                resultado = clase.getEquipoinicial().replace("•", "<br>•");
                resultado = resultado.replaceFirst("<br>", "");

                request.setAttribute("equipoinicial", resultado);

                request.setAttribute("imagen", clase.getNombre().replaceAll("\\s", ""));
                request.setAttribute("clase", clase);

                vista = "/WEB-INF/jsp/explorar/clase.jsp";
                break;
            case "/dotes":

                queryDotes = em.createNamedQuery("Dotes.findAll", Dotes.class);
                listaDotes = queryDotes.getResultList();

                request.setAttribute("listaDotes", listaDotes);

                for (int i = 0; i < listaDotes.size(); i++) {

                    resultado = "";

                    queryMDotes = em.createNamedQuery("Mejorasdote.findByNombre", Mejorasdote.class);
                    queryMDotes.setParameter("nombre", listaDotes.get(i).getNombre());
                    listaMDotes = queryMDotes.getResultList();

                    for (Mejorasdote listaMDote : listaMDotes) {
                        resultado
                                = resultado
                                + "<li>" + listaMDote.getValor() + "</li>";
                    }
                    request.setAttribute("listaMDotes" + listaDotes.get(i).getNombre(), resultado);
                    
                    resultado = "";
                    
                    queryRDotes = em.createNamedQuery("Requisitosdote.findByNombre", Requisitosdote.class);
                    queryRDotes.setParameter("nombre", listaDotes.get(i).getNombre());
                    listaRDotes = queryRDotes.getResultList();

                    for (Requisitosdote listaRDote : listaRDotes) {
                        resultado
                                = resultado
                                + "<li class=\"RequisitoDote\">" + listaRDote.getValor() + "</li>";
                    }
                    request.setAttribute("listaRDotes" + listaDotes.get(i).getNombre(), resultado);
                }

                vista = "/WEB-INF/jsp/explorar/dotes.jsp";
                break;
            case "/equipo":
                vista = "/WEB-INF/jsp/explorar/equipo.jsp";
                break;
            case "/estados":

                queryEstados = em.createNamedQuery("Estados.findAll", Estados.class);
                listaEstados = queryEstados.getResultList();

                request.setAttribute("listaEstados", listaEstados);

                vista = "/WEB-INF/jsp/explorar/estados.jsp";
                break;
            case "/hechizos":
                vista = "/WEB-INF/jsp/explorar/hechizos.jsp";
                break;
            case "/monstruos":
                vista = "/WEB-INF/jsp/explorar/monstruos.jsp";
                break;
            case "/propiedades":
                vista = "/WEB-INF/jsp/explorar/propiedades.jsp";
                break;
            case "/razas":
                vista = "/WEB-INF/jsp/explorar/razas.jsp";
                break;
            case "/trasfondos":
                vista = "/WEB-INF/jsp/explorar/trasfondos.jsp";
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

package controlador;

import entidades.Alcance;
import entidades.Clases;
import entidades.Dotes;
import entidades.Equipo;
import entidades.Estados;
import entidades.Hechizos;
import entidades.Mejorasdote;
import entidades.Monstruos;
import entidades.Propiedades;
import entidades.Razas;
import entidades.Requisitosdote;
import entidades.Trasfondos;
import java.io.IOException;
import java.util.ArrayList;
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
        TypedQuery<Equipo> queryEquipo;
        TypedQuery<Alcance> queryAlcance;
        TypedQuery<Hechizos> queryHechizos;
        TypedQuery<Monstruos> queryMonstruos;
        TypedQuery<Razas> queryRazas;
        TypedQuery<Trasfondos> queryTrasfondos;

        List<Estados> listaEstados;
        List<Clases> listaClases;
        List<Dotes> listaDotes;
        List<Mejorasdote> listaMDotes;
        List<Requisitosdote> listaRDotes;
        List<Equipo> listaEquipo;
        List<List<Propiedades>> listalistaPropiedades;
        List<Hechizos> listaHechizos;
        List<Monstruos> listaMonstruos;
        List<Razas> listaRazas;
        List<String> listaRazasImagenes;
        List<Trasfondos> listaTrasfondos;

        Clases clase;
        Trasfondos Trasfondo;
        Razas Raza;
        Hechizos Hechizo;
        Monstruos Monstruo;
        Equipo Equipo;
        Alcance Alcance;

        String sql;
        String id;
        String nombre;
        String resultado;

        String titulo;
        String subtitulo;

        String tipo;
        String categoria;
        String propiedad;

        String escuela;
        String nivel;
        String claseH;

        String vd;

        String numString;
        int num;
        int pag;
        int numpag;

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

                resultado = "";

                for (int i = 0; i < listaDotes.size(); i++) {

                    nombre = listaDotes.get(i).getNombre();

                    resultado
                            = resultado
                            + "<div class=\"ResumenDote\">"
                            + "<div class=\"tituloDote\">"
                            + "<h5>"
                            + nombre
                            + "</h5></div>"
                            + "<div class=\"ContenidoDote\">"
                            + "<ul>";

                    queryRDotes = em.createNamedQuery("Requisitosdote.findByNombre", Requisitosdote.class);
                    queryRDotes.setParameter("nombre", nombre);
                    listaRDotes = queryRDotes.getResultList();

                    for (Requisitosdote listaRDote : listaRDotes) {
                        resultado
                                = resultado
                                + "<li class=\"RequisitoDote\">" + listaRDote.getValor() + "</li>";
                    }

                    queryMDotes = em.createNamedQuery("Mejorasdote.findByNombre", Mejorasdote.class);
                    queryMDotes.setParameter("nombre", nombre);
                    listaMDotes = queryMDotes.getResultList();

                    for (Mejorasdote listaMDote : listaMDotes) {
                        resultado
                                = resultado
                                + "<li>" + listaMDote.getValor() + "</li>";
                    }

                    resultado
                            = resultado
                            + "</ul>"
                            + "</div>"
                            + "</div>";
                }

                request.setAttribute("listaDotes", resultado);

                vista = "/WEB-INF/jsp/explorar/dotes.jsp";
                break;
            case "/objeto":
                id = request.getParameter("idEquipo");

                queryEquipo = em.createNamedQuery("Equipo.findById", Equipo.class);
                queryEquipo.setParameter("id", id);
                Equipo = queryEquipo.getSingleResult();

                try {
                    queryAlcance = em.createNamedQuery("Alcance.findByNombre", Alcance.class);
                    queryAlcance.setParameter("nombre", Equipo.getNombre());
                    Alcance = queryAlcance.getSingleResult();
                } catch (javax.persistence.NoResultException ex) {
                    Alcance = null;
                }

                resultado = "<div class = \"datosDerechaEquipo\"> <h3>Propiedades</h3> ";
                if (Equipo.getPropiedadesList().isEmpty()) {
                    resultado = resultado + "<p>No tiene propiedades</p> </div>";
                } else {
                    resultado = resultado + "<ul>";
                    for (int i = 0; i < Equipo.getPropiedadesList().size(); i++) {
                        resultado
                                = resultado + "<li>"
                                + Equipo.getPropiedadesList().get(i).getNombre()
                                + Equipo.getPropiedadesList().get(i).getDescripcion()
                                + "</li>";
                    }
                    resultado = resultado + "</ul></div>";
                }

                request.setAttribute("equipo", Equipo);
                request.setAttribute("proEquipo", resultado);
                request.setAttribute("alcance", Alcance);

                vista = "/WEB-INF/jsp/explorar/objeto.jsp";
                break;
            case "/equipo":

                numString = request.getParameter("pag");//numero de pag en la que estoy
                tipo = request.getParameter("tipo");
                categoria = request.getParameter("categoria");
                propiedad = request.getParameter("propiedad");

                if (numString != null) {
                    pag = Integer.valueOf(numString);
                    num = (pag - 1) * 15;//offset
                } else {
                    pag = 1;
                    num = 0;
                }

                if (numString == null) {
                    titulo = "Todos";
                    subtitulo = "Objetos";

                    queryEquipo = em.createNamedQuery("Equipo.findAll", Equipo.class);
                    numpag = queryEquipo.getResultList().size();
                    queryEquipo.setMaxResults(15);
                    listaEquipo = queryEquipo.getResultList();

                } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TODOS
                    titulo = tipo;
                    subtitulo = categoria;

                    sql = "SELECT e.* FROM Equipo e "
                            + "INNER JOIN Tienepropiedades tp on tp.equipo = e.id "
                            + "INNER JOIN Propiedades p on p.id = tp.propiedad "
                            + "WHERE e.TIPO = '" + tipo + "' "
                            + "AND e.CATEGORIA = '" + categoria + "' "
                            + "AND p.NOMBRE ='" + propiedad + "' "
                            + "ORDER BY e.nombre";

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaEquipo = queryAUX.getResultList();
                } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO y CAT
                    titulo = tipo;
                    subtitulo = categoria;

                    queryEquipo = em.createNamedQuery("Equipo.findByTipoCategoria", Equipo.class);
                    queryEquipo.setParameter("tipo", tipo);
                    queryEquipo.setParameter("categoria", categoria);
                    numpag = queryEquipo.getResultList().size();

                    queryEquipo = em.createNamedQuery("Equipo.findByTipoCategoria", Equipo.class);
                    queryEquipo.setParameter("tipo", tipo);
                    queryEquipo.setParameter("categoria", categoria);
                    queryEquipo.setFirstResult(num);
                    queryEquipo.setMaxResults(15);
                    listaEquipo = queryEquipo.getResultList();

                } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TIPO y PROP
                    titulo = tipo;
                    subtitulo = "Objetos";

                    sql = "SELECT e.* FROM Equipo e "
                            + "INNER JOIN Tienepropiedades tp on tp.equipo = e.id "
                            + "INNER JOIN Propiedades p on p.id = tp.propiedad "
                            + "WHERE e.TIPO = '" + tipo + "' "
                            + "AND p.NOMBRE ='" + propiedad + "' "
                            + "ORDER BY e.nombre";

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaEquipo = queryAUX.getResultList();

                } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//CAT y PROP
                    titulo = "Todos";
                    subtitulo = categoria;

                    sql = "SELECT e.* FROM Equipo e "
                            + "INNER JOIN Tienepropiedades tp on tp.equipo = e.id "
                            + "INNER JOIN Propiedades p on p.id = tp.propiedad "
                            + "WHERE e.CATEGORIA = '" + categoria + "' "
                            + "AND p.NOMBRE ='" + propiedad + "' "
                            + "ORDER BY e.nombre";

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaEquipo = queryAUX.getResultList();

                } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO
                    titulo = tipo;
                    subtitulo = "Objetos";

                    queryEquipo = em.createNamedQuery("Equipo.findByTipo", Equipo.class);
                    queryEquipo.setParameter("tipo", tipo);
                    numpag = queryEquipo.getResultList().size();

                    queryEquipo = em.createNamedQuery("Equipo.findByTipo", Equipo.class);
                    queryEquipo.setParameter("tipo", tipo);
                    queryEquipo.setFirstResult(num);
                    queryEquipo.setMaxResults(15);
                    listaEquipo = queryEquipo.getResultList();

                } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//CAT
                    titulo = "Todos";
                    subtitulo = categoria;

                    queryEquipo = em.createNamedQuery("Equipo.findByCategoria", Equipo.class);
                    queryEquipo.setParameter("categoria", categoria);
                    numpag = queryEquipo.getResultList().size();

                    queryEquipo = em.createNamedQuery("Equipo.findByCategoria", Equipo.class);
                    queryEquipo.setParameter("categoria", categoria);
                    queryEquipo.setFirstResult(num);
                    queryEquipo.setMaxResults(15);
                    listaEquipo = queryEquipo.getResultList();

                } else if (tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//PRO
                    titulo = "Todos";
                    subtitulo = "Objetos";

                    sql = "SELECT e.* FROM Equipo e "
                            + "INNER JOIN Tienepropiedades tp on tp.equipo = e.id "
                            + "INNER JOIN Propiedades p on p.id = tp.propiedad "
                            + "WHERE p.NOMBRE ='" + propiedad + "' "
                            + "ORDER BY e.nombre";

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Equipo.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaEquipo = queryAUX.getResultList();

                } else {
                    titulo = "Todos";
                    subtitulo = "Objetos";

                    queryEquipo = em.createNamedQuery("Equipo.findAll", Equipo.class);
                    numpag = queryEquipo.getResultList().size();

                    queryEquipo = em.createNamedQuery("Equipo.findAll", Equipo.class);
                    queryEquipo.setFirstResult(num);
                    queryEquipo.setMaxResults(15);
                    listaEquipo = queryEquipo.getResultList();
                }

                request.setAttribute("pag", pag);
                request.setAttribute("numpag", (numpag / 15));

                request.setAttribute("vTipo", tipo);
                request.setAttribute("vCat", categoria);
                request.setAttribute("vPro", propiedad);

                listalistaPropiedades = new ArrayList();

                for (int i = 0; i < listaEquipo.size(); i++) {
                    listalistaPropiedades.add(listaEquipo.get(i).getPropiedadesList());
                }

                request.setAttribute("listalistaPropiedades", listalistaPropiedades);
                request.setAttribute("listaEquipo", listaEquipo);
                request.setAttribute("titulo", titulo);
                request.setAttribute("subtitulo", subtitulo);

                vista = "/WEB-INF/jsp/explorar/equipo.jsp";
                break;
            case "/estados":

                queryEstados = em.createNamedQuery("Estados.findAll", Estados.class);
                listaEstados = queryEstados.getResultList();

                request.setAttribute("listaEstados", listaEstados);

                vista = "/WEB-INF/jsp/explorar/estados.jsp";
                break;
            case "/hechizo":
                id = request.getParameter("idHechizo");

                queryHechizos = em.createNamedQuery("Hechizos.findById", Hechizos.class);
                queryHechizos.setParameter("id", id);
                Hechizo = queryHechizos.getSingleResult();

                request.setAttribute("hechizo", Hechizo);
                request.setAttribute("librodeHechizos", Hechizo.getClasesList());

                vista = "/WEB-INF/jsp/explorar/hechizo.jsp";
                break;
            case "/hechizos":

                numString = request.getParameter("pag");//numero de pag en la que estoy
                escuela = request.getParameter("escuela");
                nivel = request.getParameter("nivel");
                claseH = request.getParameter("clase");

                if (numString != null) {
                    pag = Integer.valueOf(numString);
                    num = (pag - 1) * 15;//offset
                } else {
                    pag = 1;
                    num = 0;
                }

                if (numString == null) {
                    subtitulo = "Todos";

                    queryHechizos = em.createNamedQuery("Hechizos.findAll", Hechizos.class);
                    numpag = queryHechizos.getResultList().size();
                    queryHechizos.setMaxResults(15);
                    listaHechizos = queryHechizos.getResultList();

                } else if (!escuela.equals("Escuela") && !nivel.equals("Nivel") && !claseH.equals("Clase")) {//TODOS
                    subtitulo = escuela;

                    sql = "SELECT h.* FROM Hechizos h "
                            + "INNER JOIN Listahechizos lb on lb.hechizo = h.id "
                            + "INNER JOIN Clases c on c.id = lb.clase "
                            + "WHERE h.ESCUELA = '" + escuela + "' "
                            + "AND h.NIVEL = '" + nivel + "' "
                            + "AND c.NOMBRE ='" + claseH + "' "
                            + "ORDER BY h.nombre";

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaHechizos = queryAUX.getResultList();

                } else if (!escuela.equals("Escuela") && !nivel.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL
                    subtitulo = escuela;

                    queryHechizos = em.createNamedQuery("Hechizos.findByEscuNivel", Hechizos.class);
                    queryHechizos.setParameter("escuela", escuela);
                    queryHechizos.setParameter("nivel", nivel);
                    numpag = queryHechizos.getResultList().size();

                    queryHechizos = em.createNamedQuery("Hechizos.findByEscuNivel", Hechizos.class);
                    queryHechizos.setParameter("escuela", escuela);
                    queryHechizos.setParameter("nivel", nivel);
                    queryHechizos.setFirstResult(num);
                    queryHechizos.setMaxResults(15);
                    listaHechizos = queryHechizos.getResultList();

                } else if (!escuela.equals("Escuela") && nivel.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE
                    subtitulo = claseH;

                    sql = "SELECT h.* FROM Hechizos h "
                            + "INNER JOIN Listahechizos lb on lb.hechizo = h.id "
                            + "INNER JOIN Clases c on c.id = lb.clase "
                            + "WHERE h.ESCUELA = '" + escuela + "' "
                            + "AND c.NOMBRE ='" + claseH + "' "
                            + "ORDER BY h.nombre";

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaHechizos = queryAUX.getResultList();

                } else if (escuela.equals("Escuela") && !nivel.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE
                    subtitulo = claseH;

                    sql = "SELECT h.* FROM Hechizos h "
                            + "INNER JOIN Listahechizos lb on lb.hechizo = h.id "
                            + "INNER JOIN Clases c on c.id = lb.clase "
                            + "WHERE h.NIVEL = '" + nivel + "' "
                            + "AND c.NOMBRE ='" + claseH + "' "
                            + "ORDER BY h.nombre";

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaHechizos = queryAUX.getResultList();

                } else if (!escuela.equals("Escuela") && nivel.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                    subtitulo = escuela;

                    queryHechizos = em.createNamedQuery("Hechizos.findByEscuela", Hechizos.class);
                    queryHechizos.setParameter("escuela", escuela);
                    numpag = queryHechizos.getResultList().size();

                    queryHechizos = em.createNamedQuery("Hechizos.findByEscuela", Hechizos.class);
                    queryHechizos.setParameter("escuela", escuela);
                    queryHechizos.setFirstResult(num);
                    queryHechizos.setMaxResults(15);
                    listaHechizos = queryHechizos.getResultList();

                } else if (escuela.equals("Escuela") && !nivel.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                    subtitulo = nivel;

                    queryHechizos = em.createNamedQuery("Hechizos.findByNivel", Hechizos.class);
                    queryHechizos.setParameter("nivel", nivel);
                    numpag = queryHechizos.getResultList().size();

                    queryHechizos = em.createNamedQuery("Hechizos.findByNivel", Hechizos.class);
                    queryHechizos.setParameter("nivel", nivel);
                    queryHechizos.setFirstResult(num);
                    queryHechizos.setMaxResults(15);
                    listaHechizos = queryHechizos.getResultList();

                } else if (escuela.equals("Escuela") && nivel.equals("Nivel") && !claseH.equals("Clase")) {//CLASE
                    subtitulo = claseH;

                    sql = "SELECT h.* FROM Hechizos h "
                            + "INNER JOIN Listahechizos lb on lb.hechizo = h.id "
                            + "INNER JOIN Clases c on c.id = lb.clase "
                            + "WHERE c.NOMBRE ='" + claseH + "' "
                            + "ORDER BY h.nombre";

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    numpag = queryAUX.getResultList().size();

                    queryAUX = em.createNativeQuery(sql, Hechizos.class);
                    queryAUX.setFirstResult(num);
                    queryAUX.setMaxResults(15);
                    listaHechizos = queryAUX.getResultList();

                } else {
                    subtitulo = "Todos";

                    queryHechizos = em.createNamedQuery("Hechizos.findAll", Hechizos.class);
                    numpag = queryHechizos.getResultList().size();

                    queryHechizos = em.createNamedQuery("Hechizos.findAll", Hechizos.class);
                    queryHechizos.setFirstResult(num);
                    queryHechizos.setMaxResults(15);
                    listaHechizos = queryHechizos.getResultList();

                    System.out.println(num);
                    System.out.println(numpag);
                }

                request.setAttribute("pag", pag);
                request.setAttribute("numpag", (numpag / 15));

                request.setAttribute("vEscu", escuela);
                request.setAttribute("vNiv", nivel);
                request.setAttribute("vClas", claseH);

                request.setAttribute("listaHechizos", listaHechizos);
                request.setAttribute("subtitulo", subtitulo);

                vista = "/WEB-INF/jsp/explorar/hechizos.jsp";
                break;
            case "/monstruo":
                id = request.getParameter("idMonstruo");

                queryMonstruos = em.createNamedQuery("Monstruos.findById", Monstruos.class);
                queryMonstruos.setParameter("id", id);
                Monstruo = queryMonstruos.getSingleResult();

                request.setAttribute("monstruo", Monstruo);
                request.setAttribute("habMonstruos", Monstruo.getCompetentemonstruoList());
                request.setAttribute("atrMonstruos", Monstruo.getTienemonstruoList());
                request.setAttribute("estMonstruos", Monstruo.getEstadosList());
                request.setAttribute("accMonstruos", Monstruo.getAccionesList());
                request.setAttribute("rasMonstruos", Monstruo.getRasgosList());

                vista = "/WEB-INF/jsp/explorar/monstruo.jsp";
                break;
            case "/monstruos":

                numString = request.getParameter("pag");//numero de pag en la que estoy
                tipo = request.getParameter("tipo");
                vd = request.getParameter("vd");

                if (numString != null) {
                    pag = Integer.valueOf(numString);
                    num = (pag - 1) * 15;//offset
                } else {
                    pag = 1;
                    num = 0;
                }

                if (numString == null) {
                    subtitulo = "Todos";

                    queryMonstruos = em.createNamedQuery("Monstruos.findAll", Monstruos.class);
                    numpag = queryMonstruos.getResultList().size();
                    queryMonstruos.setMaxResults(15);
                    listaMonstruos = queryMonstruos.getResultList();

                } else if (!tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//TODOS
                    subtitulo = tipo;

                    queryMonstruos = em.createNamedQuery("Monstruos.findByTipoVD", Monstruos.class);
                    queryMonstruos.setParameter("vdesafio", vd);
                    queryMonstruos.setParameter("tipo", tipo);
                    numpag = queryMonstruos.getResultList().size();

                    queryMonstruos = em.createNamedQuery("Monstruos.findByTipoVD", Monstruos.class);
                    queryMonstruos.setParameter("vdesafio", vd);
                    queryMonstruos.setParameter("tipo", tipo);
                    queryMonstruos.setFirstResult(num);
                    queryMonstruos.setMaxResults(15);
                    listaMonstruos = queryMonstruos.getResultList();

                } else if (tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//VD
                    subtitulo = "Todos";

                    queryMonstruos = em.createNamedQuery("Monstruos.findByVdesafio", Monstruos.class);
                    queryMonstruos.setParameter("vdesafio", vd);
                    numpag = queryMonstruos.getResultList().size();

                    queryMonstruos = em.createNamedQuery("Monstruos.findByVdesafio", Monstruos.class);
                    queryMonstruos.setParameter("vdesafio", vd);
                    queryMonstruos.setFirstResult(num);
                    queryMonstruos.setMaxResults(15);
                    listaMonstruos = queryMonstruos.getResultList();

                } else if (!tipo.equals("Tipo") && vd.equals("Valor de Desafio")) {//TIPO
                    subtitulo = tipo;

                    queryMonstruos = em.createNamedQuery("Monstruos.findByTipo", Monstruos.class);
                    queryMonstruos.setParameter("tipo", tipo);
                    numpag = queryMonstruos.getResultList().size();

                    queryMonstruos = em.createNamedQuery("Monstruos.findByTipo", Monstruos.class);
                    queryMonstruos.setParameter("tipo", tipo);
                    queryMonstruos.setFirstResult(num);
                    queryMonstruos.setMaxResults(15);
                    listaMonstruos = queryMonstruos.getResultList();

                } else {
                    subtitulo = "Todos";

                    queryMonstruos = em.createNamedQuery("Monstruos.findAll", Monstruos.class);
                    numpag = queryMonstruos.getResultList().size();

                    queryMonstruos = em.createNamedQuery("Monstruos.findAll", Monstruos.class);
                    queryMonstruos.setFirstResult(num);
                    queryMonstruos.setMaxResults(15);
                    listaMonstruos = queryMonstruos.getResultList();

                    System.out.println(num);
                    System.out.println(numpag);
                }

                request.setAttribute("pag", pag);
                request.setAttribute("numpag", (numpag / 15));

                request.setAttribute("vTipo", tipo);
                request.setAttribute("vVD", vd);

                request.setAttribute("listaMonstruos", listaMonstruos);
                request.setAttribute("subtitulo", subtitulo);

                vista = "/WEB-INF/jsp/explorar/monstruos.jsp";
                break;
            case "/razas":

                //Razas Normales
                queryRazas = em.createNamedQuery("Razas.findByTipo", Razas.class);
                queryRazas.setParameter("tipo", "Normal");
                listaRazas = queryRazas.getResultList();

                listaRazasImagenes = new ArrayList();

                for (int i = 0; i < listaRazas.size(); i++) {

                    listaRazasImagenes.add("/TFG/img/razas/" + listaRazas.get(i).getNombre().toLowerCase() + ".jpg");

                }

                request.setAttribute("listaRazasNormales", listaRazas);
                request.setAttribute("listaRazasImagenesNormales", listaRazasImagenes);

                //Razas Monstruos
                queryRazas = em.createNamedQuery("Razas.findByTipo", Razas.class);
                queryRazas.setParameter("tipo", "Monstruo");
                listaRazas = queryRazas.getResultList();

                listaRazasImagenes = new ArrayList();

                for (int i = 0; i < listaRazas.size(); i++) {

                    listaRazasImagenes.add("/TFG/img/razas/" + listaRazas.get(i).getNombre().toLowerCase() + ".jpg");

                }

                request.setAttribute("listaRazasMonstruosas", listaRazas);
                request.setAttribute("listaRazasImagenesMonstruosas", listaRazasImagenes);

                vista = "/WEB-INF/jsp/explorar/razas.jsp";
                break;
            case "/trasfondo":
                id = request.getParameter("idTrasfondo");

                queryTrasfondos = em.createNamedQuery("Trasfondos.findById", Trasfondos.class);
                queryTrasfondos.setParameter("id", id);
                Trasfondo = queryTrasfondos.getSingleResult();

                request.setAttribute("trasfondo", Trasfondo);
                request.setAttribute("habtrasfondo", Trasfondo.getHabilidadesList());
                request.setAttribute("eletrasfondo", Trasfondo.getHabilidadesList1());

                request.setAttribute("listaRasgosTrasfondos", Trasfondo.getRasgosList());

                vista = "/WEB-INF/jsp/explorar/trasfondo.jsp";
                break;
            case "/trasfondos":

                queryTrasfondos = em.createNamedQuery("Trasfondos.findAll", Trasfondos.class);
                listaTrasfondos = queryTrasfondos.getResultList();

                request.setAttribute("listaTrasfondo", listaTrasfondos);

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

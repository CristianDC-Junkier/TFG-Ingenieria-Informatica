package controlador;

import entidades.Alcance;
import entidades.Clase;
import entidades.Dote;
import entidades.Equipo;
import entidades.Estado;
import entidades.Hechizo;
import entidades.Mejoradote;
import entidades.Monstruo;
import entidades.Propiedad;
import entidades.Rasgo;
import entidades.Raza;
import entidades.Requisitosdote;
import entidades.Subclase;
import entidades.Subraza;
import entidades.Tablaclases;
import entidades.Tablaclasepornivel;
import entidades.Trasfondo;
import entidades.Usaclase;
import entidades.Usasubclase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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

        Object result;

        Clase Clase;
        Subclase SubClase;
        Trasfondo Trasfondo;
        Raza Raza;
        Subraza SubRaza;
        Hechizo Hechizo;
        Monstruo Monstruo;
        Equipo Equipo;
        Alcance Alcance;
        
        Query queryAUX;
        
        TypedQuery<Estado> queryEstados;
        TypedQuery<Clase> queryClases;
        TypedQuery<Subclase> querySubClases;
        TypedQuery<Dote> queryDotes;
        TypedQuery<Mejoradote> queryMDotes;
        TypedQuery<Requisitosdote> queryRDotes;
        TypedQuery<Equipo> queryEquipo;
        TypedQuery<Alcance> queryAlcance;
        TypedQuery<Hechizo> queryHechizos;
        TypedQuery<Monstruo> queryMonstruos;
        TypedQuery<Raza> queryRazas;
        TypedQuery<Subraza> querySubRazas;
        TypedQuery<Trasfondo> queryTrasfondos;
        TypedQuery<Tablaclasepornivel> queryTablaClaseNivel;

        List<Estado> listaEstados;
        List<Clase> listaClases;
        List<Dote> listaDotes;
        List<Mejoradote> listaMDotes;
        List<Requisitosdote> listaRDotes;
        List<Equipo> listaEquipo;
        List<List<Propiedad>> listalistaPropiedades;
        List<Hechizo> listaHechizos;
        List<Monstruo> listaMonstruos;
        List<Raza> listaRazas;
        List<Subraza> listaSubRazas;
        List<String> listaRazasImagenes;
        List<Trasfondo> listaTrasfondos;
        List<Tablaclasepornivel> listaTablaClaseNivel;
        List<Tablaclases> listaTablaClases;
        List<Rasgo> listaRasgos;
        List<Usasubclase> listaUsaSubClases;
        List<Usaclase> listaUsaClases;


        String sql;
        String id;
        String nombre;
        String resultado;

        String titulo;
        String subtitulo;

        String tipo;
        String categoria;
        String propiedad;
        String tipoSQL;
        String categoriaSQL;
        String propiedadSQL;

        String escuela;
        String nivel;
        String claseH;
        String escuelaSQL;
        String nivelSQL;
        String claseHSQL;

        String vd;
        String vdSQL;

        String Trucos;
        String ClaseValoEspecifico1;
        String ClaseValoEspecifico2;
        String ClaseValoEspecifico3;
        String ClaseValoEspecifico4;

        String numString;
        int num;
        int numpag;

        HashSet<Hechizo> hashAuxHechizos;
        HashSet<Equipo> hashAuxEquipo;
        HashSet<Monstruo> hashAuxMonstruos;

        switch (accion) {
            case "/clases":

                queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
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
                            + ">" + "<a href=\"/TFG/Explorar/clase?clase=" + listaClases.get(i).getId() + "\">" + listaClases.get(i).getNombre() + " </a></h5> "
                            + "<li>" + "<a href=\"/TFG/Explorar/clase?clase=" + listaClases.get(i).getId() + "\"> "
                            + "<img src=\"/TFG/img/clases/" + listaClases.get(i).getNombre().replaceAll("\\s", "") + ".jfif\"/> </a> "
                            + "<p>" + listaClases.get(i).getDescripcion()
                            + "</p> </li> </div> </ul>";
                }

                request.setAttribute("listaClases", resultado);

                vista = "/WEB-INF/jsp/explorar/clases.jsp";
                break;
            case "/clase":

                nombre = request.getParameter("clase");

                queryClases = em.createNamedQuery("Clase.findById", Clase.class);
                queryClases.setParameter("id", nombre);
                Clase = queryClases.getSingleResult();

                queryTablaClaseNivel = em.createNamedQuery("Tablaclasepornivel.findByClase", Tablaclasepornivel.class);
                queryTablaClaseNivel.setParameter("clase", Clase.getId());
                listaTablaClaseNivel = queryTablaClaseNivel.getResultList();

                listaTablaClases = new ArrayList();

                //Recogemos todas las clases por nivel
                for (int i = 0; i < listaTablaClaseNivel.size(); i++) {
                    listaTablaClases.add(listaTablaClaseNivel.get(i).getTablaclases());
                }

                listaUsaClases = Clase.getUsaclaseList();
                listaRasgos = new ArrayList();

                //Añadimos todos los rasgos a la lista de rasgos general
                for (int i = 0; i < listaUsaClases.size(); i++) {
                    listaRasgos.add(listaUsaClases.get(i).getRasgos());
                }

                //TablaClase = listaTablaClaseNivel.get(listaTablaClaseNivel.size()).getTablaclases();
                resultado = "<table class=\"tablaHechizos\">"
                        + "<thead>"
                        + "<tr>";

                switch (nombre) {
                    case "Artificiero":
                        Trucos = "2";
                        ClaseValoEspecifico1 = "-";
                        ClaseValoEspecifico2 = "-";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Infusiones</th>"
                                + "<th>Objetos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico1 = "4";
                                    ClaseValoEspecifico2 = "2";
                                    break;
                                case 5:
                                    ClaseValoEspecifico1 = "6";
                                    ClaseValoEspecifico2 = "3";
                                    break;
                                case 9:
                                    Trucos = "3";
                                    ClaseValoEspecifico1 = "8";
                                    ClaseValoEspecifico2 = "4";
                                    break;
                                case 13:
                                    Trucos = "4";
                                    ClaseValoEspecifico1 = "10";
                                    ClaseValoEspecifico2 = "5";
                                    break;
                                case 17:
                                    ClaseValoEspecifico1 = "12";
                                    ClaseValoEspecifico2 = "6";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Bardo":
                        Trucos = "2";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 3:
                                    Trucos = "3";
                                    break;
                                case 9:
                                    Trucos = "4";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Brujo":
                        Trucos = "-";
                        ClaseValoEspecifico1 = "2";
                        ClaseValoEspecifico2 = "1";
                        ClaseValoEspecifico3 = "1";
                        ClaseValoEspecifico4 = "-";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
                                + "<th>Hechizos Conocidos</th>"
                                + "<th>Espacio Hechizos</th>"
                                + "<th>Nivel de Espacio</th>"
                                + "<th>Invocaciones Conocidas</th>"
                                + "</tr>"
                                + "</thead>"
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico1 = "3";
                                    ClaseValoEspecifico2 = "2";
                                    ClaseValoEspecifico4 = "2";
                                    break;
                                case 2:
                                    ClaseValoEspecifico1 = "4";
                                    ClaseValoEspecifico3 = "2";
                                    break;
                                case 3:
                                    Trucos = "3";
                                    ClaseValoEspecifico1 = "5";
                                    break;
                                case 4:
                                    ClaseValoEspecifico1 = "6";
                                    ClaseValoEspecifico3 = "3";
                                    ClaseValoEspecifico4 = "3";
                                    break;
                                case 5:
                                    ClaseValoEspecifico1 = "7";
                                    break;
                                case 6:
                                    ClaseValoEspecifico1 = "8";
                                    ClaseValoEspecifico3 = "4";
                                    ClaseValoEspecifico4 = "4";
                                    break;
                                case 7:
                                    ClaseValoEspecifico1 = "9";
                                    break;
                                case 8:
                                    ClaseValoEspecifico1 = "10";
                                    ClaseValoEspecifico3 = "5";
                                    ClaseValoEspecifico4 = "5";
                                    break;
                                case 9:
                                    Trucos = "4";
                                    break;
                                case 10:
                                    ClaseValoEspecifico1 = "11";
                                    ClaseValoEspecifico2 = "3";
                                    break;
                                case 11:
                                    ClaseValoEspecifico4 = "6";
                                    break;
                                case 12:
                                    ClaseValoEspecifico1 = "12";
                                    break;
                                case 14:
                                    ClaseValoEspecifico1 = "13";
                                    ClaseValoEspecifico4 = "7";
                                    break;
                                case 15:
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "14";
                                    ClaseValoEspecifico2 = "4";
                                    break;
                                case 17:
                                    ClaseValoEspecifico4 = "8";
                                    break;
                                case 18:
                                    ClaseValoEspecifico1 = "15";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "<td>" + ClaseValoEspecifico3 + "</td>"
                                    + "<td>" + ClaseValoEspecifico4 + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Bárbaro":
                        Trucos = "-";
                        ClaseValoEspecifico1 = "2";
                        ClaseValoEspecifico2 = "+2";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Furias</th>"
                                + "<th>Daño Furia</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 2:
                                    ClaseValoEspecifico1 = "3";
                                    break;
                                case 5:
                                    ClaseValoEspecifico1 = "4";
                                    break;
                                case 8:
                                    ClaseValoEspecifico2 = "+3";
                                    break;
                                case 11:
                                    ClaseValoEspecifico1 = "5";
                                    break;
                                case 15:
                                    ClaseValoEspecifico2 = "+4";
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "6";
                                    break;
                                case 19:
                                    ClaseValoEspecifico1 = "Infinita";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Caballero de la Muerte":
                        ClaseValoEspecifico1 = "1";
                        ClaseValoEspecifico2 = "1";
                        ClaseValoEspecifico3 = "100";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Runa Sangre</th>"
                                + "<th>Runa Escarcha</th>"
                                + "<th>Runa Profano</th>"
                                + "<th>Runa Subclase</th>"
                                + "<th>Poder Rúnico</th>"
                                + "</tr>"
                                + "</thead>"
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 4:
                                    ClaseValoEspecifico2 = "2";
                                    break;
                                case 6:
                                    ClaseValoEspecifico3 = "125";
                                    break;
                                case 7:
                                    ClaseValoEspecifico2 = "3";
                                    break;
                                case 9:
                                    ClaseValoEspecifico1 = "2";
                                    break;
                                case 10:
                                    ClaseValoEspecifico3 = "150";
                                    break;
                                case 11:
                                    ClaseValoEspecifico2 = "4";
                                    break;
                                case 14:
                                    ClaseValoEspecifico3 = "175";
                                    break;
                                case 18:
                                    ClaseValoEspecifico3 = "200";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "<td>" + ClaseValoEspecifico3 + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Cazador de Sangre":
                        ClaseValoEspecifico1 = "1d4";
                        ClaseValoEspecifico2 = "-";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Daño del Rito Carmesí</th>"
                                + "<th>Maldiciones conocidas</th>"
                                + "</tr>"
                                + "</thead>"
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico2 = "1";
                                    break;
                                case 4:
                                    ClaseValoEspecifico1 = "1d6";
                                    ClaseValoEspecifico2 = "2";
                                    break;
                                case 8:
                                    ClaseValoEspecifico2 = "3";
                                    break;
                                case 10:
                                    ClaseValoEspecifico1 = "1d8";
                                    break;
                                case 12:
                                    ClaseValoEspecifico2 = "4";
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "1d10";
                                    ClaseValoEspecifico2 = "5";
                                    break;
                                case 19:
                                    ClaseValoEspecifico2 = "6";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Clérigo":
                        Trucos = "3";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 3:
                                    Trucos = "4";
                                    break;
                                case 9:
                                    Trucos = "5";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Druida":
                        Trucos = "2";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 3:
                                    Trucos = "3";
                                    break;
                                case 9:
                                    Trucos = "4";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Explorador":
                        ClaseValoEspecifico1 = "-";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Hechizos conocidos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico1 = "2";
                                    break;
                                case 2:
                                    ClaseValoEspecifico1 = "3";
                                    break;
                                case 4:
                                    ClaseValoEspecifico1 = "4";
                                    break;
                                case 6:
                                    ClaseValoEspecifico1 = "5";
                                    break;
                                case 8:
                                    ClaseValoEspecifico1 = "6";
                                    break;
                                case 10:
                                    ClaseValoEspecifico1 = "7";
                                    break;
                                case 12:
                                    ClaseValoEspecifico1 = "8";
                                    break;
                                case 14:
                                    ClaseValoEspecifico1 = "9";
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "10";
                                    break;
                                case 18:
                                    ClaseValoEspecifico1 = "11";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Hechicero":
                        Trucos = "4";
                        ClaseValoEspecifico1 = "2";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
                                + "<th>Hechizos conocidos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico1 = "3";
                                    break;
                                case 2:
                                    ClaseValoEspecifico1 = "4";
                                    break;
                                case 3:
                                    Trucos = "5";
                                    ClaseValoEspecifico1 = "5";
                                    break;
                                case 4:
                                    ClaseValoEspecifico1 = "6";
                                    break;
                                case 5:
                                    ClaseValoEspecifico1 = "7";
                                    break;
                                case 6:
                                    ClaseValoEspecifico1 = "8";
                                    break;
                                case 7:
                                    ClaseValoEspecifico1 = "9";
                                    break;
                                case 8:
                                    ClaseValoEspecifico1 = "10";
                                    break;
                                case 9:
                                    Trucos = "6";
                                    ClaseValoEspecifico1 = "11";
                                    break;
                                case 10:
                                    Trucos = "6";
                                    ClaseValoEspecifico1 = "12";
                                    break;
                                case 12:
                                    Trucos = "6";
                                    ClaseValoEspecifico1 = "13";
                                    break;
                                case 14:
                                    Trucos = "6";
                                    ClaseValoEspecifico1 = "14";
                                    break;
                                case 16:
                                    Trucos = "6";
                                    ClaseValoEspecifico1 = "15";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Luchador":

                        resultado = resultado
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Mago":
                        Trucos = "3";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";
                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 3:
                                    Trucos = "4";
                                    break;
                                case 9:
                                    Trucos = "5";
                                    break;
                            }
                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + Trucos + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Monje":
                        ClaseValoEspecifico1 = "1d4";
                        ClaseValoEspecifico2 = "-";
                        ClaseValoEspecifico3 = "-";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Artes Marciales</th>"
                                + "<th>Puntos Ki</th>"
                                + "<th>Mov sin Armadura</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            ClaseValoEspecifico2 = String.valueOf(i + 1);

                            switch (i) {
                                case 1:
                                    ClaseValoEspecifico3 = "+10 Pies (3m)";
                                    break;
                                case 3:
                                    ClaseValoEspecifico1 = "1d6";
                                    break;
                                case 5:
                                    ClaseValoEspecifico3 = "+15 Pies (5m)";
                                    break;
                                case 9:
                                    ClaseValoEspecifico3 = "+20 Pies (6m)";
                                    break;
                                case 10:
                                    ClaseValoEspecifico1 = "1d8";
                                    break;
                                case 13:
                                    ClaseValoEspecifico3 = "+25 Pies (6m)";
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "1d10";
                                    break;
                                case 17:
                                    ClaseValoEspecifico3 = "+30 Pies (10m)";
                                    break;

                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getBc() + */ "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + ClaseValoEspecifico2 + "</td>"
                                    + "<td>" + ClaseValoEspecifico3 + "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + */ "</td>"
                                    + "<td>" + /*listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + */ "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Paladin":

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Trucos</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                    case "Pícaro":

                        ClaseValoEspecifico1 = "1d6";

                        resultado = resultado
                                + "<th>Nivel</th>"
                                + "<th>BC</th>"
                                + "<th>Ataque Furtivo</th>"
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
                                + "<tbody>";

                        for (int i = 0; i < 20; i++) {

                            switch (i) {
                                case 2:
                                    ClaseValoEspecifico1 = "2d6";
                                    break;
                                case 4:
                                    ClaseValoEspecifico1 = "3d6";
                                    break;
                                case 6:
                                    ClaseValoEspecifico1 = "4d6";
                                    break;
                                case 8:
                                    ClaseValoEspecifico1 = "5d6";
                                    break;
                                case 10:
                                    ClaseValoEspecifico1 = "6d6";
                                    break;
                                case 12:
                                    ClaseValoEspecifico1 = "7d6";
                                    break;
                                case 14:
                                    ClaseValoEspecifico1 = "8d6";
                                    break;
                                case 16:
                                    ClaseValoEspecifico1 = "9d6";
                                    break;
                                case 18:
                                    ClaseValoEspecifico1 = "10d6";
                                    break;
                            }

                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getBc() + "</td>"
                                    + "<td>" + ClaseValoEspecifico1 + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv1() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv2() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv3() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv4() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv5() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv6() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv7() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv8() + "</td>"
                                    + "<td>" + listaTablaClases.get(i).getEspacioshechizosList().get(0).getNv9() + "</td>"
                                    + "</tr>";
                        }
                        break;
                }
                resultado = resultado
                        + "</tbody>"
                        + "</table>";

                request.setAttribute("tablaHechizos", resultado);

                resultado = Clase.getEquipoinicial().replace("•", "<br>•");
                resultado = resultado.replaceFirst("<br>", "");

                request.setAttribute("equipoinicial", resultado);
                request.setAttribute("listahabilidades", Clase.getHabilidadesList());
                request.setAttribute("listaatributos", Clase.getAtributosList());
                request.setAttribute("listarasgos", listaRasgos);
                request.setAttribute("listaSubclases", Clase.getSubclasesList());

                request.setAttribute("imagen", Clase.getNombre().replaceAll("\\s", ""));
                request.setAttribute("clase", Clase);

                vista = "/WEB-INF/jsp/explorar/clase.jsp";
                break;
            case "/subclase":

                nombre = request.getParameter("nombreSubClase");
                id = request.getParameter("idClase");

                //Recogemos la clase
                queryClases = em.createNamedQuery("Clase.findById", Clase.class);
                queryClases.setParameter("id", id);
                Clase = queryClases.getSingleResult();

                //Recogemos la subclase
                querySubClases = em.createNamedQuery("Subclase.findByNombre", Subclase.class);
                querySubClases.setParameter("nombre", nombre);
                SubClase = querySubClases.getSingleResult();

                listaUsaSubClases = SubClase.getUsasubclaseList();
                listaRasgos = new ArrayList();

                //Añadimos todos los rasgos a la lista de rasgos general
                for (int i = 0; i < listaUsaSubClases.size(); i++) {
                    listaRasgos.add(listaUsaSubClases.get(i).getRasgos());
                }

                request.setAttribute("listarasgos", listaRasgos);
                request.setAttribute("imagen", Clase.getNombre().replaceAll("\\s", ""));
                request.setAttribute("clase", Clase);
                request.setAttribute("subclase", SubClase);

                vista = "/WEB-INF/jsp/explorar/subclase.jsp";
                break;
            case "/dotes":

                queryDotes = em.createNamedQuery("Dote.findAll", Dote.class);
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

                    queryRDotes = em.createNamedQuery("Requisitodote.findByNombre", Requisitosdote.class);
                    queryRDotes.setParameter("nombre", nombre);
                    listaRDotes = queryRDotes.getResultList();

                    for (Requisitosdote listaRDote : listaRDotes) {
                        resultado
                                = resultado
                                + "<li class=\"RequisitoDote\">" + listaRDote.getValor() + "</li>";
                    }

                    queryMDotes = em.createNamedQuery("Mejoradote.findByNombre", Mejoradote.class);
                    queryMDotes.setParameter("nombre", nombre);
                    listaMDotes = queryMDotes.getResultList();

                    for (Mejoradote listaMDote : listaMDotes) {
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

                //Comprobamos los datos
                if (numString == null) {

                    tipoSQL = " ";
                    categoriaSQL = " ";
                    propiedadSQL = " ";
                    numString = "1";
                    num = 0;

                    titulo = "Todos";
                    subtitulo = "Objetos";

                } else {
                    num = (Integer.valueOf(numString) - 1) * 15;//offset

                    if (tipo.equals("Tipo")) {
                        tipoSQL = " ";
                    } else {
                        tipoSQL = "AND e.TIPO = '" + tipo + "' ";
                    }
                    if (categoria.equals("Categoria")) {
                        categoriaSQL = " ";
                    } else {
                        categoriaSQL = "AND e.CATEGORIA = '" + categoria + "' ";
                    }
                    if (propiedad.equals("Propiedad")) {
                        propiedadSQL = " ";
                    } else {
                        propiedadSQL = "AND p.NOMBRE = '" + propiedad + "' ";
                    }

                    if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TODOS
                        titulo = tipo;
                        subtitulo = categoria;
                    } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO y CAT
                        titulo = tipo;
                        subtitulo = categoria;
                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TIPO y PROP
                        titulo = tipo;
                        subtitulo = "Objetos";
                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//CAT y PROP
                        titulo = "Todos";
                        subtitulo = categoria;
                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO
                        titulo = tipo;
                        subtitulo = "Objetos";
                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//CAT
                        titulo = "Todos";
                        subtitulo = categoria;
                    } else if (tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//PRO
                        titulo = "Todos";
                        subtitulo = "Objetos";
                    } else {
                        titulo = "Todos";
                        subtitulo = "Objetos";
                    }

                }

                /////////////////////////////////
                ////////NUMERO DE EQUIPO/////////
                /////////////////////////////////
                sql = "SELECT COUNT(DISTINCT e.id) FROM Equipo e "
                        + "LEFT JOIN Tienepropiedad tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedad p on p.id = tp.propiedad "
                        + "WHERE e.id IS NOT NULL "
                        + tipoSQL
                        + categoriaSQL
                        + propiedadSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (15 OBJETOS POR PAGINA)
                numpag = (((Number) result).intValue() / 14) + 1;

                sql = "SELECT * FROM Equipo e "
                        + "LEFT JOIN Tienepropiedad tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedad p on p.id = tp.propiedad "
                        + "WHERE e.id IS NOT NULL "
                        + tipoSQL
                        + categoriaSQL
                        + propiedadSQL
                        + "ORDER BY e.nombre "
                        + "OFFSET " + num + " ROWS FETCH NEXT 15 ROWS ONLY";

                queryAUX = em.createNativeQuery(sql, Equipo.class);
                listaEquipo = queryAUX.getResultList();

                //Eliminar duplicados utilizando HashSet
                hashAuxEquipo = new HashSet<>(listaEquipo);
                hashAuxEquipo.clear(); //Limpiar el ArrayList
                listaEquipo.addAll(hashAuxEquipo);

                request.setAttribute("pag", numString);
                request.setAttribute("numpag", numpag);
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

                queryEstados = em.createNamedQuery("Estado.findAll", Estado.class);
                listaEstados = queryEstados.getResultList();

                request.setAttribute("listaEstados", listaEstados);

                vista = "/WEB-INF/jsp/explorar/estados.jsp";
                break;
            case "/hechizo":
                id = request.getParameter("idHechizo");

                queryHechizos = em.createNamedQuery("Hechizo.findById", Hechizo.class);
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

                //Comprobamos los datos
                if (numString == null) {

                    escuelaSQL = " ";
                    nivelSQL = " ";
                    claseHSQL = " ";
                    numString = "1";
                    num = 0;

                    subtitulo = "Todos";

                } else {

                    num = (Integer.valueOf(numString) - 1) * 15;//offset

                    if (escuela.equals("Escuela")) {
                        escuelaSQL = " ";
                    } else {
                        escuelaSQL = "AND h.ESCUELA = '" + escuela + "' ";
                    }
                    if (nivel.equals("Nivel")) {
                        nivelSQL = " ";
                    } else {
                        nivelSQL = "AND h.NIVEL = '" + nivel + "' ";
                    }
                    if (claseH.equals("Clase")) {
                        claseHSQL = " ";
                    } else {
                        claseHSQL = "AND c.NOMBRE = '" + claseH + "' ";
                    }

                    if (!escuela.equals("Escuela") && !nivel.equals("Nivel") && !claseH.equals("Clase")) {//TODOS
                        subtitulo = escuela;
                    } else if (!escuela.equals("Escuela") && !nivel.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL
                        subtitulo = escuela;
                    } else if (!escuela.equals("Escuela") && nivel.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE
                        subtitulo = claseH;
                    } else if (escuela.equals("Escuela") && !nivel.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE
                        subtitulo = claseH;
                    } else if (!escuela.equals("Escuela") && nivel.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                        subtitulo = escuela;
                    } else if (escuela.equals("Escuela") && !nivel.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                        subtitulo = nivel;
                    } else if (escuela.equals("Escuela") && nivel.equals("Nivel") && !claseH.equals("Clase")) {//CLASE
                        subtitulo = claseH;
                    } else {
                        subtitulo = "Todos";
                    }

                }

                /////////////////////////////////
                ////////NUMERO DE HECHIZOS///////
                /////////////////////////////////
                sql = "SELECT COUNT(DISTINCT h.id) FROM Hechizo h "
                        + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clase c on c.id = lb.clase "
                        + "WHERE h.id IS NOT NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (15 HECHIZOS POR PAGINA)
                numpag = (((Number) result).intValue() / 14) + 1;

                sql = "SELECT h.* FROM Hechizo h "
                        + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clase c on c.id = lb.clase "
                        + "WHERE h.id IS NOT NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL
                        + "ORDER BY h.nombre "
                        + "OFFSET " + num + " ROWS FETCH NEXT 15 ROWS ONLY";

                queryAUX = em.createNativeQuery(sql, Hechizo.class);
                listaHechizos = queryAUX.getResultList();

                //Eliminar duplicados utilizando HashSet
                hashAuxHechizos = new HashSet<>(listaHechizos);
                listaHechizos.clear(); //Limpiar el ArrayList
                listaHechizos.addAll(hashAuxHechizos);

                request.setAttribute("pag", numString);
                request.setAttribute("numpag", numpag);

                request.setAttribute("vEscu", escuela);
                request.setAttribute("vNiv", nivel);
                request.setAttribute("vClas", claseH);

                request.setAttribute("listaHechizos", listaHechizos);
                request.setAttribute("subtitulo", subtitulo);

                vista = "/WEB-INF/jsp/explorar/hechizos.jsp";
                break;
            case "/monstruo":
                id = request.getParameter("idMonstruo");

                queryMonstruos = em.createNamedQuery("Monstruo.findById", Monstruo.class);
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

                //Comprobamos los datos
                if (numString == null) {

                    tipoSQL = " ";
                    vdSQL = " ";
                    numString = "1";
                    num = 0;

                    subtitulo = "Todos";

                } else {
                    num = (Integer.valueOf(numString) - 1) * 15;//offset

                    if (vd.equals("Valor de Desafio")) {
                        vdSQL = " ";
                    } else {
                        vdSQL = "AND m.VDESAFIO = '" + vd + "' ";
                    }
                    if (tipo.equals("Tipo")) {
                        tipoSQL = " ";
                    } else {
                        tipoSQL = "AND m.TIPO = '" + tipo + "' ";
                    }

                    if (!tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//TODOS
                        subtitulo = tipo;
                    } else if (tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//VD
                        subtitulo = "Todos";
                    } else if (!tipo.equals("Tipo") && vd.equals("Valor de Desafio")) {//TIPO
                        subtitulo = tipo;
                    } else {
                        subtitulo = "Todos";
                    }
                }

                //////////////////////////////////
                ////////NUMERO DE MONSTRUOS///////
                //////////////////////////////////
                sql = "SELECT COUNT(DISTINCT m.id) FROM Monstruo m "
                        + "WHERE m.id IS NOT NULL "
                        + vdSQL
                        + tipoSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (15 MONSTRUOS POR PAGINA)
                numpag = (((Number) result).intValue() / 14) + 1;

                sql = "SELECT m.* FROM Monstruo m "
                        + "WHERE m.id IS NOT NULL "
                        + vdSQL
                        + tipoSQL
                        + "ORDER BY m.nombre "
                        + "OFFSET " + num + " ROWS FETCH NEXT 15 ROWS ONLY";

                queryAUX = em.createNativeQuery(sql, Monstruo.class);
                listaMonstruos = queryAUX.getResultList();

                //Eliminar duplicados utilizando HashSet
                hashAuxMonstruos = new HashSet<>(listaMonstruos);
                listaMonstruos.clear(); //Limpiar el ArrayList
                listaMonstruos.addAll(hashAuxMonstruos);

                request.setAttribute("pag", numString);
                request.setAttribute("numpag", numpag);

                request.setAttribute("vTipo", tipo);
                request.setAttribute("vVD", vd);

                request.setAttribute("listaMonstruos", listaMonstruos);
                request.setAttribute("subtitulo", subtitulo);

                vista = "/WEB-INF/jsp/explorar/monstruos.jsp";
                break;
            case "/raza":

                id = request.getParameter("idRaza");

                queryRazas = em.createNamedQuery("Raza.findById", Raza.class);
                queryRazas.setParameter("id", id);
                Raza = queryRazas.getSingleResult();

                //Subraza principal
                querySubRazas = em.createNamedQuery("Subraza.findByNombre", Subraza.class);
                querySubRazas.setParameter("nombre", Raza.getNombre());
                SubRaza = querySubRazas.getSingleResult();

                //Subrazas que no son la principal
                listaSubRazas = Raza.getSubrazasList();
                listaSubRazas.remove(SubRaza);

                request.setAttribute("raza", Raza);
                request.setAttribute("razaRasgos", Raza.getRasgosList());
                request.setAttribute("razaAtributos", SubRaza.getSumarazaList());
                request.setAttribute("razaEAtributos", SubRaza.getEligeatr());
                request.setAttribute("imagenRaza", "/TFG/img/razas/" + Raza.getNombre().toLowerCase() + ".jpg");
                request.setAttribute("razaExtra", SubRaza);
                request.setAttribute("listaSubRazas", listaSubRazas);

                vista = "/WEB-INF/jsp/explorar/raza.jsp";
                break;
            case "/subraza":

                id = request.getParameter("idRaza");
                nombre = request.getParameter("nombreSubRaza");

                queryRazas = em.createNamedQuery("Raza.findById", Raza.class);
                queryRazas.setParameter("id", id);
                Raza = queryRazas.getSingleResult();

                //Subraza
                querySubRazas = em.createNamedQuery("Subraza.findByNombre", Subraza.class);
                querySubRazas.setParameter("nombre", nombre);
                SubRaza = querySubRazas.getSingleResult();

                request.setAttribute("idRaza", id);
                request.setAttribute("subraza", SubRaza);
                request.setAttribute("subrazaRasgos", SubRaza.getRasgosList());
                request.setAttribute("subrazaAtributos", SubRaza.getSumarazaList());
                request.setAttribute("razaEAtributos", SubRaza.getEligeatr());
                request.setAttribute("imagenRaza", "/TFG/img/razas/" + Raza.getNombre().toLowerCase() + ".jpg");

                vista = "/WEB-INF/jsp/explorar/subraza.jsp";
                break;
            case "/razas":

                //Razas Normales
                queryRazas = em.createNamedQuery("Raza.findByTipo", Raza.class);
                queryRazas.setParameter("tipo", "Normal");
                listaRazas = queryRazas.getResultList();

                listaRazasImagenes = new ArrayList();

                for (int i = 0; i < listaRazas.size(); i++) {

                    listaRazasImagenes.add("/TFG/img/razas/" + listaRazas.get(i).getNombre().toLowerCase() + ".jpg");

                }

                request.setAttribute("listaRazasNormales", listaRazas);
                request.setAttribute("listaRazasImagenesNormales", listaRazasImagenes);

                //Razas Monstruo
                queryRazas = em.createNamedQuery("Raza.findByTipo", Raza.class);
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

                queryTrasfondos = em.createNamedQuery("Trasfondo.findById", Trasfondo.class);
                queryTrasfondos.setParameter("id", id);
                Trasfondo = queryTrasfondos.getSingleResult();

                request.setAttribute("trasfondo", Trasfondo);
                request.setAttribute("habtrasfondo", Trasfondo.getHabilidadesList());
                request.setAttribute("eletrasfondo", Trasfondo.getHabilidadesList1());

                request.setAttribute("listaRasgosTrasfondos", Trasfondo.getRasgosList());

                vista = "/WEB-INF/jsp/explorar/trasfondo.jsp";
                break;
            case "/trasfondos":

                queryTrasfondos = em.createNamedQuery("Trasfondo.findAll", Trasfondo.class);
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

package controlador;

import entidades.Atributo;
import entidades.Clase;
import entidades.Dote;
import entidades.Habilidad;
import entidades.Mesa;
import entidades.Personajeatributo;
import entidades.Personajehabilidad;
import entidades.Personaje;
import entidades.Raza;
import entidades.Seccion;
import entidades.Tablaclasepornivel;
import entidades.Tema;
import entidades.Trasfondo;
import entidades.Usaclase;
import entidades.Usasubclase;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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

        request.setCharacterEncoding("UTF-8");

        HttpSession session;
        Usuario user;
        
        Mesa mesa;
        Personaje personaje;
        Tablaclasepornivel tcnivel;
        Personajehabilidad personajeHabilidad;
        Habilidad habilidad;
        Personajeatributo personajeAtributo;
        Usaclase usaclase;
        Usasubclase usasubclase;
        Dote dote;
        Personajeatributo patributo;

        TypedQuery<Mesa> queryMesas;
        TypedQuery<Dote> queryDotes;
        TypedQuery<Clase> queryClases;
        TypedQuery<Raza> queryRazas;
        TypedQuery<Atributo> queryAtributos;
        TypedQuery<Trasfondo> queryTrasfondos;
        TypedQuery<Personaje> queryPersonajes;
        TypedQuery<Tablaclasepornivel> queryTCNivel;
        TypedQuery<Usaclase> queryUsaClases;
        TypedQuery<Usasubclase> queryUsaSubClases;
        TypedQuery<Seccion> querySeccion;
        TypedQuery<Tema> queryTema;
        TypedQuery<Usuario> queryUsuarios;

        List<Integer> listaHabValores;
        List<Dote> listaDotes;
        List<Atributo> listaAtributos;
        List<Seccion> listaSecciones;

        String id;
        String personaje_id;
        int numMesasCreadas;
        int num;
        int numaux;
        String password;

        String msj;

        String accion;
        accion = request.getPathInfo();
        String vista = "";

        switch (accion) {
            case "/contraseñaperdida":
                msj = request.getParameter("msj");
                request.setAttribute("msj", msj);
                vista = "/WEB-INF/jsp/formularios/contraseñaperdida.jsp";
                break;
            case "/crearmesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    /////////////////////
                    /////////MESA////////
                    /////////////////////
                    queryMesas = em.createNamedQuery("Mesa.findByCreador", Mesa.class);
                    queryMesas.setParameter("creador", user.getApodo());
                    numMesasCreadas = queryMesas.getResultList().size();

                    request.setAttribute("mesasTotales", numMesasCreadas);

                    vista = "/WEB-INF/jsp/formularios/crearmesa.jsp";
                }
                break;
            case "/iniciosesion":

                vista = "/WEB-INF/jsp/formularios/iniciosesion.jsp";
                break;
            case "/modificarmesa":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    id = request.getParameter("id");

                    /////////////////////
                    /////////MESA////////
                    /////////////////////
                    queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                    queryMesas.setParameter("id", id);
                    mesa = queryMesas.getSingleResult();

                    request.setAttribute("mesa", mesa);
                    vista = "/WEB-INF/jsp/formularios/modificarmesa.jsp";
                }
                break;
            case "/modificarusuario":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    vista = "/WEB-INF/jsp/formularios/modificarusuario.jsp";
                }
                break;
            case "/registro":
                vista = "/WEB-INF/jsp/formularios/registro.jsp";
                break;
            case "/restablecercontraseña":

                id = request.getParameter("id");
                password = request.getParameter("password");
                msj = request.getParameter("msj");

                queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                queryUsuarios.setParameter("id", id);

                try {
                    user = queryUsuarios.getSingleResult();

                    if (!user.getContrasena().equals(password)) {
                        throw new Exception("No es la misma contraseña");
                    } else {

                        request.setAttribute("id", id);
                        request.setAttribute("contrasenaAntigua", password);
                        request.setAttribute("msj", msj);
                        vista = "/WEB-INF/jsp/formularios/restablecercontraseña.jsp";
                    }
                } catch (Exception ex) {
                    System.out.println("Error intentando cambiar contraseña:" + ex.getMessage());
                    vista = "/Principal/inicio";
                }
                break;
            case "/usuarioperdido":
                msj = request.getParameter("msj");
                request.setAttribute("msj", msj);
                vista = "/WEB-INF/jsp/formularios/usuarioperdido.jsp";
                break;
            case "/crearpersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Numero total de personajes
                    queryPersonajes = em.createNamedQuery("Personaje.findByCreador", Personaje.class);
                    queryPersonajes.setParameter("creador", user);
                    request.setAttribute("personajesTotales", queryPersonajes.getResultList().size());

                    queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryAtributos = em.createNamedQuery("Atributo.findAll", Atributo.class);
                    request.setAttribute("listaAtributos", queryAtributos.getResultList());
                    queryTrasfondos = em.createNamedQuery("Trasfondo.findAll", Trasfondo.class);
                    request.setAttribute("listaTrasfondos", queryTrasfondos.getResultList());

                    vista = "/WEB-INF/jsp/formularios/crearpersonaje.jsp";
                }
                break;
            case "/modificarPersonajeCaracteristicas":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    //Comprobamos si el personaje es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();
                        if (personaje.getUsuario().getId().equals(user.getId())) {
                            request.setAttribute("personaje", personaje);
                            vista = "/WEB-INF/jsp/formularios/modificarPersonajeCaracteristicas.jsp";
                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/modificarPersonajeHabilidades":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    //Comprobamos si el personaje es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);

                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (personaje.getUsuario().getId().equals(user.getId())) {

                            //Necesitamos la tabla clase del nivel para el BC
                            queryTCNivel = em.createNamedQuery("Tablaclasepornivel.findByClaseNivel", Tablaclasepornivel.class);
                            queryTCNivel.setParameter("clase", personaje.getClase().getId());
                            queryTCNivel.setParameter("nivel", personaje.getNivel());
                            try {
                                tcnivel = queryTCNivel.getSingleResult();
                                num = tcnivel.getTablaclases().getBc();
                            } catch (Exception ex) {
                                num = 1;
                            }

                            //Recoger las habilidades
                            listaHabValores = new ArrayList();

                            for (int i = 0; i < personaje.getPersonajehabilidadesList().size(); i++) {

                                personajeHabilidad = personaje.getPersonajehabilidadesList().get(i);
                                habilidad = personajeHabilidad.getHabilidades();

                                for (int j = 0; j < personaje.getPersonajeatributosList().size(); j++) {

                                    personajeAtributo = personaje.getPersonajeatributosList().get(j);

                                    if (habilidad.getAtributo().getId().equals(personajeAtributo.getAtributos().getId())) {
                                        //Hay que calcular los modificadores
                                        switch (personajeAtributo.getValor()) {
                                            case 8:
                                            case 9:
                                                numaux = -1;
                                                break;
                                            case 10:
                                            case 11:
                                                numaux = 0;
                                                break;
                                            case 12:
                                            case 13:
                                                numaux = 1;
                                                break;
                                            case 14:
                                            case 15:
                                                numaux = 2;
                                                break;
                                            case 16:
                                            case 17:
                                                numaux = 3;
                                                break;
                                            case 18:
                                            case 19:
                                                numaux = 4;
                                                break;
                                            case 20:
                                                numaux = 5;
                                            default:
                                                numaux = 0;
                                        }

                                        if (personajeHabilidad.getCompetencia().equals("Si")) {
                                            numaux = num + numaux;
                                        }

                                        listaHabValores.add(numaux);
                                        j = personaje.getPersonajeatributosList().size();
                                    }
                                }
                            }
                            request.setAttribute("listaValoresHab", listaHabValores);
                            request.setAttribute("listaPersonajeHabilidades", personaje.getPersonajehabilidadesList());
                            request.setAttribute("personaje", personaje);
                            vista = "/WEB-INF/jsp/formularios/modificarPersonajeHabilidades.jsp";
                        } else {
                            vista = "/Principal/inicio";
                        }

                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/modificarPersonajeAtributos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    //Comprobamos si el personaje es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (personaje.getUsuario().getId().equals(user.getId())) {
                            request.setAttribute("listaPersonajeAtributos", personaje.getPersonajeatributosList());
                            request.setAttribute("personaje", personaje);
                            vista = "/WEB-INF/jsp/formularios/modificarPersonajeAtributos.jsp";
                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajeSubirNivel":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    //Comprobamos si el personaje es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (personaje.getUsuario().getId().equals(user.getId())) {

                            //Si quiere un dote
                            queryDotes = em.createNamedQuery("Dote.findAll", Dote.class);
                            listaDotes = queryDotes.getResultList();

                            //eliminamos los que ya tenga
                            for (int i = 0; i < personaje.getDotesList().size(); i++) {
                                dote = personaje.getDotesList().get(i);
                                for (int j = 0; j < listaDotes.size(); j++) {
                                    if (listaDotes.get(j).getId().equals(dote.getId())) {
                                        listaDotes.remove(j);
                                        j = listaDotes.size();
                                    }
                                }
                            }
                            request.setAttribute("listaDotes", listaDotes);

                            //Si quiere subir atributos 
                            queryAtributos = em.createNamedQuery("Atributo.findAll", Atributo.class);
                            listaAtributos = queryAtributos.getResultList();

                            //eliminamos los que ya tenga con valor 20
                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                patributo = personaje.getPersonajeatributosList().get(i);
                                if (patributo.getValor() == 20) {
                                    for (int j = 0; j < listaAtributos.size(); j++) {
                                        if (listaAtributos.get(j).getId().equals(patributo.getAtributos().getId())) {
                                            listaDotes.remove(j);
                                            j = listaDotes.size();
                                        }
                                    }
                                }
                            }
                            request.setAttribute("listaAtributos1", listaAtributos);

                            listaAtributos = queryAtributos.getResultList();

                            //eliminamos los que ya tenga con valor 19 (no puede sumarle 2)
                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                patributo = personaje.getPersonajeatributosList().get(i);
                                if (patributo.getValor() == 19) {
                                    for (int j = 0; j < listaAtributos.size(); j++) {
                                        if (listaAtributos.get(j).getId().equals(patributo.getAtributos().getId())) {
                                            listaDotes.remove(j);
                                            j = listaDotes.size();
                                        }
                                    }
                                }
                            }
                            request.setAttribute("listaAtributos2", listaAtributos);

                            try {

                                ////RASGOS////
                                //Espacios de conjuros y BC 
                                queryTCNivel = em.createNamedQuery("Tablaclasepornivel.findByClaseNivel", Tablaclasepornivel.class);
                                queryTCNivel.setParameter("clase", personaje.getClase().getId());
                                queryTCNivel.setParameter("nivel", personaje.getNivel() + 1);
                                //Rasgos Clase
                                queryUsaClases = em.createNamedQuery("Usaclase.findByClaseNivel", Usaclase.class);
                                queryUsaClases.setParameter("clase", personaje.getClase().getId());
                                queryUsaClases.setParameter("nivel", personaje.getNivel() + 1);
                                //Rasgos SubClase
                                if (personaje.getSubclase() != null) {
                                    queryUsaSubClases = em.createNamedQuery("Usasubclase.findBySubclaseNivel", Usasubclase.class);
                                    queryUsaSubClases.setParameter("subclase", personaje.getClase().getId());
                                    queryUsaSubClases.setParameter("nivel", (personaje.getNivel() + 1));
                                    usasubclase = queryUsaSubClases.getSingleResult();
                                    request.setAttribute("pjRasgosSubClase", usasubclase.getRasgos());
                                }

                                tcnivel = queryTCNivel.getSingleResult();
                                usaclase = queryUsaClases.getSingleResult();

                                request.setAttribute("pjHechizosClase", tcnivel.getTablaclases().getEspacioshechizosList().get(0));
                                request.setAttribute("pjTablaClase", tcnivel.getTablaclases());
                                request.setAttribute("pjRasgosClase", usaclase.getRasgos());

                            } catch (Exception ex) {
                                System.out.println("Aun no implementado");
                            }
                            request.setAttribute("dadoClase", personaje.getClase().getDpg());
                            request.setAttribute("dadoClaseInteger", Integer.parseInt(personaje.getClase().getDpg().substring(1)));
                            request.setAttribute("nombreClase", personaje.getClase().getNombre());
                            request.setAttribute("nivelSubclase", personaje.getClase().getNivelsubclase());
                            request.setAttribute("listaSubclases", personaje.getClase().getSubclasesList());
                            request.setAttribute("nivelSiguiente", (personaje.getNivel() + 1));
                            request.setAttribute("personaje", personaje);

                            vista = "/WEB-INF/jsp/formularios/personajeSubirNivel.jsp";
                        } else {
                            vista = "/Personajes/personajePerfil?=" + personaje_id;
                        }
                    } catch (Exception ex) {
                        vista = "/Personajes/personajePerfil?=" + personaje_id;
                    }
                }
                break;
            case "/crearHilo":

                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    /////////////////////
                    /////////TEMA////////
                    /////////////////////
                    queryTema = em.createNamedQuery("Tema.findAll", Tema.class);
                    request.setAttribute("listaTemas", queryTema.getResultList());

                    /////////////////////
                    ///////SECCION///////
                    /////////////////////
                    querySeccion = em.createNamedQuery("Seccion.findAll", Seccion.class);
                    listaSecciones = querySeccion.getResultList();

                    if (user.getAdmin() != 1) {
                        for (int i = 0; i < listaSecciones.size(); i++) {
                            if (listaSecciones.get(i).getTitulo().equals("Administración")) {
                                listaSecciones.remove(i);
                                i = listaSecciones.size();
                            }
                        }
                    }

                    request.setAttribute("listaSecciones", listaSecciones);
                    request.setAttribute("numHilos", user.getHiloList().size());
                    vista = "/WEB-INF/jsp/formularios/crearHilo.jsp";
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
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException,
            IOException {
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException,
            IOException {
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

package controlador;

import entidades.Atributo;
import entidades.Clase;
import entidades.Dote;
import entidades.Equipo;
import entidades.Espaciohechizo;
import entidades.Habilidad;
import entidades.Hechizo;
import entidades.Mejoradote;
import entidades.Personajeatributo;
import entidades.Personajeatributooriginal;
import entidades.Personajehabilidad;
import entidades.Personaje;
import entidades.Propiedad;
import entidades.Rasgo;
import entidades.Raza;
import entidades.Requisitosdote;
import entidades.Subclase;
import entidades.Subraza;
import entidades.Tablaclasepornivel;
import entidades.Tablaclases;
import entidades.Trasfondo;
import entidades.Usaclase;
import entidades.Usasubclase;
import entidades.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "ControladorPersonajes", urlPatterns = {"/Personajes/*"})
public class ControladorPersonajes extends HttpServlet {

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
        boolean conseguido;
        String msj;
        Object result;

        Usuario user;
        Personaje personaje;
        Personaje personajeaux;
        Clase clase;
        Subclase subclase;
        Raza raza;
        Subraza subraza;
        Trasfondo transfondo;
        Atributo atributo;
        Personajeatributo personajeAtributo;
        Personajeatributooriginal personajeAtributoOriginal;
        Personajeatributo pjAtr;
        Habilidad habilidad;
        Personajehabilidad personajeHabilidad;
        Equipo equipo;
        Hechizo hechizo;
        Tablaclasepornivel tcnivel;
        Usaclase usaclase;
        Usasubclase usasubclase;
        Dote dote;

        TypedQuery<Usuario> queryUsuarios;
        TypedQuery<Clase> queryClases;
        TypedQuery<Subclase> querySubclases;
        TypedQuery<Raza> queryRazas;
        TypedQuery<Subraza> querySubrazas;
        TypedQuery<Trasfondo> queryTrasfondos;
        TypedQuery<Personaje> queryPersonajes;
        TypedQuery<Atributo> queryAtributos;
        TypedQuery<Habilidad> queryHabilidades;
        TypedQuery<Equipo> queryEquipo;
        TypedQuery<Hechizo> queryHechizos;
        TypedQuery<Requisitosdote> queryRDotes;
        TypedQuery<Mejoradote> queryMDotes;
        TypedQuery<Tablaclasepornivel> queryTCNivel;
        TypedQuery<Usaclase> queryUsaClases;
        TypedQuery<Usasubclase> queryUsaSubClases;
        TypedQuery<Dote> queryDotes;

        Query queryAUX;

        List<Personaje> listaPersonajes;
        List<String> fotosPersonajes;
        List<Atributo> listaAtributos;
        List<Atributo> listaAtributosAux;
        List<Personajeatributo> listaPersonajeAtributos;
        List<Habilidad> listaHabilidades;
        List<Personajehabilidad> listaPersonajeHabilidades;
        List<Equipo> listaEquipo;
        List<Hechizo> listaHechizos;
        List<List<Propiedad>> listalistaPropiedades;
        List<Dote> listaDotes;
        List<Requisitosdote> listaRDotes;
        List<Mejoradote> listaMDotes;
        List<Integer> listaHabValores;
        List<String> listaValoresSalvacion;
        List<Personajeatributooriginal> listaPersonajeAtributosOriginal;

        HashSet<Hechizo> hashAuxHechizos;
        HashSet<Equipo> hashAuxEquipo;

        String id;
        String personaje_id;

        String personaje_nombre;
        String personaje_clase;
        String personaje_subclase;
        String personaje_raza;
        String personaje_subraza;
        String personaje_trasfondo;

        String[] personaje_habilidades;

        String atributo_constitucion;
        String atributo_fuerza;
        String atributo_destreza;
        String atributo_sabiduria;
        String atributo_inteligencia;
        String atributo_carisma;
        String salvacion_constitucion;
        String salvacion_fuerza;
        String salvacion_destreza;
        String salvacion_sabiduria;
        String salvacion_inteligencia;
        String salvacion_carisma;
        int valorAtributo;
        String[] atributos;
        String atributo1;
        String atributo2;

        String personaje_alineamiento;
        String personaje_edad;
        String personaje_apariencia;
        String personaje_rasgos;
        String personaje_defectos;
        String personaje_vinculos;
        String personaje_idiomas;
        String personaje_historia;

        String escuela;
        String claseH;
        String escuelaSQL;
        String claseHSQL;
        String tipo;
        String categoria;
        String propiedad;
        String tipoSQL;
        String categoriaSQL;
        String propiedadSQL;
        String ordenar;
        String razaString;
        String claseString;
        String nivelString;
        String numString;
        String razaSQL;
        String claseSQL;
        String nivelSQL;
        String titulo;
        String subtitulo;
        String nombre;

        String claseArmadura;
        String puntosHP;
        String puntosEXP;

        InputStream contenidoImagen;
        byte[] imageData;

        int num;
        int numaux;
        int numPag;
        boolean encontrado;
        int index;

        String sql;

        switch (accion) {
            case "/crearpersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                conseguido = false;
                msj = "";

                //Bloque 1
                personaje_nombre = request.getParameter("personaje_nombre");
                personaje_clase = request.getParameter("clase");
                personaje_subclase = request.getParameter("subclase");
                personaje_raza = request.getParameter("raza");
                personaje_subraza = request.getParameter("subraza");
                personaje_trasfondo = request.getParameter("trasfondo");
                //Bloque 2
                personaje_habilidades = request.getParameterValues("habilidades");
                //Bloque 3
                atributo_constitucion = request.getParameter("atributo_Constitución");
                atributo_fuerza = request.getParameter("atributo_Fuerza");
                atributo_destreza = request.getParameter("atributo_Destreza");
                atributo_sabiduria = request.getParameter("atributo_Sabiduría");
                atributo_inteligencia = request.getParameter("atributo_Inteligencia");
                atributo_carisma = request.getParameter("atributo_Carisma");
                atributos = request.getParameterValues("atributos");
                atributo1 = request.getParameter("obtienes_atr1");
                atributo2 = request.getParameter("obtienes_atr2");
                //Bloque 4
                personaje_alineamiento = request.getParameter("alineamiento");
                personaje_edad = request.getParameter("personaje_edad");
                personaje_apariencia = request.getParameter("personaje_apariencia");
                personaje_rasgos = request.getParameter("personaje_rasgos");
                personaje_defectos = request.getParameter("personaje_defectos");
                personaje_vinculos = request.getParameter("personaje_vinculos");
                personaje_idiomas = request.getParameter("personaje_idiomas");
                personaje_historia = request.getParameter("personaje_historia");

                if (personaje_nombre != null && personaje_alineamiento != null && personaje_clase != null && personaje_raza != null
                        && personaje_subraza != null && personaje_trasfondo != null) {
                    try {

                        //////////////////
                        //////NOMBRE//////
                        ////////////////// 
                        comprobarCadena(personaje_nombre, "El nombre no es válido");

                        queryPersonajes = em.createNamedQuery("Personaje.findByNombreCreador", Personaje.class);
                        queryPersonajes.setParameter("nombre", personaje_nombre);
                        queryPersonajes.setParameter("creador", user);
                        listaPersonajes = queryPersonajes.getResultList();

                        if (!listaPersonajes.isEmpty()) {
                            throw new Exception("Solo puedes tener un personaje con ese nombre ");
                        }
                        //////////////////
                        //////CLASE///////
                        //////////////////
                        queryClases = em.createNamedQuery("Clase.findByNombre", Clase.class);
                        queryClases.setParameter("nombre", personaje_clase);
                        clase = queryClases.getSingleResult();
                        //////////////////
                        ///////RAZA///////
                        //////////////////
                        queryRazas = em.createNamedQuery("Raza.findByNombre", Raza.class);
                        queryRazas.setParameter("nombre", personaje_raza);
                        raza = queryRazas.getSingleResult();
                        //////////////////
                        //////SUBRAZA/////
                        //////////////////
                        querySubrazas = em.createNamedQuery("Subraza.findByNombre", Subraza.class);
                        querySubrazas.setParameter("nombre", personaje_subraza);
                        subraza = querySubrazas.getSingleResult();
                        //////////////////
                        ////TRASFONDO/////
                        //////////////////
                        queryTrasfondos = em.createNamedQuery("Trasfondo.findByNombre", Trasfondo.class);
                        queryTrasfondos.setParameter("nombre", personaje_trasfondo);
                        transfondo = queryTrasfondos.getSingleResult();

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        personaje = new Personaje(personaje_nombre, personaje_alineamiento, 1, Integer.parseInt(clase.getDpg().substring(1)),
                                Integer.parseInt(clase.getDpg().substring(1)), 10, user);

                        personaje.setClase(clase);
                        personaje.setRaza(raza);
                        personaje.setSubraza(subraza);
                        personaje.setTrasfondo(transfondo);

                        if (!personaje_subclase.equalsIgnoreCase("-")) {
                            querySubclases = em.createNamedQuery("Subclase.findByNombre", Subclase.class);
                            querySubclases.setParameter("nombre", personaje_subclase);
                            subclase = querySubclases.getSingleResult();
                            personaje.setSubclase(subclase);
                        }
                        if (personaje_edad != null && !personaje_edad.equals("")) {
                            comprobarCadena(personaje_edad, "La edad no es válida");
                            personaje.setEdad(Integer.valueOf(personaje_edad));
                        }
                        if (personaje_apariencia != null && !personaje_apariencia.equals("")) {
                            comprobarCadena(personaje_apariencia, "La apariencia no es válida");
                            personaje.setApariencia(personaje_apariencia);
                        }
                        if (personaje_rasgos != null && !personaje_rasgos.equals("")) {
                            comprobarCadena(personaje_rasgos, "Los rasgos no son válidos");
                            personaje.setRaspersonalidad(personaje_rasgos);
                        }
                        if (personaje_defectos != null && !personaje_defectos.equals("")) {
                            comprobarCadena(personaje_defectos, "Los defectos no son válidos");
                            personaje.setDefectos(personaje_defectos);
                        }
                        if (personaje_vinculos != null && !personaje_vinculos.equals("")) {
                            comprobarCadena(personaje_vinculos, "Los vínculos no son válidos");
                            personaje.setVinculos(personaje_vinculos);
                        }
                        if (personaje_idiomas != null && !personaje_idiomas.equals("")) {
                            comprobarCadena(personaje_idiomas, "Los idiomas no son válidos");
                            personaje.setIdiomas(personaje_idiomas);
                        }
                        if (personaje_historia != null && !personaje_historia.equals("")) {
                            comprobarCadena(personaje_historia, "La historia no es válida");
                            personaje.setHistoria(personaje_historia);
                        }

                        personaje.setPexp(0);

                        persist(personaje);

                        //////////////////////
                        //////ATRIBUTOS///////
                        //////////////////////
                        listaPersonajeAtributos = new ArrayList();
                        listaPersonajeAtributosOriginal = new ArrayList();

                        queryAtributos = em.createNamedQuery("Atributo.findAll", Atributo.class);
                        listaAtributos = queryAtributos.getResultList();

                        for (int i = 0; i < listaAtributos.size(); i++) {
                            atributo = listaAtributos.get(i);
                            valorAtributo = 0;

                            switch (atributo.getNombre()) {
                                case "Constitución":
                                    valorAtributo = Integer.valueOf(atributo_constitucion);
                                    break;
                                case "Fuerza":
                                    valorAtributo = Integer.valueOf(atributo_fuerza);
                                    break;
                                case "Destreza":
                                    valorAtributo = Integer.valueOf(atributo_destreza);
                                    break;
                                case "Sabiduría":
                                    valorAtributo = Integer.valueOf(atributo_sabiduria);
                                    break;
                                case "Inteligencia":
                                    valorAtributo = Integer.valueOf(atributo_inteligencia);
                                    break;
                                case "Carisma":
                                    valorAtributo = Integer.valueOf(atributo_carisma);
                                    break;
                            }

                            index = 0;
                            encontrado = false;

                            //Se eligen 3 atributos 
                            if (atributos != null) {
                                while (encontrado == false && index < atributos.length) {
                                    if (atributo.getNombre().equals(atributos[index])) {
                                        encontrado = true;
                                        valorAtributo = valorAtributo + 1;
                                    }
                                    index++;
                                }
                                //Se eligen 2 atributo
                            } else if (atributo1 != null) {
                                if (atributo.getNombre().equals(atributo1)) {
                                    valorAtributo = valorAtributo + 1;
                                }
                                if (atributo.getNombre().equals(atributo2)) {
                                    valorAtributo = valorAtributo + 2;
                                }
                            } else {//Los atributos vienen predefinidos
                                while (encontrado == false && index < subraza.getSumarazaList().size()) {
                                    if (atributo.getId().equals(subraza.getSumarazaList().get(index).getAtributoID())) {
                                        encontrado = true;
                                        valorAtributo = valorAtributo + Integer.valueOf(subraza.getSumarazaList().get(index).getModificador());
                                    }
                                    index++;
                                }
                            }

                            personajeAtributo = new Personajeatributo(personaje.getId(), atributo.getId(),
                                    valorAtributo, "No");
                            personajeAtributoOriginal = new Personajeatributooriginal(personaje.getId(), atributo.getId(),
                                    valorAtributo, "No");

                            listaPersonajeAtributos.add(personajeAtributo);
                            listaPersonajeAtributosOriginal.add(personajeAtributoOriginal);
                        }

                        //Para cambiar a los que tu clase les pone salvación 
                        listaAtributosAux = clase.getAtributosList();

                        for (int i = 0; i < listaAtributosAux.size(); i++) {

                            atributo = listaAtributosAux.get(i);
                            index = 0;
                            encontrado = false;

                            while (encontrado == false && index < listaPersonajeAtributos.size()) {
                                if (atributo.getNombre().equals(listaPersonajeAtributos.get(index).getAtributos().getNombre())) {
                                    encontrado = true;
                                    listaPersonajeAtributos.get(index).setSalvacion("Si");
                                }
                                index++;
                            }

                            index = 0;
                            encontrado = false;

                            while (encontrado == false && index < listaPersonajeAtributos.size()) {
                                if (atributo.getNombre().equals(listaPersonajeAtributosOriginal.get(index).getAtributos().getNombre())) {
                                    encontrado = true;
                                    listaPersonajeAtributosOriginal.get(index).setSalvacion("Si");
                                }
                                index++;
                            }
                        }

                        personaje.setPersonajeatributosList(listaPersonajeAtributos);

                        //////////////////////
                        /////HABILIDADES//////
                        //////////////////////
                        listaPersonajeHabilidades = new ArrayList();

                        queryHabilidades = em.createNamedQuery("Habilidad.findAll", Habilidad.class);
                        listaHabilidades = queryHabilidades.getResultList();

                        for (int i = 0; i < listaHabilidades.size(); i++) {

                            habilidad = listaHabilidades.get(i);
                            index = 0;
                            encontrado = false;

                            personajeHabilidad = new Personajehabilidad(personaje.getId(), habilidad.getId(), "No");

                            if (personaje_habilidades != null) {

                                while (encontrado == false && index < personaje_habilidades.length) {
                                    if (personaje_habilidades[index].equalsIgnoreCase(habilidad.getNombre())) {

                                        encontrado = true;
                                        personajeHabilidad.setCompetencia("Si");

                                    }
                                    index++;
                                }
                            }
                            listaPersonajeHabilidades.add(personajeHabilidad);
                        }

                        personaje.setPersonajehabilidadesList(listaPersonajeHabilidades);

                        ////////////////////
                        ///////IMAGEN///////
                        ////////////////////
                        ServletContext context = request.getServletContext();
                        String rutaImagen = context.getRealPath("/img/razas/" + raza.getNombre().toLowerCase() + ".jpg");
                        contenidoImagen = new FileInputStream(rutaImagen);

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = contenidoImagen.read(buffer)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, bytesRead);
                        }
                        imageData = byteArrayOutputStream.toByteArray();
                        personaje.setImagenpersonaje(imageData);

                        updatePersonajes(personaje);

                        System.out.println("Creado el personaje: " + personaje_nombre + " de " + user.getId());
                        conseguido = true;

                    } catch (NumberFormatException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible crear un personaje en este momento </p>";
                        System.out.println("Error: Imposible crear un personaje en este momento");
                        System.out.println("NumberFormatException: " + ex.getMessage());
                    } catch (RuntimeException ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: Imposible crear un personaje en este momento </p>";
                        System.out.println("Error: Imposible crear un personaje en este momento: ");
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
                    vista = "/Personajes/personajesPerfil";
                } else {
                    request.setAttribute("msj", msj);
                    vista = "/Formularios/crearpersonaje";
                }
                break;
            ////////////////////////////////////////////////////
            /////////MODIFICAR/BORRAR/COPIAR PERSONAJES/////////
            ////////////////////////////////////////////////////
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

                    personaje_nombre = request.getParameter("personaje_nombre");
                    personaje_alineamiento = request.getParameter("alineamiento");
                    personaje_edad = request.getParameter("personaje_edad");
                    personaje_apariencia = request.getParameter("personaje_apariencia");
                    personaje_rasgos = request.getParameter("personaje_rasgos");
                    personaje_defectos = request.getParameter("personaje_defectos");
                    personaje_vinculos = request.getParameter("personaje_vinculos");
                    personaje_idiomas = request.getParameter("personaje_idiomas");
                    personaje_historia = request.getParameter("personaje_historia");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);

                    try {
                        personaje = queryPersonajes.getSingleResult();
                        //Comprobamos que es tuyo

                        if (personaje.getUsuario().getId().equals(user.getId())) {

                            //////////////////
                            //////NOMBRE//////
                            ////////////////// 
                            comprobarCadena(personaje_nombre, "El nombre no es valido");

                            queryPersonajes = em.createNamedQuery("Personaje.findByNombreCreador", Personaje.class);
                            queryPersonajes.setParameter("nombre", personaje_nombre);
                            queryPersonajes.setParameter("creador", user);
                            listaPersonajes = queryPersonajes.getResultList();

                            if (!listaPersonajes.isEmpty() && !personaje.getNombre().equals(personaje_nombre)) {
                                throw new Exception("Solo puedes tener un personaje con ese nombre");
                            }

                            if (personaje_edad != null && !personaje_edad.equals("")) {
                                comprobarCadena(personaje_edad, "La edad no es válida");
                                personaje.setEdad(Integer.valueOf(personaje_edad));
                            }
                            if (personaje_apariencia != null) {
                                comprobarCadena(personaje_apariencia, "La aparencia no es válida");
                                personaje.setApariencia(personaje_apariencia);
                            } else {
                                personaje.setApariencia("");
                            }
                            if (personaje_rasgos != null) {
                                comprobarCadena(personaje_rasgos, "Los rasgos no son válidos");
                                personaje.setRaspersonalidad(personaje_rasgos);
                            } else {
                                personaje.setRaspersonalidad("");
                            }
                            if (personaje_defectos != null) {
                                comprobarCadena(personaje_defectos, "Los defectos no son válidos");
                                personaje.setDefectos(personaje_defectos);
                            } else {
                                personaje.setDefectos("");
                            }
                            if (personaje_vinculos != null) {
                                comprobarCadena(personaje_vinculos, "Los vínculos no son válidos");
                                personaje.setVinculos(personaje_vinculos);
                            } else {
                                personaje.setVinculos("");
                            }
                            if (personaje_idiomas != null) {
                                comprobarCadena(personaje_idiomas, "Los idomas no son válidos");
                                personaje.setIdiomas(personaje_idiomas);
                            } else {
                                personaje.setIdiomas("");
                            }
                            if (personaje_historia != null) {
                                comprobarCadena(personaje_historia, "La historia no es válida");
                                personaje.setHistoria(personaje_historia);
                            } else {
                                personaje.setHistoria("");
                            }

                            personaje.setAlineamiento(personaje_alineamiento);

                            updatePersonajes(personaje);

                            request.setAttribute("id", personaje_id);

                            vista = "/Personajes/personajePerfilCaracteristicas";

                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception ex) {
                        msj = "<p style=\"margin-left: 10px\"> Error: " + ex.getMessage() + "</p>";
                        request.setAttribute("msj", msj);
                        vista = "/Formularios/modificarPersonajeCaracteristicas?id=" + personaje_id;
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

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);

                    try {
                        personaje = queryPersonajes.getSingleResult();
                        //Comprobamos que es tuyo
                        if (personaje.getUsuario().getId().equals(user.getId())) {

                            personaje_habilidades = request.getParameterValues("habilidades");
                            listaPersonajeHabilidades = personaje.getPersonajehabilidadesList();
                            for (int i = 0; i < personaje.getPersonajehabilidadesList().size(); i++) {
                                habilidad = personaje.getPersonajehabilidadesList().get(i).getHabilidades();
                                index = 0;
                                encontrado = false;
                                if (personaje_habilidades != null) {
                                    while (index < personaje_habilidades.length && encontrado == false) {
                                        if (personaje_habilidades[index].equals(habilidad.getNombre())) {
                                            encontrado = true;
                                            listaPersonajeHabilidades.get(i).setCompetencia("Si");
                                        } else {
                                            listaPersonajeHabilidades.get(i).setCompetencia("No");
                                        }
                                        index++;
                                    }
                                }
                                if (encontrado == true) {
                                    listaPersonajeHabilidades.get(i).setCompetencia("Si");
                                } else {
                                    listaPersonajeHabilidades.get(i).setCompetencia("No");
                                }
                            }
                            personaje.setPersonajehabilidadesList(listaPersonajeHabilidades);
                            updatePersonajes(personaje);
                            vista = "/Personajes/personajePerfilHabilidades?id=" + personaje_id;
                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception es) {
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

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);

                    try {
                        personaje = queryPersonajes.getSingleResult();
                        //Comprobamos que es tuyo
                        if (personaje.getUsuario().getId().equals(user.getId())) {
                            //////////////////////
                            //////ATRIBUTOS///////
                            //////////////////////
                            atributo_constitucion = request.getParameter("atributo_Constitución");
                            atributo_fuerza = request.getParameter("atributo_Fuerza");
                            atributo_destreza = request.getParameter("atributo_Destreza");
                            atributo_sabiduria = request.getParameter("atributo_Sabiduría");
                            atributo_inteligencia = request.getParameter("atributo_Inteligencia");
                            atributo_carisma = request.getParameter("atributo_Carisma");

                            salvacion_constitucion = request.getParameter("salvacion_Constitución");
                            salvacion_fuerza = request.getParameter("salvacion_Fuerza");
                            salvacion_destreza = request.getParameter("salvacion_Destreza");
                            salvacion_sabiduria = request.getParameter("salvacion_Sabiduría");
                            salvacion_inteligencia = request.getParameter("salvacion_Inteligencia");
                            salvacion_carisma = request.getParameter("salvacion_Carisma");

                            listaPersonajeAtributos = new ArrayList();

                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                listaPersonajeAtributos.add(personaje.getPersonajeatributosList().get(i));
                            }

                            for (int i = 0; i < listaPersonajeAtributos.size(); i++) {
                                atributo = listaPersonajeAtributos.get(i).getAtributos();
                                valorAtributo = 0;

                                switch (atributo.getNombre()) {
                                    case "Constitución":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_constitucion));
                                        if (salvacion_constitucion != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                    case "Fuerza":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_fuerza));
                                        if (salvacion_fuerza != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                    case "Destreza":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_destreza));
                                        if (salvacion_destreza != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                    case "Sabiduría":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_sabiduria));
                                        if (salvacion_sabiduria != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                    case "Inteligencia":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_inteligencia));
                                        if (salvacion_inteligencia != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                    case "Carisma":
                                        listaPersonajeAtributos.get(i).setValor(Integer.valueOf(atributo_carisma));
                                        if (salvacion_carisma != null) {
                                            listaPersonajeAtributos.get(i).setSalvacion("Si");
                                        } else {
                                            listaPersonajeAtributos.get(i).setSalvacion("No");
                                        }
                                        break;
                                }
                            }
                            personaje.setPersonajeatributosList(listaPersonajeAtributos);
                            updatePersonajes(personaje);
                            vista = "/Personajes/personajePerfilAtributos?id=" + personaje_id;
                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception es) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/claseArmadura":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    personaje = queryPersonajes.getSingleResult();

                    //Comprobamos que es tuyo
                    if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                        vista = "/Principal/inicio";
                    } else {
                        try {
                            claseArmadura = request.getParameter("claseArmadura");
                            comprobarCadena(claseArmadura, "");
                            personaje.setClasearmadura(Integer.parseInt(claseArmadura));
                            updatePersonajes(personaje);
                        } catch (Exception ex) {
                            System.out.println("clase de armadura maliciosa");
                        }
                        vista = "/Personajes/personajePerfil?id=" + personaje_id;
                    }
                }
                break;
            case "/puntosVidaActual":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    personaje = queryPersonajes.getSingleResult();

                    //Comprobamos que es tuyo
                    if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                        vista = "/Principal/inicio";
                    } else {
                        try {
                            puntosHP = request.getParameter("puntosHP");
                            comprobarCadena(puntosHP, "");
                            personaje.setPvidaactuales(Integer.parseInt(puntosHP));
                            updatePersonajes(personaje);
                        } catch (Exception ex) {
                            System.out.println("puntos de vida maliciosos");
                        }

                        vista = "/Personajes/personajePerfil?id=" + personaje_id;
                    }
                }
                break;
            case "/puntosVidaTotal":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    personaje = queryPersonajes.getSingleResult();

                    //Comprobamos que es tuyo
                    if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                        vista = "/Principal/inicio";
                    } else {
                        try {
                            puntosHP = request.getParameter("puntosHP");
                            comprobarCadena(puntosHP, "");
                            personaje.setPvida(Integer.parseInt(puntosHP));
                            updatePersonajes(personaje);
                        } catch (Exception ex) {
                            System.out.println("puntos de vida total maliciosos");
                        }
                        vista = "/Personajes/personajePerfil?id=" + personaje_id;
                    }
                }
                break;
            case "/puntosEXP":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    personaje = queryPersonajes.getSingleResult();

                    //Comprobamos que es tuyo
                    if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                        vista = "/Principal/inicio";
                    } else {
                        try {
                            puntosEXP = request.getParameter("puntosEXP");
                            comprobarCadena(puntosEXP, "");
                            personaje.setPexp(Integer.parseInt(puntosEXP));
                            updatePersonajes(personaje);
                        } catch (Exception ex) {
                            System.out.println("puntos de vida maliciosos");
                        }
                        vista = "/Personajes/personajePerfil?id=" + personaje_id;
                    }
                }
                break;
            case "/eliminarPersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        //Comprobamos que es tuyo
                        if (personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                            if (user.getPersonajeactual().getId().equals(personaje_id)) {
                                user.setPersonajeactual(null);
                            }
                            deletePersonajes(personaje);
                            vista = "/Personajes/personajesPerfil";

                        } else {
                            vista = "/Principal/inicio";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }

                break;

            case "/copiarPersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Tienes el limite
                    if (user.getPersonajesList().size() == 10) {
                        vista = "/WEB-INF/jsp/personajes/personajesPerfil.jsp";
                    } else {
                        personaje_id = request.getParameter("id");

                        queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                        queryPersonajes.setParameter("id", personaje_id);
                        personaje = queryPersonajes.getSingleResult();

                        //Comprobamos que es tuyo
                        if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

                            queryPersonajes = em.createNamedQuery("Personaje.findByCreador", Personaje.class);
                            queryPersonajes.setParameter("creador", user);

                            personajeaux = new Personaje(personaje, user, queryPersonajes.getResultList());
                            persist(personajeaux);

                            listaPersonajeAtributos = new ArrayList();
                            listaPersonajeAtributosOriginal = new ArrayList();
                            listaPersonajeHabilidades = new ArrayList();

                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                personajeAtributo = personaje.getPersonajeatributosList().get(i);
                                listaPersonajeAtributos.add(new Personajeatributo(personajeaux.getId(), personajeAtributo.getAtributos().getId(),
                                        personajeAtributo.getValor(), personajeAtributo.getSalvacion()));

                            }

                            for (int i = 0; i < personaje.getPersonajeatributosoriginalList().size(); i++) {
                                personajeAtributoOriginal = personaje.getPersonajeatributosoriginalList().get(i);
                                listaPersonajeAtributosOriginal.add(new Personajeatributooriginal(personajeaux.getId(), personajeAtributoOriginal.getAtributos().getId(),
                                        personajeAtributoOriginal.getValor(), personajeAtributoOriginal.getSalvacion()));

                            }
                            for (int i = 0; i < personaje.getPersonajehabilidadesList().size(); i++) {
                                personajeHabilidad = personaje.getPersonajehabilidadesList().get(i);
                                listaPersonajeHabilidades.add(new Personajehabilidad(personajeaux.getId(), personajeHabilidad.getHabilidades().getId(),
                                        personajeHabilidad.getCompetencia()));
                            }
                            personajeaux.setPersonajeatributosList(listaPersonajeAtributos);
                            personajeaux.setPersonajehabilidadesList(listaPersonajeHabilidades);

                            vista = "/Personajes/personajesPerfil";
                        }
                    }
                }

                break;
            ////////////////////////////////////////////
            /////////VISTAS DE LOS PERSONAJES///////////
            ////////////////////////////////////////////
            case "/personajes":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Para saber si estamos conectados o no
                if (user == null) {
                    id = "nulo";
                } else {
                    id = user.getId();
                }

                //Recogemos los datos
                numString = request.getParameter("pag");//numero de pag en la que estoy
                ordenar = request.getParameter("orden");//como ordenar
                razaString = request.getParameter("raza");//como ordenar
                claseString = request.getParameter("clase");//como ordenar
                nivelString = request.getParameter("nivel");//como ordenar

                //Comprobamos los datos
                if (ordenar == null) {

                    ordenar = "ordenar1";
                    razaSQL = " ";
                    claseSQL = " ";
                    nivelSQL = " ";
                    numString = "1";
                    num = 0;

                } else {
                    //Pagina actual
                    num = (Integer.valueOf(numString) - 1) * 6;//offset

                    if (razaString.equals("Raza")) {
                        razaSQL = " ";
                    } else {
                        razaSQL = "AND p.RAZA = '" + razaString + "' ";
                    }
                    if (claseString.equals("Clase")) {
                        claseSQL = " ";
                    } else {
                        claseSQL = "AND p.CLASE = '" + claseString + "' ";
                    }
                    if (nivelString.equals("0")) {
                        nivelSQL = " ";
                    } else {
                        nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                    }
                }

                System.out.println("Llega pag: " + numString);
                System.out.println("Llega orden: " + ordenar);
                System.out.println("Llega raza: " + razaString);
                System.out.println("Llega clase: " + claseString);
                System.out.println("Llega nivel: " + nivelString);

                /////////////////////////////////////
                ////////NUMERO DE PERSONAJES/////////
                /////////////////////////////////////
                sql = "SELECT COUNT(*) FROM PERSONAJE p "
                        + "WHERE p.USUARIO <> '" + id + "' "
                        + nivelSQL
                        + claseSQL
                        + razaSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                numPag = (((Number) result).intValue() / 7) + 1;

                switch (ordenar) {
                    case "ordenar1":
                        sql = "SELECT p.* FROM PERSONAJE p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL
                                + "ORDER BY  p.NOMBRE ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                    case "ordenar2":
                        sql = "SELECT p.* FROM PERSONAJE p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL
                                + "ORDER BY  p.NOMBRE DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Personaje.class);
                listaPersonajes = queryAUX.getResultList();

                fotosPersonajes = new ArrayList();

                for (int i = 0; i < listaPersonajes.size(); i++) {

                    if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                        fotosPersonajes.add("-");
                    } else {
                        fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                    }
                }

                request.setAttribute("listaPersonajes", listaPersonajes);

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale raza:" + razaString);
                System.out.println("Sale clase:" + claseString);
                System.out.println("Sale nivel:" + nivelString);
                System.out.println("Sale npag:" + numPag);

                queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                request.setAttribute("listaClases", queryClases.getResultList());
                queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                request.setAttribute("listaRazas", queryRazas.getResultList());

                request.setAttribute("orden", ordenar);
                request.setAttribute("filtroRaza", razaString);
                request.setAttribute("filtroClase", claseString);
                request.setAttribute("filtroNivel", nivelString);
                request.setAttribute("urlImagenes", fotosPersonajes);
                request.setAttribute("pag", numString);//numero de la pag
                request.setAttribute("numPag", numPag);//numero total de pag

                vista = "/WEB-INF/jsp/personajes/personajes.jsp";

                break;
            case "/personajesAmigos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("0")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJE p "
                            + "INNER JOIN AMIGO a ON p.USUARIO = a.AMIGO2 "
                            + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                            + "AND p.USUARIO <> '" + user.getId() + "' "
                            + nivelSQL
                            + claseSQL
                            + razaSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "INNER JOIN AMIGO a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "AND p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY  p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "INNER JOIN AMIGO a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "AND p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personaje.class);
                    listaPersonajes = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesAmigos.jsp";
                }
                break;
            case "/personajesAmigo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("0")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJE p "
                            + "WHERE p.USUARIO = '" + id + "' "
                            + nivelSQL
                            + claseSQL
                            + razaSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personaje.class);
                    listaPersonajes = queryAUX.getResultList();
                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryUsuarios = em.createNamedQuery("Usuario.findById", Usuario.class);
                    queryUsuarios.setParameter("id", id);
                    request.setAttribute("amigo", queryUsuarios.getSingleResult());

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesAmigo.jsp";
                }
                break;
            case "/personajesPerfil":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Recogemos los datos
                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    razaString = request.getParameter("raza");//como ordenar
                    claseString = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null) {

                        ordenar = "ordenar1";
                        razaSQL = " ";
                        claseSQL = " ";
                        nivelSQL = " ";
                        numString = "1";
                        num = 0;

                    } else {
                        //Pagina actual
                        num = (Integer.valueOf(numString) - 1) * 6;//offset

                        if (razaString.equals("Raza")) {
                            razaSQL = " ";
                        } else {
                            razaSQL = "AND p.RAZA = '" + razaString + "' ";
                        }
                        if (claseString.equals("Clase")) {
                            claseSQL = " ";
                        } else {
                            claseSQL = "AND p.CLASE = '" + claseString + "' ";
                        }
                        if (nivelString.equals("0")) {
                            nivelSQL = " ";
                        } else {
                            nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                        }
                    }

                    System.out.println("Llega pag: " + numString);
                    System.out.println("Llega orden: " + ordenar);
                    System.out.println("Llega raza: " + razaString);
                    System.out.println("Llega clase: " + claseString);
                    System.out.println("Llega nivel: " + nivelString);

                    /////////////////////////////////////
                    ////////NUMERO DE PERSONAJES/////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM PERSONAJE p "
                            + "WHERE p.USUARIO = '" + user.getId() + "' "
                            + nivelSQL
                            + claseSQL
                            + razaSQL;

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                    numPag = (((Number) result).intValue() / 7) + 1;

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJE p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personaje.class);
                    listaPersonajes = queryAUX.getResultList();

                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("orden", ordenar);
                    request.setAttribute("filtroRaza", razaString);
                    request.setAttribute("filtroClase", claseString);
                    request.setAttribute("filtroNivel", nivelString);
                    request.setAttribute("urlImagenes", fotosPersonajes);
                    request.setAttribute("pag", numString);//numero de la pag
                    request.setAttribute("numPag", numPag);//numero total de pag

                    vista = "/WEB-INF/jsp/personajes/personajesPerfil.jsp";
                }
                break;
            case "/personaje":
                //Recogemos los datos
                id = request.getParameter("id");

                //Comprobamos que exista
                queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                queryPersonajes.setParameter("id", id);
                try {
                    personaje = queryPersonajes.getSingleResult();

                    request.setAttribute("personaje", personaje);
                    request.setAttribute("imagenpersonaje", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                    vista = "/WEB-INF/jsp/personajes/personaje.jsp";
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }

                break;
            case "/personajePerfil":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");
                //Recogemos los datos
                personaje_id = request.getParameter("id");

                //Comprobamos que sea tuyo
                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);
                try {
                    personaje = queryPersonajes.getSingleResult();
                    request.setAttribute("personaje", personaje);
                    request.setAttribute("imagenpersonaje", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                    vista = "/WEB-INF/jsp/personajes/personajePerfil.jsp";
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }

                break;
            ////////////////////////////////////////////////////////////////////////
            //////////////////////////ELEGIR PJ ACTUAL//////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            case "/elegirPJActual":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los suyos
                    queryPersonajes = em.createNamedQuery("Personaje.findByCreador", Personaje.class);
                    queryPersonajes.setParameter("creador", user);
                    listaPersonajes = queryPersonajes.getResultList();

                    if (listaPersonajes == null || listaPersonajes.isEmpty()) {
                        vista = "/Usuarios/perfil";
                    } else {
                        //Recogemos los datos
                        numString = request.getParameter("pag");//numero de pag en la que estoy
                        ordenar = request.getParameter("orden");//como ordenar
                        razaString = request.getParameter("raza");//como ordenar
                        claseString = request.getParameter("clase");//como ordenar
                        nivelString = request.getParameter("nivel");//como ordenar

                        //Comprobamos los datos
                        if (ordenar == null) {

                            ordenar = "ordenar1";
                            razaSQL = " ";
                            claseSQL = " ";
                            nivelSQL = " ";
                            numString = "1";
                            num = 0;

                        } else {
                            num = (Integer.valueOf(numString) - 1) * 6;//offset

                            if (razaString.equals("Raza")) {
                                razaSQL = " ";
                            } else {
                                razaSQL = "AND p.RAZA = '" + razaString + "' ";
                            }
                            if (claseString.equals("Clase")) {
                                claseSQL = " ";
                            } else {
                                claseSQL = "AND p.CLASE = '" + claseString + "' ";
                            }
                            if (nivelString.equals("0")) {
                                nivelSQL = " ";
                            } else {
                                nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                            }
                        }

                        System.out.println("Llega pag: " + numString);
                        System.out.println("Llega orden: " + ordenar);
                        System.out.println("Llega raza: " + razaString);
                        System.out.println("Llega clase: " + claseString);
                        System.out.println("Llega nivel: " + nivelString);

                        /////////////////////////////////////
                        ////////NUMERO DE PERSONAJES/////////
                        /////////////////////////////////////
                        sql = "SELECT COUNT(*) FROM PERSONAJE p "
                                + "WHERE p.USUARIO = '" + user.getId() + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL;

                        queryAUX = em.createNativeQuery(sql);
                        result = queryAUX.getSingleResult();

                        //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                        numPag = (((Number) result).intValue() / 7) + 1;

                        switch (ordenar) {
                            case "ordenar1":
                                sql = "SELECT p.* FROM PERSONAJE p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                            case "ordenar2":
                                sql = "SELECT p.* FROM PERSONAJE p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                        }

                        queryAUX = em.createNativeQuery(sql, Personaje.class);
                        listaPersonajes = queryAUX.getResultList();

                        request.setAttribute("listaPersonajes", listaPersonajes);

                        System.out.println("Sale pag:" + numString);
                        System.out.println("Sale orden:" + ordenar);
                        System.out.println("Sale raza:" + razaString);
                        System.out.println("Sale clase:" + claseString);
                        System.out.println("Sale nivel:" + nivelString);
                        System.out.println("Sale npag:" + numPag);

                        queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                        request.setAttribute("listaClases", queryClases.getResultList());
                        queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                        request.setAttribute("listaRazas", queryRazas.getResultList());

                        fotosPersonajes = new ArrayList();

                        for (int i = 0; i < listaPersonajes.size(); i++) {

                            if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                                fotosPersonajes.add("-");
                            } else {
                                fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                            }
                        }

                        request.setAttribute("orden", ordenar);
                        request.setAttribute("filtroRaza", razaString);
                        request.setAttribute("filtroClase", claseString);
                        request.setAttribute("filtroNivel", nivelString);
                        request.setAttribute("urlImagenes", fotosPersonajes);
                        request.setAttribute("pag", numString);//numero de la pag
                        request.setAttribute("numPag", numPag);//numero total de pag

                        vista = "/WEB-INF/jsp/personajes/personajesPerfilElegir.jsp";
                    }
                }
                break;

            case "/elegirPJActualMesa":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los suyos
                    queryPersonajes = em.createNamedQuery("Personaje.findByCreador", Personaje.class);
                    queryPersonajes.setParameter("creador", user);
                    listaPersonajes = queryPersonajes.getResultList();

                    //Mesa
                    id = request.getParameter("id");

                    if (listaPersonajes == null || listaPersonajes.isEmpty()) {
                        vista = "/Usuarios/perfil";
                    } else {
                        //Recogemos los datos
                        numString = request.getParameter("pag");//numero de pag en la que estoy
                        ordenar = request.getParameter("orden");//como ordenar
                        razaString = request.getParameter("raza");//como ordenar
                        claseString = request.getParameter("clase");//como ordenar
                        nivelString = request.getParameter("nivel");//como ordenar

                        //Comprobamos los datos
                        if (ordenar == null) {

                            ordenar = "ordenar1";
                            razaSQL = " ";
                            claseSQL = " ";
                            nivelSQL = " ";
                            numString = "1";
                            num = 0;

                        } else {
                            num = (Integer.valueOf(numString) - 1) * 6;//offset

                            if (razaString.equals("Raza")) {
                                razaSQL = " ";
                            } else {
                                razaSQL = "AND p.RAZA = '" + razaString + "' ";
                            }
                            if (claseString.equals("Clase")) {
                                claseSQL = " ";
                            } else {
                                claseSQL = "AND p.CLASE = '" + claseString + "' ";
                            }
                            if (nivelString.equals("0")) {
                                nivelSQL = " ";
                            } else {
                                nivelSQL = "AND p.NIVEL = '" + nivelString + "' ";
                            }
                        }

                        System.out.println("Llega pag: " + numString);
                        System.out.println("Llega orden: " + ordenar);
                        System.out.println("Llega raza: " + razaString);
                        System.out.println("Llega clase: " + claseString);
                        System.out.println("Llega nivel: " + nivelString);

                        /////////////////////////////////////
                        ////////NUMERO DE PERSONAJES/////////
                        /////////////////////////////////////
                        sql = "SELECT COUNT(*) FROM PERSONAJE p "
                                + "WHERE p.USUARIO = '" + user.getId() + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL;

                        queryAUX = em.createNativeQuery(sql);
                        result = queryAUX.getSingleResult();

                        //PAGINAS QUE HAY (6 PERSONAJES POR PAGINA)
                        numPag = (((Number) result).intValue() / 7) + 1;

                        switch (ordenar) {
                            case "ordenar1":
                                sql = "SELECT p.* FROM PERSONAJE p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                            case "ordenar2":
                                sql = "SELECT p.* FROM PERSONAJE p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                        }

                        queryAUX = em.createNativeQuery(sql, Personaje.class);
                        listaPersonajes = queryAUX.getResultList();

                        request.setAttribute("listaPersonajes", listaPersonajes);

                        System.out.println("Sale pag:" + numString);
                        System.out.println("Sale orden:" + ordenar);
                        System.out.println("Sale raza:" + razaString);
                        System.out.println("Sale clase:" + claseString);
                        System.out.println("Sale nivel:" + nivelString);
                        System.out.println("Sale npag:" + numPag);

                        queryClases = em.createNamedQuery("Clase.findAll", Clase.class);
                        request.setAttribute("listaClases", queryClases.getResultList());
                        queryRazas = em.createNamedQuery("Raza.findAll", Raza.class);
                        request.setAttribute("listaRazas", queryRazas.getResultList());

                        fotosPersonajes = new ArrayList();

                        for (int i = 0; i < listaPersonajes.size(); i++) {

                            if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                                fotosPersonajes.add("-");
                            } else {
                                fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                            }
                        }

                        request.setAttribute("orden", ordenar);
                        request.setAttribute("filtroRaza", razaString);
                        request.setAttribute("filtroClase", claseString);
                        request.setAttribute("filtroNivel", nivelString);
                        request.setAttribute("urlImagenes", fotosPersonajes);
                        request.setAttribute("pag", numString);//numero de la pag
                        request.setAttribute("numPag", numPag);//numero total de pag
                        request.setAttribute("mesa", id);//Mesa

                        vista = "/WEB-INF/jsp/personajes/personajesPerfilElegirMesa.jsp";
                    }
                }
                break;
            /////////////////////////////////////////
            /////////VALORES POST CREACION///////////
            /////////////////////////////////////////
            case "/personajePerfilAtributos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprobamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();
                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {
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

                            //Lista Atributo
                            request.setAttribute("listaPAtributos", personaje.getPersonajeatributosList());

                            listaValoresSalvacion = new ArrayList();

                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                pjAtr = personaje.getPersonajeatributosList().get(i);

                                switch (pjAtr.getValor()) {
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
                                        break;
                                    default:
                                        numaux = 0;
                                }
                                if (pjAtr.getSalvacion().equals("Si")) {
                                    listaValoresSalvacion.add(String.valueOf(numaux + num));
                                } else {
                                    listaValoresSalvacion.add(String.valueOf(numaux));
                                }
                            }
                            request.setAttribute("listaPAtributosSalvacion", listaValoresSalvacion);
                            request.setAttribute("personaje", personaje);
                            vista = "/WEB-INF/jsp/personajes/personajePerfilAtributos.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajePerfilHabilidades":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprobamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);

                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

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
                                                break;
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
                            request.setAttribute("personaje", personaje);
                            request.setAttribute("listaPHabilidad", personaje.getPersonajehabilidadesList());
                            request.setAttribute("listaValoresHab", listaHabValores);
                            vista = "/WEB-INF/jsp/personajes/personajePerfilHabilidades.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajePerfilCaracteristicas":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprobamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

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
                            request.setAttribute("personajeBC", num);
                            request.setAttribute("personajeImagen", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                            request.setAttribute("personaje", personaje);
                            vista = "/WEB-INF/jsp/personajes/personajePerfilCaracteristicas.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajePerfilRasgos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprobamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

                            //Espacios de conjuros y BC 
                            queryTCNivel = em.createNamedQuery("Tablaclasepornivel.findByClaseNivel", Tablaclasepornivel.class);
                            queryTCNivel.setParameter("clase", personaje.getClase().getId());
                            queryTCNivel.setParameter("nivel", personaje.getNivel());
                            //Rasgos Clase
                            queryUsaClases = em.createNamedQuery("Usaclase.findByClaseNivel", Usaclase.class);
                            queryUsaClases.setParameter("clase", personaje.getClase().getId());
                            queryUsaClases.setParameter("nivel", personaje.getNivel());
                            //Rasgos SubClase
                            queryUsaSubClases = em.createNamedQuery("Usasubclase.findBySubclaseNivel", Usasubclase.class);
                            queryUsaSubClases.setParameter("subclase", personaje.getClase().getId());
                            queryUsaSubClases.setParameter("nivel", personaje.getNivel());

                            try {

                                request.setAttribute("personaje", personaje);

                                tcnivel = queryTCNivel.getSingleResult();
                                usaclase = queryUsaClases.getSingleResult();
                                usasubclase = queryUsaSubClases.getSingleResult();

                                request.setAttribute("pjHechizosClase", tcnivel.getTablaclases().getEspacioshechizosList().get(0));
                                request.setAttribute("pjTablaClase", tcnivel.getTablaclases());

                                request.setAttribute("pjRasgosClase", usaclase.getRasgos());
                                request.setAttribute("pjRasgosSubClase", usasubclase.getRasgos());
                                request.setAttribute("pjRasgosRaza", personaje.getRaza().getRasgosList());
                                request.setAttribute("pjRasgosSubraza", personaje.getSubraza().getRasgosList());
                                request.setAttribute("pjRasgosTrasfondos", personaje.getTrasfondo().getRasgosList());

                                vista = "/WEB-INF/jsp/personajes/personajePerfilRasgos.jsp";
                            } catch (Exception ex) {

                                //Prueba
                                Espaciohechizo example = new Espaciohechizo("", 2, 2, 3, 0, 1, 1, 1, 1, 1);
                                Tablaclases tb = new Tablaclases("2", 2, 2, 2, 2);
                                ArrayList<Rasgo> exampleR = new ArrayList();

                                Rasgo raux = new Rasgo("", "RasgoEjemplo1", "Una decripcion larga y tendida de ejemplo claro");
                                Rasgo raux2 = new Rasgo("", "RasgoEjemplo2", "Una decripcion larga y tendida de ejemplo claro");

                                exampleR.add(raux);
                                exampleR.add(raux2);

                                request.setAttribute("pjHechizosClase", example);
                                request.setAttribute("pjTablaClase", tb);

                                request.setAttribute("pjRasgosClase", exampleR);
                                request.setAttribute("pjRasgosSubClase", exampleR);
                                request.setAttribute("pjRasgosRaza", exampleR);
                                request.setAttribute("pjRasgosSubraza", exampleR);
                                request.setAttribute("pjRasgosTrasfondos", exampleR);

                                ///
                                vista = "/WEB-INF/jsp/personajes/personajePerfilRasgos.jsp";
                            }
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajeAtributos":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

                //Comprobamos si existe
                queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                try {
                    personaje = queryPersonajes.getSingleResult();

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
                    //Lista Atributo
                    request.setAttribute("listaPAtributos", personaje.getPersonajeatributosList());

                    listaValoresSalvacion = new ArrayList();

                    for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                        pjAtr = personaje.getPersonajeatributosList().get(i);

                        switch (pjAtr.getValor()) {
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
                                break;
                            default:
                                numaux = 0;
                        }
                        if (pjAtr.getSalvacion().equals("Si")) {
                            listaValoresSalvacion.add(String.valueOf(numaux + num));
                        } else {
                            listaValoresSalvacion.add(String.valueOf(numaux));
                        }
                    }
                    request.setAttribute("listaPAtributosSalvacion", listaValoresSalvacion);
                    request.setAttribute("personaje", personaje);
                    vista = "/WEB-INF/jsp/personajes/personajeAtributos.jsp";

                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;
            case "/personajeHabilidades":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

                //Comprobamos si existe
                queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                try {
                    personaje = queryPersonajes.getSingleResult();

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
                                        break;
                                    default:
                                        numaux = 0;
                                }

                                if (personajeHabilidad.getCompetencia().equals("Si")) {
                                    numaux = num + personajeAtributo.getValor();
                                }

                                listaHabValores.add(numaux);
                                j = personaje.getPersonajeatributosList().size();
                            }
                        }
                    }

                    request.setAttribute("personaje", personaje);
                    request.setAttribute("listaPHabilidad", personaje.getPersonajehabilidadesList());
                    request.setAttribute("listaValoresHab", listaHabValores);
                    vista = "/WEB-INF/jsp/personajes/personajeHabilidades.jsp";

                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;

            case "/personajeCaracteristicas":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

                //Comprobamos si existe
                queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                try {
                    personaje = queryPersonajes.getSingleResult();

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
                    request.setAttribute("personajeBC", num);
                    request.setAttribute("personajeImagen", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                    request.setAttribute("personaje", personaje);
                    vista = "/WEB-INF/jsp/personajes/personajeCaracteristicas.jsp";

                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;
            case "/personajeRasgos":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

                //Comprobamos si existe
                queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                try {
                    personaje = queryPersonajes.getSingleResult();

                    //Espacios de conjuros y BC 
                    queryTCNivel = em.createNamedQuery("Tablaclasepornivel.findByClaseNivel", Tablaclasepornivel.class);
                    queryTCNivel.setParameter("clase", personaje.getClase().getId());
                    queryTCNivel.setParameter("nivel", personaje.getNivel());
                    //Rasgos Clase
                    queryUsaClases = em.createNamedQuery("Usaclase.findByClaseNivel", Usaclase.class);
                    queryUsaClases.setParameter("clase", personaje.getClase().getId());
                    queryUsaClases.setParameter("nivel", personaje.getNivel());
                    //Rasgos SubClase
                    queryUsaSubClases = em.createNamedQuery("Usasubclase.findBySubclaseNivel", Usasubclase.class);
                    queryUsaSubClases.setParameter("subclase", personaje.getClase().getId());
                    queryUsaSubClases.setParameter("nivel", personaje.getNivel());

                    try {

                        request.setAttribute("personaje", personaje);

                        tcnivel = queryTCNivel.getSingleResult();
                        usaclase = queryUsaClases.getSingleResult();
                        usasubclase = queryUsaSubClases.getSingleResult();

                        request.setAttribute("pjHechizosClase", tcnivel.getTablaclases().getEspacioshechizosList().get(0));
                        request.setAttribute("pjTablaClase", tcnivel.getTablaclases());

                        request.setAttribute("pjRasgosClase", usaclase.getRasgos());
                        request.setAttribute("pjRasgosSubClase", usasubclase.getRasgos());
                        request.setAttribute("pjRasgosRaza", personaje.getRaza().getRasgosList());
                        request.setAttribute("pjRasgosSubraza", personaje.getSubraza().getRasgosList());
                        request.setAttribute("pjRasgosTrasfondos", personaje.getTrasfondo().getRasgosList());

                        vista = "/WEB-INF/jsp/personajes/personajeRasgos.jsp";
                    } catch (Exception ex) {

                        //Prueba
                        Espaciohechizo example = new Espaciohechizo("", 2, 2, 3, 0, 1, 1, 1, 1, 1);
                        Tablaclases tb = new Tablaclases("2", 2, 2, 2, 2);
                        ArrayList<Rasgo> exampleR = new ArrayList();

                        Rasgo raux = new Rasgo("", "RasgoEjemplo1", "Una decripcion larga y tendida de ejemplo claro");
                        Rasgo raux2 = new Rasgo("", "RasgoEjemplo2", "Una decripcion larga y tendida de ejemplo claro");

                        exampleR.add(raux);
                        exampleR.add(raux2);

                        request.setAttribute("pjHechizosClase", example);
                        request.setAttribute("pjTablaClase", tb);

                        request.setAttribute("pjRasgosClase", exampleR);
                        request.setAttribute("pjRasgosSubClase", exampleR);
                        request.setAttribute("pjRasgosRaza", exampleR);
                        request.setAttribute("pjRasgosSubraza", exampleR);
                        request.setAttribute("pjRasgosTrasfondos", exampleR);

                        ///
                        vista = "/WEB-INF/jsp/personajes/personajeRasgos.jsp";
                    }
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;
            case "/personajeDotes":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprobamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {
                            sql = "SELECT d.* FROM DOTES d "
                                    + "LEFT JOIN PERSONAJEDOTES pd on pd.dote = d.id "
                                    + "WHERE pd.personaje = '" + personaje_id + "' "
                                    + "ORDER BY d.NOMBRE DESC ";

                            queryAUX = em.createNativeQuery(sql, Dote.class);
                            listaDotes = queryAUX.getResultList();

                            result = "";

                            for (int i = 0; i < listaDotes.size(); i++) {

                                nombre = listaDotes.get(i).getNombre();

                                result
                                        = result
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
                                    result
                                            = result
                                            + "<li class=\"RequisitoDote\">" + listaRDote.getValor() + "</li>";
                                }

                                queryMDotes = em.createNamedQuery("Mejoradote.findByNombre", Mejoradote.class);
                                queryMDotes.setParameter("nombre", nombre);
                                listaMDotes = queryMDotes.getResultList();

                                for (Mejoradote listaMDote : listaMDotes) {
                                    result
                                            = result
                                            + "<li>" + listaMDote.getValor() + "</li>";
                                }

                                result
                                        = result
                                        + "</ul>"
                                        + "</div>"
                                        + "</div>";
                            }

                            if (result.equals("")) {
                                result
                                        = result
                                        + "<div class=\"ResumenDote\">"
                                        + "<div class=\"tituloDote\">"
                                        + "<h5>"
                                        + "Aún no hay dotes añadidos"
                                        + "</h5></div></div>";
                            }

                            request.setAttribute("id", personaje_id);
                            request.setAttribute("listaDotes", result);
                            vista = "/WEB-INF/jsp/personajes/personajeDotes.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajeEquipo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprovamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

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
                            sql = "SELECT DISTINCT COUNT(*) FROM Equipo e "
                                    + "LEFT JOIN Tienepropiedad tp on tp.equipo = e.id "
                                    + "LEFT JOIN Propiedad p on p.id = tp.propiedad "
                                    + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + personaje_id + "' "
                                    + "WHERE pe.personaje IS NOT NULL "
                                    + tipoSQL
                                    + categoriaSQL
                                    + propiedadSQL;

                            queryAUX = em.createNativeQuery(sql);
                            result = queryAUX.getSingleResult();

                            //PAGINAS QUE HAY (15 OBJETOS POR PAGINA)
                            numPag = (((Number) result).intValue() / 14) + 1;

                            sql = "SELECT DISTINCT * FROM Equipo e "
                                    + "LEFT JOIN Tienepropiedad tp on tp.equipo = e.id "
                                    + "LEFT JOIN Propiedad p on p.id = tp.propiedad "
                                    + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + personaje_id + "' "
                                    + "WHERE pe.personaje IS NOT NULL "
                                    + tipoSQL
                                    + categoriaSQL
                                    + propiedadSQL
                                    + "ORDER BY e.nombre "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";

                            queryAUX = em.createNativeQuery(sql, Equipo.class);
                            listaEquipo = queryAUX.getResultList();

                            //Eliminar duplicados utilizando HashSet
                            hashAuxEquipo = new HashSet<>(listaEquipo);
                            hashAuxEquipo.clear(); //Limpiar el ArrayList
                            listaEquipo.addAll(hashAuxEquipo);

                            request.setAttribute("pag", numString);
                            request.setAttribute("numpag", numPag);
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
                            request.setAttribute("id", personaje_id);

                            vista = "/WEB-INF/jsp/personajes/personajeEquipo.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajeEquipoElegir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprovamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

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
                                    + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + personaje_id + "' "
                                    + "WHERE pe.personaje IS NULL "
                                    + tipoSQL
                                    + categoriaSQL
                                    + propiedadSQL;

                            queryAUX = em.createNativeQuery(sql);
                            result = queryAUX.getSingleResult();

                            //PAGINAS QUE HAY (15 OBJETOS POR PAGINA)
                            numPag = (((Number) result).intValue() / 14) + 1;

                            sql = "SELECT DISTINCT * FROM Equipo e "
                                    + "LEFT JOIN Tienepropiedad tp on tp.equipo = e.id "
                                    + "LEFT JOIN Propiedad p on p.id = tp.propiedad "
                                    + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + personaje_id + "' "
                                    + "WHERE pe.personaje IS NULL "
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
                            request.setAttribute("numpag", numPag);
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
                            request.setAttribute("id", personaje_id);

                            vista = "/WEB-INF/jsp/personajes/personajeEquipoElegir.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;
            case "/personajeEquipoAnadir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("objeto");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();

                    queryEquipo = em.createNamedQuery("Equipo.findById", Equipo.class);
                    queryEquipo.setParameter("id", id);

                    equipo = queryEquipo.getSingleResult();
                    listaEquipo = personaje.getEquipoList();
                    listaEquipo.add(equipo);

                    personaje.setEquipoList(listaEquipo);
                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeEquipoElegir?id=" + personaje.getId();
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;

            case "/personajeEquipoEliminar":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("objeto");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();
                    queryEquipo = em.createNamedQuery("Equipo.findById", Equipo.class);
                    queryEquipo.setParameter("id", id);
                    equipo = queryEquipo.getSingleResult();

                    listaEquipo = personaje.getEquipoList();

                    for (int i = 0; i < listaEquipo.size(); i++) {
                        if (listaEquipo.get(i).getId().equals(equipo.getId())) {
                            listaEquipo.remove(i);
                            i = listaEquipo.size();
                        }
                    }

                    personaje.setEquipoList(listaEquipo);

                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeEquipo?id=" + personaje.getId();
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }

                break;
            case "/personajeHechizos":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprovamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

                            numString = request.getParameter("pag");//numero de pag en la que estoy
                            escuela = request.getParameter("escuela");
                            nivelString = request.getParameter("nivel");
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
                                if (nivelString.equals("Nivel")) {
                                    nivelSQL = " ";
                                } else {
                                    nivelSQL = "AND h.NIVEL = '" + nivelString + "' ";
                                }
                                if (claseH.equals("Clase")) {
                                    claseHSQL = " ";
                                } else {
                                    claseHSQL = "AND c.NOMBRE = '" + claseH + "' ";
                                }

                                if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//TODOS
                                    subtitulo = escuela;
                                } else if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL
                                    subtitulo = escuela;
                                } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE
                                    subtitulo = claseH;
                                } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE
                                    subtitulo = claseH;
                                } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                                    subtitulo = escuela;
                                } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                                    subtitulo = nivelString;
                                } else if (escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//CLASE
                                    subtitulo = claseH;
                                } else {
                                    subtitulo = "Todos";
                                }

                            }

                            /////////////////////////////////
                            ////////NUMERO DE HECHIZOS///////
                            /////////////////////////////////
                            sql = "SELECT DISTINCT COUNT(*) FROM Hechizos h "
                                    + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                                    + "LEFT JOIN Clase c on c.id = lb.clase "
                                    + "LEFT JOIN Personajehechizo ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                                    + "WHERE ph.personaje IS NOT NULL "
                                    + escuelaSQL
                                    + claseHSQL
                                    + nivelSQL;

                            queryAUX = em.createNativeQuery(sql);
                            result = queryAUX.getSingleResult();

                            //PAGINAS QUE HAY (15 HECHIZOS POR PAGINA)
                            numPag = (((Number) result).intValue() / 14) + 1;

                            sql = "SELECT DISTINCT * FROM Hechizos h "
                                    + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                                    + "LEFT JOIN Clase c on c.id = lb.clase "
                                    + "LEFT JOIN Personajehechizo ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                                    + "WHERE ph.personaje IS NOT NULL "
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
                            request.setAttribute("numpag", numPag);

                            request.setAttribute("vEscu", escuela);
                            request.setAttribute("vNiv", nivelString);
                            request.setAttribute("vClas", claseH);

                            request.setAttribute("listaHechizos", listaHechizos);
                            request.setAttribute("subtitulo", subtitulo);
                            request.setAttribute("id", personaje_id);

                            vista = "/WEB-INF/jsp/personajes/personajeHechizos.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;

            case "/personajeHechizosElegir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los datos
                    personaje_id = request.getParameter("id");

                    //Comprovamos si es tuyo
                    queryPersonajes = em.createNamedQuery("Personaje.findById", Personaje.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    try {
                        personaje = queryPersonajes.getSingleResult();

                        if (!personaje.getUsuario().getId().equals(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

                            numString = request.getParameter("pag");//numero de pag en la que estoy
                            escuela = request.getParameter("escuela");
                            nivelString = request.getParameter("nivel");
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
                                if (nivelString.equals("Nivel")) {
                                    nivelSQL = " ";
                                } else {
                                    nivelSQL = "AND h.NIVEL = '" + nivelString + "' ";
                                }
                                if (claseH.equals("Clase")) {
                                    claseHSQL = " ";
                                } else {
                                    claseHSQL = "AND c.NOMBRE = '" + claseH + "' ";
                                }

                                if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//TODOS
                                    subtitulo = escuela;
                                } else if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL
                                    subtitulo = escuela;
                                } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE
                                    subtitulo = claseH;
                                } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE
                                    subtitulo = claseH;
                                } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                                    subtitulo = escuela;
                                } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                                    subtitulo = nivelString;
                                } else if (escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//CLASE
                                    subtitulo = claseH;
                                } else {
                                    subtitulo = "Todos";
                                }

                            }

                            /////////////////////////////////
                            ////////NUMERO DE HECHIZOS///////
                            /////////////////////////////////
                            sql = "SELECT COUNT(DISTINCT h.id) FROM Hechizos h "
                                    + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                                    + "LEFT JOIN Clase c on c.id = lb.clase "
                                    + "LEFT JOIN Personajehechizo ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                                    + "WHERE ph.personaje IS NULL "
                                    + escuelaSQL
                                    + claseHSQL
                                    + nivelSQL;

                            queryAUX = em.createNativeQuery(sql);
                            result = queryAUX.getSingleResult();

                            //PAGINAS QUE HAY (15 HECHIZOS POR PAGINA)
                            numPag = (((Number) result).intValue() / 14) + 1;

                            sql = "SELECT h.* FROM Hechizos h "
                                    + "LEFT JOIN Listahechizo lb on lb.hechizo = h.id "
                                    + "LEFT JOIN Clase c on c.id = lb.clase "
                                    + "LEFT JOIN Personajehechizo ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                                    + "WHERE ph.personaje IS NULL "
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
                            request.setAttribute("numpag", numPag);

                            request.setAttribute("vEscu", escuela);
                            request.setAttribute("vNiv", nivelString);
                            request.setAttribute("vClas", claseH);

                            request.setAttribute("listaHechizos", listaHechizos);
                            request.setAttribute("subtitulo", subtitulo);
                            request.setAttribute("id", personaje_id);

                            vista = "/WEB-INF/jsp/personajes/personajeHechizosElegir.jsp";
                        }
                    } catch (Exception ex) {
                        vista = "/Principal/inicio";
                    }
                }
                break;

            case "/personajeHechizoAnadir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("hechizo");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();
                    queryHechizos = em.createNamedQuery("Hechizo.findById", Hechizo.class);
                    queryHechizos.setParameter("id", id);

                    hechizo = queryHechizos.getSingleResult();
                    listaHechizos = personaje.getHechizosList();
                    listaHechizos.add(hechizo);

                    personaje.setHechizosList(listaHechizos);
                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeHechizosElegir?id=" + personaje.getId();

                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;
            case "/personajeHechizoEliminar":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                id = request.getParameter("hechizo");
                personaje_id = request.getParameter("personaje");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();
                    queryHechizos = em.createNamedQuery("Hechizo.findById", Hechizo.class);
                    queryHechizos.setParameter("id", id);
                    hechizo = queryHechizos.getSingleResult();

                    listaHechizos = personaje.getHechizosList();

                    for (int i = 0; i < listaHechizos.size(); i++) {
                        if (listaHechizos.get(i).getId().equals(hechizo.getId())) {
                            listaHechizos.remove(i);
                            i = listaHechizos.size();
                        }
                    }

                    personaje.setHechizosList(listaHechizos);

                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeHechizos?id=" + personaje.getId();
                } catch (Exception ex) {
                    vista = "/Principal/inicio";
                }
                break;
            //////////////////////////////////////////////
            /////////SUBIR DE NIVEL/BAJAR NIVEL///////////
            //////////////////////////////////////////////
            case "/personajeSubirNivel":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                nombre = request.getParameter("eleccionDoteAtr");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();

                    atributo1 = request.getParameter("atributo1");
                    atributo2 = request.getParameter("atributo2");
                    String dote_id = request.getParameter("dote");
                    String subclase_id = request.getParameter("subclase");
                    String vidaString = request.getParameter("personaje_vida");
                    int vida = Integer.valueOf(vidaString);

                    //Añadimos el dote
                    if (nombre.equals("Dote")) {
                        queryDotes = em.createNamedQuery("Dote.findById", Dote.class);
                        queryDotes.setParameter("id", dote_id);
                        dote = queryDotes.getSingleResult();
                        //Añadimos sus atributos
                        for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                            personajeAtributo = personaje.getPersonajeatributosList().get(i);
                            for (int j = 0; j < dote.getAtributosList().size(); j++) {
                                atributo = dote.getAtributosList().get(j);
                                if (atributo.getId().equals(personajeAtributo.getAtributos().getId())) {
                                    personaje.getPersonajeatributosList().get(i).setValor((personajeAtributo.getValor() + 1));
                                }
                            }
                        }
                        //Añadimos sus habilidades
                        for (int i = 0; i < personaje.getPersonajehabilidadesList().size(); i++) {
                            personajeHabilidad = personaje.getPersonajehabilidadesList().get(i);
                            for (int j = 0; j < dote.getHabilidadesList().size(); j++) {
                                habilidad = dote.getHabilidadesList().get(j);
                                if (habilidad.getId().equals(personajeHabilidad.getHabilidades().getId())) {
                                    personaje.getPersonajehabilidadesList().get(i).setCompetencia("Si");
                                }
                            }

                        }
                        personaje.getDotesList().add(dote);
                    } else {//Añadimos los valores a los atributos
                        for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                            personajeAtributo = personaje.getPersonajeatributosList().get(i);
                            if (atributo1.equals(personajeAtributo.getAtributos().getNombre())) {
                                personaje.getPersonajeatributosList().get(i).setValor((personajeAtributo.getValor() + 1));
                            }
                            if (atributo2.equals(personajeAtributo.getAtributos().getNombre())) {
                                personaje.getPersonajeatributosList().get(i).setValor((personajeAtributo.getValor() + 2));
                            }
                        }
                    }
                    //Añadimos la subclase si es necesario
                    if (subclase_id != null) {
                        querySubclases = em.createNamedQuery("Subclase.findById", Subclase.class);
                        querySubclases.setParameter("id", dote_id);
                        personaje.setSubclase(querySubclases.getSingleResult());
                    }

                    //Subimos la vida
                    personaje.setPvida((personaje.getPvida() + vida));
                    personaje.setPvidaactuales((personaje.getPvidaactuales() + vida));

                    //Subimos el nivel
                    personaje.setNivel((personaje.getNivel() + 1));

                    updatePersonajes(personaje);

                    vista = "/Personajes/personajePerfil?id=" + personaje_id;
                } catch (Exception ex) {
                    vista = "/Formularios/personajeSubirNivel?id=" + personaje_id;
                }
                break;
            case "/personajeBajarNivel":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuario) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");

                queryPersonajes = em.createNamedQuery("Personaje.findByIDCreador", Personaje.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                try {
                    personaje = queryPersonajes.getSingleResult();

                    //Borramos dotes
                    personaje.setDotesList(null);
                    //Reiniciamos los atributos al original
                    for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                        personaje.getPersonajeatributosList().get(i).setValor(personaje.getPersonajeatributosoriginalList().get(i).getValor());
                        personaje.getPersonajeatributosList().get(i).setSalvacion(personaje.getPersonajeatributosoriginalList().get(i).getSalvacion());
                    }
                    //Borramos subclase si procede
                    if (personaje.getClase().getNivelsubclase() != 1) {
                        personaje.setSubclase(null);
                    }

                    //Bajamos la vida
                    personaje.setPvida((Integer.parseInt(personaje.getClase().getDpg().substring(1))));
                    personaje.setPvidaactuales((Integer.parseInt(personaje.getClase().getDpg().substring(1))));
                    personaje.setNivel(1);

                    updatePersonajes(personaje);

                    vista = "/Personajes/personajePerfil?id=" + personaje_id;
                } catch (Exception ex) {
                    vista = "/Personajes/personajePerfil?id=" + personaje_id;;
                }
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(vista);

        rd.forward(request, response);
    }

    //Lanza error por escribir un valor malicioso
    protected void comprobarCadena(String Cadena, String Mensaje) throws Exception {

        if (Cadena.toUpperCase().contains("UPDATE") || Cadena.toUpperCase().contains("CREATE")
                || Cadena.toUpperCase().contains("DELETE") || Cadena.toUpperCase().contains("SELECT")
                || Cadena.toUpperCase().contains("DROP")) {
            throw new Exception(Mensaje);
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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePersonajes(Object object) {
        try {
            utx.begin();
            object = (Personaje) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePersonajes(Object object) {
        try {
            utx.begin();
            em.merge((Personaje) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

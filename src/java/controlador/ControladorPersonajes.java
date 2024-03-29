package controlador;

import entidades.Atributos;
import entidades.Clases;
import entidades.Equipo;
import entidades.Habilidades;
import entidades.Hechizos;
import entidades.Personajeatributos;
import entidades.Personajehabilidades;
import entidades.Personajes;
import entidades.Propiedades;
import entidades.Razas;
import entidades.Subclases;
import entidades.Subrazas;
import entidades.Trasfondos;
import entidades.Usuarios;
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
import javax.servlet.http.Part;

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

        TypedQuery<Usuarios> queryUsuarios;
        TypedQuery<Clases> queryClases;
        TypedQuery<Subclases> querySubclases;
        TypedQuery<Razas> queryRazas;
        TypedQuery<Subrazas> querySubrazas;
        TypedQuery<Trasfondos> queryTrasfondos;
        TypedQuery<Personajes> queryPersonajes;
        TypedQuery<Atributos> queryAtributos;
        TypedQuery<Habilidades> queryHabilidades;
        TypedQuery<Equipo> queryEquipo;
        TypedQuery<Hechizos> queryHechizos;

        Query queryAUX;

        List<Personajes> listaPersonajes;
        List<Usuarios> listaUsuariosAmigos;
        List<Usuarios> listaUsuarios;
        List<String> fotosPersonajes;
        List<Atributos> listaAtributos;
        List<Atributos> listaAtributosAux;
        List<Personajeatributos> listaPersonajeAtributos;
        List<Habilidades> listaHabilidades;
        List<Personajehabilidades> listaPersonajeHabilidades;
        List<Equipo> listaEquipo;
        List<Hechizos> listaHechizos;
        List<List<Propiedades>> listalistaPropiedades;

        HashSet<Hechizos> hashAuxHechizos;
        HashSet<Equipo> hashAuxEquipo;

        Usuarios user;
        Usuarios useraux;
        Personajes personaje;
        Personajes personajeaux;
        Clases clase;
        Subclases subclase;
        Razas raza;
        Subrazas subraza;
        Trasfondos transfondo;
        Atributos atributo;
        Personajeatributos personajeAtributo;
        Habilidades habilidad;
        Personajehabilidades personajeHabilidad;
        Equipo equipo;
        Hechizos hechizo;

        String id;

        String ordenar;
        String razaString;
        String claseString;
        String nivelString;
        String numString;
        String razaSQL;
        String claseSQL;
        String nivelSQL;

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
        String titulo;
        String subtitulo;

        Part filePart;
        InputStream contenidoImagen;
        byte[] imageData;

        int num;
        int numPag;
        int nivel;

        String sql;

        switch (accion) {
            case "/crearpersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

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
                        if (personaje_nombre.toUpperCase().contains("UPDATE") || personaje_nombre.toUpperCase().contains("CREATE")
                                || personaje_nombre.toUpperCase().contains("DELETE") || personaje_nombre.toUpperCase().contains("SELECT")
                                || personaje_nombre.toUpperCase().contains("DROP")) {
                            throw new Exception("El Nombre no es válido");
                        }

                        queryPersonajes = em.createNamedQuery("Personajes.findByNombreCreador", Personajes.class);
                        queryPersonajes.setParameter("nombre", personaje_nombre);
                        queryPersonajes.setParameter("creador", user);
                        listaPersonajes = queryPersonajes.getResultList();

                        if (!listaPersonajes.isEmpty()) {
                            throw new Exception("Solo puedes tener un personaje con ese nombre ");
                        }
                        //////////////////
                        //////CLASE///////
                        //////////////////
                        queryClases = em.createNamedQuery("Clases.findByNombre", Clases.class);
                        queryClases.setParameter("nombre", personaje_clase);
                        clase = queryClases.getSingleResult();
                        //////////////////
                        ///////RAZA///////
                        //////////////////
                        queryRazas = em.createNamedQuery("Razas.findByNombre", Razas.class);
                        queryRazas.setParameter("nombre", personaje_raza);
                        raza = queryRazas.getSingleResult();
                        //////////////////
                        //////SUBRAZA/////
                        //////////////////
                        querySubrazas = em.createNamedQuery("Subrazas.findByNombre", Subrazas.class);
                        querySubrazas.setParameter("nombre", personaje_subraza);
                        subraza = querySubrazas.getSingleResult();
                        //////////////////
                        ////TRASFONDO/////
                        //////////////////
                        queryTrasfondos = em.createNamedQuery("Trasfondos.findByNombre", Trasfondos.class);
                        queryTrasfondos.setParameter("nombre", personaje_trasfondo);
                        transfondo = queryTrasfondos.getSingleResult();

                        //////////////////////////
                        //////////CREAMOS/////////
                        //////////////////////////
                        personaje = new Personajes(personaje_nombre, personaje_alineamiento, 1, Integer.parseInt(clase.getDpg().substring(1)),
                                Integer.parseInt(clase.getDpg().substring(1)), 10, user);

                        personaje.setClase(clase);
                        personaje.setRaza(raza);
                        personaje.setSubraza(subraza);
                        personaje.setTrasfondo(transfondo);

                        if (!personaje_subclase.equalsIgnoreCase("-")) {
                            querySubclases = em.createNamedQuery("Subclases.findByNombre", Subclases.class);
                            querySubclases.setParameter("nombre", personaje_subclase);
                            subclase = querySubclases.getSingleResult();
                            personaje.setSubclase(subclase);
                        }
                        if (personaje_edad != null && !personaje_edad.equals("")) {
                            personaje.setEdad(Integer.valueOf(personaje_edad));
                        }
                        if (personaje_apariencia != null && !personaje_apariencia.equals("")) {
                            personaje.setApariencia(personaje_apariencia);
                        }
                        if (personaje_rasgos != null && !personaje_rasgos.equals("")) {
                            personaje.setRaspersonalidad(personaje_rasgos);
                        }
                        if (personaje_defectos != null && !personaje_defectos.equals("")) {
                            personaje.setDefectos(personaje_defectos);
                        }
                        if (personaje_vinculos != null && !personaje_vinculos.equals("")) {
                            personaje.setVinculos(personaje_vinculos);
                        }
                        if (personaje_idiomas != null && !personaje_idiomas.equals("")) {
                            personaje.setIdiomas(personaje_idiomas);
                        }
                        if (personaje_historia != null && !personaje_historia.equals("")) {
                            personaje.setHistoria(personaje_historia);
                        }

                        persist(personaje);

                        //////////////////////
                        //////ATRIBUTOS///////
                        //////////////////////
                        listaPersonajeAtributos = new ArrayList();

                        queryAtributos = em.createNamedQuery("Atributos.findAll", Atributos.class);
                        listaAtributos = queryAtributos.getResultList();

                        boolean encontrado;
                        int index;

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

                            if (atributos != null) {
                                while (encontrado == false && index < atributos.length) {
                                    if (atributo.getNombre().equals(atributos[index])) {
                                        encontrado = true;
                                        valorAtributo = valorAtributo + 1;
                                    }
                                    index++;
                                }
                            } else if (atributo1 != null) {
                                if (atributo.getNombre().equals(atributo1)) {
                                    valorAtributo = valorAtributo + 1;
                                }
                                if (atributo.getNombre().equals(atributo2)) {
                                    valorAtributo = valorAtributo + 2;
                                }
                            } else {
                                while (encontrado == false && index < subraza.getSumarazaList().size()) {
                                    if (atributo.getId().equals(subraza.getSumarazaList().get(index).getAtributoID())) {
                                        encontrado = true;
                                        valorAtributo = valorAtributo + Integer.valueOf(subraza.getSumarazaList().get(index).getModificador());
                                    }
                                    index++;
                                }
                            }

                            personajeAtributo = new Personajeatributos(personaje.getId(), atributo.getId(),
                                    valorAtributo, "No");

                            listaPersonajeAtributos.add(personajeAtributo);
                        }

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
                        }

                        personaje.setPersonajeatributosList(listaPersonajeAtributos);

                        //////////////////////
                        /////HABILIDADES//////
                        //////////////////////
                        listaPersonajeHabilidades = new ArrayList();

                        queryHabilidades = em.createNamedQuery("Habilidades.findAll", Habilidades.class);
                        listaHabilidades = queryHabilidades.getResultList();

                        for (int i = 0; i < listaHabilidades.size(); i++) {

                            habilidad = listaHabilidades.get(i);
                            index = 0;
                            encontrado = false;

                            personajeHabilidad = new Personajehabilidades(personaje.getId(), habilidad.getId(), "No");

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
            case "/modificarPersonajeNombre":
                break;
            case "/modificarPersonajeHabilidades":
                break;
            case "/modificarPersonajeAtributos":
                break;
            case "/modificarPersonajeExtra":
                break;
            case "/eliminarPersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    personaje_id = request.getParameter("id");

                    queryPersonajes = em.createNamedQuery("Personajes.findById", Personajes.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    personaje = queryPersonajes.getSingleResult();

                    //Comprobamos que es tuyo
                    if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                        vista = "/Principal/inicio";
                    } else {
                        deletePersonajes(personaje);
                        vista = "/Personajes/personajesPerfil";
                    }
                }

                break;

            case "/copiarPersonaje":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {

                    //Tienes el limite
                    if (user.getPersonajesList().size() == 10) {
                        vista = "/WEB-INF/jsp/personajes/personajesPerfil.jsp";
                    } else {
                        personaje_id = request.getParameter("id");

                        queryPersonajes = em.createNamedQuery("Personajes.findById", Personajes.class);
                        queryPersonajes.setParameter("id", personaje_id);
                        personaje = queryPersonajes.getSingleResult();

                        //Comprobamos que es tuyo
                        if (!personaje.getUsuario().getId().equalsIgnoreCase(user.getId())) {
                            vista = "/Principal/inicio";
                        } else {

                            queryPersonajes = em.createNamedQuery("Personajes.findByCreador", Personajes.class);
                            queryPersonajes.setParameter("creador", user);

                            personajeaux = new Personajes(personaje, user, queryPersonajes.getResultList());
                            persist(personajeaux);

                            listaPersonajeAtributos = new ArrayList();
                            listaPersonajeHabilidades = new ArrayList();

                            for (int i = 0; i < personaje.getPersonajeatributosList().size(); i++) {
                                personajeAtributo = personaje.getPersonajeatributosList().get(i);
                                listaPersonajeAtributos.add(new Personajeatributos(personajeaux.getId(), personajeAtributo.getAtributos().getId(),
                                        personajeAtributo.getValor(), personajeAtributo.getSalvacion()));

                            }
                            for (int i = 0; i < personaje.getPersonajehabilidadesList().size(); i++) {
                                personajeHabilidad = personaje.getPersonajehabilidadesList().get(i);
                                listaPersonajeHabilidades.add(new Personajehabilidades(personajeaux.getId(), personajeHabilidad.getHabilidades().getId(),
                                        personajeHabilidad.getCompetencia()));
                            }
                            personajeaux.setPersonajeatributosList(listaPersonajeAtributos);
                            personajeaux.setPersonajehabilidadesList(listaPersonajeHabilidades);

                            vista = "/Personajes/personajesPerfil";
                        }
                    }
                }

                break;
            case "/personajes":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

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
                sql = "SELECT COUNT(*) FROM PERSONAJES p "
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
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL
                                + "ORDER BY  p.NOMBRE ASC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                    case "ordenar2":
                        sql = "SELECT p.* FROM PERSONAJES p "
                                + "WHERE p.USUARIO <> '" + id + "' "
                                + nivelSQL
                                + claseSQL
                                + razaSQL
                                + "ORDER BY  p.NOMBRE DESC "
                                + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                        break;
                }

                queryAUX = em.createNativeQuery(sql, Personajes.class);
                listaPersonajes = queryAUX.getResultList();

                fotosPersonajes = new ArrayList();
                listaUsuarios = new ArrayList();

                for (int i = 0; i < listaPersonajes.size(); i++) {

                    listaUsuarios.add(listaPersonajes.get(i).getUsuario());

                    if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                        fotosPersonajes.add("-");
                    } else {
                        fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                    }
                }

                request.setAttribute("listaPersonajes", listaPersonajes);
                request.setAttribute("listaCreador", listaUsuarios);

                System.out.println("Sale pag:" + numString);
                System.out.println("Sale orden:" + ordenar);
                System.out.println("Sale raza:" + razaString);
                System.out.println("Sale clase:" + claseString);
                System.out.println("Sale nivel:" + nivelString);
                System.out.println("Sale npag:" + numPag);

                queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                request.setAttribute("listaClases", queryClases.getResultList());
                queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
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
                user = (Usuarios) session.getAttribute("user");

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
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
                            + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
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
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "AND p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY  p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "AND p.USUARIO <> '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();
                    listaUsuarios = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {

                        listaUsuarios.add(listaPersonajes.get(i).getUsuario());

                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    request.setAttribute("listaPersonajes", listaPersonajes);
                    request.setAttribute("listaCreador", listaUsuarios);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
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
                user = (Usuarios) session.getAttribute("user");

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
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
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
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();
                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
                    request.setAttribute("listaRazas", queryRazas.getResultList());
                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
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
                user = (Usuarios) session.getAttribute("user");

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
                    sql = "SELECT COUNT(*) FROM PERSONAJES p "
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
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelSQL
                                    + claseSQL
                                    + razaSQL
                                    + "ORDER BY p.NOMBRE DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    request.setAttribute("listaPersonajes", listaPersonajes);

                    System.out.println("Sale pag:" + numString);
                    System.out.println("Sale orden:" + ordenar);
                    System.out.println("Sale raza:" + razaString);
                    System.out.println("Sale clase:" + claseString);
                    System.out.println("Sale nivel:" + nivelString);
                    System.out.println("Sale npag:" + numPag);

                    queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                    request.setAttribute("listaClases", queryClases.getResultList());
                    queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
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
                queryPersonajes = em.createNamedQuery("Personajes.findById", Personajes.class);
                queryPersonajes.setParameter("id", id);
                personaje = queryPersonajes.getResultList().get(0);

                if (personaje == null) {
                    vista = "/Principal/inicio";
                } else {
                    request.setAttribute("personaje", personaje);
                    request.setAttribute("imagenpersonaje", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                    vista = "/WEB-INF/jsp/personajes/personaje.jsp";
                }

                break;
            case "/personajePerfil":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");
                //Recogemos los datos
                id = request.getParameter("id");

                //Comprobamos que sea tuyo
                queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                queryPersonajes.setParameter("id", id);
                queryPersonajes.setParameter("creador", user);
                personaje = queryPersonajes.getResultList().get(0);

                if (personaje == null) {
                    vista = "/Principal/inicio";
                } else {
                    request.setAttribute("personaje", personaje);
                    request.setAttribute("imagenpersonaje", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                    vista = "/WEB-INF/jsp/personajes/personajePerfil.jsp";
                }

                break;
            case "/personajeAmigo":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("id");
                id = request.getParameter("amigo");

                queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                queryUsuarios.setParameter("id", id);
                useraux = queryUsuarios.getResultList().get(0);
                if (useraux == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Comprobamos que sea suyo
                    queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                    queryPersonajes.setParameter("id", personaje_id);
                    queryPersonajes.setParameter("creador", useraux);
                    personaje = queryPersonajes.getResultList().get(0);

                    if (personaje == null) {
                        vista = "/Principal/inicio";
                    } else {
                        request.setAttribute("personaje", personaje);
                        request.setAttribute("imagenpersonaje", "/TFG/Imagenes/mostrarImagenPersonaje?id=" + personaje.getId());
                        vista = "/WEB-INF/jsp/personajes/personajeAmigo.jsp";
                    }
                }
                break;
            case "/elegirPJActual":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                if (user == null) {
                    vista = "/Principal/inicio";
                } else {
                    //Recogemos los suyos
                    queryPersonajes = em.createNamedQuery("Personajes.findByCreador", Personajes.class);
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
                        sql = "SELECT COUNT(*) FROM PERSONAJES p "
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
                                sql = "SELECT p.* FROM PERSONAJES p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                            case "ordenar2":
                                sql = "SELECT p.* FROM PERSONAJES p "
                                        + "WHERE p.USUARIO = '" + user.getId() + "' "
                                        + nivelSQL
                                        + claseSQL
                                        + razaSQL
                                        + "ORDER BY p.NOMBRE DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 6 ROWS ONLY";
                                break;
                        }

                        queryAUX = em.createNativeQuery(sql, Personajes.class);
                        listaPersonajes = queryAUX.getResultList();

                        request.setAttribute("listaPersonajes", listaPersonajes);

                        System.out.println("Sale pag:" + numString);
                        System.out.println("Sale orden:" + ordenar);
                        System.out.println("Sale raza:" + razaString);
                        System.out.println("Sale clase:" + claseString);
                        System.out.println("Sale nivel:" + nivelString);
                        System.out.println("Sale npag:" + numPag);

                        queryClases = em.createNamedQuery("Clases.findAll", Clases.class);
                        request.setAttribute("listaClases", queryClases.getResultList());
                        queryRazas = em.createNamedQuery("Razas.findAll", Razas.class);
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
            case "/personajeDotes":
                break;
            case "/personajeDotesElegir":
                break;
            case "/personajeEquipo":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

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
                        + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
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
                        + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
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
                break;
            case "/personajeEquipoElegir":
                //Recogemos los datos
                personaje_id = request.getParameter("id");

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
                        + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
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
                        + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                        + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
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
                break;
            case "/personajeEquipoAnadir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("objeto");

                queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                personaje = queryPersonajes.getResultList().get(0);
                if (personaje == null) {
                    vista = "/Principal/Inicio";
                } else {
                    queryEquipo = em.createNamedQuery("Equipo.findById", Equipo.class);
                    queryEquipo.setParameter("id", id);

                    equipo = queryEquipo.getSingleResult();
                    listaEquipo = personaje.getEquipoList();
                    listaEquipo.add(equipo);

                    personaje.setEquipoList(listaEquipo);
                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeEquipoElegir?id=" + personaje.getId();
                }
                break;
            case "/personajeEquipoEliminar":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("objeto");

                queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                personaje = queryPersonajes.getResultList().get(0);
                if (personaje == null) {
                    vista = "/Principal/Inicio";
                } else {
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

                    vista = "/Personajes/personajesEquipo?id=" + personaje.getId();
                }
                break;
            case "/personajeHechizos":

                //Recogemos los datos
                personaje_id = request.getParameter("id");

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
                        + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clases c on c.id = lb.clase "
                        + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                        + "WHERE ph.personaje IS NOT NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (15 HECHIZOS POR PAGINA)
                numPag = (((Number) result).intValue() / 14) + 1;

                sql = "SELECT DISTINCT * FROM Hechizos h "
                        + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clases c on c.id = lb.clase "
                        + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                        + "WHERE ph.personaje IS NOT NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL
                        + "ORDER BY h.nombre "
                        + "OFFSET " + num + " ROWS FETCH NEXT 15 ROWS ONLY";

                queryAUX = em.createNativeQuery(sql, Hechizos.class);
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
                break;
            case "/personajeHechizosElegir":

                //Recogemos los datos
                personaje_id = request.getParameter("id");

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
                        + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clases c on c.id = lb.clase "
                        + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                        + "WHERE ph.personaje IS NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL;

                queryAUX = em.createNativeQuery(sql);
                result = queryAUX.getSingleResult();

                //PAGINAS QUE HAY (15 HECHIZOS POR PAGINA)
                numPag = (((Number) result).intValue() / 14) + 1;

                sql = "SELECT h.* FROM Hechizos h "
                        + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                        + "LEFT JOIN Clases c on c.id = lb.clase "
                        + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + personaje_id + "' "
                        + "WHERE ph.personaje IS NULL "
                        + escuelaSQL
                        + claseHSQL
                        + nivelSQL
                        + "ORDER BY h.nombre "
                        + "OFFSET " + num + " ROWS FETCH NEXT 15 ROWS ONLY";

                queryAUX = em.createNativeQuery(sql, Hechizos.class);
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
                break;
            case "/personajeHechizoAnadir":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("hechizo");

                queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                personaje = queryPersonajes.getResultList().get(0);
                if (personaje == null) {
                    vista = "/Principal/Inicio";
                } else {
                    queryHechizos = em.createNamedQuery("Hechizos.findById", Hechizos.class);
                    queryHechizos.setParameter("id", id);

                    hechizo = queryHechizos.getSingleResult();
                    listaHechizos = personaje.getHechizosList();
                    listaHechizos.add(hechizo);

                    personaje.setHechizosList(listaHechizos);
                    updatePersonajes(personaje);

                    vista = "/Personajes/personajeHechizosElegir?id=" + personaje.getId();
                }
                break;
            case "/personajeHechizoEliminar":
                /////////////////////////
                /////////SESION//////////
                /////////////////////////
                session = request.getSession();
                user = (Usuarios) session.getAttribute("user");

                //Recogemos los datos
                personaje_id = request.getParameter("personaje");
                id = request.getParameter("hechizo");

                queryPersonajes = em.createNamedQuery("Personajes.findByIDCreador", Personajes.class);
                queryPersonajes.setParameter("id", personaje_id);
                queryPersonajes.setParameter("creador", user);

                personaje = queryPersonajes.getResultList().get(0);
                if (personaje == null) {
                    vista = "/Principal/Inicio";
                } else {
                    queryHechizos = em.createNamedQuery("Hechizos.findById", Hechizos.class);
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
            object = (Personajes) em.merge(object);
            em.remove(object);
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

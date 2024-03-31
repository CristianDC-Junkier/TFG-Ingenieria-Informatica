package controlador;

import entidades.Atributos;
import entidades.Clases;
import entidades.Equipo;
import entidades.Hechizos;
import entidades.Mensajesamigos;
import entidades.Mesas;
import entidades.Monstruos;
import entidades.Personajes;
import entidades.Pertenecemesa;
import entidades.Razas;
import entidades.Subclases;
import entidades.Subrazas;
import entidades.Usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@WebServlet(name = "ControladorPeticionesAJAX", urlPatterns = {"/ControladorPeticionesAJAX/*"})
public class ControladorPeticionesAJAX extends HttpServlet {

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
            String resultado = "";

            HttpSession session;

            Usuarios user;
            Usuarios useraux;
            Mensajesamigos MEAux;
            Mensajesamigos MRAux;
            Hechizos hechizoAux;
            Monstruos monstruoAux;
            Equipo equipoAux;
            Atributos Atributo;

            TypedQuery<Usuarios> queryUsuarios;
            TypedQuery<Pertenecemesa> queryPMesas;
            TypedQuery<Mensajesamigos> queryMensajesAmigos;
            TypedQuery<Hechizos> queryHechizos;
            TypedQuery<Monstruos> queryMonstruos;
            TypedQuery<Equipo> queryEquipo;
            TypedQuery<Razas> queryRazas;
            TypedQuery<Clases> queryClases;
            TypedQuery<Subclases> querySubClases;
            TypedQuery<Subrazas> querySubRazas;
            TypedQuery<Atributos> queryAtributos;

            Query queryAUX;

            List<Usuarios> listaUsuarios;
            List<Pertenecemesa> listaPerteneceMesa;
            List<Mesas> listaMesas;
            List<Mensajesamigos> listaMensajesEnviados;
            List<Mensajesamigos> ListaMensajesRecibidos;
            List<Mensajesamigos> ListaMensajesOrdenados;
            List<Hechizos> listaHechizos;
            List<Monstruos> listaMonstruos;
            List<Equipo> listaEquipo;
            List<Razas> listaRazas;
            List<Clases> listaClases;
            List<Atributos> listaAtributos;
            List<Subclases> listaSubClases;
            List<Subrazas> listaSubRazas;
            List<Personajes> listaPersonajes;
            List<String> pertenecemesaUsuarios;
            List<Integer> listaCantidad;
            List<String> fotosMesas;
            List<String> fotosPersonajes;

            int contadorEnviados = 0;
            int contadorRecibidos = 0;
            int vfecha;
            boolean terminadaMensajesAmigos = false;
            Date fechaActual;
            LocalDateTime fechaLimite;

            String nombre;
            String id;
            String ordenar;
            String mesa;
            String lleno;

            String escuela;
            String claseH;
            String nivelString;

            String tipo;
            String vd;

            String categoria;
            String propiedad;

            Clases Clase;
            Razas Raza;
            Subrazas Subraza;

            String raza;
            String clase;
            String numString;
            int nivel;

            int num = 0; //offset
            int cantidad;
            boolean novalido;
            boolean encontrado;

            int cont;

            HashSet<Hechizos> hashAuxHechizos;
            HashSet<Equipo> hashAuxEquipo;
            HashSet<Monstruos> hashAuxMonstruos;

            String sql = "";

            System.out.println("PeticionAJAX entra: " + accion);

            switch (accion) {
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////CHATS///////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/ChatCarga":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (nombre.equalsIgnoreCase("-1")) {

                    } else {
                        queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                        queryUsuarios.setParameter("apodo", nombre);
                        useraux = queryUsuarios.getSingleResult();

                        queryMensajesAmigos = em.createNamedQuery("Mensajesamigos.findByEscritorReceptor", Mensajesamigos.class);
                        queryMensajesAmigos.setParameter("escritor", user);
                        queryMensajesAmigos.setParameter("receptor", useraux);
                        listaMensajesEnviados = queryMensajesAmigos.getResultList();

                        queryMensajesAmigos = em.createNamedQuery("Mensajesamigos.findByReceptorEscritor", Mensajesamigos.class);
                        queryMensajesAmigos.setParameter("escritor", useraux);
                        queryMensajesAmigos.setParameter("receptor", user);
                        ListaMensajesRecibidos = queryMensajesAmigos.getResultList();

                        ListaMensajesOrdenados = new ArrayList();

                        if (!listaMensajesEnviados.isEmpty() && !ListaMensajesRecibidos.isEmpty()) {

                            while (terminadaMensajesAmigos == false) {

                                MEAux = listaMensajesEnviados.get(contadorEnviados);
                                MRAux = ListaMensajesRecibidos.get(contadorRecibidos);
                                vfecha = MEAux.getFecha().compareTo(MRAux.getFecha());// Menor a 0 es antes Mayor a 0 es después

                                if (vfecha == 0) {//misma fecha = antes recibido
                                    contadorRecibidos++;
                                    ListaMensajesOrdenados.add(MRAux);

                                } else if (vfecha < 0) {//antes el enviado
                                    contadorEnviados++;
                                    ListaMensajesOrdenados.add(MEAux);

                                } else if (vfecha > 0) {//antes el recibido
                                    contadorRecibidos++;
                                    ListaMensajesOrdenados.add(MRAux);
                                }

                                if (contadorEnviados == listaMensajesEnviados.size()) {
                                    terminadaMensajesAmigos = true;
                                } else if (contadorRecibidos == ListaMensajesRecibidos.size()) {
                                    terminadaMensajesAmigos = true;
                                }
                            }
                        }
                        //Por si quedan en alguna de las dos listas
                        while (contadorEnviados != listaMensajesEnviados.size()) {
                            MEAux = listaMensajesEnviados.get(contadorEnviados);
                            ListaMensajesOrdenados.add(MEAux);
                            contadorEnviados++;

                        }
                        while (contadorRecibidos != ListaMensajesRecibidos.size()) {
                            MRAux = ListaMensajesRecibidos.get(contadorRecibidos);
                            ListaMensajesOrdenados.add(MRAux);
                            contadorRecibidos++;

                        }

                        for (int i = 0; i < ListaMensajesOrdenados.size(); i++) {
                            Mensajesamigos msj = ListaMensajesOrdenados.get(i);
                            if (i > 0) {
                                if (msj.getFecha().getDay() != ListaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
                                    resultado
                                            = resultado
                                            + "<br><p style =\"color: yellow;\">"
                                            + msj.getFecha().getDate()
                                            + "-" + (msj.getFecha().getMonth() + 1)
                                            + "-" + (msj.getFecha().getYear() + 1900)
                                            + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                }
                            } else {
                                resultado
                                        = resultado
                                        + "<p style =\"color: yellow;\">"
                                        + msj.getFecha().getDate()
                                        + "-" + (msj.getFecha().getMonth() + 1)
                                        + "-" + (msj.getFecha().getYear() + 1900)
                                        + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                            }
                            if (user.getId().equals(msj.getEscritor().getId())) {
                                resultado
                                        = resultado
                                        + "<p style=\"color: darkgray;\">"
                                        + "Tu - "
                                        + msj.getHora();
                            } else {
                                resultado
                                        = resultado
                                        + "<p>"
                                        + useraux.getApodo() + " - "
                                        + msj.getHora();
                            }
                            resultado
                                    = resultado
                                    + " - " + msj.getMensaje()
                                    + "</p>";
                        }
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatRecarga":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                    queryUsuarios.setParameter("apodo", nombre);
                    useraux = queryUsuarios.getSingleResult();
                    id = useraux.getId();

                    //Para saber si en el día ya hubo un mensaje o no
                    fechaLimite = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaFormateada = fechaLimite.format(formatter);

                    sql = "SELECT DISTINCT m.* FROM Mensajesamigos m"
                            + " WHERE ((m.escritor = '" + user.getId() + "' AND m.receptor = '" + id + "') "
                            + " OR (m.escritor = '" + id + "' AND m.receptor = '" + user.getId() + "'))"
                            + " AND m.fecha >= TO_DATE('" + fechaFormateada + "00:00:00', 'YYYY-MM-DD HH24:MI:SS')"; // Fecha de inicio del día

                    queryAUX = em.createNativeQuery(sql, Mensajesamigos.class);
                    resultado = "";

                    System.out.println(queryAUX.getResultList().size());

                    //si no hubo escribimos el dia (es decir el nuestro es el 1)
                    if (queryAUX.getResultList().size() == 1) {
                        resultado
                                = resultado
                                + "<br><p style =\"color: yellow;\">"
                                + fechaLimite.getDayOfMonth()
                                + "-" + fechaLimite.getMonthValue()
                                + "-" + fechaLimite.getYear()
                                + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                    }

                    //mensajes enviados
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    fechaFormateada = fechaLimite.format(formatter);

                    sql = "SELECT m.* FROM Mensajesamigos m"
                            + " WHERE m.escritor = '" + user.getId() + "'"
                            + " and m.receptor = '" + id + "'"
                            + " and m.fecha >= TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                            + " ORDER BY m.fecha";

                    queryAUX = em.createNativeQuery(sql, Mensajesamigos.class);
                    listaMensajesEnviados = queryAUX.getResultList();

                    //mensajes recibidos
                    fechaLimite = fechaLimite.minusSeconds(5);
                    fechaFormateada = fechaLimite.format(formatter);

                    sql = "SELECT m.* FROM Mensajesamigos m"
                            + " WHERE m.escritor = '" + id + "'"
                            + " and m.receptor = '" + user.getId() + "'"
                            + " and m.fecha > TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                            + " ORDER BY m.fecha";

                    queryAUX = em.createNativeQuery(sql, Mensajesamigos.class);
                    ListaMensajesRecibidos = queryAUX.getResultList();

                    ListaMensajesOrdenados = new ArrayList();

                    if (!listaMensajesEnviados.isEmpty() && !ListaMensajesRecibidos.isEmpty()) {

                        while (terminadaMensajesAmigos == false) {

                            MEAux = listaMensajesEnviados.get(listaMensajesEnviados.size() - 1);
                            listaMensajesEnviados.clear();

                            MRAux = ListaMensajesRecibidos.get(contadorRecibidos);
                            vfecha = MEAux.getFecha().compareTo(MRAux.getFecha());// Menor a 0 es antes Mayor a 0 es después

                            if (vfecha == 0) {//misma fecha = antes recibido
                                contadorRecibidos++;
                                ListaMensajesOrdenados.add(MRAux);

                            } else if (vfecha < 0) {//antes el enviado
                                ListaMensajesOrdenados.add(MEAux);

                            } else if (vfecha > 0) {//antes el recibido
                                contadorRecibidos++;
                                ListaMensajesOrdenados.add(MRAux);
                            }

                            terminadaMensajesAmigos = true;
                        }
                    }
                    //Por si quedan en alguna de las dos listas
                    if (!listaMensajesEnviados.isEmpty()) {
                        MEAux = listaMensajesEnviados.get(listaMensajesEnviados.size() - 1);
                        ListaMensajesOrdenados.add(MEAux);
                    }
                    while (contadorRecibidos != ListaMensajesRecibidos.size()) {
                        MRAux = ListaMensajesRecibidos.get(contadorRecibidos);
                        ListaMensajesOrdenados.add(MRAux);
                        contadorRecibidos++;

                    }

                    for (int i = 0; i < ListaMensajesOrdenados.size(); i++) {
                        Mensajesamigos msj = ListaMensajesOrdenados.get(i);
                        if (i > 0) {
                            if (msj.getFecha().getDay() != ListaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
                                resultado
                                        = resultado
                                        + "<br><p style =\"color: yellow;\">"
                                        + msj.getFecha().getDate()
                                        + "-" + (msj.getFecha().getMonth() + 1)
                                        + "-" + (msj.getFecha().getYear() + 1900)
                                        + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                            }
                        }
                        if (user.getId().equals(msj.getEscritor().getId())) {
                            resultado
                                    = resultado
                                    + "<p style=\"color: darkgray;\">"
                                    + "Tu - "
                                    + msj.getHora();
                        } else {
                            resultado
                                    = resultado
                                    + "<p>"
                                    + useraux.getApodo() + " - "
                                    + msj.getHora();
                        }
                        resultado
                                = resultado
                                + " - " + msj.getMensaje()
                                + "</p>";
                    }

                    //Si no hay ningun mensaje, borramos resultado
                    if (ListaMensajesOrdenados.isEmpty()) {
                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;

                case "/ChatAmigos":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    sql = "SELECT u2.* FROM Usuarios u "
                            + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                            + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                            + "WHERE a.amigo1 = '" + user.getId() + "'"
                            + "ORDER BY u2.apodo ASC ";

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    cont = 0;
                    encontrado = false;

                    while (encontrado == false && cont < listaUsuarios.size()) {
                        if (listaUsuarios.get(cont).getApodo().equals(nombre)) {
                            encontrado = true;
                        } else {
                            cont++;
                        }
                    }

                    resultado = "<table>";

                    if (encontrado == true) {
                        //Lo colocamos el primero
                        listaUsuarios.add(0, listaUsuarios.remove(cont));

                        useraux = listaUsuarios.get(0);

                        resultado
                                = resultado
                                + "<tr class='amigo-elegido'>"
                                + "<td id='chatActual'>" + useraux.getApodo() + "</td>"
                                + "<td>" + " - En el chat" + "</td>"
                                + "</tr>";

                        for (int i = 1; i < listaUsuarios.size(); i++) {
                            useraux = listaUsuarios.get(i);
                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + useraux.getApodo() + "</td>"
                                    + "<td><button class='botonDentro' onclick=\"cambiarChat('" + useraux.getApodo() + "')\" >Ir al chat</button></td>"
                                    + "</tr>";
                        }

                    } else {
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            useraux = listaUsuarios.get(i);
                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + useraux.getApodo() + "</td>"
                                    + "<td><button class='botonDentro' onclick=\"cambiarChat('" + useraux.getApodo() + "')\" >Ir al chat</button></td>"
                                    + "</tr>";
                        }
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatMesaRecarga":

                    break;

                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////USUARIOS////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/Usuarios":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    System.out.println("PeticionAJAX Llega");

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u.* FROM USUARIOS u "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "' "
                                        + "AND u.APODO LIKE '" + nombre + "%' "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo ASC ";

                            } else {
                                sql = "SELECT DISTINCT u.* FROM USUARIOS u "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u.id = p2.usuario "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'" + " AND p1.mesa = p2.mesa  "
                                        + "AND u.APODO LIKE '" + nombre + "%' "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo ASC ";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u.* FROM USUARIOS u "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "' "
                                        + "AND u.APODO LIKE '" + nombre + "%' "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo DESC ";
                            } else {
                                sql = "SELECT DISTINCT u.* FROM USUARIOS u "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u.id = p2.usuario "
                                        + "WHERE u.APODO <> '" + user.getApodo() + "'" + " AND p1.mesa = p2.mesa  "
                                        + "AND u.APODO LIKE '" + nombre + "%' "
                                        + "AND u.ID NOT IN ("
                                        + "    SELECT pa.ACEPTA FROM PIDEAMISTAD pa WHERE pa.PIDE = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADOR FROM BLOQUEADOS b WHERE b.BLOQUEADO = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT b.BLOQUEADO FROM BLOQUEADOS b WHERE b.BLOQUEADOR = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO1 FROM AMIGOS a WHERE a.AMIGO2 = '" + user.getId() + "'"
                                        + "    UNION "
                                        + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')"
                                        + "    ORDER BY u.apodo DESC ";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {
                            int j = 0;
                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Personajes pejaux = listaUsuarios.get(i).getPersonajeactual();
                        if (pejaux.getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + pejaux.getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (fotosPersonajes.get(i).equals("-")) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>"
                                + "<td>" + usuario.getApodo() + "</td>"
                                + "<td>" + pertenecemesaUsuarios.get(i) + "</td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/enviarPeticion?pamistad=" + usuario.getId() + "'\">Añadir Amigo</button></td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/bloquearUsuario?bloqueado=" + usuario.getId() + "'\">Bloquear Usuario</button></td>"
                                + "</tr>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                case "/Amigos":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    System.out.println("PeticionAJAX Llega");

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                        + "WHERE a.amigo1 = '" + user.getApodo() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC ";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC ";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                        + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC ";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Personajes pejaux = listaUsuarios.get(i).getPersonajeactual();
                        if (pejaux.getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + pejaux.getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (fotosPersonajes.get(i).equals("-")) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>"
                                + "<td>" + usuario.getApodo() + "</td>"
                                + "<td>" + usuario.getProvincia() + "</td>"
                                + "<td>" + usuario.getGenero() + "</td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/mostrarAmigo?amigo=" + usuario.getId() + "'\">Detalles</button></td>"
                                + "<td><button class='botonDentro' onclick='mostrarRecuadro()'>Eliminar Amigo</button></td>"
                                + "</tr>"
                                + "<div class='opcionRecuadro' id='recuadro' style='display: none;'>"
                                + "<div class='contenidoRecuadro'>"
                                + "<div class='tituloRecuadro'>¿Está seguro que quiere Eliminarlo?"
                                + "<span class='cierreRecuadro' onclick='cerrarRecuadro()'>X</span>"
                                + "</div>"
                                + "<hr>"
                                + "<button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/eliminarAmigo?amigo=" + usuario.getId() + "'\">Si</button>"
                                + "<button class='botonDentro' onclick='cerrarRecuadro()'>No</button>"
                                + "</div>"
                                + "</div>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                case "/PeticionesEnviadas":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    System.out.println("PeticionAJAX Llega");

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC ";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC ";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Personajes pejaux = listaUsuarios.get(i).getPersonajeactual();
                        if (pejaux.getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + pejaux.getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (fotosPersonajes.get(i).equals("-")) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>"
                                + "<td>" + usuario.getApodo() + "</td>"
                                + "<td>" + pertenecemesaUsuarios.get(i) + "</td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/eliminarPeticion?pamistad=" + usuario.getId() + "'\">Eliminar</button></td>"
                                + "</tr>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                case "/PeticionesRecibidas":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    System.out.println("PeticionAJAX Llega");

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC ";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo ASC ";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC ";
                            } else {
                                sql = "SELECT DISTINCT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo DESC ";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", listaUsuarios.get(i).getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        encontrado = false;
                        if (listaPerteneceMesa.isEmpty() == false) {
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                if (!queryPMesas.getResultList().isEmpty()) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (encontrado == true) {
                            pertenecemesaUsuarios.add("Compartis una Mesa");
                        } else {
                            pertenecemesaUsuarios.add("No compartis una Mesa");
                        }
                    }

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Personajes pejaux = listaUsuarios.get(i).getPersonajeactual();
                        if (pejaux.getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + pejaux.getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (fotosPersonajes.get(i).equals("-")) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>"
                                + "<td>" + usuario.getApodo() + "</td>"
                                + "<td>" + pertenecemesaUsuarios.get(i) + "</td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/aceptarPeticion?pamistad=" + usuario.getId() + "'\">Aceptar</button></td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/rechazarPeticion?pamistad=" + usuario.getId() + "'\">Rechazar</button></td>"
                                + "</tr>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                case "/Bloqueados":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Bloqueados b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "AND u2.apodo LIKE '" + nombre + "%' "
                                    + "ORDER BY u2.apodo ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Bloqueados b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "AND u2.apodo LIKE '" + nombre + "%' "
                                    + "ORDER BY u2.apodo DESC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Personajes pejaux = listaUsuarios.get(i).getPersonajeactual();
                        if (pejaux.getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + pejaux.getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (fotosPersonajes.get(i).equals("-")) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>"
                                + "<td>" + usuario.getApodo() + "</td>"
                                + "<td>" + usuario.getProvincia() + "</td>"
                                + "<td>" + usuario.getGenero() + "</td>"
                                + "<td><button class='botonDentro' onclick='mostrarRecuadro()'>Desbloquear</button></td>"
                                + "</tr>"
                                + "<div class='opcionRecuadro' id='recuadro' style='display: none;'>"
                                + "<div class='contenidoRecuadro'>"
                                + "<div class='tituloRecuadro'>¿Esta seguro que quiere desbloquearlo?"
                                + "<span class='cierreRecuadro' onclick='cerrarRecuadro()'>X</span>"
                                + "</div>"
                                + "<hr>"
                                + "<button class='botonDentro' onclick=\"location.href = '/TFG/Usuarios/desbloquearUsuario?bloqueado=" + usuario.getId() + "'\">Si</button>"
                                + "<button class='botonDentro' onclick='cerrarRecuadro()'>No</button>"
                                + "</div>"
                                + "</div>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////MESAS///////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/Mesas":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar
                    lleno = request.getParameter("lleno");//si filtramos por lleno o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    switch (ordenar) {
                        case "ordenar1":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO > ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "AND M.TITULO LIKE '" + nombre + "%' "
                                        + "ORDER BY M.TITULO ASC ";
                            } else {
                                sql = "SELECT DISTINCT M.* "
                                        + "FROM MESAS M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO = ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "AND M.TITULO LIKE '" + nombre + "%' "
                                        + "ORDER BY M.TITULO ASC ";
                            }
                            break;
                        case "ordenar2":
                            if (lleno.equalsIgnoreCase("false")) {
                                sql = "SELECT M.* "
                                        + "FROM MESAS M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO > ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "AND M.TITULO LIKE '" + nombre + "%' "
                                        + "ORDER BY M.TITULO DESC ";
                            } else {
                                sql = "SELECT DISTINCT M.* "
                                        + "FROM MESAS M "
                                        + "WHERE NOT EXISTS ( "
                                        + "    SELECT * "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + "      AND P.USUARIO = '" + user.getId() + "' "
                                        + ") "
                                        + "AND M.TAMANO = ( "
                                        + "    SELECT COUNT(*) "
                                        + "    FROM PERTENECEMESA P "
                                        + "    WHERE P.MESA = M.ID "
                                        + ") "
                                        + "AND M.TITULO LIKE '" + nombre + "%' "
                                        + "ORDER BY M.TITULO DESC ";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {
                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaMesas.size(); i++) {
                        Mesas mesaux = listaMesas.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='mesa-foto'>";
                        if (mesaux.getImagenmesa() == null) {
                            resultado
                                    = resultado
                                    + "<img src='/TFG/img/iconos/IMGNEGRO.png'>";
                        } else {
                            resultado
                                    = resultado
                                    + "<img src='" + fotosMesas.get(i) + "'>";
                        }
                        resultado
                                = resultado
                                + "</div></td>"
                                + "<td>" + mesaux.getTitulo() + "</td>"
                                + "<td>" + mesaux.getComunidad() + "</td>"
                                + "<td>" + listaCantidad.get(i) + "/" + mesaux.getTamano() + "</td>"
                                + "<td>" + mesaux.getTitulo() + "</td>";
                        if (lleno.equals("true")) {
                            if (mesaux.getContrasena() != null) {
                                resultado = resultado
                                        + "<td>Con contraseña</td>"
                                        + "<td>Esta llena</td>";
                            } else {
                                resultado = resultado
                                        + "<td>Sin contraseña</td>"
                                        + "<td>Esta llena</td>";
                            }
                        } else {
                            if (mesaux.getContrasena() != null) {
                                resultado = resultado
                                        + "<td>Con contraseña</td>"
                                        + "<td><button class='botonDentro' onclick='mostrarRecuadro()'>Entrar</button></td>";
                            } else {
                                resultado = resultado
                                        + "<td>Sin contraseña</td>"
                                        + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Mesas/anadiraMesa?id=" + mesaux.getId() + "&contrasena_anadirmesa='" + "'\">Si</button></td>";
                            }
                        }
                        resultado = resultado
                                + "</tr>"
                                + "<div class='opcionRecuadro' id='recuadro' style='display: none;'>"
                                + "<div class='contenidoRecuadro'>"
                                + "<form id = form  action='/TFG/Mesas/anadiraMesa?id=$" + mesaux.getId() + "' method='POST'>"
                                + "<label class='tituloRecuadro' for='anadirMesa' id='titulodelRecuadro'>Contraseña:</label>"
                                + "<input class='recuadroDentro' type='password' id='anadirMesa' name='contrasena_anadirmesa' required>"
                                + "<input class='botonDentro' type='submit' value='Aceptar'>"
                                + "<input class='botonDentro' type='button' onclick='cerrarRecuadro()' value='Volver'>"
                                + "</form>"
                                + "</div>"
                                + "</div>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;

                case "/MesasAmigos":

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    ordenar = request.getParameter("orden");//como ordenar

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TITULO ASC ";

                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TITULO DESC ";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TAMANO DESC ";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + id + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TAMANO ASC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {

                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaMesas.size(); i++) {
                        Mesas mesaux = listaMesas.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='mesa-foto'>";
                        if (mesaux.getImagenmesa() == null) {
                            resultado
                                    = resultado
                                    + "<img src='/TFG/img/iconos/IMGNEGRO.png'>";
                        } else {
                            resultado
                                    = resultado
                                    + "<img src='" + fotosMesas.get(i) + "'>";
                        }
                        resultado = resultado
                                + "</div></td>"
                                + "<td>" + mesaux.getTitulo() + "</td>"
                                + "<td>" + mesaux.getComunidad() + "</td>"
                                + "<td>" + listaCantidad.get(i) + "/" + mesaux.getTamano() + "</td>"
                                + "<td>" + mesaux.getCreador() + "</td>"
                                + "</tr>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale");
                    break;
                case "/MesasPerfil":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ordenar = request.getParameter("orden");//como ordenar

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TITULO ASC ";

                            break;
                        case "ordenar2":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TITULO DESC ";
                            break;
                        case "ordenar3":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TAMANO DESC ";
                            break;
                        case "ordenar4":
                            sql = "SELECT M.* "
                                    + "FROM MESAS M "
                                    + "WHERE EXISTS ( "
                                    + "    SELECT * "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + "      AND P.USUARIO = '" + user.getId() + "' "
                                    + ") "
                                    + "AND M.TAMANO >= ( "
                                    + "    SELECT COUNT(*) "
                                    + "    FROM PERTENECEMESA P "
                                    + "    WHERE P.MESA = M.ID "
                                    + ") "
                                    + "AND M.TITULO LIKE '" + nombre + "%' "
                                    + "ORDER BY M.TAMANO ASC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Mesas.class);
                    listaMesas = queryAUX.getResultList();

                    listaCantidad = new ArrayList();
                    fotosMesas = new ArrayList();

                    for (int i = 0; i < listaMesas.size(); i++) {
                        queryAUX = em.createNamedQuery("Pertenecemesa.countByMesa", Integer.class);
                        queryAUX.setParameter("mesa", listaMesas.get(i).getId());
                        cantidad = Integer.parseInt(queryAUX.getSingleResult().toString());
                        listaCantidad.add(cantidad);

                        if (listaMesas.get(i).getImagenmesa() == null) {
                            fotosMesas.add("-");
                        } else {
                            fotosMesas.add("/TFG/Imagenes/mostrarImagenMesa?id=" + listaMesas.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaMesas.size(); i++) {
                        Mesas mesaux = listaMesas.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='mesa-foto'>";
                        if (mesaux.getImagenmesa() == null) {
                            resultado
                                    = resultado
                                    + "<img src='/TFG/img/iconos/IMGNEGRO.png'>";
                        } else {
                            resultado
                                    = resultado
                                    + "<img src='" + fotosMesas.get(i) + "'>";
                        }
                        resultado
                                = resultado
                                + "</div></td>"
                                + "<td>" + mesaux.getTitulo() + "</td>"
                                + "<td>" + mesaux.getComunidad() + "</td>"
                                + "<td>" + listaCantidad.get(i) + "/" + mesaux.getTamano() + "</td>"
                                + "<td>" + mesaux.getCreador() + "</td>"
                                + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Mesas/mostrarMesa?=" + mesaux.getId() + "'" + "'\">Detalles</button></td>";
                        if (mesaux.getCreador().equals(user.getApodo())) {
                            resultado = resultado
                                    + "<td><button class='botonDentro' onclick='mostrarRecuadro()'>Borrar Mesa</button></td>";
                        } else {
                            resultado = resultado
                                    + "<td><button class='botonDentro' onclick=\"location.href = '/TFG/Mesas/salirdeMesa?id=" + mesaux.getId() + "'\">Salir</button></td>";
                        }
                        resultado = resultado
                                + "</tr>"
                                + "<div class='opcionRecuadro' id='recuadro' style='display: none;'>"
                                + "<div class='contenidoRecuadro'>"
                                + "<div class='tituloRecuadro'>¿Esta seguro que quieres Borrarla?"
                                + "<span class='cierreRecuadro' onclick='cerrarRecuadro()'>X</span>"
                                + "</div>"
                                + "<hr>"
                                + "<button class='botonDentro' onclick=\"location.href = '/TFG/Mesas/eliminarMesa?id=" + mesaux.getId() + "'\">Si</button>"
                                + "<button class='botonDentro' onclick='cerrarRecuadro()'>No</button>"
                                + "</div>"
                                + "</div>";
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale MesasPerfil");
                    break;
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////PERSONAJES//////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/CrearPersonaje":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    sql = "SELECT p.* FROM PERSONAJES p "
                            + "WHERE p.USUARIO = '" + user.getId() + "' "
                            + "AND p.NOMBRE LIKE '" + nombre + "%'";
                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    if (listaPersonajes.isEmpty()) {
                        resultado = "No encontrado";
                    } else {
                        resultado = "Encontrado";
                    }

                    System.out.println("PeticionAJAX Sale CrearPersonaje");

                    break;
                case "/Personajes":
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

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    //Recogemos los datos
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || ordenar.equals("null")) {
                        ordenar = "ordenar1";
                    }
                    if (raza == null || raza.equals("null") || raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase == null || clase.equals("null") || clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString == null || nivelString.equals("null") || nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }

                    System.out.println(raza);

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO <> '" + id + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO <> '" + id + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE DESC ";
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

                    resultado = "";

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        Personajes pejaux = listaPersonajes.get(i);
                        resultado = "<table>"
                                + "<tr>"
                                + "<td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (pejaux.getImagenpersonaje() == null) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>\n"
                                + "<td>" + pejaux.getNombre() + "</td>\n"
                                + "<td>" + pejaux.getClase().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getRaza().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getNivel() + "</td>\n"
                                + "<td>" + listaUsuarios.get(i).getApodo() + "</td>\n"
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personaje/personaje?id=" + pejaux.getId() + "'\">Detalles</button></td>\n"
                                + "</tr>\n"
                                + "</table>";
                    }

                    System.out.println(sql);

                    System.out.println("PeticionAJAX Sale Personajes");

                    break;
                case "/PersonajesAmigos":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    //Recogemos los datos
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || ordenar.equals("null")) {
                        ordenar = "ordenar1";
                    }
                    if (raza == null || raza.equals("null") || raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase == null || clase.equals("null") || clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString == null || nivelString.equals("null") || nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "p.USUARIO <> '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "INNER JOIN AMIGOS a ON p.USUARIO = a.AMIGO2 "
                                    + "WHERE a.AMIGO1 = '" + user.getId() + "' "
                                    + "p.USUARIO <> '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE DESC ";
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

                    resultado = "<table>";

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        Personajes pejaux = listaPersonajes.get(i);
                        resultado = resultado
                                + "<tr>"
                                + "<td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (pejaux.getImagenpersonaje() == null) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>\n"
                                + "<td>" + pejaux.getNombre() + "</td>\n"
                                + "<td>" + pejaux.getClase().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getRaza().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getNivel() + "</td>\n"
                                + "<td>" + listaUsuarios.get(i).getApodo() + "</td>\n"
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personaje/personajeAmigo?id=" + pejaux.getId() + "&amigo=" + listaUsuarios.get(i).getId() + "'\">Detalles</button></td>\n"
                                + "</tr>\n";
                    }
                    resultado = resultado
                            + "</table>";

                    System.out.println("PeticionAJAX Sale Personajes");

                    break;
                case "/PersonajesAmigo":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    ////////////////////////////
                    ////////////AMIGO///////////
                    ////////////////////////////
                    id = request.getParameter("amigo");

                    //Recogemos los datos
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || ordenar.equals("null")) {
                        ordenar = "ordenar1";
                    }
                    if (raza == null || raza.equals("null") || raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase == null || clase.equals("null") || clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString == null || nivelString.equals("null") || nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + id + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE DESC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    queryUsuarios = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                    queryUsuarios.setParameter("id", id);
                    useraux = queryUsuarios.getSingleResult();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        Personajes pejaux = listaPersonajes.get(i);
                        resultado = resultado
                                + "<tr>"
                                + "<td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (pejaux.getImagenpersonaje() == null) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>\n"
                                + "<td>" + pejaux.getNombre() + "</td>\n"
                                + "<td>" + pejaux.getClase().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getRaza().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getNivel() + "</td>\n"
                                + "<td>" + useraux.getApodo() + "</td>\n"
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personaje/personajeAmigo?id=" + pejaux.getId() + "&amigo=" + useraux.getId() + "'\">Detalles</button></td>\n"
                                + "</tr>\n";
                    }

                    resultado = resultado
                            + "</table>";

                    System.out.println("PeticionAJAX Sale Personajes");

                    break;
                case "/PersonajesPerfil":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    //Recogemos los datos
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || ordenar.equals("null")) {
                        ordenar = "ordenar1";
                    }
                    if (raza == null || raza.equals("null") || raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase == null || clase.equals("null") || clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString == null || nivelString.equals("null") || nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE DESC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        Personajes pejaux = listaPersonajes.get(i);
                        resultado = resultado
                                + "<tr>"
                                + "<td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (pejaux.getImagenpersonaje() == null) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>\n"
                                + "<td>" + pejaux.getNombre() + "</td>\n"
                                + "<td>" + pejaux.getClase().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getRaza().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getNivel() + "</td>\n"
                                + "<td>" + user.getApodo() + "</td>\n"
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personajePerfil?id=${personaje.id}'\">Detalles</button></td>\n";

                        if (user.getPersonajesList().size() < 10) {
                            resultado = resultado
                                    + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/copiarPersonaje?id=${personaje.id}'\">Copiar</button></td>\n";
                        }
                        resultado = resultado
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personaje/personaje?id=" + pejaux.getId() + "&amigo=" + user.getId() + "'\">Detalles</button></td>\n"
                                + "</tr>\n";

                    }
                    resultado = resultado
                            + "</table>";

                    System.out.println("PeticionAJAX Sale PersonajesPerfil");

                    break;
                case "/PersonajesPerfilElegir":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    //Recogemos los datos
                    ordenar = request.getParameter("orden");//como ordenar
                    raza = request.getParameter("raza");//como ordenar
                    clase = request.getParameter("clase");//como ordenar
                    nivelString = request.getParameter("nivel");//como ordenar

                    //Comprobamos los datos
                    if (ordenar == null || ordenar.equals("null")) {
                        ordenar = "ordenar1";
                    }
                    if (raza == null || raza.equals("null") || raza.equals("Raza")) {
                        raza = " ";
                    } else {
                        raza = "AND p.RAZA = '" + raza + "' ";
                    }
                    if (clase == null || clase.equals("null") || clase.equals("Clase")) {
                        clase = " ";
                    } else {
                        clase = "AND p.CLASE = '" + clase + "' ";
                    }
                    if (nivelString == null || nivelString.equals("null") || nivelString.equals("Nivel")) {
                        nivelString = " ";
                    } else {
                        nivelString = "AND p.NIVEL = '" + nivelString + "' ";
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE ASC ";
                            break;
                        case "ordenar2":
                            sql = "SELECT p.* FROM PERSONAJES p "
                                    + "WHERE p.USUARIO = '" + user.getId() + "' "
                                    + nivelString
                                    + clase
                                    + raza
                                    + "AND p.NOMBRE LIKE '" + nombre + "%' "
                                    + "ORDER BY p.NOMBRE DESC ";
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Personajes.class);
                    listaPersonajes = queryAUX.getResultList();

                    fotosPersonajes = new ArrayList();

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        if (listaPersonajes.get(i).getImagenpersonaje() == null) {
                            fotosPersonajes.add("-");
                        } else {
                            fotosPersonajes.add("/TFG/Imagenes/mostrarImagenPersonaje?id=" + listaPersonajes.get(i).getId());
                        }
                    }

                    resultado = "<table>";

                    for (int i = 0; i < listaPersonajes.size(); i++) {
                        Personajes pejaux = listaPersonajes.get(i);
                        resultado = resultado
                                + "<tr><td>"
                                + "<div class=\"personaje-foto\">\n";
                        if (pejaux.getImagenpersonaje() == null) {
                            resultado = resultado
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n";
                        } else {
                            resultado = resultado
                                    + "<img src=\"" + fotosPersonajes.get(i) + "\">\n";
                        }
                        resultado = resultado
                                + "</div>"
                                + "</td>\n"
                                + "<td>" + pejaux.getNombre() + "</td>\n"
                                + "<td>" + pejaux.getClase().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getRaza().getNombre() + "</td>\n"
                                + "<td>" + pejaux.getNivel() + "</td>\n"
                                + "<td>" + user.getApodo() + "</td>\n"
                                + "<td><button class=\"botonDentro\" onclick=\"location.href = '/TFG/Usuarios/personajeActual?id=" + pejaux.getId() + "'\">Elegir</button></td>\n"
                                + "</tr>\n";
                    }
                    resultado = resultado
                            + "</table>";

                    System.out.println("PeticionAJAX Sale PersonajesElegir");

                    break;
                case "/CrearPersonajesSubclase":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Encontramos la Clase
                    queryClases = em.createNamedQuery("Clases.findByNombre", Clases.class);
                    queryClases.setParameter("nombre", nombre);
                    Clase = queryClases.getSingleResult();

                    //Si se escoge al nivelString 1
                    if (Clase.getNivelsubclase() == 1) {

                        listaSubClases = Clase.getSubclasesList();

                        resultado = "";

                        for (int i = 0; i < listaSubClases.size(); i++) {
                            resultado = resultado
                                    + "<option value=\""
                                    + listaSubClases.get(i).getNombre()
                                    + "\">"
                                    + listaSubClases.get(i).getNombre()
                                    + "</option>";
                        }

                    } else {
                        resultado = "<option value=\"-\">-</option>";
                    }

                    System.out.println("PeticionAJAX Sale CrearPersonajesSubclase");
                    break;

                case "/CrearPersonajesSubraza":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Encontramos la Raza
                    queryRazas = em.createNamedQuery("Razas.findByNombre", Razas.class);
                    queryRazas.setParameter("nombre", nombre);
                    Raza = queryRazas.getSingleResult();

                    //Si hay almenos 2 razas
                    if (Raza.getSubrazasList().size() > 1) {

                        listaSubRazas = Raza.getSubrazasList();

                        resultado = "";

                        for (int i = 0; i < listaSubRazas.size(); i++) {
                            //Si no es el mismo
                            if (!listaSubRazas.get(i).getNombre().equals(Raza.getNombre())) {
                                resultado = resultado
                                        + "<option value=\""
                                        + listaSubRazas.get(i).getNombre()
                                        + "\">"
                                        + listaSubRazas.get(i).getNombre()
                                        + "</option>";
                            }
                        }

                    } else {
                        resultado = "<option value=\""
                                + nombre
                                + "\">"
                                + nombre
                                + "</option>";
                    }

                    System.out.println("PeticionAJAX Sale CrearPersonajesSubraza");
                    break;
                case "/CrearPersonajesHabilidades":

                    //////////////////////////////////
                    /////////VALORES DE AJAX//////////
                    //////////////////////////////////
                    nombre = request.getParameter("busqueda");
                    id = request.getParameter("busqueda2");

                    //Encontramos la SubRaza
                    querySubRazas = em.createNamedQuery("Subrazas.findByNombre", Subrazas.class);
                    querySubRazas.setParameter("nombre", nombre);
                    Subraza = querySubRazas.getSingleResult();

                    resultado = "<div id=\"habilidadesRaza\"";

                    if (!Subraza.getHabilidadesListC().isEmpty() || !Subraza.getElegirhab().equals("0")) {

                        //Enseñamos que Habilidades te dan
                        if (!Subraza.getHabilidadesListC().isEmpty()) {

                            resultado = "<label>Por Raza obtienes: ";
                            for (int i = 0; i < Subraza.getHabilidadesListC().size(); i++) {
                                resultado = resultado
                                        + Subraza.getHabilidadesListC().get(i).getNombre()
                                        + " ";
                            }
                            resultado = resultado
                                    + "</label>";
                        }

                        //Enseñamos que Habilidades debes escoger
                        if (!Subraza.getElegirhab().equals("0")) {

                            resultado = "<label>Por Raza, elige "
                                    + Subraza.getElegirhab()
                                    + " entre estas opciones:</label>";

                            for (int i = 0; i < Subraza.getHabilidadesListE().size(); i++) {

                                String auxHName = Subraza.getHabilidadesListE().get(i).getNombre();

                                resultado = resultado
                                        + "<input type=\"checkbox\" id=\""
                                        + auxHName
                                        + "\" name=\"habilidades\" value=\""
                                        + auxHName
                                        + "<label for=\""
                                        + auxHName
                                        + "\">"
                                        + auxHName
                                        + "</label>";
                            }
                        }

                    } else {
                        resultado = resultado
                                + "<p>"
                                + "No ganas ninguna habilidad por tu Raza"
                                + "</p>";
                    }

                    resultado = resultado + "</div>";

                    //Encontramos la Clase
                    queryClases = em.createNamedQuery("Clases.findByNombre", Clases.class);
                    queryClases.setParameter("nombre", id);
                    Clase = queryClases.getSingleResult();

                    //Enseñamos que Habilidades debes escoger
                    if (!Clase.getElegirhab().equals("0")) {

                        resultado = resultado + "<div id=\"habilidadesClase\""
                                + "<label>Por Clase, elige "
                                + Clase.getElegirhab()
                                + " entre estas opciones:</label>";

                        for (int i = 0; i < Clase.getHabilidadesList().size(); i++) {

                            String auxHName = Clase.getHabilidadesList().get(i).getNombre();

                            resultado = resultado
                                    + "<input type=\"checkbox\" id=\""
                                    + auxHName
                                    + "\" name=\"habilidades\" value=\""
                                    + auxHName
                                    + "<label for=\""
                                    + auxHName
                                    + "\">"
                                    + auxHName
                                    + "</label>";

                        }
                        resultado = resultado + "</div";
                    } else {//IRREAL SIEMPRE GANARÁS ALGUNA
                        resultado = resultado
                                + "<p>"
                                + "No ganas ninguna habilidad por tu Clase"
                                + "</p>";
                    }

                    System.out.println("PeticionAJAX Sale CrearPersonajesHabilidades");
                    break;

                case "/CrearPersonajesAtributos":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Encontramos la SubRaza
                    querySubRazas = em.createNamedQuery("Subrazas.findByNombre", Subrazas.class);
                    querySubRazas.setParameter("nombre", nombre);
                    Subraza = querySubRazas.getSingleResult();
                    
                    switch (Subraza.getEligeatr()) {
                        case "0":
                            //Encontramos los Atributos que da
                            resultado = "";
                            for (int i = 0; i < Subraza.getSumarazaList().size(); i++) {
                                //Encontramos el atributo
                                queryAtributos = em.createNamedQuery("Atributos.findById", Atributos.class);
                                queryAtributos.setParameter("id", Subraza.getSumarazaList().get(i).getAtributoID());
                                Atributo = queryAtributos.getSingleResult();

                                resultado = "Obtienes +" + Subraza.getSumarazaList().get(i).getModificador() + " de " + Atributo.getNombre() + "<br>";
                            }
                            break;
                        case "1":
                            queryAtributos = em.createNamedQuery("Atributos.findAll", Atributos.class);
                            listaAtributos = queryAtributos.getResultList();

                            resultado = resultado + "<label for=\"atr1\">Obtienes +1:</label>"
                                    + "<select id=\"atr1\" name=\"obtienes_atr1\">";
                            for (int i = 0; i < listaAtributos.size(); i++) {
                                Atributo = listaAtributos.get(i);
                                resultado = resultado + "<option value=\"" + Atributo.getNombre() + "\">" + Atributo.getNombre() + "</option>";
                            }
                            resultado = resultado + "</select><br>"
                                    + "<label for=\"atr2\">Obtienes +2:</label>"
                                    + "<select id=\"atr2\" name=\"obtienes_atr2\">";
                            for (int i = 0; i < listaAtributos.size(); i++) {
                                Atributo = listaAtributos.get(i);
                                resultado = resultado + "<option value=\"" + Atributo.getNombre() + "\">" + Atributo.getNombre() + "</option>";
                            }
                            resultado = resultado + "</select>";

                            break;
                        case "2":
                            queryAtributos = em.createNamedQuery("Atributos.findAll", Atributos.class);
                            listaAtributos = queryAtributos.getResultList();

                            resultado = "<p> Elige +1 en tres atributos: </p>";
                            resultado = resultado + "<label for=\"atrs1\">Obtienes +1:</label>"
                                    + "<select id=\"atrs1\" name=\"atributos[]\">";

                            for (int i = 0; i < listaAtributos.size(); i++) {
                                Atributo = listaAtributos.get(i);
                                resultado = resultado + "<option value=\"" + Atributo.getNombre() + "\">" + Atributo.getNombre() + "</option>";
                            }
                            resultado = resultado + "</select><br>"
                                    + "<label for=\"atrs2\">Obtienes +1:</label>"
                                    + "<select id=\"atrs2\" name=\"atributos[]\">";
                            for (int i = 0; i < listaAtributos.size(); i++) {
                                Atributo = listaAtributos.get(i);
                                resultado = resultado + "<option value=\"" + Atributo.getNombre() + "\">" + Atributo.getNombre() + "</option>";
                            }
                            resultado = resultado + "</select><br>"
                                    + "<label for=\"atrs3\">Obtienes +1:</label>"
                                    + "<select id=\"atrs3\" name=\"atributos[]\">";
                            for (int i = 0; i < listaAtributos.size(); i++) {
                                Atributo = listaAtributos.get(i);
                                resultado = resultado + "<option value=\"" + Atributo.getNombre() + "\">" + Atributo.getNombre() + "</option>";
                            }
                            resultado = resultado + "</select>";
                            break;

                    }
                    System.out.println("PeticionAJAX Sale CrearPersonajesAtributos");
                    break;
                case "/personajeHechizos":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Recogemos los datos
                    id = request.getParameter("id");

                    escuela = request.getParameter("vEscu");
                    nivelString = request.getParameter("vNiv");
                    claseH = request.getParameter("vClas");

                    if (!escuela.equals("null") && !nivelString.equals("null") && !claseH.equals("null")
                            && !escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//TODOS

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                        System.out.println("");

                    } else if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else {
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NOT NULL "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Nivel</th>"
                            + "<th>Escuela</th>"
                            + "<th>Tiempo Lanzamiento</th>"
                            + "<th>Duración</th>"
                            + "<th>Alcance</th>"
                            + "<th>Componentes</th>"
                            + "<th>&nbsp;</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxHechizos = new HashSet<>(listaHechizos);
                    listaHechizos.clear(); //Limpiar el ArrayList
                    listaHechizos.addAll(hashAuxHechizos);

                    cantidad = listaHechizos.size();
                    for (int i = 0; i < cantidad; i++) {
                        hechizoAux = listaHechizos.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + hechizoAux.getNombre() + "</td>"
                                + "<td>" + hechizoAux.getNivel() + "</td>"
                                + "<td>" + hechizoAux.getEscuela() + "</td>"
                                + "<td>" + hechizoAux.getTlanzamiento() + "</td>"
                                + "<td>" + hechizoAux.getDuracion() + "</td>"
                                + "<td>" + hechizoAux.getAlcance() + "</td>"
                                + "<td>" + hechizoAux.getComponentes() + "</td>"
                                + "<td>" + "<button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personajeHechizoEliminar?hechizo="
                                + hechizoAux.getId() + "&personaje=" + id + "'\">Eliminar</button>"
                                + "</td>"
                                + "</tr>"
                                + "<tr class=\"tablaHechizosTR\" onclick=\"window.location = \'/TFG/Explorar/hechizo?idHechizo=" + hechizoAux.getId() + "\'\">"
                                + "<td colspan=\"8\" >" + hechizoAux.getDescripcion() + "</td>"
                                + "</tr>";
                    }
                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td colspan=\"8\" >&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale personajeHechizos");
                    break;
                case "/personajeHechizosElegir":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Recogemos los datos
                    id = request.getParameter("id");

                    escuela = request.getParameter("vEscu");
                    nivelString = request.getParameter("vNiv");
                    claseH = request.getParameter("vClas");

                    if (!escuela.equals("null") && !nivelString.equals("null") && !claseH.equals("null")
                            && !escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//TODOS

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                        System.out.println("");

                    } else if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.ESCUELA = '" + escuela + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else {
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Personajehechizos ph on ph.hechizo = h.id AND ph.personaje = '" + id + "' "
                                + "WHERE ph.personaje IS NULL "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Nivel</th>"
                            + "<th>Escuela</th>"
                            + "<th>Tiempo Lanzamiento</th>"
                            + "<th>Duración</th>"
                            + "<th>Alcance</th>"
                            + "<th>Componentes</th>"
                            + "<th>&nbsp;</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxHechizos = new HashSet<>(listaHechizos);
                    listaHechizos.clear(); //Limpiar el ArrayList
                    listaHechizos.addAll(hashAuxHechizos);

                    cantidad = listaHechizos.size();
                    for (int i = 0; i < cantidad; i++) {
                        hechizoAux = listaHechizos.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + hechizoAux.getNombre() + "</td>"
                                + "<td>" + hechizoAux.getNivel() + "</td>"
                                + "<td>" + hechizoAux.getEscuela() + "</td>"
                                + "<td>" + hechizoAux.getTlanzamiento() + "</td>"
                                + "<td>" + hechizoAux.getDuracion() + "</td>"
                                + "<td>" + hechizoAux.getAlcance() + "</td>"
                                + "<td>" + hechizoAux.getComponentes() + "</td>"
                                + "<td>" + "<button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personajeHechizoAnadir?hechizo="
                                + hechizoAux.getId() + "&personaje=" + id + "'\">Añadir</button>"
                                + "</td>"
                                + "</tr>"
                                + "<tr class=\"tablaHechizosTR\" onclick=\"window.location = \'/TFG/Explorar/hechizo?idHechizo=" + hechizoAux.getId() + "\'\">"
                                + "<td colspan=\"8\" >" + hechizoAux.getDescripcion() + "</td>"
                                + "</tr>";
                    }
                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td colspan=\"8\" >&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale personajeHechizosElegir");
                    break;
                case "/personajeEquipo":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Recogemos los datos
                    id = request.getParameter("id");

                    tipo = request.getParameter("vTipo");
                    categoria = request.getParameter("vCat");
                    propiedad = request.getParameter("vPro");

                    if (!categoria.equals("null") && !tipo.equals("null") && !propiedad.equals("null")
                            && !tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TODOS

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO y CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TIPO y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//CAT y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//PRO

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else {

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NOT NULL "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();
                    }

                    System.out.println(sql);
                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Daño</th>"
                            + "<th>Propiedades</th>"
                            + "<th>Precio</th>"
                            + "<th>Peso</th>"
                            + "<th>&nbsp;</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxEquipo = new HashSet<>(listaEquipo);
                    listaEquipo.clear(); //Limpiar el ArrayList
                    listaEquipo.addAll(hashAuxEquipo);

                    cantidad = listaEquipo.size();

                    for (int i = 0; i < cantidad; i++) {
                        equipoAux = listaEquipo.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + equipoAux.getNombre() + "</td>"
                                + "<td>" + equipoAux.getDano() + "</td>"
                                + "<td>";

                        for (int j = 0; j < equipoAux.getPropiedadesList().size(); j++) {
                            resultado
                                    = resultado
                                    + equipoAux.getPropiedadesList().get(j);
                        }

                        resultado = resultado
                                + "</td>"
                                + "<td>" + equipoAux.getPrecio() + "</td>"
                                + "<td>" + equipoAux.getPeso() + "</td>"
                                + "<td>"
                                + "<button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personajeEquipoEliminar?objeto="
                                + equipoAux.getId() + "&personaje=" + id + "'\">Eliminar</button>"
                                + "</td>"
                                + "</tr>";
                    }

                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale personajeEquipo");
                    break;
                case "/personajeEquipoElegir":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Recogemos los datos
                    id = request.getParameter("id");

                    tipo = request.getParameter("vTipo");
                    categoria = request.getParameter("vCat");
                    propiedad = request.getParameter("vPro");

                    if (!categoria.equals("null") && !tipo.equals("null") && !propiedad.equals("null")
                            && !tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TODOS

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO y CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TIPO y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//CAT y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.TIPO = '" + tipo + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//PRO

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else {

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Personajeequipo pe on pe.equipo = e.id AND pe.personaje = '" + id + "' "
                                + "WHERE pe.personaje IS NULL "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();
                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Daño</th>"
                            + "<th>Propiedades</th>"
                            + "<th>Precio</th>"
                            + "<th>Peso</th>"
                            + "<th>&nbsp;</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxEquipo = new HashSet<>(listaEquipo);
                    listaEquipo.clear(); //Limpiar el ArrayList
                    listaEquipo.addAll(hashAuxEquipo);

                    cantidad = listaEquipo.size();

                    for (int i = 0; i < cantidad; i++) {
                        equipoAux = listaEquipo.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + equipoAux.getNombre() + "</td>"
                                + "<td>" + equipoAux.getDano() + "</td>"
                                + "<td>";

                        for (int j = 0; j < equipoAux.getPropiedadesList().size(); j++) {
                            resultado
                                    = resultado
                                    + equipoAux.getPropiedadesList().get(j);
                        }

                        resultado = resultado
                                + "</td>"
                                + "<td>" + equipoAux.getPrecio() + "</td>"
                                + "<td>" + equipoAux.getPeso() + "</td>"
                                + "<td>"
                                + "<button class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personajeEquipoAnadir?objeto="
                                + equipoAux.getId() + "&personaje=" + id + "'\">Añadir</button>"
                                + "</td>"
                                + "</tr>";
                    }

                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale personajeEquipoElegir");
                    break;
                //////////////////////////////////////////////////////////////////////////
                ///////////////////////////////FORMULARIOS////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/RegistroApodo":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                    queryUsuarios.setParameter("apodo", nombre);

                    if (!queryUsuarios.getResultList().isEmpty()) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;
                case "/RegistroEmail":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                    queryUsuarios.setParameter("correo", nombre);

                    if (!queryUsuarios.getResultList().isEmpty()) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;
                case "/RegistroTelefono":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByTelefono", Usuarios.class);
                    queryUsuarios.setParameter("telefono", new BigInteger(nombre));

                    if (!queryUsuarios.getResultList().isEmpty()) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;
                case "/RegistroNacimiento":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    novalido = true;

                    try {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = formatoFecha.parse(nombre);

                        fechaActual = new Date();
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -12);
                        fechaActual = calendarioAux.getTime();

                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -120);
                        fechaActual = calendarioAux.getTime();

                        novalido = !fechaActual.before(fechaNacimiento);
                    } catch (ParseException ex) {
                        System.out.println("Error recogiendo la fecha");
                    }

                    if (novalido) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;
                case "/RecuperarUser":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                    queryUsuarios.setParameter("apodo", nombre);

                    if (!queryUsuarios.getResultList().isEmpty()) {
                        resultado = "No Encontrado";
                    } else {
                        resultado = "Encontrado";
                    }
                    break;
                case "/RecuperarEmail":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                    queryUsuarios.setParameter("correo", nombre);

                    if (!queryUsuarios.getResultList().isEmpty()) {
                        resultado = "No Encontrado";
                    } else {
                        resultado = "Encontrado";
                    }
                    break;
                case "/ModificarApodo":
                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (user.getApodo().equals(nombre)) {
                        resultado = "No Encontrado";
                    } else {
                        queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                        queryUsuarios.setParameter("apodo", nombre);

                        if (!queryUsuarios.getResultList().isEmpty()) {
                            resultado = "Encontrado";
                        } else {
                            resultado = "No Encontrado";
                        }
                    }
                    break;
                case "/ModificarEmail":
                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (user.getCorreo().equals(nombre)) {
                        resultado = "No Encontrado";
                    } else {
                        queryUsuarios = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                        queryUsuarios.setParameter("correo", nombre);

                        if (!queryUsuarios.getResultList().isEmpty()) {
                            resultado = "Encontrado";
                        } else {
                            resultado = "No Encontrado";
                        }
                    }
                    break;
                case "/ModificarTelefono":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (user.getTelefono() == new BigInteger(nombre)) {
                        resultado = "No Encontrado";
                    } else {
                        queryUsuarios = em.createNamedQuery("Usuarios.findByTelefono", Usuarios.class);
                        queryUsuarios.setParameter("telefono", new BigInteger(nombre));

                        if (!queryUsuarios.getResultList().isEmpty()) {
                            resultado = "Encontrado";
                        } else {
                            resultado = "No Encontrado";
                        }
                    }
                    break;
                case "/ModificarNacimiento":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    novalido = true;

                    try {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = formatoFecha.parse(nombre);

                        fechaActual = new Date();
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -12);
                        fechaActual = calendarioAux.getTime();

                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -120);
                        fechaActual = calendarioAux.getTime();

                        novalido = !fechaActual.before(fechaNacimiento);
                    } catch (ParseException ex) {
                        System.out.println("Error recogiendo la fecha");
                    }

                    if (novalido) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;
                case "/CrearMesa":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryAUX = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                    queryAUX.setParameter("titulo", nombre);

                    if (!queryAUX.getResultList().isEmpty()) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
                    break;

                case "/ModificarMesa":

                    mesa = request.getParameter("mesaTitulo");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (mesa.equals(nombre)) {
                        resultado = "No Encontrado";
                    } else {
                        queryAUX = em.createNamedQuery("Mesas.findByTitulo", Mesas.class);
                        queryAUX.setParameter("titulo", nombre);

                        if (!queryAUX.getResultList().isEmpty()) {
                            resultado = "Encontrado";
                        } else {
                            resultado = "No Encontrado";
                        }
                    }
                    break;
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////EXPLORAR////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/Hechizos":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    escuela = request.getParameter("vEscu");
                    nivelString = request.getParameter("vNiv");
                    claseH = request.getParameter("vClas");

                    if (!escuela.equals("null") && !nivelString.equals("null") && !claseH.equals("null")
                            && !escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//TODOS

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                        System.out.println("");

                    } else if (!escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU y NIVEL

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.ESCUELA = '" + escuela + "' "
                                + "AND h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//ESCU y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.ESCUELA = '" + escuela + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && !claseH.equals("Clase")) {//NIVEL y CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.NIVEL = '" + nivelString + "' "
                                + "AND c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (!escuela.equals("Escuela") && nivelString.equals("Nivel") && claseH.equals("Clase")) {//ESCU
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.ESCUELA = '" + escuela + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && !nivelString.equals("Nivel") && claseH.equals("Clase")) {//NIVEL
                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE h.NIVEL = '" + nivelString + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else if (escuela.equals("Escuela") && nivelString.equals("Nivel") && !claseH.equals("Clase")) {//CLASE

                        sql = "SELECT h.* FROM Hechizos h "
                                + "LEFT JOIN Listahechizos lb on lb.hechizo = h.id "
                                + "LEFT JOIN Clases c on c.id = lb.clase "
                                + "WHERE c.NOMBRE ='" + claseH + "' "
                                + "AND h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();

                    } else {
                        sql = "SELECT h.* FROM Hechizos h "
                                + "WHERE h.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY h.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Hechizos.class);
                        listaHechizos = queryAUX.getResultList();
                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Nivel</th>"
                            + "<th>Escuela</th>"
                            + "<th>Tiempo Lanzamiento</th>"
                            + "<th>Duración</th>"
                            + "<th>Alcance</th>"
                            + "<th>Componentes</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxHechizos = new HashSet<>(listaHechizos);
                    listaHechizos.clear(); //Limpiar el ArrayList
                    listaHechizos.addAll(hashAuxHechizos);

                    cantidad = listaHechizos.size();
                    for (int i = 0; i < cantidad; i++) {
                        hechizoAux = listaHechizos.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + hechizoAux.getNombre() + "</td>"
                                + "<td>" + hechizoAux.getNivel() + "</td>"
                                + "<td>" + hechizoAux.getEscuela() + "</td>"
                                + "<td>" + hechizoAux.getTlanzamiento() + "</td>"
                                + "<td>" + hechizoAux.getDuracion() + "</td>"
                                + "<td>" + hechizoAux.getAlcance() + "</td>"
                                + "<td>" + hechizoAux.getComponentes() + "</td>"
                                + "</tr>"
                                + "<tr class=\"tablaHechizosTR\" onclick=\"window.location = \'/TFG/Explorar/hechizo?idHechizo=" + hechizoAux.getId() + "\'\">"
                                + "<td colspan=\"7\" >" + hechizoAux.getDescripcion() + "</td>"
                                + "</tr>";
                    }
                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td colspan=\"7\" >&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale Hechizos");
                    break;

                case "/Monstruos":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    vd = request.getParameter("vVD");
                    tipo = request.getParameter("vTipo");

                    if (!vd.equals("null") && !tipo.equals("null") && !tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//TODOS
                        sql = "SELECT m.* FROM Monstruos m "
                                + "WHERE m.vdesafio ='" + vd + "' "
                                + "AND m.tipo ='" + tipo + "' "
                                + "AND m.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY m.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";
                        queryAUX = em.createNativeQuery(sql, Monstruos.class);
                        listaMonstruos = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !vd.equals("Valor de Desafio")) {//VD

                        sql = "SELECT m.* FROM Monstruos m "
                                + "WHERE m.vdesafio ='" + vd + "' "
                                + "AND m.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY m.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";
                        queryAUX = em.createNativeQuery(sql, Monstruos.class);
                        listaMonstruos = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && vd.equals("Valor de Desafio")) {//TIPO

                        sql = "SELECT m.* FROM Monstruos m "
                                + "WHERE m.tipo ='" + tipo + "' "
                                + "AND m.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY m.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";
                        queryAUX = em.createNativeQuery(sql, Monstruos.class);
                        listaMonstruos = queryAUX.getResultList();

                    } else {
                        sql = "SELECT m.* FROM Monstruos m "
                                + "WHERE m.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY m.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";
                        queryAUX = em.createNativeQuery(sql, Monstruos.class);
                        listaMonstruos = queryAUX.getResultList();

                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>VD</th>"
                            + "<th>Nombre</th>"
                            + "<th>Tipo</th>"
                            + "<th>Alineamiento</th>"
                            + "<th>Tamaño</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxMonstruos = new HashSet<>(listaMonstruos);
                    listaMonstruos.clear(); //Limpiar el ArrayList
                    listaMonstruos.addAll(hashAuxMonstruos);

                    cantidad = listaMonstruos.size();

                    for (int i = 0; i < cantidad; i++) {
                        monstruoAux = listaMonstruos.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + monstruoAux.getVdesafio() + "</td>"
                                + "<td>" + monstruoAux.getNombre() + "</td>"
                                + "<td>" + monstruoAux.getTipo() + "</td>"
                                + "<td>" + monstruoAux.getAlineamiento() + "</td>"
                                + "<td>" + monstruoAux.getTamano() + "</td>"
                                + "</tr>";
                    }
                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale Monstruos");
                    break;
                case "/Equipo":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    tipo = request.getParameter("vTipo");
                    categoria = request.getParameter("vCat");
                    propiedad = request.getParameter("vPro");

                    if (!categoria.equals("null") && !tipo.equals("null") && !propiedad.equals("null")
                            && !tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TODOS

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO y CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "WHERE e.TIPO = '" + tipo + "' "
                                + "AND e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//TIPO y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE e.TIPO = '" + tipo + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//CAT y PROP

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE e.CATEGORIA = '" + categoria + "' "
                                + "AND p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (!tipo.equals("Tipo") && categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//TIPO

                        sql = "SELECT e.* FROM Equipo e "
                                + "WHERE e.TIPO = '" + tipo + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && !categoria.equals("Categoria") && propiedad.equals("Propiedad")) {//CAT

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE e.CATEGORIA = '" + categoria + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else if (tipo.equals("Tipo") && categoria.equals("Categoria") && !propiedad.equals("Propiedad")) {//PRO

                        sql = "SELECT e.* FROM Equipo e "
                                + "LEFT JOIN Tienepropiedades tp on tp.equipo = e.id "
                                + "LEFT JOIN Propiedades p on p.id = tp.propiedad "
                                + "WHERE p.NOMBRE ='" + propiedad + "' "
                                + "AND e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();

                    } else {

                        sql = "SELECT e.* FROM Equipo e "
                                + "WHERE e.nombre LIKE '" + nombre + "%' "
                                + "ORDER BY e.nombre "
                                + "OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";

                        queryAUX = em.createNativeQuery(sql, Equipo.class);
                        listaEquipo = queryAUX.getResultList();
                    }

                    resultado = "<table>"
                            + "<tr class=\"titulosTabla\">"
                            + "<th>Nombre</th>"
                            + "<th>Daño</th>"
                            + "<th>Propiedades</th>"
                            + "<th>Precio</th>"
                            + "<th>Peso</th>"
                            + "</tr>";

                    //Eliminar duplicados utilizando HashSet
                    hashAuxEquipo = new HashSet<>(listaEquipo);
                    listaEquipo.clear(); //Limpiar el ArrayList
                    listaEquipo.addAll(hashAuxEquipo);

                    cantidad = listaEquipo.size();

                    for (int i = 0; i < cantidad; i++) {
                        equipoAux = listaEquipo.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>" + equipoAux.getNombre() + "</td>"
                                + "<td>" + equipoAux.getDano() + "</td>"
                                + "<td>";

                        for (int j = 0; j < equipoAux.getPropiedadesList().size(); j++) {
                            resultado
                                    = resultado
                                    + equipoAux.getPropiedadesList().get(j);
                        }

                        resultado = resultado
                                + "</td>"
                                + "<td>" + equipoAux.getPrecio() + "</td>"
                                + "<td>" + equipoAux.getPeso() + "</td>"
                                + "</tr>";
                    }

                    while (cantidad < 15) {
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "<td>&nbsp;</td>"
                                + "</tr>";
                        cantidad++;
                    }
                    resultado = resultado + "</table>";
                    System.out.println("PeticionAJAX Sale Equipo");
                    break;
                case "/Razas":
                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////

                    nombre = request.getParameter("busqueda");

                    //Razas Normales
                    queryRazas = em.createNamedQuery("Razas.findByTipo", Razas.class);
                    queryRazas.setParameter("tipo", "Normal");
                    listaRazas = queryRazas.getResultList();

                    num = 0;
                    resultado = "<h3 id=\"Normales\">Normales</h3>"
                            + "<div class=\"ListaRazasTipo\">";

                    while (listaRazas.size() != num) {

                        if (listaRazas.get(num).getNombre().toLowerCase().startsWith(nombre.toLowerCase())) {
                            resultado
                                    = resultado
                                    + "<div class=\"ResumenRaza\" id=\"RazasNormales\" data-id=\"" + listaRazas.get(num).getId() + "\">"
                                    + "<img src=\"" + "/TFG/img/razas/" + listaRazas.get(num).getNombre().toLowerCase() + ".jpg" + "\">"
                                    + "<div class=\"ContenidoRaza\">"
                                    + "<div class=\"TituloRaza\">" + listaRazas.get(num).getNombre() + "</div>"
                                    + "<div class=\"DescripcionRaza\">" + listaRazas.get(num).getResumen() + "</div>"
                                    + "</div>"
                                    + "</div>";
                        }
                        num++;
                    }

                    resultado = resultado
                            + "</div>";

                    //Razas Monstruos
                    queryRazas = em.createNamedQuery("Razas.findByTipo", Razas.class);
                    queryRazas.setParameter("tipo", "Monstruo");
                    listaRazas = queryRazas.getResultList();

                    num = 0;
                    resultado = resultado
                            + "<h3 id=\"Normales\">Monstruosas</h3>"
                            + "<div class=\"ListaRazasTipo\">";

                    while (listaRazas.size() != num) {

                        if (listaRazas.get(num).getNombre().toLowerCase().startsWith(nombre.toLowerCase())) {
                            resultado
                                    = resultado
                                    + "<div class=\"ResumenRaza\" id=\"RazasMonstuosas\" data-id=\"" + listaRazas.get(num).getId() + "\">"
                                    + "<img src=\"" + "/TFG/img/razas/" + listaRazas.get(num).getNombre().toLowerCase() + ".jpg" + "\">"
                                    + "<div class=\"ContenidoRaza\">"
                                    + "<div class=\"TituloRaza\">" + listaRazas.get(num).getNombre() + "</div>"
                                    + "<div class=\"DescripcionRaza\">" + listaRazas.get(num).getResumen() + "</div>"
                                    + "</div>"
                                    + "</div>";
                        }
                        num++;
                    }

                    resultado = resultado
                            + "</div>";

                    System.out.println("PeticionAJAX Sale Razas");
                    break;

            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resultado);
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

}

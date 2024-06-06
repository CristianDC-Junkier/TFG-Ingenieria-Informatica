package controlador;

import entidades.Amigo;
import entidades.Mensajeamigo;
import entidades.Mensajemesa;
import entidades.Mesa;
import entidades.Musica;
import entidades.Musicamesa;
import entidades.Personaje;
import entidades.Pertenecemesa;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
@WebServlet(name = "ControladorChats", urlPatterns = {"/Chats/*"})
public class ControladorChats extends HttpServlet {

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
            Usuario user;

            Usuario useraux;
            Mensajeamigo MEAux;
            Mensajeamigo MRAux;
            Mensajeamigo msjA;
            Mensajemesa MMEAux;
            Mensajemesa MMRAux;
            Mensajemesa msjM;
            Pertenecemesa pmesa;
            Mesa mesa;
            Musica musica;

            TypedQuery<Usuario> queryUsuarios;
            TypedQuery<Amigo> queryAmigos;
            TypedQuery<Mensajeamigo> queryMensajesAmigos;
            TypedQuery<Pertenecemesa> queryPertenecemesas;
            TypedQuery<Mesa> queryMesas;
            TypedQuery<Musica> queryMusica;

            Query queryAUX;

            List<Usuario> listaUsuarios;
            List<Mensajeamigo> listaMensajesEnviados;
            List<Mensajeamigo> listaMensajesRecibidos;
            List<Mensajeamigo> listaMensajesOrdenados;
            List<Mensajemesa> listaMensajesMesaEnviados;
            List<Mensajemesa> listaMensajesMesaRecibidos;
            List<Mensajemesa> listaMensajesMesaOrdenados;
            List<Pertenecemesa> listaPertenecemesa;

            int contadorEnviados = 0;
            int contadorRecibidos = 0;
            int vfecha;
            boolean NoQuedanMensajes;
            LocalDateTime fechaLimite;
            Date fecha;

            String nombre;
            String id;
            String apodo;
            String dado;

            String sql = "";

            boolean encontrado;

            int cont;

            System.out.println("PeticionAJAX Chat entra: " + accion);

            switch (accion) {
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////CHAT NORMAL/////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/ChatCarga":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    if (nombre.equalsIgnoreCase("-1")) {

                    } else {
                        queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                        queryUsuarios.setParameter("apodo", nombre);
                        useraux = queryUsuarios.getSingleResult();

                        queryMensajesAmigos = em.createNamedQuery("Mensajeamigo.findByEscritorReceptor", Mensajeamigo.class);
                        queryMensajesAmigos.setParameter("escritor", user);
                        queryMensajesAmigos.setParameter("receptor", useraux);
                        try {
                            listaMensajesEnviados = queryMensajesAmigos.getResultList();

                            queryMensajesAmigos = em.createNamedQuery("Mensajeamigo.findByReceptorEscritor", Mensajeamigo.class);
                            queryMensajesAmigos.setParameter("escritor", useraux);
                            queryMensajesAmigos.setParameter("receptor", user);
                            listaMensajesRecibidos = queryMensajesAmigos.getResultList();

                            listaMensajesOrdenados = new ArrayList();
                            NoQuedanMensajes = false;

                            if (!listaMensajesEnviados.isEmpty() && !listaMensajesRecibidos.isEmpty()) {

                                while (NoQuedanMensajes == false) {

                                    MEAux = listaMensajesEnviados.get(contadorEnviados);
                                    MRAux = listaMensajesRecibidos.get(contadorRecibidos);
                                    vfecha = MEAux.getFecha().compareTo(MRAux.getFecha());// Menor a 0 es antes Mayor a 0 es después

                                    if (vfecha == 0) {//misma fecha = antes recibido
                                        contadorRecibidos++;
                                        listaMensajesOrdenados.add(MRAux);

                                    } else if (vfecha < 0) {//antes el enviado
                                        contadorEnviados++;
                                        listaMensajesOrdenados.add(MEAux);

                                    } else if (vfecha > 0) {//antes el recibido
                                        contadorRecibidos++;
                                        listaMensajesOrdenados.add(MRAux);
                                    }

                                    if (contadorEnviados == listaMensajesEnviados.size()) {
                                        NoQuedanMensajes = true;
                                    } else if (contadorRecibidos == listaMensajesRecibidos.size()) {
                                        NoQuedanMensajes = true;
                                    }
                                }
                            }
                            //Por si quedan en alguna de las dos listas
                            while (contadorEnviados != listaMensajesEnviados.size()) {
                                MEAux = listaMensajesEnviados.get(contadorEnviados);
                                listaMensajesOrdenados.add(MEAux);
                                contadorEnviados++;

                            }
                            while (contadorRecibidos != listaMensajesRecibidos.size()) {
                                MRAux = listaMensajesRecibidos.get(contadorRecibidos);
                                listaMensajesOrdenados.add(MRAux);
                                contadorRecibidos++;

                            }

                            for (int i = 0; i < listaMensajesOrdenados.size(); i++) {
                                msjA = listaMensajesOrdenados.get(i);
                                if (i > 0) {
                                    if (msjA.getFecha().getDay() != listaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
                                        resultado
                                                = resultado
                                                + "<br><p style =\"color: yellow;\">"
                                                + msjA.getFecha().getDate()
                                                + "-" + (msjA.getFecha().getMonth() + 1)
                                                + "-" + (msjA.getFecha().getYear() + 1900)
                                                + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                    }
                                } else {
                                    resultado
                                            = resultado
                                            + "<p style =\"color: yellow;\">"
                                            + msjA.getFecha().getDate()
                                            + "-" + (msjA.getFecha().getMonth() + 1)
                                            + "-" + (msjA.getFecha().getYear() + 1900)
                                            + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                }
                                if (user.getId().equals(msjA.getEscritor().getId())) {
                                    resultado
                                            = resultado
                                            + "<p style=\"color: darkgray;\">"
                                            + "Tu - "
                                            + msjA.getHora();
                                } else {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + useraux.getApodo() + " - "
                                            + msjA.getHora();
                                }
                                resultado
                                        = resultado
                                        + " - " + msjA.getMensaje()
                                        + "</p>";
                            }
                        } catch (Exception ex) {
                            resultado = "";
                        }
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatRecarga":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                    queryUsuarios.setParameter("apodo", nombre);
                    try {
                        useraux = queryUsuarios.getSingleResult();
                        id = useraux.getId();

                        //Para saber si en el día ya hubo un mensaje o no
                        fechaLimite = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String fechaFormateada = fechaLimite.format(formatter);

                        sql = "SELECT DISTINCT m.* FROM Mensajeamigo m"
                                + " WHERE ((m.escritor = '" + user.getId() + "' AND m.receptor = '" + id + "') "
                                + " OR (m.escritor = '" + id + "' AND m.receptor = '" + user.getId() + "'))"
                                + " AND m.fecha >= TO_DATE('" + fechaFormateada + "00:00:00', 'YYYY-MM-DD HH24:MI:SS')"; // Fecha de inicio del día

                        queryAUX = em.createNativeQuery(sql, Mensajeamigo.class);
                        resultado = "";

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

                        sql = "SELECT m.* FROM Mensajeamigo m"
                                + " WHERE m.escritor = '" + user.getId() + "'"
                                + " and m.receptor = '" + id + "'"
                                + " and m.fecha >= TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                                + " ORDER BY m.fecha";

                        queryAUX = em.createNativeQuery(sql, Mensajeamigo.class);
                        listaMensajesEnviados = queryAUX.getResultList();

                        //mensajes recibidos
                        fechaLimite = fechaLimite.minusSeconds(5);
                        fechaFormateada = fechaLimite.format(formatter);

                        sql = "SELECT m.* FROM Mensajeamigo m"
                                + " WHERE m.escritor = '" + id + "'"
                                + " and m.receptor = '" + user.getId() + "'"
                                + " and m.fecha > TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                                + " ORDER BY m.fecha";

                        queryAUX = em.createNativeQuery(sql, Mensajeamigo.class);
                        listaMensajesRecibidos = queryAUX.getResultList();

                        listaMensajesOrdenados = new ArrayList();
                        NoQuedanMensajes = false;

                        if (!listaMensajesEnviados.isEmpty() && !listaMensajesRecibidos.isEmpty()) {

                            while (NoQuedanMensajes == false) {

                                MEAux = listaMensajesEnviados.get(listaMensajesEnviados.size() - 1);
                                listaMensajesEnviados.clear();

                                MRAux = listaMensajesRecibidos.get(contadorRecibidos);
                                vfecha = MEAux.getFecha().compareTo(MRAux.getFecha());// Menor a 0 es antes Mayor a 0 es después

                                if (vfecha == 0) {//misma fecha = antes recibido
                                    contadorRecibidos++;
                                    listaMensajesOrdenados.add(MRAux);

                                } else if (vfecha < 0) {//antes el enviado
                                    listaMensajesOrdenados.add(MEAux);

                                } else if (vfecha > 0) {//antes el recibido
                                    contadorRecibidos++;
                                    listaMensajesOrdenados.add(MRAux);
                                }

                                NoQuedanMensajes = true;
                            }
                        }
                        //Por si quedan en alguna de las dos listas
                        if (!listaMensajesEnviados.isEmpty()) {
                            MEAux = listaMensajesEnviados.get(listaMensajesEnviados.size() - 1);
                            listaMensajesOrdenados.add(MEAux);
                        }
                        while (contadorRecibidos != listaMensajesRecibidos.size()) {
                            MRAux = listaMensajesRecibidos.get(contadorRecibidos);
                            listaMensajesOrdenados.add(MRAux);
                            contadorRecibidos++;

                        }

                        for (int i = 0; i < listaMensajesOrdenados.size(); i++) {
                            msjA = listaMensajesOrdenados.get(i);
                            if (i > 0) {
                                if (msjA.getFecha().getDay() != listaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
                                    resultado
                                            = resultado
                                            + "<br><p style =\"color: yellow;\">"
                                            + msjA.getFecha().getDate()
                                            + "-" + (msjA.getFecha().getMonth() + 1)
                                            + "-" + (msjA.getFecha().getYear() + 1900)
                                            + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                }
                            }
                            if (user.getId().equals(msjA.getEscritor().getId())) {
                                resultado
                                        = resultado
                                        + "<p style=\"color: darkgray;\">"
                                        + "Tu - "
                                        + msjA.getHora();
                            } else {
                                resultado
                                        = resultado
                                        + "<p>"
                                        + useraux.getApodo() + " - "
                                        + msjA.getHora();
                            }
                            resultado
                                    = resultado
                                    + " - " + msjA.getMensaje()
                                    + "</p>";
                        }

                        //Si no hay ningun mensaje, borramos resultado
                        if (listaMensajesOrdenados.isEmpty()) {
                            resultado = "";
                        }

                        System.out.println("PeticionAJAX Sale");
                    } catch (Exception ex) {
                        resultado = "";
                        System.out.println("PeticionAJAX Sale");
                    }

                    break;
                case "/ChatAmigos":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    sql = "SELECT u2.* FROM Usuario u "
                            + "INNER JOIN Amigo a ON u.id = a.amigo1 "
                            + "INNER JOIN Usuario u2 ON a.amigo2 = u2.id "
                            + "WHERE a.amigo1 = '" + user.getId() + "'"
                            + "ORDER BY u2.apodo ASC ";

                    queryAUX = em.createNativeQuery(sql, Usuario.class);
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
                case "/enviarmensaje":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        apodo = request.getParameter("amigo");

                        if (apodo.equalsIgnoreCase("-1")) {
                            resultado = "No Conseguido";
                        } else {
                            queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigo.findByAmigos", Amigo.class);
                            queryAmigos.setParameter("amigo1", user.getId());
                            queryAmigos.setParameter("amigo2", useraux.getId());

                            if (queryAmigos.getResultList().size() != 1) {
                                resultado = "No Conseguido";
                            } else {

                                resultado = request.getParameter("mensaje");

                                if (resultado.toUpperCase().contains("UPDATE") || resultado.toUpperCase().contains("CREATE")
                                        || resultado.toUpperCase().contains("DELETE") || resultado.toUpperCase().contains("SELECT")
                                        || resultado.toUpperCase().contains("DROP")) {
                                    resultado = "*****";
                                }

                                fecha = new Date();

                                msjA = new Mensajeamigo(resultado, fecha, useraux, user);

                                persist(msjA);

                                resultado = "Conseguido";
                            }
                        }
                    }
                    break;
                case "/enviartirada":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    dado = request.getParameter("dado");

                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        apodo = request.getParameter("amigo");

                        if (apodo.equalsIgnoreCase("-1")) {
                            resultado = "No Conseguido";
                        } else {
                            queryUsuarios = em.createNamedQuery("Usuario.findByApodo", Usuario.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigo.findByAmigos", Amigo.class);
                            queryAmigos.setParameter("amigo1", user.getId());
                            queryAmigos.setParameter("amigo2", useraux.getId());

                            if (queryAmigos.getResultList().size() != 1) {
                                resultado = "No Conseguido";
                            } else {

                                long tiempoActual = System.currentTimeMillis();
                                Random random = new Random(tiempoActual);
                                resultado = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                                fecha = new Date();

                                msjA = new Mensajeamigo(resultado, fecha, useraux, user);

                                persist(msjA);

                                resultado = "Conseguido";
                            }
                        }
                    }
                    break;
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////CHAT MESA///////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                case "/ChatCargaMesa":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                    queryMesas.setParameter("id", id);
                    try {
                        //HAY QUE ORDENARLOS POR FECHA SI O SI
                        mesa = queryMesas.getSingleResult();

                        sql = "SELECT m.* FROM Mensajemesa m"
                                + " WHERE m.mesa = '" + id + "' "
                                + " ORDER BY m.fecha";

                        queryAUX = em.createNativeQuery(sql, Mensajemesa.class);
                        listaMensajesMesaOrdenados = queryAUX.getResultList();

                        for (int i = 0; i < listaMensajesMesaOrdenados.size(); i++) {
                            msjM = listaMensajesMesaOrdenados.get(i);
                            if (i > 0) {
                                if (msjM.getFecha().getDay() != listaMensajesMesaOrdenados.get(i - 1).getFecha().getDay()) {
                                    resultado
                                            = resultado
                                            + "<br><p style =\"color: yellow;\">"
                                            + msjM.getFecha().getDate()
                                            + "-" + (msjM.getFecha().getMonth() + 1)
                                            + "-" + (msjM.getFecha().getYear() + 1900)
                                            + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                }
                            } else {
                                resultado
                                        = resultado
                                        + "<p style =\"color: yellow;\">"
                                        + msjM.getFecha().getDate()
                                        + "-" + (msjM.getFecha().getMonth() + 1)
                                        + "-" + (msjM.getFecha().getYear() + 1900)
                                        + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                            }

                            queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPertenecemesas.setParameter("usuario", msjM.getEscritor().getId());
                            queryPertenecemesas.setParameter("mesa", id);
                            pmesa = queryPertenecemesas.getSingleResult();

                            if (pmesa.getRol().equals("Dungeon Master")) {

                                resultado
                                        = resultado
                                        + "<p style=\"color: purple;\">"
                                        + "Dungeon Master - "
                                        + msjM.getHora();
                            } else {
                                if (pmesa.getPersonajemesa() == null) {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getUsuarios().getApodo()
                                            + " - "
                                            + msjM.getHora();

                                } else {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getPersonajemesa().getNombre()
                                            + " - "
                                            + msjM.getHora();
                                }
                            }
                            resultado = resultado
                                    + " - " + msjM.getMensaje()
                                    + "</p>";
                        }
                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/enviarmensajeMesa":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        id = request.getParameter("mesa");

                        try {
                            queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                            queryMesas.setParameter("id", id);
                            mesa = queryMesas.getSingleResult();

                            queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPertenecemesas.setParameter("usuario", user.getId());
                            queryPertenecemesas.setParameter("mesa", mesa.getId());

                            //Si no perteneces a la mesa saltará un error.
                            pmesa = queryPertenecemesas.getSingleResult();

                            resultado = request.getParameter("mensaje");

                            if (resultado.toUpperCase().contains("UPDATE") || resultado.toUpperCase().contains("CREATE")
                                    || resultado.toUpperCase().contains("DELETE") || resultado.toUpperCase().contains("SELECT")
                                    || resultado.toUpperCase().contains("DROP")) {
                                resultado = "*****";
                            }

                            fecha = new Date();

                            msjM = new Mensajemesa(resultado, fecha, mesa, user);

                            persist(msjM);

                            resultado = "Conseguido";
                        } catch (Exception ex) {

                            resultado = "No Conseguido";
                        }
                    }
                    break;
                case "/enviartiradaMesa":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    dado = request.getParameter("dado");
                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        id = request.getParameter("mesa");

                        try {
                            queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                            queryMesas.setParameter("id", id);
                            mesa = queryMesas.getSingleResult();

                            queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPertenecemesas.setParameter("usuario", user.getId());
                            queryPertenecemesas.setParameter("mesa", mesa.getId());

                            //Si no perteneces a la mesa saltará un error.
                            pmesa = queryPertenecemesas.getSingleResult();

                            long tiempoActual = System.currentTimeMillis();
                            Random random = new Random(tiempoActual);
                            resultado = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                            fecha = new Date();

                            msjM = new Mensajemesa(resultado, fecha, mesa, user);

                            persist(msjM);

                            resultado = "Conseguido";
                        } catch (Exception ex) {
                            resultado = "No Conseguido";
                        }
                    }
                    break;
                case "/ChatRecargaMesa":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    //Para saber si en el día ya hubo un mensaje o no
                    fechaLimite = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaFormateada = fechaLimite.format(formatter);

                    try {
                        sql = "SELECT DISTINCT m.* FROM Mensajemesa m"
                                + " WHERE m.mesa = " + id
                                + " AND m.fecha >= TO_DATE('" + fechaFormateada + "00:00:00', 'YYYY-MM-DD HH24:MI:SS')"; // Fecha de inicio del día

                        queryAUX = em.createNativeQuery(sql, Mensajemesa.class);
                        resultado = "";

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

                        sql = "SELECT m.* FROM Mensajemesa m"
                                + " WHERE m.escritor = '" + user.getId() + "' "
                                + " and m.mesa = " + id
                                + " and m.fecha >= TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                                + " ORDER BY m.fecha";

                        queryAUX = em.createNativeQuery(sql, Mensajemesa.class);
                        listaMensajesMesaEnviados = queryAUX.getResultList();

                        //mensajes recibidos
                        fechaLimite = fechaLimite.minusSeconds(5);
                        fechaFormateada = fechaLimite.format(formatter);

                        sql = "SELECT m.* FROM Mensajemesa m"
                                + " WHERE m.escritor != '" + user.getId() + "' "
                                + " and m.mesa = " + id
                                + " and m.fecha > TO_DATE( '" + fechaFormateada + "', 'YYYY-MM-DD HH24:MI:SS')"
                                + " ORDER BY m.fecha";

                        queryAUX = em.createNativeQuery(sql, Mensajemesa.class);
                        listaMensajesMesaRecibidos = queryAUX.getResultList();

                        listaMensajesMesaOrdenados = new ArrayList();
                        NoQuedanMensajes = false;

                        if (!listaMensajesMesaEnviados.isEmpty() && !listaMensajesMesaRecibidos.isEmpty()) {

                            while (NoQuedanMensajes == false) {

                                MMEAux = listaMensajesMesaEnviados.get(listaMensajesMesaEnviados.size() - 1);
                                listaMensajesMesaEnviados.clear();

                                MMRAux = listaMensajesMesaRecibidos.get(contadorRecibidos);
                                vfecha = MMEAux.getFecha().compareTo(MMRAux.getFecha());// Menor a 0 es antes Mayor a 0 es después

                                if (vfecha == 0) {//misma fecha = antes recibido
                                    contadorRecibidos++;
                                    listaMensajesMesaOrdenados.add(MMRAux);

                                } else if (vfecha < 0) {//antes el enviado
                                    listaMensajesMesaOrdenados.add(MMEAux);

                                } else if (vfecha > 0) {//antes el recibido
                                    contadorRecibidos++;
                                    listaMensajesMesaOrdenados.add(MMRAux);
                                }

                                NoQuedanMensajes = true;
                            }
                        }
                        //Por si quedan en alguna de las dos listas
                        if (!listaMensajesMesaEnviados.isEmpty()) {
                            MMEAux = listaMensajesMesaEnviados.get(listaMensajesMesaEnviados.size() - 1);
                            listaMensajesMesaOrdenados.add(MMEAux);
                        }
                        while (contadorRecibidos != listaMensajesMesaRecibidos.size()) {
                            MMRAux = listaMensajesMesaRecibidos.get(contadorRecibidos);
                            listaMensajesMesaOrdenados.add(MMRAux);
                            contadorRecibidos++;

                        }

                        for (int i = 0; i < listaMensajesMesaOrdenados.size(); i++) {
                            msjM = listaMensajesMesaOrdenados.get(i);
                            if (i > 0) {
                                if (msjM.getFecha().getDay() != listaMensajesMesaOrdenados.get(i - 1).getFecha().getDay()) {
                                    resultado
                                            = resultado
                                            + "<br><p style =\"color: yellow;\">"
                                            + msjM.getFecha().getDate()
                                            + "-" + (msjM.getFecha().getMonth() + 1)
                                            + "-" + (msjM.getFecha().getYear() + 1900)
                                            + "</p><hr style=\"border: none; height: 2px; background-color: yellow;\">";
                                }
                            }
                            queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPertenecemesas.setParameter("usuario", msjM.getEscritor().getId());
                            queryPertenecemesas.setParameter("mesa", id);
                            pmesa = queryPertenecemesas.getSingleResult();
                            if (pmesa.getRol().equals("Dungeon Master")) {

                                resultado
                                        = resultado
                                        + "<p style=\"color: purple;\">"
                                        + "Dungeon Master - "
                                        + msjM.getHora();
                            } else {
                                if (pmesa.getPersonajemesa() == null) {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getUsuarios().getApodo()
                                            + " - "
                                            + msjM.getHora();

                                } else {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getPersonajemesa().getNombre()
                                            + " - "
                                            + msjM.getHora();
                                }
                            }
                            resultado = resultado
                                    + " - " + msjM.getMensaje()
                                    + "</p>";
                        }
                        //Si no hay ningun mensaje, borramos resultado
                        if (listaMensajesMesaOrdenados.isEmpty()) {
                            resultado = "";
                        }
                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatMesaPuntosVidaActual":
                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuario) session.getAttribute("user");

                    if (user == null) {
                        resultado = "";
                    } else {
                        ///////////////////////////////
                        //////PERTENECES A LA MESA/////
                        ///////////////////////////////
                        id = request.getParameter("mesa");

                        queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                        queryPertenecemesas.setParameter("usuario", user.getId());
                        queryPertenecemesas.setParameter("mesa", id);

                        try {

                            pmesa = queryPertenecemesas.getSingleResult();

                            resultado = "<table border=\"1\">"
                                    + "<tr>"
                                    + "<th>Jugador</th>"
                                    + "<th>Personaje</th>"
                                    + "<th>Clase</th>"
                                    + "<th>Nivel</th>"
                                    + "<th>Detalles</th>"
                                    + "</tr>";

                            //////////////////////////
                            //////////USUARIOS////////
                            //////////////////////////
                            queryPertenecemesas = em.createNamedQuery("Pertenecemesa.findByMesa", Pertenecemesa.class);
                            queryPertenecemesas.setParameter("mesa", id);
                            listaPertenecemesa = queryPertenecemesas.getResultList();

                            for (int i = 0; i < listaPertenecemesa.size(); i++) {

                                pmesa = listaPertenecemesa.get(i);

                                resultado = resultado
                                        + "<tr>"
                                        + "<td>" + pmesa.getUsuarios().getApodo() + "</td>";

                                pmesa = listaPertenecemesa.get(i);
                                if (pmesa.getRol().equals("Dungeon Master")) {
                                    resultado = resultado
                                            + "<td>Dungeon Master</td>"
                                            + "<td>-</td>"
                                            + "<td>-</td>"
                                            + "<td>-</td>"
                                            + "</tr>\n";
                                } else if (pmesa.getPersonajemesa() != null) {
                                    resultado = resultado
                                            + "<td>" + pmesa.getPersonajemesa().getNombre() + "</td>\n"
                                            + "<td>" + pmesa.getPersonajemesa().getClase().getNombre() + "</td>\n"
                                            + "<td>" + pmesa.getPersonajemesa().getNivel() + "</td>\n"
                                            + "<td><button  class=\"botonDentro\" onclick=\"location.href = '/TFG/Personajes/personaje?id=" + pmesa.getPersonajemesa().getId() + "'\">Detalles</button></td>"
                                            + "</tr>\n";
                                } else {
                                    resultado = resultado
                                            + "<td>-</td>\n"
                                            + "<td>-</td>\n"
                                            + "<td>-</td>\n"
                                            + "<td>-</td>\n"
                                            + "</tr>\n";
                                }
                            }
                            resultado = resultado
                                    + "</table>";

                        } catch (Exception ex) {
                            resultado = "";
                        }

                    }
                    break;

                case "/ChatDescripcionMesa":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    try {
                        queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        if (mesa.getDescriptormesa() == null) {
                            resultado
                                    = "<div class=\"imagenDescriptor\">\n"
                                    + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">\n"
                                    + "</div>\n"
                                    + "<div class=\"descripcionDescriptor\">\n"
                                    + "<p></p>\n"
                                    + "</div>";
                        } else {
                            resultado
                                    = "<div class=\"imagenDescriptor\">";
                            if (mesa.getDescriptormesa().getImagendescriptor() != null) {
                                resultado
                                        = resultado
                                        + "<img src=\"/TFG/Imagenes/mostrarImagenDescriptor?id=" + mesa.getDescriptormesa().getMesa() + "\">";
                            }
                            else{
                                resultado
                                        = resultado
                                        + "<img src=\"/TFG/img/iconos/IMGNEGRO.png\">";
                            }
                            resultado
                                    = resultado +
                                    "</div>"
                                    + "<div class=\"descripcionDescriptor\">"
                                    + mesa.getDescriptormesa().getDescripcion()
                                    + "</div>";
                        }

                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatMesaMusica":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    try {
                        queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        if (mesa.getMusicamesa().getMusica().getNombre().equals("Ninguna")) {
                            resultado
                                    = "<p>Ahora mismo no hay musica puesta</p>"
                                    + "<audio id=\"reproductorCancion\" name=\"" + mesa.getMusicamesa().getMusica().getNombre() + "\">"
                                    + "<source id=\"cancion\"  src=\"/TFG/musica/" + mesa.getMusicamesa().getMusica().getNombre() + ".mp3\" type=\"audio/mpeg\">"
                                    + "Tu navegador no soporta la reproducción de audio."
                                    + "</audio>\n";
                        } else {
                            resultado
                                    = "<p>Ahora mismo está sonando:</p>"
                                    + "<audio id=\"reproductorCancion\" name=\"" + mesa.getMusicamesa().getMusica().getNombre() + "\">"
                                    + "<source id=\"cancion\"  src=\"/TFG/musica/" + mesa.getMusicamesa().getMusica().getNombre() + ".mp3\" type=\"audio/mpeg\">"
                                    + "Tu navegador no soporta la reproducción de audio."
                                    + "</audio>\n"
                                    + "<p>" + mesa.getMusicamesa().getMusica().getNombre() + "</p>";
                        }

                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatMesaMusicaCambio":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    nombre = request.getParameter("musica");

                    try {
                        queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        queryMusica = em.createNamedQuery("Musica.findById", Musica.class);
                        queryMusica.setParameter("id", nombre);
                        musica = queryMusica.getSingleResult();

                        mesa.setMusicamesa(new Musicamesa(mesa.getId(), musica));

                        updateMesas(mesa);

                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

                    break;
                case "/ChatMesaMusicaActual":

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    id = request.getParameter("mesa");

                    try {
                        queryMesas = em.createNamedQuery("Mesa.findById", Mesa.class);
                        queryMesas.setParameter("id", id);
                        mesa = queryMesas.getSingleResult();

                        resultado = mesa.getMusicamesa().getMusica().getId();

                    } catch (Exception ex) {

                        resultado = "";
                    }

                    System.out.println("PeticionAJAX Sale");

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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateMesas(Object object) {
        try {
            utx.begin();
            em.merge((Mesa) object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

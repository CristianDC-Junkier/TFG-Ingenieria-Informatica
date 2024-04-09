package controlador;

import entidades.Amigos;
import entidades.Mensajesamigos;
import entidades.Mensajesmesas;
import entidades.Mesas;
import entidades.Pertenecemesa;
import entidades.Usuarios;
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

            Usuarios user;
            Usuarios useraux;
            Mensajesamigos MEAux;
            Mensajesamigos MRAux;
            Mensajesamigos msjA = null;
            Mensajesmesas MMEAux;
            Mensajesmesas MMRAux;
            Mensajesmesas msjM = null;
            Pertenecemesa pmesa;
            Mesas mesa;

            TypedQuery<Usuarios> queryUsuarios;
            TypedQuery<Amigos> queryAmigos;
            TypedQuery<Mensajesamigos> queryMensajesAmigos;
            TypedQuery<Mensajesmesas> queryMensajesMesas;
            TypedQuery<Pertenecemesa> queryPerteneceMesas;
            TypedQuery<Mesas> queryMesas;

            Query queryAUX;

            List<Usuarios> listaUsuarios;
            List<Mensajesamigos> listaMensajesEnviados;
            List<Mensajesamigos> ListaMensajesRecibidos;
            List<Mensajesamigos> ListaMensajesOrdenados;
            List<Mensajesmesas> listaMensajesMesasEnviados;
            List<Mensajesmesas> ListaMensajesMesaRecibidos;
            List<Mensajesmesas> ListaMensajesMesaOrdenados;

            int contadorEnviados = 0;
            int contadorRecibidos = 0;
            int vfecha;
            boolean terminadaMensajesAmigos = false;
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
                        try {
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
                                msjA = ListaMensajesOrdenados.get(i);
                                if (i > 0) {
                                    if (msjA.getFecha().getDay() != ListaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
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
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                    queryUsuarios.setParameter("apodo", nombre);
                    try {
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
                            msjA = ListaMensajesOrdenados.get(i);
                            if (i > 0) {
                                if (msjA.getFecha().getDay() != ListaMensajesOrdenados.get(i - 1).getFecha().getDay()) {
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
                        if (ListaMensajesOrdenados.isEmpty()) {
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
                case "/enviarmensaje":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        apodo = request.getParameter("amigo");

                        if (apodo.equalsIgnoreCase("-1")) {
                            resultado = "No Conseguido";
                        } else {
                            queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
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

                                msjA = new Mensajesamigos(resultado, fecha, useraux, user);

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
                    user = (Usuarios) session.getAttribute("user");

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
                            queryUsuarios = em.createNamedQuery("Usuarios.findByApodo", Usuarios.class);
                            queryUsuarios.setParameter("apodo", apodo);
                            useraux = queryUsuarios.getSingleResult();

                            queryAmigos = em.createNamedQuery("Amigos.findByAmigos", Amigos.class);
                            queryAmigos.setParameter("amigo1", user.getId());
                            queryAmigos.setParameter("amigo2", useraux.getId());

                            if (queryAmigos.getResultList().size() != 1) {
                                resultado = "No Conseguido";
                            } else {

                                long tiempoActual = System.currentTimeMillis();
                                Random random = new Random(tiempoActual);
                                resultado = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                                fecha = new Date();

                                msjA = new Mensajesamigos(resultado, fecha, useraux, user);

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
                    id = request.getParameter("busqueda");

                    queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                    queryMesas.setParameter("id", id);
                    try {
                        mesa = queryMesas.getSingleResult();
                        queryMensajesMesas = em.createNamedQuery("Mensajesmesas.findByMesa", Mensajesmesas.class);
                        queryMensajesMesas.setParameter("mesa", mesa);
                        ListaMensajesMesaOrdenados = queryMensajesMesas.getResultList();
                        for (int i = 0; i < ListaMensajesMesaOrdenados.size(); i++) {
                            msjM = ListaMensajesMesaOrdenados.get(i);
                            if (i > 0) {
                                if (msjM.getFecha().getDay() != ListaMensajesMesaOrdenados.get(i - 1).getFecha().getDay()) {
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
                            queryPerteneceMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPerteneceMesas.setParameter("usuario", msjM.getEscritor().getId());
                            queryPerteneceMesas.setParameter("mesa", id);
                            pmesa = queryPerteneceMesas.getSingleResult();
                            if (pmesa.getRol().equals("Dungeon Master")) {

                                resultado
                                        = resultado
                                        + "<p style=\"color: purple;\">"
                                        + "Dungeon Master - "
                                        + msjM.getHora()
                                        + "</p>";
                            } else {
                                if (pmesa.getPersonajemesa() == null) {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getUsuarios().getApodo()
                                            + " - " + msjM.getMensaje()
                                            + "</p>";
                                } else {
                                    resultado
                                            = resultado
                                            + "<p>"
                                            + pmesa.getPersonajemesa().getNombre()
                                            + " - " + msjM.getMensaje()
                                            + "</p>";
                                }
                            }
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
                    user = (Usuarios) session.getAttribute("user");

                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        id = request.getParameter("mesa");

                        try {
                            queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                            queryMesas.setParameter("id", id);
                            mesa = queryMesas.getSingleResult();

                            queryPerteneceMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPerteneceMesas.setParameter("usuario", user.getId());
                            queryPerteneceMesas.setParameter("mesa", mesa.getId());

                            //Si no perteneces a la mesa saltará un error.
                            pmesa = queryPerteneceMesas.getSingleResult();

                            resultado = request.getParameter("mensaje");

                            if (resultado.toUpperCase().contains("UPDATE") || resultado.toUpperCase().contains("CREATE")
                                    || resultado.toUpperCase().contains("DELETE") || resultado.toUpperCase().contains("SELECT")
                                    || resultado.toUpperCase().contains("DROP")) {
                                resultado = "*****";
                            }

                            fecha = new Date();

                            msjM = new Mensajesmesas(resultado, fecha, mesa, user);

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
                    user = (Usuarios) session.getAttribute("user");

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    dado = request.getParameter("dado");
                    if (user == null) {
                        resultado = "No Conseguido";
                    } else {
                        id = request.getParameter("mesa");

                        try {
                            queryMesas = em.createNamedQuery("Mesas.findById", Mesas.class);
                            queryMesas.setParameter("id", id);
                            mesa = queryMesas.getSingleResult();

                            queryPerteneceMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                            queryPerteneceMesas.setParameter("usuario", user.getId());
                            queryPerteneceMesas.setParameter("mesa", mesa.getId());

                            //Si no perteneces a la mesa saltará un error.
                            pmesa = queryPerteneceMesas.getSingleResult();

                            long tiempoActual = System.currentTimeMillis();
                            Random random = new Random(tiempoActual);
                            resultado = "Tiró de D" + dado + ": " + String.valueOf(random.nextInt(Integer.valueOf(dado)) + 1);

                            fecha = new Date();

                            msjM = new Mensajesmesas(resultado, fecha, mesa, user);

                            persist(msjM);

                            resultado = "Conseguido";
                        } catch (Exception ex) {
                            resultado = "No Conseguido";
                        }
                    }
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
}

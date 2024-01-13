package controlador;

import entidades.Mensajesamigos;
import entidades.Mesas;
import entidades.Pertenecemesa;
import entidades.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        try ( PrintWriter out = response.getWriter()) {

            String accion;
            accion = request.getPathInfo();
            String resultado = "";

            HttpSession session;

            Usuarios user = null;
            Usuarios useraux = null;
            Mensajesamigos MEAux = null;
            Mensajesamigos MRAux = null;

            TypedQuery<Usuarios> queryUsuarios;
            TypedQuery<Pertenecemesa> queryPMesas;
            TypedQuery<Mensajesamigos> queryMensajesAmigos;

            Query queryAUX;

            List<Usuarios> listaUsuarios;
            ArrayList<Usuarios> listaUsuariosAux;
            List<Pertenecemesa> listaPerteneceMesa;
            List<Mesas> listaMesas;
            List<Mensajesamigos> listaMensajesEnviados;
            List<Mensajesamigos> ListaMensajesRecibidos;
            List<Mensajesamigos> ListaMensajesOrdenados;

            ArrayList<String> pertenecemesaUsuarios;
            ArrayList<Integer> listaCantidad;
            ArrayList<String> fotosMesas;

            int contadorEnviados = 0;
            int contadorRecibidos = 0;
            int vfecha;
            boolean terminadaMensajesAmigos = false;

            String nombre;
            String id;
            String ordenar;
            String mesa;
            String lleno;
            int num = 0; //offset
            int cantidad = 0;
            boolean novalido;
            boolean encontrado = false;

            int cont;

            String sql = "";

            switch (accion) {
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////CHATS///////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
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

                    queryMensajesAmigos = em.createNamedQuery("Mensajesamigos.findByEscritorReceptor", Mensajesamigos.class);
                    queryMensajesAmigos.setParameter("escritor", user.getId());
                    queryMensajesAmigos.setParameter("receptor", id);
                    listaMensajesEnviados = queryMensajesAmigos.getResultList();

                    queryMensajesAmigos = em.createNamedQuery("Mensajesamigos.findByReceptorEscritor", Mensajesamigos.class);
                    queryMensajesAmigos.setParameter("escritor", id);
                    queryMensajesAmigos.setParameter("receptor", user.getId());
                    ListaMensajesRecibidos = queryMensajesAmigos.getResultList();

                    ListaMensajesOrdenados = new ArrayList();

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

                        if (contadorEnviados > listaMensajesEnviados.size()) {
                            terminadaMensajesAmigos = true;
                        } else if (contadorRecibidos > ListaMensajesRecibidos.size()) {
                            terminadaMensajesAmigos = true;
                        }
                    }

                    //Por si quedan en alguna de las dos listas
                    while (contadorEnviados != listaMensajesEnviados.size()) {
                        contadorEnviados++;
                        ListaMensajesOrdenados.add(MEAux);
                    }
                    while (contadorRecibidos != ListaMensajesRecibidos.size()) {
                        contadorRecibidos++;
                        ListaMensajesOrdenados.add(MRAux);
                    }

                    //NOTA: SI ES TU MENSAJE EL <P> ES UNA CLASE DIFERENTE PARA QUE SALGA A LA DERECHA??
                    resultado = "<p>";

                    for (int i = 0; i < ListaMensajesOrdenados.size(); i++) {
                        Mensajesamigos msj = ListaMensajesOrdenados.get(i);
                        if (id.equals(user.getId())) {
                            resultado
                                    = resultado
                                    + user.getApodo();
                        } else {
                            resultado
                                    = resultado
                                    + useraux.getApodo();
                        }
                        resultado
                                = resultado
                                + " - " + msj.getMensaje();
                    }
                    resultado = resultado + "</p>";

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
                                + "<td>" + "En el chat" + "</td>"
                                + "</tr>";

                        for (int i = 1; i < listaUsuarios.size(); i++) {
                            useraux = listaUsuarios.get(i);
                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + useraux.getApodo() + "</td>"
                                    + "<td><button class='botonDentro' onclick=\"cambiarChat("+ useraux.getApodo()+")\" >Ir al chat</button></td>"
                                    + "</tr>";
                        }

                    } else {
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            useraux = listaUsuarios.get(i);
                            resultado
                                    = resultado
                                    + "<tr>"
                                    + "<td>" + useraux.getApodo() + "</td>"
                                    + "<td><button class='botonDentro' onclick=\"cambiarChat('"+ useraux.getApodo()+"')\" >Ir al chat</button></td>"
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

                    System.out.println(listaUsuarios.size());

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='personaje-foto'>" + "<img src='/TFG/img/iconos/IMGNEGRO.png'>" + "</div></td>"
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

                    System.out.println(listaUsuarios.size());

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='personaje-foto'>" + "<img src='/TFG/img/iconos/IMGNEGRO.png'>" + "</div></td>"
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

                    System.out.println(listaUsuarios.size());

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='personaje-foto'>" + "<img src='/TFG/img/iconos/IMGNEGRO.png'>" + "</div></td>"
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

                    System.out.println(listaUsuarios.size());

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='personaje-foto'>" + "<img src='/TFG/img/iconos/IMGNEGRO.png'>" + "</div></td>"
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

                    System.out.println(listaUsuarios.size());

                    resultado = "<table>";

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        Usuarios usuario = listaUsuarios.get(i);
                        resultado
                                = resultado
                                + "<tr>"
                                + "<td><div class='personaje-foto'>" + "<img src='/TFG/img/iconos/IMGNEGRO.png'>" + "</div></td>"
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
                            System.out.println("entro");
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
                case "/Personajes":
                    break;
                case "/PersonajesAmigo":
                    break;
                case "/PersonajesAmigos":
                    break;
                case "/PersonajesPerfil":
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

                        Date fechaActual = new Date();
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -12);
                        fechaActual = calendarioAux.getTime();

                        if (fechaNacimiento.before(fechaActual)) {
                            novalido = false;
                        }
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -120);
                        fechaActual = calendarioAux.getTime();
                        System.out.println(fechaActual);
                        System.out.println(fechaNacimiento);
                        System.out.println(fechaActual.before(fechaNacimiento));
                        if (fechaActual.before(fechaNacimiento)) {
                            novalido = false;
                        } else {
                            novalido = true;
                        }
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

                        Date fechaActual = new Date();
                        Calendar calendarioAux = Calendar.getInstance();
                        calendarioAux.setTime(fechaActual);
                        calendarioAux.add(Calendar.YEAR, -12);
                        fechaActual = calendarioAux.getTime();

                        if (fechaNacimiento.before(fechaActual)) {
                            novalido = false;
                        }
                    } catch (ParseException ex) {
                        System.out.println("Error recogiendo la fecha");
                    }

                    if (novalido) {
                        resultado = "Encontrado";
                    } else {
                        resultado = "No Encontrado";
                    }
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

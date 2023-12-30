package controlador;

import entidades.Amigos;
import entidades.Bloqueados;
import entidades.Pertenecemesa;
import entidades.Pideamistad;
import entidades.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String accion;
            accion = request.getPathInfo();
            String resultado = "";

            HttpSession session;
            boolean conseguido;
            String msj;
            Object result;

            Usuarios user = null;
            Amigos amigo = null;
            Bloqueados bloqueado = null;
            Pideamistad pamistad = null;
            Pertenecemesa pertenecemesa = null;

            TypedQuery<Usuarios> queryUsuarios;
            TypedQuery<Amigos> queryAmigos;
            TypedQuery<Bloqueados> queryBloqueados;
            TypedQuery<Pideamistad> queryPideAmistad;
            TypedQuery<Pertenecemesa> queryPMesas;

            Query queryAUX;

            List<Usuarios> listaUsuarios;
            List<Pideamistad> listaPideAmistad;
            List<Pertenecemesa> listaPerteneceMesa;
            ArrayList<String> pertenecemesaUsuarios;

            String id;
            String apodo;
            String nombre;
            String correo;
            String contrasena;
            String contrasenahash;
            String telefono;
            String fechaNacimientoString;
            String provincia;
            String genero;

            String ordenar;
            String mesa;
            String peticiones;
            String numString;
            int num;
            int numPag;

            String sql;

            switch (accion) {
                case "/Usuarios":

                    /////////////////////////
                    /////////SESION//////////
                    /////////////////////////
                    session = request.getSession();
                    user = (Usuarios) session.getAttribute("user");

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    /////////////////////////////////////
                    /////////NUMERO DE USUARIOS//////////
                    /////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM USUARIOS u "
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
                                + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')";
                    } else {
                        sql = "SELECT COUNT(*) FROM USUARIOS u "
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
                                + "    SELECT a.AMIGO2 FROM AMIGOS a WHERE a.AMIGO1 = '" + user.getId() + "')";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    System.out.println("PeticionAJAX Llega");

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

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
                                        + "    ORDER BY u.apodo DESC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";

                            } else {
                                sql = "SELECT u.* FROM USUARIOS u "
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
                                        + "    ORDER BY u.apodo DESC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
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
                                        + "    ORDER BY u.apodo ASC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u.* FROM USUARIOS u "
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
                                        + "    ORDER BY u.apodo ASC "
                                        + "    OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", user.getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaPerteneceMesa.isEmpty() == false) {
                            boolean encontrado = false;
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                pertenecemesa = queryPMesas.getSingleResult();
                                if (pertenecemesa != null) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                            if (encontrado == true) {
                                pertenecemesaUsuarios.add("Compartis una Mesa");
                            } else {
                                pertenecemesaUsuarios.add("No compartis una Mesa");
                            }
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

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    ///////////////////////////////////
                    /////////NUMERO DE AMIGOS//////////
                    ///////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' ";
                    } else {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                                + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE a.amigo1 = '" + user.getId() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    System.out.println("PeticionAJAX Llega");

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                        + "WHERE a.amigo1 = '" + user.getApodo() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.id = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.id "
                                        + "WHERE a.amigo1 = '" + user.getId() + "'"
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Amigos a ON u.apodo = a.amigo1 "
                                        + "INNER JOIN Usuarios u2 ON a.amigo2 = u2.apodo "
                                        + "WHERE a.amigo1 = '" + user.getApodo() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
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

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    ///////////////////////////////////////
                    /////////NUMERO DE PETICIONES//////////
                    ///////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                + "WHERE p.pide = '" + user.getId() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' ";
                    } else {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Pideamistad p ON u.apodo = p.pide "
                                + "INNER JOIN Usuarios u2 ON p.acepta = u2.apodo "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE p.pide = '" + user.getId() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    System.out.println("PeticionAJAX Llega");

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.pide "
                                        + "INNER JOIN Usuarios u2 ON p.acepta = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.pide = '" + user.getId() + "' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", user.getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaPerteneceMesa.isEmpty() == false) {
                            boolean encontrado = false;
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                pertenecemesa = queryPMesas.getSingleResult();
                                if (pertenecemesa != null) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                            if (encontrado == true) {
                                pertenecemesaUsuarios.add("Compartis una Mesa");
                            } else {
                                pertenecemesaUsuarios.add("No compartis una Mesa");
                            }
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

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar
                    mesa = request.getParameter("mesa");//si filtramos por mesa o no

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    ///////////////////////////////////////
                    /////////NUMERO DE PETICIONES//////////
                    ///////////////////////////////////////
                    if (mesa == null || mesa.equalsIgnoreCase("false")) {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                + "WHERE p.acepta = '" + user.getId() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' ";
                    } else {
                        sql = "SELECT COUNT(*) FROM Usuarios u "
                                + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                + "WHERE p.acepta = '" + user.getId() + "' "
                                + "AND u2.apodo LIKE '" + nombre + "%' "
                                + "AND p1.mesa = p2.mesa ";
                    }

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    System.out.println("PeticionAJAX Llega");

                    if (ordenar == null || mesa == null || numString == null) {

                        ordenar = "ordenar1";
                        mesa = "false";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo DESC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                        case "ordenar2":
                            if (mesa.equalsIgnoreCase("false")) {
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            } else {
                                System.out.println("AUN NO IMPLEMENTADO, REPLICAMOS");
                                sql = "SELECT u2.* FROM Usuarios u "
                                        + "INNER JOIN Pideamistad p ON u.id = p.acepta "
                                        + "INNER JOIN Usuarios u2 ON p.pide = u2.id "
                                        + "INNER JOIN Pertenecemesa p1 ON u.id = p1.usuario "
                                        + "INNER JOIN Pertenecemesa p2 ON u2.id = p2.usuario "
                                        + "WHERE p.acepta = '" + user.getId() + "' "
                                        + "AND u2.apodo LIKE '" + nombre + "%' "
                                        + "AND p1.mesa = p2.mesa "
                                        + "ORDER BY u2.apodo ASC "
                                        + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            }
                            break;
                    }

                    queryAUX = em.createNativeQuery(sql, Usuarios.class);
                    listaUsuarios = queryAUX.getResultList();

                    pertenecemesaUsuarios = new ArrayList();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuario", Pertenecemesa.class);
                        queryPMesas.setParameter("usuario", user.getId());
                        listaPerteneceMesa = queryPMesas.getResultList();

                        if (listaPerteneceMesa.isEmpty() == false) {
                            boolean encontrado = false;
                            int j = 0;

                            while (encontrado == false && j < listaPerteneceMesa.size()) {
                                queryPMesas = em.createNamedQuery("Pertenecemesa.findByUsuarioMesa", Pertenecemesa.class);
                                queryPMesas.setParameter("usuario", user.getId());
                                queryPMesas.setParameter("mesa", listaPerteneceMesa.get(j).getPertenecemesaPK().getMesa());
                                pertenecemesa = queryPMesas.getSingleResult();
                                if (pertenecemesa != null) {
                                    encontrado = true;
                                } else {
                                    j++;
                                }
                            }
                            if (encontrado == true) {
                                pertenecemesaUsuarios.add("Compartis una Mesa");
                            } else {
                                pertenecemesaUsuarios.add("No compartis una Mesa");
                            }
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

                    numString = request.getParameter("pag");//numero de pag en la que estoy
                    ordenar = request.getParameter("orden");//como ordenar

                    ////////////////////////////////
                    /////////VALOR DE AJAX//////////
                    ////////////////////////////////
                    nombre = request.getParameter("busqueda");

                    /////////////////////////////////////
                    /////////NUMERO DE BLOQUEADOS////////
                    /////////////////////////////////////
                    sql = "SELECT COUNT(*) FROM Usuarios u "
                            + "INNER JOIN Bloqueados b ON u.id = b.bloqueador "
                            + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.id "
                            + "WHERE b.bloqueador = '" + user.getId() + "' "
                            + "AND u2.apodo LIKE '" + nombre + "%' ";

                    queryAUX = em.createNativeQuery(sql);
                    result = queryAUX.getSingleResult();

                    //PAGINAS QUE HAY (10 AMIGOS POR PAGINA)
                    numPag = (((Number) result).intValue() / 10) + 1;

                    if (ordenar == null || numString == null) {

                        ordenar = "ordenar1";
                        numString = "1";
                        num = 0;

                    } else {

                        num = (Integer.valueOf(numString) - 1) * 10;//offset
                    }

                    switch (ordenar) {
                        case "ordenar1":
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Bloqueados b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "AND u2.apodo LIKE '" + nombre + "%' "
                                    + "ORDER BY u2.apodo DESC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
                            break;
                        case "ordenar2":
                            sql = "SELECT u2.* FROM Usuarios u "
                                    + "INNER JOIN Bloqueados b ON u.id = b.bloqueador "
                                    + "INNER JOIN Usuarios u2 ON b.bloqueado = u2.id "
                                    + "WHERE b.bloqueador = '" + user.getId() + "' "
                                    + "AND u2.apodo LIKE '" + nombre + "%' "
                                    + "ORDER BY u2.apodo ASC "
                                    + "OFFSET " + num + " ROWS FETCH NEXT 10 ROWS ONLY";
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
                    case "/Mesas":
                        break;
                    case "/MesasAmigos":
                        break;
                    case "/MesasPerfil":
                        break;
                    case "/Personajes":
                        break;
                    case "/PersonajesAmigo":
                        break;
                    case "/PersonajesAmigos":
                        break;
                    case "/PersonajesPerfil":
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

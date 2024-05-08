package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByApodo", query = "SELECT u FROM Usuario u WHERE u.apodo = :apodo"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByFechanac", query = "SELECT u FROM Usuario u WHERE u.fechanac = :fechanac"),
    @NamedQuery(name = "Usuario.findByProvincia", query = "SELECT u FROM Usuario u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Usuario.findByGenero", query = "SELECT u FROM Usuario u WHERE u.genero = :genero"),
    @NamedQuery(name = "Usuario.findByAdmin", query = "SELECT u FROM Usuario u WHERE u.admin = :admin"),
})

public class Usuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "APODO", nullable = false, length = 50)
    private String apodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "CORREO", nullable = false, length = 80)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CONTRASENA", nullable = false, length = 100)
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHANAC", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name="PROVINCIA", nullable = false, length = 40)
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "GENERO", nullable = false, length = 40)
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMIN", nullable = false)
    private short admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creador")
    private List<Hilo> hiloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escritor")
    private List<Mensajehilo> mensajehiloList;
    @JoinColumn(name = "PERSONAJEACTUAL", referencedColumnName = "ID")
    @ManyToOne
    private Personajes personajeactual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Personajes> personajesList;
    @Column(name = "TELEFONO")
    private BigInteger telefono;
    @JoinTable(name = "BLOQUEADOS", joinColumns = {
        @JoinColumn(name = "BLOQUEADO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "BLOQUEADOR", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuario> usuariosList;
    @ManyToMany(mappedBy = "usuariosList")
    private List<Usuario> usuariosList1;
    @JoinTable(name = "PIDEAMISTAD", joinColumns = {
        @JoinColumn(name = "ACEPTA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "PIDE", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuario> usuariosList2;
    @ManyToMany(mappedBy = "usuariosList2")
    private List<Usuario> usuariosAmigos;
    @JoinTable(name = "AMIGOS", joinColumns = {
        @JoinColumn(name = "AMIGO1", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "AMIGO2", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuario> usuariosList4;
    @ManyToMany(mappedBy = "usuariosList4")
    private List<Usuario> usuariosList5;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<Pertenecemesa> pertenecemesaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escritor")
    private List<Mensajesmesas> mensajesmesasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receptor")
    private List<Mensajesamigos> mensajesamigosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escritor")
    private List<Mensajesamigos> mensajesamigosList1;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;

    public Usuario() {
    }

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, String apodo, String nombre, String correo, String contrasena, Date fechanac, String provincia, String genero, short admin) {
        this.id = id;
        this.apodo = apodo;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechanac = fechanac;
        this.provincia = provincia;
        this.genero = genero;
        this.admin = admin;
    }

    public Usuario(String apodo, String nombre, String correo, String contrasena, Date fechanac, String provincia, String genero, short admin) {
        this.apodo = apodo;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechanac = fechanac;
        this.provincia = provincia;
        this.genero = genero;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList1() {
        return usuariosList1;
    }

    public void setUsuariosList1(List<Usuario> usuariosList1) {
        this.usuariosList1 = usuariosList1;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList2() {
        return usuariosList2;
    }

    public void setUsuariosList2(List<Usuario> usuariosList2) {
        this.usuariosList2 = usuariosList2;
    }

    @XmlTransient
    public List<Usuario> getUsuariosAmigos() {
        return usuariosAmigos;
    }

    public void setUsuariosAmigos(List<Usuario> usuariosAmigos) {
        this.usuariosAmigos = usuariosAmigos;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList4() {
        return usuariosList4;
    }

    public void setUsuariosList4(List<Usuario> usuariosList4) {
        this.usuariosList4 = usuariosList4;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList5() {
        return usuariosList5;
    }

    public void setUsuariosList5(List<Usuario> usuariosList5) {
        this.usuariosList5 = usuariosList5;
    }

    @XmlTransient
    public List<Pertenecemesa> getPertenecemesaList() {
        return pertenecemesaList;
    }

    public void setPertenecemesaList(List<Pertenecemesa> pertenecemesaList) {
        this.pertenecemesaList = pertenecemesaList;
    }

    @XmlTransient
    public List<Mensajesmesas> getMensajesmesasList() {
        return mensajesmesasList;
    }

    public void setMensajesmesasList(List<Mensajesmesas> mensajesmesasList) {
        this.mensajesmesasList = mensajesmesasList;
    }

    @XmlTransient
    public List<Mensajesamigos> getMensajesamigosList() {
        return mensajesamigosList;
    }

    public void setMensajesamigosList(List<Mensajesamigos> mensajesamigosList) {
        this.mensajesamigosList = mensajesamigosList;
    }

    @XmlTransient
    public List<Mensajesamigos> getMensajesamigosList1() {
        return mensajesamigosList1;
    }

    public void setMensajesamigosList1(List<Mensajesamigos> mensajesamigosList1) {
        this.mensajesamigosList1 = mensajesamigosList1;
    }
    @XmlTransient
    public List<Personajes> getPersonajesList() {
        return personajesList;
    }
    public void setPersonajesList(List<Personajes> personajesList) {
        this.personajesList = personajesList;
    }
    public Personajes getPersonajeactual() {
        return personajeactual;
    }
    public void setPersonajeactual(Personajes personajeactual) {
        this.personajeactual = personajeactual;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public short getAdmin() {
        return admin;
    }

    public void setAdmin(short admin) {
        this.admin = admin;
    }

    @XmlTransient
    public List<Hilo> getHiloList() {
        return hiloList;
    }

    public void setHiloList(List<Hilo> hiloList) {
        this.hiloList = hiloList;
    }

    @XmlTransient
    public List<Mensajehilo> getMensajehiloList() {
        return mensajehiloList;
    }

    public void setMensajehiloList(List<Mensajehilo> mensajehiloList) {
        this.mensajehiloList = mensajehiloList;
    }
}

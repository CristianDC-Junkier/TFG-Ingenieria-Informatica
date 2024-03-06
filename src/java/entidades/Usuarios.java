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
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id"),
    @NamedQuery(name = "Usuarios.findByApodo", query = "SELECT u FROM Usuarios u WHERE u.apodo = :apodo"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuarios.findByContrasena", query = "SELECT u FROM Usuarios u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByFechanac", query = "SELECT u FROM Usuarios u WHERE u.fechanac = :fechanac"),
    @NamedQuery(name = "Usuarios.findByProvincia", query = "SELECT u FROM Usuarios u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Usuarios.findByGenero", query = "SELECT u FROM Usuarios u WHERE u.genero = :genero"),
    @NamedQuery(name = "Usuarios.findByAdmin", query = "SELECT u FROM Usuarios u WHERE u.admin = :admin"),
})

public class Usuarios implements Serializable {

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
    @Column(name = "TELEFONO")
    private BigInteger telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHANAC", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PROVINCIA", nullable = false, length = 40)
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
    @JoinTable(name = "BLOQUEADOS", joinColumns = {
        @JoinColumn(name = "BLOQUEADO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "BLOQUEADOR", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuarios> usuariosList;
    @ManyToMany(mappedBy = "usuariosList")
    private List<Usuarios> usuariosList1;
    @JoinTable(name = "PIDEAMISTAD", joinColumns = {
        @JoinColumn(name = "ACEPTA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "PIDE", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuarios> usuariosList2;
    @ManyToMany(mappedBy = "usuariosList2")
    private List<Usuarios> usuariosList3;
    @JoinTable(name = "AMIGOS", joinColumns = {
        @JoinColumn(name = "AMIGO1", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "AMIGO2", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Usuarios> usuariosList4;
    @ManyToMany(mappedBy = "usuariosList4")
    private List<Usuarios> usuariosList5;
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

    public Usuarios() {
    }

    public Usuarios(String id) {
        this.id = id;
    }

    public Usuarios(String id, String apodo, String nombre, String correo, String contrasena, Date fechanac, String provincia, String genero, short admin) {
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

    public Usuarios(String apodo, String nombre, String correo, String contrasena, Date fechanac, String provincia, String genero, short admin) {
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

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList1() {
        return usuariosList1;
    }

    public void setUsuariosList1(List<Usuarios> usuariosList1) {
        this.usuariosList1 = usuariosList1;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList2() {
        return usuariosList2;
    }

    public void setUsuariosList2(List<Usuarios> usuariosList2) {
        this.usuariosList2 = usuariosList2;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList3() {
        return usuariosList3;
    }

    public void setUsuariosList3(List<Usuarios> usuariosList3) {
        this.usuariosList3 = usuariosList3;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList4() {
        return usuariosList4;
    }

    public void setUsuariosList4(List<Usuarios> usuariosList4) {
        this.usuariosList4 = usuariosList4;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList5() {
        return usuariosList5;
    }

    public void setUsuariosList5(List<Usuarios> usuariosList5) {
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
}

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String apodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String contrasena;
    private BigInteger telefono;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String genero;
    @Basic(optional = false)
    @NotNull
    private short admin;

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

    @Override
    public String toString() {
        return "controlador.Usuarios[ id=" + id + " ]";
    }

}

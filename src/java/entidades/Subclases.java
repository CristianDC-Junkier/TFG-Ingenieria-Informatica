package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "SUBCLASES", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subclases.findAll", query = "SELECT s FROM Subclases s"),
    @NamedQuery(name = "Subclases.findById", query = "SELECT s FROM Subclases s WHERE s.id = :id"),
    @NamedQuery(name = "Subclases.findByNombre", query = "SELECT s FROM Subclases s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Subclases.findByNivel", query = "SELECT s FROM Subclases s WHERE s.nivel = :nivel")})
public class Subclases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private short nivel;
    @ManyToMany(mappedBy = "subclasesList", fetch = FetchType.LAZY)
    private List<Clases> clasesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subclases1", fetch = FetchType.LAZY)
    private List<Tablaclasespornivel> tablaclasespornivelList;

    public Subclases() {
    }

    public Subclases(String id) {
        this.id = id;
    }

    public Subclases(String id, String nombre, String descripcion, short nivel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public Subclases(String nombre, String descripcion, short nivel) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public List<Clases> getClasesList() {
        return clasesList;
    }

    public void setClasesList(List<Clases> clasesList) {
        this.clasesList = clasesList;
    }

    @XmlTransient
    public List<Tablaclasespornivel> getTablaclasespornivelList() {
        return tablaclasespornivelList;
    }

    public void setTablaclasespornivelList(List<Tablaclasespornivel> tablaclasespornivelList) {
        this.tablaclasespornivelList = tablaclasespornivelList;
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
        if (!(object instanceof Subclases)) {
            return false;
        }
        Subclases other = (Subclases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Subclases[ id=" + id + " ]";
    }

}
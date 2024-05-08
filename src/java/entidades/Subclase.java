
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "SUBCLASE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subclase.findAll", query = "SELECT s FROM Subclase s"),
    @NamedQuery(name = "Subclase.findById", query = "SELECT s FROM Subclase s WHERE s.id = :id"),
    @NamedQuery(name = "Subclase.findByNombre", query = "SELECT s FROM Subclase s WHERE s.nombre = :nombre")})
public class Subclase implements Serializable {

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
    @OneToMany(mappedBy = "subclase")
    private List<Personaje> personajesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subclases")
    private List<Usasubclase> usasubclaseList;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Clase clase;

    public Subclase() {
    }

    public Subclase(String id) {
        this.id = id;
    }

    public Subclase(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @XmlTransient
    public List<Usasubclase> getUsasubclaseList() {
        return usasubclaseList;
    }

    public void setUsasubclaseList(List<Usasubclase> usasubclaseList) {
        this.usasubclaseList = usasubclaseList;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
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
        if (!(object instanceof Subclase)) {
            return false;
        }
        Subclase other = (Subclase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Subclase[ id=" + id + " ]";
    }


    @XmlTransient
    public List<Personaje> getPersonajesList() {
        return personajesList;
    }

    public void setPersonajesList(List<Personaje> personajesList) {
        this.personajesList = personajesList;
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
    
}

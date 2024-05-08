package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "RASGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rasgo.findAll", query = "SELECT r FROM Rasgo r"),
    @NamedQuery(name = "Rasgo.findById", query = "SELECT r FROM Rasgo r WHERE r.id = :id"),
    @NamedQuery(name = "Rasgo.findByNombre", query = "SELECT r FROM Rasgo r WHERE r.nombre = :nombre")})
public class Rasgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @ManyToMany(mappedBy = "rasgosList")
    private List<Subraza> subrazasList;
    @JoinTable(name = "USATRASFONDO", joinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondo> trasfondosList;
    @ManyToMany(mappedBy = "rasgosList")
    private List<Monstruo> monstruosList;
    @ManyToMany(mappedBy = "rasgosList")
    private List<Raza> razasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rasgos")
    private List<Usasubclase> usasubclaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rasgos")
    private List<Usaclase> usaclaseList;

    public Rasgo() {
    }

    public Rasgo(String id) {
        this.id = id;
    }

    public Rasgo(String id, String nombre, String descripcion) {
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

    @XmlTransient
    public List<Subraza> getSubrazasList() {
        return subrazasList;
    }

    public void setSubrazasList(List<Subraza> subrazasList) {
        this.subrazasList = subrazasList;
    }

    @XmlTransient
    public List<Trasfondo> getTrasfondosList() {
        return trasfondosList;
    }

    public void setTrasfondosList(List<Trasfondo> trasfondosList) {
        this.trasfondosList = trasfondosList;
    }

    @XmlTransient
    public List<Monstruo> getMonstruosList() {
        return monstruosList;
    }

    public void setMonstruosList(List<Monstruo> monstruosList) {
        this.monstruosList = monstruosList;
    }

    @XmlTransient
    public List<Raza> getRazasList() {
        return razasList;
    }

    public void setRazasList(List<Raza> razasList) {
        this.razasList = razasList;
    }

    @XmlTransient
    public List<Usasubclase> getUsasubclaseList() {
        return usasubclaseList;
    }

    public void setUsasubclaseList(List<Usasubclase> usasubclaseList) {
        this.usasubclaseList = usasubclaseList;
    }

    @XmlTransient
    public List<Usaclase> getUsaclaseList() {
        return usaclaseList;
    }

    public void setUsaclaseList(List<Usaclase> usaclaseList) {
        this.usaclaseList = usaclaseList;
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
        if (!(object instanceof Rasgo)) {
            return false;
        }
        Rasgo other = (Rasgo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rasgo[ id=" + id + " ]";
    }
    
}

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
@Table(name = "RASGOS", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rasgos.findAll", query = "SELECT r FROM Rasgos r"),
    @NamedQuery(name = "Rasgos.findById", query = "SELECT r FROM Rasgos r WHERE r.id = :id"),
    @NamedQuery(name = "Rasgos.findByNombre", query = "SELECT r FROM Rasgos r WHERE r.nombre = :nombre")})
public class Rasgos implements Serializable {

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
    private List<Subrazas> subrazasList;
    @JoinTable(name = "USATRASFONDO", joinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondos> trasfondosList;
    @ManyToMany(mappedBy = "rasgosList")
    private List<Monstruos> monstruosList;
    @ManyToMany(mappedBy = "rasgosList")
    private List<Razas> razasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rasgos")
    private List<Usasubclase> usasubclaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rasgos")
    private List<Usaclase> usaclaseList;

    public Rasgos() {
    }

    public Rasgos(String id) {
        this.id = id;
    }

    public Rasgos(String id, String nombre, String descripcion) {
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
    public List<Subrazas> getSubrazasList() {
        return subrazasList;
    }

    public void setSubrazasList(List<Subrazas> subrazasList) {
        this.subrazasList = subrazasList;
    }

    @XmlTransient
    public List<Trasfondos> getTrasfondosList() {
        return trasfondosList;
    }

    public void setTrasfondosList(List<Trasfondos> trasfondosList) {
        this.trasfondosList = trasfondosList;
    }

    @XmlTransient
    public List<Monstruos> getMonstruosList() {
        return monstruosList;
    }

    public void setMonstruosList(List<Monstruos> monstruosList) {
        this.monstruosList = monstruosList;
    }

    @XmlTransient
    public List<Razas> getRazasList() {
        return razasList;
    }

    public void setRazasList(List<Razas> razasList) {
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
        if (!(object instanceof Rasgos)) {
            return false;
        }
        Rasgos other = (Rasgos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rasgos[ id=" + id + " ]";
    }
    
}

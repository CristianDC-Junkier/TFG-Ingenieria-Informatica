
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
@Table(name = "HABILIDADES", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidades.findAll", query = "SELECT h FROM Habilidades h"),
    @NamedQuery(name = "Habilidades.findById", query = "SELECT h FROM Habilidades h WHERE h.id = :id"),
    @NamedQuery(name = "Habilidades.findByNombre", query = "SELECT h FROM Habilidades h WHERE h.nombre = :nombre")})
public class Habilidades implements Serializable {

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
    @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID")
    @ManyToOne
    private Atributos atributo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidades")
    private List<Personajehabilidades> personajehabilidadesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @ManyToMany(mappedBy = "habilidadesListC")
    private List<Subrazas> subrazasList;
    @JoinTable(name = "ELIGETRASFONDO", joinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondos> trasfondosList;
    @ManyToMany(mappedBy = "habilidadesList")
    private List<Clases> clasesList;
    @JoinTable(name = "COMPETETRASFONDO", joinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondos> trasfondosList1;
    @ManyToMany(mappedBy = "habilidadesListE")
    private List<Subrazas> subrazasList1;

    public Habilidades() {
    }

    public Habilidades(String id) {
        this.id = id;
    }

    public Habilidades(String id, String nombre, String descripcion) {
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
    public List<Clases> getClasesList() {
        return clasesList;
    }

    public void setClasesList(List<Clases> clasesList) {
        this.clasesList = clasesList;
    }

    @XmlTransient
    public List<Trasfondos> getTrasfondosList1() {
        return trasfondosList1;
    }

    public void setTrasfondosList1(List<Trasfondos> trasfondosList1) {
        this.trasfondosList1 = trasfondosList1;
    }

    @XmlTransient
    public List<Subrazas> getSubrazasList1() {
        return subrazasList1;
    }

    public void setSubrazasList1(List<Subrazas> subrazasList1) {
        this.subrazasList1 = subrazasList1;
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
        if (!(object instanceof Habilidades)) {
            return false;
        }
        Habilidades other = (Habilidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Habilidades[ id=" + id + " ]";
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

    public Atributos getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributos atributo) {
        this.atributo = atributo;
    }

    @XmlTransient
    public List<Personajehabilidades> getPersonajehabilidadesList() {
        return personajehabilidadesList;
    }

    public void setPersonajehabilidadesList(List<Personajehabilidades> personajehabilidadesList) {
        this.personajehabilidadesList = personajehabilidadesList;
    }
    
}

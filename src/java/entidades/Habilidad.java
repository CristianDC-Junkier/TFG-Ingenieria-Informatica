
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
@Table(name = "HABILIDAD", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidad.findAll", query = "SELECT h FROM Habilidad h"),
    @NamedQuery(name = "Habilidad.findById", query = "SELECT h FROM Habilidad h WHERE h.id = :id"),
    @NamedQuery(name = "Habilidad.findByNombre", query = "SELECT h FROM Habilidad h WHERE h.nombre = :nombre")})
public class Habilidad implements Serializable {

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
    @ManyToMany(mappedBy = "habilidadesList")
    private List<Dote> dotesList;
    @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID")
    @ManyToOne
    private Atributo atributo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidades")
    private List<Personajehabilidad> personajehabilidadesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @ManyToMany(mappedBy = "habilidadesListC")
    private List<Subraza> subrazasList;
    @JoinTable(name = "ELIGETRASFONDO", joinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondo> trasfondosList;
    @ManyToMany(mappedBy = "habilidadesList")
    private List<Clase> clasesList;
    @JoinTable(name = "COMPETETRASFONDO", joinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Trasfondo> trasfondosList1;
    @ManyToMany(mappedBy = "habilidadesListE")
    private List<Subraza> subrazasList1;

    public Habilidad() {
    }

    public Habilidad(String id) {
        this.id = id;
    }

    public Habilidad(String id, String nombre, String descripcion) {
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
    public List<Clase> getClasesList() {
        return clasesList;
    }

    public void setClasesList(List<Clase> clasesList) {
        this.clasesList = clasesList;
    }

    @XmlTransient
    public List<Trasfondo> getTrasfondosList1() {
        return trasfondosList1;
    }

    public void setTrasfondosList1(List<Trasfondo> trasfondosList1) {
        this.trasfondosList1 = trasfondosList1;
    }

    @XmlTransient
    public List<Subraza> getSubrazasList1() {
        return subrazasList1;
    }

    public void setSubrazasList1(List<Subraza> subrazasList1) {
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
        if (!(object instanceof Habilidad)) {
            return false;
        }
        Habilidad other = (Habilidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Habilidad[ id=" + id + " ]";
    }


    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    @XmlTransient
    public List<Personajehabilidad> getPersonajehabilidadesList() {
        return personajehabilidadesList;
    }

    public void setPersonajehabilidadesList(List<Personajehabilidad> personajehabilidadesList) {
        this.personajehabilidadesList = personajehabilidadesList;
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
    public List<Dote> getDotesList() {
        return dotesList;
    }

    public void setDotesList(List<Dote> dotesList) {
        this.dotesList = dotesList;
    }
    
}

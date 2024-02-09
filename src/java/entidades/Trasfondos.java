
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TRASFONDOS", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trasfondos.findAll", query = "SELECT t FROM Trasfondos t"),
    @NamedQuery(name = "Trasfondos.findById", query = "SELECT t FROM Trasfondos t WHERE t.id = :id"),
    @NamedQuery(name = "Trasfondos.findByNombre", query = "SELECT t FROM Trasfondos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Trasfondos.findByElegirhab", query = "SELECT t FROM Trasfondos t WHERE t.elegirhab = :elegirhab")})
public class Trasfondos implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Lob
    @Column(name = "CHERRAMIENTAS", nullable = false)
    private String cherramientas;
    @Basic(optional = false)
    @NotNull
    @Lob()
    @Column(name = "IDIOMAS", nullable = false)
    private String idiomas;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EQUIPO", nullable = false)
    private String equipo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Size(max = 1)
    @Column(name = "ELEGIRHAB", length = 1)
    private String elegirhab;
    @ManyToMany(mappedBy = "trasfondosList")
    private List<Habilidades> habilidadesList;
    @ManyToMany(mappedBy = "trasfondosList1")
    private List<Habilidades> habilidadesList1;
    @ManyToMany(mappedBy = "trasfondosList")
    private List<Rasgos> rasgosList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    public Trasfondos() {
    }

    public Trasfondos(String id) {
        this.id = id;
    }

    public Trasfondos(String id, String nombre, String cherramientas, String idiomas, String equipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cherramientas = cherramientas;
        this.idiomas = idiomas;
        this.equipo = equipo;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(object instanceof Trasfondos)) {
            return false;
        }
        Trasfondos other = (Trasfondos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entidades.Trasfondos[ id=" + id + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCherramientas() {
        return cherramientas;
    }

    public void setCherramientas(String cherramientas) {
        this.cherramientas = cherramientas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElegirhab() {
        return elegirhab;
    }

    public void setElegirhab(String elegirhab) {
        this.elegirhab = elegirhab;
    }

    @XmlTransient
    public List<Habilidades> getHabilidadesList() {
        return habilidadesList;
    }

    public void setHabilidadesList(List<Habilidades> habilidadesList) {
        this.habilidadesList = habilidadesList;
    }

    @XmlTransient
    public List<Habilidades> getHabilidadesList1() {
        return habilidadesList1;
    }

    public void setHabilidadesList1(List<Habilidades> habilidadesList1) {
        this.habilidadesList1 = habilidadesList1;
    }

    @XmlTransient
    public List<Rasgos> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgos> rasgosList) {
        this.rasgosList = rasgosList;
    }
    
}

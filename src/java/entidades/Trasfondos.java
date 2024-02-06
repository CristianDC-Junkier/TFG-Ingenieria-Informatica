
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
    @NamedQuery(name = "Trasfondos.findAll", query = "SELECT t FROM Trasfondos t ORDER BY t.nombre"),
    @NamedQuery(name = "Trasfondos.findById", query = "SELECT t FROM Trasfondos t WHERE t.id = :id"),
    @NamedQuery(name = "Trasfondos.findByNombre", query = "SELECT t FROM Trasfondos t WHERE t.nombre = :nombre")})
public class Trasfondos implements Serializable {

    @Size(max = 80)
    @Column(name = "NOMBRE", length = 80)
    private String nombre;
    @ManyToMany(mappedBy = "trasfondosList")
    private List<Habilidades> habilidadesList;
    @ManyToMany(mappedBy = "trasfondosList")
    private List<Rasgos> rasgosList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Lob
    @Column(name = "CHERRAMIENTAS")
    private String cherramientas;
    @Lob
    @Column(name = "IDIOMAS")
    private String idiomas;
    @Lob
    @Column(name = "EQUIPO")
    private String equipo;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Trasfondos() {
    }

    public Trasfondos(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @XmlTransient
    public List<Habilidades> getHabilidadesList() {
        return habilidadesList;
    }

    public void setHabilidadesList(List<Habilidades> habilidadesList) {
        this.habilidadesList = habilidadesList;
    }

    @XmlTransient
    public List<Rasgos> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgos> rasgosList) {
        this.rasgosList = rasgosList;
    }
    
}

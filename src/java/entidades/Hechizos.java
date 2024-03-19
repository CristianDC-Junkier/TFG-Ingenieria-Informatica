
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "HECHIZOS", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hechizos.findAll", query = "SELECT h FROM Hechizos h ORDER BY h.nombre"),
    @NamedQuery(name = "Hechizos.findById", query = "SELECT h FROM Hechizos h WHERE h.id = :id"),
    @NamedQuery(name = "Hechizos.findByNombre", query = "SELECT h FROM Hechizos h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hechizos.findByNivel", query = "SELECT h FROM Hechizos h WHERE h.nivel = :nivel"),
    @NamedQuery(name = "Hechizos.findByEscuela", query = "SELECT h FROM Hechizos h WHERE h.escuela = :escuela"),
    @NamedQuery(name = "Hechizos.findByTlanzamiento", query = "SELECT h FROM Hechizos h WHERE h.tlanzamiento = :tlanzamiento"),
    @NamedQuery(name = "Hechizos.findByDuracion", query = "SELECT h FROM Hechizos h WHERE h.duracion = :duracion"),
    @NamedQuery(name = "Hechizos.findByAlcance", query = "SELECT h FROM Hechizos h WHERE h.alcance = :alcance"),
    @NamedQuery(name = "Hechizos.findByComponentes", query = "SELECT h FROM Hechizos h WHERE h.componentes = :componentes"),
    @NamedQuery(name = "Hechizos.findByTsalvacion", query = "SELECT h FROM Hechizos h WHERE h.tsalvacion = :tsalvacion"),
    @NamedQuery(name = "Hechizos.findByDano", query = "SELECT h FROM Hechizos h WHERE h.dano = :dano"),
    @NamedQuery(name = "Hechizos.findByRitual", query = "SELECT h FROM Hechizos h WHERE h.ritual = :ritual"),
    @NamedQuery(name = "Hechizos.findByEscuNivel", query = "SELECT h FROM Hechizos h WHERE h.escuela = :escuela AND h.nivel = :nivel"),
    //@NamedQuery(name = "Hechizos.findByNivel", query = "SELECT h FROM Hechizos h WHERE h.escuela = :escuela AND h.nivel = :nivel")
})
public class Hechizos implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 10)
    @Column(name = "NIVEL", nullable = false, length = 10)
    private String nivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ESCUELA", nullable = false, length = 30)
    private String escuela;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TLANZAMIENTO", nullable = false, length = 30)
    private String tlanzamiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DURACION", nullable = false, length = 50)
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ALCANCE", nullable = false, length = 50)
    private String alcance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COMPONENTES", nullable = false, length = 50)
    private String componentes;
    @Size(max = 30)
    @Column(name = "TSALVACION", length = 30)
    private String tsalvacion;
    @Size(max = 50)
    @Column(name = "DANO", length = 50)
    private String dano;
    @Basic(optional = false)
    @NotNull()
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 2)
    @Column(name = "RITUAL", nullable = false, length = 2)
    private String ritual;
    @ManyToMany(mappedBy = "hechizosList")
    private List<Personajes> personajesList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @JoinTable(name = "LISTAHECHIZOS", joinColumns = {
        @JoinColumn(name = "HECHIZO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Clases> clasesList;

    public Hechizos() {
    }

    public Hechizos(String id) {
        this.id = id;
    }

    public Hechizos(String id, String nombre, String nivel, String escuela, String tlanzamiento, String duracion, String alcance, String componentes, String descripcion, String ritual) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.escuela = escuela;
        this.tlanzamiento = tlanzamiento;
        this.duracion = duracion;
        this.alcance = alcance;
        this.componentes = componentes;
        this.descripcion = descripcion;
        this.ritual = ritual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @XmlTransient
    public List<Clases> getClasesList() {
        return clasesList;
    }
    public void setClasesList(List<Clases> clasesList) {
        this.clasesList = clasesList;
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
        if (!(object instanceof Hechizos)) {
            return false;
        }
        Hechizos other = (Hechizos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entidades.Hechizos[ id=" + id + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getTlanzamiento() {
        return tlanzamiento;
    }

    public void setTlanzamiento(String tlanzamiento) {
        this.tlanzamiento = tlanzamiento;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getComponentes() {
        return componentes;
    }

    public void setComponentes(String componentes) {
        this.componentes = componentes;
    }

    public String getTsalvacion() {
        return tsalvacion;
    }

    public void setTsalvacion(String tsalvacion) {
        this.tsalvacion = tsalvacion;
    }

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRitual() {
        return ritual;
    }

    public void setRitual(String ritual) {
        this.ritual = ritual;
    }

    @XmlTransient
    public List<Personajes> getPersonajesList() {
        return personajesList;
    }

    public void setPersonajesList(List<Personajes> personajesList) {
        this.personajesList = personajesList;
    }
    
}

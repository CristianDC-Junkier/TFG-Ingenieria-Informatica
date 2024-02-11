
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "RAZAS", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Razas.findAll", query = "SELECT r FROM Razas r ORDER BY r.nombre"),
    @NamedQuery(name = "Razas.findById", query = "SELECT r FROM Razas r WHERE r.id = :id"),
    @NamedQuery(name = "Razas.findByNombre", query = "SELECT r FROM Razas r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Razas.findByTipo", query = "SELECT r FROM Razas r WHERE r.tipo = :tipo ORDER BY r.nombre")})
public class Razas implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob()
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob()
    @Column(name = "RESUMEN", nullable = false)
    private String resumen;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EDAD", nullable = false)
    private String edad;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "IDIOMAS", nullable = false)
    private String idiomas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;
    @JoinTable(name = "USARAZA", joinColumns = {
        @JoinColumn(name = "RAZA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Rasgos> rasgosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raza")
    private List<Subrazas> subrazasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;

    public Razas() {
    }

    public Razas(String id) {
        this.id = id;
    }

    public Razas(String id, String nombre, String descripcion, String resumen, String edad, String idiomas, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.resumen = resumen;
        this.edad = edad;
        this.idiomas = idiomas;
        this.tipo = tipo;
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
        if (!(object instanceof Razas)) {
            return false;
        }
        Razas other = (Razas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entidades.Razas[ id=" + id + " ]";
    }
    @XmlTransient
    public List<Subrazas> getSubrazasList() {
        return subrazasList;
    }
    public void setSubrazasList(List<Subrazas> subrazasList) {
        this.subrazasList = subrazasList;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Rasgos> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgos> rasgosList) {
        this.rasgosList = rasgosList;
    }

}

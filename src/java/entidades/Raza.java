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
@Table(name = "RAZA", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Raza.findAll", query = "SELECT r FROM Raza r "),
    @NamedQuery(name = "Raza.findById", query = "SELECT r FROM Raza r WHERE r.id = :id"),
    @NamedQuery(name = "Raza.findByNombre", query = "SELECT r FROM Raza r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Raza.findByTipo", query = "SELECT r FROM Raza r WHERE r.tipo = :tipo")})
public class Raza implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Lob
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raza")
    private List<Personaje> personajesList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @JoinTable(name = "USARAZA", joinColumns = {
        @JoinColumn(name = "RAZA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Rasgo> rasgosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raza")
    private List<Subraza> subrazasList;

    public Raza() {
    }

    public Raza(String id) {
        this.id = id;
    }

    public Raza(String id, String nombre, String descripcion, String resumen, String edad, String idiomas, String tipo) {
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


    @XmlTransient
    public List<Rasgo> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgo> rasgosList) {
        this.rasgosList = rasgosList;
    }

    @XmlTransient
    public List<Subraza> getSubrazasList() {
        return subrazasList;
    }

    public void setSubrazasList(List<Subraza> subrazasList) {
        this.subrazasList = subrazasList;
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
        if (!(object instanceof Raza)) {
            return false;
        }
        Raza other = (Raza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Raza[ id=" + id + " ]";
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
    
}

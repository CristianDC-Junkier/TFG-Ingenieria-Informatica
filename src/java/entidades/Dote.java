
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
@Table(name = "DOTE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dote.findAll", query = "SELECT d FROM Dote d order by d.nombre"),
    @NamedQuery(name = "Dote.findById", query = "SELECT d FROM Dote d WHERE d.id = :id"),
    @NamedQuery(name = "Dote.findByNombre", query = "SELECT d FROM Dote d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Dote.findByRaza", query = "SELECT d FROM Dote d WHERE d.raza = :raza"),
    @NamedQuery(name = "Dote.findByElegiratr", query = "SELECT d FROM Dote d WHERE d.elegiratr = :elegiratr")})
public class Dote implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RAZA", nullable = false, length = 100)
    private String raza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELEGIRATR", nullable = false, length = 1)
    private String elegiratr;
    @JoinTable(name = "AUMENTADOTE", joinColumns = {
        @JoinColumn(name = "DOTE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Atributo> atributosList;
    @JoinTable(name = "COMPETEDOTE", joinColumns = {
        @JoinColumn(name = "DOTE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Habilidad> habilidadesList;
    @ManyToMany(mappedBy = "dotesList")
    private List<Personaje> personajesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    public Dote() {
    }

    public Dote(String id) {
        this.id = id;
    }

    public Dote(String id, String nombre, String descripcion, String raza, String elegiratr) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raza = raza;
        this.elegiratr = elegiratr;
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
        if (!(object instanceof Dote)) {
            return false;
        }
        Dote other = (Dote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Dote[ id=" + id + " ]";
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

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getElegiratr() {
        return elegiratr;
    }

    public void setElegiratr(String elegiratr) {
        this.elegiratr = elegiratr;
    }

    @XmlTransient
    public List<Atributo> getAtributosList() {
        return atributosList;
    }

    public void setAtributosList(List<Atributo> atributosList) {
        this.atributosList = atributosList;
    }

    @XmlTransient
    public List<Habilidad> getHabilidadesList() {
        return habilidadesList;
    }

    public void setHabilidadesList(List<Habilidad> habilidadesList) {
        this.habilidadesList = habilidadesList;
    }
    
}

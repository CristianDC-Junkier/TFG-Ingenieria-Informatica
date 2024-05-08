
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ATRIBUTO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atributo.findAll", query = "SELECT a FROM Atributo a"),
    @NamedQuery(name = "Atributo.findById", query = "SELECT a FROM Atributo a WHERE a.id = :id"),
    @NamedQuery(name = "Atributo.findByNombre", query = "SELECT a FROM Atributo a WHERE a.nombre = :nombre")})
public class Atributo implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributos")
    private List<Personajeatributooriginal> personajeatributosoriginalList;
    @ManyToMany(mappedBy = "atributosList")
    private List<Dote> dotesList;
    @OneToMany(mappedBy = "atributo")
    private List<Habilidad> habilidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributos")
    private List<Personajeatributo> personajeatributosList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @ManyToMany(mappedBy = "atributosList")
    private List<Clase> clasesList;

    public Atributo() {
    }

    public Atributo(String id) {
        this.id = id;
    }

    public Atributo(String id, String nombre, String descripcion) {
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
    public List<Clase> getClasesList() {
        return clasesList;
    }

    public void setClasesList(List<Clase> clasesList) {
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
        if (!(object instanceof Atributo)) {
            return false;
        }
        Atributo other = (Atributo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Atributo[ id=" + id + " ]";
    }


    @XmlTransient
    public List<Habilidad> getHabilidadesList() {
        return habilidadesList;
    }

    public void setHabilidadesList(List<Habilidad> habilidadesList) {
        this.habilidadesList = habilidadesList;
    }

    @XmlTransient
    public List<Personajeatributo> getPersonajeatributosList() {
        return personajeatributosList;
    }

    public void setPersonajeatributosList(List<Personajeatributo> personajeatributosList) {
        this.personajeatributosList = personajeatributosList;
    }


    @XmlTransient
    public List<Dote> getDotesList() {
        return dotesList;
    }

    public void setDotesList(List<Dote> dotesList) {
        this.dotesList = dotesList;
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
    public List<Personajeatributooriginal> getPersonajeatributosoriginalList() {
        return personajeatributosoriginalList;
    }

    public void setPersonajeatributosoriginalList(List<Personajeatributooriginal> personajeatributosoriginalList) {
        this.personajeatributosoriginalList = personajeatributosoriginalList;
    }
    
}

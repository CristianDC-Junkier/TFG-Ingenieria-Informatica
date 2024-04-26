package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "SECCION", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TITULO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findById", query = "SELECT s FROM Seccion s WHERE s.id = :id"),
    @NamedQuery(name = "Seccion.findByTitulo", query = "SELECT s FROM Seccion s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "Seccion.findByNumerohilosmax", query = "SELECT s FROM Seccion s WHERE s.numerohilosmax = :numerohilosmax")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "TITULO", nullable = false, length = 80)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROHILOSMAX", nullable = false)
    private Integer numerohilosmax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccion")
    private List<Hilo> hiloList;

    public Seccion() {
    }

    public Seccion(String id) {
        this.id = id;
    }

    public Seccion(String id, String titulo, Integer numerohilosmax) {
        this.id = id;
        this.titulo = titulo;
        this.numerohilosmax = numerohilosmax;
    }

    public Seccion(String titulo, Integer numerohilosmax) {
        this.titulo = titulo;
        this.numerohilosmax = numerohilosmax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumerohilosmax() {
        return numerohilosmax;
    }

    public void setNumerohilosmax(Integer numerohilosmax) {
        this.numerohilosmax = numerohilosmax;
    }

    @XmlTransient
    public List<Hilo> getHiloList() {
        return hiloList;
    }

    public void setHiloList(List<Hilo> hiloList) {
        this.hiloList = hiloList;
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
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Seccion[ id=" + id + " ]";
    }

}

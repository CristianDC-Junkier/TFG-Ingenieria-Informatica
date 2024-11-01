package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "REQUISITODOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requisitodote.findAll", query = "SELECT r FROM Requisitodote r"),
    @NamedQuery(name = "Requisitodote.findById", query = "SELECT r FROM Requisitodote r WHERE r.id = :id"),
    @NamedQuery(name = "Requisitodote.findByNombre", query = "SELECT r FROM Requisitodote r WHERE r.nombre = :nombre")})
public class Requisitodote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Size(max = 100)
    @Column(name = "NOMBRE", length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "VALOR", nullable = false)
    private String valor;

    public Requisitodote() {
    }

    public Requisitodote(String id) {
        this.id = id;
    }

    public Requisitodote(String id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        if (!(object instanceof Requisitodote)) {
            return false;
        }
        Requisitodote other = (Requisitodote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Requisitosdote[ id=" + id + " ]";
    }
    
}


package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ALCANCE", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alcance.findAll", query = "SELECT a FROM Alcance a"),
    @NamedQuery(name = "Alcance.findById", query = "SELECT a FROM Alcance a WHERE a.id = :id"),
    @NamedQuery(name = "Alcance.findByNombre", query = "SELECT a FROM Alcance a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alcance.findByMinimo", query = "SELECT a FROM Alcance a WHERE a.minimo = :minimo"),
    @NamedQuery(name = "Alcance.findByMaximo", query = "SELECT a FROM Alcance a WHERE a.maximo = :maximo")})
public class Alcance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MINIMO", nullable = false)
    private BigInteger minimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXIMO", nullable = false)
    private BigInteger maximo;

    public Alcance() {
    }

    public Alcance(String id) {
        this.id = id;
    }

    public Alcance(String id, String nombre, BigInteger minimo, BigInteger maximo) {
        this.id = id;
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
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

    public BigInteger getMinimo() {
        return minimo;
    }

    public void setMinimo(BigInteger minimo) {
        this.minimo = minimo;
    }

    public BigInteger getMaximo() {
        return maximo;
    }

    public void setMaximo(BigInteger maximo) {
        this.maximo = maximo;
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
        if (!(object instanceof Alcance)) {
            return false;
        }
        Alcance other = (Alcance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Alcance[ id=" + id + " ]";
    }
    
}

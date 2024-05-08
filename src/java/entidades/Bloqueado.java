
package entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "BLOQUEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bloqueado.findAll", query = "SELECT b FROM Bloqueado b"),
    @NamedQuery(name = "Bloqueado.findByBloqueador", query = "SELECT b FROM Bloqueado b WHERE b.bloqueadosPK.bloqueador = :bloqueador"),
    @NamedQuery(name = "Bloqueado.findByBloqueado", query = "SELECT b FROM Bloqueado b WHERE b.bloqueadosPK.bloqueado = :bloqueado"),
    @NamedQuery(name = "Bloqueado.findByBloqueados", query = "SELECT b FROM Bloqueado b WHERE b.bloqueadosPK.bloqueador = :bloqueador and b.bloqueadosPK.bloqueado = :bloqueado")
})
public class Bloqueado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BloqueadoPK bloqueadosPK;

    public Bloqueado() {
    }

    public Bloqueado(BloqueadoPK bloqueadosPK) {
        this.bloqueadosPK = bloqueadosPK;
    }

    public Bloqueado(String bloqueador, String bloqueado) {
        this.bloqueadosPK = new BloqueadoPK(bloqueador, bloqueado);
    }

    public BloqueadoPK getBloqueadosPK() {
        return bloqueadosPK;
    }

    public void setBloqueadosPK(BloqueadoPK bloqueadosPK) {
        this.bloqueadosPK = bloqueadosPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloqueadosPK != null ? bloqueadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloqueado)) {
            return false;
        }
        Bloqueado other = (Bloqueado) object;
        if ((this.bloqueadosPK == null && other.bloqueadosPK != null) || (this.bloqueadosPK != null && !this.bloqueadosPK.equals(other.bloqueadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bloqueado[ bloqueadosPK=" + bloqueadosPK + " ]";
    }
    
}

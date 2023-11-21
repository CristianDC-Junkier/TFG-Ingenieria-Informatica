
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
@Table(name = "BLOQUEADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bloqueados.findAll", query = "SELECT b FROM Bloqueados b"),
    @NamedQuery(name = "Bloqueados.findByBloqueador", query = "SELECT b FROM Bloqueados b WHERE b.bloqueadosPK.bloqueador = :bloqueador"),
    @NamedQuery(name = "Bloqueados.findByBloqueado", query = "SELECT b FROM Bloqueados b WHERE b.bloqueadosPK.bloqueado = :bloqueado")})
public class Bloqueados implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BloqueadosPK bloqueadosPK;

    public Bloqueados() {
    }

    public Bloqueados(BloqueadosPK bloqueadosPK) {
        this.bloqueadosPK = bloqueadosPK;
    }

    public Bloqueados(String bloqueador, String bloqueado) {
        this.bloqueadosPK = new BloqueadosPK(bloqueador, bloqueado);
    }

    public BloqueadosPK getBloqueadosPK() {
        return bloqueadosPK;
    }

    public void setBloqueadosPK(BloqueadosPK bloqueadosPK) {
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
        if (!(object instanceof Bloqueados)) {
            return false;
        }
        Bloqueados other = (Bloqueados) object;
        if ((this.bloqueadosPK == null && other.bloqueadosPK != null) || (this.bloqueadosPK != null && !this.bloqueadosPK.equals(other.bloqueadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bloqueados[ bloqueadosPK=" + bloqueadosPK + " ]";
    }
    
}

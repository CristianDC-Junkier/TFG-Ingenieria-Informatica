
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
@Table(name = "AMIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amigo.findAll", query = "SELECT a FROM Amigo a"),
    @NamedQuery(name = "Amigo.findByAmigo1", query = "SELECT a FROM Amigo a WHERE a.amigosPK.amigo1 = :amigo1"),
    @NamedQuery(name = "Amigo.findByAmigo2", query = "SELECT a FROM Amigo a WHERE a.amigosPK.amigo2 = :amigo2"),
    @NamedQuery(name = "Amigo.findByAmigos", query = "SELECT a FROM Amigo a WHERE a.amigosPK.amigo1 = :amigo1 and a.amigosPK.amigo2 = :amigo2")
})
public class Amigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmigoPK amigosPK;

    public Amigo() {
    }

    public Amigo(AmigoPK amigosPK) {
        this.amigosPK = amigosPK;
    }

    public Amigo(String amigo1, String amigo2) {
        this.amigosPK = new AmigoPK(amigo1, amigo2);
    }

    public AmigoPK getAmigosPK() {
        return amigosPK;
    }

    public void setAmigosPK(AmigoPK amigosPK) {
        this.amigosPK = amigosPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amigosPK != null ? amigosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amigo)) {
            return false;
        }
        Amigo other = (Amigo) object;
        if ((this.amigosPK == null && other.amigosPK != null) || (this.amigosPK != null && !this.amigosPK.equals(other.amigosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Amigo[ amigosPK=" + amigosPK + " ]";
    }
    
}

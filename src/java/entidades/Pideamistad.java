
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
@Table(name = "PIDEAMISTAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pideamistad.findAll", query = "SELECT p FROM Pideamistad p"),
    @NamedQuery(name = "Pideamistad.findByPide", query = "SELECT p FROM Pideamistad p WHERE p.pideamistadPK.pide = :pide"),
    @NamedQuery(name = "Pideamistad.findByAcepta", query = "SELECT p FROM Pideamistad p WHERE p.pideamistadPK.acepta = :acepta")})
public class Pideamistad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PideamistadPK pideamistadPK;

    public Pideamistad() {
    }

    public Pideamistad(PideamistadPK pideamistadPK) {
        this.pideamistadPK = pideamistadPK;
    }

    public Pideamistad(String pide, String acepta) {
        this.pideamistadPK = new PideamistadPK(pide, acepta);
    }

    public PideamistadPK getPideamistadPK() {
        return pideamistadPK;
    }

    public void setPideamistadPK(PideamistadPK pideamistadPK) {
        this.pideamistadPK = pideamistadPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pideamistadPK != null ? pideamistadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pideamistad)) {
            return false;
        }
        Pideamistad other = (Pideamistad) object;
        if ((this.pideamistadPK == null && other.pideamistadPK != null) || (this.pideamistadPK != null && !this.pideamistadPK.equals(other.pideamistadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pideamistad[ pideamistadPK=" + pideamistadPK + " ]";
    }
    
}

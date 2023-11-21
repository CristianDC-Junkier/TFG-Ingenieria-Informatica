
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Embeddable
public class PideamistadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PIDE")
    private String pide;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ACEPTA")
    private String acepta;

    public PideamistadPK() {
    }

    public PideamistadPK(String pide, String acepta) {
        this.pide = pide;
        this.acepta = acepta;
    }

    public String getPide() {
        return pide;
    }

    public void setPide(String pide) {
        this.pide = pide;
    }

    public String getAcepta() {
        return acepta;
    }

    public void setAcepta(String acepta) {
        this.acepta = acepta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pide != null ? pide.hashCode() : 0);
        hash += (acepta != null ? acepta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PideamistadPK)) {
            return false;
        }
        PideamistadPK other = (PideamistadPK) object;
        if ((this.pide == null && other.pide != null) || (this.pide != null && !this.pide.equals(other.pide))) {
            return false;
        }
        if ((this.acepta == null && other.acepta != null) || (this.acepta != null && !this.acepta.equals(other.acepta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PideamistadPK[ pide=" + pide + ", acepta=" + acepta + " ]";
    }
    
}

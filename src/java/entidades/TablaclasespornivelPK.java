
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
public class TablaclasespornivelPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "SUBCLASES", nullable = false, length = 36)
    private String subclases;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private short nivel;


    public TablaclasespornivelPK() {
    }

    public TablaclasespornivelPK(String subclases, short nivel) {
        this.subclases = subclases;
        this.nivel = nivel;
    }

    public String getSubclases() {
        return subclases;
    }

    public void setSubclases(String subclases) {
        this.subclases = subclases;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subclases != null ? subclases.hashCode() : 0);
        hash += (int) nivel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaclasespornivelPK)) {
            return false;
        }
        TablaclasespornivelPK other = (TablaclasespornivelPK) object;
        if ((this.subclases == null && other.subclases != null) || (this.subclases != null && !this.subclases.equals(other.subclases))) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TablaclasespornivelPK[ subclases=" + subclases + ", nivel=" + nivel + " ]";
    }
    
}

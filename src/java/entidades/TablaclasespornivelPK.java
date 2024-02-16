
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
    @Column(name = "CLASES", nullable = false, length = 36)
    private String clases;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "SUBCLASES", nullable = false, length = 36)
    private String subclases;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private BigInteger nivel;

    public TablaclasespornivelPK() {
    }

    public TablaclasespornivelPK(String clases, String subclases, BigInteger nivel) {
        this.clases = clases;
        this.subclases = subclases;
        this.nivel = nivel;
    }

    public String getClases() {
        return clases;
    }

    public void setClases(String clases) {
        this.clases = clases;
    }

    public String getSubclases() {
        return subclases;
    }

    public void setSubclases(String subclases) {
        this.subclases = subclases;
    }

    public BigInteger getNivel() {
        return nivel;
    }

    public void setNivel(BigInteger nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clases != null ? clases.hashCode() : 0);
        hash += (subclases != null ? subclases.hashCode() : 0);
        hash += (nivel != null ? nivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaclasespornivelPK)) {
            return false;
        }
        TablaclasespornivelPK other = (TablaclasespornivelPK) object;
        if ((this.clases == null && other.clases != null) || (this.clases != null && !this.clases.equals(other.clases))) {
            return false;
        }
        if ((this.subclases == null && other.subclases != null) || (this.subclases != null && !this.subclases.equals(other.subclases))) {
            return false;
        }
        if ((this.nivel == null && other.nivel != null) || (this.nivel != null && !this.nivel.equals(other.nivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TablaclasespornivelPK[ clases=" + clases + ", subclases=" + subclases + ", nivel=" + nivel + " ]";
    }
    
}

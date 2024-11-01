
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
public class TienemonstruoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "MONSTRUO", nullable = false, length = 36)
    private String monstruo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ATRIBUTO", nullable = false, length = 100)
    private String atributo;

    public TienemonstruoPK() {
    }

    public TienemonstruoPK(String monstruo, String atributo) {
        this.monstruo = monstruo;
        this.atributo = atributo;
    }

    public String getMonstruo() {
        return monstruo;
    }

    public void setMonstruo(String monstruo) {
        this.monstruo = monstruo;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monstruo != null ? monstruo.hashCode() : 0);
        hash += (atributo != null ? atributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TienemonstruoPK)) {
            return false;
        }
        TienemonstruoPK other = (TienemonstruoPK) object;
        if ((this.monstruo == null && other.monstruo != null) || (this.monstruo != null && !this.monstruo.equals(other.monstruo))) {
            return false;
        }
        if ((this.atributo == null && other.atributo != null) || (this.atributo != null && !this.atributo.equals(other.atributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TienemonstruoPK[ monstruo=" + monstruo + ", atributo=" + atributo + " ]";
    }
    
}

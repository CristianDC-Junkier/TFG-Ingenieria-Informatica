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
public class SumarazaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "SUBRAZA", nullable = false, length = 36)
    private String subraza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ATRIBUTO", nullable = false, length = 100)
    private String atributo;

    public SumarazaPK() {
    }

    public SumarazaPK(String subraza, String atributo) {
        this.subraza = subraza;
        this.atributo = atributo;
    }

    public String getSubraza() {
        return subraza;
    }

    public void setSubraza(String subraza) {
        this.subraza = subraza;
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
        hash += (subraza != null ? subraza.hashCode() : 0);
        hash += (atributo != null ? atributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SumarazaPK)) {
            return false;
        }
        SumarazaPK other = (SumarazaPK) object;
        if ((this.subraza == null && other.subraza != null) || (this.subraza != null && !this.subraza.equals(other.subraza))) {
            return false;
        }
        if ((this.atributo == null && other.atributo != null) || (this.atributo != null && !this.atributo.equals(other.atributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SumarazaPK[ subraza=" + subraza + ", atributo=" + atributo + " ]";
    }
    
}

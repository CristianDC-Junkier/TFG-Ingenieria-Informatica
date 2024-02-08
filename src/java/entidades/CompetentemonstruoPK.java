
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
public class CompetentemonstruoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "MONSTRUO", nullable = false, length = 36)
    private String monstruo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "HABILIDAD", nullable = false, length = 100)
    private String habilidad;

    public CompetentemonstruoPK() {
    }

    public CompetentemonstruoPK(String monstruo, String habilidad) {
        this.monstruo = monstruo;
        this.habilidad = habilidad;
    }

    public String getMonstruo() {
        return monstruo;
    }

    public void setMonstruo(String monstruo) {
        this.monstruo = monstruo;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monstruo != null ? monstruo.hashCode() : 0);
        hash += (habilidad != null ? habilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetentemonstruoPK)) {
            return false;
        }
        CompetentemonstruoPK other = (CompetentemonstruoPK) object;
        if ((this.monstruo == null && other.monstruo != null) || (this.monstruo != null && !this.monstruo.equals(other.monstruo))) {
            return false;
        }
        if ((this.habilidad == null && other.habilidad != null) || (this.habilidad != null && !this.habilidad.equals(other.habilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CompetentemonstruoPK[ monstruo=" + monstruo + ", habilidad=" + habilidad + " ]";
    }
    
}


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
    @Column(name = "CLASE", nullable = false, length = 36)
    private String clase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private Integer nivel;

    public TablaclasespornivelPK() {
    }

    public TablaclasespornivelPK(String clase, Integer nivel) {
        this.clase = clase;
        this.nivel = nivel;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clase != null ? clase.hashCode() : 0);
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
        if ((this.clase == null && other.clase != null) || (this.clase != null && !this.clase.equals(other.clase))) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TablaclasespornivelPK[ clase=" + clase + ", nivel=" + nivel + " ]";
    }
    
}

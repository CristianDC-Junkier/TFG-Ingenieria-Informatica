
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
public class BloqueadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BLOQUEADOR")
    private String bloqueador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BLOQUEADO")
    private String bloqueado;

    public BloqueadoPK() {
    }

    public BloqueadoPK(String bloqueador, String bloqueado) {
        this.bloqueador = bloqueador;
        this.bloqueado = bloqueado;
    }

    public String getBloqueador() {
        return bloqueador;
    }

    public void setBloqueador(String bloqueador) {
        this.bloqueador = bloqueador;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloqueador != null ? bloqueador.hashCode() : 0);
        hash += (bloqueado != null ? bloqueado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueadoPK)) {
            return false;
        }
        BloqueadoPK other = (BloqueadoPK) object;
        if ((this.bloqueador == null && other.bloqueador != null) || (this.bloqueador != null && !this.bloqueador.equals(other.bloqueador))) {
            return false;
        }
        if ((this.bloqueado == null && other.bloqueado != null) || (this.bloqueado != null && !this.bloqueado.equals(other.bloqueado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.BloqueadoPK[ bloqueador=" + bloqueador + ", bloqueado=" + bloqueado + " ]";
    }
    
}

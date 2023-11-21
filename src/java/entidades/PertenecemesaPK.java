
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
public class PertenecemesaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MESA")
    private String mesa;

    public PertenecemesaPK() {
    }

    public PertenecemesaPK(String usuario, String mesa) {
        this.usuario = usuario;
        this.mesa = mesa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        hash += (mesa != null ? mesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PertenecemesaPK)) {
            return false;
        }
        PertenecemesaPK other = (PertenecemesaPK) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        if ((this.mesa == null && other.mesa != null) || (this.mesa != null && !this.mesa.equals(other.mesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PertenecemesaPK[ usuario=" + usuario + ", mesa=" + mesa + " ]";
    }
    
}

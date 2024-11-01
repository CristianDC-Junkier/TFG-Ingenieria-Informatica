
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "PERTENECEMESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pertenecemesa.findAll", query = "SELECT p FROM Pertenecemesa p"),
    @NamedQuery(name = "Pertenecemesa.findByUsuario", query = "SELECT p FROM Pertenecemesa p WHERE p.pertenecemesaPK.usuario = :usuario"),
    @NamedQuery(name = "Pertenecemesa.findByMesa", query = "SELECT p FROM Pertenecemesa p WHERE p.pertenecemesaPK.mesa = :mesa"),
    @NamedQuery(name = "Pertenecemesa.findByRol", query = "SELECT p FROM Pertenecemesa p WHERE p.rol = :rol"),
    @NamedQuery(name = "Pertenecemesa.findByRolMesa", query = "SELECT p FROM Pertenecemesa p WHERE p.rol = :rol and p.pertenecemesaPK.mesa = :mesa"),
    @NamedQuery(name = "Pertenecemesa.findByUsuarioMesa", query = "SELECT p FROM Pertenecemesa p WHERE p.pertenecemesaPK.usuario = :usuario and p.pertenecemesaPK.mesa = :mesa"),
    @NamedQuery(name = "Pertenecemesa.countByMesa", query = "SELECT COUNT(p) FROM Pertenecemesa p WHERE p.pertenecemesaPK.mesa = :mesa")
})
public class Pertenecemesa implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ROL", nullable = false, length = 50)
    private String rol;
    @JoinColumn(name = "PERSONAJEMESA", referencedColumnName = "ID")
    @ManyToOne
    private Personaje personajemesa;
    @JoinColumn(name = "MESA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mesa mesas;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarios;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PertenecemesaPK pertenecemesaPK;

    public Pertenecemesa() {
    }

    public Pertenecemesa(PertenecemesaPK pertenecemesaPK) {
        this.pertenecemesaPK = pertenecemesaPK;
    }

    public Pertenecemesa(PertenecemesaPK pertenecemesaPK, String rol) {
        this.pertenecemesaPK = pertenecemesaPK;
        this.rol = rol;
    }

    public Pertenecemesa(String usuario, String mesa, String rol) {
        this.pertenecemesaPK = new PertenecemesaPK(usuario, mesa);
        this.rol = rol;
    }

    public PertenecemesaPK getPertenecemesaPK() {
        return pertenecemesaPK;
    }

    public void setPertenecemesaPK(PertenecemesaPK pertenecemesaPK) {
        this.pertenecemesaPK = pertenecemesaPK;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pertenecemesaPK != null ? pertenecemesaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pertenecemesa)) {
            return false;
        }
        Pertenecemesa other = (Pertenecemesa) object;
        if ((this.pertenecemesaPK == null && other.pertenecemesaPK != null) || (this.pertenecemesaPK != null && !this.pertenecemesaPK.equals(other.pertenecemesaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pertenecemesa[ pertenecemesaPK=" + pertenecemesaPK + " ]";
    }


    public Mesa getMesas() {
        return mesas;
    }

    public void setMesas(Mesa mesas) {
        this.mesas = mesas;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Personaje getPersonajemesa() {
        return personajemesa;
    }

    public void setPersonajemesa(Personaje personajemesa) {
        this.personajemesa = personajemesa;
    }
    
}

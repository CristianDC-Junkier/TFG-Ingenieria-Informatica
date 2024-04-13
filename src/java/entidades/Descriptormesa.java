
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "DESCRIPTORMESA", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descriptormesa.findAll", query = "SELECT d FROM Descriptormesa d"),
    @NamedQuery(name = "Descriptormesa.findByDescripcion", query = "SELECT d FROM Descriptormesa d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Descriptormesa.findByMesa", query = "SELECT d FROM Descriptormesa d WHERE d.mesa = :mesa")})
public class Descriptormesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Lob
    @Column(name = "IMAGENDESCRIPTOR")
    private byte[] imagendescriptor;
    @Size(max = 500)
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "MESA", nullable = false, length = 36)
    private String mesa;
    @JoinColumn(name = "MESA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mesas mesas;

    public Descriptormesa() {
    }

    public Descriptormesa(String mesa) {
        this.mesa = mesa;
    }

    public byte[] getImagendescriptor() {
        return imagendescriptor;
    }

    public void setImagendescriptor(byte[] imagendescriptor) {
        this.imagendescriptor = imagendescriptor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Mesas getMesas() {
        return mesas;
    }

    public void setMesas(Mesas mesas) {
        this.mesas = mesas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mesa != null ? mesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descriptormesa)) {
            return false;
        }
        Descriptormesa other = (Descriptormesa) object;
        if ((this.mesa == null && other.mesa != null) || (this.mesa != null && !this.mesa.equals(other.mesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Descriptormesa[ mesa=" + mesa + " ]";
    }
    
}

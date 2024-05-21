package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MUSICAMESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musicamesa.findAll", query = "SELECT m FROM Musicamesa m"),
    @NamedQuery(name = "Musicamesa.findByMesa", query = "SELECT m FROM Musicamesa m WHERE m.mesa = :mesa")})
public class Musicamesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "MESA", nullable = false, length = 36)
    private String mesa;
    @JoinColumn(name = "MESA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mesa mesas;
    @JoinColumn(name = "MUSICA", referencedColumnName = "ID")
    @ManyToOne
    private Musica musica;

    public Musicamesa() {
    }

    public Musicamesa(String mesa) {
        this.mesa = mesa;
    }

    public Musicamesa(String mesa, Musica musica) {
        this.mesa = mesa;
        this.musica = musica;
    }

    public Musicamesa(Mesa mesa, Musica musica) {
        this.mesa = mesa.getId();
        this.mesas = mesa;
        this.musica = musica;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Mesa getMesas() {
        return mesas;
    }

    public void setMesas(Mesa mesas) {
        this.mesas = mesas;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
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
        if (!(object instanceof Musicamesa)) {
            return false;
        }
        Musicamesa other = (Musicamesa) object;
        if ((this.mesa == null && other.mesa != null) || (this.mesa != null && !this.mesa.equals(other.mesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Musicamesa[ mesa=" + mesa + " ]";
    }

}

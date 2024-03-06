
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "ELECCIONATRIBUTOS", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleccionatributos.findAll", query = "SELECT e FROM Eleccionatributos e"),
    @NamedQuery(name = "Eleccionatributos.findBySubraza", query = "SELECT e FROM Eleccionatributos e WHERE e.subraza = :subraza"),
    @NamedQuery(name = "Eleccionatributos.findByElegircan", query = "SELECT e FROM Eleccionatributos e WHERE e.elegircan = :elegircan"),
    @NamedQuery(name = "Eleccionatributos.findByElegirmod", query = "SELECT e FROM Eleccionatributos e WHERE e.elegirmod = :elegirmod")})
public class Eleccionatributos implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELEGIRCAN", nullable = false, length = 1)
    private String elegircan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ELEGIRMOD", nullable = false, length = 2)
    private String elegirmod;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "SUBRAZA", nullable = false, length = 36)
    private String subraza;
    @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Subrazas subrazas;

    public Eleccionatributos() {
    }

    public Eleccionatributos(String subraza) {
        this.subraza = subraza;
    }

    public Eleccionatributos(String subraza, String elegircan, String elegirmod) {
        this.subraza = subraza;
        this.elegircan = elegircan;
        this.elegirmod = elegirmod;
    }

    public String getSubraza() {
        return subraza;
    }

    public void setSubraza(String subraza) {
        this.subraza = subraza;
    }


    public Subrazas getSubrazas() {
        return subrazas;
    }

    public void setSubrazas(Subrazas subrazas) {
        this.subrazas = subrazas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subraza != null ? subraza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eleccionatributos)) {
            return false;
        }
        Eleccionatributos other = (Eleccionatributos) object;
        if ((this.subraza == null && other.subraza != null) || (this.subraza != null && !this.subraza.equals(other.subraza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Eleccionatributos[ subraza=" + subraza + " ]";
    }

    public String getElegircan() {
        return elegircan;
    }

    public void setElegircan(String elegircan) {
        this.elegircan = elegircan;
    }

    public String getElegirmod() {
        return elegirmod;
    }

    public void setElegirmod(String elegirmod) {
        this.elegirmod = elegirmod;
    }
    
}

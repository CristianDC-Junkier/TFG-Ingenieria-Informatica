
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "USASUBCLASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usasubclase.findAll", query = "SELECT u FROM Usasubclase u"),
    @NamedQuery(name = "Usasubclase.findBySubclase", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.subclase = :subclase"),
    @NamedQuery(name = "Usasubclase.findByRasgo", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.rasgo = :rasgo"),
    @NamedQuery(name = "Usasubclase.findByNivel", query = "SELECT u FROM Usasubclase u WHERE u.nivel = :nivel"),
    @NamedQuery(name = "Usasubclase.findBySubclaseNivel", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.subclase = :subclase AND u.nivel <= :nivel"),
    @NamedQuery(name = "Usasubclase.findBySubclaseNivelOnly", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.subclase = :subclase AND u.nivel = :nivel")
})
public class Usasubclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsasubclasePK usasubclasePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private short nivel;
    @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rasgo rasgos;
    @JoinColumn(name = "SUBCLASE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subclase subclases;

    public Usasubclase() {
    }

    public Usasubclase(UsasubclasePK usasubclasePK) {
        this.usasubclasePK = usasubclasePK;
    }

    public Usasubclase(UsasubclasePK usasubclasePK, short nivel) {
        this.usasubclasePK = usasubclasePK;
        this.nivel = nivel;
    }

    public Usasubclase(String subclase, String rasgo) {
        this.usasubclasePK = new UsasubclasePK(subclase, rasgo);
    }

    public UsasubclasePK getUsasubclasePK() {
        return usasubclasePK;
    }

    public void setUsasubclasePK(UsasubclasePK usasubclasePK) {
        this.usasubclasePK = usasubclasePK;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    public Rasgo getRasgos() {
        return rasgos;
    }

    public void setRasgos(Rasgo rasgos) {
        this.rasgos = rasgos;
    }

    public Subclase getSubclases() {
        return subclases;
    }

    public void setSubclases(Subclase subclases) {
        this.subclases = subclases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usasubclasePK != null ? usasubclasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usasubclase)) {
            return false;
        }
        Usasubclase other = (Usasubclase) object;
        if ((this.usasubclasePK == null && other.usasubclasePK != null) || (this.usasubclasePK != null && !this.usasubclasePK.equals(other.usasubclasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usasubclase[ usasubclasePK=" + usasubclasePK + " ]";
    }
    
}

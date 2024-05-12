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
@Table(name = "USACLASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usaclase.findAll", query = "SELECT u FROM Usaclase u"),
    @NamedQuery(name = "Usaclase.findByClase", query = "SELECT u FROM Usaclase u WHERE u.usaclasePK.clase = :clase"),
    @NamedQuery(name = "Usaclase.findByRasgo", query = "SELECT u FROM Usaclase u WHERE u.usaclasePK.rasgo = :rasgo"),
    @NamedQuery(name = "Usaclase.findByNivel", query = "SELECT u FROM Usaclase u WHERE u.nivel = :nivel"),
    @NamedQuery(name = "Usaclase.findByClaseNivel", query = "SELECT u FROM Usaclase u WHERE u.usaclasePK.clase = :clase AND u.nivel = :nivel")
})
public class Usaclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsaclasePK usaclasePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private Integer nivel;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clase clases;
    @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rasgo rasgos;

    public Usaclase() {
    }

    public Usaclase(UsaclasePK usaclasePK) {
        this.usaclasePK = usaclasePK;
    }

    public Usaclase(UsaclasePK usaclasePK, Integer nivel) {
        this.usaclasePK = usaclasePK;
        this.nivel = nivel;
    }

    public Usaclase(String clase, String rasgo) {
        this.usaclasePK = new UsaclasePK(clase, rasgo);
    }

    public UsaclasePK getUsaclasePK() {
        return usaclasePK;
    }

    public void setUsaclasePK(UsaclasePK usaclasePK) {
        this.usaclasePK = usaclasePK;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Clase getClases() {
        return clases;
    }

    public void setClases(Clase clases) {
        this.clases = clases;
    }

    public Rasgo getRasgos() {
        return rasgos;
    }

    public void setRasgos(Rasgo rasgos) {
        this.rasgos = rasgos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usaclasePK != null ? usaclasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usaclase)) {
            return false;
        }
        Usaclase other = (Usaclase) object;
        if ((this.usaclasePK == null && other.usaclasePK != null) || (this.usaclasePK != null && !this.usaclasePK.equals(other.usaclasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usaclase[ usaclasePK=" + usaclasePK + " ]";
    }

}

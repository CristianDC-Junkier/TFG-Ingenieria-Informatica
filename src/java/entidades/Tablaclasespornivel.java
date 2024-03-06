
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "TABLACLASESPORNIVEL", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablaclasespornivel.findAll", query = "SELECT t FROM Tablaclasespornivel t"),
    @NamedQuery(name = "Tablaclasespornivel.findByClases", query = "SELECT t FROM Tablaclasespornivel t WHERE t.tablaclasespornivelPK.clases = :clases"),
    @NamedQuery(name = "Tablaclasespornivel.findBySubclases", query = "SELECT t FROM Tablaclasespornivel t WHERE t.tablaclasespornivelPK.subclases = :subclases"),
    @NamedQuery(name = "Tablaclasespornivel.findByNivel", query = "SELECT t FROM Tablaclasespornivel t WHERE t.tablaclasespornivelPK.nivel = :nivel"),
})
public class Tablaclasespornivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TablaclasespornivelPK tablaclasespornivelPK;
    @JoinColumn(name = "CLASES", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clases clases1;
    @JoinColumn(name = "SUBCLASES", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subclases subclases1;
    @JoinColumn(name = "TABLACLASES", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tablaclases tablaclases;

    public Tablaclasespornivel() {
    }

    public Tablaclasespornivel(TablaclasespornivelPK tablaclasespornivelPK) {
        this.tablaclasespornivelPK = tablaclasespornivelPK;
    }

    public Tablaclasespornivel(String clases, String subclases, BigInteger nivel) {
        this.tablaclasespornivelPK = new TablaclasespornivelPK(clases, subclases, nivel);
    }

    public TablaclasespornivelPK getTablaclasespornivelPK() {
        return tablaclasespornivelPK;
    }

    public void setTablaclasespornivelPK(TablaclasespornivelPK tablaclasespornivelPK) {
        this.tablaclasespornivelPK = tablaclasespornivelPK;
    }

    public Clases getClases1() {
        return clases1;
    }

    public void setClases1(Clases clases1) {
        this.clases1 = clases1;
    }

    public Subclases getSubclases1() {
        return subclases1;
    }

    public void setSubclases1(Subclases subclases1) {
        this.subclases1 = subclases1;
    }

    public Tablaclases getTablaclases() {
        return tablaclases;
    }

    public void setTablaclases(Tablaclases tablaclases) {
        this.tablaclases = tablaclases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablaclasespornivelPK != null ? tablaclasespornivelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablaclasespornivel)) {
            return false;
        }
        Tablaclasespornivel other = (Tablaclasespornivel) object;
        if ((this.tablaclasespornivelPK == null && other.tablaclasespornivelPK != null) || (this.tablaclasespornivelPK != null && !this.tablaclasespornivelPK.equals(other.tablaclasespornivelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tablaclasespornivel[ tablaclasespornivelPK=" + tablaclasespornivelPK + " ]";
    }
    
}

package entidades;

import java.io.Serializable;
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
@Table(name = "TABLACLASEPORNIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablaclasepornivel.findAll", query = "SELECT t FROM Tablaclasepornivel t"),
    @NamedQuery(name = "Tablaclasepornivel.findByClase", query = "SELECT t FROM Tablaclasepornivel t WHERE t.tablaclasespornivelPK.clase = :clase"),
    @NamedQuery(name = "Tablaclasepornivel.findByNivel", query = "SELECT t FROM Tablaclasepornivel t WHERE t.tablaclasespornivelPK.nivel = :nivel"),
    @NamedQuery(name = "Tablaclasepornivel.findByClaseNivel", query = "SELECT t FROM Tablaclasepornivel t WHERE t.tablaclasespornivelPK.clase = :clase AND t.tablaclasespornivelPK.nivel = :nivel")
})
public class Tablaclasepornivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TablaclasepornivelPK tablaclasespornivelPK;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clase clases;
    @JoinColumn(name = "TABLACLASE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tablaclase tablaclases;

    public Tablaclasepornivel() {
    }

    public Tablaclasepornivel(TablaclasepornivelPK tablaclasespornivelPK) {
        this.tablaclasespornivelPK = tablaclasespornivelPK;
    }

    public Tablaclasepornivel(String clase, Integer nivel) {
        this.tablaclasespornivelPK = new TablaclasepornivelPK(clase, nivel);
    }

    public TablaclasepornivelPK getTablaclasespornivelPK() {
        return tablaclasespornivelPK;
    }

    public void setTablaclasespornivelPK(TablaclasepornivelPK tablaclasespornivelPK) {
        this.tablaclasespornivelPK = tablaclasespornivelPK;
    }

    public Clase getClases() {
        return clases;
    }

    public void setClases(Clase clases) {
        this.clases = clases;
    }

    public Tablaclase getTablaclases() {
        return tablaclases;
    }

    public void setTablaclases(Tablaclase tablaclases) {
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
        if (!(object instanceof Tablaclasepornivel)) {
            return false;
        }
        Tablaclasepornivel other = (Tablaclasepornivel) object;
        if ((this.tablaclasespornivelPK == null && other.tablaclasespornivelPK != null) || (this.tablaclasespornivelPK != null && !this.tablaclasespornivelPK.equals(other.tablaclasespornivelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tablaclasepornivel[ tablaclasespornivelPK=" + tablaclasespornivelPK + " ]";
    }
    
}

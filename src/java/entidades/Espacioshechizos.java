package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Espacioshechizos.findAll", query = "SELECT e FROM Espacioshechizos e"),
    @NamedQuery(name = "Espacioshechizos.findById", query = "SELECT e FROM Espacioshechizos e WHERE e.id = :id"),
    @NamedQuery(name = "Espacioshechizos.findByNv1", query = "SELECT e FROM Espacioshechizos e WHERE e.nv1 = :nv1"),
    @NamedQuery(name = "Espacioshechizos.findByNv2", query = "SELECT e FROM Espacioshechizos e WHERE e.nv2 = :nv2"),
    @NamedQuery(name = "Espacioshechizos.findByNv3", query = "SELECT e FROM Espacioshechizos e WHERE e.nv3 = :nv3"),
    @NamedQuery(name = "Espacioshechizos.findByNv4", query = "SELECT e FROM Espacioshechizos e WHERE e.nv4 = :nv4"),
    @NamedQuery(name = "Espacioshechizos.findByNv5", query = "SELECT e FROM Espacioshechizos e WHERE e.nv5 = :nv5"),
    @NamedQuery(name = "Espacioshechizos.findByNv6", query = "SELECT e FROM Espacioshechizos e WHERE e.nv6 = :nv6"),
    @NamedQuery(name = "Espacioshechizos.findByNv7", query = "SELECT e FROM Espacioshechizos e WHERE e.nv7 = :nv7"),
    @NamedQuery(name = "Espacioshechizos.findByNv8", query = "SELECT e FROM Espacioshechizos e WHERE e.nv8 = :nv8"),
    @NamedQuery(name = "Espacioshechizos.findByNv9", query = "SELECT e FROM Espacioshechizos e WHERE e.nv9 = :nv9")})
public class Espacioshechizos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv1;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv2;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv3;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv4;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv5;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv6;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv7;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv8;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private short nv9;
    @JoinTable(name = "TABLAHECHIZOS", joinColumns = {
        @JoinColumn(name = "ESPACIOSHECHIZOS", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TABLACLASES", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tablaclases> tablaclasesList;

    public Espacioshechizos() {
    }

    public Espacioshechizos(String id) {
        this.id = id;
    }

    public Espacioshechizos(String id, short nv1, short nv2, short nv3, short nv4, short nv5, short nv6, short nv7, short nv8, short nv9) {
        this.id = id;
        this.nv1 = nv1;
        this.nv2 = nv2;
        this.nv3 = nv3;
        this.nv4 = nv4;
        this.nv5 = nv5;
        this.nv6 = nv6;
        this.nv7 = nv7;
        this.nv8 = nv8;
        this.nv9 = nv9;
    }

    public Espacioshechizos(short nv1, short nv2, short nv3, short nv4, short nv5, short nv6, short nv7, short nv8, short nv9) {
        this.nv1 = nv1;
        this.nv2 = nv2;
        this.nv3 = nv3;
        this.nv4 = nv4;
        this.nv5 = nv5;
        this.nv6 = nv6;
        this.nv7 = nv7;
        this.nv8 = nv8;
        this.nv9 = nv9;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public short getNv1() {
        return nv1;
    }

    public void setNv1(short nv1) {
        this.nv1 = nv1;
    }

    public short getNv2() {
        return nv2;
    }

    public void setNv2(short nv2) {
        this.nv2 = nv2;
    }

    public short getNv3() {
        return nv3;
    }

    public void setNv3(short nv3) {
        this.nv3 = nv3;
    }

    public short getNv4() {
        return nv4;
    }

    public void setNv4(short nv4) {
        this.nv4 = nv4;
    }

    public short getNv5() {
        return nv5;
    }

    public void setNv5(short nv5) {
        this.nv5 = nv5;
    }

    public short getNv6() {
        return nv6;
    }

    public void setNv6(short nv6) {
        this.nv6 = nv6;
    }

    public short getNv7() {
        return nv7;
    }

    public void setNv7(short nv7) {
        this.nv7 = nv7;
    }

    public short getNv8() {
        return nv8;
    }

    public void setNv8(short nv8) {
        this.nv8 = nv8;
    }

    public short getNv9() {
        return nv9;
    }

    public void setNv9(short nv9) {
        this.nv9 = nv9;
    }

    @XmlTransient
    public List<Tablaclases> getTablaclasesList() {
        return tablaclasesList;
    }

    public void setTablaclasesList(List<Tablaclases> tablaclasesList) {
        this.tablaclasesList = tablaclasesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espacioshechizos)) {
            return false;
        }
        Espacioshechizos other = (Espacioshechizos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Espacioshechizos[ id=" + id + " ]";
    }

}

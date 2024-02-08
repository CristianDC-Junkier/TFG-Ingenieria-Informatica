package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "CLASES", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clases.findAll", query = "SELECT c FROM Clases c ORDER BY c.nombre"),
    @NamedQuery(name = "Clases.findById", query = "SELECT c FROM Clases c WHERE c.id = :id"),
    @NamedQuery(name = "Clases.findByNombre", query = "SELECT c FROM Clases c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clases.findByDpg", query = "SELECT c FROM Clases c WHERE c.dpg = :dpg"),
    @NamedQuery(name = "Clases.findByComparmas", query = "SELECT c FROM Clases c WHERE c.comparmas = :comparmas"),
    @NamedQuery(name = "Clases.findByComparmaduras", query = "SELECT c FROM Clases c WHERE c.comparmaduras = :comparmaduras"),
    @NamedQuery(name = "Clases.findByCompherramientas", query = "SELECT c FROM Clases c WHERE c.compherramientas = :compherramientas"),
    @NamedQuery(name = "Clases.findByHabhechizos", query = "SELECT c FROM Clases c WHERE c.habhechizos = :habhechizos"),
    @NamedQuery(name = "Clases.findByEquipoinicial", query = "SELECT c FROM Clases c WHERE c.equipoinicial = :equipoinicial"),
    @NamedQuery(name = "Clases.findByOroinicial", query = "SELECT c FROM Clases c WHERE c.oroinicial = :oroinicial")})
public class Clases implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob()
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Size(max = 3)
    @Column(name = "DPG", length = 3)
    private String dpg;
    @Size(max = 255)
    @Column(name = "COMPARMAS", length = 255)
    private String comparmas;
    @Size(max = 255)
    @Column(name = "COMPARMADURAS", length = 255)
    private String comparmaduras;
    @Size(max = 255)
    @Column(name = "COMPHERRAMIENTAS", length = 255)
    private String compherramientas;
    @Size(max = 20)
    @Column(name = "HABHECHIZOS", length = 20)
    private String habhechizos;
    @Size(max = 500)
    @Column(name = "EQUIPOINICIAL", length = 500)
    private String equipoinicial;
    @Size(max = 15)
    @Column(name = "OROINICIAL", length = 15)
    private String oroinicial;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    
    @ManyToMany(mappedBy = "clasesList", fetch = FetchType.LAZY)
    private List<Hechizos> hechizosList;

    @JoinTable(name = "SUSSUBCLASESSON", joinColumns = {
        @JoinColumn(name = "CLASES", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "SUBCLASES", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subclases> subclasesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clases", fetch = FetchType.LAZY)
    private List<Tablaclasespornivel> tablaclasespornivelList;

    public Clases() {
    }

    public Clases(String id) {
        this.id = id;
    }

    public Clases(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Clases(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Clases(String nombre, String descripcion, String dpg, String comparmas, String comparmaduras, String compherramientas, String habhechizos, String equipoinicial, String oroinicial, List<Subclases> subclasesList, List<Tablaclasespornivel> tablaclasespornivelList) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dpg = dpg;
        this.comparmas = comparmas;
        this.comparmaduras = comparmaduras;
        this.compherramientas = compherramientas;
        this.habhechizos = habhechizos;
        this.equipoinicial = equipoinicial;
        this.oroinicial = oroinicial;
        this.subclasesList = subclasesList;
        this.tablaclasespornivelList = tablaclasespornivelList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(object instanceof Clases)) {
            return false;
        }
        Clases other = (Clases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Clases[ id=" + id + " ]";
    }
    @XmlTransient
    public List<Subclases> getSubclasesList() {
        return subclasesList;
    }
    public void setSubclasesList(List<Subclases> subclasesList) {
        this.subclasesList = subclasesList;
    }
    @XmlTransient
    public List<Tablaclasespornivel> getTablaclasespornivelList() {
        return tablaclasespornivelList;
    }
    public void setTablaclasespornivelList(List<Tablaclasespornivel> tablaclasespornivelList) {
        this.tablaclasespornivelList = tablaclasespornivelList;
    }
    @XmlTransient
    public List<Hechizos> getHechizosList() {
        return hechizosList;
    }
    public void setHechizosList(List<Hechizos> hechizosList) {
        this.hechizosList = hechizosList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDpg() {
        return dpg;
    }

    public void setDpg(String dpg) {
        this.dpg = dpg;
    }

    public String getComparmas() {
        return comparmas;
    }

    public void setComparmas(String comparmas) {
        this.comparmas = comparmas;
    }

    public String getComparmaduras() {
        return comparmaduras;
    }

    public void setComparmaduras(String comparmaduras) {
        this.comparmaduras = comparmaduras;
    }

    public String getCompherramientas() {
        return compherramientas;
    }

    public void setCompherramientas(String compherramientas) {
        this.compherramientas = compherramientas;
    }

    public String getHabhechizos() {
        return habhechizos;
    }

    public void setHabhechizos(String habhechizos) {
        this.habhechizos = habhechizos;
    }

    public String getEquipoinicial() {
        return equipoinicial;
    }

    public void setEquipoinicial(String equipoinicial) {
        this.equipoinicial = equipoinicial;
    }

    public String getOroinicial() {
        return oroinicial;
    }

    public void setOroinicial(String oroinicial) {
        this.oroinicial = oroinicial;
    }

}

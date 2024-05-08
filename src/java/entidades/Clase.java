
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "CLASE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c ORDER BY c.nombre"),
    @NamedQuery(name = "Clase.findById", query = "SELECT c FROM Clase c WHERE c.id = :id"),
    @NamedQuery(name = "Clase.findByNombre", query = "SELECT c FROM Clase c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clase.findByDpg", query = "SELECT c FROM Clase c WHERE c.dpg = :dpg"),
    @NamedQuery(name = "Clase.findByComparmas", query = "SELECT c FROM Clase c WHERE c.comparmas = :comparmas"),
    @NamedQuery(name = "Clase.findByComparmaduras", query = "SELECT c FROM Clase c WHERE c.comparmaduras = :comparmaduras"),
    @NamedQuery(name = "Clase.findByCompherramientas", query = "SELECT c FROM Clase c WHERE c.compherramientas = :compherramientas"),
    @NamedQuery(name = "Clase.findByHabhechizos", query = "SELECT c FROM Clase c WHERE c.habhechizos = :habhechizos"),
    @NamedQuery(name = "Clase.findByEquipoinicial", query = "SELECT c FROM Clase c WHERE c.equipoinicial = :equipoinicial"),
    @NamedQuery(name = "Clase.findByOroinicial", query = "SELECT c FROM Clase c WHERE c.oroinicial = :oroinicial"),
    @NamedQuery(name = "Clase.findByElegirhab", query = "SELECT c FROM Clase c WHERE c.elegirhab = :elegirhab"),
    @NamedQuery(name = "Clase.findByNivelsubclase", query = "SELECT c FROM Clase c WHERE c.nivelsubclase = :nivelsubclase")})
public class Clase implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Lob
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
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 2)
    @Column(name = "ELEGIRHAB", nullable = false, length = 2)
    private String elegirhab;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "NIVELSUBCLASE", nullable = false)
    private Integer nivelsubclase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<Personaje> personajesList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @JoinTable(name = "DACOMPETENCIACLASE", joinColumns = {
        @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Habilidad> habilidadesList;
    @ManyToMany(mappedBy = "clasesList")
    private List<Hechizo> hechizosList;
    @JoinTable(name = "DASALVACIONCLASE", joinColumns = {
        @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Atributo> atributosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<Subclase> subclasesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clases")
    private List<Usaclase> usaclaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clases")
    private List<Tablaclasepornivel> tablaclasespornivelList;

    public Clase() {
    }

    public Clase(String id) {
        this.id = id;
    }

    public Clase(String id, String nombre, String descripcion, String elegirhab, Integer nivelsubclase) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.elegirhab = elegirhab;
        this.nivelsubclase = nivelsubclase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @XmlTransient
    public List<Habilidad> getHabilidadesList() {
        return habilidadesList;
    }

    public void setHabilidadesList(List<Habilidad> habilidadesList) {
        this.habilidadesList = habilidadesList;
    }

    @XmlTransient
    public List<Hechizo> getHechizosList() {
        return hechizosList;
    }

    public void setHechizosList(List<Hechizo> hechizosList) {
        this.hechizosList = hechizosList;
    }

    @XmlTransient
    public List<Atributo> getAtributosList() {
        return atributosList;
    }

    public void setAtributosList(List<Atributo> atributosList) {
        this.atributosList = atributosList;
    }

    @XmlTransient
    public List<Subclase> getSubclasesList() {
        return subclasesList;
    }

    public void setSubclasesList(List<Subclase> subclasesList) {
        this.subclasesList = subclasesList;
    }

    @XmlTransient
    public List<Usaclase> getUsaclaseList() {
        return usaclaseList;
    }

    public void setUsaclaseList(List<Usaclase> usaclaseList) {
        this.usaclaseList = usaclaseList;
    }

    @XmlTransient
    public List<Tablaclasepornivel> getTablaclasespornivelList() {
        return tablaclasespornivelList;
    }

    public void setTablaclasespornivelList(List<Tablaclasepornivel> tablaclasespornivelList) {
        this.tablaclasespornivelList = tablaclasespornivelList;
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
        if (!(object instanceof Clase)) {
            return false;
        }
        Clase other = (Clase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Clase[ id=" + id + " ]";
    }
    @XmlTransient
    public List<Personaje> getPersonajesList() {
        return personajesList;
    }
    public void setPersonajesList(List<Personaje> personajesList) {
        this.personajesList = personajesList;
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

    public String getElegirhab() {
        return elegirhab;
    }

    public void setElegirhab(String elegirhab) {
        this.elegirhab = elegirhab;
    }

    public Integer getNivelsubclase() {
        return nivelsubclase;
    }

    public void setNivelsubclase(Integer nivelsubclase) {
        this.nivelsubclase = nivelsubclase;
    }
    
}

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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e ORDER BY e.tipo"),
    @NamedQuery(name = "Equipo.findById", query = "SELECT e FROM Equipo e WHERE e.id = :id"),
    @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipo.findByTipo", query = "SELECT e FROM Equipo e WHERE e.tipo = :tipo ORDER BY e.nombre"),
    @NamedQuery(name = "Equipo.findByCategoria", query = "SELECT e FROM Equipo e WHERE e.categoria = :categoria ORDER BY e.nombre"),
    @NamedQuery(name = "Equipo.findByPrecio", query = "SELECT e FROM Equipo e WHERE e.precio = :precio"),
    @NamedQuery(name = "Equipo.findByPeso", query = "SELECT e FROM Equipo e WHERE e.peso = :peso"),
    @NamedQuery(name = "Equipo.findByDano", query = "SELECT e FROM Equipo e WHERE e.dano = :dano"),
    @NamedQuery(name = "Equipo.findByCarmadura", query = "SELECT e FROM Equipo e WHERE e.carmadura = :carmadura"),
    @NamedQuery(name = "Equipo.findByPgolpe", query = "SELECT e FROM Equipo e WHERE e.pgolpe = :pgolpe"),
    @NamedQuery(name = "Equipo.findByTipoCategoria", query = "SELECT e FROM Equipo e WHERE e.tipo = :tipo AND e.categoria = :categoria"),
    //@NamedQuery(name = "Equipo.findByTipoPropiedad", query = "SELECT e FROM Equipo e WHERE e.tipo = :tipo AND e.propiedad = :propiedad"),
    //@NamedQuery(name = "Equipo.findByCategoriaPropiedad", query = "SELECT e FROM Equipo e WHERE e.categoria = :categoria AND e.propiedad = :propiedad"),
    //@NamedQuery(name = "Equipo.findBy3", query = "SELECT e FROM Equipo e WHERE e.tipo = :tipo AND e.categoria = :categoria AND e.propiedad = :propiedad")
})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORIA")
    private String categoria;
    @Size(max = 30)
    @Column(name = "PRECIO")
    private String precio;
    @Size(max = 30)
    @Column(name = "PESO")
    private String peso;
    @Size(max = 50)
    @Column(name = "DANO")
    private String dano;
    @Size(max = 30)
    @Column(name = "CARMADURA")
    private String carmadura;
    @Size(max = 30)
    @Column(name = "PGOLPE")
    private String pgolpe;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinTable(name = "TIENEPROPIEDADES", joinColumns = {
        @JoinColumn(name = "EQUIPO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "PROPIEDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Propiedades> propiedadesList;

    public Equipo() {
    }

    public Equipo(String id) {
        this.id = id;
    }

    public Equipo(String id, String nombre, String tipo, String categoria, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public String getCarmadura() {
        return carmadura;
    }

    public void setCarmadura(String carmadura) {
        this.carmadura = carmadura;
    }

    public String getPgolpe() {
        return pgolpe;
    }

    public void setPgolpe(String pgolpe) {
        this.pgolpe = pgolpe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Propiedades> getPropiedadesList() {
        return propiedadesList;
    }

    public void setPropiedadesList(List<Propiedades> propiedadesList) {
        this.propiedadesList = propiedadesList;
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
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Equipo[ id=" + id + " ]";
    }

}


package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "EQUIPO", uniqueConstraints = {
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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 20)
    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORIA", nullable = false, length = 30)
    private String categoria;
    @Size(max = 30)
    @Column(name = "PRECIO", length = 30)
    private String precio;
    @Size(max = 30)
    @Column(name = "PESO", length = 30)
    private String peso;
    @Size(max = 50)
    @Column(name = "DANO", length = 50)
    private String dano;
    @Size(max = 30)
    @Column(name = "CARMADURA", length = 30)
    private String carmadura;
    @Size(max = 30)
    @Column(name = "PGOLPE", length = 30)
    private String pgolpe;
    @Basic(optional = false)
    @NotNull()
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @ManyToMany(mappedBy = "equipoList")
    private List<Personaje> personajesList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @JoinTable(name = "TIENEPROPIEDADES", joinColumns = {
        @JoinColumn(name = "EQUIPO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "PROPIEDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Propiedad> propiedadesList;

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
    @XmlTransient
    public List<Propiedad> getPropiedadesList() {
        return propiedadesList;
    }
    public void setPropiedadesList(List<Propiedad> propiedadesList) {
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
    public List<Personaje> getPersonajesList() {
        return personajesList;
    }

    public void setPersonajesList(List<Personaje> personajesList) {
        this.personajesList = personajesList;
    }
    
}

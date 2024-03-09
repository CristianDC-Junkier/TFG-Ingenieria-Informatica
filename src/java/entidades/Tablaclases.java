/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "TABLACLASES", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablaclases.findAll", query = "SELECT t FROM Tablaclases t"),
    @NamedQuery(name = "Tablaclases.findById", query = "SELECT t FROM Tablaclases t WHERE t.id = :id"),
    @NamedQuery(name = "Tablaclases.findByRasgos", query = "SELECT t FROM Tablaclases t WHERE t.rasgos = :rasgos"),
    @NamedQuery(name = "Tablaclases.findByBc", query = "SELECT t FROM Tablaclases t WHERE t.bc = :bc"),
    @NamedQuery(name = "Tablaclases.findByTrucos", query = "SELECT t FROM Tablaclases t WHERE t.trucos = :trucos"),
    @NamedQuery(name = "Tablaclases.findByHechizos", query = "SELECT t FROM Tablaclases t WHERE t.hechizos = :hechizos")})
public class Tablaclases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RASGOS", nullable = false)
    private short rasgos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BC", nullable = false)
    private short bc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRUCOS", nullable = false)
    private short trucos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HECHIZOS", nullable = false)
    private short hechizos;
    @ManyToMany(mappedBy = "tablaclasesList")
    private List<Espacioshechizos> espacioshechizosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaclases")
    private List<Tablaclasespornivel> tablaclasespornivelList;

    public Tablaclases() {
    }

    public Tablaclases(String id) {
        this.id = id;
    }

    public Tablaclases(String id, short rasgos, short bc, short trucos, short hechizos) {
        this.id = id;
        this.rasgos = rasgos;
        this.bc = bc;
        this.trucos = trucos;
        this.hechizos = hechizos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public short getRasgos() {
        return rasgos;
    }

    public void setRasgos(short rasgos) {
        this.rasgos = rasgos;
    }

    public short getBc() {
        return bc;
    }

    public void setBc(short bc) {
        this.bc = bc;
    }

    public short getTrucos() {
        return trucos;
    }

    public void setTrucos(short trucos) {
        this.trucos = trucos;
    }

    public short getHechizos() {
        return hechizos;
    }

    public void setHechizos(short hechizos) {
        this.hechizos = hechizos;
    }

    @XmlTransient
    public List<Espacioshechizos> getEspacioshechizosList() {
        return espacioshechizosList;
    }

    public void setEspacioshechizosList(List<Espacioshechizos> espacioshechizosList) {
        this.espacioshechizosList = espacioshechizosList;
    }

    @XmlTransient
    public List<Tablaclasespornivel> getTablaclasespornivelList() {
        return tablaclasespornivelList;
    }

    public void setTablaclasespornivelList(List<Tablaclasespornivel> tablaclasespornivelList) {
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
        if (!(object instanceof Tablaclases)) {
            return false;
        }
        Tablaclases other = (Tablaclases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tablaclases[ id=" + id + " ]";
    }
    
}

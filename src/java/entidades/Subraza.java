
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "SUBRAZA", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subraza.findAll", query = "SELECT s FROM Subraza s"),
    @NamedQuery(name = "Subraza.findById", query = "SELECT s FROM Subraza s WHERE s.id = :id"),
    @NamedQuery(name = "Subraza.findByNombre", query = "SELECT s FROM Subraza s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Subraza.findByTamano", query = "SELECT s FROM Subraza s WHERE s.tamano = :tamano"),
    @NamedQuery(name = "Subraza.findByVelocidad", query = "SELECT s FROM Subraza s WHERE s.velocidad = :velocidad"),
    @NamedQuery(name = "Subraza.findByElegirhab", query = "SELECT s FROM Subraza s WHERE s.elegirhab = :elegirhab")})
public class Subraza implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TAMANO", nullable = false, length = 50)
    private String tamano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VELOCIDAD", nullable = false)
    private Integer velocidad;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELEGIRHAB", nullable = false, length = 1)
    private String elegirhab;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIGEATR", nullable = false, length = 1)
    private String eligeatr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subraza")
    private List<Personaje> personajesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @JoinTable(name = "USASUBRAZA", joinColumns = {
        @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Rasgo> rasgosList;
    @JoinTable(name = "COMPETERAZA", joinColumns = {
        @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Habilidad> habilidadesListC;
    @JoinTable(name = "ELIGERAZA", joinColumns = {
        @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Habilidad> habilidadesListE;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subrazas")
    private List<Sumaraza> sumarazaList;
    @JoinColumn(name = "RAZA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Raza raza;

    public Subraza() {
    }

    public Subraza(String id) {
        this.id = id;
    }

    public Subraza(String id, String nombre, String tamano, Integer velocidad, String descripcion, String elegirhab) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.elegirhab = elegirhab;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @XmlTransient
    public List<Rasgo> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgo> rasgosList) {
        this.rasgosList = rasgosList;
    }

    @XmlTransient
    public List<Habilidad> getHabilidadesListC() {
        return habilidadesListC;
    }

    public void setHabilidadesListC(List<Habilidad> habilidadesListC) {
        this.habilidadesListC = habilidadesListC;
    }

    @XmlTransient
    public List<Habilidad> getHabilidadesListE() {
        return habilidadesListE;
    }

    public void setHabilidadesListE(List<Habilidad> habilidadesListE) {
        this.habilidadesListE = habilidadesListE;
    }

    @XmlTransient
    public List<Sumaraza> getSumarazaList() {
        return sumarazaList;
    }

    public void setSumarazaList(List<Sumaraza> sumarazaList) {
        this.sumarazaList = sumarazaList;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
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
        if (!(object instanceof Subraza)) {
            return false;
        }
        Subraza other = (Subraza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Subraza[ id=" + id + " ]";
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

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElegirhab() {
        return elegirhab;
    }

    public void setElegirhab(String elegirhab) {
        this.elegirhab = elegirhab;
    }

    public String getEligeatr() {
        return eligeatr;
    }

    public void setEligeatr(String eligeatr) {
        this.eligeatr = eligeatr;
    }
    
}

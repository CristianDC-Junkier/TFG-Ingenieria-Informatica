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
@Table(name = "MONSTRUO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monstruo.findAll", query = "SELECT m FROM Monstruo m ORDER BY m.nombre"),
    @NamedQuery(name = "Monstruo.findById", query = "SELECT m FROM Monstruo m WHERE m.id = :id"),
    @NamedQuery(name = "Monstruo.findByNombre", query = "SELECT m FROM Monstruo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Monstruo.findByTipo", query = "SELECT m FROM Monstruo m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Monstruo.findByTamano", query = "SELECT m FROM Monstruo m WHERE m.tamano = :tamano"),
    @NamedQuery(name = "Monstruo.findByVdesafio", query = "SELECT m FROM Monstruo m WHERE m.vdesafio = :vdesafio"),
    @NamedQuery(name = "Monstruo.findByAlineamiento", query = "SELECT m FROM Monstruo m WHERE m.alineamiento = :alineamiento"),
    @NamedQuery(name = "Monstruo.findByCarmadura", query = "SELECT m FROM Monstruo m WHERE m.carmadura = :carmadura"),
    @NamedQuery(name = "Monstruo.findByPgolpe", query = "SELECT m FROM Monstruo m WHERE m.pgolpe = :pgolpe"),
    @NamedQuery(name = "Monstruo.findByVelocidad", query = "SELECT m FROM Monstruo m WHERE m.velocidad = :velocidad"),
    @NamedQuery(name = "Monstruo.findByResdano", query = "SELECT m FROM Monstruo m WHERE m.resdano = :resdano"),
    @NamedQuery(name = "Monstruo.findByInmdano", query = "SELECT m FROM Monstruo m WHERE m.inmdano = :inmdano"),
    @NamedQuery(name = "Monstruo.findBySentidos", query = "SELECT m FROM Monstruo m WHERE m.sentidos = :sentidos"),
    @NamedQuery(name = "Monstruo.findByIdiomas", query = "SELECT m FROM Monstruo m WHERE m.idiomas = :idiomas"),
    @NamedQuery(name = "Monstruo.findByVuldano", query = "SELECT m FROM Monstruo m WHERE m.vuldano = :vuldano"),
    @NamedQuery(name = "Monstruo.findByTipoVD", query = "SELECT m FROM Monstruo m WHERE m.tipo = :tipo AND m.vdesafio = :vdesafio")
})
public class Monstruo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TAMANO", nullable = false, length = 30)
    private String tamano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VDESAFIO", nullable = false, length = 30)
    private String vdesafio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALINEAMIENTO", nullable = false, length = 30)
    private String alineamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARMADURA", nullable = false)
    private BigInteger carmadura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PGOLPE", nullable = false, length = 30)
    private String pgolpe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VELOCIDAD", nullable = false, length = 30)
    private String velocidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "RESDANO", nullable = false, length = 120)
    private String resdano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "INMDANO", nullable = false, length = 120)
    private String inmdano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "SENTIDOS", nullable = false, length = 120)
    private String sentidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "IDIOMAS", nullable = false, length = 120)
    private String idiomas;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "VULDANO", nullable = false, length = 120)
    private String vuldano;
    @JoinTable(name = "INMUNE", joinColumns = {
        @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ESTADO", referencedColumnName = "NOMBRE", nullable = false)})
    @ManyToMany
    private List<Estado> estadosList;
    @JoinTable(name = "UTILIZAMONSTRUO", joinColumns = {
        @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ACCION", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Accion> accionesList;
    @JoinTable(name = "USAMONSTRUO", joinColumns = {
        @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Rasgo> rasgosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monstruos")
    private List<Tienemonstruo> tienemonstruoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monstruos")
    private List<Competentemonstruo> competentemonstruoList;

    public Monstruo() {
    }

    public Monstruo(String id) {
        this.id = id;
    }

    public Monstruo(String id, String nombre, String tipo, String tamano, String vdesafio, String alineamiento, BigInteger carmadura, String pgolpe, String velocidad, String resdano, String inmdano, String sentidos, String idiomas, String descripcion, String vuldano) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tamano = tamano;
        this.vdesafio = vdesafio;
        this.alineamiento = alineamiento;
        this.carmadura = carmadura;
        this.pgolpe = pgolpe;
        this.velocidad = velocidad;
        this.resdano = resdano;
        this.inmdano = inmdano;
        this.sentidos = sentidos;
        this.idiomas = idiomas;
        this.descripcion = descripcion;
        this.vuldano = vuldano;
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

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getVdesafio() {
        return vdesafio;
    }

    public void setVdesafio(String vdesafio) {
        this.vdesafio = vdesafio;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public BigInteger getCarmadura() {
        return carmadura;
    }

    public void setCarmadura(BigInteger carmadura) {
        this.carmadura = carmadura;
    }

    public String getPgolpe() {
        return pgolpe;
    }

    public void setPgolpe(String pgolpe) {
        this.pgolpe = pgolpe;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getResdano() {
        return resdano;
    }

    public void setResdano(String resdano) {
        this.resdano = resdano;
    }

    public String getInmdano() {
        return inmdano;
    }

    public void setInmdano(String inmdano) {
        this.inmdano = inmdano;
    }

    public String getSentidos() {
        return sentidos;
    }

    public void setSentidos(String sentidos) {
        this.sentidos = sentidos;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVuldano() {
        return vuldano;
    }

    public void setVuldano(String vuldano) {
        this.vuldano = vuldano;
    }

    @XmlTransient
    public List<Estado> getEstadosList() {
        return estadosList;
    }

    public void setEstadosList(List<Estado> estadosList) {
        this.estadosList = estadosList;
    }

    @XmlTransient
    public List<Accion> getAccionesList() {
        return accionesList;
    }

    public void setAccionesList(List<Accion> accionesList) {
        this.accionesList = accionesList;
    }

    @XmlTransient
    public List<Rasgo> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgo> rasgosList) {
        this.rasgosList = rasgosList;
    }

    @XmlTransient
    public List<Tienemonstruo> getTienemonstruoList() {
        return tienemonstruoList;
    }

    public void setTienemonstruoList(List<Tienemonstruo> tienemonstruoList) {
        this.tienemonstruoList = tienemonstruoList;
    }

    @XmlTransient
    public List<Competentemonstruo> getCompetentemonstruoList() {
        return competentemonstruoList;
    }

    public void setCompetentemonstruoList(List<Competentemonstruo> competentemonstruoList) {
        this.competentemonstruoList = competentemonstruoList;
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
        if (!(object instanceof Monstruo)) {
            return false;
        }
        Monstruo other = (Monstruo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Monstruo[ id=" + id + " ]";
    }
    
}

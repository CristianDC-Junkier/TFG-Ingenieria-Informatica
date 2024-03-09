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
@Table(name = "MONSTRUOS", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monstruos.findAll", query = "SELECT m FROM Monstruos m ORDER BY m.nombre"),
    @NamedQuery(name = "Monstruos.findById", query = "SELECT m FROM Monstruos m WHERE m.id = :id"),
    @NamedQuery(name = "Monstruos.findByNombre", query = "SELECT m FROM Monstruos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Monstruos.findByTipo", query = "SELECT m FROM Monstruos m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Monstruos.findByTamano", query = "SELECT m FROM Monstruos m WHERE m.tamano = :tamano"),
    @NamedQuery(name = "Monstruos.findByVdesafio", query = "SELECT m FROM Monstruos m WHERE m.vdesafio = :vdesafio"),
    @NamedQuery(name = "Monstruos.findByAlineamiento", query = "SELECT m FROM Monstruos m WHERE m.alineamiento = :alineamiento"),
    @NamedQuery(name = "Monstruos.findByCarmadura", query = "SELECT m FROM Monstruos m WHERE m.carmadura = :carmadura"),
    @NamedQuery(name = "Monstruos.findByPgolpe", query = "SELECT m FROM Monstruos m WHERE m.pgolpe = :pgolpe"),
    @NamedQuery(name = "Monstruos.findByVelocidad", query = "SELECT m FROM Monstruos m WHERE m.velocidad = :velocidad"),
    @NamedQuery(name = "Monstruos.findByResdano", query = "SELECT m FROM Monstruos m WHERE m.resdano = :resdano"),
    @NamedQuery(name = "Monstruos.findByInmdano", query = "SELECT m FROM Monstruos m WHERE m.inmdano = :inmdano"),
    @NamedQuery(name = "Monstruos.findBySentidos", query = "SELECT m FROM Monstruos m WHERE m.sentidos = :sentidos"),
    @NamedQuery(name = "Monstruos.findByIdiomas", query = "SELECT m FROM Monstruos m WHERE m.idiomas = :idiomas"),
    @NamedQuery(name = "Monstruos.findByVuldano", query = "SELECT m FROM Monstruos m WHERE m.vuldano = :vuldano"),
    @NamedQuery(name = "Monstruos.findByTipoVD", query = "SELECT m FROM Monstruos m WHERE m.tipo = :tipo AND m.vdesafio = :vdesafio")
})
public class Monstruos implements Serializable {

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
    private List<Estados> estadosList;
    @JoinTable(name = "UTILIZAMONSTRUO", joinColumns = {
        @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ACCION", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Acciones> accionesList;
    @JoinTable(name = "USAMONSTRUO", joinColumns = {
        @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Rasgos> rasgosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monstruos")
    private List<Tienemonstruo> tienemonstruoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monstruos")
    private List<Competentemonstruo> competentemonstruoList;

    public Monstruos() {
    }

    public Monstruos(String id) {
        this.id = id;
    }

    public Monstruos(String id, String nombre, String tipo, String tamano, String vdesafio, String alineamiento, BigInteger carmadura, String pgolpe, String velocidad, String resdano, String inmdano, String sentidos, String idiomas, String descripcion, String vuldano) {
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
    public List<Estados> getEstadosList() {
        return estadosList;
    }

    public void setEstadosList(List<Estados> estadosList) {
        this.estadosList = estadosList;
    }

    @XmlTransient
    public List<Acciones> getAccionesList() {
        return accionesList;
    }

    public void setAccionesList(List<Acciones> accionesList) {
        this.accionesList = accionesList;
    }

    @XmlTransient
    public List<Rasgos> getRasgosList() {
        return rasgosList;
    }

    public void setRasgosList(List<Rasgos> rasgosList) {
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
        if (!(object instanceof Monstruos)) {
            return false;
        }
        Monstruos other = (Monstruos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Monstruos[ id=" + id + " ]";
    }
    
}

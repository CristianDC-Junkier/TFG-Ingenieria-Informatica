package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @Size(min = 1, max = 30)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TAMANO")
    private String tamano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VDESAFIO")
    private String vdesafio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALINEAMIENTO")
    private String alineamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARMADURA")
    private BigInteger carmadura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PGOLPE")
    private String pgolpe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VELOCIDAD")
    private String velocidad;
    @Size(max = 120)
    @Column(name = "RESDANO")
    private String resdano;
    @Size(max = 120)
    @Column(name = "INMDANO")
    private String inmdano;
    @Size(max = 120)
    @Column(name = "SENTIDOS")
    private String sentidos;
    @Size(max = 120)
    @Column(name = "IDIOMAS")
    private String idiomas;
    @Size(max = 120)
    @Column(name = "VULDANO")
    private String vuldano;
    @Basic(optional = false)
    @NotNull()
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Monstruos() {
    }

    public Monstruos(String id) {
        this.id = id;
    }

    public Monstruos(String id, String nombre, String tipo, String tamano, String vdesafio, String alineamiento, BigInteger carmadura, String pgolpe, String velocidad, String resdano, String inmdano, String sentidos, String idiomas, String vuldano, String descripcion) {
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
        this.vuldano = vuldano;
        this.descripcion = descripcion;
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

    public String getVuldano() {
        return vuldano;
    }

    public void setVuldano(String vuldano) {
        this.vuldano = vuldano;
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

}

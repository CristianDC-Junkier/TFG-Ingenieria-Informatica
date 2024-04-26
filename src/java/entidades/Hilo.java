
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "HILO", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TITULO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hilo.findAll", query = "SELECT h FROM Hilo h"),
    @NamedQuery(name = "Hilo.findById", query = "SELECT h FROM Hilo h WHERE h.id = :id"),
    @NamedQuery(name = "Hilo.findByTitulo", query = "SELECT h FROM Hilo h WHERE h.titulo = :titulo"),
    @NamedQuery(name = "Hilo.findByMensajeinicial", query = "SELECT h FROM Hilo h WHERE h.mensajeinicial = :mensajeinicial"),
    @NamedQuery(name = "Hilo.findByFecha", query = "SELECT h FROM Hilo h WHERE h.fecha = :fecha")})
public class Hilo implements Serializable {

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
    @Column(name = "TITULO", nullable = false, length = 80)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MENSAJEINICIAL", nullable = false, length = 255)
    private String mensajeinicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hilo")
    private List<Hilo> hiloList;
    @JoinColumn(name = "HILO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Hilo hilo;
    @JoinColumn(name = "SECCION", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Seccion seccion;
    @JoinColumn(name = "TEMA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tema tema;
    @JoinColumn(name = "CREADOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios creador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hilo")
    private List<Mensajehilo> mensajehiloList;

    public Hilo() {
    }

    public Hilo(String id) {
        this.id = id;
    }

    public Hilo(String id, String titulo, String mensajeinicial, Date fecha) {
        this.id = id;
        this.titulo = titulo;
        this.mensajeinicial = mensajeinicial;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensajeinicial() {
        return mensajeinicial;
    }

    public void setMensajeinicial(String mensajeinicial) {
        this.mensajeinicial = mensajeinicial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Hilo> getHiloList() {
        return hiloList;
    }

    public void setHiloList(List<Hilo> hiloList) {
        this.hiloList = hiloList;
    }

    public Hilo getHilo() {
        return hilo;
    }

    public void setHilo(Hilo hilo) {
        this.hilo = hilo;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuarios getCreador() {
        return creador;
    }

    public void setCreador(Usuarios creador) {
        this.creador = creador;
    }

    @XmlTransient
    public List<Mensajehilo> getMensajehiloList() {
        return mensajehiloList;
    }

    public void setMensajehiloList(List<Mensajehilo> mensajehiloList) {
        this.mensajehiloList = mensajehiloList;
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
        if (!(object instanceof Hilo)) {
            return false;
        }
        Hilo other = (Hilo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Hilo[ id=" + id + " ]";
    }
    
}


package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "MENSAJEHILO", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajehilo.findAll", query = "SELECT m FROM Mensajehilo m"),
    @NamedQuery(name = "Mensajehilo.findById", query = "SELECT m FROM Mensajehilo m WHERE m.id = :id"),
    @NamedQuery(name = "Mensajehilo.findByMensaje", query = "SELECT m FROM Mensajehilo m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajehilo.findByFecha", query = "SELECT m FROM Mensajehilo m WHERE m.fecha = :fecha")})
public class Mensajehilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MENSAJE", nullable = false, length = 255)
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "HILO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Hilo hilo;
    @JoinColumn(name = "ESCRITOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios escritor;

    public Mensajehilo() {
    }

    public Mensajehilo(String id) {
        this.id = id;
    }

    public Mensajehilo(String id, String mensaje, Date fecha) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Hilo getHilo() {
        return hilo;
    }

    public void setHilo(Hilo hilo) {
        this.hilo = hilo;
    }

    public Usuarios getEscritor() {
        return escritor;
    }

    public void setEscritor(Usuarios escritor) {
        this.escritor = escritor;
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
        if (!(object instanceof Mensajehilo)) {
            return false;
        }
        Mensajehilo other = (Mensajehilo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mensajehilo[ id=" + id + " ]";
    }
    
}

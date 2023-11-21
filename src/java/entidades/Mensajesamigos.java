
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "MENSAJESAMIGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajesamigos.findAll", query = "SELECT m FROM Mensajesamigos m"),
    @NamedQuery(name = "Mensajesamigos.findByMensaje", query = "SELECT m FROM Mensajesamigos m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajesamigos.findByEscritor", query = "SELECT m FROM Mensajesamigos m WHERE m.escritor = :escritor"),
    @NamedQuery(name = "Mensajesamigos.findByReceptor", query = "SELECT m FROM Mensajesamigos m WHERE m.receptor = :receptor"),
    @NamedQuery(name = "Mensajesamigos.findByFecha", query = "SELECT m FROM Mensajesamigos m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajesamigos.findByHora", query = "SELECT m FROM Mensajesamigos m WHERE m.hora = :hora")})
public class Mensajesamigos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESCRITOR")
    private String escritor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RECEPTOR")
    private String receptor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;

    public Mensajesamigos() {
    }

    public Mensajesamigos(String id) {
        this.id = id;
    }

    public Mensajesamigos(String id, String mensaje, String escritor, String receptor, Date fecha, Date hora) {
        this.id = id;
        this.mensaje = mensaje;
        this.escritor = escritor;
        this.receptor = receptor;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    public Mensajesamigos( String mensaje, String escritor, String receptor, Date fecha, Date hora) {
        this.mensaje = mensaje;
        this.escritor = escritor;
        this.receptor = receptor;
        this.fecha = fecha;
        this.hora = hora;
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

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
        if (!(object instanceof Mensajesamigos)) {
            return false;
        }
        Mensajesamigos other = (Mensajesamigos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mensajesamigos[ id=" + id + " ]";
    }
    
}

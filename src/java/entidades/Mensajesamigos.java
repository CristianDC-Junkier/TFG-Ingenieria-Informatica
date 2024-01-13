package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajesamigos.findAll", query = "SELECT m FROM Mensajesamigos m"),
    @NamedQuery(name = "Mensajesamigos.findById", query = "SELECT m FROM Mensajesamigos m WHERE m.id = :id"),
    @NamedQuery(name = "Mensajesamigos.findByMensaje", query = "SELECT m FROM Mensajesamigos m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajesamigos.findByFecha", query = "SELECT m FROM Mensajesamigos m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajesamigos.findByReceptor", query = "SELECT m FROM Mensajesamigos m WHERE m.receptor = :receptor"),
    @NamedQuery(name = "Mensajesamigos.findByEscritor", query = "SELECT m FROM Mensajesamigos m WHERE m.escritor = :escritor"),
    @NamedQuery(name = "Mensajesamigos.findByEscritorReceptor", query = "SELECT m FROM Mensajesamigos m WHERE m.escritor = :escritor and m.receptor = :receptor"),
    @NamedQuery(name = "Mensajesamigos.findByReceptorEscritor", query = "SELECT m FROM Mensajesamigos m WHERE m.receptor = :receptor and m.escritor = :escritor")
})
public class Mensajesamigos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String receptor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String escritor;

    public Mensajesamigos() {
    }

    public Mensajesamigos(String id) {
        this.id = id;
    }

    public Mensajesamigos(String id, String mensaje, Date fecha, String receptor, String escritor) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.receptor = receptor;
        this.escritor = escritor;
    }

    public Mensajesamigos(String mensaje, Date fecha, String receptor, String escritor) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.receptor = receptor;
        this.escritor = escritor;
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

    public String getHora() {

        // Formatear la hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fecha);

        return horaFormateada;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
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
        return "controlador.Mensajesamigos[ id=" + id + " ]";
    }

}

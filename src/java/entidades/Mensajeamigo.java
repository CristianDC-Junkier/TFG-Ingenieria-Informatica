package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "MENSAJEAMIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajeamigo.findAll", query = "SELECT m FROM Mensajeamigo m"),
    @NamedQuery(name = "Mensajeamigo.findById", query = "SELECT m FROM Mensajeamigo m WHERE m.id = :id"),
    @NamedQuery(name = "Mensajeamigo.findByMensaje", query = "SELECT m FROM Mensajeamigo m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajeamigo.findByFecha", query = "SELECT m FROM Mensajeamigo m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajeamigo.findByReceptor", query = "SELECT m FROM Mensajeamigo m WHERE m.receptor = :receptor"),
    @NamedQuery(name = "Mensajeamigo.findByEscritor", query = "SELECT m FROM Mensajeamigo m WHERE m.escritor = :escritor"),
    @NamedQuery(name = "Mensajeamigo.findByEscritorReceptor", query = "SELECT m FROM Mensajeamigo m WHERE m.escritor = :escritor and m.receptor = :receptor ORDER BY m.fecha"),
    @NamedQuery(name = "Mensajeamigo.findByReceptorEscritor", query = "SELECT m FROM Mensajeamigo m WHERE m.receptor = :receptor and m.escritor = :escritor ORDER BY m.fecha")
})
public class Mensajeamigo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
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
    @JoinColumn(name = "RECEPTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuario receptor;
    @JoinColumn(name = "ESCRITOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuario escritor;

    public Mensajeamigo() {
    }

    public Mensajeamigo(String id) {
        this.id = id;
    }

    public Mensajeamigo(String id, String mensaje, Date fecha) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Mensajeamigo(String mensaje, Date fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Mensajeamigo(String mensaje, Date fecha, Usuario receptor, Usuario escritor) {
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
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormateada = formatoHora.format(fecha);

        return horaFormateada;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public Usuario getEscritor() {
        return escritor;
    }

    public void setEscritor(Usuario escritor) {
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
        if (!(object instanceof Mensajeamigo)) {
            return false;
        }
        Mensajeamigo other = (Mensajeamigo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Mensajeamigo[ id=" + id + " ]";
    }
}

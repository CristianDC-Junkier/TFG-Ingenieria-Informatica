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
@XmlRootElement
@Table(name = "MENSAJEMESA")
@NamedQueries({
    @NamedQuery(name = "Mensajemesa.findAll", query = "SELECT m FROM Mensajemesa m"),
    @NamedQuery(name = "Mensajemesa.findById", query = "SELECT m FROM Mensajemesa m WHERE m.id = :id"),
    @NamedQuery(name = "Mensajemesa.findByMensaje", query = "SELECT m FROM Mensajemesa m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajemesa.findByFecha", query = "SELECT m FROM Mensajemesa m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajemesa.findByMesa", query = "SELECT m FROM Mensajemesa m WHERE m.mesa = :mesa"),
    @NamedQuery(name = "Mensajemesa.findByEscritor", query = "SELECT m FROM Mensajemesa m WHERE m.escritor = :escritor")})
public class Mensajemesa implements Serializable {

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
    @JoinColumn(name = "MESA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Mesa mesa;
    @JoinColumn(name = "ESCRITOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuario escritor;

    public Mensajemesa() {
    }

    public Mensajemesa(String id) {
        this.id = id;
    }

    public Mensajemesa(String id, String mensaje, Date fecha, Mesa mesa, Usuario escritor) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.mesa = mesa;
        this.escritor = escritor;
    }

    public Mensajemesa(String mensaje, Date fecha, Mesa mesa, Usuario escritor) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.mesa = mesa;
        this.escritor = escritor;
    }

    public Mensajemesa(String id, String mensaje, Date fecha) {
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

    public String getHora() {

        // Formatear la hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormateada = formatoHora.format(fecha);

        return horaFormateada;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setReceptor(Mesa mesa) {
        this.mesa = mesa;
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
        if (!(object instanceof Mensajemesa)) {
            return false;
        }
        Mensajemesa other = (Mensajemesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Mensajemesa[ id=" + id + " ]";
    }
}

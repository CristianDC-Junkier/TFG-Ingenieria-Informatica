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
@NamedQueries({
    @NamedQuery(name = "Mensajesmesas.findAll", query = "SELECT m FROM Mensajesmesas m"),
    @NamedQuery(name = "Mensajesmesas.findById", query = "SELECT m FROM Mensajesmesas m WHERE m.id = :id"),
    @NamedQuery(name = "Mensajesmesas.findByMensaje", query = "SELECT m FROM Mensajesmesas m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajesmesas.findByFecha", query = "SELECT m FROM Mensajesmesas m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajesmesas.findByMesa", query = "SELECT m FROM Mensajesmesas m WHERE m.mesa = :mesa"),
    @NamedQuery(name = "Mensajesmesas.findByEscritor", query = "SELECT m FROM Mensajesmesas m WHERE m.escritor = :escritor")})
public class Mensajesmesas implements Serializable {

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
    private Mesas mesa;
    @JoinColumn(name = "ESCRITOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios escritor;

    public Mensajesmesas() {
    }

    public Mensajesmesas(String id) {
        this.id = id;
    }

    public Mensajesmesas(String id, String mensaje, Date fecha) {
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

    public Mesas getMesa() {
        return mesa;
    }

    public void setReceptor(Mesas mesa) {
        this.mesa = mesa;
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
        if (!(object instanceof Mensajesmesas)) {
            return false;
        }
        Mensajesmesas other = (Mensajesmesas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Mensajesmesas[ id=" + id + " ]";
    }
}

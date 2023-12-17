package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "MESAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesas.findAll", query = "SELECT m FROM Mesas m"),
    @NamedQuery(name = "Mesas.findById", query = "SELECT m FROM Mesas m WHERE m.id = :id"),
    @NamedQuery(name = "Mesas.findByCreador", query = "SELECT m FROM Mesas m WHERE m.creador = :creador"),
    @NamedQuery(name = "Mesas.findByComunidad", query = "SELECT m FROM Mesas m WHERE m.comunidad = :comunidad"),
    @NamedQuery(name = "Mesas.findByTamano", query = "SELECT m FROM Mesas m WHERE m.tamano = :tamano"),
    @NamedQuery(name = "Mesas.findByTitulo", query = "SELECT m FROM Mesas m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "Mesas.findByDescripcion", query = "SELECT m FROM Mesas m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mesas.findByContrasena", query = "SELECT m FROM Mesas m WHERE m.contrasena = :contrasena"),
    @NamedQuery(name = "Mesas.CountByCreador", query = "SELECT COUNT(m) FROM Mesas m WHERE m.creador = :creador")})
public class Mesas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CREADOR")
    private String creador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COMUNIDAD")
    private String comunidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAMANO")
    private short tamano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "CONTRASENA")
    private String contrasena;

    public Mesas() {
    }

    public Mesas(String id) {
        this.id = id;
    }

    public Mesas(String id, String creador, String comunidad, short tamano, String titulo) {
        this.id = id;
        this.creador = creador;
        this.comunidad = comunidad;
        this.tamano = tamano;
        this.titulo = titulo;
    }

    public Mesas(String creador, String comunidad, short tamano, String titulo) {
        this.creador = creador;
        this.comunidad = comunidad;
        this.tamano = tamano;
        this.titulo = titulo;
    }

    public Mesas(String creador, String comunidad, short tamano, String titulo, String descripcion, String contrasena) {
        this.creador = creador;
        this.comunidad = comunidad;
        this.tamano = tamano;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public short getTamano() {
        return tamano;
    }

    public void setTamano(short tamano) {
        this.tamano = tamano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        if (!(object instanceof Mesas)) {
            return false;
        }
        Mesas other = (Mesas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mesas[ id=" + id + " ]";
    }

}

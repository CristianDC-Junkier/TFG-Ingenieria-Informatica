package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "MESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findById", query = "SELECT m FROM Mesa m WHERE m.id = :id"),
    @NamedQuery(name = "Mesa.findByCreador", query = "SELECT m FROM Mesa m WHERE m.creador = :creador"),
    @NamedQuery(name = "Mesa.findByComunidad", query = "SELECT m FROM Mesa m WHERE m.comunidad = :comunidad"),
    @NamedQuery(name = "Mesa.findByContrasena", query = "SELECT m FROM Mesa m WHERE m.contrasena = :contrasena"),
    @NamedQuery(name = "Mesa.findByTamano", query = "SELECT m FROM Mesa m WHERE m.tamano = :tamano"),
    @NamedQuery(name = "Mesa.findByTitulo", query = "SELECT m FROM Mesa m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "Mesa.findByDescripcion", query = "SELECT m FROM Mesa m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mesa.CountByCreador", query = "SELECT COUNT(m) FROM Mesa m WHERE m.creador = :creador")})
public class Mesa implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CREADOR", nullable = false, length = 50)
    private String creador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COMUNIDAD", nullable = false, length = 100)
    private String comunidad;
    @Size(max = 100)
    @Column(name = "CONTRASENA", length = 100)
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAMANO", nullable = false)
    private Integer tamano;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 100)
    @Column(name = "TITULO", nullable = false, length = 100)
    private String titulo;
    @Size(max = 255)
    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;
    @Lob()
    @Column(name = "IMAGENMESA")
    private byte[] imagenmesa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mesas")
    private Musicamesa musicamesa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mesas")
    private Descriptormesa descriptormesa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesas")
    private List<Pertenecemesa> pertenecemesaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesa")
    private List<Mensajemesa> mensajesmesasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;

    public Mesa() {
    }

    public Mesa(String id) {
        this.id = id;
    }

    public Mesa(String id, String creador, String comunidad, Integer tamano, String titulo) {
        this.id = id;
        this.creador = creador;
        this.comunidad = comunidad;
        this.tamano = tamano;
        this.titulo = titulo;
    }

    public Mesa(String creador, String comunidad, Integer tamano, String titulo) {
        this.creador = creador;
        this.comunidad = comunidad;
        this.tamano = tamano;
        this.titulo = titulo;
    }

    public Mesa(String creador, String comunidad, Integer tamano, String titulo, String descripcion, String contrasena) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mesa[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Pertenecemesa> getPertenecemesaList() {
        return pertenecemesaList;
    }

    public void setPertenecemesaList(List<Pertenecemesa> pertenecemesaList) {
        this.pertenecemesaList = pertenecemesaList;
    }

    @XmlTransient
    public List<Mensajemesa> getMensajesmesasList() {
        return mensajesmesasList;
    }

    public void setMensajesmesasList(List<Mensajemesa> mensajesmesasList) {
        this.mensajesmesasList = mensajesmesasList;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public Descriptormesa getDescriptormesa() {
        return descriptormesa;
    }

    public void setDescriptormesa(Descriptormesa descriptormesa) {
        this.descriptormesa = descriptormesa;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public byte[]  getImagenmesa() {
        return imagenmesa;
    }

    public void setImagenmesa(byte[]  imagenmesa) {
        this.imagenmesa = imagenmesa;
    }

    public Musicamesa getMusicamesa() {
        return musicamesa;
    }

    public void setMusicamesa(Musicamesa musicamesa) {
        this.musicamesa = musicamesa;
    }

}

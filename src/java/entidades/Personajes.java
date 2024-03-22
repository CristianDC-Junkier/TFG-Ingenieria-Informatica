
package entidades;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "PERSONAJES", catalog = "", schema = "SYS_G4", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajes.findAll", query = "SELECT p FROM Personajes p"),
    @NamedQuery(name = "Personajes.findById", query = "SELECT p FROM Personajes p WHERE p.id = :id"),
    @NamedQuery(name = "Personajes.findByNombre", query = "SELECT p FROM Personajes p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personajes.findByEdad", query = "SELECT p FROM Personajes p WHERE p.edad = :edad"),
    @NamedQuery(name = "Personajes.findByAlineamiento", query = "SELECT p FROM Personajes p WHERE p.alineamiento = :alineamiento"),
    @NamedQuery(name = "Personajes.findByIdiomas", query = "SELECT p FROM Personajes p WHERE p.idiomas = :idiomas"),
    @NamedQuery(name = "Personajes.findByPexp", query = "SELECT p FROM Personajes p WHERE p.pexp = :pexp"),
    @NamedQuery(name = "Personajes.findByNivel", query = "SELECT p FROM Personajes p WHERE p.nivel = :nivel"),
    @NamedQuery(name = "Personajes.findByPvida", query = "SELECT p FROM Personajes p WHERE p.pvida = :pvida"),
    @NamedQuery(name = "Personajes.findByPvidaactuales", query = "SELECT p FROM Personajes p WHERE p.pvidaactuales = :pvidaactuales"),
    @NamedQuery(name = "Personajes.findByClasearmadura", query = "SELECT p FROM Personajes p WHERE p.clasearmadura = :clasearmadura"),
    @NamedQuery(name = "Personajes.findByCreador", query = "SELECT p FROM Personajes p WHERE p.usuario = :creador")
})
public class Personajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "NOMBRE", nullable = false, length = 120)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDAD", nullable = false)
    private int edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALINEAMIENTO", nullable = false, length = 30)
    private String alineamiento;
    @Lob
    @Column(name = "APARIENCIA")
    private String apariencia;
    @Lob
    @Column(name = "RASPERSONALIDAD")
    private String raspersonalidad;
    @Lob
    @Column(name = "DEFECTOS")
    private String defectos;
    @Lob
    @Column(name = "VINCULOS")
    private String vinculos;
    @Size(max = 255)
    @Column(name = "IDIOMAS", length = 255)
    private String idiomas;
    @Column(name = "PEXP")
    private Long pexp;
    @Lob
    @Column(name = "HISTORIA")
    private String historia;
    @Lob
    @Column(name = "IMAGENPERSONAJE")
    private Serializable imagenpersonaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private BigInteger nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PVIDA", nullable = false)
    private BigInteger pvida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PVIDAACTUALES", nullable = false)
    private BigInteger pvidaactuales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASEARMADURA", nullable = false)
    private BigInteger clasearmadura;
    @JoinTable(name = "PERSONAJEHECHIZOS", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HECHIZO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Hechizos> hechizosList;
    @JoinTable(name = "PERSONAJEDOTES", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "DOTE", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Dotes> dotesList;
    @JoinTable(name = "PERSONAJEEQUIPO", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "EQUIPO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Equipo> equipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personajes")
    private List<Personajehabilidades> personajehabilidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personajes")
    private List<Personajeatributos> personajeatributosList;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Clases clase;
    @JoinColumn(name = "RAZA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Razas raza;
    @JoinColumn(name = "SUBCLASE", referencedColumnName = "ID")
    @ManyToOne
    private Subclases subclase;
    @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Subrazas subraza;
    @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Trasfondos trasfondo;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios usuario;

    public Personajes() {
    }

    public Personajes(String id) {
        this.id = id;
    }

    public Personajes(String id, String nombre, int edad, String alineamiento, BigInteger nivel, BigInteger pvida, BigInteger pvidaactuales, BigInteger clasearmadura) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.alineamiento = alineamiento;
        this.nivel = nivel;
        this.pvida = pvida;
        this.pvidaactuales = pvidaactuales;
        this.clasearmadura = clasearmadura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public String getRaspersonalidad() {
        return raspersonalidad;
    }

    public void setRaspersonalidad(String raspersonalidad) {
        this.raspersonalidad = raspersonalidad;
    }

    public String getDefectos() {
        return defectos;
    }

    public void setDefectos(String defectos) {
        this.defectos = defectos;
    }

    public String getVinculos() {
        return vinculos;
    }

    public void setVinculos(String vinculos) {
        this.vinculos = vinculos;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Long getPexp() {
        return pexp;
    }

    public void setPexp(Long pexp) {
        this.pexp = pexp;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Serializable getImagenpersonaje() {
        return imagenpersonaje;
    }

    public void setImagenpersonaje(Serializable imagenpersonaje) {
        this.imagenpersonaje = imagenpersonaje;
    }

    public BigInteger getNivel() {
        return nivel;
    }

    public void setNivel(BigInteger nivel) {
        this.nivel = nivel;
    }

    public BigInteger getPvida() {
        return pvida;
    }

    public void setPvida(BigInteger pvida) {
        this.pvida = pvida;
    }

    public BigInteger getPvidaactuales() {
        return pvidaactuales;
    }

    public void setPvidaactuales(BigInteger pvidaactuales) {
        this.pvidaactuales = pvidaactuales;
    }

    public BigInteger getClasearmadura() {
        return clasearmadura;
    }

    public void setClasearmadura(BigInteger clasearmadura) {
        this.clasearmadura = clasearmadura;
    }

    @XmlTransient
    public List<Hechizos> getHechizosList() {
        return hechizosList;
    }

    public void setHechizosList(List<Hechizos> hechizosList) {
        this.hechizosList = hechizosList;
    }

    @XmlTransient
    public List<Dotes> getDotesList() {
        return dotesList;
    }

    public void setDotesList(List<Dotes> dotesList) {
        this.dotesList = dotesList;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @XmlTransient
    public List<Personajehabilidades> getPersonajehabilidadesList() {
        return personajehabilidadesList;
    }

    public void setPersonajehabilidadesList(List<Personajehabilidades> personajehabilidadesList) {
        this.personajehabilidadesList = personajehabilidadesList;
    }

    @XmlTransient
    public List<Personajeatributos> getPersonajeatributosList() {
        return personajeatributosList;
    }

    public void setPersonajeatributosList(List<Personajeatributos> personajeatributosList) {
        this.personajeatributosList = personajeatributosList;
    }

    public Clases getClase() {
        return clase;
    }

    public void setClase(Clases clase) {
        this.clase = clase;
    }

    public Razas getRaza() {
        return raza;
    }

    public void setRaza(Razas raza) {
        this.raza = raza;
    }

    public Subclases getSubclase() {
        return subclase;
    }

    public void setSubclase(Subclases subclase) {
        this.subclase = subclase;
    }

    public Subrazas getSubraza() {
        return subraza;
    }

    public void setSubraza(Subrazas subraza) {
        this.subraza = subraza;
    }

    public Trasfondos getTrasfondo() {
        return trasfondo;
    }

    public void setTrasfondo(Trasfondos trasfondo) {
        this.trasfondo = trasfondo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Personajes)) {
            return false;
        }
        Personajes other = (Personajes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personajes[ id=" + id + " ]";
    }
    
}

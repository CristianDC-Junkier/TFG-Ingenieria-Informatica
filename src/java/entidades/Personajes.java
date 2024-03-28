package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @NamedQuery(name = "Personajes.findByCreador", query = "SELECT p FROM Personajes p WHERE p.usuario = :creador"),
    @NamedQuery(name = "Personajes.findByNombreCreador", query = "SELECT p FROM Personajes p WHERE p.nombre = :nombre AND p.usuario = :creador"),
    @NamedQuery(name = "Personajes.findByIDCreador", query = "SELECT p FROM Personajes p WHERE p.id = :id AND p.usuario = :creador")
})
public class Personajes implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "NOMBRE", nullable = false, length = 120)
    private String nombre;
    @Column(name = "EDAD")
    private Integer edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALINEAMIENTO", nullable = false, length = 30)
    private String alineamiento;
    @Size(max = 255)
    @Column(name = "IDIOMAS", length = 255)
    private String idiomas;
    @Lob
    @Column(name = "IMAGENPERSONAJE")
    private byte[] imagenpersonaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private Integer nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PVIDA", nullable = false)
    private Integer pvida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PVIDAACTUALES", nullable = false)
    private Integer pvidaactuales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASEARMADURA", nullable = false)
    private Integer clasearmadura;
    @OneToMany(mappedBy = "personajeactual")
    private List<Usuarios> usuariosList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
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
    @Column(name = "PEXP")
    private Long pexp;
    @Lob
    @Column(name = "HISTORIA")
    private String historia;
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

    public Personajes(String id, String nombre, Integer edad, String alineamiento, Integer nivel, Integer pvida, Integer pvidaactuales, Integer clasearmadura) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.alineamiento = alineamiento;
        this.nivel = nivel;
        this.pvida = pvida;
        this.pvidaactuales = pvidaactuales;
        this.clasearmadura = clasearmadura;
    }

    public Personajes(String nombre, String alineamiento, Integer nivel, Integer pvida, Integer pvidaactuales, Integer clasearmadura, Usuarios usuario) {
        this.nombre = nombre;
        this.alineamiento = alineamiento;
        this.nivel = nivel;
        this.pvida = pvida;
        this.pvidaactuales = pvidaactuales;
        this.clasearmadura = clasearmadura;
        this.usuario = usuario;
    }

    public Personajes(Personajes personaje, Usuarios usuario, List<Personajes> personajes) {

        this.alineamiento = personaje.getAlineamiento();
        this.apariencia = personaje.getApariencia();
        this.clase = personaje.getClase();
        this.clasearmadura = personaje.getClasearmadura();
        this.defectos = personaje.getDefectos();
        this.dotesList = personaje.getDotesList();
        this.edad = personaje.getEdad();
        this.equipoList = personaje.getEquipoList();
        this.hechizosList = personaje.getHechizosList();
        this.historia = personaje.getHistoria();
        this.idiomas = personaje.getIdiomas();
        this.imagenpersonaje = personaje.getImagenpersonaje();
        this.nivel = personaje.getNivel();

        this.nombre = personaje.getNombre();

        //Si ya existe sumamos uno
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println(i + " " + personajes.get(i).getNombre());
            if (this.nombre.equals(personajes.get(i).getNombre())) {
                //Comprobar si el nombre contiene un numero
                if (this.nombre.matches(".*_\\d+")) {
                    //Extraer el número después del "_"
                    String[] partes = this.nombre.split("_");
                    int numero = Integer.parseInt(partes[1]);
                    numero++;
                    this.nombre = partes[0] + "_" + numero;
                } else {
                    this.nombre = this.nombre + "_1";
                }
                i=-1;
            }
        }

        this.pexp = personaje.getPexp();
        this.pvida = personaje.getPvida();
        this.pvidaactuales = personaje.getPvidaactuales();
        this.raspersonalidad = personaje.getRaspersonalidad();
        this.raza = personaje.getRaza();
        this.subclase = personaje.getSubclase();
        this.subraza = personaje.getSubraza();
        this.trasfondo = personaje.getTrasfondo();
        this.vinculos = personaje.getVinculos();

        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public byte[] getImagenpersonaje() {
        return imagenpersonaje;
    }

    public void setImagenpersonaje(byte[] imagenpersonaje) {
        this.imagenpersonaje = imagenpersonaje;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getPvida() {
        return pvida;
    }

    public void setPvida(Integer pvida) {
        this.pvida = pvida;
    }

    public Integer getPvidaactuales() {
        return pvidaactuales;
    }

    public void setPvidaactuales(Integer pvidaactuales) {
        this.pvidaactuales = pvidaactuales;
    }

    public Integer getClasearmadura() {
        return clasearmadura;
    }

    public void setClasearmadura(Integer clasearmadura) {
        this.clasearmadura = clasearmadura;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

}

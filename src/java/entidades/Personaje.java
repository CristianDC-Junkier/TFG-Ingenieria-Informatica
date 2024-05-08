package entidades;

import java.io.Serializable;
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
@Table(name = "PERSONAJE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personaje.findAll", query = "SELECT p FROM Personaje p"),
    @NamedQuery(name = "Personaje.findById", query = "SELECT p FROM Personaje p WHERE p.id = :id"),
    @NamedQuery(name = "Personaje.findByNombre", query = "SELECT p FROM Personaje p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personaje.findByEdad", query = "SELECT p FROM Personaje p WHERE p.edad = :edad"),
    @NamedQuery(name = "Personaje.findByAlineamiento", query = "SELECT p FROM Personaje p WHERE p.alineamiento = :alineamiento"),
    @NamedQuery(name = "Personaje.findByIdiomas", query = "SELECT p FROM Personaje p WHERE p.idiomas = :idiomas"),
    @NamedQuery(name = "v.findByPexp", query = "SELECT p FROM Personaje p WHERE p.pexp = :pexp"),
    @NamedQuery(name = "Personaje.findByNivel", query = "SELECT p FROM Personaje p WHERE p.nivel = :nivel"),
    @NamedQuery(name = "Personaje.findByPvida", query = "SELECT p FROM Personaje p WHERE p.pvida = :pvida"),
    @NamedQuery(name = "Personaje.findByPvidaactuales", query = "SELECT p FROM Personaje p WHERE p.pvidaactuales = :pvidaactuales"),
    @NamedQuery(name = "Personaje.findByClasearmadura", query = "SELECT p FROM Personaje p WHERE p.clasearmadura = :clasearmadura"),
    @NamedQuery(name = "Personaje.findByCreador", query = "SELECT p FROM Personaje p WHERE p.usuario = :creador"),
    @NamedQuery(name = "Personaje.findByNombreCreador", query = "SELECT p FROM Personaje p WHERE p.nombre = :nombre AND p.usuario = :creador"),
    @NamedQuery(name = "Personaje.findByIDCreador", query = "SELECT p FROM Personaje p WHERE p.id = :id AND p.usuario = :creador")
})
public class Personaje implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "NOMBRE", nullable = false, length = 120)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALINEAMIENTO", nullable = false, length = 30)
    private String alineamiento;
    @Size(max = 255)
    @Column(name = "IDIOMAS", length = 255)
    private String idiomas;
    @Column(name = "PEXP")
    private Integer pexp;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personajes")
    private List<Personajeatributooriginal> personajeatributosoriginalList;

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
    @Lob
    @Column(name = "HISTORIA")
    private String historia;
    @OneToMany(mappedBy = "personajemesa")
    private List<Pertenecemesa> pertenecemesaList;
    @Column(name = "EDAD")
    private Integer edad;

    @OneToMany(mappedBy = "personajeactual")
    private List<Usuario> usuariosList;
    @JoinTable(name = "PERSONAJEHECHIZOS", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "HECHIZO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Hechizo> hechizosList;
    @JoinTable(name = "PERSONAJEDOTES", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "DOTE", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Dote> dotesList;
    @JoinTable(name = "PERSONAJEEQUIPO", joinColumns = {
        @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "EQUIPO", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Equipo> equipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personajes")
    private List<Personajehabilidad> personajehabilidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personajes")
    private List<Personajeatributo> personajeatributosList;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Clase clase;
    @JoinColumn(name = "RAZA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Raza raza;
    @JoinColumn(name = "SUBCLASE", referencedColumnName = "ID")
    @ManyToOne
    private Subclase subclase;
    @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Subraza subraza;
    @JoinColumn(name = "TRASFONDO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Trasfondo trasfondo;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Personaje() {
    }

    public Personaje(String id) {
        this.id = id;
    }

    public Personaje(String id, String nombre, Integer edad, String alineamiento, Integer nivel, Integer pvida, Integer pvidaactuales, Integer clasearmadura) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.alineamiento = alineamiento;
        this.nivel = nivel;
        this.pvida = pvida;
        this.pvidaactuales = pvidaactuales;
        this.clasearmadura = clasearmadura;
    }

    public Personaje(String nombre, String alineamiento, Integer nivel, Integer pvida, Integer pvidaactuales, Integer clasearmadura, Usuario usuario) {
        this.nombre = nombre;
        this.alineamiento = alineamiento;
        this.nivel = nivel;
        this.pvida = pvida;
        this.pvidaactuales = pvidaactuales;
        this.clasearmadura = clasearmadura;
        this.usuario = usuario;
    }

    public Personaje(Personaje personaje, Usuario usuario, List<Personaje> personajes) {

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
                i = -1;
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

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    @XmlTransient
    public List<Hechizo> getHechizosList() {
        return hechizosList;
    }

    public void setHechizosList(List<Hechizo> hechizosList) {
        this.hechizosList = hechizosList;
    }

    @XmlTransient
    public List<Dote> getDotesList() {
        return dotesList;
    }

    public void setDotesList(List<Dote> dotesList) {
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
    public List<Personajehabilidad> getPersonajehabilidadesList() {
        return personajehabilidadesList;
    }

    public void setPersonajehabilidadesList(List<Personajehabilidad> personajehabilidadesList) {
        this.personajehabilidadesList = personajehabilidadesList;
    }

    @XmlTransient
    public List<Personajeatributo> getPersonajeatributosList() {
        return personajeatributosList;
    }

    public void setPersonajeatributosList(List<Personajeatributo> personajeatributosList) {
        this.personajeatributosList = personajeatributosList;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Subclase getSubclase() {
        return subclase;
    }

    public void setSubclase(Subclase subclase) {
        this.subclase = subclase;
    }

    public Subraza getSubraza() {
        return subraza;
    }

    public void setSubraza(Subraza subraza) {
        this.subraza = subraza;
    }

    public Trasfondo getTrasfondo() {
        return trasfondo;
    }

    public void setTrasfondo(Trasfondo trasfondo) {
        this.trasfondo = trasfondo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
        if (!(object instanceof Personaje)) {
            return false;
        }
        Personaje other = (Personaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personaje[ id=" + id + " ]";
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @XmlTransient
    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Pertenecemesa> getPertenecemesaList() {
        return pertenecemesaList;
    }

    public void setPertenecemesaList(List<Pertenecemesa> pertenecemesaList) {
        this.pertenecemesaList = pertenecemesaList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getPexp() {
        return pexp;
    }

    public void setPexp(Integer pexp) {
        this.pexp = pexp;
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
    public List<Personajeatributooriginal> getPersonajeatributosoriginalList() {
        return personajeatributosoriginalList;
    }

    public void setPersonajeatributosoriginalList(List<Personajeatributooriginal> personajeatributosoriginalList) {
        this.personajeatributosoriginalList = personajeatributosoriginalList;
    }

}

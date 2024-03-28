package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PERSONAJEHABILIDADES", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajehabilidades.findAll", query = "SELECT p FROM Personajehabilidades p"),
    @NamedQuery(name = "Personajehabilidades.findByPersonaje", query = "SELECT p FROM Personajehabilidades p WHERE p.personajehabilidadesPK.personaje = :personaje"),
    @NamedQuery(name = "Personajehabilidades.findByHabilidad", query = "SELECT p FROM Personajehabilidades p WHERE p.personajehabilidadesPK.habilidad = :habilidad"),
    @NamedQuery(name = "Personajehabilidades.findByCompetencia", query = "SELECT p FROM Personajehabilidades p WHERE p.competencia = :competencia")})
public class Personajehabilidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonajehabilidadesPK personajehabilidadesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "COMPETENCIA", nullable = false, length = 2)
    private String competencia;
    @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidades habilidades;
    @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personajes personajes;

    public Personajehabilidades() {
    }

    public Personajehabilidades(PersonajehabilidadesPK personajehabilidadesPK) {
        this.personajehabilidadesPK = personajehabilidadesPK;
    }

    public Personajehabilidades(PersonajehabilidadesPK personajehabilidadesPK, String competencia) {
        this.personajehabilidadesPK = personajehabilidadesPK;
        this.competencia = competencia;
    }

    public Personajehabilidades(String personaje, String habilidad) {
        this.personajehabilidadesPK = new PersonajehabilidadesPK(personaje, habilidad);
    }

    public Personajehabilidades(String personaje, String habilidad, String competencia) {
        this.personajehabilidadesPK = new PersonajehabilidadesPK(personaje, habilidad);
        this.competencia = competencia;
    }

    public PersonajehabilidadesPK getPersonajehabilidadesPK() {
        return personajehabilidadesPK;
    }

    public void setPersonajehabilidadesPK(PersonajehabilidadesPK personajehabilidadesPK) {
        this.personajehabilidadesPK = personajehabilidadesPK;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public Habilidades getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidades habilidades) {
        this.habilidades = habilidades;
    }
    
    public void setPersonajes(String personaje) {
        this.personajehabilidadesPK = new PersonajehabilidadesPK(personaje,this.getHabilidades().getId());
    }
    
    public Personajes getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Personajes personajes) {
        this.personajes = personajes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personajehabilidadesPK != null ? personajehabilidadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personajehabilidades)) {
            return false;
        }
        Personajehabilidades other = (Personajehabilidades) object;
        if ((this.personajehabilidadesPK == null && other.personajehabilidadesPK != null) || (this.personajehabilidadesPK != null && !this.personajehabilidadesPK.equals(other.personajehabilidadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personajehabilidades[ personajehabilidadesPK=" + personajehabilidadesPK + " ]";
    }

}

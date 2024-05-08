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
@Table(name = "PERSONAJEHABILIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajehabilidad.findAll", query = "SELECT p FROM Personajehabilidad p"),
    @NamedQuery(name = "Personajehabilidad.findByPersonaje", query = "SELECT p FROM Personajehabilidad p WHERE p.personajehabilidadesPK.personaje = :personaje"),
    @NamedQuery(name = "Personajehabilidad.findByHabilidad", query = "SELECT p FROM Personajehabilidad p WHERE p.personajehabilidadesPK.habilidad = :habilidad"),
    @NamedQuery(name = "Personajehabilidad.findByCompetencia", query = "SELECT p FROM Personajehabilidad p WHERE p.competencia = :competencia")})
public class Personajehabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonajehabilidadPK personajehabilidadesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "COMPETENCIA", nullable = false, length = 2)
    private String competencia;
    @JoinColumn(name = "HABILIDAD", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidades;
    @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personaje personajes;

    public Personajehabilidad() {
    }

    public Personajehabilidad(PersonajehabilidadPK personajehabilidadesPK) {
        this.personajehabilidadesPK = personajehabilidadesPK;
    }

    public Personajehabilidad(PersonajehabilidadPK personajehabilidadesPK, String competencia) {
        this.personajehabilidadesPK = personajehabilidadesPK;
        this.competencia = competencia;
    }

    public Personajehabilidad(String personaje, String habilidad) {
        this.personajehabilidadesPK = new PersonajehabilidadPK(personaje, habilidad);
    }

    public Personajehabilidad(String personaje, String habilidad, String competencia) {
        this.personajehabilidadesPK = new PersonajehabilidadPK(personaje, habilidad);
        this.competencia = competencia;
    }

    public PersonajehabilidadPK getPersonajehabilidadesPK() {
        return personajehabilidadesPK;
    }

    public void setPersonajehabilidadesPK(PersonajehabilidadPK personajehabilidadesPK) {
        this.personajehabilidadesPK = personajehabilidadesPK;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public Habilidad getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidad habilidades) {
        this.habilidades = habilidades;
    }
    
    public void setPersonajes(String personaje) {
        this.personajehabilidadesPK = new PersonajehabilidadPK(personaje,this.getHabilidades().getId());
    }
    
    public Personaje getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Personaje personajes) {
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
        if (!(object instanceof Personajehabilidad)) {
            return false;
        }
        Personajehabilidad other = (Personajehabilidad) object;
        if ((this.personajehabilidadesPK == null && other.personajehabilidadesPK != null) || (this.personajehabilidadesPK != null && !this.personajehabilidadesPK.equals(other.personajehabilidadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personajehabilidad[ personajehabilidadesPK=" + personajehabilidadesPK + " ]";
    }

}

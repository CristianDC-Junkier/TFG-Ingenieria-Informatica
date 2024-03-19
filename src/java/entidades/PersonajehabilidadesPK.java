
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Embeddable
public class PersonajehabilidadesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "PERSONAJE", nullable = false, length = 36)
    private String personaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "HABILIDAD", nullable = false, length = 36)
    private String habilidad;

    public PersonajehabilidadesPK() {
    }

    public PersonajehabilidadesPK(String personaje, String habilidad) {
        this.personaje = personaje;
        this.habilidad = habilidad;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaje != null ? personaje.hashCode() : 0);
        hash += (habilidad != null ? habilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonajehabilidadesPK)) {
            return false;
        }
        PersonajehabilidadesPK other = (PersonajehabilidadesPK) object;
        if ((this.personaje == null && other.personaje != null) || (this.personaje != null && !this.personaje.equals(other.personaje))) {
            return false;
        }
        if ((this.habilidad == null && other.habilidad != null) || (this.habilidad != null && !this.habilidad.equals(other.habilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.PersonajehabilidadesPK[ personaje=" + personaje + ", habilidad=" + habilidad + " ]";
    }
    
}

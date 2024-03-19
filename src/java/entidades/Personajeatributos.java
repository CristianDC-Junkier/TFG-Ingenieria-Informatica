
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
@Table(name = "PERSONAJEATRIBUTOS", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajeatributos.findAll", query = "SELECT p FROM Personajeatributos p"),
    @NamedQuery(name = "Personajeatributos.findByPersonaje", query = "SELECT p FROM Personajeatributos p WHERE p.personajeatributosPK.personaje = :personaje"),
    @NamedQuery(name = "Personajeatributos.findByAtributo", query = "SELECT p FROM Personajeatributos p WHERE p.personajeatributosPK.atributo = :atributo"),
    @NamedQuery(name = "Personajeatributos.findByValor", query = "SELECT p FROM Personajeatributos p WHERE p.valor = :valor"),
    @NamedQuery(name = "Personajeatributos.findBySalvacion", query = "SELECT p FROM Personajeatributos p WHERE p.salvacion = :salvacion")})
public class Personajeatributos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonajeatributosPK personajeatributosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR", nullable = false)
    private short valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SALVACION", nullable = false, length = 2)
    private String salvacion;
    @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atributos atributos;
    @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personajes personajes;

    public Personajeatributos() {
    }

    public Personajeatributos(PersonajeatributosPK personajeatributosPK) {
        this.personajeatributosPK = personajeatributosPK;
    }

    public Personajeatributos(PersonajeatributosPK personajeatributosPK, short valor, String salvacion) {
        this.personajeatributosPK = personajeatributosPK;
        this.valor = valor;
        this.salvacion = salvacion;
    }

    public Personajeatributos(String personaje, String atributo) {
        this.personajeatributosPK = new PersonajeatributosPK(personaje, atributo);
    }

    public PersonajeatributosPK getPersonajeatributosPK() {
        return personajeatributosPK;
    }

    public void setPersonajeatributosPK(PersonajeatributosPK personajeatributosPK) {
        this.personajeatributosPK = personajeatributosPK;
    }

    public short getValor() {
        return valor;
    }

    public void setValor(short valor) {
        this.valor = valor;
    }

    public String getSalvacion() {
        return salvacion;
    }

    public void setSalvacion(String salvacion) {
        this.salvacion = salvacion;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
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
        hash += (personajeatributosPK != null ? personajeatributosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personajeatributos)) {
            return false;
        }
        Personajeatributos other = (Personajeatributos) object;
        if ((this.personajeatributosPK == null && other.personajeatributosPK != null) || (this.personajeatributosPK != null && !this.personajeatributosPK.equals(other.personajeatributosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personajeatributos[ personajeatributosPK=" + personajeatributosPK + " ]";
    }
    
}

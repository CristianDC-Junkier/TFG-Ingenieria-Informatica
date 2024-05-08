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
@Table(name = "PERSONAJEATRIBUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajeatributo.findAll", query = "SELECT p FROM Personajeatributo p"),
    @NamedQuery(name = "Personajeatributo.findByPersonaje", query = "SELECT p FROM Personajeatributo p WHERE p.personajeatributosPK.personaje = :personaje"),
    @NamedQuery(name = "Personajeatributo.findByAtributo", query = "SELECT p FROM Personajeatributo p WHERE p.personajeatributosPK.atributo = :atributo"),
    @NamedQuery(name = "Personajeatributo.findByValor", query = "SELECT p FROM Personajeatributo p WHERE p.valor = :valor"),
    @NamedQuery(name = "Personajeatributo.findBySalvacion", query = "SELECT p FROM Personajeatributo p WHERE p.salvacion = :salvacion")})
public class Personajeatributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonajeatributoPK personajeatributosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR", nullable = false)
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SALVACION", nullable = false, length = 2)
    private String salvacion;
    @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atributo atributos;
    @JoinColumn(name = "PERSONAJE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personaje personajes;

    public Personajeatributo() {
    }

    public Personajeatributo(PersonajeatributoPK personajeatributosPK) {
        this.personajeatributosPK = personajeatributosPK;
    }

    public Personajeatributo(PersonajeatributoPK personajeatributosPK, int valor, String salvacion) {
        this.personajeatributosPK = personajeatributosPK;
        this.valor = valor;
        this.salvacion = salvacion;
    }

    public Personajeatributo(String personaje, String atributo) {
        this.personajeatributosPK = new PersonajeatributoPK(personaje, atributo);
    }

    public Personajeatributo(String personaje, String atributo, int valor, String salvacion) {
        this.personajeatributosPK = new PersonajeatributoPK(personaje, atributo);
        this.valor = valor;
        this.salvacion = salvacion;
    }

    public PersonajeatributoPK getPersonajeatributosPK() {
        return personajeatributosPK;
    }

    public void setPersonajeatributosPK(PersonajeatributoPK personajeatributosPK) {
        this.personajeatributosPK = personajeatributosPK;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getSalvacion() {
        return salvacion;
    }

    public void setSalvacion(String salvacion) {
        this.salvacion = salvacion;
    }

    public Atributo getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributo atributos) {
        this.atributos = atributos;
    }

    public Personaje getPersonajes() {
        return personajes;
    }

    public void setPersonajes(String personaje) {
        this.personajeatributosPK = new PersonajeatributoPK(personaje,this.getAtributos().getId());
    }

    public void setPersonajes(Personaje personajes) {
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
        if (!(object instanceof Personajeatributo)) {
            return false;
        }
        Personajeatributo other = (Personajeatributo) object;
        if ((this.personajeatributosPK == null && other.personajeatributosPK != null) || (this.personajeatributosPK != null && !this.personajeatributosPK.equals(other.personajeatributosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlador.Personajeatributo[ personajeatributosPK=" + personajeatributosPK + " ]";
    }

}

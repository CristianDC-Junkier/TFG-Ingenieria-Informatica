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
@Table(name = "PERSONAJEATRIBUTOSORIGINAL", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personajeatributosoriginal.findAll", query = "SELECT p FROM Personajeatributosoriginal p"),
    @NamedQuery(name = "Personajeatributosoriginal.findByPersonaje", query = "SELECT p FROM Personajeatributosoriginal p WHERE p.personajeatributosoriginalPK.personaje = :personaje"),
    @NamedQuery(name = "Personajeatributosoriginal.findByAtributo", query = "SELECT p FROM Personajeatributosoriginal p WHERE p.personajeatributosoriginalPK.atributo = :atributo"),
    @NamedQuery(name = "Personajeatributosoriginal.findByValor", query = "SELECT p FROM Personajeatributosoriginal p WHERE p.valor = :valor"),
    @NamedQuery(name = "Personajeatributosoriginal.findBySalvacion", query = "SELECT p FROM Personajeatributosoriginal p WHERE p.salvacion = :salvacion")})
public class Personajeatributosoriginal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonajeatributosoriginalPK personajeatributosoriginalPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR", nullable = false)
    private Integer valor;
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

    public Personajeatributosoriginal() {
    }

    public Personajeatributosoriginal(PersonajeatributosoriginalPK personajeatributosoriginalPK) {
        this.personajeatributosoriginalPK = personajeatributosoriginalPK;
    }

    public Personajeatributosoriginal(PersonajeatributosoriginalPK personajeatributosoriginalPK, Integer valor, String salvacion) {
        this.personajeatributosoriginalPK = personajeatributosoriginalPK;
        this.valor = valor;
        this.salvacion = salvacion;
    }

    public Personajeatributosoriginal(String personaje, String atributo, Integer valor, String salvacion) {
        this.personajeatributosoriginalPK = new PersonajeatributosoriginalPK(personaje, atributo);
        this.valor = valor;
        this.salvacion = salvacion;
    }

    public Personajeatributosoriginal(String personaje, String atributo) {
        this.personajeatributosoriginalPK = new PersonajeatributosoriginalPK(personaje, atributo);
    }

    public PersonajeatributosoriginalPK getPersonajeatributosoriginalPK() {
        return personajeatributosoriginalPK;
    }

    public void setPersonajeatributosoriginalPK(PersonajeatributosoriginalPK personajeatributosoriginalPK) {
        this.personajeatributosoriginalPK = personajeatributosoriginalPK;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
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
        hash += (personajeatributosoriginalPK != null ? personajeatributosoriginalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personajeatributosoriginal)) {
            return false;
        }
        Personajeatributosoriginal other = (Personajeatributosoriginal) object;
        if ((this.personajeatributosoriginalPK == null && other.personajeatributosoriginalPK != null) || (this.personajeatributosoriginalPK != null && !this.personajeatributosoriginalPK.equals(other.personajeatributosoriginalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Personajeatributosoriginal[ personajeatributosoriginalPK=" + personajeatributosoriginalPK + " ]";
    }

}

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
@Table(name = "SUMARAZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sumaraza.findAll", query = "SELECT s FROM Sumaraza s"),
    @NamedQuery(name = "Sumaraza.findBySubraza", query = "SELECT s FROM Sumaraza s WHERE s.sumarazaPK.subraza = :subraza"),
    @NamedQuery(name = "Sumaraza.findByAtributo", query = "SELECT s FROM Sumaraza s WHERE s.sumarazaPK.atributo = :atributo"),
    @NamedQuery(name = "Sumaraza.findByModificador", query = "SELECT s FROM Sumaraza s WHERE s.modificador = :modificador")})
public class Sumaraza implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SumarazaPK sumarazaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MODIFICADOR", nullable = false, length = 2)
    private String modificador;
    @JoinColumn(name = "ATRIBUTO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atributo atributos;
    @JoinColumn(name = "SUBRAZA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subraza subrazas;

    public Sumaraza() {
    }

    public Sumaraza(SumarazaPK sumarazaPK) {
        this.sumarazaPK = sumarazaPK;
    }

    public Sumaraza(SumarazaPK sumarazaPK, String modificador) {
        this.sumarazaPK = sumarazaPK;
        this.modificador = modificador;
    }

    public Sumaraza(String subraza, String atributo) {
        this.sumarazaPK = new SumarazaPK(subraza, atributo);
    }

    public SumarazaPK getSumarazaPK() {
        return sumarazaPK;
    }

    public void setSumarazaPK(SumarazaPK sumarazaPK) {
        this.sumarazaPK = sumarazaPK;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    public Subraza getSubrazas() {
        return subrazas;
    }

    public void setSubrazas(Subraza subrazas) {
        this.subrazas = subrazas;
    }
    
    public Atributo getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributo atributos) {
        this.atributos = atributos;
    }

    public String getAtributoID() {
        return sumarazaPK.getAtributo();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sumarazaPK != null ? sumarazaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sumaraza)) {
            return false;
        }
        Sumaraza other = (Sumaraza) object;
        if ((this.sumarazaPK == null && other.sumarazaPK != null) || (this.sumarazaPK != null && !this.sumarazaPK.equals(other.sumarazaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Sumaraza[ sumarazaPK=" + sumarazaPK + " ]";
    }

}

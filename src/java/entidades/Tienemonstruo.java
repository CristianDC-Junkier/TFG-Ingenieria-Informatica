
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
@Table(name = "TIENEMONSTRUO", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienemonstruo.findAll", query = "SELECT t FROM Tienemonstruo t"),
    @NamedQuery(name = "Tienemonstruo.findByMonstruo", query = "SELECT t FROM Tienemonstruo t WHERE t.tienemonstruoPK.monstruo = :monstruo"),
    @NamedQuery(name = "Tienemonstruo.findByAtributo", query = "SELECT t FROM Tienemonstruo t WHERE t.tienemonstruoPK.atributo = :atributo"),
    @NamedQuery(name = "Tienemonstruo.findByValor", query = "SELECT t FROM Tienemonstruo t WHERE t.valor = :valor"),
    @NamedQuery(name = "Tienemonstruo.findByModificador", query = "SELECT t FROM Tienemonstruo t WHERE t.modificador = :modificador")})
public class Tienemonstruo implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VALOR", nullable = false, length = 10)
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MODIFICADOR", nullable = false, length = 10)
    private String modificador;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TienemonstruoPK tienemonstruoPK;
    @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Monstruos monstruos;

    public Tienemonstruo() {
    }

    public Tienemonstruo(TienemonstruoPK tienemonstruoPK) {
        this.tienemonstruoPK = tienemonstruoPK;
    }

    public Tienemonstruo(TienemonstruoPK tienemonstruoPK, String valor, String modificador) {
        this.tienemonstruoPK = tienemonstruoPK;
        this.valor = valor;
        this.modificador = modificador;
    }

    public Tienemonstruo(String monstruo, String atributo) {
        this.tienemonstruoPK = new TienemonstruoPK(monstruo, atributo);
    }

    public TienemonstruoPK getTienemonstruoPK() {
        return tienemonstruoPK;
    }

    public void setTienemonstruoPK(TienemonstruoPK tienemonstruoPK) {
        this.tienemonstruoPK = tienemonstruoPK;
    }


    public Monstruos getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(Monstruos monstruos) {
        this.monstruos = monstruos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tienemonstruoPK != null ? tienemonstruoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienemonstruo)) {
            return false;
        }
        Tienemonstruo other = (Tienemonstruo) object;
        if ((this.tienemonstruoPK == null && other.tienemonstruoPK != null) || (this.tienemonstruoPK != null && !this.tienemonstruoPK.equals(other.tienemonstruoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tienemonstruo[ tienemonstruoPK=" + tienemonstruoPK + " ]";
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }
    
}

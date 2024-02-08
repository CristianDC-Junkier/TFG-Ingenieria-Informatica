
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
@Table(name = "COMPETENTEMONSTRUO", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competentemonstruo.findAll", query = "SELECT c FROM Competentemonstruo c"),
    @NamedQuery(name = "Competentemonstruo.findByMonstruo", query = "SELECT c FROM Competentemonstruo c WHERE c.competentemonstruoPK.monstruo = :monstruo"),
    @NamedQuery(name = "Competentemonstruo.findByHabilidad", query = "SELECT c FROM Competentemonstruo c WHERE c.competentemonstruoPK.habilidad = :habilidad"),
    @NamedQuery(name = "Competentemonstruo.findByCompetencia", query = "SELECT c FROM Competentemonstruo c WHERE c.competencia = :competencia")})
public class Competentemonstruo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetentemonstruoPK competentemonstruoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COMPETENCIA", nullable = false, length = 10)
    private String competencia;
    @JoinColumn(name = "MONSTRUO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Monstruos monstruos;

    public Competentemonstruo() {
    }

    public Competentemonstruo(CompetentemonstruoPK competentemonstruoPK) {
        this.competentemonstruoPK = competentemonstruoPK;
    }

    public Competentemonstruo(CompetentemonstruoPK competentemonstruoPK, String competencia) {
        this.competentemonstruoPK = competentemonstruoPK;
        this.competencia = competencia;
    }

    public Competentemonstruo(String monstruo, String habilidad) {
        this.competentemonstruoPK = new CompetentemonstruoPK(monstruo, habilidad);
    }

    public CompetentemonstruoPK getCompetentemonstruoPK() {
        return competentemonstruoPK;
    }

    public void setCompetentemonstruoPK(CompetentemonstruoPK competentemonstruoPK) {
        this.competentemonstruoPK = competentemonstruoPK;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
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
        hash += (competentemonstruoPK != null ? competentemonstruoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competentemonstruo)) {
            return false;
        }
        Competentemonstruo other = (Competentemonstruo) object;
        if ((this.competentemonstruoPK == null && other.competentemonstruoPK != null) || (this.competentemonstruoPK != null && !this.competentemonstruoPK.equals(other.competentemonstruoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Competentemonstruo[ competentemonstruoPK=" + competentemonstruoPK + " ]";
    }
    
}

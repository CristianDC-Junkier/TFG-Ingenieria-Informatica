/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "USASUBCLASE", catalog = "", schema = "SYS_G4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usasubclase.findAll", query = "SELECT u FROM Usasubclase u"),
    @NamedQuery(name = "Usasubclase.findBySubclase", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.subclase = :subclase"),
    @NamedQuery(name = "Usasubclase.findByRasgo", query = "SELECT u FROM Usasubclase u WHERE u.usasubclasePK.rasgo = :rasgo"),
    @NamedQuery(name = "Usasubclase.findByNivel", query = "SELECT u FROM Usasubclase u WHERE u.nivel = :nivel")})
public class Usasubclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsasubclasePK usasubclasePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL", nullable = false)
    private short nivel;
    @JoinColumn(name = "RASGO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rasgos rasgos;
    @JoinColumn(name = "SUBCLASE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subclases subclases;

    public Usasubclase() {
    }

    public Usasubclase(UsasubclasePK usasubclasePK) {
        this.usasubclasePK = usasubclasePK;
    }

    public Usasubclase(UsasubclasePK usasubclasePK, short nivel) {
        this.usasubclasePK = usasubclasePK;
        this.nivel = nivel;
    }

    public Usasubclase(String subclase, String rasgo) {
        this.usasubclasePK = new UsasubclasePK(subclase, rasgo);
    }

    public UsasubclasePK getUsasubclasePK() {
        return usasubclasePK;
    }

    public void setUsasubclasePK(UsasubclasePK usasubclasePK) {
        this.usasubclasePK = usasubclasePK;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    public Rasgos getRasgos() {
        return rasgos;
    }

    public void setRasgos(Rasgos rasgos) {
        this.rasgos = rasgos;
    }

    public Subclases getSubclases() {
        return subclases;
    }

    public void setSubclases(Subclases subclases) {
        this.subclases = subclases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usasubclasePK != null ? usasubclasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usasubclase)) {
            return false;
        }
        Usasubclase other = (Usasubclase) object;
        if ((this.usasubclasePK == null && other.usasubclasePK != null) || (this.usasubclasePK != null && !this.usasubclasePK.equals(other.usasubclasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usasubclase[ usasubclasePK=" + usasubclasePK + " ]";
    }
    
}

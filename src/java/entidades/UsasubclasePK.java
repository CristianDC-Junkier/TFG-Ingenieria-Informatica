/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class UsasubclasePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "SUBCLASE", nullable = false, length = 36)
    private String subclase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "RASGO", nullable = false, length = 36)
    private String rasgo;

    public UsasubclasePK() {
    }

    public UsasubclasePK(String subclase, String rasgo) {
        this.subclase = subclase;
        this.rasgo = rasgo;
    }

    public String getSubclase() {
        return subclase;
    }

    public void setSubclase(String subclase) {
        this.subclase = subclase;
    }

    public String getRasgo() {
        return rasgo;
    }

    public void setRasgo(String rasgo) {
        this.rasgo = rasgo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subclase != null ? subclase.hashCode() : 0);
        hash += (rasgo != null ? rasgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsasubclasePK)) {
            return false;
        }
        UsasubclasePK other = (UsasubclasePK) object;
        if ((this.subclase == null && other.subclase != null) || (this.subclase != null && !this.subclase.equals(other.subclase))) {
            return false;
        }
        if ((this.rasgo == null && other.rasgo != null) || (this.rasgo != null && !this.rasgo.equals(other.rasgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsasubclasePK[ subclase=" + subclase + ", rasgo=" + rasgo + " ]";
    }
    
}

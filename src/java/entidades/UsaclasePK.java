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
public class UsaclasePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "CLASE", nullable = false, length = 36)
    private String clase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "RASGO", nullable = false, length = 36)
    private String rasgo;

    public UsaclasePK() {
    }

    public UsaclasePK(String clase, String rasgo) {
        this.clase = clase;
        this.rasgo = rasgo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
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
        hash += (clase != null ? clase.hashCode() : 0);
        hash += (rasgo != null ? rasgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsaclasePK)) {
            return false;
        }
        UsaclasePK other = (UsaclasePK) object;
        if ((this.clase == null && other.clase != null) || (this.clase != null && !this.clase.equals(other.clase))) {
            return false;
        }
        if ((this.rasgo == null && other.rasgo != null) || (this.rasgo != null && !this.rasgo.equals(other.rasgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsaclasePK[ clase=" + clase + ", rasgo=" + rasgo + " ]";
    }
    
}

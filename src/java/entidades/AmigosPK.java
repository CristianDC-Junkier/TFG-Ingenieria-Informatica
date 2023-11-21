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
public class AmigosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AMIGO1")
    private String amigo1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AMIGO2")
    private String amigo2;

    public AmigosPK() {
    }

    public AmigosPK(String amigo1, String amigo2) {
        this.amigo1 = amigo1;
        this.amigo2 = amigo2;
    }

    public String getAmigo1() {
        return amigo1;
    }

    public void setAmigo1(String amigo1) {
        this.amigo1 = amigo1;
    }

    public String getAmigo2() {
        return amigo2;
    }

    public void setAmigo2(String amigo2) {
        this.amigo2 = amigo2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amigo1 != null ? amigo1.hashCode() : 0);
        hash += (amigo2 != null ? amigo2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmigosPK)) {
            return false;
        }
        AmigosPK other = (AmigosPK) object;
        if ((this.amigo1 == null && other.amigo1 != null) || (this.amigo1 != null && !this.amigo1.equals(other.amigo1))) {
            return false;
        }
        if ((this.amigo2 == null && other.amigo2 != null) || (this.amigo2 != null && !this.amigo2.equals(other.amigo2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AmigosPK[ amigo1=" + amigo1 + ", amigo2=" + amigo2 + " ]";
    }
    
}

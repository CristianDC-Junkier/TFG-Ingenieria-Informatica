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
public class PersonajeatributosoriginalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "PERSONAJE", nullable = false, length = 36)
    private String personaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ATRIBUTO", nullable = false, length = 36)
    private String atributo;

    public PersonajeatributosoriginalPK() {
    }

    public PersonajeatributosoriginalPK(String personaje, String atributo) {
        this.personaje = personaje;
        this.atributo = atributo;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaje != null ? personaje.hashCode() : 0);
        hash += (atributo != null ? atributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonajeatributosoriginalPK)) {
            return false;
        }
        PersonajeatributosoriginalPK other = (PersonajeatributosoriginalPK) object;
        if ((this.personaje == null && other.personaje != null) || (this.personaje != null && !this.personaje.equals(other.personaje))) {
            return false;
        }
        if ((this.atributo == null && other.atributo != null) || (this.atributo != null && !this.atributo.equals(other.atributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PersonajeatributosoriginalPK[ personaje=" + personaje + ", atributo=" + atributo + " ]";
    }
    
}

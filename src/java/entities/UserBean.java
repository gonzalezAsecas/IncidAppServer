/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lander Lluvia
 */
@Entity
@Table(name="user", schema="incidapp")
@XmlRootElement
public class UserBean extends PersonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String dni;
    private String street;
    @ManyToOne
    private TownHallBean townHall;
    @OneToMany(mappedBy="user")
    private List<IncidentBean> incidents;
    @ManyToMany(mappedBy="users")
    private List<IncidentBean> signatureIncidents;

    public UserBean() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public TownHallBean getTownHall() {
        return townHall;
    }

    public void setTownHall(TownHallBean townHall) {
        this.townHall = townHall;
    }

    public List<IncidentBean> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents = incidents;
    }

    public List<IncidentBean> getSignatureIncidents() {
        return signatureIncidents;
    }

    public void setSignatureIncidents(List<IncidentBean> signatureIncidents) {
        this.signatureIncidents = signatureIncidents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dni);
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + Objects.hashCode(this.townHall);
        hash = 53 * hash + Objects.hashCode(this.incidents);
        hash = 53 * hash + Objects.hashCode(this.signatureIncidents);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserBean other = (UserBean) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.townHall, other.townHall)) {
            return false;
        }
        if (!Objects.equals(this.incidents, other.incidents)) {
            return false;
        }
        if (!Objects.equals(this.signatureIncidents, other.signatureIncidents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserBean{" + "dni=" + dni + ", street=" + street + ", townHall=" + townHall + ", incidents=" + incidents + ", signatureIncidents=" + signatureIncidents + '}';
    }
}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jon Gonzalez
 */
@Entity
@Table(name="location", schema="incidapp")
@XmlRootElement
@NamedQuery(name="findAllLocations",
            query="SELECT s FROM LocationBean s")
public class LocationBean implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String street;
    @ManyToOne
    private TownHallBean townHall;
    @OneToMany(mappedBy="location")
    private List<IncidentBean> incidents;

    public LocationBean(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer idLocation) {
        this.id = idLocation;
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

    @XmlTransient
    public List<IncidentBean> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents = incidents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.street);
        hash = 83 * hash + Objects.hashCode(this.townHall);
        hash = 83 * hash + Objects.hashCode(this.incidents);
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
        final LocationBean other = (LocationBean) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.townHall, other.townHall)) {
            return false;
        }
        if (!Objects.equals(this.incidents, other.incidents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocationBean{" + "idLocation=" + id + ", street=" + street + ", townHall=" + townHall + ", incidents=" + incidents + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lander Lluvia
 */

@Entity
@Table(name="townhalluser", schema="incidapp")
public class TownHallUserBean extends PersonBean implements Serializable  {
   @ManyToOne
   private TownHallBean townHall;

    public TownHallUserBean() {}

    public TownHallBean getTownHall() {
        return townHall;
    }

    public void setTownHall(TownHallBean townHall) {
        this.townHall = townHall;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.townHall);
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
        final TownHallUserBean other = (TownHallUserBean) obj;
        if (this.townHall != other.townHall) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " TownHallUserBean{" + "idTownHall=" + townHall + '}';
    }

    
   
}

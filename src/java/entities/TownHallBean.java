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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jon Gonzalez
 */
@Entity
@Table(name="townhall", schema="incidapp")
public class TownHallBean implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTownHall;
    @NotNull
    private String locality;
    private String email;
    private String telephoneNumbre;
    @OneToMany(mappedBy="townHall")
    private List<LocationBean> locations;
    @OneToMany(mappedBy="townHall")
    private List<TownHallUserBean> townhallusers;
    @OneToMany(mappedBy="townHall")
    private List<UserBean> users;

    public TownHallBean(){}

    public Integer getIdTownHall() {
        return idTownHall;
    }

    public void setIdTownHall(Integer idTownHall) {
        this.idTownHall = idTownHall;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumbre() {
        return telephoneNumbre;
    }

    public void setTelephoneNumbre(String telephoneNumbre) {
        this.telephoneNumbre = telephoneNumbre;
    }
}

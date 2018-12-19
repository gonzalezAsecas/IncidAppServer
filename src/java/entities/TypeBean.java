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
@Table(name="type", schema="incidapp")
public class TypeBean implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idType;
    @NotNull
    private String name;
    @NotNull
    private Integer Severity;
    @OneToMany(mappedBy="type")
    private List<IncidentBean> incidents;

    public TypeBean(){}

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeverity() {
        return Severity;
    }

    public void setSeverity(Integer Severity) {
        this.Severity = Severity;
    }

    public List<IncidentBean> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents = incidents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idType);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.Severity);
        hash = 67 * hash + Objects.hashCode(this.incidents);
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
        final TypeBean other = (TypeBean) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.idType, other.idType)) {
            return false;
        }
        if (!Objects.equals(this.Severity, other.Severity)) {
            return false;
        }
        if (!Objects.equals(this.incidents, other.incidents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeBean{" + "idType=" + idType + ", name=" + name + ", Severity=" + Severity + ", incidents=" + incidents + '}';
    }
}

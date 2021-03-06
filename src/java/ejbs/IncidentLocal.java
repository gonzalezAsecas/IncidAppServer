/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.IncidentBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author Gorka Redondo
 */
public interface IncidentLocal {
    public void createIncident(IncidentBean incident) throws CreateException;

    public void editIncident(IncidentBean incident) throws UpdateException;

    public void removeIncident(IncidentBean incident) throws DeleteException;

    public IncidentBean findIncidentById(Integer id) throws ReadException;

    public List<IncidentBean> findAllIncidents() throws ReadException;
}

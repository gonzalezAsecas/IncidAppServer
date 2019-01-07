/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.IncidentBean;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import exceptions.CreateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jon Gonzalez
 */
@Stateless
public class IncidentEJB implements IncidentLocal {
    
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * 
     * @param incident
     * @return
     * @throws CreateException 
     */
    @Override
    public IncidentBean createIncident(IncidentBean incident) throws CreateException{
        try{
            LOGGER.info("IncidentEJB: Adding an incident.");
            em.persist(incident);
            LOGGER.info("IncidentEJB: Incident added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "IncidentEJB: Exception adding the incident.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return incident;
    }
    
    /**
     * 
     * @param entity
     * @throws UpdateException 
     */
    @Override
    public void editIncident(IncidentBean entity) throws UpdateException{
        try{
            LOGGER.info("IncidentEJB: Editting an incident.");
            em.merge(entity);
            em.flush();
            LOGGER.info("IncidentEJB: Incident updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "IncidentEJB: Exception updating the incident.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param entity
     * @throws DeleteException 
     */
    @Override
    public void removeIncident(IncidentBean entity) throws DeleteException{
        try{
            LOGGER.info("IncidentEJB: Removing an incident.");
            em.remove(em.merge(entity));
            LOGGER.info("IncidentEJB: Incident removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "IncidentEJB: Exception removing the incident.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param incident
     * @return
     * @throws ReadException 
     */
    @Override
    public IncidentBean findIncidentbyId(IncidentBean incident) throws ReadException{
        IncidentBean incid = null;
        try{
            LOGGER.info("IncidentEJB: Finding an incident by id.");
            incid = em.find(IncidentBean.class, incident.getIdIncident());
            LOGGER.info("IncidentEJB: Incident found by id.");
            return incid;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "IncidentEJB: Exception finding the incident by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<IncidentBean> findAllIncidents() throws ReadException{
        List<IncidentBean> incidents = null;
        try{
            LOGGER.info("IncidentEJB: Finding all the incidents.");
            incidents = em.createNamedQuery("finsAllIncidents").getResultList();
            LOGGER.info("IncidentEJB: Incidents found.");
            return incidents;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "IncidentEJB: Exception finding the incidents.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<IncidentBean> findIncidentsbyFilter(IncidentBean incident) throws ReadException {
        List<IncidentBean> incidents = null;
        try{
            LOGGER.info("IncidentEJB: Finding the incidents filtred.");
            incidents = em.createNamedQuery("finsIncidentsbyFilter")
                    .setParameter("title", incident.getTitle())
                    .setParameter("type", incident.getType())
                    .setParameter("estate", incident.getEstate()).getResultList();
            LOGGER.info("IncidentEJB: Incidents found.");
            return incidents;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "IncidentEJB: Exception finding the incidents by filter.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gorka Redondo
 */
@Stateless
public class IncidentEJB implements IncidentLocal{
    
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
    public void createIncident(IncidentBean incident) throws CreateException {
        try{
            LOGGER.info("IncidentEJB: Adding a incident.");
            em.persist(incident);
            LOGGER.info("IncidentEJB: incident added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "IncidentEJB: Exception adding the incident.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param incident
     * @throws UpdateException 
     */
    @Override
    public void editIncident(IncidentBean incident) throws UpdateException {
        try{
            LOGGER.info("IncidentEJB: Editting a incident.");
            em.merge(incident);
            em.flush();
            LOGGER.info("IncidentEJB: incident updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "IncidentEJB: Exception updating the incident.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * 
     * @param incident
     * @throws DeleteException 
     */
    @Override
    public void removeIncident(IncidentBean incident) throws DeleteException {
        try{
            LOGGER.info("IncidentEJB: Removing a incident.");
            em.remove(em.merge(incident));
            LOGGER.info("IncidentEJB: incident removed.");
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
    public IncidentBean findIncidentbyId(Integer id) throws ReadException {
        IncidentBean inc = null;
        try{
            LOGGER.info("IncidentEJB: Finding a incident by id.");
            inc = em.find(IncidentBean.class, id);
            LOGGER.info("IncidentEJB: incident found by id.");
            return inc;
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
    public List<IncidentBean> findAllIncidents() throws ReadException {
        List<IncidentBean> incidents = null;
        try{
            LOGGER.info("IncidentEJB: Finding all the incidents.");
            incidents = em.createNamedQuery("findAllIncidents").getResultList();
            LOGGER.info("IncidentEJB: incidents found.");
            return incidents;
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "IncidentEJB: Exception finding the incidents.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}

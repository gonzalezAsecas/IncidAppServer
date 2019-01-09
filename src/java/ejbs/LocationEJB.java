/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.LocationBean;
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
 * @author Jon Gonzalez
 */
@Stateless
public class LocationEJB implements LocationLocal{
    
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
     * @param location
     * @return
     * @throws CreateException 
     */
    @Override
    public LocationBean createLocation(LocationBean location) throws CreateException {
        try{
            LOGGER.info("LocationEJB: Adding a location.");
            em.persist(location);
            LOGGER.info("LocationEJB: Location added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "LocationEJB: Exception adding the location.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return location;
    }
    
    /**
     * 
     * @param location
     * @throws UpdateException 
     */
    @Override
    public void editLocation(LocationBean location) throws UpdateException {
        try{
            LOGGER.info("LocationEJB: Editting a location.");
            em.merge(location);
            em.flush();
            LOGGER.info("LocationEJB: Location updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "LocationEJB: Exception updating the location.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * 
     * @param location
     * @throws DeleteException 
     */
    @Override
    public void removeLocation(LocationBean location) throws DeleteException {
        try{
            LOGGER.info("LocationEJB: Removing a location.");
            em.remove(em.merge(location));
            LOGGER.info("LocationEJB: Location removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "LocationEJB: Exception removing the location.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * 
     * @param location
     * @return
     * @throws ReadException 
     */
    @Override
    public LocationBean findLocationbyId(LocationBean location) throws ReadException {
        LocationBean loc = null;
        try{
            LOGGER.info("LocationEJB: Finding a location by id.");
            loc = em.find(LocationBean.class, location.getId());
            LOGGER.info("LocationEJB: Location found by id.");
            return loc;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "LocationEJB: Exception finding the location by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<LocationBean> findAllLocations() throws ReadException {
        List<LocationBean> locations = null;
        try{
            LOGGER.info("LocationEJB: Finding all the locations.");
            locations = em.createNamedQuery("finsAllLocations").getResultList();
            LOGGER.info("LocationEJB: Locations found.");
            return locations;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "LocationEJB: Exception finding the locations.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
}

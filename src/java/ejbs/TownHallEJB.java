/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TownHallBean;
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
public class TownHallEJB implements TownHallLocal{

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
     * @param townhall
     * @return
     * @throws CreateException 
     */
    @Override
    public TownHallBean createTownHall(TownHallBean townhall) throws CreateException {
        try{
            LOGGER.info("TownHallEJB: Adding a location.");
            em.persist(townhall);
            LOGGER.info("TownHallEJB: Town hall added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "TownHallEJB: Exception adding the town hall.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return townhall;
    }

    /**
     * 
     * @param townhall
     * @throws UpdateException 
     */
    @Override
    public void editTownHall(TownHallBean townhall) throws UpdateException {
        try{
            LOGGER.info("TownHallEJB: Editting a town hall.");
            em.merge(townhall);
            em.flush();
            LOGGER.info("TownHallEJB: Town hall updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "LocationEJB: Exception updating the town hall.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param townhall
     * @throws DeleteException 
     */
    @Override
    public void removeTownHall(Integer id) throws DeleteException {
        try{
            LOGGER.info("TownHallEJB: Removing a town hall.");
            em.remove(em.merge(id));
            LOGGER.info("TownHallEJB: Town hall removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "TownHallEJB: Exception removing the town hall.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param townhall
     * @return
     * @throws ReadException 
     */
    @Override
    public TownHallBean findTownHallbyId(Integer id) throws ReadException {
        TownHallBean townHall = null;
        try{
            LOGGER.info("TownHallEJB: Finding a town hall by id.");
            townHall = em.find(TownHallBean.class, id);
            LOGGER.info("TownHallEJB: Town hall found by id.");
            return townHall;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallEJB: Exception finding the town hall by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<TownHallBean> findAllTownHalls() throws ReadException {
        List<TownHallBean> townhalls = null;
        try{
            LOGGER.info("TownHallEJB: Finding all the town halls.");
            townhalls = em.createNamedQuery("finsAllTownHalls").getResultList();
            LOGGER.info("TownHallEJB: Town halls found.");
            return townhalls;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallEJB: Exception finding the town halls.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}
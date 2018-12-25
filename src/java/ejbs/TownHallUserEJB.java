/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TownHallUserBean;
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
public class TownHallUserEJB implements TownHallUserLocal{

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
    public TownHallUserBean createTownHallUser(TownHallUserBean townhalluser) throws CreateException {
        try{
            LOGGER.info("TownHallUserEJB: Adding a town hall user.");
            em.persist(townhalluser);
            LOGGER.info("TownHallUserEJB: Town hall user added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "TownHallUserEJB: Exception adding the town hall user.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return townhalluser;
    }

    /**
     * 
     * @param townhall
     * @throws UpdateException 
     */
    @Override
    public void editTownHallUser(TownHallUserBean townhalluser) throws UpdateException {
        try{
            LOGGER.info("TownHallUserEJB: Editting a town hall user.");
            em.merge(townhalluser);
            em.flush();
            LOGGER.info("TownHallUserEJB: Town hall user updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "TownHallUserEJB: Exception updating the town hall user.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param townhall
     * @throws DeleteException 
     */
    @Override
    public void removeTownHallUser(TownHallUserBean townhalluser) throws DeleteException {
        try{
            LOGGER.info("TownHallUserEJB: Removing a town hall user.");
            em.remove(em.merge(townhalluser));
            LOGGER.info("TownHallUserEJB: Town hall user removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "TownHallUserEJB: Exception removing the town hall user.", e.getMessage());
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
    public TownHallUserBean findTownHallUserbyId(TownHallUserBean townhalluser) throws ReadException {
        TownHallUserBean townHallUser = null;
        try{
            LOGGER.info("TownHallUserEJB: Finding a town hall user by id.");
            townHallUser = em.find(TownHallUserBean.class, townhalluser.getIdPerson());
            LOGGER.info("TownHallUserEJB: Town hall user found by id.");
            return townHallUser;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallUserEJB: Exception finding the town hall user by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<TownHallUserBean> findAllTownHallUsers() throws ReadException {
        List<TownHallUserBean> townhallusers = null;
        try{
            LOGGER.info("TownHallUserEJB: Finding all the town hall users.");
            townhallusers = em.createNamedQuery("finsAllTownHallUsers").getResultList();
            LOGGER.info("TownHallUserEJB: Town hall users found.");
            return townhallusers;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallUserEJB: Exception finding the town hall users.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}

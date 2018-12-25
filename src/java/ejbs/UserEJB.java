/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TownHallUserBean;
import entities.UserBean;
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
public class UserEJB implements UserLocal{
    
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
     * @param user
     * @return
     * @throws CreateException 
     */
    @Override
    public UserBean createUser(UserBean user) throws CreateException {
        try{
            LOGGER.info("UserEJB: Adding a user.");
            em.persist(user);
            LOGGER.info("UserEJB: User added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception adding the user.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return user;
    }
    
    /**
     * 
     * @param user
     * @throws UpdateException 
     */
    @Override
    public void editUser(UserBean user) throws UpdateException {
        try{
            LOGGER.info("UserEJB: Editting a user.");
            em.merge(user);
            em.flush();
            LOGGER.info("UserEJB: User updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception updating the user.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param user
     * @throws DeleteException 
     */
    @Override
    public void removeUser(UserBean user) throws DeleteException {
        try{
            LOGGER.info("UserEJB: Removing a user.");
            em.remove(em.merge(user));
            LOGGER.info("UserEJB: User removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "UserEJB: Exception removing the user.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * 
     * @param user
     * @return
     * @throws ReadException 
     */
    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        UserBean us = null;
        try{
            LOGGER.info("UserEJB: Finding a user by id.");
            us = em.find(UserBean.class, user.getIdPerson());
            LOGGER.info("UserEJB: User found by id.");
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserEJB: Exception finding the user by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        List<UserBean> users = null;
        try{
            LOGGER.info("UserEJB: Finding all the users.");
            users = em.createNamedQuery("finsAllUsers").getResultList();
            LOGGER.info("TownHallUserEJB: Users found.");
            return users;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserEJB: Exception finding the users.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
}

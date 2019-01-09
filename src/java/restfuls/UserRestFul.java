/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.UserLocal;
import entities.UserBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jon Gonzalez
 */
@Stateless
@Path("entities.user")
public class UserRestFul {

    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private UserLocal userejb;

    
    /**
     * 
     * @param user 
     */
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_XML})
    public void create(UserBean user) {
        try {
            LOGGER.info("UserRestFul: Adding a user.");
            userejb.createUser(user);
            LOGGER.info("UserRestFul: User added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception adding the user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param user 
     */
    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(UserBean user) {
        try {
            LOGGER.info("UserRestFul: Editting a user.");
            userejb.editUser(user);
            LOGGER.info("UserRestFul: User edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception editting the user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param user 
     */
    @DELETE
    @Path("/rmv/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void remove(UserBean user) {
        try {
            LOGGER.info("UserRestFul: Removing a user.");
            userejb.removeUser(user);
            LOGGER.info("UserRestFul: User removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception removing the user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param user
     * @return 
     */
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public UserBean find(UserBean user) {
        UserBean us = null;
        try {
            LOGGER.info("UserRestFul: Finding a user by id.");
            us = userejb.findUserbyId(user);
            LOGGER.info("UserRestFul: User found by id.");
            return us;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding the user by id.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @return 
     */
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_XML})
    public List<UserBean> findAll() {
        List<UserBean> users = null;
        try {
            LOGGER.info("UserRestFul: Finding all users.");
            users = userejb.findAllUsers();
            LOGGER.info("UserRestFul: All users found.");
            return users;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding all the users.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @return 
     */
    @GET
    @Path("/find/{login}")
    @Produces({MediaType.APPLICATION_XML})
    public UserBean findUserbyLogin(UserBean user) {
        try {
            LOGGER.info("UserRestFul: Finding user by login.");
            user = userejb.findUserbyLogin(user);
            LOGGER.info("UserRestFul: User found by login.");
            return user;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding the user by login.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    @GET
    @Path("/chgpass/{Login}")
    @Produces({MediaType.APPLICATION_XML})
    public void findUserToChangePassword(UserBean user){
        try{
            LOGGER.info("UserRestFul: Finding user by login to change the password.");
            userejb.findUserToChangePassword(user);
            LOGGER.info("UserRestFul: User found.");
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding the user by login.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

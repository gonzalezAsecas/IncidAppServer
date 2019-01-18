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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jon Gonzalez
 */
@Path("user")
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
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("UserRestFul: Removing a user.");
            userejb.removeUser(userejb.findUserbyId(id));
            LOGGER.info("UserRestFul: User removed.");
        } catch (DeleteException | ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception removing the user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public UserBean find(@PathParam("id") Integer id) {
        UserBean us = null;
        try {
            LOGGER.info("UserRestFul: Finding a user by id.");
            us = userejb.findUserbyId(id);
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
     * @param login
     * @param password
     * @return 
     */
    @GET
    @Path("{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public UserBean findUserbyLogin(@PathParam("login")String login, @PathParam("password")String password) {
        UserBean user= new UserBean();
        user.setLogin(login);
        user.setPassword(password);
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
    @Path("passwordChange/{login}")
    @Produces({MediaType.APPLICATION_XML})
    public UserBean findUserToChangePassword(@PathParam("login")String login){
        UserBean us= new UserBean();
        try{
            LOGGER.info("UserRestFul: Finding user by login to change the password.");
            us = userejb.findUserToChangePassword(login);
            LOGGER.info("UserRestFul: User found.");
            return us;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding the user by login.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

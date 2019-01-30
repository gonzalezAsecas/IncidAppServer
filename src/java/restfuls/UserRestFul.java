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
     * The server logger
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * The user´s ejb injected
     */
    @EJB
    private UserLocal userejb;

    
    /**
     * The method for create an user
     * @param user the user is going to be created
     */
    /*@POST
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
    }*/
    
    /**
     * The method for modify an user
     * @param user the user is going to be modified 
     * @param pass 
     */
    /*@PUT
    @Path("{pass}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(UserBean user, @PathParam("pass") Boolean pass) {
        try {
            LOGGER.info("UserRestFul: Editting a user.");
            userejb.editUser(user, pass);
            LOGGER.info("UserRestFul: User edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception editting the user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }*/
    
    /**
     * The method for remove an user
     * @param id the id of the user is going to be deleted
     */
    /*@DELETE
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
    }*/
    
    /**
     * the method for find an user by id
     * @param id the id of the user
     * @return the user if it is found
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
     * The method for find all the users
     * @return all the users in the database
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
     * The method for find an user by it login
     * @param login the login of the user
     * @param password the password of the user encrypted
     * @return the user found
     */
    /*@GET
    @Path("{login}/{password}")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public UserBean findUserbyLogin(@PathParam("login") String login, @PathParam("password") String password) {
        UserBean user = new UserBean();
        try {
            LOGGER.info("UserRestFul: Finding user by login.");
            user = userejb.findUserbyLogin(login, password);
            LOGGER.info("UserRestFul: User found by login.");
            return user;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserRestFul: Exception finding the user by login.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }*/
    
    /**
     * The method for change user's password
     * @param login the login of the user
     * @return the user found
     */
    /*@GET
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
    }*/
}

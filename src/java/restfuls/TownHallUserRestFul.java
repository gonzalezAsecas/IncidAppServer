/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.TownHallUserLocal;
import entities.TownHallUserBean;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jon Gonzalez
 */
@Path("entities.townhalluserbean")
public class TownHallUserRestFul{

    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private TownHallUserLocal townhalluserejb;

    
    /**
     * 
     * @param townhalluser 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(TownHallUserBean townhalluser) {
        try {
            LOGGER.info("TownHallUserRestFul: Adding a town hall user.");
            townhalluserejb.createTownHallUser(townhalluser);
            LOGGER.info("TownHallUserRestFul: Town hall user added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "TownHallUserRestFul: Exception adding the town hall user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhalluser 
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(TownHallUserBean townhalluser) {
        try {
            LOGGER.info("TownHallUserRestFul: Editting a town hall user.");
            townhalluserejb.editTownHallUser(townhalluser);
            LOGGER.info("TownHallUserRestFul: Town hall user edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "TownHallUserRestFul: Exception editting the town hall user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhalluser 
     */
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void remove(TownHallUserBean townhalluser) {
        try {
            LOGGER.info("TownHallUserRestFul: Removing a town hall user.");
            townhalluserejb.removeTownHallUser(townhalluser);
            LOGGER.info("TownHallUserRestFul: Town hall user removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "TownHallUserRestFul: Exception removing the town hall user.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhalluser
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public TownHallUserBean find(TownHallUserBean townhalluser) {
        TownHallUserBean thu = null;
        try {
            LOGGER.info("TownHallUserRestFul: Finding a town hall user by id.");
            thu = townhalluserejb.findTownHallUserbyId(townhalluser);
            LOGGER.info("TownHallUserRestFul: Town hall user found by id.");
            return thu;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TownHallUserRestFul: Exception finding the town hall user by id.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<TownHallUserBean> findAll() {
        List<TownHallUserBean> townhallusers = null;
        try {
            LOGGER.info("TownHallUserRestFul: Finding all town hall users.");
            townhallusers = townhalluserejb.findAllTownHallUsers();
            LOGGER.info("TownHallUserRestFul: All town hall users found.");
            return townhallusers;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TownHallUserRestFul: Exception finding all the town hall users.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

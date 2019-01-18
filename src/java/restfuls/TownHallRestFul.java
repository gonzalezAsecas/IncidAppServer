/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.TownHallLocal;
import entities.TownHallBean;
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
@Path("townhall")
public class TownHallRestFul{

    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private TownHallLocal townhallejb;

    
    /**
     * 
     * @param townhall 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(TownHallBean townhall) {
        try {
            LOGGER.info("TownHallRestFul: Adding a town hall.");
            townhallejb.createTownHall(townhall);
            LOGGER.info("TownHallRestFul: Town hall added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "TownHallRestFul: Exception adding the town hall.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhall 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(TownHallBean townhall) {
        try {
            LOGGER.info("TownHallRestFul: Editting a town hall.");
            townhallejb.editTownHall(townhall);
            LOGGER.info("TownHallRestFul: Town hall edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "TownHallRestFul: Exception editting the town hall.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhall 
     */
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("TownHallRestFul: Removing a town hall.");
            townhallejb.removeTownHall(id);
            LOGGER.info("TownHallRestFul: Town hall removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "TownHallRestFul: Exception removing the town hall.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param townhall
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public TownHallBean find(@PathParam("id") Integer id) {
        TownHallBean th = null;
        try {
            LOGGER.info("TownHallRestFul: Finding a town hall by id.");
            th = townhallejb.findTownHallbyId(id);
            LOGGER.info("TownHallRestFul: Town hall found by id.");
            return th;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TownHallRestFul: Exception finding the town hall by id.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    /**
     * 
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<TownHallBean> findAll() {
        List<TownHallBean> townhalls = null;
        try {
            LOGGER.info("TownHallRestFul: Finding all town halls.");
            townhalls = townhallejb.findAllTownHalls();
            LOGGER.info("TownHallRestFul: All town halls found.");
            return townhalls;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TownHallRestFul: Exception finding all the town halls.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.LocationLocal;
import entities.LocationBean;
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
@Path("entities.locationbean")
public class LocationRestFul{

    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private LocationLocal locationejb;

    
    /**
     * 
     * @param location 
     */
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_XML})
    public void create(LocationBean location) {
        try {
            LOGGER.info("LocationRestFul: Adding a location.");
            locationejb.createLocation(location);
            LOGGER.info("LocationRestFul: Location added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "LocationRestFul: Exception adding the location.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param location 
     */
    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(LocationBean location) {
        try {
            LOGGER.info("LocationRestFul: Editting a location.");
            locationejb.editLocation(location);
            LOGGER.info("LocationRestFul: Location edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "LocationRestFul: Exception editting the location.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param location 
     */
    @DELETE
    @Path("/rmv/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void remove(LocationBean location) {
        try {
            LOGGER.info("LocationRestFul: Removing a location.");
            locationejb.removeLocation(location);
            LOGGER.info("LocationRestFul: Location removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "LocationRestFul: Exception removing the location.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param location
     * @return 
     */
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public LocationBean find(LocationBean location) {
        LocationBean loc = null;
        try {
            LOGGER.info("LocationRestFul: Finding a location by id.");
            loc = locationejb.findLocationbyId(location);
            LOGGER.info("LocationRestFul: Location found by id.");
            return loc;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LocationRestFul: Exception finding the location by id.", ex.getMessage());
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
    public List<LocationBean> findAll() {
        List<LocationBean> locations = null;
        try {
            LOGGER.info("LocationRestFul: Finding all locations.");
            locations = locationejb.findAllLocations();
            LOGGER.info("LocationRestFul: All locations found.");
            return locations;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LocationRestFul: Exception finding all the locations.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

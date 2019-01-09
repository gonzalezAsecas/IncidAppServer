/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.IncidentLocal;
import entities.IncidentBean;
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
@Path("entities.incidentbean")
public class IncidentRestFul{
    
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private IncidentLocal incidentejb;
    
    /**
     * 
     * @param incident 
     */
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_XML})
    public void create(IncidentBean incident) {
        try {
            LOGGER.info("IncidentRestFul: Adding an incident.");
            incidentejb.createIncident(incident);
            LOGGER.info("IncidentRestFul: Incident added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception adding the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param incident 
     */
    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(IncidentBean incident) {
        try {
            LOGGER.info("IncidentRestFul: Editting an incident.");
            incidentejb.editIncident(incident);
            LOGGER.info("IncidentRestFul: Incident edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception editting the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param incident 
     */
    @DELETE
    @Path("/rmv/{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void remove(IncidentBean incident) {
        try {
            LOGGER.info("IncidentRestFul: Removing an incident.");
            incidentejb.removeIncident(incident);
            LOGGER.info("IncidentRestFul: Incident removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception removing the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param incident
     * @return 
     */
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public IncidentBean find(IncidentBean incident) {
        IncidentBean incid = null;
        try {
            LOGGER.info("IncidentRestFul: Finding an incident by id.");
            incid = incidentejb.findIncidentbyId(incident);
            LOGGER.info("IncidentRestFul: Incident found by id.");
            return incid;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception finding the incident by id.", ex.getMessage());
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
    public List<IncidentBean> findAll(IncidentBean incident) {
        List<IncidentBean> incidents = null;
        try {
            LOGGER.info("IncidentRestFul: Finding the incidents by filter.");
            incidents = incidentejb.findIncidentsbyFilter(incident);
            LOGGER.info("IncidentRestFul: The filtred incidents found.");
            return incidents;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception finding the incidents by filter.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

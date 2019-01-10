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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Gorka Redondo
 */
@Path("incident")
public class IncidentRestFul {

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
     * @param entity 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(IncidentBean incident) {
        try {
            LOGGER.info("IncidentRestFul: Adding a incident.");
            incidentejb.createIncident(incident);
            LOGGER.info("IncidentRestFul: incident added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception adding the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, IncidentBean incident) {
        try {
            LOGGER.info("IncidentRestFul: Editting a incident.");
            incidentejb.editIncident(incident);
            LOGGER.info("IncidentRestFul: incident edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception editting the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("IncidentRestFul: Removing a incident.");
            incidentejb.removeIncident(incidentejb.findIncidentbyId(id));
            LOGGER.info("IncidentRestFul: incident removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception removing the incident.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (ReadException ex) {
            Logger.getLogger(IncidentRestFul.class.getName()).log(Level.SEVERE, null, ex);
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
    public IncidentBean find(@PathParam("id") Integer id) {
        IncidentBean inc = null;
        try {
            LOGGER.info("IncidentRestFul: Finding a incident by id.");
            inc = incidentejb.findIncidentbyId(id);
            LOGGER.info("IncidentRestFul: incident found by id.");
            return inc;
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
    @Produces({MediaType.APPLICATION_XML})
    public List<IncidentBean> findAll() {
        List<IncidentBean> incidents = null;
        try {
            LOGGER.info("IncidentRestFul: Finding all incidents.");
            incidents = incidentejb.findAllIncidents();
            LOGGER.info("IncidentRestFul: All incidents found.");
            return incidents;
        } catch (ReadException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "IncidentRestFul: Exception finding all the incidents.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
}

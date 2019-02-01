/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.TypeLocal;
import entities.TypeBean;
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
@Path("type")
public class TypeRestFul {
    
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @EJB
    private TypeLocal typeejb;
    
    /**
     * 
     * @param type 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(TypeBean type) {
        try {
            LOGGER.info("TypeRestFul: Adding a type.");
            typeejb.createType(type);
            LOGGER.info("TypeRestFul: type added.");
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception adding the type.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * 
     * @param id
     * @param type 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, TypeBean type) {
        try {
            LOGGER.info("TypeRestFul: Editting a type.");
            typeejb.editType(type);
            LOGGER.info("TypeRestFul: type edited.");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception editting the type.", ex.getMessage());
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
            LOGGER.info("TypeRestFul: Removing a type.");
            typeejb.removeType(typeejb.findTypeById(id));
            LOGGER.info("TypeRestFul: type removed.");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception removing the type.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (ReadException ex) {
            Logger.getLogger(IncidentRestFul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param id
     * @return type
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public TypeBean find(@PathParam("id") Integer id) {
        TypeBean type = null;
        try {
            LOGGER.info("TypeRestFul: Finding a type by id.");
            type = typeejb.findTypeById(id);
            LOGGER.info("TypeRestFul: type found by id.");
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception finding the type by id.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return type;
    }
    
    /**
     * 
     * @return types
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<TypeBean> findAll() {
        List<TypeBean> types = null;
        try {
            LOGGER.info("TypeRestFul: Finding all types.");
            types = typeejb.findAllTypes();
            LOGGER.info("TypeRestFul: All types found.");
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception finding all the types.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return types;
    }
}

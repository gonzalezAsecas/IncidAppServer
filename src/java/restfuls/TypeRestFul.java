/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfuls;

import ejbs.TypeLocal;
import entities.TypeBean;
import exceptions.ReadException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Gorka Redondo
 */
@Path("type")
public class TypeRestFul{

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
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<TypeBean> findAll() {
        List<TypeBean> types = null;
        try {
            LOGGER.info("TypeRestFul: Finding all types.");
            types = typeejb.findAllTypes();
            LOGGER.info("TypeRestFul: All types found.");
            return types;
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TypeRestFul: Exception finding all the types.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}

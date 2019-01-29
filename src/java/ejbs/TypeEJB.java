/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TypeBean;
import exceptions.ReadException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gorka Redondo
 */
@Stateless
public class TypeEJB implements TypeLocal{
    
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * 
     * @return types
     * @throws ReadException 
     */
    @Override
    public List<TypeBean> findAllTypes() throws ReadException {
        List<TypeBean> types = null;
        try{
            LOGGER.info("TypeEJB: Finding all the types.");
            types = em.createNamedQuery("findAllTypes").getResultList();
            LOGGER.info("TypeEJB: Types found.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TypeEJB: Exception finding the types.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return types;
    }
    
}

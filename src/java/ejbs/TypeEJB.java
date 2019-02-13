/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TypeBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
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
     * @param type
     * @throws CreateException 
     */
    @Override
    public void createType(TypeBean type) throws CreateException {
        try{
            LOGGER.info("TypeEJB: Adding a type.");
            em.persist(type);
            LOGGER.info("TypeEJB: type added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "TypeEJB: Exception adding the type.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param type
     * @throws UpdateException 
     */
    @Override
    public void editType(TypeBean type) throws UpdateException {
        try{
            LOGGER.info("TypeEJB: Editting a type.");
            em.merge(type);
            em.flush();
            LOGGER.info("TypeEJB: type updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "TypeEJB: Exception updating the type.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * 
     * @param type
     * @throws DeleteException 
     */
    @Override
    public void removeType(TypeBean type) throws DeleteException {
        try{
            LOGGER.info("TypeEJB: Removing a type.");
            em.remove(em.merge(type));
            LOGGER.info("TypeEJB: type removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "TypeEJB: Exception removing the type.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * 
     * @param id
     * @return incident
     * @throws ReadException 
     */
    @Override
    public TypeBean findTypeById(Integer id) throws ReadException {
        TypeBean type = null;
        try{
            LOGGER.info("TypeEJB: Finding a type by id.");
            type = em.find(TypeBean.class, id);
            LOGGER.info("TypeEJB: type found by id.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TypeEJB: Exception finding the type by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return type;
    }
    
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

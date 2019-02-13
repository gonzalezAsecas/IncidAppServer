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

/**
 *
 * @author Gorka Redondo
 */
public interface TypeLocal {
    public void createType(TypeBean type) throws CreateException;

    public void editType(TypeBean type) throws UpdateException;

    public void removeType(TypeBean type) throws DeleteException;

    public TypeBean findTypeById(Integer id) throws ReadException;

    public List<TypeBean> findAllTypes() throws ReadException;
}

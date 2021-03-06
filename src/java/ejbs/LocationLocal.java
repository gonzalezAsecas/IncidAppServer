/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.LocationBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author Gorka Redondo
 */
public interface LocationLocal {
    public void createLocation(LocationBean location) throws CreateException;

    public void editLocation(LocationBean location) throws UpdateException;

    public void removeLocation(LocationBean location) throws DeleteException;

    public LocationBean findLocationById(Integer id) throws ReadException;

    public List<LocationBean> findAllLocations() throws ReadException;
}

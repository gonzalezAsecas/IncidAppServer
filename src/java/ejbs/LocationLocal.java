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
 * @author Jon Gonzalez
 */
public interface LocationLocal {
    public LocationBean createLocation(LocationBean location) throws CreateException;

    public void editLocation(LocationBean location) throws UpdateException;

    public void removeLocation(Integer id) throws DeleteException;

    public LocationBean findLocationbyId(Integer id) throws ReadException;

    public List<LocationBean> findAllLocations() throws ReadException;
}

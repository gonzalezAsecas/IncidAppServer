/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TownHallBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jon Gonzalez
 */
@Local
public interface TownHallLocal {
    public void createTownHall(TownHallBean townhall) throws CreateException;

    public void editTownHall(TownHallBean townhall) throws UpdateException;

    public void removeTownHall(TownHallBean townhall) throws DeleteException;

    public TownHallBean findTownHallbyId(Integer id) throws ReadException;
    
    public TownHallBean findTownHallbyName(String name) throws ReadException;

    public List<TownHallBean> findAllTownHalls() throws ReadException;
}

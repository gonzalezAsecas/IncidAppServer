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

/**
 *
 * @author Jon Gonzalez
 */
public interface TownHallLocal {
    public TownHallBean createTownHall(TownHallBean townhall) throws CreateException;

    public void editTownHall(TownHallBean townhall) throws UpdateException;

    public void removeTownHall(Integer id) throws DeleteException;

    public TownHallBean findTownHallbyId(Integer id) throws ReadException;

    public List<TownHallBean> findAllTownHalls() throws ReadException;
}

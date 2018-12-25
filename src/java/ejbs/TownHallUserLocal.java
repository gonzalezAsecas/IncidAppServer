/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TownHallUserBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author Jon Gonzalez
 */
public interface TownHallUserLocal {
    public TownHallUserBean createTownHallUser(TownHallUserBean townhalluser) throws CreateException;

    public void editTownHallUser(TownHallUserBean townhalluser) throws UpdateException;

    public void removeTownHallUser(TownHallUserBean townhalluser) throws DeleteException;

    public TownHallUserBean findTownHallUserbyId(TownHallUserBean townhalluser) throws ReadException;

    public List<TownHallUserBean> findAllTownHallUsers() throws ReadException;
}

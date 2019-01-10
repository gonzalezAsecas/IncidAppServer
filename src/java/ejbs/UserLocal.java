/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.UserBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author Jon Gonzalez
 */
public interface UserLocal {
    public UserBean createUser(UserBean user) throws CreateException;

    public void editUser(UserBean user) throws UpdateException;

    public void removeUser(UserBean user) throws DeleteException;

    public UserBean findUserbyId(Integer id) throws ReadException;

    public List<UserBean> findAllUsers() throws ReadException;
    
    public UserBean findUserbyLogin(UserBean user) throws ReadException;
    
    public void findUserToChangePassword(String login) throws ReadException;
}

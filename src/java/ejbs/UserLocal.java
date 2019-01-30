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
import javax.ejb.Local;

/**
 *
 * @author Jon Gonzalez
 */
@Local
public interface UserLocal {
    
    /**
     * Create an user in the database decrypting and hashing the password
     * @param user the user is going to be save in the database
     * @return the user inself
     * @throws CreateException when there is a problem creating the user
     */
    //public UserBean createUser(UserBean user) throws CreateException;

    /**
     * Merge the state of the current user and synchronized the persistent context
     * @param user the user for modify it
     * @throws UpdateException when there is a problem modifying the user
     */
    //public void editUser(UserBean user, Boolean pass) throws UpdateException;
    
    /**
     * Remove the user from the database
     * @param user the user is going to be removed
     * @throws DeleteException if there are any problem deleting the user
     */
    //public void removeUser(UserBean user) throws DeleteException;
    
    /**
     * Find user by id
     * @param id the id of the user
     * @return the user found
     * @throws ReadException if there are any problem finding the user
     */
    public UserBean findUserbyId(Integer id) throws ReadException;
    
    /**
     * find all the users
     * @return a list of users
     * @throws ReadException if there are any problem finding all the users
     */
    public List<UserBean> findAllUsers() throws ReadException;
    
    /**
     * find users by login and verify that the password are the same
     * @param login the login of the user
     * @param password the password of the user
     * @return the user found
     * @throws ReadException if there are any problem finding the user
     */
    //public UserBean findUserbyLogin(String login, String password) throws ReadException;
    
    /**
     * find user to change password, create a neew and random password, send 
     * it to her email and save the new password
     * @param login of the user is asking the password change
     * @return the user that have change the password
     * @throws ReadException if there are any problem changing the password
     */
    //public UserBean findUserToChangePassword(String login) throws ReadException;
}

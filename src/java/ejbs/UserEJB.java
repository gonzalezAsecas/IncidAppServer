/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Privilege;
import entities.UserBean;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jon Gonzalez
 */
@Stateless
public class UserEJB implements UserLocal{
    
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
     * @param user
     * @return
     * @throws CreateException 
     */
    @Override
    public UserBean createUser(UserBean user) throws CreateException {
        try{
            user.setPassword(hashPassword(decryptPassword(user.getPassword())));
            LOGGER.info("UserEJB: Adding a user.");
            em.persist(user);
            LOGGER.info("UserEJB: User added.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception adding the user.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
        return user;
    }
    
    /**
     * 
     * @param user
     * @throws UpdateException 
     */
    @Override
    public void editUser(UserBean user) throws UpdateException {
        try{
            LOGGER.info("UserEJB: Editting a user.");
            em.merge(user);
            em.flush();
            LOGGER.info("UserEJB: User updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception updating the user.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param user
     * @throws DeleteException 
     */
    @Override
    public void removeUser(UserBean user) throws DeleteException {
        try{
            LOGGER.info("UserEJB: Removing a user.");
            em.remove(em.merge(user));
            LOGGER.info("UserEJB: User removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "UserEJB: Exception removing the user.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws ReadException 
     */
    @Override
    public UserBean findUserbyId(Integer id) throws ReadException {
        UserBean us = null;
        try{
            LOGGER.info("UserEJB: Finding a user by id.");
            us = em.find(UserBean.class, id);
            LOGGER.info("UserEJB: User found by id.");
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserEJB: Exception finding the user by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        List<UserBean> users = null;
        try{
            LOGGER.info("UserEJB: Finding all the users.");
            users = em.createNamedQuery("findAllUsers").getResultList();
            LOGGER.info("UserEJB: Users found.");
            return users;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception finding the users.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<UserBean> findAllTHUsers() throws ReadException {
        List<UserBean> users = null;
        try{
            LOGGER.info("UserEJB: Finding all the town hall users.");
            users = em.createNamedQuery("findAllTHUsers")
                    .setParameter("privilege", Privilege.TOWNHALLUSER).getResultList();
            LOGGER.info("UserEJB: Town hall users found.");
            return users;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception finding the town hall users.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws ReadException 
     */
    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException{
        UserBean us;
        String cause = "login";
        try{
            LOGGER.info("UserEJB: Finding the user by login.");
            us = (UserBean) em.createNamedQuery("findUserbyLogin")
                    .setParameter("login", user.getLogin()).getSingleResult();
            LOGGER.info("UserEJB: User found.");
            if(!us.getPassword().equals(user.getPassword())){
                cause = "password";
                throw new Exception();
            }
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallUserEJB: Exception finding the users.", e.getMessage());
            throw new ReadException(e.getMessage(), cause);
        }
    }
    
    /**
     * 
     * @param login
     * @throws ReadException 
     */
    @Override
    public void findUserToChangePassword(String login) throws ReadException {
        UserBean us = new UserBean();
        try{
            LOGGER.info("UserEJB: Finding user by login for change the password");
            us = (UserBean) em.createNamedQuery("findUserbyLogin").setParameter("login", login).getSingleResult();
            LOGGER.info("UserEJB: User found");
            if(us != null){
                //make new password
                
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TownHallUserEJB: Exception finding the users.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * Decrypt the password with the rsa algorithm
     * @param password the password encrypted
     * @return the password decrypted
     */
    private String decryptPassword(String password) throws Exception{
        FileInputStream fis;
        byte[] passwordBytes;
        byte[] key;
        KeyFactory keyFactory;
        PrivateKey privateKey;
        Cipher cipher;
        try{
            //get the lenght of the password in bytes
            passwordBytes = new byte[password.getBytes().length];
            //save the password converted in bytes
            passwordBytes = password.getBytes();
            LOGGER.info("UserEJB: Decrypting the password.");
            //Open the stream for read the private key
            fis= new FileInputStream("private.key");
            //set the size of the byte array regard to the key
            key = new byte[fis.available()];
            //read the file
            fis.read(key);
            //close  the stream
            fis.close();
            //instance the key factory with the rsa algorithm
            keyFactory = KeyFactory.getInstance("RSA");
            //generate the private key from the byte array with the PKCS8 specification
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(key));
            //instace the cipher object with the rsa algorithm
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //initialize the cipher object in decrypt mode with the private key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //decrypt the text and set the password with the data
            password = new String(cipher.doFinal(passwordBytes));
            LOGGER.info("UserEJB: Decrypted the password");
            return password;
        }   catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Exception decrypting the password", ex);
            throw new Exception(ex);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException
                | NoSuchPaddingException | InvalidKeyException 
                | IllegalBlockSizeException | BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, "Exception decrypting the password", ex);
            throw new Exception(ex);
        }
    }
    
    /**
     *
     * @param password
     * @return
     */
    private String hashPassword(String password) throws Exception {
        MessageDigest md;
        try {
            LOGGER.info("USEREJB: Hashing the password");
            //instance the message digest with sha algorithm
            md = MessageDigest.getInstance("SHA");
            //set the password in bytes for resume
            md.update(password.getBytes());
            //calculate the resume
            password = new String(md.digest());
            LOGGER.info("USEREJB: Hashed the password");
            return password;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Exception hashing the password", ex);
            throw new Exception(ex);
        }
    }
}

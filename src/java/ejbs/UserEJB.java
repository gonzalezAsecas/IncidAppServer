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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;

/**
 * The user ejb for communication with the entity manager.
 * There are method for send emails, decrypt and hash data and do 
 * the user's CRUD
 * @author Jon Gonzalez
 */
@Stateless
public class UserEJB implements UserLocal{
    
    /**
     * The logger of the server application
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    
    /**
     * The entity manager 
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * The resource bundle properties for read the properties file
     */
    /*ResourceBundle properties = ResourceBundle
                .getBundle("properties/Properties");*/
    
    /**
     * Create an user in the database decrypting and hashing the password
     * @param user the user is going to be save in the database
     * @return the user inself
     * @throws CreateException when there is a problem creating the user
     */
    /*@Override
    public UserBean createUser(UserBean user) throws CreateException {
        try{
            //hash and decrypt the password and set into the user
            user.setPassword(hashPassword(decryptPassword(user.getPassword())));
            user.setLastAccess(Date.valueOf(LocalDate.now()));
            user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
            LOGGER.info("UserEJB: Adding a user.");
            //Do persist the user
            em.persist(user);
            LOGGER.info("UserEJB: User added.");
            return user;
        //if any problem have ocurred
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception adding the user.", e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }*/
    
    /**
     * Merge the state of the current user and synchronized the persistent context
     * @param user the user for modify it
     * @throws UpdateException when there is a problem modifying the user
     */
    /*@Override
    public void editUser(UserBean user, Boolean pass) throws UpdateException {
        try{
            LOGGER.info("UserEJB: Editting a user.");
            //merge the state of the user into the persistent context
            em.merge(user);
            //synchronize the persistent context
            em.flush();
            if(pass){
                sendEmail(this.decryptData("email.data"),
                        this.decryptData("pwd.data"),
                        user.getEmail(), "", false);
            }
            LOGGER.info("UserEJB: User updated.");
        //if any problem have ocurred
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception updating the user.", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }*/
    
    /**
     * Remove the user from the database
     * @param user the user is going to be removed
     * @throws DeleteException if there are any problem deleting the user
     */
    /*@Override
    public void removeUser(UserBean user) throws DeleteException {
        try{
            LOGGER.info("UserEJB: Removing a user.");
            //remove the user and merge the state of the user into the persistence context
            em.remove(em.merge(user));
            LOGGER.info("UserEJB: User removed.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, 
                    "UserEJB: Exception removing the user.", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }*/

    /**
     * Find user by id
     * @param id the id of the user
     * @return the user found
     * @throws ReadException if there are any problem finding the user
     */
    @Override
    public UserBean findUserbyId(Integer id) throws ReadException {
        UserBean us = null;
        try{
            LOGGER.info("UserEJB: Finding a user by id.");
            //find the user
            us = em.find(UserBean.class, id);
            LOGGER.info("UserEJB: User found by id.");
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserEJB: Exception finding the user by id.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * find all the users
     * @return a list of users
     * @throws ReadException if there are any problem finding all the users
     */
    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        List<UserBean> users = null;
        try{
            LOGGER.info("UserEJB: Finding all the users.");
            //create the query and get the list of users
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
     * find users by login and verify that the password are the same
     * @param login the login of the user
     * @param password the password of the user
     * @return the user found
     * @throws ReadException if there are any problem finding the user
     */
    /*@Override
    public UserBean findUserbyLogin(String login, String password) throws ReadException{
        UserBean user = new UserBean();
        UserBean us;
        try {
            //set the login
            user.setLogin(login);
            //set the password converting from Hexadecimal string to binary, 
            //decrypting and hashing it
            user.setPassword(this.hashPassword(this.decryptPassword(
                    DatatypeConverter.parseHexBinary(password))));
            LOGGER.info("UserEJB: Finding the user by login.");
            //create he query, set the login as parameter and get the result
            us = (UserBean) em.createNamedQuery("findUserbyLogin")
                    .setParameter("login", user.getLogin()).getSingleResult();
            LOGGER.info("UserEJB: User found.");
            //verify that the paswords are the same
            if(!MessageDigest.isEqual(user.getPassword(), us.getPassword())){
                throw new Exception();
            }
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserEJB: Exception finding the user.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }*/
    
    /**
     * find user to change password, create a neew and random password, send 
     * it to her email and save the new password
     * @param login of the user is asking the password change
     * @return the user that have change the password
     * @throws ReadException if there are any problem changing the password
     */
    /*@Override
    public UserBean findUserToChangePassword(String login) throws ReadException {
        UserBean us = new UserBean();
        SecureRandom random;
        String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
                    "9", "a", "b", "c", "d", "e", "f"};
        String newPassword = "";
        try{
            LOGGER.info("UserEJB: Finding user by login for change the password");
            //find the user by login
            us = (UserBean) em.createNamedQuery("findUserbyLogin")
                    .setParameter("login", login).getSingleResult();
            LOGGER.info("UserEJB: User found");
            //if the user exist
            if(us != null){
                //get instance for the secure random
                random = SecureRandom.getInstance("SHA1PRNG");
                //generate a password of a lenght of 10 characters
                for(int i=0; i<10; i++){
                    //generate a random character from the array
                    newPassword+=symbols[random.nextInt(symbols.length)];
                }
                //send the email
                sendEmail(this.decryptData("email.data"),
                        this.decryptData("pwd.data"),
                        us.getEmail(), newPassword, true);
                //save the password hashing it
                us.setPassword(hashPassword(newPassword.getBytes()));
                //save the modified user
                this.editUser(us, false);
            }
            return us;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "UserEJB: Exception finding the user to change the password.", e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }*/
    
    /**
     * Decrypt the password with the rsa algorithm
     * @param password the password encrypted
     * @return the password decrypted
     */
    /*private byte[] decryptPassword(byte[] password) throws Exception{
        FileInputStream fis;
        byte[] key;
        KeyFactory keyFactory;
        PrivateKey privateKey;
        Cipher cipher;
        try{
            //passwordBytes = DatatypeConverter.parseHexBinary(new String(password));
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
            password = cipher.doFinal(password);
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
    }*/
    
    /**
     * Hash the password for save in the database
     * @param password the password i going to be hashed
     * @return the password hashed
     */
    /*private byte[] hashPassword(byte[] password) throws Exception {
        MessageDigest md;
        try {
            LOGGER.info("UserEJB: Hashing the password");
            //instance the message digest with sha algorithm
            md = MessageDigest.getInstance("SHA");
            //set the password in bytes for resume
            md.update(password);
            //calculate the resume
            password = md.digest();
            LOGGER.info("UserEJB: Hashed the password");
            return password;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Exception hashing the password", ex);
            throw new Exception(ex);
        }
    }*/
    
    /**
     * read and decrypt the data of a file
     * @param path the fil is going to be readed and decrypted
     * @return the data of the file
     * @throws Exception if there are any problems in the decryption of the data
     */
    /*private String decryptData(String path) throws Exception{
        File file;
        FileInputStream fis;
        ObjectInputStream ois;
        String data;
        String pwd= properties.getString("pwd");
        try {
            byte[] salt = "Hello World!!!!!".getBytes();
            KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 6000, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = factory.generateSecret(spec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            file = new File(path);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            IvParameterSpec ivParam = new IvParameterSpec((byte[]) ois.readObject());
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] s = (byte[]) ois.readObject();
            byte[] decodedMessage = cipher.doFinal(s);
            data = new String(decodedMessage);
            ois.close();
            fis.close();
            return data;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IOException | ClassNotFoundException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, "", ex);
            throw new Exception(ex);
        }
    }*/
    
    /**
     * Send a email to the user
     * @param path the email is going to send the email
     * @param password the password of that email
     * @param userEmail the user email
     * @param newPassword the new password of the user
     * @param pass if it is from a modify or password  
     */
    /*private void sendEmail(String path, String password, String userEmail, String newPassword, boolean pass) throws Exception {
        Email email;
        try{
            email = new SimpleEmail();
            //set the hostname
            email.setHostName("smtp.googlemail.com");
            //set the port
            email.setSmtpPort(Integer.parseInt(properties.getString("emailPort")));
            //set the email and the password for authentication
            email.setAuthentication(path, password);
            //set TLS enabled
            email.setStartTLSEnabled(true);
            //set the name of the sent email
            email.setFrom(path);
            //set a subject
            email.setSubject("IncidApp: Your password have changed");
            //set the mesage
            if(pass){
                email.setMsg("Today, "+ LocalDate.now().toString() + ", your password has been changed and now is " + newPassword + ".");
            }else{
                email.setMsg("You have change your password today. " + LocalDate.now().toString());
            }
            //set the user email
            email.addTo(userEmail);
            //send the email
            email.send();
       }catch(EmailException ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new Exception(ex);
        }
    }*/
}

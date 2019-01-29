/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TypeBean;
import exceptions.ReadException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jon Gonzalez
 */
@Local
public interface TypeLocal {
    public List<TypeBean> findAllTypes() throws ReadException;
}

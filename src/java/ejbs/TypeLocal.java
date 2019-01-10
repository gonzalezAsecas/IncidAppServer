/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.TypeBean;
import exceptions.ReadException;
import java.util.List;

/**
 *
 * @author Jon Gonzalez
 */
public interface TypeLocal {
    public List<TypeBean> findAllTypes() throws ReadException;
}

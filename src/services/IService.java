/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public interface IService <T>{
    public void ajouter(T p) throws SQLException ;
    public void supprimer(int id);
    public void modifier(T p);
    public List<T> getAll();
    
}
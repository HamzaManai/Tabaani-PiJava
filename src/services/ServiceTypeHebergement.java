/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import entities.TypeHebergement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class ServiceTypeHebergement implements IService<TypeHebergement> {


        Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(TypeHebergement t) throws SQLException  {
        try {
            String req = "INSERT INTO `type_hebergement` (`nom_type_hbrg`) VALUES ('" + t.getNom_type_hbrg() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Type Hebergement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `type_hebergement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Type Hebergement  deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(TypeHebergement t) {
        try {
            String req = "UPDATE `type_hebergement` SET `nom_type_hbrg` = '" + t.getNom_type_hbrg() + "' WHERE `type_hebergement`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Type Hebergement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<TypeHebergement> getAll() {

        List<TypeHebergement> list = new ArrayList<>();
        try {
            String req = "Select * from type_hebergement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                TypeHebergement t = new TypeHebergement(rs.getInt(1), rs.getString("nom_type_hbrg"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;    }
        public ObservableList<TypeHebergement> TrieNom() {

       ObservableList<TypeHebergement>  list = FXCollections.observableArrayList();
        try {
            String req = "Select * from type_hebergement order by nom_type_hbrg DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                TypeHebergement t = new TypeHebergement(rs.getInt(1), rs.getString("nom_type_hbrg"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;    }
    
}

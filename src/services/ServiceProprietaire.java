 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Proprietaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class ServiceProprietaire implements IService<Proprietaire> {

    Connection cnx = DataSource.getInstance().getCnx();

            
    @Override
    public void ajouter(Proprietaire p) {
        try {
            String req = "INSERT INTO `proprietaire`(`nom_prop`, `prenom_prop`, `email_prop`, `num_tlf_prop` , `img_prop` ) VALUES ('" + p.getNom_prop() + "', '" +
                    p.getPrenom_prop() + "', '" +  p.getEmail_prop() + "', '" + p.getNum_tlf_prop() + "', '" + p.getImg_prop() + "')" ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Proprietaire created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }


    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `proprietaire` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Propeitaire  deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public void modifier(Proprietaire p) {
        try {
            String req = "UPDATE `proprietaire` SET `nom_prop` = '" + p.getNom_prop() +"', `prenom_prop` = '" +    p.getPrenom_prop() + 
                    "', `email_prop` = '" + p.getEmail_prop() + "', `num_tlf_prop` = '" + p.getNum_tlf_prop()  
                    + "' WHERE `proprietaire`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Propreitaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       }

    @Override
    public List getAll() {

        List<Proprietaire> list = new ArrayList<>();
        try {
            String req = "Select * from proprietaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Proprietaire p = new Proprietaire(rs.getInt(1), rs.getInt("num_tlf_prop") ,rs.getString("nom_prop") , rs.getString("prenom_prop") , rs.getString("email_prop") , rs.getString("img_prop")   );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;           
    }
    
    
            public ObservableList<Proprietaire> getAllTriNom() {
      ObservableList<Proprietaire> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from proprietaire order by nom_prop";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Proprietaire p = new Proprietaire(rs.getInt(1), rs.getInt("num_tlf_prop") ,rs.getString("nom_prop") , rs.getString("prenom_prop") , rs.getString("email_prop")   );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        
           public ObservableList<Proprietaire> getAllTriPrenom() {
      ObservableList<Proprietaire> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from proprietaire order by prenom_prop ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Proprietaire p = new Proprietaire(rs.getInt(1), rs.getInt("num_tlf_prop") ,rs.getString("nom_prop") , rs.getString("prenom_prop") , rs.getString("email_prop")   );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

               public ObservableList<Proprietaire> getAllTriEmail() {
      ObservableList<Proprietaire> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from proprietaire order by email_prop ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Proprietaire p = new Proprietaire(rs.getInt(1), rs.getInt("num_tlf_prop") ,rs.getString("nom_prop") , rs.getString("prenom_prop") , rs.getString("email_prop")   );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}

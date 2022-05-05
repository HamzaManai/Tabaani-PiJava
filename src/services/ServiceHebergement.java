/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hebergement;
import entities.Proprietaire;
import entities.TypeHebergement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class ServiceHebergement implements IService<Hebergement> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Hebergement h) {
        try {
            String req;
            req = "INSERT INTO `hebergement`( `nom_hbrg`, `adresse_hbrg`, `nbr_place_hbrg` , `type_hbrg_id`,`img_hbrg` ,`date_hbrg`, `prix_hbrg`) VALUES ('"
                    + h.getNom_hbrg() + "', '" + h.getAdresse_hbrg() + "', '" + h.getNbr_place_hbrg() + "', '" + h.type.getId()
                    + "', '" + h.getImg_hbrg() + "', '" + h.getDate_hbrg() + "', '" + h.getPrix_hbrg() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterHebergementProprietaire(Hebergement h) {
        try {
            String req;
            req = "INSERT INTO `hebergement`( `nom_hbrg`, `adresse_hbrg`, `nbr_place_hbrg` , `proprietaire_id`, `type_hbrg_id`, `date_hbrg`, `prix_hbrg` ,`img_hbrg`) VALUES ('"
                    + h.getNom_hbrg() + "', '" + h.getAdresse_hbrg() + "', '" + h.getNbr_place_hbrg() + "', '" + h.propritaire.getId() + "', '" + h.type.getId()
                    + "', '" + h.getDate_hbrg() + "', '" + h.getPrix_hbrg() + "', '" + h.getImg_hbrg() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterHebergementUtilisateur(Hebergement h) {
        try {
            String req;
            req = "INSERT INTO `hebergement`( `nom_hbrg`, `adresse_hbrg`, `nbr_place_hbrg` , `user_id`, `type_hbrg_id`, `date_hbrg`, `prix_hbrg`) VALUES ('"
                    + h.getNom_hbrg() + "', '" + h.getAdresse_hbrg() + "', '" + h.getNbr_place_hbrg() + "', '" + 1 + "', '" + h.type.getId()
                    + "', '" + h.getDate_hbrg() + "', '" + h.getPrix_hbrg() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement Utilisateur created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req0 = "Delete from reservation_hebergement where hebergement_id ="+ id;
            String req = "DELETE  FROM `hebergement` WHERE id = " + id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req0);
            st.executeUpdate(req);
            System.out.println("hebergement  deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    @Override
    public void modifier(Hebergement p) {
        try {
            String req = "UPDATE `hebergement` SET `nom_hbrg` = '" + p.getNom_hbrg() + "', `adresse_hbrg` = '" + p.getAdresse_hbrg()
                    + "', `nbr_place_hbrg` = '" + p.getNbr_place_hbrg() + "', `type_hbrg_id` = '" + p.type.getId() + "', `proprietaire_id` = '" + p.propritaire.getId()
                    + "', `date_hbrg` = '" + p.getDate_hbrg() + "', `prix_hbrg` = '" + p.getPrix_hbrg() + "', `img_prop` = '" + p.getImg_hbrg() + "' WHERE `hebergement`.`id` = " + p.getId();
            //"', `date_hbrg` = '" + p.getDate_hbrg()+
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier2(Hebergement p) {
        try {
            String req = "UPDATE `hebergement` SET `nom_hbrg` = '" + p.getNom_hbrg() + "', `adresse_hbrg` = '" + p.getAdresse_hbrg()
                    + "', `nbr_place_hbrg` = '" + p.getNbr_place_hbrg() + "', `type_hbrg_id` = '" + p.type.getId()
                    + "', `date_hbrg` = '" + p.getDate_hbrg() + "', `prix_hbrg` = '" + p.getPrix_hbrg() + "', `img_prop` = '" + p.getImg_hbrg() + "' WHERE `hebergement`.`id` = " + p.getId();
            //"', `date_hbrg` = '" + p.getDate_hbrg()+
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ModifierNbrPlace(Hebergement p) {
        try {
            String req = "UPDATE `hebergement` SET `nbr_place_hbrg` = " + p.getNbr_place_hbrg() + " WHERE `hebergement`.`id` = " + p.getId();
            //"', `date_hbrg` = '" + p.getDate_hbrg()+
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement updated Nbr Place !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Hebergement> getAll() {
        /*
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `hebergement`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(),rs.getObject("type_hbrg_id"),rs.getClass(TypeHebergement),rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
         */
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM hebergement h, type_hebergement t,proprietaire p where t.id = h.type_hbrg_id And p.id = h.proprietaire_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Proprietaire p = new Proprietaire(rs.getInt("p.id"), rs.getString("p.nom_prop"), rs.getString("p.prenom_prop"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, p, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Hebergement> list() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        String date =java.time.LocalDate.now().toString();
        try {
            //String req = "SELECT DISTINCT * FROM hebergement h, type_hebergement t, proprietaire p,utilisateur u where t.id = h.type_hbrg_id  ";
            System.out.println(java.time.LocalDate.now().toString());
            String req = "SELECT DISTINCT * FROM hebergement h, type_hebergement t where t.id = h.type_hbrg_id and h.date_hbrg > '2022-04-28' " ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Hebergement> listHbrgUtil() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM hebergement h, type_hebergement t where t.id = h.type_hbrg_id AND h.user_id = " + 1;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<Hebergement> getAllTriPriceAsc() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM hebergement h, type_hebergement t,proprietaire p where t.id = h.type_hbrg_id order by prix_hbrg ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public ObservableList<Hebergement> getAllTriPriceDesc() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM hebergement h, type_hebergement t,proprietaire p where t.id = h.type_hbrg_id order by prix_hbrg DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t,  rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void ReservationHebergement(Hebergement h) {
        try {
            String req, num_rs;
            num_rs = "1" + h.getNom_hbrg() + h.getId();
            req = "INSERT INTO `reservation_hebergement`(`hebergement_id`, `utilisateur_id`, `num_resrv`) VALUES ('"
                    + h.getId() + "', '" + 1 + "', '" + num_rs + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Hebergement> listResvUtil() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
        String date =  java.time.LocalDate.now().toString() ;
        try {
           // String req = "SELECT * FROM hebergement h, type_hebergement t, reservation_hebergement r where t.id = h.type_hbrg_id And r.hebergement_id=h.id AND r.utilisateur_id=" + 1
               //     + " AND h.date_hbrg > " + java.time.LocalDate.now() ; 
           String req = "SELECT * FROM hebergement h, type_hebergement t, reservation_hebergement r where t.id = h.type_hbrg_id And r.hebergement_id=h.id AND r.utilisateur_id=" + 1
     + " AND h.date_hbrg > '2022-04-28'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

        public List<Hebergement> listResvUtilPrec() {
        ObservableList<Hebergement> list = FXCollections.observableArrayList();
                String date =  java.time.LocalDate.now().toString() ;

        try {
            String req = "SELECT * FROM hebergement h, type_hebergement t, reservation_hebergement r where t.id = h.type_hbrg_id And r.hebergement_id=h.id AND r.utilisateur_id=" + 1
                 //  + " and h.date_hbrg < " +  java.time.LocalDate.now().toString() ;
                 +  " AND h.date_hbrg < '2022-04-28'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeHebergement t = new TypeHebergement(rs.getInt("t.id"), rs.getString("t.nom_type_hbrg"));
                Hebergement h = new Hebergement(rs.getInt(1), rs.getInt("nbr_place_hbrg"), rs.getInt("prix_hbrg"), rs.getString("nom_hbrg"), rs.getString("adresse_hbrg"),
                        rs.getDate("date_hbrg").toLocalDate(), t, rs.getString("img_hbrg"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}

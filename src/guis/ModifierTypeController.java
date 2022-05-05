/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.TypeHebergement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceTypeHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ModifierTypeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfType;
    private TypeHebergement t;
    private String imgp;

    public void setA(TypeHebergement a) {
        this.t = a;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNom(String nom) {
        tfType.setText(nom);
    }

    @FXML
    private void modifierTypeHebergement(ActionEvent event) throws IOException {

        if (tfType.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, " champs invalide");
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs invlaide ", ButtonType.OK);
            a.showAndWait();
        } else {
            ServiceTypeHebergement sth = new ServiceTypeHebergement();
            // TypeHebergement t = new TypeHebergement(tfType.getText());
            t.setNom_type_hbrg(tfType.getText());
            sth.modifier(t); // JOptionPane.showMessageDialog(null, ex.getMessage());
            // JOptionPane.showMessageDialog(null, " Type added ");
            Alert b = new Alert(Alert.AlertType.INFORMATION, "Type modifiee ", ButtonType.OK);
            b.showAndWait();
            /*
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListTypeHebergement.fxml"));
                Parent root = loader.load();
                tfNom_type_hbrg.getScene().setRoot(root);
                AfficherTypeController atc = loader.getController();
                atc.setNom(tfNom_type_hbrg.getText());
             */
            Parent root = FXMLLoader.load(getClass().getResource("ListTypeHebergement.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            /*
                Scene scene = new Scene (root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Afficher Type Hebergement");
                primaryStage.show(); */
        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        tfType.setText("");

    }

    @FXML
    private void MenuTypeHebergement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuTypeHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

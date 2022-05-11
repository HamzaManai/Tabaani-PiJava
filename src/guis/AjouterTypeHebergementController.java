/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.TypeHebergement;
import java.awt.HeadlessException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.ServiceTypeHebergement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class AjouterTypeHebergementController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfNom_type_hbrg;
    @FXML
    private Button hebergement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterTypeHebergement(ActionEvent event) throws IOException {

        if (tfNom_type_hbrg.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, " champs invalide");
            /*
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs invlaide ", ButtonType.OK);
            a.showAndWait();
             */
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("ajouter Type Hebergement");
            tray.setMessage("champs invalide");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            try {
                ServiceTypeHebergement sth = new ServiceTypeHebergement();
                TypeHebergement t = new TypeHebergement(tfNom_type_hbrg.getText());
                sth.ajouter(t); // JOptionPane.showMessageDialog(null, ex.getMessage());
                // JOptionPane.showMessageDialog(null, " Type added ");

                //Alert b = new Alert(Alert.AlertType.INFORMATION, "Type added ", ButtonType.OK);
                //   b.showAndWait();
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("ajouter Type Hebergement");
                tray.setMessage("Type added");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
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
            } catch (SQLException ex) {
                Alert c = new Alert(Alert.AlertType.INFORMATION, ex.getMessage(), ButtonType.OK);
                c.showAndWait();

            }
        }

    }

    @FXML
    private void MenuTypeHebergement(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuTypeHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        tfNom_type_hbrg.setText("");
    }

    @FXML
    private void hebergementMenu(MouseEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void hebergementMenu(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

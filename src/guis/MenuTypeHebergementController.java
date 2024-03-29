/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class MenuTypeHebergementController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    private void add(ActionEvent event) throws IOException {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTypeHebergement.fxml"));
        Parent root = loader.load();
        AjouterTypeHebergementController ac = loader.getController();
         */
 
        Parent root = FXMLLoader.load(getClass().getResource("AjouterTypeHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         

       // loadPage("AjouterTypeHebergement");
    }

    @FXML
    private void list(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListTypeHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MenuPrincipale(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.setClip(root);

    }

}

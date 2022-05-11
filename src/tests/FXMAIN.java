/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class FXMAIN extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/MenuBack.fxml"));
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/ListHebergementUtilisateur.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/ListHebergement.fxml"));
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/ListProprietaire.fxml"));
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/DashboardAdmin.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/ListHebergementUtilisateur.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("../guis/AjouterHebergementUtilisateur.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

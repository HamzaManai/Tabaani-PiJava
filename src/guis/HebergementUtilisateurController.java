/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class HebergementUtilisateurController implements Initializable {

    private AnchorPane rootpane;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    @FXML
    private ScrollPane scroll2;
    @FXML
    private GridPane grid2;

    private Image image;
    private MyListener myListener;
    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    ServiceHebergement sh = new ServiceHebergement();
    ObservableList<Hebergement> list = FXCollections.observableArrayList(
            sh.listHbrgUtil()
    );
    ObservableList<Hebergement> list1 = FXCollections.observableArrayList(
            sh.listResvUtil()
    );
    ObservableList<Hebergement> list2 = FXCollections.observableArrayList(
            sh.listResvUtilPrec()
    );

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        int column = 0;
        int row = 1;
        int column1 = 0;
        int row1 = 1;
        int column2 = 0;
        int row2 = 1;

        try {
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Hebergement2.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Hebergement2Controller Hebergement2Controller = fxmlLoader.getController();
                Hebergement2Controller.setData(list.get(i), myListener);


                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < list1.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Hebergement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                HebergementController HebergementController = fxmlLoader.getController();
                HebergementController.setData(list1.get(i), myListener);

                if (column1 == 3) {
                    column1 = 0;
                    row1++;
                }
                System.out.println(java.time.LocalDate.now());
                grid1.add(anchorPane, column1++, row1); //(child,column,row)
                //set grid width
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * ******************************
         */
        try {
            for (int i = 0; i < list2.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Hebergement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                HebergementController HebergementController = fxmlLoader.getController();
                HebergementController.setData(list2.get(i), myListener);

                if (column2 == 3) {
                    column2 = 0;
                    row2++;
                }
                System.out.println(java.time.LocalDate.now());
                grid2.add(anchorPane, column2++, row2); //(child,column,row)
                //set grid width
                grid2.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid2.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid2.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid2.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid2.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addHbrg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterHebergementUtilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listHbrg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListHebergementUtilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

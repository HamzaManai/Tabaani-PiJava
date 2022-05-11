/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entities.TypeHebergement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceTypeHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ListTypeHebergementController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<TypeHebergement, String> nom_type;
    @FXML
    private TableColumn<TypeHebergement, Void> modifier;
    @FXML
    private TableColumn<TypeHebergement, Void> supprimer;
    @FXML
    private TableView<TypeHebergement> ListTypes;

    ServiceTypeHebergement stp = new ServiceTypeHebergement();

    ObservableList<TypeHebergement> list = FXCollections.observableArrayList(
            stp.getAll()
    );
    @FXML
    private Button hebergement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Callback<TableColumn<TypeHebergement, Void>, TableCell<TypeHebergement, Void>> cellfactorydelete = (param) -> {
            final TableCell<TypeHebergement, Void> cell = new TableCell<TypeHebergement, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.setOnAction(event -> {
                            TypeHebergement t = getTableView().getItems().get(getIndex());
                            stp.supprimer(t.getId());
                            list.remove(t);

                        });

                        setGraphic(deletebutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };

        Callback<TableColumn<TypeHebergement, Void>, TableCell<TypeHebergement, Void>> cellfactoryedit = (param) -> {
            final TableCell<TypeHebergement, Void> cell = new TableCell<TypeHebergement, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event -> {
                            TypeHebergement t = getTableView().getItems().get(getIndex());
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierType.fxml"));
                                Parent second = loader.load();

                                ModifierTypeController secondcontroller = loader.<ModifierTypeController>getController();
                                secondcontroller.setNom(t.getNom_type_hbrg());
                                secondcontroller.setA(t);

                                Scene s = new Scene(second);
                                Stage stageedit = new Stage();
                                stageedit.setScene(s);

                                stageedit.show();

                            } catch (IOException ex) {
                                Logger.getLogger(ListTypeHebergementController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        setGraphic(editbutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };

        modifier.setCellFactory(cellfactoryedit);
        supprimer.setCellFactory(cellfactorydelete);
        nom_type.setCellValueFactory(new PropertyValueFactory<TypeHebergement, String>("nom_type_hbrg"));
        ListTypes.setItems(list);
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
    private void add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterTypeHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void trierNom(ActionEvent event) {
        nom_type.setCellValueFactory(new PropertyValueFactory<TypeHebergement, String>("nom_type_hbrg"));
        list = stp.TrieNom();

        ListTypes.setItems(list);
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

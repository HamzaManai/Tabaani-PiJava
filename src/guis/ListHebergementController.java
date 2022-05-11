/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import entities.Proprietaire;
import entities.TypeHebergement;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceHebergement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import services.ServiceProprietaire;
import services.ServiceTypeHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ListHebergementController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Hebergement> ListHebergements;
    @FXML
    private TableColumn<Hebergement, String> nom_hbrg;
    @FXML
    private TableColumn<Hebergement, String> adresse_hbrg;
    @FXML
    private TableColumn<Hebergement, String> type_hbrg;
    @FXML
    private TableColumn<Hebergement, String> num_place_hbrg;
    @FXML
    private TableColumn<Hebergement, String> prix_hbrg;
    @FXML
    private TableColumn<Hebergement, String> prop_hbrg;
    @FXML
    private TableColumn<Hebergement, String> date_hbrg;
    @FXML
    private TableColumn<Hebergement, Void> modifier;
    @FXML
    private TableColumn<Hebergement, Void> supprimer;
    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    ServiceHebergement sh = new ServiceHebergement();

    ObservableList<Hebergement> list = FXCollections.observableArrayList(
            sh.list()
    );
    @FXML
    private TextField tfsearchNom;
    @FXML
    private ComboBox<TypeHebergement> cbSearchType;
    @FXML
    private ComboBox<Proprietaire> cbSearchProp;
    @FXML
    private TableColumn<Hebergement, String> img_hbrg;
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private Button hebergement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> listcb = FXCollections.observableArrayList("Par Nom ↑", "Par Nom ↓", "Par Prix ↑", "Par Prix ↓");
        cbox.setItems(listcb);
        cbox.getSelectionModel().selectFirst();

        ServiceProprietaire sp = new ServiceProprietaire();
        ServiceTypeHebergement st = new ServiceTypeHebergement();

        List<TypeHebergement> LT = st.getAll();
        for (int i = 0; i < LT.size(); i++) {
            //cbType_hbrg.getItems().add(LT.get(i).getNom_type_hbrg());
            cbSearchType.getItems().add(LT.get(i));

        }

        List<Proprietaire> LP = sp.getAll();
        for (int i = 0; i < LP.size(); i++) {
            cbSearchProp.getItems().add(LP.get(i));
        }

        Callback<TableColumn<Hebergement, Void>, TableCell<Hebergement, Void>> cellfactorydelete = (param) -> {
            final TableCell<Hebergement, Void> cell = new TableCell<Hebergement, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.setOnAction(event -> {
                            Hebergement h = getTableView().getItems().get(getIndex());
                            sh.supprimer(h.getId());
                            list.remove(h);

                        });

                        setGraphic(deletebutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };

        Callback<TableColumn<Hebergement, Void>, TableCell<Hebergement, Void>> cellfactoryedit = (param) -> {
            final TableCell<Hebergement, Void> cell = new TableCell<Hebergement, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event -> {
                            Hebergement h = getTableView().getItems().get(getIndex());
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierHebergement.fxml"));
                                Parent second = loader.load();

                                ModifierHebergementController secondcontroller = loader.<ModifierHebergementController>getController();
                                secondcontroller.setH(h);
                                secondcontroller.setTfNom_hbrg(h.getNom_hbrg());
                                secondcontroller.setTfAdresse_hbrg(h.getNom_hbrg());
                                secondcontroller.setDpDate_hbrg(h.getDate_hbrg());
                                secondcontroller.setTfPrix_hbrg(String.valueOf(h.getPrix_hbrg()));
                                secondcontroller.setTfNbr_place_hbrg(String.valueOf(h.getNbr_place_hbrg()));
                                secondcontroller.setCbProprietaire(h.getPropritaire());
                                secondcontroller.setCbType_hbrg(h.getType());
                                secondcontroller.setImg_hbrg(h.getImg_hbrg());

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

        nom_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nom_hbrg"));
        adresse_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("adresse_hbrg"));
        type_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("t.nom_type_hbrg"));
        num_place_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nbr_place_hbrg"));
        prix_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("prix_hbrg"));
        prop_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("p.nom_prop"));
        date_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("date_hbrg"));
        //Image im =);
        ImageView iv = new ImageView(new Image("file:///" + path + new PropertyValueFactory<Hebergement, String>("img_hbrg")));
        iv.setFitHeight(112);
        iv.setFitWidth(200);
        //img_hbrg.setCellValueFactory(iv);
        img_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("img_hbrg"));

        ListHebergements.setItems(list);
    }

    @FXML
    private void MenuHebergement(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<Hebergement> getTableList() {

        ObservableList<Hebergement> ListH = (ObservableList<Hebergement>) sh.list();
        return ListH;
    }

    FilteredList<Hebergement> filter = new FilteredList<>(list, e -> true);
    SortedList<Hebergement> sort = new SortedList<>(filter);

    @FXML
    private void searchNom(KeyEvent event) {
        tfsearchNom.setOnKeyReleased(e -> {

            tfsearchNom.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(t.getNom_hbrg()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            sort.comparatorProperty().bind(ListHebergements.comparatorProperty());
            ListHebergements.setItems(sort);
        });
    }

    @FXML
    private void searchType(MouseDragEvent event) {

        cbSearchType.setOnMouseClicked(e -> {

            cbSearchType.valueProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null) {
                        return true;
                    }
                    // String lowerCaseFilter = newValue.toLowerCase();
                    if (cbSearchType.getValue() == t.getType()) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            sort.comparatorProperty().bind(ListHebergements.comparatorProperty());
            ListHebergements.setItems(sort);
        });
    }

    @FXML
    private void TrierHbrg(ActionEvent event) {
        String s = cbox.getSelectionModel().getSelectedItem();
        list = (ObservableList<Hebergement>) sh.list();
        ListHebergements.setItems(list);
        if (s.equals("Par Nom ↓")) {
            SortedList<Hebergement> sortedList = new SortedList(ListHebergements.getItems().sorted());
            ListHebergements.setItems(sortedList);
        }
        if (s.equals("Par Nom ↑")) {
            SortedList<Hebergement> sortedList = new SortedList(ListHebergements.getItems().sorted());
            List<Hebergement> listpp = new LinkedList<>(Arrays.asList());
            int i = sortedList.size();
            while (i > 0) {
                listpp.add(sortedList.get(i - 1));
                i--;
            }
            ListHebergements.setItems(FXCollections.observableList(listpp));
        }
        if (s.equals("Par Prix ↓")) {
            list.clear();
            list = null;
            ListHebergements.setItems(list);
            nom_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nom_hbrg"));
            adresse_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("adresse_hbrg"));
            type_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("t.nom_type_hbrg"));
            num_place_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nbr_place_hbrg"));
            prix_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("prix_hbrg"));
            prop_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("p.nom_prop"));
            date_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("date_hbrg"));
            //Image im =);
            ImageView iv = new ImageView(new Image("file:///" + path + new PropertyValueFactory<Hebergement, String>("img_hbrg")));
            iv.setFitHeight(112);
            iv.setFitWidth(200);
            //img_hbrg.setCellValueFactory(iv);
            img_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("img_hbrg"));
            list = sh.getAllTriPriceDesc();
            ListHebergements.setItems(list);

        }
        if (s.equals("Par Prix ↑")) {
            list.clear();
            ListHebergements.setItems(list);
            nom_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nom_hbrg"));
            adresse_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("adresse_hbrg"));
            type_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("t.nom_type_hbrg"));
            num_place_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("nbr_place_hbrg"));
            prix_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("prix_hbrg"));
            prop_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("p.nom_prop"));
            date_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("date_hbrg"));
            //Image im =);
            ImageView iv = new ImageView(new Image("file:///" + path + new PropertyValueFactory<Hebergement, String>("img_hbrg")));
            iv.setFitHeight(112);
            iv.setFitWidth(200);
            //img_hbrg.setCellValueFactory(iv);
            img_hbrg.setCellValueFactory(new PropertyValueFactory<Hebergement, String>("img_hbrg"));
            list = sh.getAllTriPriceAsc();
            ListHebergements.setItems(list);

        }
    }

    @FXML
    private void Refresh(TouchEvent event) {
        list = (ObservableList<Hebergement>) sh.getAll();
        ListHebergements.setItems(list);
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

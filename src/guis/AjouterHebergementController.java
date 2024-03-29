/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import entities.Proprietaire;
import entities.TypeHebergement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceHebergement;
import services.ServiceProprietaire;
import services.ServiceTypeHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class AjouterHebergementController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfNom_hbrg;
    @FXML
    private TextField tfAdresse_hbrg;
    @FXML
    private TextField tfNbr_place_hbrg;
    @FXML
    private TextField tfPrix_hbrg;
    @FXML
    private ImageView img_hbrg;
    @FXML
    private Button btnImage;
    @FXML
    private ChoiceBox<TypeHebergement> cbType_hbrg;
    @FXML
    private DatePicker dpDate_hbrg;
    @FXML
    private ChoiceBox<Proprietaire> cbProprietaire;
    String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
    String uploads = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";
    @FXML
    private Button hebergement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceProprietaire sp = new ServiceProprietaire();
        ServiceTypeHebergement st = new ServiceTypeHebergement();

        List<TypeHebergement> LT = st.getAll();
        for (int i = 0; i < LT.size(); i++) {
            //cbType_hbrg.getItems().add(LT.get(i).getNom_type_hbrg());
            cbType_hbrg.getItems().add(LT.get(i));

        }

        List<Proprietaire> LP = sp.getAll();
        for (int i = 0; i < LP.size(); i++) {
            cbProprietaire.getItems().add(LP.get(i));
        }
    }

    @FXML
    private void ajouterHebergement(ActionEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (tfNom_hbrg.getText().isEmpty() || tfAdresse_hbrg.getText().isEmpty() || tfNbr_place_hbrg.getText().isEmpty() || tfPrix_hbrg.getText().isEmpty()
                || dpDate_hbrg.getValue().equals(null) || cbType_hbrg.getValue().equals(null)) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs vides !", ButtonType.OK);
            a.showAndWait();
        } else if ((!tfPrix_hbrg.getText().matches("\\d+") || Integer.parseInt(tfPrix_hbrg.getText()) <= 0)) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le prix doit etre positive ! numbers only", ButtonType.OK);
            a.showAndWait();
        } else if (!tfNbr_place_hbrg.getText().matches("\\d+") || Integer.parseInt(tfNbr_place_hbrg.getText()) <= 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le nombre de places doit etre positive ! numbers only", ButtonType.OK);
            a.showAndWait();
        } else if (dpDate_hbrg.getValue().isBefore(java.time.LocalDate.now())) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifier la date ", ButtonType.OK);
            a.showAndWait();

        } else if (cbProprietaire.getValue() == null) {
            ServiceHebergement sh = new ServiceHebergement();

            Hebergement h = new Hebergement(Integer.parseInt(tfNbr_place_hbrg.getText()), Integer.parseInt(tfPrix_hbrg.getText()), tfNom_hbrg.getText(),
                    tfAdresse_hbrg.getText(), dpDate_hbrg.getValue(), cbType_hbrg.getValue(), filename);
            sh.ajouter(h);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "hebergement ajoutée avec success Sans Proprietaire !", ButtonType.OK);
            a.showAndWait();
        } else {

            ServiceHebergement sh = new ServiceHebergement();

            Hebergement h = new Hebergement(Integer.parseInt(tfNbr_place_hbrg.getText()), Integer.parseInt(tfPrix_hbrg.getText()), tfNom_hbrg.getText(),
                    tfAdresse_hbrg.getText(), dpDate_hbrg.getValue(), cbProprietaire.getValue(), cbType_hbrg.getValue(), filename);
            sh.ajouterHebergementProprietaire(h);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "hebergement ajoutée avec success Avec Proprietaire !", ButtonType.OK);
            a.showAndWait();
        }

    }

    @FXML
    private void Image_hbrg(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        // Set the title of the displayed file dialog 
        fc.setTitle("Choisir une image");
        // Gets the extension filters used in the displayed file dialog. 
        fc.getExtensionFilters().clear();
        // Removes all of the elements from this list 
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        // Set the selected file or null if no file has been selected 
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname 
            img_hbrg.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();
//            System.out.println("INITAL filename : " + filename);
//            System.out.println("filepath : " + filepath);

            String extension = Arrays.stream(filename.split("\\.")).reduce((a, b) -> b).orElse(null);

            byte[] bytesOfMessage = filename.getBytes();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytesOfMessage, 0, filename.length());

            filename = new BigInteger(1, md.digest()).toString(16) + "." + extension;
            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
//            System.out.println("HASHED filename : " + filename);

        } else {
            System.out.println("Fichier invalide!");
        }
    }

    @FXML
    private void MenuHebergement(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuHebergement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Cancel(ActionEvent event) {
        tfNom_hbrg.setText("");
        tfAdresse_hbrg.setText("");
        tfNbr_place_hbrg.setText("");

        tfPrix_hbrg.setText("");

        img_hbrg.setImage(null);
        cbType_hbrg.setValue(null);
        dpDate_hbrg.setValue(null);
        cbProprietaire.setValue(null);
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

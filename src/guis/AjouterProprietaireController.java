/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Proprietaire;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ServiceProprietaire;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class AjouterProprietaireController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField tfNom_prop;
    @FXML
    private TextField tfPrenom_prop;
    @FXML
    private TextField tfEmail_prop;
    @FXML
    private TextField tfNum_tlf_pro;
    @FXML
    private ImageView img_prop;
    @FXML
    private Button btnImage;

    private Proprietaire p;

    String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
    String uploads = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    public void setA(Proprietaire a) {
        this.p = a;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterProprietaire(ActionEvent event) {

        /*
        if (tfNom_prop.getText().isEmpty() || tfPrenom_prop.getText().isEmpty() || tfEmail_prop.getText().isEmpty() || tfNum_tlf_pro.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs vides !", ButtonType.OK);
            a.showAndWait();
        } else if (!tfEmail_prop.getText().contains("@") || !tfEmail_prop.getText().contains(".")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Email invalide !", ButtonType.OK);
            a.showAndWait();
        } else if (tfNum_tlf_pro.getText().length() != 8) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le numero de telephone doit contenir 8 numbers !", ButtonType.OK);
            a.showAndWait();
        } else if (!tfNum_tlf_pro.getText().matches("\\d+")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Vérifier le numero de telephone ! numbers only", ButtonType.OK);
            a.showAndWait();
        } else {

            ServiceProprietaire sp = new ServiceProprietaire();
            Proprietaire p = new Proprietaire(Integer.parseInt(tfNum_tlf_pro.getText()), tfNom_prop.getText(), tfPrenom_prop.getText(), tfEmail_prop.getText(),
                    filename);
            sp.ajouter(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Proprietaire ajoutée avec success !", ButtonType.OK);
            a.showAndWait();

        }
         */
        if (tfNom_prop.getText().isEmpty() || tfPrenom_prop.getText().isEmpty() || tfEmail_prop.getText().isEmpty() || tfNum_tlf_pro.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajouter Proprietaire")
                    .text("Champs vides")
                    .graphic(null)
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //System.out.println("Supp");
                        }
                    });
            notificationBuilder.showError();
        } else if (!tfEmail_prop.getText().contains("@") || !tfEmail_prop.getText().contains(".")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajouter Proprietaire")
                    .text("Email invalide")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //System.out.println("Supp");
                        }
                    });
            notificationBuilder.showError();
        } else if (tfNum_tlf_pro.getText().length() != 8 && !tfNum_tlf_pro.getText().matches("\\d+")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajouter Proprietaire")
                    .text("Le numero de telephone doit contenir 8 numbers ! ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //System.out.println("Supp");
                        }
                    });
            notificationBuilder.showError();
        } else {

            ServiceProprietaire sp = new ServiceProprietaire();
            Proprietaire p = new Proprietaire(Integer.parseInt(tfNum_tlf_pro.getText()), tfNom_prop.getText(), tfPrenom_prop.getText(), tfEmail_prop.getText(),
                    filename);
            sp.ajouter(p);

            Notifications notificationBuilder = Notifications.create()
                    .title("Ajouter Proprietaire")
                    //.text("Un sms vous a été envoyé")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //System.out.println("Supp");
                        }
                    });
            notificationBuilder.showConfirm();
        }
    }

    @FXML
    private void Image_prop(ActionEvent event) throws IOException, NoSuchAlgorithmException {
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
            img_prop.setImage(new Image(file.toURI().toString()));

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
    private void MenuProprietaire(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuProprietaire.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Cancel(ActionEvent event) {
        tfNom_prop.setText("");
        tfPrenom_prop.setText("");
        tfEmail_prop.setText("");
        tfNum_tlf_pro.setText("");
    }

}

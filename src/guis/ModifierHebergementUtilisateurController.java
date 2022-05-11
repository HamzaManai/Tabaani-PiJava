/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import entities.Proprietaire;
import entities.TypeHebergement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import services.ServiceHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ModifierHebergementUtilisateurController implements Initializable {

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
    private ChoiceBox<?> cbType_hbrg;
    @FXML
    private DatePicker dpDate_hbrg;
    @FXML
    private ImageView qrCode;

    private Hebergement h;

    String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    public void setTfNom_hbrg(String nom) {
        tfNom_hbrg.setText(nom);
    }

    public void setTfAdresse_hbrg(String adresse) {
        tfAdresse_hbrg.setText(adresse);
    }

    public void setTfNbr_place_hbrg(String nbr) {
        tfNbr_place_hbrg.setText(nbr);
    }

    public void setTfPrix_hbrg(String prix) {
        tfPrix_hbrg.setText(prix);
    }

    /*
    public void setCbType_hbrg(TypeHebergement type) {
        cbType_hbrg.setValue(type);
    }
    
    */
    
    
    public void setDpDate_hbrg(LocalDate date) {
        dpDate_hbrg.setValue(date);
    }

    public void setImg_hbrg(String img) {
        Image im = new Image("file:///" + path + img);
        img_hbrg.setImage(im);
    }

    public void setH(Hebergement h) {
        this.h = h;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifierrHebergement(ActionEvent event) {

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

        } else {

            ServiceHebergement sh = new ServiceHebergement();

            //Hebergement h = new Hebergement(Integer.parseInt(tfNbr_place_hbrg.getText()), Integer.parseInt(tfPrix_hbrg.getText()), tfNom_hbrg.getText(),
            //  tfAdresse_hbrg.getText(), dpDate_hbrg.getValue(), cbProprietaire.getValue(), cbType_hbrg.getValue(), filename);
            h.setNom_hbrg(tfNom_hbrg.getText());
            h.setAdresse_hbrg(tfAdresse_hbrg.getText());
            h.setDate_hbrg(dpDate_hbrg.getValue());
            h.setNbr_place_hbrg(Integer.parseInt(tfNbr_place_hbrg.getText()));
            h.setPrix_hbrg(Integer.parseInt(tfPrix_hbrg.getText()));
            //h.setType(cbType_hbrg.getValue());
            h.setImg_hbrg(filename);
            sh.modifier(h);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "hebergement modifiee avec success Avec Proprietaire !", ButtonType.OK);
            a.showAndWait();
        }

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
            FileChannel dest = new FileOutputStream(path + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
//            System.out.println("HASHED filename : " + filename);

        } else {
            System.out.println("Fichier invalide!");
        }
    }

    @FXML
    private void ListHebergementUtilisateur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListHebergementUtilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void CodeQr(ActionEvent event) {
        String Id_stat = tfNom_hbrg.getText();

        String LoginCode = tfAdresse_hbrg.getText() + " " + tfPrix_hbrg.getText();
        ByteArrayOutputStream out = QRCode.from(LoginCode)
                .to(ImageType.PNG).stream();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File(Path_name + "/" + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public void affichImage() {
        String Id_stat = tfNom_hbrg.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        qrCode.setImage(im);
    }

}
